package com.rbinternational.neura.timeplus.presenter;

import com.rbinternational.neura.timeplus.dto.Project;
import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.request.InsertTimeRecordingRequest;
import com.rbinternational.neura.timeplus.service.TimeRecordingService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class TimeRecordingPresenter {

    private final TimeRecordingService timeRecordingService;

    public TimeRecordingPresenter(TimeRecordingService timeRecordingService) {
        this.timeRecordingService = timeRecordingService;
    }

    public void insertTimeRecord(Double workedHours, LocalDate date, String projectId) {
        timeRecordingService.insertTimeRecording(workedHours, date, projectId);
    }

    public Map<String, Double> getTimeRecordingsByEmployeeId() {
        return timeRecordingService.getRecordedTimeByEmployeeId();
    }
}
