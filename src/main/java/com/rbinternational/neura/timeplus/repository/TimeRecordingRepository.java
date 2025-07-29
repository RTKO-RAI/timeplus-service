package com.rbinternational.neura.timeplus.repository;

import com.rbinternational.neura.timeplus.dto.TimeRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRecordingRepository extends JpaRepository<TimeRecording, Long> {

    List<TimeRecording> findByEmployeeId(Long employeeId);
}
