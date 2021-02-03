/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Cashier.cart;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sad_pis.BackEnd.CustomerCartDetails;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class CartController implements Initializable {

    @FXML
    private TableColumn<CustomerCartDetails,String> tc1;
    @FXML
    private TableColumn<CustomerCartDetails,String> tc2;
    @FXML
    private TableColumn<CustomerCartDetails,String> tc3;
    @FXML
    private TableColumn<CustomerCartDetails,String> tc4;
    @FXML
    private TableColumn<CustomerCartDetails,JFXButton> tc5;
    @FXML
    private TableView tb;    
    @FXML
    private Label label_customer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCustomerCartDetails();
        label_customer.setText(ObjectController.cmc.dc.get(ObjectController.cmc.index).getCustomer_name());
    }    
    public void initCustomerCartDetails(){
        tc1.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("qnty"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("price"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("total_amount"));    
        tc5.setCellValueFactory(new PropertyValueFactory<>("remove"));    
        tb.setItems(ObjectController.cmc.dc.get(ObjectController.cmc.index).ccd);
        editableColumns();
    }
    private void editableColumns(){
        tc2.setCellFactory(TextFieldTableCell.forTableColumn());
        tc2.setOnEditCommit(e->{            
            String product_id = e.getTableView().getItems().get(e.getTablePosition().getRow()).getProduct_id();
            String stock_quantity = Database.getRecord("select *from tbl_inventorylist where product_id = "+product_id, "quantity");
            double combined_value = Double.parseDouble(e.getOldValue())+ Double.parseDouble(stock_quantity);
            if(Double.parseDouble(e.getNewValue()) > combined_value){
                AlertMessages.AlertMessagess.alertWarning(null, "Product Out of Stock!", null);
                tb.refresh();
            }else{
                //start of query tbl_inventorylist
                double old = Double.parseDouble(e.getOldValue());
                double _new = Double.parseDouble(e.getNewValue());
                double _final = 0;
                if(old > _new){
                    _final = old - _new;
                    String query = "update tbl_inventorylist set quantity = quantity + "+_final+" where product_id = "+product_id;
                    Database.processSQL(query, null, null);
                }else if (_new > old){
                    _final = _new - old;
                    String query = "update tbl_inventorylist set quantity = quantity - "+_final+" where product_id = "+product_id;
                    Database.processSQL(query, null, null);
                }                                
                //end of query
                String old_totalamt_product = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTotal_amount();
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setQnty(e.getNewValue());
                tb.refresh();
                ObjectController.cmc.initCustomerTableView();
                String new_totalamt_product = e.getTableView().getItems().get(e.getTablePosition().getRow()).getTotal_amount();
                //updates label automatically
                int customer_index = ObjectController.cmc.index;            
                if(Double.parseDouble(old_totalamt_product) > Double.parseDouble(new_totalamt_product)){
                    double final_val = Double.parseDouble(old_totalamt_product) - Double.parseDouble(new_totalamt_product);
                    ObjectController.cmc.dc.get(customer_index).setTotal_amount("-"+final_val);
                }else{
                    double final_val = Double.parseDouble(new_totalamt_product) - Double.parseDouble(old_totalamt_product);
                    ObjectController.cmc.dc.get(customer_index).setTotal_amount(String.valueOf(final_val));
                }                
                String t_product = ObjectController.cmc.dc.get(customer_index).getTotal_products();
                String t_amount = ObjectController.cmc.dc.get(customer_index).getTotal_amount();
                ObjectController.cmc.label_totalproducts.setText(t_product);
                ObjectController.cmc.label_totalamount.setText(t_amount);            
            }            
        });
        tb.setEditable(true);
    }
    
}
