package com.rbinternational.neura.timeplus.service;

import com.rbinternational.neura.timeplus.dto.Employee;
import com.rbinternational.neura.timeplus.dto.Project;
import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.repository.EmployeeRepository;
import com.rbinternational.neura.timeplus.repository.ProjectRepository;
import com.rbinternational.neura.timeplus.repository.TimeRecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Tool(name = "Insert Time Recording by Project Name, Worked Hours And Registered Date if it is given by user", description = "This tool inserts Time Recording by Project Name, Worked Hours And Registered Date if it is given by user. If date is not given then take today's date")
    public void insertTimeRecording(@ToolParam(description = "Worked Hours") Double workedHours, @ToolParam(description = "Registered Date") LocalDate date, @ToolParam(description = "Project Name") String projectName) {
        Employee employee = employeeRepository.findById(1L).orElseThrow();
        Project project = projectRepository.findByProjectName(projectName).orElseThrow();
        LocalDate registeredDate =  date == null ? LocalDate.now() : date;
        TimeRecording entity = TimeRecording.builder()
                .workedHours(workedHours)
                .weekNumber(getWeekOfYear(registeredDate))
                .employee(employee)
                .project(project)
                .build();
        timeRecordingRepository.save(entity);
    }

    @Tool(name = "Get Worked Hours for Employee", description = "Retrieves worked hours for a specific employee. Please when you return response return only worked hours.")
    public Map<String, Double> getRecordedTimeByEmployeeId() {
        List<TimeRecording> recordings =  timeRecordingRepository.findByEmployeeEmployeeId(1L);
        return recordings.stream()
                .collect(Collectors.groupingBy(
                        tr -> tr.getProject().getProjectName(),  // group by project name
                        Collectors.summingDouble(TimeRecording::getWorkedHours)
                ));

    }
}
