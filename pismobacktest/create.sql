create sequence hibernate_sequence start 1 increment 1;
create table accounts (account_id int8 not null, document_number varchar(20) not null, primary key (account_id));
create table operation_type (operation_type_id int4 not null, description varchar(255), primary key (operation_type_id));
create table transactions (transaction_id int8 not null, ammount numeric(19, 2) not null, event_date timestamp not null, account_id int8 not null, operation_type_id int4 not null, primary key (transaction_id));
alter table transactions add constraint FK20w7wsg13u9srbq3bd7chfxdh foreign key (account_id) references accounts;
alter table transactions add constraint FK5myt5nhwr3y6shiscij7p5iru foreign key (operation_type_id) references operation_type;
