package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.PackageQueries;
import sample.Model.packageTable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewPackageController implements Initializable {

    @FXML
    private RadioButton rb1;

    @FXML
    private ToggleGroup RadioGroup;

    @FXML
    private RadioButton rb5;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb6;

    @FXML
    private RadioButton rb9;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb7;

    @FXML
    private RadioButton rb10;

    @FXML
    private RadioButton rb4;

    @FXML
    private RadioButton rb8;
    @FXML
    private TableView<Object> packageTable1;

    @FXML
    private TableColumn<packageTable, String> packageName;

    @FXML
    private TableColumn<packageTable, String> depDate;

    @FXML
    private TableColumn<packageTable, String> depCity;

    @FXML
    private TableColumn<packageTable, String> arrivalCity;

    @FXML
    private TableColumn<packageTable, String> flightName;

    @FXML
    private TableColumn<packageTable, String> hotelName;

    @FXML
    private TableColumn<packageTable, String> nights;

    @FXML
    private TableColumn<packageTable, String> roomType;

    @FXML
    private TableColumn<packageTable, String> carType;

    @FXML
    private TableColumn<packageTable, String> Price;

    @FXML private TextField ssnTxtField;
    @FXML private TextField bookDateTxtField;
    @FXML private Button bookingButton;
    PackageQueries pq = new PackageQueries();


    public void setDisable(){
        ssnTxtField.setDisable(true);
        bookDateTxtField.setDisable(true);
        bookingButton.setDisable(true);

    }
    public void setAble(){
        ssnTxtField.setDisable(false);
        bookDateTxtField.setDisable(false);
        bookingButton.setDisable(false);

    }




    @FXML public void radioSelect1(ActionEvent ae) throws SQLException {


        packageName.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        depDate.setCellValueFactory(new PropertyValueFactory<>("flightDepartureDate"));
        depCity.setCellValueFactory(new PropertyValueFactory<>("departing_city"));
        arrivalCity.setCellValueFactory(new PropertyValueFactory<>("arriving_city"));
        flightName.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        hotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        nights.setCellValueFactory(new PropertyValueFactory<>("numOfNights"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("typeOfRoom"));
        carType.setCellValueFactory(new PropertyValueFactory<>("carType"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        if (rb1.isSelected()) {

            pq.getPackageInfo("Honey Moon");
            packageTable1.setItems(pq.getObList());
        }
        if (rb2.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Family Trip");
            packageTable1.setItems(pq.getObList());
        }
        if (rb3.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Barcelona Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb4.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Shabi Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb5.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Berlin Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb6.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Amsterdam Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb7.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Sahara Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb8.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Massawa Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb9.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Azmarino Trip");
            packageTable1.setItems(pq.getObList());

        }
        if (rb10.isSelected()) {
            packageTable1.getItems().clear();
            pq.getPackageInfo("Campya Trip");
            packageTable1.setItems(pq.getObList());

        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      /*setDisable();

      while (true) {
          packageTable1.getSelectionModel().selectedItemProperty().getValue();
      }*/


       /* if (packageTable1.getSelectionModel().isSelected(1))
            ssnTxtField.setEditable(true);
*/
        }

    }

