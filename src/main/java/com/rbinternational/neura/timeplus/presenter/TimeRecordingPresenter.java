package com.rbinternational.neura.timeplus.presenter;

import com.rbinternational.neura.timeplus.client.HrServiceClient;
import com.rbinternational.neura.timeplus.dto.Response.CreateLeaveRequestDto;
import com.rbinternational.neura.timeplus.dto.Response.Leave;
import com.rbinternational.neura.timeplus.service.TimeRecordingService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class TimeRecordingPresenter {

    private final TimeRecordingService timeRecordingService;
    private final HrServiceClient hrServiceClient;

    public TimeRecordingPresenter(TimeRecordingService timeRecordingService, HrServiceClient hrServiceClient) {
        this.timeRecordingService = timeRecordingService;
        this.hrServiceClient = hrServiceClient;
    }

    @Tool(name = "Insert Time Recording by Project Name, Worked Hours And Registered Date if it is given by user", description = "This tool inserts Time Recording by Project Name, Worked Hours And Registered Date if it is given by user. If date is not given then take today's date")
    public void insertTimeRecord(@ToolParam(description = "Worked Hours") Double workedHours, @ToolParam(description = "Registered Date") LocalDate date, @ToolParam(description = "Project Name") String projectName) {
        timeRecordingService.insertTimeRecording(workedHours, date, projectName);
    }

    @Tool(name = "Get Worked Hours for Employee", description = "Retrieves worked hours for a specific employee. Please when you return response return only worked hours.")
    public Map<String, Double> getTimeRecordingsByEmployeeId() {
        return timeRecordingService.getRecordedTimeByEmployeeId();
    }

    @Tool(name = "Get Employee leave details", description = "Fetches employee leave details from the HR service.")
    public List<Leave> getEmployeeLeaveDetails() {
        return hrServiceClient.getAllLeaves();
    }

    @Tool(name = "Get Remaining Leave Days", description = "Fetches remaining leave days for a specific employee from the HR service.")
    public Map<String, Integer> getRemainingLeaveDays(@ToolParam(description = "Employee ID") Long employeeId) {
        return hrServiceClient.getRemainingDays(employeeId);

    }

    @Tool(name = "Create Leave Request", description = "Creates a new leave request for an employee in the HR service.")
    public ResponseEntity<Leave> createLeaveRequest(@ToolParam(description = "Create Leave Request DTO") CreateLeaveRequestDto createLeave) {
        return hrServiceClient.createLeave(createLeave);
    }

    @Tool(name = "Update Leave Status", description = "Updates the status of an existing leave request in the HR service.")
    public Leave updateLeaveStatus(@ToolParam(description = "Leave ID") Long id, @ToolParam(description = "Status") String status) {
        return hrServiceClient.updateLeaveStatus(id, Map.of("status", status));
    }
}
