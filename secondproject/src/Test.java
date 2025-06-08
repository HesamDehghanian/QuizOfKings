import java.sql.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/secondproject1", "root", "He3aMDeH77");
        Statement statement = connection.createStatement();
        Scanner input = new Scanner(System.in);
        String a = input.next();
        if (a.equals("show")) {
            String username = input.next();
            String selectQuery = "SELECT * FROM userinformation WHERE username = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, username);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("First Name: " + resultSet.getString("firstName"));
                System.out.println("Last Name: " + resultSet.getString("lastName"));
            } else {
                System.out.println("Information not found");
            }

            resultSet.close();
            selectStatement.close();
        }
    }
}