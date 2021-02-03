/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import sad_pis.BackEnd.Customer;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Supplier;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class CustomerController implements Initializable {

    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerID;
    @FXML
    private TableColumn<Customer, String> customerName;
    @FXML
    private TableColumn<Customer, String> customerContact;
    @FXML
    private TableColumn<Customer, String> customerAddress;
    @FXML
    private StackPane rootPane;
    @FXML
    private TableColumn<Customer, String> gender;
//    @FXML
//    private TableColumn<Customer, JFXButton> col_orders;
    @FXML
    private TableColumn<Customer, JFXButton> col_update;
//    @FXML
//    private TableColumn<Customer, JFXButton> col_status;
    @FXML
    private TextField text_search;
    ObservableList<Customer> customerlist; 
    
    @FXML
    private void showCustomerDialog(ActionEvent e) throws IOException{
        Dialog.setDialog(rootPane, "src/sad_pis/FormDialogs/addCustomerDialog.fxml",DialogTransition.TOP);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize
        ObjectController.cc = this;
        initTableView();
        filter();
        
    }    
    
    //init table column and view...
    public void initTableView(){
        customerID.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        customerContact.setCellValueFactory(new PropertyValueFactory<>("customer_contact"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        gender.setCellValueFactory(new PropertyValueFactory<>("customer_gender"));
//        col_orders.setCellValueFactory(new PropertyValueFactory<>("orders"));
        col_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
//        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        editableColumns();
        
        customerlist = Database.getCustomerRecords("select *from customerDetails order by ID desc");
        customerTable.setItems(customerlist);
    }
    
    private void editableColumns(){
        customerName.setCellFactory(TextFieldTableCell.forTableColumn());        
        customerName.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomer_name(e.getNewValue());
        });
        customerContact.setCellFactory(TextFieldTableCell.forTableColumn());        
        customerContact.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomer_contact(e.getNewValue());
        });
        customerAddress.setCellFactory(TextFieldTableCell.forTableColumn());        
        customerAddress.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomer_address(e.getNewValue());
        });
        gender.setCellFactory(ComboBoxTableCell.forTableColumn(Database.getItems("select *from tbl_gender")));        
        gender.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomer_gender(e.getNewValue());
        });
        
        customerTable.setEditable(true);
        
        
    }
       private void filter(){
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    customerTable.setItems(customerlist);
                    return;                
                }
                ObservableList<Customer> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Customer,?>> cols = customerTable.getColumns();
                for(int r = 0; r < customerlist.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(customerlist.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_search.textProperty().get().toLowerCase())){
                            tableItems.add(customerlist.get(r));
                            break;
                        }
                    }
                    
                }
                customerTable.setItems(tableItems);
                
            }
        
        
        });
    }
    
    
    
}
