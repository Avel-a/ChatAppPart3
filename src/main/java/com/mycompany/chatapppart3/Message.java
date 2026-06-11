/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author avela
 */
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;



/*
 *
 * @author avela
 */
public class Message {

    
    //Declaring all the variables that are going to be used.
     private String messageID;
     private int messageNumber;
     private String recipientCell;
     private String messageText;
     private String messageHash;
     private String messageStatus;
     private String messageStore;
     private  static int totalMessages;
     
     //
private static List<String> sentMessages = new ArrayList<>();
private static List<String> disregardedMessages = new ArrayList<>();
private static List<String> storedMessages = new ArrayList<>();
private static List<String> messageHashes = new ArrayList<>();
private static List<String> messageIDs = new ArrayList<>();
private static List<String> recipients = new ArrayList<>();
    
    //Initialising all string fields to empty strings
     public Message(){
     this.messageID = "";
     this.messageNumber= 0;
     this.recipientCell= "";
     this.messageText = "";
     this.messageHash = "";
     this.messageStatus = "";
  
    }
    //Allows creating messages objecct with these specified values
    public Message(int messageNumber, String recipient, String messageText) {

    this.messageNumber = messageNumber;
    this.recipientCell = recipient;
    this.messageText = messageText;
    this.messageID = generateMessageID();
    this.messageHash = createMessageHash();
    this.messageStatus = "";
    
    }
    //Generating a random 10-digit message id
     public String generateMessageID() {
       
    //Creating random object used to create random numbers
     Random random = new Random();
        
    //Empty string used to create random numbers
     String messageID= "";
 
    //The loop have to run 10 times
     for (int i = 0; i < 10; i++) {

   //Add random digit to ID
     messageID = messageID + random.nextInt(10);
    }
   //Returning
    return messageID;
    }
     
