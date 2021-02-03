/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.FormDialogs;

import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class AddNewCarcassDialogController implements Initializable {

    @FXML
    private JFXCheckBox check_current;
    @FXML
    private DatePicker date_text;
    //date variable..
    private String date;
    ObjectController ob = new ObjectController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCarcass(ActionEvent event) {
         if(check_current.isSelected()){            
            date = getDate();   
        }else{
            date = date_text.getValue().toString();
        } 
        String supplier_id = Database.getRecord("select *from tbl_supplier where supplierName = '"+ObjectController.si_carcass.cbo_carcasssupplier.getSelectionModel().getSelectedItem()+"'", "supplier_id");        
        String query = "insert into tbl_livestockin values (null,"+supplier_id+","+ ObjectController.getComboBoxObjectID(ObjectController.si_carcass.cbo_animal) 
                      +","+ObjectController.si_carcass.text_carcassprice.getText()+",1,'"+date+"',1)";
        Database.processSQL(query,"Stock No.","Created!");
        ob.loadMainPanels("Stocks/Stock_inCarcass.fxml");
    }
    @FXML
    private void check(ActionEvent e){
        if(check_current.isSelected()){
            date_text.setDisable(true);
            date_text.setValue(null);
        }else{            
            date_text.setDisable(false);
        }
    }
    
    //method for getting the current date...
    private String getDate(){
        String value = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date();  
        value = formatter.format(date);  
        return value;
    }
}
