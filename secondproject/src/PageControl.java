//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.sql.*;
//
//public class PageControl extends Thread {
//
////    private Socket clientHandelerSocket;
////    private PrintWriter out;
////    private BufferedReader in;
////
////    public PageControl(Socket socket) throws IOException {
////        this.clientHandelerSocket = socket;
//////        out = new PrintWriter(clientHandelerSocket.getOutputStream(), true);
//////        in = new BufferedReader(new InputStreamReader(clientHandelerSocket.getInputStream()));
////    }
//
//    public void run() {
//        try {
//            while (true) {
//                //out = new PrintWriter(clientHandelerSocket.getOutputStream(), true);
//                //in = new BufferedReader(new InputStreamReader(clientHandelerSocket.getInputStream()));
//
//                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/secondproject1", "root", "He3aMDeH77");
//                Statement statement = connection.createStatement();
//
//                String request = in.readLine();
//
//                if (request.startsWith("show information")) {
//                    String[] requestWhat = request.split(":");
//                    String username = requestWhat[1];
//
//                    String selectQuery = "SELECT * FROM userinformation WHERE username = ?";
//                    PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
//                    selectStatement.setString(1, username);
//
//                    ResultSet resultSet = selectStatement.executeQuery();
//
//
//                    if (resultSet.next()) {
//                        out.println("Username: " + resultSet.getString("username"));
//                        out.println("Email: " + resultSet.getString("email"));
//                        out.println("First Name: " + resultSet.getString("firstName"));
//                        out.println("Last Name: " + resultSet.getString("lastName"));
//                    } else {
//                        out.println("Information not found");
//                    }
//
//                    resultSet.close();
//                    selectStatement.close();
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                in.close();
//                out.close();
//                clientHandelerSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        }
//    }
