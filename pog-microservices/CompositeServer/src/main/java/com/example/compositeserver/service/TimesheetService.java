package com.example.compositeserver.service;

import com.example.compositeserver.client.TimesheetClient;
import com.example.compositeserver.domain.Timesheet;
import com.example.compositeserver.domain.req.WeeksheetTSRequest;
import com.example.compositeserver.domain.req.WeeksheetsTSRequest;
import com.example.compositeserver.domain.res.WeeksheetTSResponse;
import com.example.compositeserver.domain.res.WeeksheetsTSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
