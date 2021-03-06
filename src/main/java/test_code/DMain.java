package test_code;

import DAO.*;
import controller.AccountController;
import controller.CustomerController;
import model_entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DMain {

    static Logger logger = LogManager.getLogger(DMain.class);
    public static void main(String[] args) {



////       String url = "jdbc:postgresql://localhost/postgres";
////        String user = "postgres";
////        String password = "Lik@su9121";
////
////        try{
////
////            Connection connection = DriverManager.getConnection(url,user,password);
////            System.out.println("Connected success!");
////
////            String sql = "INSERT INTO public.\"reporters\" (first_name, last_name, email)" +
////                    "VALUES ('Mavungu', 'Mangasa', 'mav@test')";
////            Statement statement = connection.createStatement();
////            int row = statement.executeUpdate(sql);
////
////            if(row > 0)
////                System.out.println("Row updated!!!");
////            connection.close();
////        }catch (SQLException sqlException){}
//        Connection connection = PostgresConnection.getConnection();
//
//        try{
//
////            String sql = "INSERT INTO public.\"reporters\" (first_name, last_name, email)" +
////                    "VALUES ('Mayasi', 'Kinzeza', 'mayas@test')";
//            String sql = "INSERT INTO public.\"reporters\" (first_name, last_name, email)" +
//                    "VALUES (?, ?, ?)";
//
//            //tatement statement = connection.createStatement();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setString(1,"Mohindo");
//            preparedStatement.setString(2,"Nzandi");
//            preparedStatement.setString(3,"Mohindo@test.com");
//
////            int row = statement.executeUpdate(sql);
//            int row = preparedStatement.executeUpdate();
//
//            if(row > 0)
//                System.out.println("Row updated!!!");
//
//        }catch (SQLException e){
//            e.getErrorCode();
//        }
//        System.out.println();
//        //DMain.insert("Mayala","Mosanzi","Moyika@test.com");
//    }
//    public static void insert(String x, String y, String z) {
//        Connection connection = PostgresConnection.getConnection();
//
//        try{
//
//            String sql = "INSERT INTO public.\"reporters\" (first_name, last_name, email)" +
//                    "VALUES (x, y, z)";
//
//            Statement statement = connection.createStatement();
//
//            int row = statement.executeUpdate(sql);
//
//            if(row > 0)
//                System.out.println("Row updated!!!");
//
//        }catch (SQLException e){
//            e.getErrorCode(); }



       // try{
            Customer customer = new Customer(900,"Musenzi","Nkukuta");
            //Customer customer4 = new Customer(900,"Musenzi","Nkukuta");
            // Customer customer = new Customer();

//
//        CustomerDAO customerDAO = new CustomerDAO();
//        System.out.println(customerDAO.saveCustomer(customer));
            //customerDAO.getCustomer(28);
            //customerDAO.updateCustomer(9, customer);

            CustomerController customerController = new CustomerController();
            //System.out.println(customerController.saveCustomer(customer));
            //System.out.println(customerController.deleteCustomer(19));

           // System.out.println(customerController.saveCustomer(customer));
            Account account = new CheckingAccount(825,customer,352377623,500);
            Account account2 = new SavingAccount(17,customer,2003523776,1500);
           // AccountService accountDAO = new AccountService();
            //customerController.updateCustomer(900, customer);
//            System.out.println(accountDAO.saveAccount(account2));
//            System.out.println(accountDAO.getAccounts(825));
//            accountDAO.updateAccount(account2);
            //accountDAO.deleteAccount(account2);
            //accountDAO.updateBalance(account2);
            //accountDAO.getAccounts();


//        BankTransaction bankTransaction = new BankTransaction(account);
//
//        //bankTransaction.deposit(account,500);
////        System.out.println("Transaction ID: " +bankTransaction.getTransactionId() + "\n" + "Account ID: "
////                + bankTransaction.getAccount().getId());
////        System.out.println("Transaction ID#: " + bankTransaction.getTransactionId());
//        BankTransactionDAO bankTransactionDAO = new BankTransactionDAO();
////        bankTransactionDAO.saveDepositTransaction(bankTransaction);
////        System.out.println("______________________________________________");
////        System.out.println();
////
////        BankTransaction bankTransaction2 = new BankTransaction(account);
////        bankTransaction2.withdraw(account,200);
////        bankTransactionDAO.saveWithdrawTransaction(bankTransaction2);
////        BankTransaction bankTransaction3 = new BankTransaction(account);
////        bankTransaction3.transferFund(account2,100);
////
////        bankTransactionDAO.saveTransferFundTransaction(bankTransaction3);
//
//        bankTransactionDAO.getAllTransaction(1153856065);
//
////        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        Date date = new Date();
////        System.out.println(dateFormat.format(date));
//        logger.info("test success");
        Banker employee = new Banker(2,"Bolowa","Bokul","BANK_TELLER");
//        BankerDAO bankerDAO = new BankerDAO();
//        System.out.println(bankerDAO.addEmployee(employee));
////        System.out.println(employee.getType());
//       // bankerDAO.getAllEmployee();
//
//        bankerDAO.updateEmployee(employee);
//        bankerDAO.getEmployeeByID(2);
//
//        AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();
//        Account account3 = new SavingAccount(1700,customer,2003523776,1800);
//        //accountOperationManagerDAO.createCustomerAccount(employee,account3 );
//
//        AccountDAO accountDAO = new AccountDAO();
//        accountDAO.getAccountBalance(account);

//        Account accountB = new SavingAccount(9800,customer,99999,16800);
//        //Banker employee = new Banker(2,"Bolowa","Bokul","BANK_TELLER");
////
//        AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();
////
//        System.out.println(accountOperationManagerDAO.createCustomerAccount(employee,accountB,700));
//       System.out.println(accountOperationManagerDAO.approveAccount(accountB));
//       System.out.println(accountOperationManagerDAO.getReviewAccount(accountB));
//        AccountDAO accountController = new AccountDAO();
//        accountController.getAccounts();
//        accountOperationManagerDAO.getAccountsforReview();
//        System.out.println();

        BankUser bankUser = new BankUser(customer.getEmp_id(),customer.getFirstName(),customer.getLastName(),"ndombasi","p009hopp9");
        AuthenticableDAO authenticableDAO = new AuthenticableDAO();

        //authenticableDAO.register(bankUser);
        authenticableDAO.login("ndombasi","p009hopp9");
       Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("1. LOGOUT\n2. STAY IN");
            int p = in.nextInt();
            switch (p){
                case 1:
                    authenticableDAO.logout();
                    break;
                case 2:
                    authenticableDAO.login("ndombasi","p009hopp9");
                    break;
                default:
                    System.out.println("Error occur!");
                    break;
            }
        }

    }
}
