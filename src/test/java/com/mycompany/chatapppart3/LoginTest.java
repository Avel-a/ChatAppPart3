/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapppart3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author avela
 */
public class LoginTest {
    
        Login login = new Login();
    
    // ---USERNAME TEST---
    /*Check if all the conditions were met when the user was creating
    their username, if the userame met all the conditions then it should
    be accepted.
    */
    @Test
    public void testValidUserName(){
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    /* Checks if the user met all the condition whilst creating the 
    username, if the conditions were not met then it should rejected.
    */
    @Test 
    public void testInvalidUsername(){
        assertFalse(login.checkUserName("Kyle!!!!!!!!!"));
    }
    // ---PASSWORD TEST---
    /*Checks if conditons were met when the user was creatig their
    password, if the password contain a special characters and numbers
    then it will be accepted.
    */
    @Test
       public void testValidPassword(){
            assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99"));
        } 
       /*Checks if conditions were met when the user was creating a
       password,if the password does not contain special characters and
       numbers then the password will be rejected.
       */
     @Test
     public void testInvalidPassword(){
         assertFalse(login.checkPasswordComplexity("password"));
        
     }
     //--- PHONENUMBER TEST---
     /*This method checks if the user did include the South African international
     code which is the following (+27), when they were entering their number.
     If the condition was met, then the system will return cellphone succesfully
     captured.
     */
     @Test
     public void testValidPhoneNumber(){
             assertTrue(login.checkCellPhoneNumber("+27838968976"));
   }   
     /*Checks if the user did include the South African international code, if
     the user did not include the international code then the system will
     return a message stating that the number was incorrectly formatted, the user
     should try and insert the number again.
    */
     @Test
     public void testInvalidPhoneNumber(){
             assertFalse(login.checkCellPhoneNumber("08966553"));
                   
    }
        //---REGISTRATION TEST---
     
     /*Checks if the user correctly formatted all the specified conditions above,
     if they were correct then system should return a message saying "user registered
     successfully*/
     @Test
     public void testValidregisterUser(){
         String result= login.registerUser("Kyl_1", "Ch&&sec@ke99","+27838968976");
         assertEquals("User registered successfully", result);
         
     }
        /*Cheks if the user formatteed all the specified conditions incorrectly, 
     the system is should retuen a message sayng "User registered unsuccessfully
     */

     @Test
     public void testInvalidRegisterUser(){
         String result =login.registerUser("Kyle!!!!!!!!!", "password", "08966553");
         assertNotEquals("User registred unseccessfully", result);
         
     }
     //---LOGINUSER TEST---
     /*Checks if the user is registered successfully before the user login, 
     if the user is registered successfully then the user will be able to be processed 
     with login, then the system will check if the user's username contains an
     underscore, five characters and the password have a special character
     */
     @Test
     public void testValidLoginUser(){
        login.registerUser("Kyl_1", "Ch&&sec@ke99", "+27838968976");
            boolean result= login.loginUser("Kyl_1", "Ch&&sec@ke99");
         assertTrue(result);
         
     }
     /*Checks if the user username consists of five characters and an underscore
     and if the password has a special character, if it was incorrectly formatted
     then the system should return false
     */
     @Test
        public void testInvalidLoginUser(){
          boolean result = login.loginUser("Kyle!!!!!!!!!", "password");
          assertFalse(result);
        }

  }

