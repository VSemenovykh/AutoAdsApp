insert into brand (id, name_brand, name_model, year) values (1, 'AUDI', 'A3', '2016');
insert into brand (id, name_brand, name_model, year) values (2, 'AUDI', 'A4', '2019');
insert into brand (id, name_brand, name_model, year) values (3, 'AUDI', 'A8', '2015');
insert into brand (id, name_brand, name_model, year) values (4, 'FORD', 'FIESTA', '2016');
insert into brand (id, name_brand, name_model, year) values (5, 'FORD', 'FOCUS', '2019');

insert into motor (id, motor_type, volume) values (1, 'DIESEL', 1.0);
insert into motor (id, motor_type, volume) values (2, 'DIESEL', 1.3);
insert into motor (id, motor_type, volume) values (3, 'DIESEL', 1.6);
insert into motor (id, motor_type, volume) values (4, 'DIESEL', 2.0);
insert into motor (id, motor_type, volume) values (5, 'DIESEL', 2.4);

insert into image_auto (id, image_name, raster) values (1,'A3', local ('./image-auto/Audi/AUDI-A3.JPG')); --?
insert into image_auto (id, image_name, raster) values (2,'A4', local('./image-auto/Audi/AUDI-A4.JPG'));  --?
insert into image_auto (id, image_name, raster) values (3,'A8', local('./image-auto/Audi/AUDI-A8.JPG'));  --?
insert into image_auto (id, image_name, raster) values (4,'FIESTA', local('./image-auto/Ford/FORD-FIESTA.JPG')); --?
insert into image_auto (id, image_name, raster) values (5,'FOCUS', local('./image-auto/Ford/FORD-FOCUS.JPG'));   --?

insert into contact (id, email, phone) values (1, 'audi@gmail.com', '+7(111)-111-11-11');
insert into contact (id, email, phone) values (2, 'ford@gmail.com', '+7(222)-222-22-22');
insert into contact (id, email, phone) values (3, 'honda@gmail.com', '+7(333)-333-33-33');
insert into contact (id, email, phone) values (4, 'hyundai@gmail.com', '+7(444)-444-44-44');
insert into contact (id, email, phone) values (5, 'bmv@gmail.com', '+7(555)-555-55-55');

insert into auto (id, id_image, id_brand, id_contact, id_motor, color, price, transmission_type, drive_type, body_style)
values (1, 1, 1, 1, 4, 'GRAY', 1935000, 'DSG', 'FWD', 'SEDAN');
insert into auto (id, id_image, id_brand, id_contact, id_motor, color, price, transmission_type, drive_type, body_style)
values (2, 2, 2, 1, 4, 'BLUE', 2850000, 'DSG', 'FWD', 'STATION WAGON');
insert into auto (id, id_image, id_brand, id_contact, id_motor, color, price, transmission_type, drive_type, body_style)
values (3, 3, 3, 1, 6, 'WHITE', 10000000, 'AUTOMATIC', 'AWD', 'SEDAN');
insert into auto (id, id_image, id_brand, id_contact, id_motor, color, price, transmission_type, drive_type, body_style)
values (4, 4, 4, 2, 3, 'RED', 726000, 'MANUAL', 'FWD', 'SEDAN');
insert into auto (id, id_image, id_brand, id_contact, id_motor, color, price, transmission_type, drive_type, body_style)
values (5, 5, 5, 2, 3, 'BLUE', 1440500, 'AUTOMATIC', 'FWD', 'SEDAN');
