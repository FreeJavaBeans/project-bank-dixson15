package test_code;

import DAO.CustomerDAO;
import controller.CustomerController;
import model_entity.Customer;

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

        Customer customer = new Customer("Jacy","Dubroula2",19);
       // Customer customer = new Customer();

//
        CustomerDAO customerDAO = new CustomerDAO();
//        System.out.println(customerDAO.saveCustomer(customer));
        //customerDAO.getCustomer(28);
        //customerDAO.updateCustomer(9, customer);


        CustomerController customerController = new CustomerController();
        //System.out.println(customerController.saveCustomer(customer));
       System.out.println(customerController.deleteCustomer(19));



    }
}
