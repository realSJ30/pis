
package sad_pis.FormDialogs;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class AddSupplierDialogController implements Initializable {

    @FXML
    private TextField text_name;
    @FXML
    private TextField text_contact;
    @FXML
    private TextField text_email;
    @FXML
    private TextArea text_address;
    @FXML
    private JFXButton btn_add;
    ObjectController ob = new ObjectController();
    
    
    @FXML
    public void addSupplier(ActionEvent e){
        String query = "insert into tbl_supplier values"
                + "(null,'"+ text_name.getText() +"','"+ text_contact.getText() +"','"
                + text_email.getText() +"','"+ text_address.getText() +"',1)";
        Database.processSQL(query,text_name.getText(),"Added!");
        ob.loadMainPanels("Supplier/supplier.fxml");    //returns and reloads the parent page of the dialog...
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
