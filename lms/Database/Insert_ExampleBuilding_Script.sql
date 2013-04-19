use lms;

INSERT INTO building (ID, CREATED, HBM_VERSION, FOREIGN_BUILDING_ID) 
VALUES (1, CURRENT_TIMESTAMP, 0,1);

INSERT INTO floor (ID, CREATED, HBM_VERSION, FOREIGN_FLOOR_ID,FOREIGN_BUILDING_ID) 
VALUES (1, CURRENT_TIMESTAMP, 0,1,1);

INSERT INTO floor (ID, CREATED, HBM_VERSION, FOREIGN_FLOOR_ID,FOREIGN_BUILDING_ID) 
VALUES (2, CURRENT_TIMESTAMP, 0,2,1);

INSERT INTO floor (ID, CREATED, HBM_VERSION, FOREIGN_FLOOR_ID,FOREIGN_BUILDING_ID) 
VALUES (3, CURRENT_TIMESTAMP, 0,3,1);

INSERT INTO room (ID, CREATED, HBM_VERSION, ROOM_TYPE, ENERGY_USAGE_DAY, ENERGY_USAGE_WEEK, ENERGY_USAGE_MONTH, FOREIGN_ROOM_ID, FOREIGN_FLOOR_ID) 
VALUES (1, CURRENT_TIMESTAMP, 0, 'O', 1, 2,3,1,1);