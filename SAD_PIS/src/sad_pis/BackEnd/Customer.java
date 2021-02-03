
package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.HBox;



public class Customer {

    
    //data properties...
    private int customer_ID;
    private String customer_name;
    private String customer_contact;
    private String customer_address;
    private String customer_gender;
    private JFXButton orders;
    private JFXButton update;
    private JFXButton status;
    private HBox action_col;

    public HBox getAction_col() {
        return action_col;
    }

    public void setAction_col(HBox action_col,JFXButton btn1,JFXButton btn2,JFXButton btn3) {
        action_col.setSpacing(5);
        action_col.getChildren().addAll(btn1,btn2,btn3);
        this.action_col = action_col;
    }
    
    //setters and getters for customer ID...
    public JFXButton getOrders() {
        return orders;
    }

    public void setOrders(JFXButton orders) {
        orders.setPrefWidth(100);
        orders.setStyle("-fx-background-color: #FBAE40;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        this.orders = orders;
    }

    public JFXButton getUpdate() {
        update.setPrefWidth(130);
        update.setStyle("-fx-background-color: #38424A;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        update.setOnAction(e->{
            String query = "update tbl_customer set customerName = '"+ this.customer_name +"',contact = '"+this.customer_contact
                            +"',address = '"+ this.customer_address+"' where customer_ID = "+this.customer_ID;
            Database.processSQL(query,this.customer_name,"Updated");
        });
        return update;
    }

    public void setUpdate(JFXButton update) {
        this.update = update;
    }

    public JFXButton getStatus() {
        return status;
    }

    public void setStatus(JFXButton status) {
        status.setPrefWidth(100);
        if(status.getText().equalsIgnoreCase("Actived")){
            status.setStyle("-fx-background-color: #2FCC55;"
                            + "-fx-text-fill: white;" 
                            + "-fx-border-radius: 4;");
            status.setOnAction(e->{
                String query = "update tbl_customer set status_id = 2 where customer_id = "+this.customer_ID;
                System.out.println(query);
                Database.processSQL(query,"Status","Updated"); 
                ObjectController.initCustomerTableView();
            });
        }else{
            status.setStyle("-fx-background-color: #95232F;"
                            + "-fx-text-fill: white;" 
                            + "-fx-border-radius: 4;");
            status.setOnAction(e->{
                String query = "update tbl_customer set status_id = 1 where customer_id = "+this.customer_ID;
                Database.processSQL(query,"Status","Updated"); 
                ObjectController.initCustomerTableView();                
            });
            
        }
        
        this.status = status;
    }
    
    public String getCustomer_ID() {
        return "C-00"+customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    
    
}
