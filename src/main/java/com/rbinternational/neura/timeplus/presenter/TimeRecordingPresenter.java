package com.rbinternational.neura.timeplus.presenter;

import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.request.InsertTimeRecordingRequest;
import com.rbinternational.neura.timeplus.service.TimeRecordingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeRecordingPresenter {

    private final TimeRecordingService timeRecordingService;

    public TimeRecordingPresenter(TimeRecordingService timeRecordingService) {
        this.timeRecordingService = timeRecordingService;
    }

    public void insertTimeRecord(InsertTimeRecordingRequest request, Long employeeId, Long projectId) {
        timeRecordingService.insertTimeRecording(request, employeeId, projectId);
    }

    public List<TimeRecording> getTimeRecordingsByEmployeeId(Long employeeId) {
        return timeRecordingService.getRecordedTimeByEmployeeId(employeeId);
    }
}
