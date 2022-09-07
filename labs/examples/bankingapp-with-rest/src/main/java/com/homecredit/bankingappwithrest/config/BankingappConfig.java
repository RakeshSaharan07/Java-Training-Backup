package com.homecredit.bankingappwithrest.config;

import com.homecredit.bankingappwithrest.dao.AccountDao;
import com.homecredit.bankingappwithrest.dao.AccountDaoJdbcImpl;
//import com.homecredit.bankingappwithrest.dao.AccountDaoJdbcTempImpl;
import com.homecredit.bankingappwithrest.model.Account;
import com.homecredit.bankingappwithrest.service.AccountService;
import com.homecredit.bankingappwithrest.service.AccountServiceTreeMapImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class BankingappConfig {

       @Bean(value = "service")
       public AccountService accountService() throws SQLException, ClassNotFoundException {
           return new AccountServiceTreeMapImpl(accountDaoService());
       }

       @Bean
       public AccountDao accountDaoService(){
           return new AccountDaoJdbcImpl();
//           return new AccountDaoJdbcTempImpl();
       }

    @Bean("datasource")
    public MysqlDataSource datasource(){
        MysqlDataSource dataSource ;
        dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("jdbctraining");
        dataSource.setUser("training");
        dataSource.setPassword("training");
        return dataSource;
    }

    @Bean("connection")
    public Connection connection(){
        Connection conn = null;
        MysqlDataSource dataSource = datasource();
        try {
            conn = dataSource.getConnection();
            System.out.println("Connection created successfully... " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Bean
    public dbConfig DbConfiguration(){
           return  new dbConfig();
    }

 
}
