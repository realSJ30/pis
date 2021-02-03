
package sad_pis.BackEnd;

import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

public class StockIN {

    
    //stockin data properties...
    private String stockin_id;
    private String employee_name;
    private String total_products;
    private String total_quantity;
    private String date;    
    private JFXButton view;
    private JFXButton status;
    private HBox action_col;

    public HBox getAction_col() {
        return action_col;
    }

    public void setAction_col(HBox action_col,JFXButton btn1, JFXButton btn2) {
        action_col.setSpacing(5);
        action_col.getChildren().addAll(btn1,btn2);
        this.action_col = action_col;
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
                        if(!this.total_products.equals("N/A")){
                            AlertMessagess.alertWarning("", "Cannot Cancel Stock!\nMake sure to remove all products first!", date);
                        }else{
                            String query = "update tbl_stockin set status_id = 3 where stockin_id = "+this.stockin_id;
                            Database.processSQL(query,"","STOCK UPDATED!");
                            ObjectController.initStockTable();                            
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
                        String query = "delete from tbl_stockindetails where stockin_id = "+this.stockin_id;
                        Database.processSQL(query,"","STOCK DELETED SUCCESSFULLY!");
                        String squery = "delete from tbl_stockin where stockin_id = "+this.stockin_id;
                        Database.processSQL(squery,"","STOCK DELETED SUCCESSFULLY!");                        
                        ObjectController.initStockTable();    
                    }
//                    String query = "update tbl_stockin set status_id = 1 where stockin_id = "+this.stockin_id;
//                    Database.processSQL(query,"","STOCK UPDATED!");
//                    ObjectController.initStockTable();
                }); break;
        }        
        
        this.status = status;
    }
    
    
    
    public String getTotal_products() {
        return total_products;
    }

    public void setTotal_products(String total_products) {
        this.total_products = total_products;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getStockin_id() {
        return "SI-001P"+stockin_id;
    }

    public void setStockin_id(String stockin_id) {
        this.stockin_id = stockin_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public JFXButton getView() {
        return view;
    }

    public void setView(JFXButton view) {
        view.setPrefWidth(150);
        view.setStyle("-fx-background-color: #187CD2;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        view.setOnAction(e->{
            ObjectController.stock_in.label_no.setText(this.stockin_id);            
            ObjectController.stock_in.label_productCount.setText(this.total_products);
            String totalamt = Database.getRecord("select sum(quantity * purchaseprice)"
                                                + " as Total from tbl_stockindetails"
                                                + " where stockin_id = "+this.stockin_id, "Total");
            if(totalamt == null){                
                ObjectController.stock_in.label_totalPrice.setText("N/A");               
            }else{
                ObjectController.stock_in.label_totalPrice.setText(totalamt);               
            }            
            ObjectController.initDetailsTable();
        });
        this.view = view;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
    
    
    
    
       
}
