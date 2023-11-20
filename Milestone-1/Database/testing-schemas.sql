use `central-student-hub`;

-- Test #1
insert into useraccount (ssn)
values ('xx-xxx-xxx-xxx-xyy'),
       ('xx-xxx-xxx-xxx-xxy'),
       ('xx-xxx-xxx-xxx-xxz'),
       ('xx-xxx-xxx-xxx-xyx'),
       ('xx-xxx-xxx-xxx-xzx'),
       ('xx-xxx-xxx-xxx-xxx');

-- Test #2
insert into useraccount (ssn)
values ('xx-xxx-xxx-xxx-xxx');

-- Test #3
insert into useraccount (ssn)
values (null);

-- Test #4
insert into useraccount (ssn)
values ('xx-xxx-xxx-xxy-zzz'),
       ('xx-xxx-xxx-xxz-zzz');


-- Test #5
insert into useraccount (ssn, userType, email, passwordHash, passwordSalt, passwordDate)
values ('yy-xxx-xxx-xxx-xxx', 'student', 'std1@alex.edu.eg', '123456789', '123456789', '2020-01-01'),
       ('yy-xxx-xxx-xxx-xxy', 'admin', 'admin@alex.edu.eg', '123456789', '123456789', '2020-01-01'),
       ('yy-xxx-xxx-xxx-xxz', 'teaching staff', 'tc1@alex.edu.eg', '123456789', '123456789', '2020-01-01');

-- Test #6
delete from useraccount where ssn in ('xx-xxx-xxx-xxx-xyy', 'xx-xxx-xxx-xxx-xxy',
                                      'xx-xxx-xxx-xxx-xxz', 'xx-xxx-xxx-xxx-xyx',
                                      'xx-xxx-xxx-xxx-xzx', 'xx-xxx-xxx-xxx-xxx');

-- Test #7
update useraccount set userType = 'student' where userAccountId in (8, 9);
update useraccount set passwordDate = '2020-01-01' where ssn in ('xx-xxx-xxx-xxy-zzz', 'xx-xxx-xxx-xxz-zzz');
