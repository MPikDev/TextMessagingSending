//@ file Main.java
//@ author Mark Pikulik 
//@ date 4/20/15
//@ Version 2.0

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

	public static void main(String[] args) throws IOException {
		// this is how to set up all the emailtoText stuff
		EmailToTxt e = new EmailToTxt();
		e.setEmail("???????@gmail.com");
		e.setPassword("password");
		e.setUserName("?????????");
		e.setSuject("Subject");
		e.setGreeting("Hey ");
		e.setMessage(", we are having an event this weekend");

		// this is how to setup the CSV stuff
		csvReader c = new csvReader();
		// inside the data folder is where the CSV goes
		// if you got this from github there should be 3 files
		// test, messedup, and contacts
		// I uses test to test, contacts to send to all and if it
		// fails or crashes to modify the messedup file to contain only the ones
		// that
		// did get the message
		c.setFile("Test.csv");
		c.countRowInCSV();
		c.printCSV();

		String[][] Contact = c.getCsvFile();

		// it calls the function to email and gives the index to which contact
		// to send
		for (int i = 0; i < c.getRowCount(); i++) {
			e.SendEmail(Contact, i);
		}
	}
}
