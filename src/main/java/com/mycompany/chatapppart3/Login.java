/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart3;

/**
 *
 * @author avela
 */
public class Login {
       
    // Declaring variables that are going to be stored by user
    
    String username;
    String password;
    String phoneNumber;
   
    //Initialising all string fields to empty strings
    public Login(){
        this.username = "";
        this.password = "";
    }
   
    //Allows creating a Login objecct with these specified values
     public Login(String username, String password, String phonenumber){
        this.username = username;
        this.password = password;
    }
    
    /* The username entered by the user must contain an underscore (_)
    and it must contain only 5 characters
    Returns true if all conditions are met
    */
    
    public boolean checkUserName(String username){
    return username.contains("_") && username.length()<=5;
       }
    /*The user must create a password that have atleast 1 capital letter,
    1 number and special character such as (!,@,#,*)
    */
    
    public boolean checkPasswordComplexity(String password){
        
    // Declaring boolean variables
    
    boolean hasCapital = false; 
    boolean hasNumber = false;
    boolean hasSpecial = false;
    
    for (int i = 0; i< password.length(); i++){
        char c = password.charAt(i);
        
        if (Character.isUpperCase(c)){
            hasCapital = true;
        }else if (Character.isDigit(c)){
            hasNumber = true;
        }else if (!Character.isLetterOrDigit(c)){
            hasSpecial = true;
        }
     
    }
    // Returns true only if all conditions are met
    
    return password.length()>=8 && hasCapital && hasNumber && hasSpecial;
   }
    
    /* The user must create a cellphone number that starts with an 
    international code which is +27
    */
    
    public boolean checkCellPhoneNumber(String phone){
        return phone.startsWith("+27" ) && phone.length()>=12;
    }
    
    /* This registration method signs up new users for the application
       before saving the user's details, it checks if the user's 
       password is in the correct format. If the user's username is in the 
       right format. It also checks if the user's cellphone number contains
       the South African international code.
    */
     
    public String registerUser(String username,String password,String phoneNumber){
        if(!checkUserName(username)){
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length";
        }
        
        if(!checkPasswordComplexity(password)){
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, and a special character";
        }
        if(!checkCellPhoneNumber(phoneNumber)){
            return "Cell phone number incorectlly formatted or does not contain an international code";
        }
        // If the validation passed, then store the user's information
        this.username= username;
        this.password= password;
        
        return "User registered successfully";
        
        
    }
    
   //This method gives authintication to user's
    public boolean loginUser(String username, String password){
        if (username== null || password == null) 
            return false;
        return this.username.equals(username) && this.password.equals(password);
     
    }
   /*This login method gives the user's a welcome message, based on whether they
    were able to login successfully or not
    */
    public String returnLoginStatus(boolean success){
        if(success){
           return "Welcome " + username +  " it is great to see you again";
                   
        }else {
            return "username or password incorrect, please try again";
        }
    }
    
}
