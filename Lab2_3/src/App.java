import java.sql.*;
import static java.lang.System.out;

public class App {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/web?user=root&password=demo";

    public static void main(String[] args) throws Exception {
        Class.forName(DRIVER_NAME);

        try(Connection connect = DriverManager.getConnection(CONNECTION_STRING)){
            DatabaseMetaData meta = connect.getMetaData();
            try(ResultSet result = meta.getProcedures("web", "web", null)){
                while(result.next()){
                    out.println(result.getString("PROCEDURE_NAME"));
                }

            }

        }


    }
}
