package com.example.compositeserver.service;

import com.example.compositeserver.client.TimesheetClient;
import com.example.compositeserver.domain.Timesheet;
import com.example.compositeserver.domain.Weeksheet;
import com.example.compositeserver.domain.req.WeeksheetTSRequest;
import com.example.compositeserver.domain.req.WeeksheetsTSRequest;
import com.example.compositeserver.domain.res.WeeksheetTSResponse;
import com.example.compositeserver.domain.res.WeeksheetsTSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TimesheetService {
    private TimesheetClient timesheetClient;

    @Autowired
    public TimesheetService(TimesheetClient timesheetClient) {
        this.timesheetClient = timesheetClient;
    }

    public List<Timesheet> getAllTimesheet() {
        return timesheetClient.getAllTimesheet();
    }

    public Timesheet getTimesheet(Integer userId) {
        return timesheetClient.getTimesheet(userId);
    }

    public WeeksheetsTSResponse getSummaryRecords(WeeksheetsTSRequest weeksheetsTSRequest) {
        return timesheetClient.getSummaryRecords(weeksheetsTSRequest);
    }

    public WeeksheetTSResponse getWeeklyRecord(WeeksheetTSRequest weeksheetTSRequest) {
        return timesheetClient.getWeeklyRecord(weeksheetTSRequest);
    }

    public Timesheet updateTimesheet(Timesheet timesheet){
        return timesheetClient.updateTimesheet(timesheet);
    }

    public WeeksheetTSResponse updateSingleWeeksheet(Weeksheet weeksheet, String weekEnding, int userId){
        return timesheetClient.updateSingleWeeksheet(weeksheet, weekEnding, userId);
    }

    public WeeksheetTSResponse getDefualtTemplate(Timesheet timesheet, String weekEnding){
        return timesheetClient.getDefualtTemplate(timesheet,weekEnding);
    };

    public Timesheet updateDefaultTemplate(Timesheet timesheet){
        return timesheetClient.updateDefaultTemplate(timesheet);
    }
}
