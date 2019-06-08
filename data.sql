CREATE TABLE `rollingstone_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_number` varchar(45) NOT NULL,
  `department_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `rollingstone_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_number` varchar(45) NOT NULL,
  `employee_name` varchar(200) NOT NULL,
    `employee_title` varchar(45) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


INSERT INTO `verbose_demo_db`.`rollingstone_department`
(`id`,
`department_number`,
`department_name`)
VALUES(1,'
1001',
'Finance');


INSERT INTO `verbose_demo_db`.`rollingstone_department`
(`id`,
`department_number`,
`department_name`)
VALUES(2,'
1002',
'Materials');

INSERT INTO `verbose_demo_db`.`rollingstone_department`
(`id`,
`department_number`,
`department_name`)
VALUES(3,'
1003',
'Pharmacy');

INSERT INTO `verbose_demo_db`.`rollingstone_department`
(`id`,
`department_number`,
`department_name`)
VALUES(4,'
1004',
'Beauty');

INSERT INTO `verbose_demo_db`.`rollingstone_department`
(`id`,
`department_number`,
`department_name`)
VALUES(5,'
1005',
'HR');

INSERT INTO `verbose_demo_db`.`rollingstone_department`
(`id`,
`department_number`,
`department_name`)
VALUES(6,'
1006',
'Digital Technologies');

INSERT INTO `verbose_demo_db`.`rollingstone_employee`
(`id`,
`employee_number`,
`employee_name`,
`employee_title`,
`department_id`)
VALUES
(1,
'1001',
'Steven Smith',
'Tech Lead',
6);

INSERT INTO `verbose_demo_db`.`rollingstone_employee`
(`id`,
`employee_number`,
`employee_name`,
`employee_title`,
`department_id`)
VALUES
(2,
'1002',
'Paul Fleming',
'Sr. Software Engineer',
6);

INSERT INTO `verbose_demo_db`.`rollingstone_employee`
(`id`,
`employee_number`,
`employee_name`,
`employee_title`,
`department_id`)
VALUES
(3,
'1003',
'Raj Balasubramanian',
'Director',
6);

INSERT INTO `verbose_demo_db`.`rollingstone_employee`
(`id`,
`employee_number`,
`employee_name`,
`employee_title`,
`department_id`)
VALUES
(4,
'1004',
'Saurabh Kalia',
'Project Manager',
6);

INSERT INTO `verbose_demo_db`.`rollingstone_employee`
(`id`,
`employee_number`,
`employee_name`,
`employee_title`,
`department_id`)
VALUES
(5,
'1005',
'Bikram Batra',
'Software Engineer II',
6);

INSERT INTO `verbose_demo_db`.`rollingstone_employee`
(`id`,
`employee_number`,
`employee_name`,
`employee_title`,
`department_id`)
VALUES
(6,
'1006',
'Kanad Bhattacharya',
'Software Engineer II',
6);
