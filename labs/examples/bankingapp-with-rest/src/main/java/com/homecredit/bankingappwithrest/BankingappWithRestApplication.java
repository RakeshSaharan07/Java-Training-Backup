package com.homecredit.bankingappwithrest;

import com.homecredit.bankingappwithrest.exception.AccountException;
import com.homecredit.bankingappwithrest.service.AccountService;
import com.homecredit.bankingappwithrest.model.Account;
import com.homecredit.bankingappwithrest.service.AccountServiceTreeMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import javax.security.auth.login.AccountException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class BankingappWithRestApplication implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "service")
    AccountService service;

    private Scanner scan;


//    @Autowired
//    private AccountDao serviceTwo;

    public static void main(String[] args) {

        SpringApplication.run(BankingappWithRestApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Logger logger = LoggerFactory.getLogger(BankingappWithRestApplication.class);

//        AccountService service = new AccountServiceTreeMapImpl();


        System.out.println("\n\n*---------------------🙏 ✨✨  WELCOME TO BANK APPLICATION  ✨✨ 🙏 --------------------------*\n");
        int choice = 0;
        do {

            try {

                Scanner scan = new Scanner(System.in);
//            Thread.sleep(3000);

                System.out.println("\nPress 1 : Create New Account ");
                System.out.println("Press 2 : Get Account Status ");
                System.out.println("Press 3 : For Updation In Account");
                System.out.println("Press 4 : Delete Exist Account ");
                System.out.println("Press 5 : Get All Account Details ");
                System.out.println("Press 6 : Get Some Statistics ");
                System.out.println("Press 7 : Import Accounts ");
                System.out.println("Press 8 : Export Accounts ");
                System.out.println("Press 0 : To go EXIT ");
                System.out.print("\nEnter your Choice :- ");


                choice = scan.nextInt();


                switch (choice) {
                    case 1: {
                        System.out.println("\n  *8------------------- Creation of Bank Account in Processing --------------------8* \n");
                        //code
                        System.out.println("\n  *8------------------- New Bank Account created Successfully with Account ID : " + service.create() + ". --------------------8* \n");
                        break;
                    }
                    case 2: {
                        System.out.println("\n  *8------------------------- Bank Account Status with ID -------------------------8* \n");
                        //code
                        System.out.print("Enter your Account ID :- ");
                        try {
//                        System.out.println(service.get(scan.nextInt()).toString());
                            int x = scan.nextInt();
//                            System.out.println("\n" + service.get(x).toString());
                            System.out.println((service).get(x).toString());
                        } catch (InputMismatchException ex) {
                            System.out.println("\n-------- Mind Your Input Statement -------");
                        } catch (Exception ex) {
                            System.out.println("\n-------- We Don't have Account by this ID -------");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\n  *8------------------- Welcome! Updation in Account Status Counter --------------------8* \n");
                        //code
                        System.out.print("Enter your Account ID :- ");
                        if (!service.update(scan.nextInt()))
                            System.out.println("\n-------- We Don't have Account by this ID -------");
                        break;
                    }
                    case 4: {
                        System.out.println("\n  *8------------------- Deletion of Account on Processing... --------------------8* \n");
                        //code
                        System.out.print("Enter your Account ID :- ");
                        if (service.delete(scan.nextInt())) {
                            System.out.println("\n  *8------------------- Account Delete Successfully...  --------------------8* \n");
                        } else {
                            System.out.println("\n-------- We Don't have Account by this ID -------");
                        }


                        break;
                    }
                    case 5: {
                        System.out.println("\n  *8-------------------All Bank Application Account Details--------------------8* \n");
                        service.getAll();
                        break;
                    }
                    case 6: {
                        System.out.println("\n  *8-------------------Let's Go to PreDefined Data Statistics--------------------8* \n");
                        service.printStatistics();
                        break;
                    }
                    case 7: {
                        System.out.println("\n   *8------------------- Importing Account Details are in Progress...  --------------------8* \n ");
                        service.importAccounts();
                        break;
                    }
                    case 8: {
                        System.out.println("\n   *8------------------- Exporting Account Details in Progress...  --------------------8* \n ");
                        service.exportAccounts();
                        break;
                    }
                    default: {
                        if (choice == 0) {
                            System.out.println("\n*--------------------------  🙏 😘  T H A N K    Y O U   T O   V I S I T   B A N K   A P P  😍 🙏  -----------------------------*");
                            ExecutorService executorServiceForExports = Executors.newFixedThreadPool(3);
                            executorServiceForExports.submit(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        System.out.print("\t\t\t\t\t\t\t\t\t\t");
                                        ((AccountServiceTreeMapImpl) service).storeDataToDatabase();
//                                    System.out.println(Thread.currentThread().getName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            executorServiceForExports.shutdown();
                        } else
                            System.out.println("\nDo you want to try some more Operations...");
                    }
                }
            } catch (AccountException ex) {
                System.out.println("Ohh! Mind Your Input...");
            }
        }
        while (choice != 0);


    }


}


