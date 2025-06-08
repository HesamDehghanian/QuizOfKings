import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandeler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandeler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/secondproject1", "root", "He3aMDeH77");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from userinformation");

                String request = in.readLine();

                String response;

                if (request.startsWith("1")) {

                    String[] requestWhat = request.split(":");
                    String username = requestWhat[1];
                    String password = requestWhat[2];

                    boolean loggedIn = false;

                    while (resultSet.next()) {
                        if (resultSet.getString("username").equals(username)) {
                            loggedIn = true;
                            if (resultSet.getString("password").equals(password)) {
                                out.println("Login successfully");
                                //وارد صفحه کاربریش باید بشه
                                break;
                            } else {
                                //System.out.println("Wrong password");
                                out.println("Wrong password");
                                break;
                            }
                        }
                    }

                    if (!loggedIn) {
                        out.println("User not found");
                    }
                    resultSet.close();
                    statement.close();
                }

                else if (request.startsWith("2")) {
                    String[] requestWhat = request.split(":");
                    String username = requestWhat[1];
                    String password = requestWhat[2];
                    String email = requestWhat[3];
                    String fname = requestWhat[4];
                    String lname = requestWhat[5];

                    boolean SignedUp = false;

                    while (resultSet.next()) {
                        if (resultSet.getString("username").equals(username)) {
                            SignedUp = true;
                            out.println("already have");
                            break;
                        }
                    }

                    if (!SignedUp) {
                        String insertQuery = "INSERT INTO userinformation (username, password,email,firstName,lastName) VALUES (?, ?,?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, fname);
                        preparedStatement.setString(5, lname);

                        // Execute the insert query
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            out.println("added successfully");
                        } else {
                            out.println("Failed to add");
                        }
                    }
                    resultSet.close();
                    statement.close();
                }

                else if (request.startsWith("4")) {
                    String[] requestWhat = request.split(":");
                    String username = requestWhat[1];
                    out.println("heloooo");

                    String selectQuery = "SELECT * FROM userinformation WHERE username = ?";
                    PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                    selectStatement.setString(1, username);
                    ResultSet resSet = selectStatement.executeQuery();

                    if (resSet.next()) {
                        out.println(resSet.getString("username"));
                        out.println(resSet.getString("email"));
                        out.println(resSet.getString("firstName"));
                        out.println(resSet.getString("lastName"));
                    } else {
                        out.println("Information not found");
                    }

                    resSet.close();
                    selectStatement.close();
                }
                connection.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
