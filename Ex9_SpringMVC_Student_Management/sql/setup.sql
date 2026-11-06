-- Step 4: create the database and the student table.
-- Run in pgAdmin (Query Tool) or psql as the postgres user.

-- 1) Run this line while connected to the default "postgres" database:
CREATE DATABASE studentdb;

-- 2) Connect to "studentdb", then run the rest:
CREATE TABLE IF NOT EXISTS student (
    regno   INTEGER PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    cgpa    DOUBLE PRECISION NOT NULL
);

-- Optional sample data
INSERT INTO student (regno, name, cgpa) VALUES
    (101, 'Arun',  8.5),
    (102, 'Divya', 9.1)
ON CONFLICT (regno) DO NOTHING;

-- Step 13: use this to verify the changes made through the web application
SELECT * FROM student ORDER BY regno;
