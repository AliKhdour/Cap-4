BEGIN TRANSACTION;


INSERT INTO appliance_type
        (type)
        VALUES('Refrigerator')
;
INSERT INTO appliance_type
        (type)
        VALUES('Range')
;
INSERT INTO appliance_type
        (type)
        VALUES('Dishwasher')
;
INSERT INTO appliance_type
        (type)
        VALUES('Washer')
;
INSERT INTO appliance_type
        (type)
        VALUES('Dryer')
;

INSERT INTO mechanical_asset_type
        (type)
        VALUES('Furnace')
;
INSERT INTO mechanical_asset_type
        (type)
        VALUES('Water Heater')
;
INSERT INTO mechanical_asset_type
        (type)
        VALUES('Air Conditioner')
;
INSERT INTO mechanical_asset_type
        (type)
        VALUES('Boiler')
;
INSERT INTO mechanical_asset_type
        (type)
        VALUES('Heat Pump')
;

INSERT INTO infrastructure_type
        (type)
        VALUES('Fence')
;
INSERT INTO infrastructure_type
        (type)
        VALUES('Roof')
;
INSERT INTO infrastructure_type
        (type)
        VALUES('Driveway')
;
INSERT INTO infrastructure_type
        (type)
        VALUES('Patio')
;



INSERT INTO question
        (question)
        VALUES('Model #')
;

INSERT INTO question
        (question)
        VALUES('Filter Changed')
;
INSERT INTO question
        (question)
        VALUES('Age')
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(1, 1)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(1, 2)
;       
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(1, 3)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(1, 4)
;  
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(1, 5)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(2, 1)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(2, 2)
;       
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(2, 3)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(2, 4)
;  
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(2, 5)
; 
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(3, 1)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(3, 2)
;       
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(3, 3)
;
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(3, 4)
;  
INSERT INTO type_question
        (question_id, appliance_type_id)
        VALUES(3, 5)
; 


INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Furnace','Replace','Replace the furnace filter','Reason','https://www.familyhandyman.com/heating-cooling/furnace-repair/how-to-change-a-furnace-filter/','3');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Water Heater','Flush','Flush out hot water from the water heater to remove accumulated sediment.','Reason','https://www.directenergy.com/blog/how-to-flush-a-water-heater/','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Refrigerator','Dust','Vacuum refrigerator and freezer coils and empty and clean drip trays','Reason','https://products.geappliances.com/appliance/gea-support-search-content?contentId=16266','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Roof','Inspect','Inspect roofing for missing, loose, or damaged shingles and leaks.','Reason','https://woodard247.com/roof-inspection-tips-7-easy-steps-to-inspecting-your-roof/','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Deck','Refinish','Refinish the deck','Reason','https://www.thespruce.com/clean-and-refinish-a-wood-deck-1825146','36');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Gutters','Clear','Remove leaves and debris from gutters and downspouts','Reason','https://www.lowes.com/n/how-to/gutter-cleaning-and-repair','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Septic Tank','Inspect','Have a professional inspect and pump the septic tank','Reason','https://www.epa.gov/septic/how-care-your-septic-system','36');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Chimney','Inspect','Inspect chimney for damage.','Reason','https://www.bhg.com/decorating/fireplace/styles/wood-burning-fireplace-and-stove-maintenance/','12');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Outdoor Faucet','Insulate','Insulate Outdoor Faucets','Reason','https://larryandsons.com/blog/plumbing-service/insulate-outdoor-faucets/','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Air Conditioner','Service','Have air-conditioning system serviced.','Reason','https://www.diynetwork.com/how-to/maintenance-and-repair/cleaning/10-easy-steps-to-keep-your-air-conditioning-unit-running-smoothl','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Dryer','Remove','Remove lint from dryer','Reason','https://www.familyhandyman.com/appliance-repair/washer-and-dryer-repair/dryer-lint-cleaning-tips/','12');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Dishwasher','Inspect','Inspect dishwasher for leaks','Reason','https://monkeysee.com/how-to-check-your-dishwasher-for-leaks/','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Garage Door','Maintain','Oil garage-door opener and chain, garage door, and all door hinges.','Reason','https://www.familyhandyman.com/doors/garage-door-repair/garage-door-tune-up/','12');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('General ','Inspect','Have home inspected for termites ','Reason','https://www.epa.gov/safepestcontrol/termites-how-identify-and-control-them','12');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Air Conditioner','Winterize','Winterize your central A/C unit','Reason','https://www.frostking.com/tips-and-tricks/air-conditioner-accessories/winterize-your-central-air-conditioner','6');
INSERT INTO task (type,task_name,task_description,reason,task_url,task_interval) VALUES ('Garbage Disposal','Clean','Clean the garbage disposal by grinding ice cubes, then flushing with hot water and baking soda','Reason','https://www.wikihow.com/Clean-Your-Garbage-Disposal','3');

   
END TRANSACTION;