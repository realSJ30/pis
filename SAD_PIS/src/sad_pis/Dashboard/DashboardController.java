/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sad_pis.BackEnd.Database;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class DashboardController implements Initializable {

    @FXML
    private Label label_productline;
    @FXML
    private Label label_dailysales;
    @FXML
    private Label label_availablestocks;
    @FXML
    private Label label_wholesalers;
    @FXML
    private Label label_users;
    @FXML
    private Label label_pendingstocks;
    @FXML
    private Label label_availablestocks2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //init all labels...
        String kgs_query = "select sum(qnty)as sum from stocks where unit = 'per kilo'";
        label_availablestocks.setText(Database.getRecord(kgs_query, "sum"));
        String pcs_query = "select sum(qnty)as sum from stocks where unit = 'per pieces'";
        label_availablestocks.setText(Database.getRecord(pcs_query, "sum"));
        String product_query = "select count(product_id)as count from tbl_product";
        label_productline.setText(Database.getRecord(product_query, "count"));
        
        
    }    
    
}