    //We are checking if the message ID is valid and if it contains only 10 characters.
     public boolean checkMessageID() {
     
    //we are checking the length of message ID
     if (messageID.length() <= 10) {

    return true;

    } else {

    return false;
       }
    }
   //We are checking if recipient number is correct
     public String checkRecipientCell() { 
   
    //we are checking if the number start wih +27 and if the length is correct
     if (recipientCell.startsWith("+27") && recipientCell.length()<= 12) {

     return "Cell phone number successfully captured.";

    } else {

     return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    //This method checks if the message is under 250 characters
     public String checkMessageLength() {
     
    //We are if the length of the message is correct
    if (messageText.length() <= 250) {

     return "Message ready to send.";

    } else {
        
    //Calculate how many characters are over the limit
     int over = messageText.length() - 250;

     return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    // We are creating a message hash
     public String createMessageHash() {

    //Get first 2 numbers from message ID
     String firstTwoDigits = messageID.substring(0, 2);
     
    //Split message into words
     String[] words = messageText.split(" ");

     //
    String firstWord =
    words[0].replaceAll("[^a-zA-Z0-9]", "");

    //
   String lastWord =
   words[words.length - 1] .replaceAll("[^a-zA-Z0-9]", "");
    //Build hash format
     String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;

    //Return hash in uppercase
     return hash.toUpperCase();
    }
     
   //Allows the user to select what to do with the message
   public String sentMessage() {

    //Scanner for user input
     Scanner input = new Scanner(System.in);

    //Display menu options
     System.out.println("What would you like to do with this message?");
     System.out.println("1) Send Message");
     System.out.println("2) Disregard Message");
     System.out.println("3) Store Message to send later");
    
    //Store user option
     int choice = input.nextInt();

        switch (choice) {

            case 1:

                messageStatus = "Sent";

                totalMessages++;

                sentMessages.add(messageText);

                messageHashes.add(messageHash);

                messageIDs.add(messageID);

                recipients.add(recipientCell);

                return "Message successfully sent.";

            case 2:

                messageStatus = "Disregarded";
            disregardedMessages.add(messageText);

               return "Press 0 to delete the message.";

            case 3:

                messageStatus = "Stored";

                storedMessages.add(messageText);

                messageHashes.add(messageHash);

                messageIDs.add(messageID);

                recipients.add(recipientCell);

                storeMessage();

                return "Message successfully stored.";
                 default:

                return "Invalid option selected.";
        }
    }

  // Used for unit testing
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete message.";

            case 3:

                return "Message successfully stored.";

            default:

                return "Invalid option selected.";
        }
    }
     // Print message details
    public String printMessages() {

        String details = "";

        details += "Message ID: "
                + messageID + "\n";

        details += "Message Hash: "
                + messageHash + "\n";

        details += "Recipient: "
                + recipientCell + "\n";

        details += "Message: "
                + messageText + "\n";

        return details;
    }
     // Returns total number of sent messages
     public static int returnTotalMessages() {

    return totalMessages;
    
    }  
    //We are going to store the message into our JSON file
     public void storeMessage() {

    //We are creating JSON object
     JSONObject obj = new JSONObject();

    //We are going to add the message ID to JSON
    obj.put("messageID", messageID);
    
    //
    obj.put("messageNumber", messageNumber);

    //We are going to add message hash to JSON
     obj.put("messageHash", messageHash);

    //We are going to add recipient number to JSON
     obj.put("recipient", recipientCell);

    //Add message text to JSON
     obj.put("message", messageText);

    //We are going to try writing to JSON file
    try (FileWriter file =
    new FileWriter("messages.json", true)) {

    file.write(obj.toString());
    file.write("\n");
} catch (IOException e) {

    //Display error if file fails
    System.out.println("Error writing to JSON file.");
        }
    }
   
    // =====================================================
// DISPLAY STORED MESSAGES
// =====================================================

public static String displayStoredMessages() {

    // Empty string used to build output
    String output = "";

    // Check if there are no stored messages
    if (storedMessages.isEmpty()) {

        return "No stored messages found.";
    }

    // Display heading
    output += "=== STORED MESSAGES ===\n\n";

    // Loop through stored messages
    for (int i = 0; i < storedMessages.size(); i++) {

        output += "Message "
        + (i + 1)
        + ": "
        + storedMessages.get(i)
        + "\n";

        output += "----------------------------------\n";
    }

    // Return final output
    return output;
}
// =====================================================
// DISPLAY LONGEST STORED MESSAGE
// =====================================================

public static String displayLongestMessage() {

    // Variable used to store the longest message
    String longest = "";

    // Loop through stored messages array
    for (int i = 0; i < storedMessages.size(); i++) {

        // Store current message
        String currentMessage = storedMessages.get(i);

        // Check if current message is longer
        if (currentMessage.length() > longest.length()) {

            // Update longest message
            longest = currentMessage;
        }
    }

    // Return longest message
    return longest;
}
// =====================================================
// SEARCH BY MESSAGE ID METHOD
// =====================================================

public static String searchByMessageID(String id) {

    // Loop through all message IDs
    for (int i = 0; i < messageIDs.size(); i++) {

        // Check if ID matches
        if (messageIDs.get(i).equals(id)) {

            // Return matching message
            return sentMessages.get(i);
        }
    }

    // If no match was found
    return "Message not found.";
}
   // =====================================================
// SEARCH BY RECIPIENT METHOD
// =====================================================

public static String searchByRecipient(String recipient) {

    // StringBuilder stores all matching messages
    StringBuilder results = new StringBuilder();

    // Loop through all recipients
    for (int i = 0; i < recipients.size(); i++) {

        // Check if recipient matches
        if (recipients.get(i).equals(recipient)) {

            // Add matching message
            results.append(sentMessages.get(i));

            // Move to next line
            results.append("\n");
        }
    }

    // If no message was found
    if (results.length() == 0) {

        return "No messages found for this recipient.";
    }

    // Return all matching messages
    return results.toString();
}

   // =====================================================
// DELETE MESSAGE BY HASH METHOD
// =====================================================

public static String deleteByHash(String hash) {

    // Loop through all message hashes
    for (int i = 0; i < messageHashes.size(); i++) {

        // Check if hash matches
        if (messageHashes.get(i).equals(hash)) {

            // Store message before deleting
            String deletedMessage = sentMessages.get(i);

            // Remove hash
            messageHashes.remove(i);

            // Remove message
            sentMessages.remove(i);

            // Remove recipient
            recipients.remove(i);

            // Remove message ID
            messageIDs.remove(i);

            // Return success message
            return "Message: " + deletedMessage
            + " successfully deleted.";
        }
    }

    // If hash was not found
    return "Hash not found.";
}

// =====================================================
// DISPLAY FULL MESSAGE REPORT
// =====================================================

public static String displayReport() {

    // StringBuilder used to build the report
    StringBuilder report = new StringBuilder();

    // Report heading
    report.append("=== MESSAGE REPORT ===\n\n");

    // Loop through all sent messages
    for (int i = 0; i < sentMessages.size(); i++) {

        // Display message number
        report.append("Message ").append(i + 1).append("\n");

        // Display message hash
        report.append("Message Hash: ")
        .append(messageHashes.get(i))
        .append("\n");

        // Display recipient
        report.append("Recipient: ")
        .append(recipients.get(i))
        .append("\n");

        // Display message text
        report.append("Message: ")
        .append(sentMessages.get(i))
        .append("\n");

        // Divider line
        report.append("-----------------------------------\n");
    }

    // If there are no messages
    if (sentMessages.isEmpty()) {

        return "No sent messages available.";
    }

    // Return completed report
    return report.toString();
}
// =====================================================
// GETTER METHODS
// =====================================================

// Returns sent messages array
public static List<String> getSentMessages() {

    return sentMessages;
}

// Returns disregarded messages array
public static List<String> getDisregardedMessages() {

    return disregardedMessages;
}

// Returns stored messages array
public static List<String> getStoredMessages() {

    return storedMessages;
}

// Returns message hashes array
public static List<String> getMessageHashes() {

    return messageHashes;
}

// Returns message IDs array
public static List<String> getMessageIDs() {

    return messageIDs;
}

// Returns recipients array
public static List<String> getRecipients() {

    return recipients;
}
}
