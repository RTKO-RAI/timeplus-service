package com.rbinternational.neura.timeplus.controller;

import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.request.InsertTimeRecordingRequest;
import com.rbinternational.neura.timeplus.service.TimeRecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timeplus")
@RequiredArgsConstructor
public class TimeRecordingController {

    private final TimeRecordingService timeRecordingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees/{employeeId}/projects/{projectId}")
    public void insertTimeRecordingForEmployee(@RequestBody InsertTimeRecordingRequest request, @PathVariable Long employeeId, @PathVariable Long projectId) {
        timeRecordingService.insertTimeRecording(request, employeeId, projectId);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<List<TimeRecording>> getTimeRecordingsByEmployeeId(@PathVariable Long employeeId) {
        List<TimeRecording> response = timeRecordingService.getRecordedTimeByEmployeeId(employeeId);
        return ResponseEntity.ok(response);
    }
}
