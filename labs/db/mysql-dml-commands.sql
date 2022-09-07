#INSERT data syntax
# INSERT INTO <TABLENAME> (FIELDNAMES) VALUES(FIELDVALUES);

-- INSERT EMPLOYEE DATA
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Kumar',25,'M',20000,'Manager','IT',1);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Selvam',28,'M',25000,'Software Engineer','IT',1);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Solvin',29,'M',30000,'Developer','IT',2);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Siddarth',45,'M',50000,'VP','Finance',1);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Mathes',35,'M',60000,'Function Head','HR',1);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Sinthura',27,'F',40000,'Lead','Operations',2);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Sunil',50,'M',70000,'President','Delivery',3);
INSERT INTO EMPLOYEE (NAME, AGE, GENDER, SALARY, DESIGNATION, DEPARTMENT, COUNTRY_ID) VALUES('Mukesh',40,'M',70000,'President','Delivery',3);

-- LOAD COUNTRY DATA FROM LOCAL FILE
LOAD DATA LOCAL INFILE 'D:/Training/homecredit_java-fullstack/labs/db/data/country.txt' INTO TABLE COUNTRY FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'D:/Training/homecredit_java-fullstack/labs/db/data/employee.txt' INTO TABLE EMPLOYEE FIELDS TERMINATED BY ',';

#SELECT data syntax
# SELECT <FIELDNAMES> 
#	FROM <TABLENAME>
#	WHERE <FILTER>
# 	GROUP BY <FIELDLIST>
#	HAVING <FILTER>
#	ORDER BY <FIELDNAMES> ASC|DESC;

# SELECT ALL RECORDS
SELECT * FROM EMPLOYEE;
SELECT * FROM COUNTRY;

# SELECT SELECTIVE COLUMNS
SELECT ID, NAME, SALARY FROM EMPLOYEE;

#COUNT NO OF EMPLOYEES
SELECT COUNT(*) FROM EMPLOYEE;

#COUNT NO OF EMPLOYEES BELONGS TO SPECIFIC DEPT
SELECT COUNT(*)
	FROM EMPLOYEE
	WHERE DEPARTMENT = 'IT';

#FETCH ALL EMPLOYEES BELONGS TO SPECIFIC DEPT	
SELECT *
	FROM EMPLOYEE
	WHERE DEPARTMENT = 'IT';

#FETCH EMPLOYEES WITH SALARY GREATER THAN 25000
SELECT *
	FROM EMPLOYEE
	WHERE DEPARTMENT = 'IT' OR SALARY > 25000;	

#FETCH EMPLOYEES BELONGS TO ANY DEPARTMENT SPECIFIED AS LIST OF VALUES
SELECT *
	FROM EMPLOYEE
	WHERE DEPARTMENT IN ('IT','HR')
	
#FETCH EMPLOYEES SALARY BETWEEN SPECIFIC RANGE     
SELECT *
	FROM EMPLOYEE
	WHERE SALARY >= 30000 AND SALARY <= 60000;
	
#FETCH EMPLOYEES SALARY BETWEEN SPECIFIC RANGE USING BETWEEN OPERATOR        
SELECT *
	FROM EMPLOYEE
	WHERE SALARY BETWEEN 30000 AND 60000;
    
#SELECT EMPLOYEES WITH NAME MATCH
SELECT *
	FROM EMPLOYEE
    WHERE NAME LIKE 'Mary';

#SORT BY AGE
SELECT *
	FROM EMPLOYEE
	ORDER BY AGE DESC;
	
#SORT BY DEPARTMENT AND AGE	
SELECT *
	FROM EMPLOYEE
	ORDER BY DEPARTMENT DESC, AGE DESC;	

#Designation wise employee count
SELECT designation,COUNT(*) as 'Emp Count'
	FROM Employee
    GROUP BY designation;

#List designations have average salary greater than 50000
SELECT designation, avg(salary) AS avg_sal
	FROM Employee
    GROUP BY designation
	HAVING avg_sal > 50000
	ORDER BY avg_sal DESC
	LIMIT 1;
    
#Department wise employee count
SELECT department,COUNT(*)
	FROM Employee
    GROUP BY department;
    
#Department AND Designation wise employee count    
SELECT department,designation,COUNT(*)
	FROM Employee
    GROUP BY department, designation
    ORDER BY department;
    
#FETCH DEPARTMENTS HAVE MORE THAN TWO EMPLOYEES    
SELECT department,COUNT(*) AS empCount
	FROM Employee
    GROUP BY department
    HAVING empCount > 2;

