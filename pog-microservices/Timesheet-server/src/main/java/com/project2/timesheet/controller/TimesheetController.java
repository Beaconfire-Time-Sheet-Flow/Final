package com.project2.timesheet.controller;

//import com.netflix.discovery.converters.Auto;

import com.project2.timesheet.domain.Timesheet;
import com.project2.timesheet.domain.Weeksheet;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Api(tags = {"Timesheet - core service"})
@RequestMapping("timesheet-service")
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

    @PutMapping("/update-timesheet")
    @ApiOperation(value = "update timesheet", response = Timesheet.class)
    public ResponseEntity<Timesheet> updateTimesheet(@RequestBody Timesheet timesheet) {
        Timesheet res = timesheetService.updateTimesheet(timesheet);
        //WeeksheetsTSResponse weeksheetsTSResponse = new WeeksheetsTSResponse();
        return new ResponseEntity<Timesheet>(res, HttpStatus.OK);
    }

    @PutMapping("/update-weeksheet")
    @ApiOperation(value = "update weeksheet", response = WeeksheetTSResponse.class)
    public ResponseEntity<WeeksheetTSResponse> updateSingleWeeksheet(
            @RequestBody Weeksheet weeksheet,
            @RequestParam String weekEnding,
            @RequestParam int userId) {
        WeeksheetTSResponse res = timesheetService.updateSingleWeeksheet(weeksheet, weekEnding, userId);
        return new ResponseEntity<WeeksheetTSResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/create-week-sheet")
    @ApiOperation(value = "create a weeksheet", response = WeeksheetTSResponse.class)
    public ResponseEntity<WeeksheetTSResponse> getDefualtTemplate(@RequestBody Timesheet timesheet,
                                                                    @RequestParam String weekEnding){
        WeeksheetTSResponse weeksheetTSResponse = new WeeksheetTSResponse();

        weeksheetTSResponse = timesheetService.getDefaultTemplate(timesheet, weekEnding, timesheet.getUserId());
        return new ResponseEntity<WeeksheetTSResponse>(weeksheetTSResponse, HttpStatus.OK);
    }
}
