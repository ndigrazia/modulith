DROP TABLE IF EXISTS inventory;

create table inventory
(
    id bigint generated by default as identity primary key,
    description varchar(255),
    name  varchar(255) not null constraint ukne303by1rwopd2swtivu9h3kd unique,
    price bigint not null
);


create index inv_name_idx on inventory (name);