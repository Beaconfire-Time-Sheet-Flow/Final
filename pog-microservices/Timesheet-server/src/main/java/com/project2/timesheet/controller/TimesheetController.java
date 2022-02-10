package com.project2.timesheet.controller;

//import com.netflix.discovery.converters.Auto;
import com.project2.timesheet.domain.*;
import com.project2.timesheet.domain.dto.WeeksheetDTO;
import com.project2.timesheet.domain.req.WeeksheetTSRequest;
import com.project2.timesheet.domain.req.WeeksheetsTSRequest;
import com.project2.timesheet.domain.res.WeeksheetTSResponse;
import com.project2.timesheet.domain.res.WeeksheetsTSResponse;
import com.project2.timesheet.service.TimesheetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.helpers.AttributesImpl;

import java.sql.Time;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Api(tags = {"Timesheet - core service"})
public class TimesheetController {

    private TimesheetService timesheetService;

    @Autowired
    public void setTimesheetService(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping("all")
    @ApiOperation(value = "List All Timesheet", response = Timesheet.class)
    public List<Timesheet> getAllTimesheet() {
        return timesheetService.getAllTimesheet();
    }

    @GetMapping("fetch")
    @ApiOperation(value = "Get the Timesheet for current user", response = Timesheet.class)
    public ResponseEntity<Timesheet> getTimesheet(@RequestParam Integer userId) {
        if(userId == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Timesheet timesheet = timesheetService.findTimesheetByUserId(userId);

        return new ResponseEntity<>(timesheet, HttpStatus.OK);
    }

    @PostMapping("fetch-summary")
    @ApiOperation(value = "Get the summary records for current user", response = WeeksheetsTSResponse.class)
    public ResponseEntity<WeeksheetsTSResponse> getSummaryRecords(@RequestBody WeeksheetsTSRequest weeksheetsTSRequest) {
        int userId = weeksheetsTSRequest.getUserId();

        WeeksheetsTSResponse weeksheetsTSResponse = new WeeksheetsTSResponse();
        List<WeeksheetDTO> weeksheetDTOS = timesheetService.getWeeksheetSummaryByUserId(userId);

        weeksheetsTSResponse.setWeeksheetDTOS(weeksheetDTOS);

        return new ResponseEntity<>(weeksheetsTSResponse, HttpStatus.OK);
    }

    @PostMapping("fetch-weekly-record")
    @ApiOperation(value = "Get the weekly record for the data", response = WeeksheetTSResponse.class)
    public ResponseEntity<WeeksheetTSResponse> getWeeklyRecord(@RequestBody WeeksheetTSRequest weeksheetTSRequest) {
        int userId = weeksheetTSRequest.getUserId();
        String weekEnding = weeksheetTSRequest.getWeekEnding();

        WeeksheetTSResponse weeksheetTSResponse = timesheetService.getWeeksheets(weekEnding, userId);

        if (weeksheetTSResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(weeksheetTSResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Timesheet> update(@RequestBody Timesheet timesheet) {
        Timesheet res = timesheetService.updateTimesheet(timesheet);
        //WeeksheetsTSResponse weeksheetsTSResponse = new WeeksheetsTSResponse();
        return new ResponseEntity<Timesheet>(res, HttpStatus.OK);
    }
}
