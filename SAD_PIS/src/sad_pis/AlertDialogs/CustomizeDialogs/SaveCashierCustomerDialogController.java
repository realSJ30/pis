/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.AlertDialogs.CustomizeDialogs;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sad_pis.BackEnd.AlertControllers;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class SaveCashierCustomerDialogController implements Initializable {

    @FXML
    public JFXButton btn_Save;
    @FXML
    public JFXButton btn_dontsave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // TODO
        AlertControllers.save_cashier = this;
        
    }    

   
    
    
    
    
}
