import java.util.Scanner;
import java.sql.*;

public class App {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/web?user=root&password=demo";


    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER_NAME);

        String value = "";
        try(Scanner sc = new Scanner(System.in)){
            System.out.print("Введите строку для поиска: ");
            value = sc.nextLine();
        }



        try(Connection connect = DriverManager.getConnection(CONNECTION_STRING)){
            String sql = "SELECT title, length FROM courses WHERE title LIKE '%" + value + "%' ORDER BY title";
            Statement cmd = connect.createStatement();
            ResultSet result = cmd.executeQuery(sql);

            while(result.next()){
                System.out.printf("%-50s \t %d\n", result.getString("title"),result.getInt("length"));
            }

            result.close();

        }
        
    }
}
