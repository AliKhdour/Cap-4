BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_task CASCADE;
DROP TABLE IF EXISTS user_account CASCADE;
DROP TABLE IF EXISTS home CASCADE;
DROP TABLE IF EXISTS appliance CASCADE;
DROP TABLE IF EXISTS mechanical_asset CASCADE;
DROP TABLE IF EXISTS infrastructure CASCADE;
DROP TABLE IF EXISTS appliance CASCADE;
DROP TABLE IF EXISTS appliance_type CASCADE;
DROP TABLE IF EXISTS mechanical_asset_type CASCADE;
DROP TABLE IF EXISTS infrastructure_type CASCADE;
DROP TABLE IF EXISTS question CASCADE;
DROP TABLE IF EXISTS type_question CASCADE;
DROP TABLE IF EXISTS task CASCADE;



DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_home_id;
DROP SEQUENCE IF EXISTS seq_appliance_id;
DROP SEQUENCE IF EXISTS seq_asset_id;
DROP SEQUENCE IF EXISTS seq_infrastructure_id;
DROP SEQUENCE IF EXISTS seq_user_task_id;
DROP SEQUENCE IF EXISTS seq_appliance_type_id;
DROP SEQUENCE IF EXISTS seq_mechanical_asset_type_id;
DROP SEQUENCE IF EXISTS seq_infrastructure_type_id;
DROP SEQUENCE IF EXISTS seq_question_id;
DROP SEQUENCE IF EXISTS seq_user_task_id;
DROP SEQUENCE IF EXISTS seq_task_id;
DROP SEQUENCE IF EXISTS seq_type_question_id;



CREATE TABLE user_account (
        user_id serial NOT NULL,
        first_name varchar(20) NOT NULL,
        last_name varchar(20) NOT NULL,
        email_address varchar(320) NOT NULL,
        notification_preference varchar(20) NOT NULL,
        phone_number varchar(320),
        
        CONSTRAINT pk_user_id PRIMARY KEY (user_id)
);


CREATE TABLE home (
        home_id serial NOT NULL,
        user_id int NOT NULL,
        street_address varchar(30) NOT NULL,
        address_two varchar(30),
        city varchar(30) NOT NULL,
        state varchar(30) NOT NULL,
        zip int NOT NULL,
        occupants int,
       
        CONSTRAINT pk_home_id PRIMARY KEY (home_id),
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user_account(user_id)

);


CREATE TABLE appliance (
        appliance_id serial NOT NULL,
        home_id int NOT NULL,
        type varchar(20) NOT NULL,
        age varchar,
        model_number varchar(50),
        
        CONSTRAINT pk_appliance_id PRIMARY KEY (appliance_id),
        CONSTRAINT fk_home_id FOREIGN KEY (home_id) REFERENCES home(home_id),
        CONSTRAINT appliance_type_check CHECK ((type = 'Refrigerator') OR (type = 'Dishwasher') OR (type = 'Washing Machine') OR (type = 'Dryer') OR (type = 'Range') OR (type = 'Oven'))
        
);

CREATE TABLE mechanical_asset (
        asset_id serial NOT NULL,
        home_id int NOT NULL,
        type varchar(20) NOT NULL,
        age date,
        model_number varchar(50),
        maintenance_duration int NOT NULL,
        
        CONSTRAINT pk_asset_id PRIMARY KEY (asset_id),
        CONSTRAINT fk_home_id FOREIGN KEY (home_id) REFERENCES home(home_id),
        CONSTRAINT mechanical_asset_type_check CHECK ((type = 'Furnace') OR (type = 'Water Heater') OR (type = 'Air Conditioner') OR (type = 'Boiler') OR (type = 'Heat Pump') OR (type = 'Forced Air') )
        
);

CREATE TABLE infrastructure (
        infrastructure_id serial NOT NULL,
        home_id int NOT NULL,
        type varchar(20) NOT NULL,
        age date,
        model_number varchar(50),
        maintenance_duration int,
        
        CONSTRAINT pk_infrastructure_id PRIMARY KEY (infrastructure_id),
        CONSTRAINT fk_home_id FOREIGN KEY (home_id) REFERENCES home(home_id),
        CONSTRAINT infrastructure_type_check CHECK ((type = 'Fence') OR (type = 'Roof') OR (type = 'Driveway') OR (type = 'Patio'))
        
);

CREATE TABLE task (
        the_task_id serial,
        type varchar,
        task_name varchar,
        task_description text,
        reason text,
        task_url text,
        task_interval varchar(40),
        
        CONSTRAINT pk_the_task_id PRIMARY KEY (the_task_id)
);


CREATE TABLE user_task(
        user_task_id serial NOT NULL,
        appliance_id int,
        asset_id int,
        infrastructure_id int,
        home_id int NOT NULL,
        task_id int NOT NULL,
        date_last_performed varchar,
        associated_products varchar,
        maintenance_duration int,
        
        CONSTRAINT pk_user_task_id PRIMARY KEY (user_task_id),
        CONSTRAINT fk_home_id FOREIGN KEY (home_id) REFERENCES home(home_id),
        CONSTRAINT fk_infrastructure_id FOREIGN KEY (infrastructure_id) REFERENCES infrastructure(infrastructure_id),
        CONSTRAINT fk_asset_id FOREIGN KEY (asset_id) REFERENCES mechanical_asset(asset_id),
        CONSTRAINT fk_appliance_id FOREIGN KEY (appliance_id) REFERENCES appliance(appliance_id),
        CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES task(the_task_id)
);

CREATE TABLE appliance_type (
        appliance_type_id serial NOT NULL,
        type varchar(20) NOT NULL,
        
        CONSTRAINT pk_type_id PRIMARY KEY (appliance_type_id)
);

CREATE TABLE mechanical_asset_type(
        mechanical_asset_type_id serial NOT NULL,
        type varchar(20) NOT NULL,
        
        CONSTRAINT pk_mechanical_asset_type_id PRIMARY KEY (mechanical_asset_type_id)
);

CREATE TABLE infrastructure_type (
        infrastructure_type_id serial NOT NULL,
        type varchar(20) NOT NULL,
        
        CONSTRAINT pk_infrastructure_type_id PRIMARY KEY (infrastructure_type_id)
);

CREATE TABLE question (
        question_id serial NOT NULL,
        question varchar NOT NULL,
        sub_question varchar,
        
        CONSTRAINT pk_question_id PRIMARY KEY (question_id)
);
CREATE TABLE type_question (
        type_question_id serial,
        mechanical_type_id int,
        appliance_type_id int,
        infrastructure_type_id int,
        question_id int,
        
        CONSTRAINT pk_type_question_id PRIMARY KEY (type_question_id),
        CONSTRAINT fk_mechanical_type_id FOREIGN KEY (mechanical_type_id) REFERENCES mechanical_asset_type(mechanical_asset_type_id),
        CONSTRAINT fk_appliance_type_id FOREIGN KEY (appliance_type_id) REFERENCES appliance_type(appliance_type_id),
        CONSTRAINT fk_infrastructure_type_id FOREIGN KEY (infrastructure_type_id) REFERENCES infrastructure_type(infrastructure_type_id),
        CONSTRAINT fk_question_id FOREIGN KEY (question_id) REFERENCES question(question_id)

);


END TRANSACTION;    
