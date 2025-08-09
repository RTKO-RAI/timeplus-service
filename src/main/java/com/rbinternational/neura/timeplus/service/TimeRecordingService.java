package com.rbinternational.neura.timeplus.service;

import com.rbinternational.neura.timeplus.dto.Employee;
import com.rbinternational.neura.timeplus.dto.Project;
import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.repository.EmployeeRepository;
import com.rbinternational.neura.timeplus.repository.ProjectRepository;
import com.rbinternational.neura.timeplus.repository.TimeRecordingRepository;
import com.rbinternational.neura.timeplus.request.InsertTimeRecordingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TimeRecordingService {

    private final TimeRecordingRepository timeRecordingRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;


    private Integer getWeekOfYear(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.GERMANY);
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    @Tool(name = "InsertTimeRecording", description = "Inserts a new time recording for an employee on a specific project.")
    public void insertTimeRecording(InsertTimeRecordingRequest request, Long employeeId, Long projectId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        LocalDate registeredDate =  request.date() == null ? LocalDate.now() : request.date();
        TimeRecording entity = TimeRecording.builder()
                .workedHours(request.workedHours())
                .weekNumber(getWeekOfYear(registeredDate))
                .employee(employee)
                .project(project)
                .build();
        timeRecordingRepository.save(entity);
    }

    @Tool(name = "GetRecordedTimeByEmployeeId", description = "Retrieves all time recordings for a specific employee with employeeId.")
    public List<TimeRecording> getRecordedTimeByEmployeeId(Long employeeId) {
        return timeRecordingRepository.findByEmployeeId(employeeId);
    }
}
