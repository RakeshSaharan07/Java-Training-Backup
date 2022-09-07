package com.homecredit.bankingappwithrest.dao;
//
//import com.homecredit.bankingapp.exception.AccountException;
//import com.homecredit.bankingapp.model.Account;

import com.homecredit.bankingappwithrest.exception.AccountException;
import com.homecredit.bankingappwithrest.model.Account;
//import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountDaoJdbcImpl implements AccountDao {

//    MysqlDataSource dataSource = null;
//    @Autowired
//    MysqlDataSource datasource  ;


    @Autowired
//    @Qualifier(value = "connection")
    Connection conn;
    //    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public AccountDaoJdbcImpl() {
//        dataSource = new MysqlDataSource();
//        dataSource.setServerName("localhost");
//        dataSource.setDatabaseName("jdbctraining");
//        dataSource.setUser("training");
//        dataSource.setPassword("training");
//        try {
//            conn = dataSource.getConnection();
//            System.out.println("Connection created successfully. " + conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public boolean create(Account account) throws AccountException {
        try {
            String query = "INSERT INTO employee (id, name,type,balance,branch) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, account.getAccountId());
            stmt.setString(2, account.getName());
            stmt.setString(3, account.getType());
            stmt.setDouble(4, account.getBalance());
            stmt.setString(5, account.getBranch());
            int insertCount = stmt.executeUpdate();
            stmt.close();
            System.out.println(insertCount + " Account(s) inserted\n");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AccountException("Exception in create function...\n", e);
        }
        return false;
    }

    @Override
    public boolean update(int accountId, Account account) throws AccountException {
        boolean status = false;
        try {
//            String query = "UPDATE accountdetails SET name = \"" + account.getName() + "\", type = " + account.getType()
//                    + ",balance = \"" + account.getBalance() + "\",branch = \"" + account.getBalance()
//                    + "\", branch = \"" + account.getBranch() + "\", active = \"" + account.getActive() + "\" WHERE id = " + accountId;
            String query = "UPDATE employee SET id = ?, name = ?,type = ? , balance = ? , branch = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountId);
            stmt.setString(2, account.getName());
            stmt.setString(3, account.getType());
            stmt.setDouble(4, account.getBalance());
            stmt.setString(5, account.getBranch());
//            stmt.setBoolean(5, account.getActive());
            int updateCount = stmt.executeUpdate();
            stmt.close();
            System.out.println(updateCount + " Account(s) updated");
        } catch (Exception e) {
            throw new AccountException("Exception in update method...\n", e);
        }
        return status;
    }
//
//    public boolean updateBalance(int aid ,double amt){
//        try{
//            String query = "UPDATE accountdetails SET balance = ? WHERE id =?";
//            stmt=conn.prepareStatement(query);
//            stmt.setDouble(1,amt);
//            stmt.setInt(2,aid);
//            stmt.executeUpdate();
//            stmt.close();
//            System.out.println("Account Balance updated");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return true;
//    }

    @Override
    public boolean delete(int accountId) throws AccountException {
        boolean status = false;
        try {
            String query = "DELETE FROM employee WHERE id = " + accountId;
            stmt = conn.prepareStatement(query);
            int deleteCount = stmt.executeUpdate();
            stmt.close();
            System.out.println(deleteCount + " Account(s) deleted");
            return !status;
        } catch (Exception e) {
            throw new AccountException("Exception in delete...", e);
        }
    }

    @Override
    public Account get(int accountId) {
        Account a = null;
        String query = "SELECT * FROM employee WHERE id = " + accountId;
        try {
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double bal = rs.getDouble("balance");
                String branch = rs.getString("branch");
                a = new Account(id, name, type, bal, branch);
                
//                System.out.println(a.toString());
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HashMap<Integer, Account> getAll() {
        Account a = null;
        HashMap<Integer, Account> accounts = new HashMap<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM employee");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                double bal = rs.getDouble("balance");
                String branch = rs.getString("branch");
                a = new Account(id, name, type, bal, branch);
                accounts.put(id, a);
//                System.out.println(a.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


    public void storeDataToDatabase(Map<Integer, Account> accounts) {


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {

//            Class.forName(JDBC_DRIVER);
//
//            // STEP 3: Open a connection
////            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            conn.setAutoCommit(false); // enable transaction

//            System.out.println("Connection estabilished: " + conn);

            String insertNewQuery = "DELETE FROM employee";
            pstmt = conn.prepareStatement(insertNewQuery);
            pstmt.executeUpdate();


            String insertQueryForPrepareStmt = "INSERT INTO employee (id, name, type, balance, branch) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertQueryForPrepareStmt);

            for (Account account : accounts.values()) {
                pstmt.setInt(1, account.getAccountId());
                pstmt.setString(2, account.getName());
                pstmt.setString(3, account.getType());
                pstmt.setDouble(4, account.getBalance());
                pstmt.setString(5, account.getBranch());
                pstmt.executeUpdate();
            }

            pstmt.close();
            conn.commit();
            System.out.println("Store Data to the Database successfully...");


        } catch (
                SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
            }
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException sqle) {
            }
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
//        System.out.println("Goodbye!");
    }

}


