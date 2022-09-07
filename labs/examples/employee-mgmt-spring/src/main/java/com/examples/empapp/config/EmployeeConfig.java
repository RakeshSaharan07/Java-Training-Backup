package com.examples.empapp.config;

import com.examples.empapp.dao.EmployeeDao;
import com.examples.empapp.dao.EmployeeDaoJdbcImpl;
import com.examples.empapp.service.EmployeeService;
import com.examples.empapp.service.EmployeeServiceImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class EmployeeConfig {
    @Bean("empService")
    public EmployeeService empService() {
        return new EmployeeServiceImpl();
    }

    @Bean("empDao")
    public EmployeeDao empDao() {
        return new EmployeeDaoJdbcImpl();
    }

    @Bean("datasource")
    public MysqlDataSource dataSource() {
        MysqlDataSource	datasource = new MysqlDataSource();
		datasource.setServerName("localhost");
		datasource.setDatabaseName("jdbctraining");
		datasource.setUser("training");
		datasource.setPassword("training");
        return datasource;
    }

    @Bean("connection")
    public Connection connection() {
        Connection conn = null;
        try {
            conn = dataSource().getConnection();
            System.out.println("Connection created successfully. " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
