package sample.Controllers;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        display();

        table.getSelectionModel().selectFirst();

        Tooltip deleteRow= new Tooltip();
        deleteRow.setText("right Click and Press remove to delete the selected row");
        table.setTooltip(deleteRow);

    }
    public void display(){

        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        packageNameColumn.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookingQueries.viewBooked();
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
