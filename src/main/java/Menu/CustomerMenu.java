package Menu;

import DAO.AccountOperationManagerDAO;
import DAO.AuthenticableDAO;
import controller.AccountOperationManagerController;
import controller.CustomerController;
import model_entity.*;

import java.util.Scanner;

public class CustomerMenu {
    public static Scanner scan = new Scanner(System.in);

    public static void applyForAccount(){
        Customer customer = null;
        Account account = null;
        System.out.println("-------------- APPLICATION FOR BANK ACCOUNT -----------");
        System.out.println();
        System.out.println("ENTER CUSTOMER ID#:" );
        int custID = scan.nextInt();
        System.out.println("First name: ");
        String first = scan.next();
        System.out.println("Last name: ");
        String last = scan.next();
        customer = new Customer(custID,first,last);

        /**
         * save customer info
          */
        CustomerController customerController = new CustomerController();
        customerController.saveCustomer(customer);

        System.out.println();
        System.out.println("ACCOUNT ID#:");
        int accID = scan.nextInt();
        System.out.println("ACCOUNT NUMBER :" );
        long accN = scan.nextLong();
        System.out.println("ACCOUNT BALANCE :" );
        double accB = scan.nextDouble();

        System.out.println("ACCOUNT TYPE :1. SAVING \t 2. CHECKING" );
        int accT = scan.nextInt();

        switch (accT){
            case 1:
                account = new SavingAccount(accID,customer,accN,accB);
                break;
            case 2:
                account = new CheckingAccount(accID,customer,accN,accB);
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }

        Banker banker = new Banker(2,"Bolowa","Bokul","BANK_TELLER");

        AccountOperationManagerController accountOperationManagerController = new AccountOperationManagerController();
        System.out.println("Credit score: ");

        int credit = scan.nextInt();
        /**
         * store customer bank account for review and approval
         */
        System.out.println(accountOperationManagerController.createCustomerAccount(banker,account,credit));


        System.out.println("Enter R for Review:" );
        String review = scan.next().toUpperCase();

        switch (review.toUpperCase()){
            case "R":
                accountOperationManagerController.getAccountsforReview();
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }

        System.out.println("Enter S. to submit for account approval" );
        String review2 = scan.next().toUpperCase();

        switch (review2.toUpperCase()){
            case "S":
                accountOperationManagerController.approveAccount(account);
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }
        accountOperationManagerController.getAccountsforReview();
        System.out.println();
    }
    
    public static void register(){
        System.out.println("---REGISTER FOR AN ONLINE ACCOUNT__");
        System.out.println();
        System.out.print("FIRSTNAME: ");
        String first = scan.next();
        System.out.println();
        System.out.print("LASTNAME: ");
        String last = scan.next();
        System.out.println();
        System.out.println("CUSTOMER ID#: ");
        int customerId = scan.nextInt();
        System.out.println();
        System.out.println("USERNAME: ");
        String username= scan.next();
        System.out.println();
        System.out.println("PASSWORD: ");
        String password = scan.next();

        AuthenticableDAO authenticableDAO = new AuthenticableDAO();
        authenticableDAO.register(new BankUser(customerId,first,last,username,password));

    }

    public static void login(){
        System.out.println("-------LOG IN----------");
        System.out.println("USERNAME: ");
        String username= scan.next();
        System.out.println();
        System.out.print("PASSWORD: ");
        String password = scan.next();

        AuthenticableDAO authenticableDAO = new AuthenticableDAO();
        boolean result = authenticableDAO.login(username,password);

        if(result)
            CustomerMenu.customerMenu();
        else
            System.out.println("Authentication failed!");
    }

    public static void customerMenu(){
        System.out.println("------MENU--------");
        System.out.println();
        System.out.println("1. VIEW ACCOUNTS\n2. DEPOSIT\n3. WITHDRAW\n4. TRANSFER FUND\n5. LOGOUT");
    }

    public static void main(String[] args) {
        //CustomerMenu customerMenu = new CustomerMenu();
        //CustomerMenu.applyForAccount();
        CustomerMenu.login();

    }
}
