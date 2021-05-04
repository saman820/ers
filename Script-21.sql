create table if not exists ers_reimbursement(
	reimb_id int primary key generated always as identity
	(start with 10000 increment by 1),
	reimb_amount DOUBLE PRECISION  not null,
	reimb_currency varchar(25) not null,
	reimb_submitted timestamp not null,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_receipt bytea,
	reimb_author int not null references ers_users(ers_users_id) on delete cascade,
	reimb_resolver int references ers_users(ers_users_id) on delete cascade,
	reimb_status_id int not null references ers_reimbursement_status(reimb_status_id) on delete cascade,
	reimb_type_id int not null references ers_reimbursement_type(reimb_type_id)
);

create table if not exists ers_reimbursement_status(
	reimb_status_id int primary key,
	reimb_status reimbstatus not null
);
create table if not exists ers_reimbursement_type(
	reimb_type_id int primary key,
	reimb_type reimbtype not null
);
create table if not exists ers_user_roles(
	ers_user_role_id int primary key,
	user_role reimbrole not null
);
create table if not exists ers_users(
	ers_users_id int primary key generated always as identity
	(start with 1000 increment by 1),
	ers_username varchar(50) unique not null,
	ers_password varchar(50) not null,
	user_first_name varchar(100) not null,
	user_last_name varchar(100) not null,
	user_email varchar(150) unique not null,
	user_role_id int not null references ers_user_roles(ers_user_role_id) on delete cascade
);
create type reimbstatus as enum ('PENDING', 'MODIFY','REJECTED', 'APPROVED', 'OTHER');
create type reimbtype as enum ('FOOD','GAS','TAXI', 'BUS','AIR TRAVEL', 'CLOTHING', 'OTHER');
create type reimbrole as enum ('FINANCE MANAGER', 'HR', 'SALES', 'ADMINISTRATIVE','BOOKKEEPING','FINANCE', 'OTHER');
create type reimbcurrency as enum ('USD', 'CAD', 'EURO', 'other');
drop type reimbstatus;
drop type reimbtype;
drop type reimbrole;



create or replace function create_reimbursement(amount DOUBLE PRECISION, currency varchar, author int, resolver int, type_id int)
returns varchar(10) as $$
begin 
	insert into ers_reimbursement (reimb_amount, reimb_currency, reimb_submitted, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
							values (amount, currency, current_timestamp , author, resolver, 1, type_id);
	return 'success';
end;
$$ language 'plpgsql';


create or replace function create_reimbursement_with_desc(amount DOUBLE PRECISION, currency varchar, description varchar, author int, resolver int, type_id int)
returns varchar(10) as $$
begin 
	insert into ers_reimbursement (reimb_amount, reimb_currency, reimb_submitted, reimb_description , reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
							values (amount, currency, current_timestamp , description, author, resolver, 1, type_id);
	return 'success';
end;
$$ language 'plpgsql';



create or replace function create_reimbursement_with_img(amount DOUBLE PRECISION, currency varchar,img bytea ,description varchar, author int, resolver int, type_id int)
returns varchar(10) as $$
begin 
	insert into ers_reimbursement (reimb_amount, reimb_currency, reimb_submitted, reimb_receipt ,reimb_description , reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
							values (amount, currency, current_timestamp ,img ,description, author, resolver, 1, type_id);
	return 'success';
end;
$$ language 'plpgsql';


select create_reimbursement_with_img(50,'CAD','k','lunch',1008,1007,2);

insert into ers_reimbursement (reimb_amount, reimb_currency, reimb_submitted, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values(200,'CAD', current_timestamp, 'airplane ticket', 1001,1000,1,5);


create or replace function create_user(username varchar, password varchar, fname varchar, lname varchar, email varchar, role_id int, salt varchar)
returns varchar(10) as $$
begin 
	insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id, salt) 
							values (username, password, fname, lname, email, role_id, salt);
	return 'success';
end;
$$ language 'plpgsql';



drop function create_reimbursement(amount DOUBLE PRECISION, currency varchar, description varchar, author int, resolver int, type_id int);

select create_reimbursement_with_desc(198.65, 'USD', 'airplane ticket', 1001 , 1000 , 5);
select create_reimbursement(798.65, 'USD', 1001,1000, 5);
DELETE from ers_reimbursement where reimb_resolver = 1001;

select create_user('user1', 'password1', 'saman', 'karimi', 'saman.820@gmail.com', 3, 'fdgchgj');
select create_user('user0', 'password0', 'super', 'super', 'saman.karimi@revature.net', 0);


--drop type reimbrole;
--alter type reimbrole add value 'other';
--alter type reimbstatus add value 'other';
--alter type reimbtype rename attribute 'clothes' to 'clothing' cascade;
--drop type reimbtype;
--drop table ers_reimbursement;
--drop table ers_reimbursement_type;
--drop table ers_reimbursement_status;
--drop table ers_user_roles;
--drop table ers_users;


insert into ers_reimbursement_status values(1,'PENDING');
insert into ers_reimbursement_status values(2,'MODIFY');
insert into ers_reimbursement_status values(3,'REJECTED');
insert into ers_reimbursement_status values(4,'APPROVED');
insert into ers_reimbursement_status values(5,'OTHER');
insert into ers_reimbursement_type values(1,'FOOD');
insert into ers_reimbursement_type values(2,'GAS');
insert into ers_reimbursement_type values(3,'TAXI');
insert into ers_reimbursement_type values(4,'BUS');
insert into ers_reimbursement_type values(5,'AIR TRAVEL');
insert into ers_reimbursement_type values(6,'CLOTHING');
insert into ers_reimbursement_type values(7,'OTHER');
insert into ers_user_roles values(0,'FINANCE MANAGER');
insert into ers_user_roles values(1,'HR');
insert into ers_user_roles values(2,'SALES');
insert into ers_user_roles values(3,'ADMINISTRATIVE');
insert into ers_user_roles values(4,'BOOKKEEPING');
insert into ers_user_roles values(5,'FINANCE');
insert into ers_user_roles values(6,'OTHER');


select * from ers_reimbursement ;
select * from ers_users;
select * from ers_reimbursement_status ;
select * from ers_reimbursement_type ;
select * from ers_user_roles;


select * from ers_reimbursement;
select * from ers_reimbursement where reimb_resolver=1000;
select * from ers_users where ers_username='user1';
UPDATE ers_reimbursement SET reimb_amount=880.2, reimb_currency='CAD', reimb_description='lunch in Vancouver', reimb_resolver=1000, reimb_type_id=1  WHERE reimb_id=1000;
select ers_username from ers_users where user_role_id=0;

truncate table ers_reimbursement ;
truncate table ers_reimbursement,ers_users ;
alter table ers_users add column hashed_password varchar(100);
alter table ers_users drop column hashed_password;
alter table ers_users add column salt varchar(100);
update ers_users set user_email='khkhdu@kjlh.com' where ers_users_id =1013;