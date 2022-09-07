package com.homecredit.bankingappwithrest.service;


import com.homecredit.bankingappwithrest.dao.AccountDao;
import com.homecredit.bankingappwithrest.dao.AccountDaoJdbcImpl;
import com.homecredit.bankingappwithrest.exception.AccountException;
import com.homecredit.bankingappwithrest.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

//import javax.xml.bind.ValidationException;
//import com.homecredit.bankingapp.exception.ValidationException;

public class AccountServiceTreeMapImpl implements AccountService {


//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/jdbctraining";
//
//    // Database credentials
//    static final String USER = "training";
//    static final String PASS = "training";

//    @Autowired
    AccountDao objOfAccountDao;




    private static Map<Integer, Account> accounts = new TreeMap<>();


    Scanner scan = new Scanner(System.in);

    public AccountServiceTreeMapImpl(AccountDao accountdaoservice) throws SQLException, ClassNotFoundException {

//          objOfAccountDao = new AccountDaoJdbcImpl();
//          accounts = accountdaoservice.getAll();

    }

    @Override
    public int create() throws AccountException {
        try
        {

        System.out.println("Enter your Unique ID:- ");
        int id = scan.nextInt();

        System.out.println("Enter your Aadhar Name :- ");
        String name = scan.nextLine();
        while(name.equals("")){
            name = scan.nextLine();
        }

        System.out.println("Type of your Account (Savings/Deposit) :- ");
        String type = scan.next();

        System.out.println("Initial Deposit Amount :- ");
        double balance = scan.nextDouble();

        System.out.println("Branch Name :- ");
        String branch = scan.next();
        Account obj = new Account(id,name, type, balance, branch);
//        accounts.put(obj.getAccountId(),obj)
          objOfAccountDao.create(obj);

        return obj.getAccountId();
        }catch (InputMismatchException ex){
            throw new AccountException(" Focus on Your Input...");
        } catch (javax.security.auth.login.AccountException e) {
            e.printStackTrace();
        }

        return 0;
    }


    //
    @Override
    public boolean update(int accountId) {
        //code
        if (!accounts.containsKey(accountId))
            return false;
        else {
            System.out.println("\n-------------------Welcome to Update Account Detail Counter------------------*");
            int choice;
//            Account obj = get(accountId);
            do {

                System.out.println("Press 1: Name Modify ");
                System.out.println("Press 2: Bank Type Modify ");
                System.out.println("Press 3: Branch Modify  ");
                System.out.println("Press 4: Bank Balance  ");
                System.out.println("Press 5: For Withdraw  ");
                System.out.println("Press 6: For Deposit  ");
                System.out.println("Press 0: Exit from this Counter ");

                System.out.print("\nWhat your Choice :- ");
                choice = scan.nextInt();

                switch (choice) {
                    case 1: {
                        System.out.print("Enter New Modification Name :- ");
                        String newName = scan.next();
//                        (accounts.get(obj)).setName(newName);
                        (accounts.get(accountId)).setName(newName);
                        System.out.println("New Name: " +  (accounts.get(accountId)).getName() + ". Modified Successfully... \n");
                        break;
                    }
                    case 2: {
                        //code
                        System.out.print("\nEnter New Modification Bank Type :- ");
                        String newType = scan.next();
                        accounts.get(accountId).setType(newType);
                        System.out.println("New Bank Type: " + accounts.get(accountId).getType() + ". Modified Successfully... \n");
                        break;
                    }
                    case 3: {
                        //code
                        System.out.print("\nEnter New Modification Branch Name :- ");
                        String newBranch = scan.next();
                        accounts.get(accountId).setBranch(newBranch);
                        System.out.println("New Branch Name: " + accounts.get(accountId).getBranch() + ". Modified Successfully... \n");
                        break;
                    }
                    case 4: {
                        //code
                        System.out.println("\nBank Balance is: " + accounts.get(accountId).getBalance() + ".\n");
                        break;
                    }
                    case 5: {
                        //code
                        System.out.print("Enter Withdrawal Amount :- ");
                        double amount = scan.nextDouble();
                        if (amount <= accounts.get(accountId).getBalance()) {
                            accounts.get(accountId).setBalance((accounts.get(accountId).getBalance() - amount));
                            System.out.println("\nWithdrawal process is complete Successfully. & your Now Balance: " + accounts.get(accountId).getBalance() + ".\n");
                        } else
                            System.out.println("\n----Failed! You haven't this much Balance----\n");
                        break;
                    }
                    case 6: {
                        //code
                        System.out.print("Enter Deposit Amount :- ");
                        double amount = scan.nextDouble();
                        accounts.get(accountId).setBalance(accounts.get(accountId).getBalance() + amount);
                        System.out.println("\nDeposit process is complete Successfully. & your Current Balance: " + accounts.get(accountId).getBalance() + ".\n");

                        break;
                    }
                    default: {
                        //code
                        System.out.println("\n--------------  Thanks for visit this Counter  ---------------------");
                        break;
                    }
                }

            } while (choice != 0);
            return true;
        }

    }

