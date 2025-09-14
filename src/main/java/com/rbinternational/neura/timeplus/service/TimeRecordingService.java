package com.rbinternational.neura.timeplus.service;

import com.rbinternational.neura.timeplus.dto.Employee;
import com.rbinternational.neura.timeplus.dto.Project;
import com.rbinternational.neura.timeplus.dto.TimeRecording;
import com.rbinternational.neura.timeplus.repository.EmployeeRepository;
import com.rbinternational.neura.timeplus.repository.ProjectRepository;
import com.rbinternational.neura.timeplus.repository.TimeRecordingRepository;
import lombok.RequiredArgsConstructor;
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

    public void insertTimeRecording(Double workedHours, LocalDate date, String projectName) {
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

    public Map<String, Double> getRecordedTimeByEmployeeId() {
        List<TimeRecording> recordings =  timeRecordingRepository.findByEmployeeEmployeeId(1L);
        return recordings.stream()
                .collect(Collectors.groupingBy(
                        tr -> tr.getProject().getProjectName(),  // group by project name
                        Collectors.summingDouble(TimeRecording::getWorkedHours)
                ));

    }
}
