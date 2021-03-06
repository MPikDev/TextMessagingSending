//@ file EmailToTxt.java
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

public class EmailToTxt {
	// the carrier that the the cell phone has
	private static String carrier;
	// the cell phone number
	private static String phone;
	// name to add to the message
	private static String to;
	// "???????????@gmail.com"
	private static String from;
	// the begin of the email address ????????? from ???????????@gmail.com
	private static String username;
	// the password to the email
	private static String password;
	// the heading of the text
	private static String subject;
	// if the character count is to longer than 155(including the name) then
	// split the message and put first half into message1 and the rest into
	// mess 2 and change the bool to true that will enable the second message to
	// be sent
	private static boolean VerizonTwoMessages = false;
	// I prefer "Hey "
	private static String greet;
	// for all carrier except for Verizon
	private static String fullMessage;
	// fist message for Verizon
	private static String message1;
	// if the VerizonTwoMessages is true this message will be sent
	private static String message2;
	// changes the print statements and the text subject for more clarity if
	// VerizonTwoMessages is true it will change to 1 of 2
	private static String MessageOutOfMessage = "1 of 1";
	// when there are two messages this will be the subject for the second
	// message
	private static String VerizonTotal = "2 of 2";

	// @post sets the email address
	public static void setEmail(String email) {
		from = email;
	}

	// @post sets the subject
	// @param a string that will be a suject header to text
	public static void setSuject(String Subject) {
		subject = Subject;
	}

	// @post sets the password
	// @param a string that is the password to the email
	public static void setPassword(String passWord) {
		password = passWord;
	}

	// @post sets the the username
	// @param a string that is the beginning of the email
	public static void setUserName(String UserName) {
		username = UserName;
	}

	// @post sets the greeting
	// @param a string that will be before the name in the message like "Hey "
	// then the name will be add
	public static void setGreeting(String greeting) {
		greet = greeting;
	}

	// @post sets the message if the message is longer than 155 then will ask
	// the user
	// if they want to split it themselves or have the program split it
	// if thats the case then VerizonTwoMessages will be sent to true
	// and MessageOutOfMessage will be set to "1 of 2"
	// at the end it will preview the message and the user can continue or quite
	// the program
	// @param a string that will the message that will be sent out
	public static void setMessage(String message) {
		fullMessage = message;
		message1 = message;
		if (message.length() > 155) {
			VerizonTwoMessages = true;
			Scanner user_input = new Scanner(System.in);
			System.out
					.println("The message is longer than 155. Do you what the app to split it or yourself? (app/y)");
			String splitingMessage = user_input.next();
			if (splitingMessage.compareTo("y") == 0) {
				System.exit(0);
			} else {
				message1 = null;
				message2 = null;
				message1 = message.substring(0, 155);
				System.out.println("message1 = " + message1);
				message2 = message.substring(155);
				System.out.println("message2 = " + message2);
				System.out
						.println("\nDid you want to keep this? (y/n)  n: will stop the app");
				splitingMessage = user_input.next();
				if (splitingMessage.compareTo("n") == 0) {
					System.exit(0);
				}
				if (VerizonTwoMessages == true)
					MessageOutOfMessage = "1 of 2";
			}
		}
		MessagePreview();
	}

	// @pre this will not check if the message is longer than 155
	// run setMessage to check the length unless you are sure the length of your
	// message
	// @post sets the first message for Verizon
	// @param a string that will be first message for Verizon
	public static void setVerizonMessage1(String message) {
		message1 = message;
	}

	// @pre check message manually to see if its shorter than 155 because it
	// will not send the complete message
	// @post sets the second message for Verizon
	// @param a string that will be second message for Verizon
	public static void setVerizonMessage2(String message) {
		VerizonTwoMessages = true;
		MessageOutOfMessage = "1 of 2";
		message2 = message;
	}

	// @pre setSuject()
	// setGreeting()
	// setMessage()
	// @post will display in console how the messages for all carriers and for
	// Verizon will look
	// if the second message has a null then it will only send one message to
	// Verizon contacts
	public static void MessagePreview() {
		System.out.println("For all carrier expect Verizon\nSubject:" + subject
				+ "\n" + greet + "<Fisrt Name>" + fullMessage + "\n");
		System.out.println("For Verizon\nSubject:" + MessageOutOfMessage + "\n"
				+ greet + "<Fisrt Name>" + message1);
		System.out.println("\nSubject:" + VerizonTotal + "\n" + message2);
		Scanner user_input = new Scanner(System.in);
		System.out
				.println("Do you want to continue with these messages? (y/n)");
		String splitingMessage = user_input.next();
		if (splitingMessage.compareTo("n") == 0) {
			System.exit(0);
		}
	}

