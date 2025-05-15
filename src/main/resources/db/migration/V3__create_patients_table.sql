CREATE TABLE patients
(
    id        BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    personal_no  VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    address      VARCHAR(255),
    city         VARCHAR(255),
    country      VARCHAR(255),
    notes        VARCHAR(255),
    gender       CHAR(1),
    birth_date   DATE,
    active       BOOLEAN,
    photo        VARCHAR(255),
    nationality  VARCHAR(255),
    doctor_id    BIGINT,
    CONSTRAINT fk_patient_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctors (id)
);
