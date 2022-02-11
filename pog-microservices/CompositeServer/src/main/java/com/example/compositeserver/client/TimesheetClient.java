package com.example.compositeserver.client;

import com.example.compositeserver.config.FeignHeadConfiguration;
import com.example.compositeserver.domain.Timesheet;
import com.example.compositeserver.domain.Weeksheet;
import com.example.compositeserver.domain.req.WeeksheetTSRequest;
import com.example.compositeserver.domain.req.WeeksheetsTSRequest;
import com.example.compositeserver.domain.res.WeeksheetTSResponse;
import com.example.compositeserver.domain.res.WeeksheetsTSResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "timesheet-service")
public interface TimesheetClient {
    @GetMapping("timesheet-service/all")
    List<Timesheet> getAllTimesheet();

    @GetMapping("timesheet-service/fetch")
    Timesheet getTimesheet(@RequestParam Integer userId);

    @PostMapping("timesheet-service/fetch-summary")
    WeeksheetsTSResponse getSummaryRecords(@RequestBody WeeksheetsTSRequest weeksheetsTSRequest);

    @PostMapping("timesheet-service/fetch-weekly-record")
    WeeksheetTSResponse getWeeklyRecord(@RequestBody WeeksheetTSRequest weeksheetTSRequest);

    @PutMapping("timesheet-service/update-timesheet")
    Timesheet updateTimesheet(@RequestBody Timesheet timesheet);

    @PutMapping("timesheet-service/update-weeksheet")
    WeeksheetTSResponse updateSingleWeeksheet(
            @RequestBody Weeksheet weeksheet,
            @RequestParam String weekEnding,
            @RequestParam int userId);

    @GetMapping("timesheet-service/create-week-sheet")
    WeeksheetTSResponse getDefualtTemplate(@RequestBody Timesheet timesheet,
                                            @RequestParam String weekEnding);

    @PutMapping("timesheet-service/update-template")
    Timesheet updateDefaultTemplate(@RequestBody Timesheet timesheet);
}
