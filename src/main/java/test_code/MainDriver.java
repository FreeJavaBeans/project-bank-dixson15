package test_code;

import java.sql.*;

public class MainDriver {

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "Lik@su9121";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public long insertActor(Actor actor) {
        String SQL = " INSERT INTO reporters(first_name,last_name, email) "
                + "VALUES(?,?,?)";

        long id = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, actor.getFirstName());
            pstmt.setString(2, actor.getLastName());
            pstmt.setString(3, actor.getEmail());

           // PreparedStatement pstmt = conn.prepareStatement(sql,new String[]{"emp_id"});

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public static void main(String[] args) {
        MainDriver mainDriver = new MainDriver();
        Actor actor = new Actor("Jerry","Henry","jh@test.com");
        long id = 0;
        mainDriver.insertActor(actor);
        System.out.println(actor.getFirstName() + " \n" + actor.getLastName() + "\n" +actor.getEmail() + "\n" +id);
    }

}
