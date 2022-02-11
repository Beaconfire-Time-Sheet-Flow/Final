package com.example.compositeserver.client;

import com.example.compositeserver.config.FeignHeadConfiguration;
import com.example.compositeserver.domain.Timesheet;
import com.example.compositeserver.domain.req.WeeksheetTSRequest;
import com.example.compositeserver.domain.req.WeeksheetsTSRequest;
import com.example.compositeserver.domain.res.WeeksheetTSResponse;
import com.example.compositeserver.domain.res.WeeksheetsTSResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "timesheet-service")
public interface TimesheetClient {
    @GetMapping("timesheet-service/all")
    List<Timesheet> getAllTimesheet();

    @GetMapping("timesheet/fetch")
    Timesheet getTimesheet(@RequestParam Integer userId);

    @PostMapping("fetch-summary")
    WeeksheetsTSResponse getSummaryRecords(@RequestBody WeeksheetsTSRequest weeksheetsTSRequest);

    @PostMapping("fetch-weekly-record")
    WeeksheetTSResponse getWeeklyRecord(@RequestBody WeeksheetTSRequest weeksheetTSRequest);
}
