
package sad_pis.BackEnd;


import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

/**
 *
 * @author SJ
 */
public class Livestockin {
    
    //'livestock data  properties...
    private String livestockin_id;    
    private String supplier_name;    
    private String employee;
    private String Qnty;
    private String total_kgs;
    private String price;
    private String total_price;
    private String animal_name;
    private HBox col_action;    
    private JFXButton view;
    private JFXButton status;
    private String date;


    public HBox getCol_action() {
        return col_action;
    }

    public void setCol_action(HBox col_action,JFXButton btn1,JFXButton btn2) {
        col_action.setSpacing(5);
        col_action.getChildren().addAll(btn1,btn2);
        this.col_action = col_action;
    }

    public String getSupplier_name() {
        return supplier_name;
    }
    
    public JFXButton getView() {
        return view;
    }

    public void setView(JFXButton view) {
        //view.setGraphic(MaterialIconView.);        
        view.setStyle("-fx-background-color: #187CD2;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        view.setOnAction(e->{                                    
            ObjectController.si_carcass.label_no.setText(this.livestockin_id);
            ObjectController.si_carcass.carcass = Database.getCarcassRecords("select *from meatproductlist where meat = '"+this.animal_name+"'");            
            ObjectController.si_carcass.initParticularDetailsTable();
            ObjectController.si_carcass.initCarcassTable();
            ObjectController.si_carcass.setLabels();
        });
        this.view = view;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
    
    public String getLivestockin_id() {
        return livestockin_id;
    }

    public void setLivestockin_id(String livestockin_id) {
        this.livestockin_id = livestockin_id;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getQnty() {
        return Qnty;
    }

    public void setQnty(String Qnty) {
        this.Qnty = Qnty;
    }

    public String getTotal_kgs() {
        return total_kgs;
    }

    public void setTotal_kgs(String total_kgs) {
        this.total_kgs = total_kgs;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public JFXButton getStatus() {
        return status;
    }

    public void setStatus(JFXButton status) {
        status.setPrefWidth(100);
        
        switch (status.getText()) {
            case "Pending":
                status.setStyle("-fx-background-color: #187CD2;"
                        + "-fx-text-fill: white;"
                        + "-fx-border-radius: 4;");
                status.setOnAction(e->{
                    Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
                    dg.setTitle("Confirmation!");
                    dg.setHeaderText("Warning! Do you want to CANCEL stock?");
                    dg.getButtonTypes().set(0, ButtonType.YES);
                    dg.getButtonTypes().set(1, ButtonType.NO);
                    Optional<ButtonType> result = dg.showAndWait();
                    if(result.get() == ButtonType.YES){
                            if(Database.getRecord("select *from tbl_livestockin_carcass where livestockin_id = "+this.livestockin_id+ " limit 1", "livestockin_id").equals(this.livestockin_id)){
                                AlertMessagess.alertWarning(null, "Warning! Cannot delete stock!", null);
                            }else{
                                    String query = "update tbl_livestockin set status_id = 3 where livestockin_id = "+this.livestockin_id;
                                    Database.processSQL(query,"","STOCK UPDATED!");
                                    String query2 = "delete from tbl_livestockin_particulars where livestockin_id = "+this.livestockin_id;
                                    Database.processSQL(query2,"","PARTICULAR DELETED!");
                                    String query3 = "delete from tbl_livestockin_carcass where livestockin_id = "+this.livestockin_id;
                                    Database.processSQL(query3,"","CARCASS DELETED!");
                                try {
                                    ObjectController.mdc.loadMainPanels("Stocks/Stock_inCarcass.fxml");
                                } catch (IOException ex) {
                                    //Logger.getLogger(Livestockin.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                                        
                    }                    
                }); break;
            case "Done":                
                status.setStyle("-fx-background-color: #2FCC55;"
                        + "-fx-text-fill: white;"
                        + "-fx-border-radius: 4;");                
                break;
            case "Canceled":
                status.setStyle("-fx-background-color: #95232F;"
                        + "-fx-text-fill: white;"
                        + "-fx-border-radius: 4;");                
                status.setOnAction(e->{
                    Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
                    dg.setTitle("Confirmation!");
                    dg.setHeaderText("Warning! Do you want to permanently remove this stock?");
                    dg.getButtonTypes().set(0, ButtonType.YES);
                    dg.getButtonTypes().set(1, ButtonType.NO);
                    Optional<ButtonType> result = dg.showAndWait();
                    if(result.get() == ButtonType.YES){
                        String query = "delete from tbl_livestockin where livestockin_id = "+this.livestockin_id;
                        Database.processSQL(query,"","STOCK DELETED SUCCESSFULLY!");                                        
                        ObjectController.si_carcass.initParticularTable();
                        ObjectController.si_carcass.initCarcassTable();
                    }
//                    String query = "update tbl_stockin set status_id = 1 where stockin_id = "+this.stockin_id;
//                    Database.processSQL(query,"","STOCK UPDATED!");
//                    ObjectController.initStockTable();
                }); break;
        }        
        
        this.status = status;
        this.status = status;
    }

    
    
    
    
    
}
