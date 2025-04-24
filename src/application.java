import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/

public class application {
    Connection connection = null;
    ResultSet res = null;
    ResultSet nes = null;
    JTextField tf = new JTextField(100);

    public void startGUI(){
        String command1 = "select * from driver join pos on driver.ID = pos.ID Order by driver.score desc";
        String command2 = "select * from driver join pos on driver.ID = pos.ID Order by driver.score";
        JFrame F = new JFrame("Racing Manager");
        F.setSize(1360, 720);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        F.setLayout(new BorderLayout());

        JButton b1=new JButton("List Ascending");
        JButton b2=new JButton("List Desc");
        JButton b3=new JButton("List Desc");

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\saura\\IdeaProjects\\SME_GradeUP_JAVA\\pexels-jonathanborba-29406740.jpg");
        JLabel label = new JLabel(backgroundImage);
        label.setSize(F.getSize());

        JPanel topPanel = new JPanel(new FlowLayout());

        topPanel.add(b1);
        topPanel.add(b2);
        topPanel.add(b3);

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    display2(command1);
                }
                catch (Exception i)
                {
                    System.out.println(i.getMessage());
                }
            }
        });
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    display2(command2);
                }
                catch (Exception i)
                {
                    System.out.println(i.getMessage());
                }
            }
        });

        F.add(topPanel, BorderLayout.NORTH);
        F.add(tf);

        F.add(label);
        F.setVisible(true);
    }

    public void startJDBC() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver", "root", "Suman21@");
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public void display2(String str)
    {
        try{
            Statement smt = connection.createStatement();
            res = smt.executeQuery(str);
            System.out.print("ID\tName\tTeam\tScore");
            while (res.next())
            {
                String w = res.getString("ID");
                String x = res.getString("Name");
                String y = res.getString("Team");
                int z = res.getInt("Score");
                tf.setText(w+"\t"+x+"\t"+y+"\t"+z+"\n");
            }
        }
        catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
    public static void main(String[] args)  {
        application ob = new application();
        ob.startGUI();
    }
}
