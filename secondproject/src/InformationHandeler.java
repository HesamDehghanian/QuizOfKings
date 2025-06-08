import java.sql.*;

public class InformationHandeler {
    private Connection connection;

    public InformationHandeler(Connection connection) {
        this.connection = connection;
    }

    public String handle(String str) throws SQLException {
        String[] parts = str.split(":");
        String num = parts[0];
        String username = parts[1];

        try (Statement statement = connection.createStatement()) {
            switch (num) {

                case "2":
                    if (userExists(statement, username)) {
                        String newUsername = parts[2];
                        statement.executeUpdate("UPDATE userinformation SET username = '" + newUsername + "' WHERE username = '" + username + "'");
                        return "successfully";
                    } else {
                        return "no user";
                    }

                case "3":
                    if (userExists(statement, username)) {
                        String newEmail = parts[2];
                        statement.executeUpdate("UPDATE userinformation SET email = '" + newEmail + "' WHERE username = '" + username + "'");
                        return "successfully";
                    } else {
                        return "no user";
                    }

                case "4":
                    if (userExists(statement, username)) {
                        String newFirstName = parts[2];
                        statement.executeUpdate("UPDATE userinformation SET firstName = '" + newFirstName + "' WHERE username = '" + username + "'");
                        return "successfully";
                    } else {
                        return "no user";
                    }
                case "5":
                    if (userExists(statement, username)) {
                        String newLastName = parts[2];
                        statement.executeUpdate("UPDATE userinformation SET lastName = '" + newLastName + "' WHERE username = '" + username + "'");
                        return "successfully";
                    } else {
                        return "no user";
                    }
                default:
                    return "Invalid";
            }
        }
    }

    private boolean userExists(Statement statement, String username) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM userinformation WHERE username = '" + username + "'");
        return resultSet.next();
    }

}