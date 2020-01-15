INSERT INTO home
        (user_id, street_address, address_two, city, state, zip, occupants)
        VALUES(1, '12333 First Street', 'Apt 11', 'Stow', 'OH', 44720, 5);
INSERT INTO home
        (user_id, street_address, address_two, city, state, zip, occupants)
        VALUES(1, '456 Second Street', 'Apt 11', 'Stow', 'OH', 44720, 5);
INSERT INTO appliance
        (home_id,type,age,model_number)
        VALUES(1, 'Refrigerator', '1', 'T1000');
INSERT INTO appliance
        (home_id,type,age,model_number)
        VALUES(1, 'Dryer', '1', 'T2000');