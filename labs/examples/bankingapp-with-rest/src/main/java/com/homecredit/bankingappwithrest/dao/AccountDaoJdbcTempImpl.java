//package com.homecredit.bankingappwithrest.dao;
//
//
//import com.homecredit.bankingappwithrest.model.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.security.auth.login.AccountException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AccountDaoJdbcTempImpl implements AccountDao {
//
//
//    @Autowired
//    JdbcTemplate jdbcTemp;
//
//
//    @Override
//    public boolean create(Account account) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException {
//        return false;
//    }
//
//    @Override
//    public boolean update(int accountId, Account account) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(int accountId) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException {
//        return false;
//    }
//
//    @Override
//    public Account get(int accountId) {
//        return null;
//    }
//
//    @Override
//    public HashMap<Integer, Account> getAll() {
//        return null;
//    }
//
//    @Override
//    public void storeDataToDatabase(Map<Integer, Account> accounts) {
//
//    }
//}
