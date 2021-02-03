
package sad_pis.BackEnd;


import com.jfoenix.controls.JFXButton;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;




public class StockIN_Products {
    //data properties...
    private String details_id;    
    private String product_name;
    private String product_qnty;
    private String purchase_price;
    private String total_price;
    private String supplier;
    private String unit;
    private HBox action_col;
    private JFXButton update;
    private JFXButton action;
    //private JFXButton remove;

    public HBox getAction_col() {
        return action_col;
    }

    public void setAction_col(HBox action_col,JFXButton btn1,JFXButton btn2) {
        action_col.setSpacing(5);
        action_col.getChildren().addAll(btn1,btn2);        
        this.action_col = action_col;
    }
    

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    

    public JFXButton getAction() {
        return action;
    }

    public void setAction(JFXButton action) {
        action.setPrefWidth(120);
        action.setStyle("-fx-background-color: #95232F;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        action.setOnAction(e->{
            Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
            dg.setTitle("Confirmation!");
            dg.setHeaderText("Warning! Product has been added to the stocks\n"
                            + "Removing such stock could also remove the Quantity that has been updated on your stock.");
            dg.getButtonTypes().set(0, ButtonType.YES);
            dg.getButtonTypes().set(1, ButtonType.NO);
            if(Database.getRecord("select *from tbl_stockindetails where details_id = "+this.details_id, "status_id").equals("2")){
                //meaning na update na
                Optional<ButtonType> result = dg.showAndWait();
                if(result.get() == ButtonType.YES){
                    String inventory_id = Database.getRecord("select *from stocks where product = '"+ this.getProduct_name() +"'","ID");
                    String inv_query = "update tbl_inventorylist set quantity = quantity - "+this.product_qnty+" where inventory_id = "+inventory_id;
                    Database.processSQL(inv_query, "Stock", "Updated");
                    String query = "delete from tbl_stockindetails where details_id = "+this.details_id;
                    Database.processSQL(query, this.product_name, "Deleted");            
//                    ObjectController.initDetailsTable();   
//                    ObjectController.initStockTable();
                    String totalamt = Database.getRecord("select sum(quantity * purchaseprice)"
                                                        + " as Total from tbl_stockindetails"
                                                        + " where stockin_id = "+ObjectController.stock_in.label_no.getText(), "Total");
                    if(totalamt == null){                
                        ObjectController.stock_in.label_totalPrice.setText("N/A");               
                    }else{
                        ObjectController.stock_in.label_totalPrice.setText(totalamt);               
                    }
                    String productCount = String.valueOf(Integer.parseInt(ObjectController.stock_in.label_productCount.getText())-1);
                    if(productCount.equals("0")){
                        ObjectController.stock_in.label_productCount.setText("N/A");
                    }else{
                        ObjectController.stock_in.label_productCount.setText(productCount);
                    }


                }
            }else{
                String query = "delete from tbl_stockindetails where details_id = "+this.details_id;
                Database.processSQL(query, this.product_name, "Deleted");            
//                ObjectController.initDetailsTable();   
//                ObjectController.initStockTable();
                String totalamt = Database.getRecord("select sum(quantity * purchaseprice)"
                                                    + " as Total from tbl_stockindetails"
                                                    + " where stockin_id = "+ObjectController.stock_in.label_no.getText(), "Total");
                if(totalamt == null){                
                    ObjectController.stock_in.label_totalPrice.setText("N/A");               
                }else{
                    ObjectController.stock_in.label_totalPrice.setText(totalamt);               
                }
                String productCount = String.valueOf(Integer.parseInt(ObjectController.stock_in.label_productCount.getText())-1);
                if(productCount.equals("0")){
                    ObjectController.stock_in.label_productCount.setText("N/A");
                }else{
                    ObjectController.stock_in.label_productCount.setText(productCount);
                }
            }
            
            ObjectController.checkStockStatus();
            ObjectController.initStockTable();
            ObjectController.initDetailsTable(); 
        });
        this.action = action;
    }
    
    
    public String getDetails_id() {
        return details_id;
    }

    public void setDetails_id(String details_id) {
        this.details_id = details_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_qnty() {
        return product_qnty;
    }

    public void setProduct_qnty(String product_qnty) {
        this.product_qnty = product_qnty;
        this.setTotal_price();
    }

    public String getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(String purchase_price) {
        this.purchase_price = purchase_price;
        this.setTotal_price();
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price() {
        if(this.purchase_price == null || this.product_qnty == null){
            this.total_price = "0";
        }else{
            this.total_price = String.valueOf(Double.parseDouble(this.purchase_price) * Double.parseDouble(this.product_qnty));
        }
        
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {        
        this.supplier = supplier;
    }

    public JFXButton getUpdate() {
        return update;
    }

    public void setUpdate(JFXButton update) {
        update.setPrefWidth(120);        
        if(update.getText().equals("Add to Stock")){
        update.setStyle("-fx-background-color: #38424A;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        update.setOnAction(e->{
            
            String inventory_id = Database.getRecord("select *from stocks where product = '"+ this.getProduct_name() +"'","ID");
            
            String update_query = "update tbl_inventorylist set quantity = "+this.product_qnty+" where inventory_id = "+ inventory_id;                                                                        
            System.out.println(update_query);
            Database.processSQL(update_query,"Stock Products","UPDATED!");                   
                                   
            String status_query = "update tbl_stockindetails set status_id = 2 where details_id = "+this.details_id;
            Database.processSQL(status_query,"Stock in list","UPDATED!");                
            System.out.println(status_query);
            ObjectController.initDetailsTable();
            String stock_items = Database.getRecord("select *from tbl_stockindetails where stockin_id = "+ObjectController.stock_in.label_no.getText(), "details_ID");
            if(stock_items != null){                
                String stock_item_stat = Database.getRecord("select *from tbl_stockindetails where status_id = 1", "details_id");
                if(stock_item_stat == null){
                    String s_query = "update tbl_stockin set status_id = 2 where stockIN_id = "+ObjectController.stock_in.label_no.getText();
                    Database.processSQL(s_query, "Stock", "Updated");
                    System.out.println(s_query);
                }
            }
            ObjectController.checkStockStatus();
            ObjectController.initStockTable();
            
        });
        }else{
        update.setStyle("-fx-background-color: #1CA261;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");            
        }
        this.update = update;
    }

//    public JFXButton getRemove() {
//        return remove;
//    }
//
//    public void setRemove(JFXButton remove) {
//        remove.setPrefWidth(100);
//        remove.setStyle("-fx-background-color: #95232F;"
//                        + "-fx-text-fill: white;" 
//                        + "-fx-border-radius: 4;");
//        remove.setOnAction(e->{
//            String remove_query = "delete from tbl_stockindetails where details_id = "+this.details_id;
//            Database.processSQL(remove_query, "Product", "Removed"); 
//            
//        });
//        
//        this.remove = remove;
//    }
        
    
}