#Case Insensitive Match using String functions   
SELECT id, name FROM employee WHERE UPPER(name) = UPPER('kuMar');    

# Table joins - Fetch Employee and Country details based on COUNTRY_ID and specific COUNTRY_CODE
SELECT 
    e.ID, e.NAME, e.DEPARTMENT, c.NAME AS 'Country Name'
FROM
    EMPLOYEE e,
    COUNTRY c
WHERE
    e.COUNTRY_ID = c.ID AND c.code = 'IND';

# Inner Join - Fetch Employee and Country details based on COUNTRY_ID and specific COUNTRY_CODE
SELECT 
    e.ID, e.NAME, e.DEPARTMENT, c.NAME AS 'Country Name'
FROM
    EMPLOYEE e
        INNER JOIN
    COUNTRY c ON e.COUNTRY_ID = c.ID AND c.code = 'IND';
    
# Left Outer Join - Fetch All Employees and matching Country details based on COUNTRY_ID andspecific COUNTRY_CODE
SELECT 
    e.ID, e.NAME, e.DEPARTMENT, c.NAME AS 'Country Name'
FROM
    EMPLOYEE e
        LEFT OUTER JOIN
    COUNTRY c ON e.COUNTRY_ID = c.ID AND c.code = 'IND'; 
    
# Right Outer Join -  Fetch All Country details and matching Employee details based on COUNTRY_ID andspecific COUNTRY_CODE
SELECT 
    e.ID, e.NAME, e.DEPARTMENT, c.NAME AS 'Country Name'
FROM
    EMPLOYEE e
        RIGHT OUTER JOIN
    COUNTRY c ON e.COUNTRY_ID = c.ID AND c.code = 'IND';
	
# Full Outer Join - with UNION
SELECT 
    e.ID, e.NAME, e.DEPARTMENT, c.NAME AS 'Country Name'
FROM
    EMPLOYEE e
        LEFT OUTER JOIN
    COUNTRY c ON e.COUNTRY_ID = c.ID AND c.code = 'IND'
UNION
SELECT 
    e.ID, e.NAME, e.DEPARTMENT, c.NAME AS 'Country Name'
FROM
    EMPLOYEE e
        RIGHT OUTER JOIN
    COUNTRY c ON e.COUNTRY_ID = c.ID AND c.code = 'IND';

#Multi key condition	
SELECT * FROM EMPLOYEE WHERE DEPARTMENT = 'IT' OR SALARY > 25000;

#Multi key condition decomposed as union operation
SELECT * FROM EMPLOYEE WHERE DEPARTMENT = 'IT' UNION SELECT * FROM EMPLOYEE WHERE SALARY > 25000;

# Uncorrelated Subquery to fetch employees belongs to India
SELECT * FROM EMPLOYEE WHERE COUNTRY_ID IN (SELECT ID FROM COUNTRY WHERE CODE = 'IND');
    
# Correlated Subquery to fetch COUNTRY name
SELECT e.ID, e.NAME, e.DEPARTMENT, (SELECT c.Name FROM COUNTRY c WHERE c.ID=e.COUNTRY_ID) as "Country Name" FROM EMPLOYEE e WHERE e.COUNTRY_ID = (SELECT c.ID FROM COUNTRY c WHERE e.COUNTRY_ID = c.ID);

# Correlated Subquery to fetch employee details with country name belongs to India
SELECT * FROM EMPLOYEE e WHERE e.COUNTRY_ID = (SELECT c.ID FROM COUNTRY c WHERE e.COUNTRY_ID = c.ID AND c.CODE = 'IND');

# EXISTS
SELECT * FROM EMPLOYEE e1 WHERE EXISTS (SELECT ID FROM EMPLOYEE1 e2 WHERE e1.ID = e2.ID);

#UPDATE statement syntax
#UPDATE <TABLENAME>
#		SET <FIELDLIST>
#        WHERE <FILTER>;
        
Update Employee set country='INDIA' where ID=6;
UPDATE EMPLOYEE e SET DEPARTMENT='HR',DESIGNATION='Developer' WHERE e.COUNTRY_ID = (SELECT ID FROM COUNTRY c WHERE e.COUNTRY_ID = c.ID and c.CODE='IND')

#TRUNCATE TABLE DATA
TRUNCATE EMPLOYEE;

#DELETE SPECIFIC RECORDS
DELETE FROM EMPLOYEE AS e WHERE e.ID = 7; 
DELETE FROM EMPLOYEE WHERE COUNTRY_ID = (SELECT ID FROM COUNTRY WHERE COUNTRY_ID = ID and CODE='IND'); 