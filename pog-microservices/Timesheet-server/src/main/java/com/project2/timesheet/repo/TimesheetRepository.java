package com.project2.timesheet.repo;

import com.project2.timesheet.domain.Timesheet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//@EnableMongoRepositories
//@Repository
public interface TimesheetRepository extends MongoRepository<Timesheet, String>{

    //List<Timesheet> findAllByUserId(int uid);

    //Timesheet findByEndDateAndUserId(String endDate, int uid);

    Optional<Timesheet> findTimesheetByUserId(int uid);
}
