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
		
	public static void main(String [] args) throws IOException  {    
		EmailToTxt e = new EmailToTxt();
		e.setEmail("???????@gmail.com");
		e.setPassword("password");
		e.setUserName("?????????");
		e.setSuject("Subject");
		e.setGreeting("Hey ");
		e.setMessage(", we are having an event this weekend");

		csvReader c = new csvReader();
		c.setFile("Test.csv");
		c.countRowInCSV();
		c.printCSV();
		
		String [][] Contact = c.getCsvFile();
		
		for(int i = 0; i < c.getRowCount(); i++){
			e.SendEmail(Contact, i);
		}
	}
	}


