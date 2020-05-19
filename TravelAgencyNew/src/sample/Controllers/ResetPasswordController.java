package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Database.PersonQueries;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {


    @FXML
    private TextField codeTxtFiled;

    @FXML
    private TextField emailTxtFiled;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Button con;

    @FXML
    private TextField ssnTextField;

    @FXML
    private Label resetLabel;

    @FXML
    private Label newPassLabel;


    public void clear(){
        emailTxtFiled.clear();
        ssnTextField.clear();
        codeTxtFiled.clear();
        passwordTxtField.clear();
    }

    public void setInvisible(){
        codeTxtFiled.setVisible(false);
        passwordTxtField.setVisible(false);
        con.setVisible(false);
        resetLabel.setVisible(false);
        newPassLabel.setVisible(false);
    }

    public void setVisible(){
        codeTxtFiled.setVisible(true);
        passwordTxtField.setVisible(true);
        con.setVisible(true);
        resetLabel.setVisible(true);
        newPassLabel.setVisible(true);
    }

    private Random random = new Random();
    private int randomNumber = random.nextInt(999999);

    @FXML   public void send() {


        String host = "smtp.gmail.com";
        String username = "TravelAgencyNew@gmail.com";
        String password = "Agency100";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTxtFiled.getText()));

            message.setSubject("Reset code");
            message.setText("The reset code is " + randomNumber);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();


            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Reset code sent");
            a.show();
            setVisible();
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setHeaderText("Could not send code, try again..");
            b.show();
        }

    }
        @FXML public void updatePassword(){
             int reset = Integer.parseInt(codeTxtFiled.getText());

        if (reset == randomNumber){
            PersonQueries pq = new PersonQueries();
            pq.sendPassword(passwordTxtField.getText(),ssnTextField.getText());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Password updated");
            a.show();
            clear();

        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Reset number is wrong!");
            a.show();
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setInvisible();

        Tooltip emailTool = new Tooltip();
        emailTool.setText("Enter your email where you will get the reset code");
        emailTxtFiled.setTooltip(emailTool);

        Tooltip codeTool = new Tooltip();
        codeTool.setText("Enter the reset code you have in your email!");
        codeTxtFiled.setTooltip(codeTool);

        Tooltip passTool = new Tooltip();
        passTool.setText("Enter your new password here!");
        passwordTxtField.setTooltip(passTool);


    }
}