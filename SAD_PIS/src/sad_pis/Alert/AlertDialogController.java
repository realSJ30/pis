/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Alert;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sad_pis.BackEnd.CustomerCartDetails;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;


/**
 * FXML Controller class
 *
 * @author SJ
 */
public class AlertDialogController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    public Label label_product;
    @FXML
    public Label label_price;
    @FXML
    private TextField text_quantity;
    @FXML
    private Label label_totalprice;
    int prod_index = ObjectController.cmc.prod_index;
    @FXML
    public JFXButton btn_add;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {                
        label_product.setText(ObjectController.cmc.stock.get(prod_index).getProduct_name());
        label_price.setText(ObjectController.cmc.stock.get(prod_index).getProduct_price());
        
    }    
    

    @FXML
    private void initChangeTotalValue(KeyEvent event) {              
        if(text_quantity.getText().equals("")){
           label_totalprice.setText("N/A");
        }else{            
            double final_value =  Double.parseDouble(label_price.getText()) * Double.parseDouble(text_quantity.getText());
            label_totalprice.setText(String.valueOf(new DecimalFormat("##.##").format(final_value)));        
        }                          
    }

    @FXML
    private void addToCart(ActionEvent event) {        
        int customer_index = ObjectController.cmc.index;
        String product_id = ObjectController.cmc.stock.get(prod_index).getProduct_id();
        String stock_quantity = Database.getRecord("select *from tbl_inventorylist where product_id = "+product_id, "quantity");
        if(Double.parseDouble(text_quantity.getText()) > Double.parseDouble(stock_quantity)){
            AlertMessages.AlertMessagess.alertWarning(null, "Product Out of Stock!", null);
        }else{
            String query = "update tbl_inventorylist set quantity = quantity - "+text_quantity.getText()+" where product_id = "+product_id;
            Database.processSQL(query, null, null);
            //specific observable list inside the model
            //mao ni ang observablelist sa order sa isa ka customer...
            ObjectController.cmc.dc.get(customer_index).ccd.add(new CustomerCartDetails(product_id,label_product.getText(),text_quantity.getText(),label_price.getText(),label_totalprice.getText()));                
            ObjectController.cmc.dc.get(customer_index).setTotal_products("1");
            ObjectController.cmc.dc.get(customer_index).setTotal_amount(label_totalprice.getText());
            String total_products = ObjectController.cmc.dc.get(customer_index).getTotal_products();
            String total_amount = ObjectController.cmc.dc.get(customer_index).getTotal_amount();
            ObjectController.cmc.label_totalproducts.setText(total_products);
            ObjectController.cmc.label_totalamount.setText(total_amount);
            ObjectController.cmc.initCustomerTableView();

            Dialog.dialog.close();
        }
        
    }
   

   
}
