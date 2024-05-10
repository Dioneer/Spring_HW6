create table if not exists notes(
    id bigserial primary key,
    title varchar(125) not null,
    info varchar(254) not null,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP
);

insert into notes (title, info, created_time, update_time)
values ('My cofee', 'I need to drink cofee', '2024-05-10 13:38:00','2024-05-10 13:38:00'),
('Read book', 'I need to raed book', '2024-05-10 13:38:00','2024-05-10 13:38:00'),
('Swimming pool', 'go to the swimming pool', '2024-05-10 13:38:00','2024-05-10 13:38:00'),
('Relax', 'Relax in the evening', '2024-05-10 13:38:00','2024-05-10 13:38:00');