package com.rbinternational.neura.timeplus.controller;

import com.rbinternational.neura.timeplus.dto.Project;
import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.presenter.TimeRecordingPresenter;
import com.rbinternational.neura.timeplus.request.InsertTimeRecordingRequest;
import com.rbinternational.neura.timeplus.service.TimeRecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/timeplus")
@RequiredArgsConstructor
public class TimeRecordingController {

    private final TimeRecordingPresenter timeRecordingPresenter;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees/{employeeId}/projects/{projectId}")
    public void insertTimeRecordingForEmployee(@RequestBody Double workedHours, LocalDate date, @PathVariable String projectId) {
        timeRecordingPresenter.insertTimeRecord(workedHours, date, projectId);
    }

    @GetMapping("/employees/")
    public ResponseEntity<Map<String, Double>> getTimeRecordingsByEmployeeId() {
        Map<String, Double> response = timeRecordingPresenter.getTimeRecordingsByEmployeeId();
        return ResponseEntity.ok(response);
    }
}
