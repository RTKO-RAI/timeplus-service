-- Insert into projects
INSERT INTO projects (name) VALUES 
  ('AI Research'),
  ('Internal Tools Development'),
  ('Client Platform Support');

-- Insert into employees
INSERT INTO employee (first_name, last_name, email, datlns) VALUES 
  ('Alice', 'Johnson', 'alice.johnson@example.com', '2022-03-15'),
  ('Bob', 'Smith', 'bob.smith@example.com', '2023-01-10'),
  ('Carla', 'Lee', 'carla.lee@example.com', '2024-06-01');

-- Insert into timing
-- Alice worked on AI Research in two different weeks
INSERT INTO timing (employee_id, project_id, week_start, service, working_hours) VALUES
  (1, 1, '2025-07-07', 'Model Evaluation', 32.5),
  (1, 1, '2025-07-14', 'Data Labeling', 40.0),

-- Bob worked on Internal Tools and Client Platform
  (2, 2, '2025-07-07', 'Frontend Fixes', 25.0),
  (2, 3, '2025-07-14', 'Customer Tickets', 35.5),

-- Carla worked full-time on Internal Tools
  (3, 2, '2025-07-07', 'API Integration', 40.0),
  (3, 2, '2025-07-14', 'Unit Testing', 40.0);
