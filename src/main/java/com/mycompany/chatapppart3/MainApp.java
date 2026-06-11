/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

import java.util.Scanner;

/**
 *
 * @author avela
 */
public class MainApp {
      public static void main(String[] args){
    
        //Declaring a new scanner
        
        Scanner input = new Scanner(System.in);
        
        //This gets the login page ready for the user
        
        Login login = new Login();
        
        // --- REGISTRATION SECTION ---
        
        /*This method where the user is going  to be asked to insert their 
        username, password and their cellphone number in order to create their
        profile
        */
        
        System.out.println("=== USER REGISTRATION ===");
        while(true){
        //Prompting the user to insert a name
        System.out.println("Enter a name:");
        String name = input.nextLine();
        //Prompting the user to insert a surname
        System.out.println("Enter a surname");
        String surname = input.nextLine();
        //Prompting the user to insert username
        System.out.println("Enter a username: ");
        String username = input.nextLine();
        
        //Prompting the user to insert password
        System.out.println("Enter a password: ");
        String password = input.nextLine();
        
        //Prompting the user to insert number
        System.out.println("Enter your South African (+27...):");
        String phone = input.nextLine();
        
        //Checks and save the username, passwoed, and phonenumber
        String response = login.registerUser(username, password,  phone);
        
        //Shows the user if their registration was succesfull or not.
        System.out.println(response);
        
        if(response.equals("User registered successfully")){
            break;
        }
        System.out.println("Please try again");
        }
        //---LOGIN SECTIION---
        
        /* This method is where it ask the registered user for their 
        username and password in order for the user's to login
        */
         System.out.println("\n === USER LOGIN ===");

// Variable used to check login
boolean loggedIn = false;

// This loop keeps asking until login is correct
while (!loggedIn) {

    // Prompting the user to enter username
    System.out.println("Enter your username:");
    String loginUsername = input.nextLine();

    // Prompting the user to enter password
    System.out.println("Enter your password:");
    String loginPassword = input.nextLine();

    // Checking login details
    loggedIn = login.loginUser(loginUsername, loginPassword);

    // Display login result
    System.out.println(login.returnLoginStatus(loggedIn));

    // If login fails
    if (!loggedIn) {

        System.out.println("Please try again.");
    }
}
        //--- QUICKCHAT SECTION---
        
       if (loggedIn) {
        // Displaying a welcom message to the user
        System.out.println("Welcome to QuickChat.");
        boolean running = true;

        // While loop keeps application running
        while (running) {

        // Display menu to the user
        System.out.println("\n===== CHAT APP MENU =====");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");        
        System.out.println("4) Stored Messages ");

        //Prompting theuser to choose menu option
        System.out.println("Choose an option:");

        // Store menu choice
        int choice = input.nextInt();
         input.nextLine();
         
        // Switch statement handles menu options
        switch (choice) 
        {
        case 1:

        //Asking the user how many messages they want to send
        System.out.println("How many messages would you like to send?");

        // Store number of messages
        int numMessages = input.nextInt();
         input.nextLine();

        // For loop runs based on number entered
        for (int i = 0; i < numMessages; i++) {

        // Message number from loop counter
         int messageNumber = i + 1;

        // Display message heading
        System.out.println("\n--- Message " + (i + 1) + " ---");
        
        // Prompting the user for recipient number
        System.out.println("Enter recipient number:");
        String recipient = input.nextLine();

        //Prompting the user to type a message
         System.out.println("Enter your message:");
         String messageText = input.nextLine();

        // We are creating A Message object
        Message message = new Message(messageNumber, recipient, messageText);

        // This loop keeps asking until the number is correct
      while (!message.checkRecipientCell().equals
     ("Cell phone number successfully captured.")) {

    // Display error message
    System.out.println(message.checkRecipientCell());

    // Ask user to enter number again
    System.out.println("Invalid number. Please try again:");

    recipient = input.nextLine();

    // Create message object again
    message = new Message(messageNumber, recipient, messageText);
}
         // Display recipient validation result
                    System.out.println(message.checkRecipientCell());

                    // Display message length result
                    System.out.println(message.checkMessageLength());

                    // Continue only if inputs are valid
                    if (message.checkMessageLength().equals
                    ("Message ready to send.")) {

                        // Ask user what to do with message
                        String result = message.sentMessage();

                        // Display result
                        System.out.println(result);

                        // Print message details
                        System.out.println(message.printMessages());
           }                 
        }
        break;
        case 2:
            
        //This is going to display to the user that this feature has been not created yet
        System.out.println("Coming Soon.");
        
        // Display total messages sent
        System.out.println("Total messages sent: "
        + Message.returnTotalMessages());
        break;
        case 3:

        //This is going to display a exit message to the user
        System.out.println("Exiting ChatApp...");

        // Stops while loop
        running = false;

        break;
        case 4:

                // Opens stored messages menu
                storedMessagesMenu(input);

                break;

            // This handles invalid menu choices entered by the user
            default:

                System.out.println(
                "Invalid option. Please choose between 1 and 4."
                );
           }
        }
        
        } else {

        // This is the message that is going to be displayed if login fails
        System.out.println("Login failed, please try again.");
        }
      }  
    
// =====================================================
// STORED MESSAGES SUB MENU
// =====================================================

public static void storedMessagesMenu(
        Scanner input) {

    // Variable controls loop
    boolean back = false;

    // Loop keeps submenu running
    while (!back) {

        // Display submenu heading
        System.out.println(
        "\n===== STORED MESSAGES MENU ====="
        );

        // Display submenu options
        System.out.println(
        "a) Display all stored messages"
        );

        System.out.println(
        "b) Display longest message"
        );

        System.out.println(
        "c) Search by Message ID"
        );

        System.out.println(
        "d) Search by Recipient"
        );

        System.out.println(
        "e) Delete message using Hash"
        );

        System.out.println(
        "f) Display full report"
        );

        System.out.println(
        "g) Back to main menu"
        );

        // Ask user for option
        System.out.println(
        "Choose an option:"
        );

        // Store user choice as String
        String option =
        input.nextLine();

        // Switch statement handles submenu
        switch (option.toLowerCase()) {

            // =====================================
            // DISPLAY STORED MESSAGES
            // =====================================
            case "a":

                System.out.println(
                Message.displayStoredMessages()
                );

                break;

            // =====================================
            // DISPLAY LONGEST MESSAGE
            // =====================================
            case "b":

                System.out.println(
                "Longest Message:"
                );

                System.out.println(
                Message.displayLongestMessage()
                );

                break;

            // =====================================
            // SEARCH BY MESSAGE ID
            // =====================================
           case "c":

    // Ask user for message ID
    System.out.println("Enter Message ID:");

    String id = input.nextLine();

    // Display matching message
    System.out.println(
    Message.searchByMessageID(id)
    );

    break;
               

            // =====================================
            // SEARCH BY RECIPIENT
            // =====================================
           case "d":

    // Ask user for recipient number
    System.out.println("Enter recipient number:");
    String recipient = input.nextLine();

    // Display matching messages
    System.out.println(
    Message.searchByRecipient(recipient)
    );

    break;

            // =====================================
            // DELETE MESSAGE USING HASH
            // =====================================
case "e":

    // Ask user for message hash
    System.out.println("Enter message hash:");

    String hash = input.nextLine();

    // Display delete result
    System.out.println(
    Message.deleteByHash(hash)
    );

    break;
            // =====================================
            // DISPLAY FULL REPORT
            // =====================================
            case "f":
              //
                System.out.println( Message.displayReport() );
                break;

            // =====================================
            // BACK TO MAIN MENU
            // =====================================
            case "g":

                back = true;

                break;

            // =====================================
            // INVALID OPTION
            // =====================================
            default:

                System.out.println(
                "Invalid option selected. Please choose between a and g."
                );
        }
    }
}
    
}