	// @pre set all the setter functions 
	// @post first previews the the messages and asks if they look fine
	// then check what carrier to send to and sets the carrier 
	// and then calls the Emailer() sends the email
	// @param a string[][] contain the contact info from the CSV 
	// and the index of which contact to send too
	public static void SendEmail(String[][] CSVFile, int index) {
		MessagePreview();
		if (CSVFile[index][3].compareTo("A") == 0) {
			carrier = "@txt.att.net";
			Emailer(CSVFile, index, carrier);

			// @mms.att.net
			// @txt.att.net
		} else if (CSVFile[index][3].compareTo("V") == 0) {
			carrier = "@vtext.com";
			verzion(CSVFile[index][2], CSVFile[index][0]);
		}
		// @vtext.com
		// @vzwpix.com
		else if (CSVFile[index][3].compareTo("S") == 0) {
			carrier = "@messaging.sprintpcs.com";
			Emailer(CSVFile, index, carrier);

		} else if (CSVFile[index][3].compareTo("T") == 0) {
			carrier = "@tmomail.net";
			Emailer(CSVFile, index, carrier);
		} else if (CSVFile[index][3].compareTo("ST") == 0) {
			carrier = "@mms-tf.net";
			Emailer(CSVFile, index, carrier);
		} else if (CSVFile[index][3].compareTo("C") == 0) {
			carrier = "@mms.mycricket.com";
			Emailer(CSVFile, index, carrier);
			// @sms.mycricket.com
		} else if (CSVFile[index][3].compareTo("U") == 0) {

			for (int car = 0; car < 6; car++) {
				if (car == 0) {
					carrier = "@txt.att.net";
					Emailer(CSVFile, index, carrier);
					// @mms.att.net
				} else if (car == 1) {
					carrier = "@vtext.com";
					verzion(CSVFile[index][2], CSVFile[index][0]);
				}
				// @vtext.com
				// @vzwpix.com
				else if (car == 2) {
					carrier = "@messaging.sprintpcs.com";
					Emailer(CSVFile, index, carrier);
				} else if (car == 3) {
					carrier = "@tmomail.net";
					Emailer(CSVFile, index, carrier);
				} else if (car == 4) {
					carrier = "@mms-tf.net";
					Emailer(CSVFile, index, carrier);
				} else if (car == 5) {
					carrier = "@mms.mycricket.com";
					Emailer(CSVFile, index, carrier);
					// @sms.mycricket.com
				}
			}
		}
	}

	// @pre set all the setter functions 
	// @post send the email
	// @param a string[][] contain the contact info from the CSV 
	// and the index of which contact to send too
	// and string that is the carrier designated email info
	private static void Emailer(String[][] csvContact, int index, String carrier) {
		phone = csvContact[index][2];
		// Recipient's email ID needs to be mentioned.
		to = phone + carrier;
		System.out.println(to);
		// Sender's email ID needs to be mentioned

		// Assuming you are sending email through relay.jangosmtp.net

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(greet + csvContact[index][0] + fullMessage);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully for "
					+ csvContact[index][0]);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	// @pre set all the setter functions 
	// @post send the email to Verizon contacts only 
	// if sending two it will do that
	// @param a string that is the phone number 
	// and a string that is the first name of the contact
	private static void verzion(String ph, String nm) {
		phone = ph;
		// Recipient's email ID needs to be mentioned.
		to = phone + carrier;// change accordingly
		System.out.println(to);
		// Sender's email ID needs to be mentioned

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(MessageOutOfMessage);

			// Now set the actual message
			message.setText(greet + nm + message1);

			// Send message
			Transport.send(message);
			System.out.println("Sent message(" + MessageOutOfMessage
					+ ") successfully for " + nm);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		if (VerizonTwoMessages == true) {
			phone = ph;
			// Recipient's email ID needs to be mentioned.
			to = phone + carrier;// change accordingly
			System.out.println(to);
			// Sender's email ID needs to be mentioned

			// Assuming you are sending email through relay.jangosmtp.net

			// Get the Session object.
			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));

				// Set Subject: header field
				message.setSubject(VerizonTotal);

				// Now set the actual message
				message.setText(message2);

				// Send message
				Transport.send(message);

				System.out.println("Sent message(2-2) successfully for " + nm);

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}