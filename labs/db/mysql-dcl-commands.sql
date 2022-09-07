#View user details
select * from mysql.user;
select host, user, authentication_string, select_priv, create_priv, update_priv, delete_priv from mysql.user;

#Create user and Allow access to mysql_training schema from local machine
CREATE USER 'hcfsd'@'localhost' IDENTIFIED BY 'hcfsd';
GRANT ALL PRIVILEGES ON mysql_training.employee TO 'hcfsd'@'localhost';
GRANT SELECT ON mysql_training.employee TO 'hcfsd'@'localhost';
REVOKE SELECT ON mysql_training.employee FROM 'hcfsd'@'localhost';

GRANT ALL ON mysql_training.* TO 'hcfsd'@'localhost' WITH GRANT OPTION;  

#Allow access to mysql_training schema from all machines with access to create schema
CREATE USER 'hcfsd'@'*' IDENTIFIED BY 'hcfsd';
GRANT ALL ON mysql_training.* TO 'hcfsd'@'*' WITH GRANT OPTION;

#Allow access to all schemas from all machines with access to create schema
CREATE USER 'hcfsd'@'%' IDENTIFIED BY 'hcfsd';
GRANT ALL PRIVILEGES ON *.* TO 'hcfsd'@'%' IDENTIFIED BY 'hcfsd' WITH GRANT OPTION;

#Revoke access for all schemas
REVOKE ALL PRIVILEGES ON mysql_training.* FROM 'hcfsd'@'localhost';
REVOKE ALL PRIVILEGES ON *.* FROM 'hcfsd'@'localhost';    
REVOKE ALL PRIVILEGES ON *.* FROM 'hcfsd'@'%'; 

#Delete user
delete from mysql.user where user = 'hcfsd'; 

select user();
select database();