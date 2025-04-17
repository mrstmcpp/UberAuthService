ALTER TABLE passenger
    ADD email VARCHAR(255) NOT NULL;

ALTER TABLE passenger
    ADD password VARCHAR(255) NOT NULL;

ALTER TABLE passenger
    ADD phone_number VARCHAR(255) NOT NULL;

ALTER TABLE passenger
    ADD CONSTRAINT uc_passenger_email UNIQUE (email);

ALTER TABLE passenger
    ADD CONSTRAINT uc_passenger_phonenumber UNIQUE (phone_number);

ALTER TABLE passenger
    MODIFY passanger_name VARCHAR(255) NOT NULL;