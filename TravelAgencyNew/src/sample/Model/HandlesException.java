package sample.Model;


import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HandlesException {
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
    public void inputLimit ( final TextField txtFld, final int maxSize){
        txtFld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (txtFld.getText().length() > maxSize) {
                    String  str = txtFld.getText().substring(0, maxSize);
                    txtFld.setText(str);

                }
            }
        });
    }

   public boolean sizePassword(String password){
        boolean passwordError = false;
        if (password.length() < 8 ){
            passwordError=true;
        }
        return  passwordError;
    }

    public void emptyTextFields(TextField ssnTxtField,Button bookingButton){
        bookingButton.disableProperty().bind(Bindings.createBooleanBinding(()->ssnTxtField.getText().trim().isEmpty(),ssnTxtField.textProperty()
        ));
    }
    public void emptyTextFields1(TextField flightIdTxtField,TextField departureDateTxtField,TextField returnDateTxtField,Button updateButton){
        updateButton.disableProperty().bind(Bindings.createBooleanBinding(()->flightIdTxtField.getText().trim().isEmpty(),flightIdTxtField.textProperty()
        ).or(Bindings.createBooleanBinding(()->departureDateTxtField.getText().trim().isEmpty(),departureDateTxtField.textProperty())
        ).or(Bindings.createBooleanBinding(()->returnDateTxtField.getText().trim().isEmpty(),returnDateTxtField.textProperty())));
    }
    public void emptyTxtFields1(TextField TfEmail,TextField TfPassword,Button logInButton){
        logInButton.disableProperty().bind(Bindings.createBooleanBinding(()->TfEmail.getText().trim().isEmpty(),TfEmail.textProperty()
        ).or(Bindings.createBooleanBinding(()->TfPassword.getText().trim().isEmpty(),TfPassword.textProperty())));
    }



    public void emptyTxtFields(TextField textFieldSSN, TextField textFieldFirstName, TextField  textFieldLastName,
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

