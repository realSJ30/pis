
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
public class AddNewStockinDialogController implements Initializable {

    @FXML
    private JFXCheckBox check_current;
    @FXML
    private DatePicker date_text;
    //date variable..
    private String date;
    ObjectController ob = new ObjectController();
    
    
    @FXML
    private void doneButton(ActionEvent e){
        if(check_current.isSelected()){            
            date = getDate();   
        }else{
            date = date_text.getValue().toString();
        } 
        String query = "insert into tbl_stockin values (null,1,'"+ date +"',1)";
        Database.processSQL(query,"Stock No.","Created!");
        ob.loadMainPanels("Stocks/stock_in.fxml");
        
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
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
