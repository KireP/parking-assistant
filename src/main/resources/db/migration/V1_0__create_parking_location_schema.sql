create table parking_location
(
    id                   bigserial primary key,
    price                double precision not null,
    zone                 text             not null,
    company              text             not null,
    company_phone_number text             not null,
    type                 text             not null
);

create table coordinate
(
    id                  bigserial primary key,
    latitude            double precision not null,
    longitude           double precision not null,
    parking_location_id bigint           not null,
    constraint fk_coordinate_parking_location foreign key (parking_location_id) references parking_location (id)
);

create table availability
(
    id           bigserial primary key,
    day          text not null,
    opening_hour text not null,
    closing_hour text not null,
    parking_location_id bigint           not null,
    constraint fk_availability_parking_location foreign key (parking_location_id) references parking_location (id)
);