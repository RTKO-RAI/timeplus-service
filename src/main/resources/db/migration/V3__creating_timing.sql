CREATE TABLE timing (
    timing_id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    week_start DATE NOT NULL,
	service VARCHAR(100) NOT NULL,
    working_hours DECIMAL(5,2) NOT NULL,

    CONSTRAINT fk_timing_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    CONSTRAINT fk_timing_project FOREIGN KEY (project_id) REFERENCES projects(id)
);