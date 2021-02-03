

package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.HBox;


/**
 *
 * @author SJ
 */
public class Supplier {
    
    //data properties...
    private int supplier_ID;
    private String supplier_Name;
    private String supplier_Contact;
    private String supplier_Email;
    private String supplier_Address;
    private JFXButton update;
    private JFXButton status;
    private HBox action_col;
    

    public HBox getAction_col() {
        return action_col;
    }

    public void setAction_col(HBox action_col,JFXButton btn1,JFXButton btn2) {
        action_col.setSpacing(5);
        action_col.getChildren().add(btn1);        
        action_col.getChildren().add(btn2);
        this.action_col = action_col;
    }

    public JFXButton getUpdate() {
        return update;
    }

    public void setUpdate(JFXButton update) {
        update.setPrefWidth(130);
        update.setStyle("-fx-background-color: #38424A;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        update.setOnAction(e->{
            String query = "update tbl_supplier set supplierName = '"+ this.supplier_Name +"',contact = '"+this.supplier_Contact
                            +"',Email = '"+ this.supplier_Email+"',address = '"+ this.supplier_Address+"' where supplier_ID = "+this.supplier_ID;
            Database.processSQL(query,this.supplier_Name,"Updated!");
        });
        this.update = update;
    }

    public JFXButton getStatus() {
        return status;
    }

    public void setStatus(JFXButton status) {
        status.setPrefWidth(120);
        if(status.getText().equalsIgnoreCase("Actived")){
            status.setStyle("-fx-background-color: #2FCC55;"
                                + "-fx-text-fill: white;" 
                                + "-fx-border-radius: 4;");
            status.setOnAction(e->{                                
                String query = "update tbl_supplier set status_id = 2 where supplier_id = "+this.supplier_ID;            
                Database.processSQL(query,"Status","Updated"); 
                ObjectController.initSupplierTableView();
            });                        
        }else{
            status.setStyle("-fx-background-color: #95232F;"
                            + "-fx-text-fill: white;" 
                            + "-fx-border-radius: 4;");
            status.setOnAction(e->{
                String query = "update tbl_supplier set status_id = 1 where supplier_id = "+this.supplier_ID;
                Database.processSQL(query,"Status","Updated"); 
                ObjectController.initSupplierTableView();
            });
            
        }
        this.status = status;
    }
    

    public String getSupplier_ID() {
        return "S-00"+supplier_ID;
    }

    public void setSupplier_ID(int supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_Name() {
        return supplier_Name;
    }

    public void setSupplier_Name(String supplier_Name) {
        this.supplier_Name = supplier_Name;
    }

    public String getSupplier_Contact() {
        return supplier_Contact;
    }

    public void setSupplier_Contact(String supplier_Contact) {
        this.supplier_Contact = supplier_Contact;
    }

    public String getSupplier_Email() {
        return supplier_Email;
    }

    public void setSupplier_Email(String supplier_Email) {
        this.supplier_Email = supplier_Email;
    }

    public String getSupplier_Address() {
        return supplier_Address;
    }

    public void setSupplier_Address(String supplier_Address) {
        this.supplier_Address = supplier_Address;
    }
    
    
    
    
    
    
    
    
    
}
