package com.example.compositeserver.controller;

import com.example.compositeserver.domain.Timesheet;
import com.example.compositeserver.domain.Weeksheet;
import com.example.compositeserver.domain.req.WeeksheetTSRequest;
import com.example.compositeserver.domain.req.WeeksheetsTSRequest;
import com.example.compositeserver.domain.res.WeeksheetTSResponse;
import com.example.compositeserver.domain.res.WeeksheetsTSResponse;
import com.example.compositeserver.service.TimesheetService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = {"Timesheet - core service"})
public class TimesheetController {
    private TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping("/all")
    public List<Timesheet> getAllTimesheet() {
        return timesheetService.getAllTimesheet();
    }

    @GetMapping("/fetch")
    public ResponseEntity<Timesheet> getTimesheet(@RequestParam Integer userId) {
        return ResponseEntity.ok().body(timesheetService.getTimesheet(userId));
    }

    @PostMapping("fetch-summary")
    public ResponseEntity<WeeksheetsTSResponse> getSummaryRecords(@RequestBody WeeksheetsTSRequest weeksheetsTSRequest) {
        return ResponseEntity.ok().body(timesheetService.getSummaryRecords(weeksheetsTSRequest));
    }

    @PostMapping("fetch-weekly-record")
    public ResponseEntity<WeeksheetTSResponse> getWeeklyRecord(@RequestBody WeeksheetTSRequest weeksheetTSRequest) {
        return ResponseEntity.ok().body(timesheetService.getWeeklyRecord(weeksheetTSRequest));
    }

    @PutMapping("update-timesheet")
    public ResponseEntity<Timesheet> updateTimesheet(@RequestBody Timesheet timesheet){
        return ResponseEntity.ok().body(timesheetService.updateTimesheet(timesheet));
    }

    @PutMapping("update-weeksheet")
    public ResponseEntity<WeeksheetTSResponse> updateSingleWeeksheet(
            @RequestBody Weeksheet weeksheet,
            @RequestParam String weekEnding,
            @RequestParam int userId){
        return ResponseEntity.ok().body(timesheetService.updateSingleWeeksheet(weeksheet,weekEnding,userId));
    }

    @GetMapping("create-week-sheet")
    public ResponseEntity<WeeksheetTSResponse> getDefualtTemplate(@RequestBody Timesheet timesheet,
                                           @RequestParam String weekEnding){
        return ResponseEntity.ok().body(timesheetService.getDefualtTemplate(timesheet,weekEnding));
    }

    @PutMapping("/update-template")
    public ResponseEntity<Timesheet> updateDefaultTemplate(@RequestBody Timesheet timesheet){
        return ResponseEntity.ok().body(timesheetService.updateDefaultTemplate(timesheet));
    }
}
