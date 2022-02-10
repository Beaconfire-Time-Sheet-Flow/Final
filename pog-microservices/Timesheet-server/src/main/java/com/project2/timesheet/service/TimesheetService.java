package com.project2.timesheet.service;

import com.project2.timesheet.domain.*;
import com.project2.timesheet.domain.dto.DaysheetDTO;
import com.project2.timesheet.domain.dto.WeeksheetDTO;
import com.project2.timesheet.domain.res.WeeksheetTSResponse;
import com.project2.timesheet.domain.res.WeeksheetsTSResponse;
import com.project2.timesheet.repo.TimesheetRepository;
import com.project2.timesheet.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;
    @Autowired
    private DateConverter dateConverter;

    @Autowired
    public void setTimesheetRepository(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public List<Timesheet> getAllTimesheet() {
        List<Timesheet> timesheetList = timesheetRepository.findAll();
        return timesheetList;
    }

    public Timesheet findTimesheetByUserId(int userId) {
        return timesheetRepository.findTimesheetByUserId(userId).orElse(null);
    }

    public List<Weeksheet> getWeeksheetByUserId(int userId) {
        Timesheet timesheet = findTimesheetByUserId(userId);
        return timesheet == null ? null : timesheet.getWeeks();
    }

    public List<WeeksheetDTO> getWeeksheetSummaryByUserId(int userId) {
        List<WeeksheetDTO> weeksheetDTOS = new ArrayList<>();
        List<Weeksheet> weeksheets = getWeeksheetByUserId(userId);

        if (weeksheets == null) {
            return null;
        }

        for (Weeksheet weeksheet : weeksheets) {
            weeksheetDTOS.add(getWeeksheetDTO(weeksheet));
        }
        return weeksheetDTOS;
    }

    private WeeksheetDTO getWeeksheetDTO(Weeksheet weeksheet) {
        WeeksheetDTO weeksheetDTO = new WeeksheetDTO();

        weeksheetDTO.setWeekEnding(weeksheet.getWeekEnding());
        weeksheetDTO.setTotalBillingHours(weeksheet.getTotalBillingHours());
        weeksheetDTO.setTotalCompensatedHours(weeksheet.getTotalCompensatedHours());
        weeksheetDTO.setSubmissionStatus(weeksheet.getSubmissionStatus());
        weeksheetDTO.setApprovalStatus(weeksheet.getApprovalStatus());
        weeksheetDTO.setFloatingDaysLeft(weeksheet.getFloatingDaysLeft());
        weeksheetDTO.setVacationDaysLeft(weeksheet.getVacationDaysLeft());

        return weeksheetDTO;
    }

    public WeeksheetTSResponse getWeeksheets(String weekEnding, int userId) {
        Timesheet timesheet =findTimesheetByUserId(userId);
        if (timesheet == null) {
            return null;
        }

        List<Weeksheet> weeksheets = timesheet.getWeeks();

        for (Weeksheet weeksheet : weeksheets) {
            if (weeksheet.getWeekEnding().equals(weekEnding)) {
                return getDaysheetByWeekEnding(weeksheet);
            }
        }
        return getDaysheetByTemplate(timesheet.getDays(), weekEnding);
    }

    private WeeksheetTSResponse getDaysheetByTemplate(List<Daysheet> defaultTemplate, String weekEnding) {
        WeeksheetTSResponse weeksheetTSResponse = new WeeksheetTSResponse();

        List<DaysheetDTO> daysheetDTOS = new ArrayList<>();

        List<String> dates = dateConverter.getDatesFromWeekEnding(weekEnding, defaultTemplate.size());

        for (int i = 0; i < defaultTemplate.size(); i++) {
            DaysheetDTO daysheetDTO = new DaysheetDTO();

            daysheetDTO.setDate(dates.get(i));
            daysheetDTO.setDay(defaultTemplate.get(i).getDay());
            daysheetDTO.setStartTime(defaultTemplate.get(i).getStartTime());
            daysheetDTO.setEndTime(defaultTemplate.get(i).getEndTime());
            daysheetDTO.setIfFloating((defaultTemplate.get(i).isFloating()));
            daysheetDTO.setIfHoliday(defaultTemplate.get(i).isHoliday());
            daysheetDTO.setIfVacation(defaultTemplate.get(i).isVacation());

            daysheetDTOS.add(daysheetDTO);
        }

        weeksheetTSResponse.setDaysheetDTOS(daysheetDTOS);
        weeksheetTSResponse.setTotalBillingHours(0);
        weeksheetTSResponse.setTotalCompensatedHours(0);
        return weeksheetTSResponse;
    }

    private WeeksheetTSResponse getDaysheetByWeekEnding(Weeksheet weeksheet) {
        WeeksheetTSResponse weeksheetTSResponse = new WeeksheetTSResponse();
        List<DaysheetDTO> daysheetDTOS = new ArrayList<>();
        List<Daysheet> daysheets = weeksheet.getDays();

        int totalBillingHours = weeksheet.getTotalBillingHours();
        int totalCompensatedHours = weeksheet.getTotalCompensatedHours();

        for (Daysheet daysheet : daysheets) {
            DaysheetDTO daysheetDTO = new DaysheetDTO();

            daysheetDTO.setDate(daysheet.getDate());
            daysheetDTO.setDay(daysheet.getDay());
            daysheetDTO.setStartTime(daysheet.getStartTime());
            daysheetDTO.setEndTime(daysheet.getEndTime());
            daysheetDTO.setIfFloating(daysheet.isFloating());
            daysheetDTO.setIfHoliday(daysheet.isHoliday());
            daysheetDTO.setIfVacation(daysheet.isVacation());

            daysheetDTOS.add(daysheetDTO);
        }

        weeksheetTSResponse.setDaysheetDTOS(daysheetDTOS);
        weeksheetTSResponse.setTotalBillingHours(totalBillingHours);
        weeksheetTSResponse.setTotalCompensatedHours(totalCompensatedHours);

        return weeksheetTSResponse;
    }


//    public ResponseEntity<List<Timesheet>> getAllTimesheets(int uid) {
//        List<Timesheet> list = timesheetPepository.findAllByUserId(uid);
//        return ResponseEntity.status(HttpStatus.CREATED).body(list);
//    }
//
//    public ResponseEntity<Timesheet> getSingleTimesheet(String endDate, int uid){
//        Timesheet report = timesheetPepository.findByEndDateAndUserId(endDate, uid);
//        return ResponseEntity.status(HttpStatus.CREATED).body(report);
//    }
    @Transactional
    public Timesheet updateTimesheet(Timesheet timesheet) {
        //System.out.println(timesheet.toString());
        timesheetRepository.save(timesheet);
        return timesheetToDomain(timesheet);
    }
    Timesheet timesheetToDomain(Timesheet timesheet) {
        List<Weeksheet> weeksheets = timesheet.getWeeks();
        return timesheet;
    }
//        try {
//            System.out.println(timesheetMap.toString());
//
//            int uid = (Integer) timesheetMap.get(0);
//            String WeekEnd = (String) timesheetMap.get(1);
//            Timesheet timesheet = timesheetRepository.findByEndDateAndUserId(WeekEnd, uid);
//
//            timesheet.setTotalBillingHours(Double.parseDouble((String) timesheetMap.get(2)));
//            timesheet.setTotalCompensatedHours(Double.parseDouble((String) timesheetMap.get(3)));
//            timesheet.setApprovalStatus((String) timesheetMap.get(4));
//            timesheet.setSubmissionStatus((String) timesheetMap.get(5));
//
//            double hours = 0.0;
//
//            String file = (String) timesheetMap.get(8);
//
//        }


}
