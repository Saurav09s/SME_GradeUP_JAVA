/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Formula1Driver extends Driver {
    public void startJDBC() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver", "root", "Suman21@");
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
    Connection connection = null;
    String[] x = {"First", "Second", "Third", "Fourth","Fifth","Sixth","Seventh","Eighth","Ninth","Tenth"};
    int[] pos = new int[10];
    int[] points = {25,18,15,12,10,8,6,4,2,1};
    Scanner sc = new Scanner(System.in);
    @Override
    void details() {
        score = 0;
        try {
            System.out.println("Connection successful!");
            System.out.print("Enter the id of the driver: ");
            id = sc.next();
            System.out.print("Enter the name: ");
            name = sc.next();
            System.out.print("Enter the location: ");
            location = sc.next();
            System.out.print("Enter the team name: ");
            team = sc.next();
            Statement smt = connection.createStatement();
            smt.executeUpdate("insert into driver (ID,Name,Location,Team) values("+id+","+name+","+location+","+team+")");
            smt.executeUpdate("insert into pos(ID)values("+id+")");
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    @Override
    void statistics() {
        try {
            Statement smt = connection.createStatement();
            System.out.println("Enter how many times they have achieved a certain position: ");
            for (int i = 0; i < 10; i++)
            {
                int m = i+1;
                System.out.print("No of "+m+"position:");
                pos[i] = sc.nextInt();
                smt.executeUpdate("update pos set "+x[i]+" = "+pos[i]+" where ID = "+id);
            }
            for(int i = 0; i < 10; i++)
            {
                score += pos[i] * points[i];
            }
            smt.executeUpdate("update driver set Score = "+score+" where ID = "+id);
        }
       catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

    }
}