    //
    @Override
    public boolean delete ( int accountId) throws javax.security.auth.login.AccountException, AccountException {
//        if (accounts.containsKey(accountId)) {
//            accounts.remove(accountId);
//            return true;
//        }
//        return false;

        return objOfAccountDao.delete(accountId) ;
    }



//
//    public Account get ( int accountId){
//
//
//        if (accounts.containsKey(accountId)) {
//
//            return accounts.get(accountId);
//        }
//        return null;
//    }

    public Account get(int accountId){
        return objOfAccountDao.get(accountId);
    }



    @Override
    public HashMap<Integer, Account> getAll () {

//		for(int id: accounts.keySet()) {
//			Account acc = accounts.get(id);
//			System.out.println(acc.toString());
//		}
//        for(Account account: accounts.values()) {
//            System.out.println(account.toString());
//        }



        //By Database

        HashMap<Integer, Account> values = objOfAccountDao.getAll();
//        for (Map.Entry<Integer, Account> entry : values.entrySet()) {
//            System.out.println(entry.getValue());
//        }
        return values;
    }

    public int noOfAccountWhichHasMoreThanOneLakh(){
        int noOfAccount=0;
        //code
        for(Account account: accounts.values()) {
            if(account.getBalance() > 100000){
                ++noOfAccount;
            }
        }
        return noOfAccount;
    }

    public int noOfAccountWhichHasAType(String type) {
        int noOfAccount=0;
        //code
        for(Account account: accounts.values()) {
            if(account.getType().equals(type)){
                ++noOfAccount;
            }
        }
        return noOfAccount;
    }

    public void sortingAccountsWithType(){

        //code
        Map<String, String> sortedAccounts = new TreeMap<>();

        for (Account account : accounts.values()){
            if(sortedAccounts.containsKey(account.getType())) {
                sortedAccounts.put(account.getType() , account.toString() +",  \n\t\t\t\t\t\t\t"+ sortedAccounts.get(account.getType()));
            }
            else{
                sortedAccounts.put(account.getType(), account.toString());
            }
        }

        Set<Map.Entry<String, String>> entries = sortedAccounts.entrySet();
        for(Map.Entry entry: entries) {
            System.out.println("Account ID: "+entry.getKey() + " :|-->   " + entry.getValue()+"\n");
        }

    }

    public double showAverageBalance(String type){
        double total = 0;
        int count = 0;

        //code
        for(Account account: accounts.values()) {
            if(account.getType().equals(type)){
                total += account.getBalance();
                count++;
            }
        }
        if (count == 0)
            return 0;
        return total / count;

    }

    public List showIdsWithSameName(String name){

        List<Integer>list = new ArrayList<>();
        for(Account account: accounts.values()) {
            if(account.getName().equals(name)){
                list.add(account.getAccountId());
            }
        }
        return list;
    }

    private Set allTypesOfAccounts(){
        Set<String> types = new HashSet<>();

        for(Account account: accounts.values()) {
            types.add(account.getType());
        }

        return types;
    }

