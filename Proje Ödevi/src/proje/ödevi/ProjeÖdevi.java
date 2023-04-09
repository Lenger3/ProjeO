/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proje.ödevi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProjeÖdevi {
    public static void main(String[] args) throws IOException {
        int seçim,kontrol=0;
       
        Scanner scanner=new Scanner(System.in);
        do{ //do-while döngüsünü kontrol değişkenine bağlı olarak çıkılmak istendiği zamanda kontrol=1 olarak çıkıyor
            System.out.println("1-   Elit üye ekleme");
            System.out.println("2-   Genel üye ekleme");
            System.out.println("3-   Mail gönderme");
            System.out.println("9-  Çıkış");
            kontrol=0;
            seçim=scanner.nextInt();
            switch (seçim) {
                case 1:
                    ElitÜyeEkleme();
                    break;
                case 2:
                    GemelÜyeEkleme();
                    break;
                case 3:
                    MailGöndermeEkranı();
                    break;
                case 9:
                    System.out.println("Ekrandan çıkıyorsunuz.");
                    kontrol=1;
                    break;
                default:
                    System.out.println("Yanlış sayı girdiniz.");
            }
        }while (kontrol==0);
    }  
    
    
    
    
    public static void ElitÜyeEkleme() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Elit üye ekleme ekranına hoş geldiniz.");
        System.out.println("Kaç kişi eklemek istiyorsun.");
        int i=scanner.nextInt();//kaç kişi girileceği yazılıyor
        for (int j = 0; j < i; j++) {
            String[] isimarr =new String[i];
            String[] soyisimarr=new String[i];
            String[] emailarr=new String[i];
            System.out.println("İsim"); 
            String isim=scanner.next();
            System.out.println("Soyisim");
            String Soyisim=scanner.next();
            System.out.println("Email");
            String Email=scanner.next();
            ElitÜyeler elitÜyeler=new ElitÜyeler(isim, Soyisim, Email);
            isimarr[j]=elitÜyeler.getIsim();//ElitÜyeler classına atıyor
            soyisimarr[j]=elitÜyeler.getSoyisim();//ElitÜyeler classına atıyor
            emailarr[j]=elitÜyeler.getEmail();//ElitÜyeler classındaki dosya yazma fonksiyonu
            elitÜyeler.DosyaYaz();
         }
    }
    
    
    
    
    public static void GemelÜyeEkleme() throws IOException {//!!Yapılan her şey ElitÜyeEKleme fonksiyonuyla aynı!!
        Scanner scanner=new Scanner(System.in);
        System.out.println("Elit üye ekleme ekranına hoş geldiniz.");
        System.out.println("Kaç kişi eklemek istiyorsun.");
        int i=scanner.nextInt();
        for (int j = 0; j < i; j++) {
            String[] isimarr =new String[i];
            String[] soyisimarr=new String[i];
            String[] emailarr=new String[i];
            System.out.println("İsim"); 
            String isim=scanner.next();
            System.out.println("Soyisim");
            String Soyisim=scanner.next();
            System.out.println("Email");
            String Email=scanner.next();
            GenelÜyeler genelÜyeler=new GenelÜyeler(isim, Soyisim, Email);
            isimarr[j]=genelÜyeler.getIsim();
            soyisimarr[j]=genelÜyeler.getSoyisim();
            emailarr[j]=genelÜyeler.getEmail();
            genelÜyeler.DosyaYaz();
        }
    }
    
    
    
    
    public static void MailGöndermeEkranı() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1-  Elit üyelere mail");
        System.out.println("2-  Genel üyelere mail");
        System.out.println("3-  Tüm üyelere mail");
        int seçim=scanner.nextInt();
                switch (seçim) {
                    case 1:
                        ElitÜyelereMailGönder();
                        break;
                    case 2:
                        GenelÜyelereMailGönder();
                        break;
                     case 3:
                        
                        break;
                    default:
                        System.out.println("Yanlış sayı girdiniz.");
                }

    }
    
    
    
    public static void ElitÜyelereMailGönder() throws FileNotFoundException, IOException {
        BufferedReader br=new BufferedReader(new FileReader("Üyeler.txt"));
        int i=0;
        String [] email=new String[50];//dizi oluşturuyor
        while (br.ready()) {
        String okunanSatır=br.readLine();
        if (okunanSatır.contains("Elit Üye")){//satırda "Elit Üye kelimesi arıyor
        String[] parçala=okunanSatır.split("  ");//satırı "     " yerleinden bölüyor
        for (String parçalanmış : parçala) {
            if (parçalanmış.contains("@")) {//parçalanan satırı kelime kelime kontrol ediyor 
                email[i]=parçalanmış;//diziye ekler
                }
            }
        }
        i++;
        }
        for (String email1 : email) {
            if (email1==null) {
                continue;
            }
        // SMTP ayarları
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.starttls.enable", "true");
      
      // Oturum açma bilgileri
      String username = "tahalenger@gmail.com";
      String password = "yvxertwyntemvcbh";
      
      // Oturum açma
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });
      
      try {
         // Mesaj oluşturma
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress());
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email1));
         message.setSubject("Genel üye mail gönderme.");
         message.setText("Genel üyemiz merhaba");
         
         // Mesaj gönderme
         Transport.send(message);
         
         System.out.println("E-posta gönderildi.    "+email1);
         
      } catch (MessagingException e) {
         throw new RuntimeException(e);
            }
        }
    }
    
    
    
    
    public static void GenelÜyelereMailGönder() throws FileNotFoundException, IOException {//ElitÜyelereMailGöndermeyle aynı şekilde çalışıyor
        BufferedReader br=new BufferedReader(new FileReader("Üyeler.txt"));
        int i=0;
        String [] email=new String[50];
        while (br.ready()) {
        String okunanSatır=br.readLine();
        if (okunanSatır.contains("Genel Üye")){//bu kısımda "Genel Üye" kelimeleri aranıyor
        String[] parçala=okunanSatır.split("  ");
        for (String parçalanmış : parçala) {
            if (parçalanmış.contains("@")) {
                email[i]=parçalanmış;
                }
            }
        }
        i++;
        }
        System.out.println(email[0]);
        for (String email1 : email) {
            if (email1==null) {
                continue;
            }
        // SMTP ayarları
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.starttls.enable", "true");
      
      // Oturum açma bilgileri
      String username = "tahalenger@gmail.com";
      String password = "yvxertwyntemvcbh";
      
      // Oturum açma
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });
      
      try {
         // Mesaj oluşturma
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress());
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email1));
         message.setSubject("Genel üye mail gönderme.");
         message.setText("Genel üyemiz merhaba");
         
         // Mesaj gönderme
         Transport.send(message);
         
         System.out.println("E-posta gönderildi.    "+email1);
         
      } catch (MessagingException e) {
         throw new RuntimeException(e);
            }
        }       
    }
    
    
    
    public static void TümKullanıcılarMailGönder() throws IOException {//Burada if kullanmadan .txt de bulunan herkese mail atar
        BufferedReader br=new BufferedReader(new FileReader("Üyeler.txt"));
        int i=0;
        String [] email=new String[50];
        while (br.ready()) {
        String okunanSatır=br.readLine();
        String[] parçala=okunanSatır.split("  ");
        for (String parçalanmış : parçala) {
            if (parçalanmış.contains("@")) {
                email[i]=parçalanmış;
                }
            }
        }
        i++;
        for (String email1 : email) {
            if (email1==null) {
                continue;
            }
        // SMTP ayarları
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.starttls.enable", "true");
      
      // Oturum açma bilgileri
      String username = "tahalenger@gmail.com";
      String password = "yvxertwyntemvcbh";
      
      // Oturum açma
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });
      
      try {
         // Mesaj oluşturma
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress());
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email1));
         message.setSubject("Genel üye mail gönderme.");
         message.setText("Genel üyemiz merhaba");
         
         // Mesaj gönderme
         Transport.send(message);
         
         System.out.println("E-posta gönderildi.    "+email1);
         
      } catch (MessagingException e) {
         throw new RuntimeException(e);
            }
        }       
    }
}

