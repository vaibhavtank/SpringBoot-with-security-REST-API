package com.spring.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSend {





    public static Boolean forgotpassword(String username, String email2, String password) throws MessagingException {
        // TODO Auto-generated method stub
        String result=null;
        //String authlink="http://localhost:8080/FRirstApp/verifyaccount.jsp?panno="+applypancardnri.getRandomnum()+"&num="+applypancardnri.getRandomnum();
        String host = "smtp.gmail.com";
        String Password = "Satraj01"; //provide you email account password here
        String from = "vaibhavtank@gmail.com";       //give email id of gmail
        String toAddress = email2;
        String subject="Forgot Password";
        String bodyText="Hello "+username+", \n \nYour password is "+password+" .";
        String footerText="\nThanks And Regards ,\nNeverStop";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session1 = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session1);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, toAddress);

        message.setSubject(subject);

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText(bodyText+"\n"+footerText);

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        message.setContent(multipart);

        try {
            Transport tr = session1.getTransport("smtps");
            tr.connect(host, from, Password);
            System.out.println("after connect");
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("sent msg");
            tr.close();
            System.out.println("close");
            result="Your mail send Successfully..";
            return true;
        } catch (SendFailedException sfe) {
            result="Error Occured During sending your mail...";
            return false;
        }

    }
    
    
    
    
    public static void sendMail(String username, String email2, String password) {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vaibhavtank@gmail.com", "Satraj01");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = "vaibhavtank@email.com";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            
            String subject="Forget Password";
            String bodyText="Hello "+username+", Your password is "+password+"";
            String footerText="Thanks And Regards ,NeverStop";
            
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setText(bodyText+"\n"+footerText);
            msg.setSentDate(new Date());
            msg.setText("Forget Password");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
    }
}
