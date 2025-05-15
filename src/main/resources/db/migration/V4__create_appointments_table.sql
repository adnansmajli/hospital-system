-- src/main/resources/db/migration/V4__create_appointments_table.sql

CREATE TABLE appointments
(
    id                    BIGSERIAL PRIMARY KEY,
    patient_id            BIGINT NOT NULL,
    first_name            VARCHAR(255),
    last_name             VARCHAR(255),
    doctor_id             BIGINT NOT NULL,
    appointment_date_time TIMESTAMP,
    date                  DATE,
    time                  TIME,
    status                VARCHAR(255),
    notes                 VARCHAR(255),
    CONSTRAINT fk_app_patient FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_app_doctor FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);
