package sample.Model;


import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Exception {
    public void onlyLetters(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                textField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));}
        }); }
    public void onlyNumber(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    public boolean IsNotPhoneNumber(String phoneNumber){
        boolean phoneNumberChecker = false;
        if (phoneNumber.length() != 13)
            phoneNumberChecker = true;
        return phoneNumberChecker;
    }

   public boolean sizePassword(String password){
        boolean passwordError = false;
        if (password.length() < 8 ){
            passwordError=true;
        }
        return  passwordError;
    }


    public void fieldsAreEmpty(TextField textFieldSSN, TextField textFieldFirstName, TextField  textFieldLastName,
                               TextField textFieldEmail, TextField passwordTextField, TextField textFieldPhoneNum,
                               TextField textFieldAddress, Button buttonCreate){

        buttonCreate.disableProperty().bind(Bindings.createBooleanBinding(()->
                        textFieldSSN.getText().trim().isEmpty(),textFieldSSN.textProperty()
                ).or(  Bindings.createBooleanBinding(()->
                        textFieldFirstName.getText().trim().isEmpty(), textFieldLastName.textProperty()
                )
                )
                        .or(  Bindings.createBooleanBinding(()->
                                        textFieldLastName.getText().trim().isEmpty(), textFieldLastName.textProperty()
                                )
                        )
                        .or(  Bindings.createBooleanBinding(()->
                                        textFieldEmail.getText().trim().isEmpty(), textFieldEmail.textProperty()
                                )
                        )
                        .or(  Bindings.createBooleanBinding(()->
                                        passwordTextField.getText().trim().isEmpty(), passwordTextField.textProperty()
                                )
                        )
                        .or(  Bindings.createBooleanBinding(()->
                                        textFieldPhoneNum.getText().trim().isEmpty(), textFieldPhoneNum.textProperty()
                                )
                        )
                        .or(  Bindings.createBooleanBinding(()->
                                        textFieldAddress.getText().trim().isEmpty(), textFieldAddress.textProperty()
                                )
                        )
        );
    }
}

