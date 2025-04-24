/**
 * Written by - Saurav Suman
 * Email-Id - sauravsuman1020@outlook.com
 * Mobile No. - +91 6203820253
 **/

abstract class Driver {
    String id;
    String name;                /* Name of the driver **/
    String location;/* Location Of the driver **/
    String team;
    String date;
    int score;
    abstract void details();
    abstract void statistics(); /* Statistics of the driver **/
}
