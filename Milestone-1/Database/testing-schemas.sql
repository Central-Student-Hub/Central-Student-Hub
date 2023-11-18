use `central-student-hub`;

-- Test #1
insert into useraccountkeys (userName, userSSN)
values ('Ibrahim', 'xx-xxx-xxx-xxx-xyy'),
       ('ZiadRedaSaad', 'xx-xxx-xxx-xxx-xxy'),
       ('Ali', 'xx-xxx-xxx-xxx-xxz'),
       ('Marwan', 'xx-xxx-xxx-xxx-xyx'),
       ('Mostafa', 'xx-xxx-xxx-xxx-xzx'),
       ('MuhammedAdelTaha', 'xx-xxx-xxx-xxx-xxx');

-- Test #2
insert into useraccountkeys (userName, userSSN)
values ('Ibrahim', 'xx-xxx-xxx-xxx-zzz');

insert into useraccountkeys (userName, userSSN)
values ('Mahmoud', 'xx-xxx-xxx-xxx-xyy');

insert into useraccountkeys (userName, userSSN)
values ('Ibrahim', 'xx-xxx-xxx-xxx-xyy');

-- Test #3
insert into useraccountinfo values ('zz-zzz-zzz-zzz-zzz', 'admin', 'Muhamed', 'Adel', 'moAdel@yahoo.com',
                                    '############', '############', '2023-11-16');

-- Test #4
insert into useraccountinfo values ('xx-xxx-xxx-xxx-xxx', 'admin', 'Muhamed', 'Adel', 'moAdel@yahoo.com',
                                    '############', '############', '2023-11-16');

-- Test #5
update useraccountinfo set ssn = '123466' where userType = 'admin';

-- Test #6
delete from useraccountkeys where userSSN = 'xx-xxx-xxx-xxx-xxx';
delete from useraccountkeys where userSSN = 'xx-xxx-xxx-xxx-xxy';

-- Test #7
update useraccountkeys set userSSN = 'xx-xxx-xxx-zzz-zzz' where userAccountId = 6;
