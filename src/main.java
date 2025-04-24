/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/
public class main {
    public static void main(String[] args) {
        Formula1Driver ob = new Formula1Driver();
        Formula1ChampionshipManager om = new Formula1ChampionshipManager();
        ob.startJDBC();
        ob.details();
        ob.statistics();
    }
}
