/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Formula1Driver extends Driver {
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


    Connection connection = null;


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
    int[] pos = new int[10];
    int[] points = {25,18,15,12,10,8,6,4,2,1};
    Scanner sc = new Scanner(System.in);


    @Override
    public void details() {
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
            Formula1ChampionshipManager oj = new Formula1ChampionshipManager();
            oj.cars(team,id);
            Statement smt = connection.createStatement();
            smt.executeUpdate("insert into driver (ID,Name,Location,Team) values("+id+","+name+","+location+","+team+")");
            smt.executeUpdate("insert into pos(ID)values("+id+")");
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
                System.out.print("Enter the date of the race: ");
                date = sc.next();
                pos[i] = 1;
                smt.executeUpdate("update pos set "+x[i]+" = "+pos[i]+" where ID = "+id);
                smt.executeUpdate("insert into drace(ID,date)values("+id+","+date+")");
            }
            for(int i = 0; i < 10; i++)
            {
                score += pos[i] * points[i];
            }
            smt.executeUpdate("update driver set Score = "+score+" where ID = "+id);
            dataStore();
        }
       catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
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
                        w.write("Score = " + score);
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
}
