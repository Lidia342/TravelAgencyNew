package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Database.PersonQueries;
import sample.Model.Encryption;
import sample.Model.HandlesException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {

    private HandlesException handlesException;


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
    @FXML
    private Button sendButton;


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

    @FXML public void send() {


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
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);

            if(!emailTxtFiled.getText().matches("[A-Za-z0-9/.]+([/@])[A-Za-z0-9]+[/.][A-Za-z/.]+")){
                a.setTitle("Email");
                a.setHeaderText("The email must be of this format: \"example@gmail.com\"!");
                a.showAndWait();
            }
            else {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTxtFiled.getText()));

                message.setSubject("Reset code");
                message.setText("The reset code is " + randomNumber);

                Transport transport = session.getTransport("smtp");
                transport.connect(host, username, password);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();


                a.setHeaderText("Reset code sent");
                a.show();
                setVisible();
            } 
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setHeaderText("Enter correct SSN and your email Address");
            b.show();
        }

    }
        @FXML public void updatePassword() throws GeneralSecurityException, UnsupportedEncodingException {
        int reset = Integer.parseInt(codeTxtFiled.getText());

        if (reset == randomNumber){
            PersonQueries pq = new PersonQueries();
            pq.sendPassword(Encryption.encrypt(passwordTxtField.getText()),ssnTextField.getText());
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

        handlesException = new HandlesException();
        handlesException.onlyNumber(ssnTextField);
        handlesException.emptyTxtFields1(emailTxtFiled,ssnTextField,sendButton);
        handlesException.emptyTxtFields1(codeTxtFiled,passwordTxtField,con);
        handlesException.onlyNumber(codeTxtFiled);
        handlesException.inputLimit(passwordTxtField,16);

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