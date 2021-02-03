
package sad_pis.BackEnd;


import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;


public class Products {
    
    //data properties....
    private int product_id;
    private String product_name;
    private String product_type;
    private String qnty_type;
    private String product_price;
    private String product_category;
    private String meat_type;
    private String price;
    private HBox action_col;
    private JFXButton update;
    private JFXButton status;

    public HBox getAction_col() {
        return action_col;
    }

    public void setAction_col(HBox action_col,JFXButton btn1, JFXButton btn2) {
        action_col.setSpacing(5);
        action_col.getChildren().add(btn1);        
        action_col.getChildren().add(btn2);        
        this.action_col = action_col;
    }
    
   


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {    //setting the selling price...        
        if(price == null){
            this.price = "N/A";
        }else{
            this.price = price;
        }
        
    }

    public JFXButton getStatus() {
        return status;
    }

    public void setStatus(JFXButton status) {
        this.status = status;
    }
    
    
    //default constructors ...
    //--note: giwala nko ang constructors..
    
    //getters and setters..
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getQnty_type() {
        return qnty_type;
    }

    public void setQnty_type(String qnty_type) {
        this.qnty_type = qnty_type;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getMeat_type() {
        return meat_type;
    }

    public void setMeat_type(String meat_type) {
        this.meat_type = meat_type;
    }

    public JFXButton getUpdate() {
        return update;
    }
    

    public void setUpdate(JFXButton button) {
        button.setPrefWidth(90);
        
        button.setStyle("-fx-background-color: #38424A;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        if(this.meat_type == null){
        button.setOnAction(e->{          
            String query = "update tbl_product set productName = '"+ this.getProduct_name() +"',QntyType_id = "
                            + Database.getRecord("select *from tbl_quantitytype where title = '"+this.getQnty_type()+"'", 
                                                "QntyType_ID") + ",price = "+ this.getProduct_price()
                                                + ",productCategory_ID = " 
                            + Database.getRecord("select *from tbl_productcategory where title = '"+this.getProduct_category()+"'", "productCategory_id")
                                                + " where product_id = "+ this.getProduct_id();            
            Database.processSQL(query,this.getProduct_name(),"Updated");            
            try {
                ObjectController.initGroceryTableView();                
                //System.out.println(query);
            } catch (ClassNotFoundException ex) {                
            }
            
        });
        }else{
        button.setOnAction(e->{          
            String query = "update tbl_product set productName = '"+ this.getProduct_name() +"',"
                            + "price = "+ this.getProduct_price()                                                
                            + " where product_id = "+ this.getProduct_id();            
            Database.processSQL(query,this.product_name,"Updated");
            try {
                ObjectController.initMeatTableView();
            } catch (ClassNotFoundException ex) {                
            }
        });
            
        }
        
        this.update = button;
        
    }
    public JFXButton getRemove() {        
        return status;
    }

    public void setRemove(JFXButton button) {
        button.setPrefWidth(80);
        if(button.getText().equalsIgnoreCase("Actived")){        
            //button style...
            button.setStyle("-fx-background-color: #2FCC55;"
                            + "-fx-text-fill: white;" 
                            + "-fx-border-radius: 4;");
            //button action
            button.setOnAction(e->{
                String query = "update tbl_product set status_id = 2 where product_id = "+this.getProduct_id();
                Database.processSQL(query,"Status","Updated");   
                //reloads the tableview...
                try {
                    if(this.meat_type == null){
                        ObjectController.initGroceryTableView();
                        //System.out.println(this.meat_type);
                    }else{
                        //System.out.println(this.meat_type);
                        ObjectController.initMeatTableView();
                    }

                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }else{
            //button style...
            button.setStyle("-fx-background-color: #95232F;"
                            + "-fx-text-fill: white;" 
                            + "-fx-border-radius: 4;");
            //button action
            button.setOnAction(e->{
                String query = "update tbl_product set status_id = 1 where product_id = "+this.getProduct_id();
                Database.processSQL(query,"Status","Updated"); 
                //reloads the tableview...
                 try {
                    if(this.meat_type == null){                                                
                        ObjectController.initGroceryTableView();
                    }else{                        
                        ObjectController.initMeatTableView();
                        
                    }
                } catch (ClassNotFoundException ex) {
                    //Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
                }
            });    
                
        }        
        this.status = button;
        
    }
    
    
   
        
    //method for incrementing product number.. for UI presentation lang..
    public static int incrementProductNo() throws SQLException{
        int no = 0;
        if(Database.getRecord("select product_id from tbl_product order by product_id desc limit 1", "product_id").equals("")){
            no++;
        }else{
            no = Integer.parseInt(Database.getRecord("select product_id from tbl_product order by product_id desc limit 1", "product_id")) + 1;
        }
        return no;
    }
    
    //method for predefined QUERY returns the query...
    public static String showProductType(String type,String order){
        String query = "";
        if(type.equalsIgnoreCase("grocery")){
            query = "select *from GroceryProductList order by product_id "+ order +"";
        }else{  //if meat kay nakajoin
            query = "select *from MeatProductList order by product_id "+ order +"";
        }
        
        return query;
    }
    
    //method for predefined QUERY with meat type and category type, returns the query...
    public static String showProductType(String type,String order,String category_or_type){
        String query = "";
        if(type.equalsIgnoreCase("grocery")){
            query = "select *from GroceryProductList where category = '"+ category_or_type +"' order by product_id "+ order +"";
        }else{  //if meat kay nakajoin
            query = "select *from MeatProductList where meat = '"+ category_or_type +"' order by product_id "+ order +"";
        }        
        return query;
    }
     
    
}
