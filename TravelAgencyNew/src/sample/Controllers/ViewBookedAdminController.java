package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Database.BookingQueries;
import sample.Model.BookingTable;
import sample.Model.SceneSwitcher;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ViewBookedAdminController implements Initializable {

    BookingQueries bookingQueries = new BookingQueries();

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<BookingTable, String > bookingIdColumn;

    @FXML
    private TableColumn<BookingTable, String> ssnColumn;

    @FXML
    private TableColumn<BookingTable, String> firstNameColumn;

    @FXML
    private TableColumn<BookingTable, String> lastNameColumn;

    @FXML
    private TableColumn<BookingTable, String> packageNameColumn;

    @FXML
    private TableColumn<BookingTable, String> dateColumn;

    @FXML
    private TableColumn<BookingTable, String> priceColumn;

    @FXML
    private ComboBox<String> searchComboBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
        ObservableList<String> packageName = FXCollections.observableArrayList();
        packageName.addAll("Amsterdam Trip","Rome Trip","Barcelona Trip","Los Angeles Trip","Rio Trip",
                "Maimi Trip","Launda Trip", "Kampala Trip","Khartoum Trip","Massawa Trip");

        searchComboBox.setItems(packageName);


        table.getSelectionModel().selectFirst();

        Tooltip deleteRow= new Tooltip();
        deleteRow.setText("right Click and Press remove to delete the selected row");
        table.setTooltip(deleteRow);


    }
    public void tableView (){
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        packageNameColumn.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void show(){
        tableView();
        table.getItems().clear();
        bookingQueries.displayBooked();
        table.setItems(bookingQueries.getViewList());
    }

   @FXML public void display(){

        tableView();
        table.getItems().clear();
        bookingQueries.viewBooked(searchComboBox.getValue());
        table.setItems(bookingQueries.getObList());
    }

    public void clicked(ActionEvent event) {

        try{
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Booked");
            alert.setHeaderText("Delete this booking:");
            alert.setContentText("Are you Sure? Press ok to confirm, or cancel to back out.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)){
                BookingTable bTable = (BookingTable) table.getSelectionModel().getSelectedItem();
                bookingQueries.removeBookingHasPackage(bTable.getBookingId());
                bookingQueries.removeBooking(bTable.getBookingId());
                table.getItems().clear();
                display();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML public void handleKeyPressed (KeyEvent keyEvent) {
        String selectedItem = String.valueOf(table.getSelectionModel().getSelectedItem());
        if (selectedItem != null){
            if (keyEvent.getCode().equals(KeyCode.DELETE)){
                Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Booked");
                alert.setHeaderText("Delete this booking:");
                alert.setContentText("Are you Sure? Press ok to confirm, or cancel to back out.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && (result.get() == ButtonType.OK)){
                    BookingTable bTable = (BookingTable) table.getSelectionModel().getSelectedItem();
                    bookingQueries.removeBookingHasPackage(bTable.getBookingId());
                    bookingQueries.removeBooking(bTable.getBookingId());

                    table.getItems().clear();
                    display();
                }

            }
        }
    }

    public void back(ActionEvent event) {
        SceneSwitcher.SwitchScene(event, "../View/AdminMenu.fxml");
    }

    public void cancel(ActionEvent event) {
        System.exit(0);
    }
}
