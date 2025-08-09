package com.rbinternational.neura.timeplus.repository;

import com.rbinternational.neura.timeplus.dto.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
