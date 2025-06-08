import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws IOException {



        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/secondproject1", "root", "He3aMDeH77");

            String number = userInput.readLine();
            while (true) {

                String searchUser = "";
                int flag = 0;
                //System.out.println("choose one of them:");
//            System.out.println("1-Login");
//            System.out.println("2-Signup");
//            System.out.println("3-page_setting:");
//            System.out.println("choose one of them:");
//            System.out.println("choose one of them:");
//            System.out.println("choose one of them:");
//            System.out.println("choose one of them:");


                if (number.equals("1")) {
                    String username;
                    String password;

                    String response;
                    do {
                        System.out.print("Enter username: ");
                        username = userInput.readLine();

                        System.out.print("Enter password: ");
                        password = userInput.readLine();

                        out.println("1:" + username + ":" + password);

                        response = in.readLine();
                        System.out.println("Server response: " + response);

                        if (response.equals("Login successfully")) {
                            System.out.println("Welcome");
                            searchUser = username;
                            number = "4";
                            break;
                        }
                        if (response.equals("User not found")) {
                            System.out.println("you need to signup first");
                            number = "2";
                            break;
                        }
                        if (response.equals("Wrong password")) {
                            System.out.println("Please try again.");
                        }

                    } while (response.equals("Wrong password"));

                }
                if (number.equals("2")) {
                    String response;
                    System.out.println("if you want to back,enter back:");
                    String userInputStr = userInput.readLine();
                    if (userInputStr.equals("back")) {
                        number = "1";
                        break;
                    } else {
                        do {
                            System.out.print("Enter username: ");
                            String username = userInput.readLine();

                            System.out.print("Enter password: ");
                            String password = userInput.readLine();

                            System.out.print("Enter your email: ");
                            String email = userInput.readLine();

                            System.out.print("Enter firstName: ");
                            String fname = userInput.readLine();

                            System.out.print("Enter LastName: ");
                            String lName = userInput.readLine();


                            out.println("2:" + username + ":" + password + ":" + email + ":" + fname + ":" + lName);

                            response = in.readLine();
                            System.out.println("Server response: " + response);

                            if (response.equals("added successfully")) {
                                System.out.println("Welcome");
                                searchUser = username;
                                number = "4";

                                break;
                            }
                            if (response.equals("already have") || response.equals("Failed to add")) {
                                System.out.println("Please try again.");
                            }
                        } while (response.equals("already have"));
                    }
                }

//                if (number.equals("3")){
//                    //first page
//                    System.out.println("1-back:");
//                    System.out.println("2-information:");
////                    System.out.println("3-score:");
////                    System.out.println("4-startPlay:");
////                    System.out.println("5-makeQuestion:");
//
//                }

                if (number.equals("4")) {             //information page
                    String response;

                    System.out.println("1-back:");
                    System.out.println("2-show:");
                    //System.out.println("3-change:");

                    String userInputStr = userInput.readLine();
                    if (userInputStr.equals("1")) {
                        number = "3";
                        //flag = 1;
                    }
                    if (userInputStr.equals("2")) {
                        out.println("4:" + searchUser);
                        response = in.readLine();
                        System.out.println("Server response: " + response);

                        if (response != null) {
                            String username = in.readLine();
                            String email = in.readLine();
                            String firstName = in.readLine();
                            String lastName = in.readLine();

                            System.out.println("Username: " + username);
                            System.out.println("Email: " + email);
                            System.out.println("First Name: " + firstName);
                            System.out.println("Last Name: " + lastName);

                        } else {
                            System.out.println("Information not found");
                        }
                        System.out.println();
                        System.out.println("1-back:");
                        System.out.println("2-change:");
                        String userInputString = userInput.readLine();
                        if (userInputString.equals("1")){
                            number = "4";
                            flag = 1;
                        }
                        if (userInputString.equals("2")){
                            InformationHandeler informationHandeler = new InformationHandeler(connection);
                            System.out.println("Select an option to change:");
                            System.out.println("1-Username");
                            System.out.println("2-Email");
                            System.out.println("3-First Name");
                            System.out.println("4-Last Name");

                            String newOption = userInput.readLine();
                            String newValue;

                            switch (newOption) {
                                case "1":
                                    System.out.println("Enter new username:");
                                    newValue = userInput.readLine();
                                    response = informationHandeler.handle(("2:" + searchUser + ":" + newValue));
                                    System.out.println("Server response: " + response);
                                    break;
                                case "2":
                                    System.out.println("Enter new email:");
                                    newValue = userInput.readLine();
                                    response = informationHandeler.handle(("3:" + searchUser + ":" + newValue));
                                    System.out.println("Server response: " + response);
                                    break;
                                case "3":
                                    System.out.println("Enter new first name:");
                                    newValue = userInput.readLine();
                                    response = informationHandeler.handle(("4:" + searchUser + ":" + newValue));
                                    System.out.println("Server response: " + response);
                                    break;

                                case "4":
                                    System.out.println("Enter new last name:");
                                    newValue = userInput.readLine();
                                    response = informationHandeler.handle(("5:" + searchUser + ":" + newValue));
                                    System.out.println("Server response: " + response);
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        }
                    }
                }

                if (number.equals("3")){
                    //first page
                    System.out.println("1-back:");
                    System.out.println("2-information:");
//                    System.out.println("3-score:");
//                    System.out.println("4-startPlay:");
//                    System.out.println("5-makeQuestion:");

                }
                if (flag != 1){
                    number = userInput.readLine();
                }


            }
            userInput.close();
            in.close();
            socket.close();
            out.close();

        } catch (SocketException e) {
            System.err.println("Connection reset. Check if the server is running and reachable.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O error occurred.");
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            try {
//                in.close();
//                out.close();
//                socket.close();
//                userInput.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}