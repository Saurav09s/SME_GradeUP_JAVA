/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;



public class Formula1ChampionshipManager implements ChampionshipManager {
    Connection connection = null;
    ResultSet res = null;
    public void startJDBC() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver", "root", "Suman21@");
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    String[] x = {"First", "Second", "Third", "Fourth","Fifth","Sixth","Seventh","Eighth","Ninth","Tenth"};
    int[] points = {25,18,15,12,10,8,6,4,2,1};
    int score = 0, count = 0;
    int[] new_pos = new int[10];

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    String name;
    String id;
    String team;
    String location;
    int pos;

    @Override
    public void drivers() {

        System.out.print("Enter Your Choice: ");
        int ch = sc.nextInt();
        switch (ch)
        {
            case 1:/* Create a new driver **/
                Formula1Driver ob = new Formula1Driver();
                ob.startJDBC();
                ob.details();
                ob.statistics();
                break;

            case 2:/* Delete a driver from Formula1 championship. **/
                System.out.print("Enter Your ID: ");
                id = sc.next();
                delete();
                break;

            case 3: /* Change the driver for the existing team **/
                System.out.print("Enter the team: ");
                team = sc.next();
                System.out.print("Enter the id of the driver: ");
                id = sc.next();
                System.out.print("Enter the name: ");
                name = sc.next();
                System.out.print("Enter the location: ");
                location = sc.next();
                System.out.print("Enter the team name: ");
                team = sc.next();
                update();
                break;

            case 4: /* Display the statistics of a particular driver **/
                System.out.print("Enter the id of the driver: ");
                id = sc.next();
                display1();
                break;

            case 5: /* Display the complete driver table **/
                display2();
                break;

            case 6: /* Add a race **/
                Race1();
                update2();
                break;

            case 7: /* Saving in a file all the information entered by the user so far **/
                break;

            case 8: /* Read the information from a file and starts writing from the last character**/
                break;

            default:
                System.out.println("Wrong Choice");
        }
    }

    @Override
    public void cars() {
        System.out.print("Enter the name of the team: ");
        String Name = sc.next();
    }

    public void delete()
    {
        try{
            Statement smt = connection.createStatement();
            smt.executeUpdate("delete from driver where id ="+id);
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public void update(){
        try{
            Statement smt = connection.createStatement();
            smt.executeUpdate("update driver set ID = "+id+",Name = "+name+",Location = "+location+"where Team = "+team);
        }
       catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
    public void display1()
    {
        try{
            Statement smt = connection.createStatement();
            smt.executeUpdate("select * from driver where ID ="+id);
        }
       catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
    public void display2()
    {
        try{
            Statement smt = connection.createStatement();
            smt.executeUpdate("select * from driver");
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
    public void Race1()
    {
        for (int i = 0; i < 10; i++)
        {
            new_pos[i] = rand.nextInt(1,11);
        }
    }
    public void Race2()
    {

    }

    public void update2()
    {

        try{
            Statement smt = connection.createStatement();
            res = smt.executeQuery("Select count(ID) from driver");
            while (res.next()){
                count = res.getInt("count(ID)");
            }
            for (int i = 0; i < count; i++)
            {
                new_pos[i]= rand.nextInt(1,11);
            }
            int i = 0;
            res = smt.executeQuery("Select * from driver");
            while (res.next() && i < count){

                pos++;

                i++;
            }
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
