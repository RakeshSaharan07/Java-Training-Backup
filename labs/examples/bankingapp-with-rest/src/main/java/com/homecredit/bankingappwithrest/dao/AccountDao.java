package com.homecredit.bankingappwithrest.dao;
import com.homecredit.bankingappwithrest.model.Account;
//import com.homecredit.bankingappwithrest.model.Account;

import javax.security.auth.login.AccountException;
import java.util.HashMap;
import java.util.Map;

public interface AccountDao {
    public boolean create(Account account) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException;
    public boolean update(int accountId, Account account) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException;
    public boolean delete(int accountId) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException;;
    public Account get(int accountId);
    public HashMap<Integer, Account> getAll();
    public  void storeDataToDatabase(Map<Integer, Account> accounts);

//    boolean isValid(int no);
}
