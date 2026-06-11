# QuickChat Application - Part 3

## Student Information

**Student Name:** Avela
**Module:** Programming 1A
**Project:** QuickChat Application (POE Part 3)
**Language:** Java
**IDE:** Apache NetBeans

# QuickChat Application - POE Part 3

## Overview

Part 3 extends the QuickChat application by introducing ArrayLists, message searching, message deletion, message reporting, and message storage functionality. These features allow users to manage messages more effectively and retrieve information from previously sent or stored messages.

---

## New Features Implemented

### 1. Store Messages

Users can choose to store messages for later instead of sending them immediately.

Stored messages are saved in:

* ArrayLists
* JSON file (`messages.json`)

The following information is stored:

* Message ID
* Message Number
* Message Hash
* Recipient Number
* Message Text

---

### 2. Display Stored Messages

The system can display all messages that have been stored for later use.

This feature loops through the stored messages collection and displays each message.

---

### 3. Display Longest Message

The system searches through all stored messages and returns the message with the greatest number of characters.

This functionality uses a loop and comparison logic to identify the longest message.

---

### 4. Search by Message ID

The user can enter a Message ID and the application searches for a matching message.

If found:

* The corresponding message is returned.

If not found:

* "Message not found." is displayed.

---

### 5. Search by Recipient

The user can enter a recipient cellphone number.

The system searches all stored recipients and returns every message sent to that recipient.

This feature uses:

* ArrayLists
* StringBuilder
* Looping structures

---

### 6. Delete by Message Hash

The user can enter a Message Hash.

The application:

* Searches for the matching hash.
* Removes the message.
* Removes all related information from the corresponding ArrayLists.

Success Message:

Message: [message text] successfully deleted.

---

### 7. Display Full Message Report

The application generates a complete report showing:

* Message Hash
* Recipient Number
* Message Text

The report is generated using StringBuilder and data stored in parallel ArrayLists.


These collections are used to store and retrieve message information throughout the application.


