/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.FormDialogs;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class AddCustomerDialogController implements Initializable {

    @FXML
    private TextField text_name;
    @FXML
    private TextField text_contact;
    @FXML
    private ComboBox<?> cbo_gender;
    @FXML
    private TextArea text_address;
    @FXML
    private JFXButton btn_add;

    @FXML
    private void addCustomer(ActionEvent event) {
        String query = "insert into tbl_customer values"
                + "(null,'"+ text_name.getText() +"','"+ text_contact.getText() +"','"
                + text_address.getText() +"',"+ ObjectController.getComboBoxObjectID(cbo_gender) +",1)";
        
        Database.processSQL(query,text_name.getText(),"Added");
        ObjectController ob = new ObjectController();
        ob.loadMainPanels("Customer/customer.fxml");
        
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load data to combobox
        String cbo_query = "select *from tbl_gender";
        Database.loadComboBox(cbo_query, cbo_gender, "title");
        cbo_gender.getSelectionModel().selectFirst();
    }    

    
}
