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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;


/**
 * FXML Controller class
 *
 * @author SJ
 */
public class AddCategoryDialogController implements Initializable {

    @FXML
    private JFXButton btn_AddCategory;
    @FXML
    private TextField txt_categoryName;
    @FXML
    private TextArea txt_description;

    
    @FXML
    public void addCategory(ActionEvent e){
        String query = "insert into tbl_productcategory values"
                +      "(null,'"+ txt_categoryName.getText() +"','"+ txt_description.getText() +"')";
        Database.processSQL(query,txt_categoryName.getText(),"Added");     //insert query for product category
        ObjectController oc = new ObjectController();
        
        oc.loadMainPanels("Products/finishedproducts.fxml"); //returns and reloads the current parent page of the dialog...
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
