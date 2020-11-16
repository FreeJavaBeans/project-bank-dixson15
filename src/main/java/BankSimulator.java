import DAO.AccountOperationManagerDAO;
import controller.AccountOperationManagerController;
import controller.CustomerController;
import model_entity.*;

import java.util.Scanner;

public class BankSimulator {

    private static  Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        displayInfo();
    }

    private static void displayInfo(){
        System.out.println("\n\n\t----WELCOME TO DICKSON BANK----");
        //Scanner in = new Scanner(System.in);
        try
        {
            while(true)
            {
                System.out.println("1.CUSTOMER\t2.BANKER \n3.EXIT");
                int ch = scan.nextInt();
                switch(ch)
                {
                    case 1:
                        promptCustomer();
                        break;

                    case 2:
                        promptBanker();
                        break;

                    case 3:
                        System.exit(0);
                        break;

                    default: System.out.println("Invalid Option");
                            break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("SELF THROWN EXCEPTION IS--->"+e);
        }
    }

    private static void promptCustomer() {

        System.out.println("--------------CUSTOMER SERVICE------------");
            try {
                while (true) {
                    System.out.println("1.APPLY FOR BANK ACCOUNT\n2.LOGIN \n3.EXIT");
                    int ch = scan.nextInt();
                    switch (ch) {
                        case 1:
                            applyforAccount();
                            break;

                        case 2:
                            loginToAccount();
                            break;

                        case 3:
                            displayInfo();
                            break;

                        default:
                            System.out.println("Invalid Option");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }

    private static void loginToAccount() {

        System.out.println("REGISTER");
        System.out.println("");

        System.out.println("CUSTOMER ID#:" );
        int custID = scan.nextInt();
        System.out.println("First name: ");
        String first = scan.next();
        System.out.println("Last name: ");
        String last = scan.next();

        Customer customer = new Customer(custID,first,last);
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

                break;
            case 2:

                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }

    }
    private static void applyforAccount() {
        Account account = null;
        BankTransaction bankTransaction = new BankTransaction();

        System.out.println("CUSTOMER ID#:" );
        int custID = scan.nextInt();
        System.out.println("First name: ");
        String first = scan.next();
        System.out.println("Last name: ");
        String last = scan.next();

        Customer customer = new Customer(custID,first,last);
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
        System.out.println(accountOperationManagerController.createCustomerAccount(banker,account,credit));
        AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();


        System.out.println("R for Review:" );
        String review = scan.next().toUpperCase();

        switch (review){
            case "R":
                accountOperationManagerDAO.getAccountsforReview();
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }

        System.out.println("A for Review:" );
        String review2 = scan.next().toUpperCase();

        switch (review2){
            case "A":
                accountOperationManagerDAO.approveAccount(account);
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }
        accountOperationManagerDAO.getAccountsforReview();
        System.out.println();

    }

    private static void promptBanker() {

        System.out.println("--------------BANK EMPLOYEE SERVICE------------");

        try {
            while (true) {
                System.out.println("1.REVIEW ACCOUNT FOR APPROVAL \n2.APPROVE/REJECT \n3.VIEW ALL TRANSACTIONS LOG\n4.VIEW ALL TRANSACTIONS LOG\n5.EXIT. ");

                int ch = scan.nextInt();
                switch (ch) {
                    case 1:
                        rewiewAccount();
                        break;
                    case 2:
                        approveAccount();
                        break;
                    case 3:
                        break;

                    case 4:
                        break;
                    case 5:
                        displayInfo();
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCUR!!!!!" + e);
        }
    }

    private static void rewiewAccount() {
       System.out.println("___________VIEW ACCOUNT FOR APPROVAL__________");

        try {
            AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();
            accountOperationManagerDAO.getAccountsforReview();
        }
        catch(Exception e){
            System.out.println("ERROR OCCUR!!!!!" + e);
        }
    }

    private static void approveAccount() {

        System.out.println("___________VIEW ACCOUNT FOR APPROVAL__________");

        try {

            AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();
           // accountOperationManagerDAO.approveAccount();

        }
        catch(Exception e){
            System.out.println("ERROR OCCUR!!!!!" + e);
        }
    }
}
