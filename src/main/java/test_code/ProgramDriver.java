package test_code;

import java.sql.*;

public class ProgramDriver {

    public static void main(String[] args) {
        Connection c = null;

        Statement stmt = null;

        try {

            //Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres", "Lik@su9121");

//     c.setAutoCommit(false);

            System.out.println("Successfully Connected.");



            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "select * from chinook.\"Album\" ;" );

            while ( rs.next() ) {

                int albumid = rs.getInt("AlbumId");

                String  title = rs.getString("Title");

                int artistid  = rs.getInt("ArtistId");

                System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );

                System.out.println();

            }

            rs.close();

            stmt.close();

            c.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }

        System.out.println(" Data Retrieved Successfully ..");
        //System.out.println("Table created successfully");
    }
}
