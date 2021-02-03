
package sad_pis.Products;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Products;


public class CarcassproductsController implements Initializable {

    @FXML
    private Label product_no;
    @FXML
    private TextField text_name;
    @FXML
    private TextField text_price;
    @FXML
    private JFXButton btn_addproduct;
    @FXML
    private ComboBox<?> cbo_meat;
    
    ObjectController ob = new ObjectController();
    @FXML
    private TableColumn<Products, String> col_beefname;
    @FXML
    private TableColumn<Products, String> col_beefprice;
    @FXML
    private TableColumn<Products, String> col_porkname;
    @FXML
    private TableColumn<Products, String> col_porkprice;
    @FXML
    private TableColumn<Products, String> col_chickname;
    @FXML
    private TableColumn<Products, String> col_chickprice;
    @FXML
    private TableView table_beef;
    @FXML
    private TableView tbl_pork;
    @FXML
    private TableView tbl_chicken;
    @FXML
    private TableColumn<Products, HBox> beef_update;
//    @FXML
//    private TableColumn<Products, JFXButton> beef_status;
    @FXML
    private TableColumn<Products, HBox> pork_update;
//    @FXML
//    private TableColumn<Products, JFXButton> pork_status;
    @FXML
    private TableColumn<Products, HBox> chick_update;
//    @FXML
//    private TableColumn<Products, JFXButton> chick_status;
    @FXML
    private TextField text_search;
    @FXML
    private TableView tbl_all;
    @FXML
    private TableColumn<Products, String> all_name;
    @FXML
    private TableColumn<Products, String> all_price;        
    @FXML
    private TableColumn<Products, HBox> all_update;
//    @FXML
//    private TableColumn<Products, JFXButton> all_status;
    @FXML
    private TableColumn<Products, String> all_meat;
    
    
    ObservableList<Products> all;
    
    
    
    
    
    
    
    
    @FXML
        public void addCarcassProduct(ActionEvent e) throws ClassNotFoundException{
        //insert data to tbl_product
        String query = "insert into tbl_product values (null,'"+ text_name.getText() +"',2,2,"+ text_price.getText() +",null,1)";
        Database.processSQL(query,text_name.getText(),"Added");        
        String inventory_query = "insert into tbl_inventorylist values (null,"+product_no.getText()+",0)";
        Database.processSQL(inventory_query, "Stock", "Added");
        String query2 = "insert into tbl_carcassclassification values ("+ product_no.getText()+","+ ObjectController.getComboBoxObjectID(cbo_meat)+")";
        Database.processSQL(query2,"Product","Updated");
        ob.loadMainPanels("Products/carcassproducts.fxml"); //tentative kay gusto nkp ang fields lg mureset dli jd ang whole fxml..
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            //load combobox and product ID
            Database.loadComboBox("select *from tbl_animal",cbo_meat,"title");
            ObjectController.cpc = this;
            cbo_meat.getSelectionModel().selectFirst();            
        try {   //sets the label for the existing product id
            product_no.textProperty().set(String.valueOf(Products.incrementProductNo()));
            initBeefTableView();
            initPorkTableView();
            initChickenTableView();
            initAllTableView();
        } catch (SQLException ex) {
            //Logger.getLogger(CarcassproductsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(CarcassproductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        filter();
        
    }
    
    //init table view and columns...
    public void initBeefTableView()throws ClassNotFoundException{
        //ObservableList<Products> prodlist = Database.getProductRecords(Products.showProductType("meat", "desc"),"meat");
        ObservableList<Products> beef = Database.getProductRecords(Products.showProductType("meat", "desc","beef"),"meat");
        
        col_beefname.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_beefprice.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        beef_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
        
                
        editableColumns(table_beef,col_beefname,col_beefprice);
        //method for populating the table..
        populateTable(beef,table_beef);
        
    }
    public void initPorkTableView()throws ClassNotFoundException{

        ObservableList<Products> pork = Database.getProductRecords(Products.showProductType("meat", "desc","pork"),"meat");
        
        col_porkname.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_porkprice.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        pork_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
        
        
        editableColumns(tbl_pork,col_porkname,col_porkprice);
        //method for populating the table..
        populateTable(pork,tbl_pork);
        
        
    }
    public void initChickenTableView()throws ClassNotFoundException{
        ObservableList<Products> chicken = Database.getProductRecords(Products.showProductType("meat", "desc","chicken"),"meat");;                                        
        col_chickname.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_chickprice.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        chick_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
        
        
        editableColumns(tbl_chicken,col_chickname,col_chickprice);
        //method for populating the table..
        populateTable(chicken,tbl_chicken);        
        
    }
    public void initAllTableView() throws ClassNotFoundException{
        all = Database.getProductRecords("select *from meatproductlist order by product_id desc", "meat");
        all_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        all_price.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        all_meat.setCellValueFactory(new PropertyValueFactory<>("meat_type"));        
        all_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
        
        
        editableColumns(tbl_all,all_name,all_price);
        populateTable(all,tbl_all);        
        
    }
    
    //populate the tableview
    public void populateTable(ObservableList<Products> prodlist,TableView tbl){
        tbl.setItems(prodlist);
    }
    //editable columns
    private void editableColumns(TableView table,TableColumn<Products,String> name,TableColumn<Products,String> price){
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        
        name.setOnEditCommit(e->{            
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_name(e.getNewValue());
            
        });
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        
        price.setOnEditCommit(e->{            
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_price(e.getNewValue());
            
        });
        
        table.setEditable(true);
                
    }
    private void filter(){
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    tbl_all.setItems(all);
                    return;                
                }
                ObservableList<Products> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Products,?>> cols = tbl_all.getColumns();
                for(int r = 0; r < all.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(all.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_search.textProperty().get().toLowerCase())){
                            tableItems.add(all.get(r));
                            break;
                        }
                    }
                    
                }
                tbl_all.setItems(tableItems);
                
            }
        
        
        });
    }
    
    

        
}
