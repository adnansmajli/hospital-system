-- src/main/resources/db/migration/V2__Add_role_enum.sql
ALTER TABLE users
ALTER
COLUMN role TYPE VARCHAR(10)
USING role::VARCHAR(10);