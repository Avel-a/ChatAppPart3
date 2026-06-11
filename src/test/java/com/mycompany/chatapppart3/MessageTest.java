/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapppart3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author avela
 */
public class MessageTest {
    
     //This a message object that is going to be used in the test
    Message message1;
    Message message2;

    //This runs before each test method.
    @BeforeEach
    public void setUp() {
        //In this method we have a valid message object and an invalid one.
        message1 = new Message( 0, "+27718693002","Hi Mike, can you join us for dinner tonight?" );
        message2 = new Message(1,"08575975889","Hi Keegan, did you receive the payment?");
    }

    /*
      Checks if message length consist of 250 characters and 
     if the correct success message has been returned.
     */
    @Test
    public void testCheckMessageLength_validMessage_returnsSuccess() { 
        String result = message1.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    /*
      Checks if the length of the message exceeds 250 and
     if the correct failure message returns.
    */
    @Test
    public void testCheckMessageLength_over250chars_returnsFailureWithCount() {
        String longMessage =
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Message message = new Message(0, "+27718693002",longMessage);
        String result = message.checkMessageLength(); 
        assertEquals(
        "Message exceeds 250 characters by 5; please reduce the size.",
        result
        );
    }

    /*
      Checks if a message containing exactly 250 characters
     and if it is accepted successfully
    */
    @Test
    public void testCheckMessageLength_exactly250Characters_returnsSuccess() {
        String text = "a".repeat(250);
        Message message = new Message(0,"+27718693002",text );
        String result = message.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    /*
    Checks if a message containing 251 characters
     if it returns the correct failure message
    */
    @Test
    public void testCheckMessageLength_251Characters_returnsFailureWithCount1() { 
        String text = "a".repeat(251);
        Message msg = new Message( 0,"+27718693002",text );
        String result = msg.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.",result);
    }

    /*
      Checks if a correctly formatted recipient number
     returns a success message
    */
    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess() {
        String result = message1.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.",result);
    }

    /*
     Checks if an incorrectly formatted recipient number
    returns the correct failure message
    */
    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure() {
        String result = message2.checkRecipientCell();
        assertEquals( "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    /* Checks if the generated message hash
     contains the expected format separator (:)
    */
    @Test
    public void testCreateMessageHash_correctFormat_endsWithExpectedWords() {
        String hash = message1.createMessageHash();
        assertTrue(hash.contains(":"));
    }

    /*
    Checks if the generated message hash
     contains of uppercase only
    */
    @Test
    public void testCreateMessageHash_isUppercase() {
        String hash = message1.createMessageHash();
        assertEquals(hash.toUpperCase(), hash);
    }

    /*
    Checks if multiple messages generate valid hashes
     using a loop
    */
    @Test
    public void testCreateMessageHash_multipleMessages_loopTest() {
        Message[] messages = {message1, message2};
        
        for (Message msg : messages) { 
            String hash = msg.createMessageHash();
            assertTrue(hash.contains(":"));
        }
    }

    /*
     Checks if the generated message ID
     is not null
    */
    @Test
    public void testGenerateMessageID_generatedID_isNotNull() { 
        String id = message1.generateMessageID(); 
        assertNotNull(id);
    }

    /*
     Checks if the generated message ID
     contains exactly 10 characters
    */
    @Test
    public void testGenerateMessageID_generatedID_isExactly10Chars() { 
        String id = message1.generateMessageID();      
        assertEquals(10, id.length());
    }

    /*
     Checks if the user selects option 1 (Send Message)
     and if the correct message is returned
    */
    @Test
    public void testSentMessage_userSelectsSend_returnsCorrectString() {
        Message msg = new Message();
        String result = msg.sentMessage(1);
        assertEquals( "Message successfully sent.", result);
    }

   /*
Checks if the user selects option 2 (Disregard Message)
and if the correct message is returned
*/
@Test
public void testSentMessage_userSelectsDisregard_returnsCorrectString() {

    // Creating message object
    Message msg = new Message();

    // Store returned result
    String result = msg.sentMessage(2);

    // Check if correct message is returned
    assertEquals("Press 0 to delete message.", result);
}
    
    /*
    Checks if the user selects option 3 (Store Message)
     and if the correct message is returned
    */
    @Test
    public void testSentMessage_userSelectsStore_returnsCorrectString() {
        Message msg = new Message();
        String result = msg.sentMessage(3);
        assertEquals("Message successfully stored.",result);
    }
  /*
 Checks if searchByMessageID returns
 the correct message when ID exists
*/
@Test
public void testSearchByMessageID_returnsCorrectMessage() {

    // Clear arrays before test
    Message.getMessageIDs().clear();
    Message.getSentMessages().clear();

    // Add test message ID
    Message.getMessageIDs().add("0838884567");

    // Add matching message
    Message.getSentMessages().add("It is dinner time!");

    // Search using message ID
    String result =
    Message.searchByMessageID("0838884567");

    // Check if correct message is returned
    assertEquals("It is dinner time!", result);
}

/*
 Checks if displayLongestMessage
 returns the correct longest message
*/
@Test
public void testDisplayLongestMessage_returnsCorrectLongestMessage() {

    // Clear stored messages array
    Message.getStoredMessages().clear();

    // Add stored messages
    Message.getStoredMessages().add("Hi");

    Message.getStoredMessages().add(
    "Where are you? You are late! I have asked you to be on time."
    );

    Message.getStoredMessages().add("Ok");

    // Store returned result
    String result =
    Message.displayLongestMessage();

    // Check if correct longest message is returned
    assertEquals(
    "Where are you? You are late! I have asked you to be on time.",
    result
    );
}

/*
 Checks if searchByRecipient
 returns all matching messages
*/
@Test
public void testSearchByRecipient_returnsMatchingMessages() {

    // Clear arrays before test
    Message.getRecipients().clear();
    Message.getSentMessages().clear();

    // Add recipients
    Message.getRecipients().add("+27838884567");
    Message.getRecipients().add("+27838884567");

    // Add messages
    Message.getSentMessages().add(
    "Where are you? You are late! I have asked you to be on time."
    );

    Message.getSentMessages().add(
    "Ok, I am leaving without you."
    );

    // Search using recipient number
    String result =
    Message.searchByRecipient("+27838884567");

    // Check if first message exists
    assertTrue(result.contains(
    "Where are you? You are late! I have asked you to be on time."
    ));

    // Check if second message exists
    assertTrue(result.contains(
    "Ok, I am leaving without you."
    ));
}

/*
 Checks if deleteByHash removes
 the correct message
*/
@Test
public void testDeleteByHash_removesCorrectMessage() {

    // Clear arrays before test
    Message.getMessageHashes().clear();
    Message.getSentMessages().clear();
    Message.getRecipients().clear();
    Message.getMessageIDs().clear();

    // Add message hash
    Message.getMessageHashes().add("HASH123");

    // Add sent message
    Message.getSentMessages().add(
    "Where are you? You are late! I have asked you to be on time."
    );

    // Add recipient
    Message.getRecipients().add("+27838884567");

    // Add message ID
    Message.getMessageIDs().add("0838884567");

    // Delete using hash
    String result =
    Message.deleteByHash("HASH123");

    // Check returned message
    assertEquals(
    "Message: Where are you? You are late! I have asked you to be on time. successfully deleted.",
    result
    );
}

/*
 Checks if displayReport contains
 all required message details
*/
@Test
public void testDisplayReport_containsRequiredFields() {

    // Clear arrays before test
    Message.getMessageHashes().clear();
    Message.getRecipients().clear();
    Message.getSentMessages().clear();

    // Add message hash
    Message.getMessageHashes().add("HASH001");

    // Add recipient
    Message.getRecipients().add("+27838884567");

    // Add sent message
    Message.getSentMessages().add(
    "Did you get the cake?"
    );

    // Generate report
    String report =
    Message.displayReport();

    // Check if report contains hash
    assertTrue(report.contains("HASH001"));

    // Check if report contains recipient
    assertTrue(report.contains("+27838884567"));

    // Check if report contains message
    assertTrue(report.contains("Did you get the cake?"));
}
}


