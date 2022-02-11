package com.project2.timesheet.service;

import com.project2.timesheet.domain.Daysheet;
import com.project2.timesheet.domain.Timesheet;
import com.project2.timesheet.domain.Weeksheet;
import com.project2.timesheet.domain.dto.DaysheetDTO;
import com.project2.timesheet.domain.dto.WeeksheetDTO;
import com.project2.timesheet.domain.res.WeeksheetTSResponse;
import com.project2.timesheet.repo.TimesheetRepository;
import com.project2.timesheet.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List<Timesheet> getAllTimesheet() {
        List<Timesheet> timesheetList = timesheetRepository.findAll();
        return timesheetList;
    }

    @Transactional
    public Timesheet findTimesheetByUserId(int userId) {
        return timesheetRepository.findTimesheetByUserId(userId).orElse(null);
    }

    @Transactional
    public List<Weeksheet> getWeeksheetByUserId(int userId) {
        Timesheet timesheet = findTimesheetByUserId(userId);
        return timesheet == null ? null : timesheet.getWeeks();
    }

    @Transactional
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

    //@Transactional
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

    @Transactional
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
        return getDaysheetByTemplate(timesheet.getDefaultTemplate(), weekEnding);
    }

    //@Transactional
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

    @Transactional
    public WeeksheetTSResponse getDefaultTemplate(Timesheet timesheet, String weekEnding, int userId) {
        WeeksheetTSResponse weeksheetTSResponse = new WeeksheetTSResponse();
        List<Daysheet> defaultTemplate = timesheet.getDefaultTemplate();
        List<DaysheetDTO> daysheetDTOS = new ArrayList<>();

        List<String> dates = new ArrayList<>();
        dates.add("Monday");
        dates.add("Tuesday");
        dates.add("Wednesday");
        dates.add("Thursday");
        dates.add("Friday");

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
        weeksheetTSResponse.setWeekEnding(weekEnding);
        weeksheetTSResponse.setDaysheetDTOS(daysheetDTOS);
        weeksheetTSResponse.setTotalBillingHours(0);
        weeksheetTSResponse.setTotalCompensatedHours(0);
        return weeksheetTSResponse;
    }

   // @Transactional
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

    @Transactional
    public WeeksheetTSResponse updateSingleWeeksheet(Weeksheet weeksheetInput, String weekEnding, int userId) {
        Timesheet timesheet = findTimesheetByUserId(userId);
        //System.out.println(timesheet.toString());
        if (timesheet == null) {
            return null;
        }

        List<Weeksheet> weeksheets = timesheet.getWeeks();
        //System.out.println(weeksheets.toString());
        WeeksheetTSResponse weeksheetTSResponse = new WeeksheetTSResponse();

        for (int i = 0; i < weeksheets.size(); i++) {
            Weeksheet weeksheet = weeksheets.get(i);
            if (weeksheet.getWeekEnding().equals(weekEnding)) {

                weeksheets.set(i, weeksheetInput);
                //System.out.println(weeksheet.toString());
                //System.out.println(weeksheets.toString());
                weeksheetTSResponse.setWeekEnding(weeksheet.getWeekEnding());
                weeksheetTSResponse.setTotalBillingHours(weeksheet.getTotalBillingHours());
                weeksheetTSResponse.setTotalCompensatedHours(weeksheet.getTotalCompensatedHours());
                weeksheetTSResponse.setDaysheetDTOS(null);
            }
        }
        timesheet.setWeeks(weeksheets);
        //System.out.println(weeksheets.toString());
        //System.out.println(timesheet.toString());
        timesheetRepository.save(timesheet);
        return weeksheetTSResponse;
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
