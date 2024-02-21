import java.sql.*;
import java.util.Scanner;
import static java.lang.System.out;

public class App {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/web?user=root&password=demo";

    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER_NAME);


        Scanner sc = new Scanner(System.in);
        out.print("Введите наименование курса: ");
        String name = sc.nextLine();
        out.print("Введите длительность курса: ");
        int length = sc.nextInt();
        out.print("Введите описание курса: ");
        String desc = sc.nextLine();
        sc.close();

        try(Connection connect = DriverManager.getConnection(CONNECTION_STRING)){


            String sql = "INSERT INTO courses (title, length, description) VALUE (?,?,?)";

            PreparedStatement cmd = connect.prepareStatement(sql);
            cmd.setString(1, name);
            cmd.setInt(2, length);
            cmd.setString(3, desc);

            int result = cmd.executeUpdate();
            if(result != 1){
                //
            }

        }

        
    }
}
