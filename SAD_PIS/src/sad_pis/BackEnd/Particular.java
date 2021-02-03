package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author SJ
 */
public class Particular {
    
    public String particular_id;
    private String livestockin_id;
    private String quantity;
    private JFXButton remove;

    public JFXButton getRemove() {
        return remove;
    }

    public void setRemove(JFXButton remove) {
        remove.setPrefWidth(100);
        remove.setStyle("-fx-background-color: #95232F;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        remove.setOnAction(e->{
            String remove_query = "delete from tbl_livestockin_particulars where particular_id = "+this.particular_id;
            Database.processSQL(remove_query, "Product", "Removed");      
            ObjectController.si_carcass.initParticularDetailsTable();
            ObjectController.si_carcass.initParticularTable();
            ObjectController.si_carcass.setLabels();
        });
        this.remove = remove;
    }

    public String getParticular_id() {        
        return "Particular-00"+this.livestockin_id+particular_id;
    }

    public void setParticular_id(String particular_id) {        
        this.particular_id = particular_id;
    }

    public String getLivestockin_id() {
        return livestockin_id;
    }

    public void setLivestockin_id(String livestockin_id) {
        this.livestockin_id = livestockin_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    
    
}