    @Override
    public void printStatistics(){

        //code
        System.out.println("\n------------------- Welcome to Statistics PreDefined Queries ------------------*");
        char choice;
//            Account obj = get(accountId);
        do {

            System.out.println("\nPress 'a': No of accounts which has balance more than 1 lac ");
            System.out.println("Press 'b': Show no of account by account type  ");
            System.out.println("Press 'c': Show no of accounts by account type with sorting ");
            System.out.println("Press 'd': Show avg balance by account type ");
            System.out.println("Press 'e': List account ids whose account name contains given name ");
            System.out.println("Press 'f': For Exit  ");

            System.out.print("\nWhat your Choice :- ");
            choice = scan.next().charAt(0);

            switch (choice) {
                case 'a': {
                    System.out.print("\nNo of accounts which has balance more than 1 lac are:  "+noOfAccountWhichHasMoreThanOneLakh()+". \n");

                    break;
                }
                case 'b': {
                    //code
                    System.out.println("All Types of Accounts :- "+allTypesOfAccounts());
                    System.out.print("\nShow no of account by account type, Enter Account Type : ");
                    String type = scan.next();
                    System.out.println("\nNo. of Accounts with Account "+type+" are "+noOfAccountWhichHasAType(type));
                    break;
                }
                case 'c': {
                    //code
                    System.out.println("\nShow no of accounts by account type with sorting : ");
                    sortingAccountsWithType();
                    break;
                }
                case 'd': {
                    //code
                    System.out.println("All Types of Accounts :- "+allTypesOfAccounts());
                    System.out.print("\nShow avg balance by account type, Enter Account Type : ");
                    String type = scan.next();
                    System.out.println("\nAverage Balance of "+type+" is : "+showAverageBalance(type));
                    break;
                }
                case 'e' : {
                    //code
                    System.out.print("\n List account ids whose account name contains given name, Enter Account Holder Name : ");
                    scan.nextLine();
                    String name = scan.nextLine();
                    System.out.println("\nAccount ID's are : "+showIdsWithSameName(name));
                    break;
                } case 'f' : {
                    //code
                    System.out.print("\nExit From This Counter ");

                    break;
                }

                default: {
                    //code
                    System.out.println("\n--------------  Thanks for visited Here  -----------------");
                    choice = 'f';
                    break;
                }
            }

        } while (choice != 'f');

    }



    @Override
    public void importAccounts() throws Exception{
        File accountsDetail = new File(".\\files\\accountsDetailForImport.txt");

        BufferedReader bufRead = new BufferedReader(new FileReader(accountsDetail));

        String fileData;

        while ((fileData = bufRead.readLine()) != null){

            String[] values = fileData.split(",");
            double balance = Double.parseDouble(values[3]);
            int id = Integer.parseInt(values[0]);
            String name = values[1];
            String type = values[2];
            String branch = values[4];

            Account obj = new Account(id,name, type, balance, branch);
            accounts.put(obj.getAccountId(),obj);

        }

        System.out.println("Account Details Imported Successfully...");
    }

    @Override
    public void exportAccounts() throws Exception{

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(".\\files\\exportedAccountDetails.txt"));

            for (Account account :
                    accounts.values()) {
                out.write(newToString(account));
                out.write("\n");
            }
            System.out.println("Export Accounts Detail Successfully...");
            out.flush();

        }finally {
            if (out != null) {
                out.close();
            }
        }


    }

    private String newToString(Account obj){
        return obj.getAccountId()+","+obj.getName()+","+obj.getType()+","+obj.getBalance()+","+obj.getBranch();
    }



//    public void importFromDatabase() {
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//
//        try {
//
//            Class.forName(JDBC_DRIVER);
//
//            // STEP 3: Open a connection
//            System.out.println("Connecting to Database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            conn.setAutoCommit(false); // enable transaction
//
////            System.out.println("Connection estabilished: " + conn);
//
//            String selectQuery = "SELECT * FROM employee";
//            pstmt = conn.prepareStatement(selectQuery);
//            rs = pstmt.executeQuery(selectQuery);
//
//            // STEP 5: Extract data from result set
//            while (rs.next()) {
//                // Retrieve by column name
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String type = rs.getString("type");
//                double balance = rs.getDouble("balance");
//                String branch = rs.getString("branch");
//
//                Account obj = new Account(id,name, type, balance, branch);
//                accounts.put(id,obj);
//            }
//
//            System.out.println("Data Inserted successfully from DataBase...");
//
//            conn.commit();
//
//
//        } catch (
//                SQLException se) {
//            // Handle errors for JDBC
//            se.printStackTrace();
//            try {
//                conn.rollback();
//            } catch (SQLException e) {
//            }
//        } catch (Exception e) {
//            // Handle errors for Class.forName
//            e.printStackTrace();
//            try {
//                conn.rollback();
//            } catch (SQLException sqle) {
//            }
//        } finally {
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            } // end finally try
//        } // end try
////        System.out.println("Goodbye!");
//    }

   public void storeDataToDatabase(){
       objOfAccountDao.storeDataToDatabase(accounts);
   }
}








