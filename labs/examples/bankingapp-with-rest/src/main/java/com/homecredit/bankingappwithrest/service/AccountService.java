package com.homecredit.bankingappwithrest.service;

//import com.homecredit.bankingapp.exception.AccountException;
//import com.homecredit.bankingapp.model.Account;
import com.homecredit.bankingappwithrest.exception.AccountException;
import com.homecredit.bankingappwithrest.model.Account;

import java.util.HashMap;
//import com.homecredit.bankingapp.exception.ValidationException;


//import javax.xml.bind.ValidationException;


public interface AccountService {

    public int create() throws AccountException, AccountException;

    public boolean update(int accountId) ;

    public boolean delete(int accountId) throws javax.security.auth.login.AccountException, AccountException;
    //
    public Account get(int accountId) ;

    public HashMap<Integer, Account> getAll();

    public void printStatistics();

    public void importAccounts() throws Exception;

    public void exportAccounts() throws Exception;

//    public void importFromDatabase();

//    public void storeDataToDatabase();
}



