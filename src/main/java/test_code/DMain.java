package test_code;

import DAO.AccountService;
import controller.CustomerController;
import model_entity.Account;
import model_entity.Customer;
import model_entity.SavingAccount;

public class DMain {

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
//            Account account = new CheckingAccount(825,customer,352377623,500);
            Account account2 = new SavingAccount(17,customer,2003523776,1500000);

            AccountService accountDAO = new AccountService();
            //customerController.updateCustomer(900, customer);
//            System.out.println(accountDAO.saveAccount(account2));
//            System.out.println(accountDAO.getAccounts(825));
//            accountDAO.updateAccount(account2);
            //accountDAO.deleteAccount(account2);
            //accountDAO.updateBalance(account2);
            accountDAO.getAccounts();



    }
}
