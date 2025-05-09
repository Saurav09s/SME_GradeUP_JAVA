/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;



public class Formula1ChampionshipManager implements ChampionshipManager {


    Formula1Driver ob = new Formula1Driver();
    public void dataStore(){
        try {
            File f = new File("C:\\Users\\saura\\IdeaProjects\\SME_GradeUP_JAVA\\Record.txt");
            if(f.createNewFile())
            {
                System.out.println("File created..");
            }
            else {
                System.out.println("File already exist..");
                storedata();
            }
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    public void storedata()
    {
        try {
            try (FileWriter w = new FileWriter("C:\\Users\\saura\\IdeaProjects\\SME_GradeUP_JAVA\\Record.txt")) {
                {
                    w.write("Name = " + name);
                    w.write("ID = " + id);
                    w.write("Location = " + location);
                    w.write("Team =" + team);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public void readData()
    {

        try (FileReader fileReader = new FileReader("C:\\Users\\saura\\IdeaProjects\\SME_GradeUP_JAVA\\Record.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Connection connection = null;
    ResultSet res = null;
    ResultSet nes = null;
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
    String date;
    int k = 0;
    String[] company = new String[100];
    int pos;

    @Override
    public void drivers() {

        System.out.print("Enter Your Choice: ");
        int ch = sc.nextInt();
        switch (ch)
        {
            case 1:/* Create a new driver **/
                ob.startJDBC();
                ob.details();
                ob.statistics();
                break;

            case 2:/* Delete a driver from Formula1 championship. **/
                System.out.print("Enter Your ID: ");
                id = sc.next();
                startJDBC();
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
                startJDBC();
                update();
                break;

            case 4: /* Display the statistics of a particular driver **/
                System.out.print("Enter the id of the driver: ");
                id = sc.next();
                startJDBC();
                display1();
                break;

            case 5:/* Display the complete driver table **/
                startJDBC();
                display2();
                break;

            case 6: /* Add a race **/
                startJDBC();
                Race1();
                update2();
                break;

            case 7: /* Saving in a file all the information entered by the user so far **/
                dataStore();
                break;

            case 8: /* Read the information from a file and starts writing from the last character**/
                readData();
                break;

            case 9:
                try {
                    connection.close();
                }
                catch (Exception e){
                    System.out.print(e.getMessage());
                }
                break;

            default:
                System.out.println("Wrong Choice");
        }
    }

    @Override
    public void cars(String t,String iid) {
        try{
            startJDBC();
            Statement smt = connection.createStatement();
            company[k] = t;
            k++;
            smt.executeUpdate("insert into car (ID,Name) values("+iid+","+t+")");
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

    }

    public void delete()
    {
        try{
            Statement smt = connection.createStatement();
            smt.executeUpdate("delete from driver where id ="+id);
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
            res = smt.executeQuery("select * from driver join drace on driver."+id+"= drace."+id);
            System.out.print("ID\tName\tTeam\tScore\tDate");
            while (res.next())
            {
                String w = res.getString("ID");
                String x = res.getString("Name");
                String y = res.getString("Team");
                int z = res.getInt("Score");
                String v = res.getString("date");
                System.out.print(w+"\t"+x+"\t"+y+"\t"+z+"\t"+v);
            }
        }
       catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }



    public void display2()
    {
        try{
            Statement smt = connection.createStatement();
            res = smt.executeQuery("select * from driver");
            System.out.print("ID\tName\tTeam\tScore");
            while (res.next())
            {
                String w = res.getString("ID");
                String x = res.getString("Name");
                String y = res.getString("Team");
                int z = res.getInt("Score");
                System.out.print(w+"\t"+x+"\t"+y+"\t"+z);
            }
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }



    public void Race1()
    {
        try{
            Statement smt = connection.createStatement();
            res = smt.executeQuery("Select count(ID) from driver");
            while (res.next()){
                count = res.getInt("count(ID)");
            }
            for (int i = 0; i < count; i++)
            {
                new_pos[i] = rand.nextInt(1,11);
            }
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public void update2()
    {

        try{
            Statement smt = connection.createStatement();
            int i = 0;
            res = smt.executeQuery("Select ID from driver");
            while (res.next() && i < count){
                String e = res.getString("ID");
                smt.executeUpdate("update pos set "+x[new_pos[i]]+" = "+(x[new_pos[i]]+"+"+1)+" where ID = "+e);
                System.out.print("Enter the date of the race: ");
                date = sc.next();
                smt.executeUpdate("insert into drace(ID,date)values("+e+","+date+")");
                int j = 0;
                while (j<10)
                {
                    nes = smt.executeQuery("Select"+x[j]+"from driver where ID = "+e);
                    while (nes.next()){
                        pos = nes.getInt(x[j]);
                    }
                    score += pos * points[j];
                    j++;
                }
                smt.executeUpdate("update driver set Score = "+score+" where ID = "+e);
                i++;
            }
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
