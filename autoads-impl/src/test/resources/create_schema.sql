DROP TABLE IF EXISTS brand;
CREATE TABLE IF NOT EXISTS brand(
                            id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                            name_brand VARCHAR(32) NOT NULL,
                            name_model VARCHAR(32) NOT NULL,
                            year VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS motor;
CREATE TABLE IF NOT EXISTS motor(
                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           motor_type VARCHAR(32) NOT NULL,
                           volume DOUBLE NOT NULL
);

DROP TABLE IF EXISTS image_auto;
CREATE TABLE IF NOT EXISTS image_auto(
                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           image_name VARCHAR(256) NOT NULL,
                           raster BYTEA NOT NULL
);

DROP TABLE IF EXISTS contact;
CREATE TABLE IF NOT EXISTS contact(
                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           email VARCHAR(256) NOT NULL,
                           phone VARCHAR(17) NOT NULL
);

DROP TABLE IF EXISTS auto;
CREATE TABLE IF NOT EXISTS auto(
                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                           id_image BIGINT NOT NULL REFERENCES image_auto (id),
                           id_brand BIGINT NOT NULL REFERENCES brand (id),
                           id_contact BIGINT NOT NULL REFERENCES contact (id),
                           id_motor BIGINT NOT NULL REFERENCES motor (id),
                           color VARCHAR(32) NOT NULL,
                           price DOUBLE NOT NULL,
                           transmission_type VARCHAR(32) NOT NULL,
                           drive_type VARCHAR(32) NOT NULL,
                           body_style VARCHAR(32) NOT NULL
);