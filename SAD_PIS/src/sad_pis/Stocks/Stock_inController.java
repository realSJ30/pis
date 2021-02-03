/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Stocks;

import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.textfield.TextFields;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.StockIN;
import sad_pis.BackEnd.StockIN_Products;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class Stock_inController implements Initializable {

    @FXML
    private StackPane rootPane;
    
    
    //stocks...
    @FXML
    private TableView table_stocks;
    @FXML
    private TableColumn<StockIN, String> col_stockno;
    @FXML
    private TableColumn<StockIN, String> col_totalproducts;    
    @FXML
    private TableColumn<StockIN, String> col_date;
    @FXML
    private TableColumn<StockIN, String> col_user;    
    @FXML
    private TableColumn<StockIN_Products, HBox> col_action;
    
    
    //stock details
    @FXML
    private TableView table_stockdetails;
    @FXML
    private TableColumn<StockIN_Products, String> col_productname;
    @FXML
    private TableColumn<StockIN_Products, String> col_quantity;
    @FXML
    private TableColumn<StockIN_Products, String> col_purchaseprice;
    @FXML
    private TableColumn<StockIN_Products, String> col_totalprice;
    @FXML
    private TableColumn<StockIN_Products, String> col_supplier;
    @FXML
    private TableColumn<StockIN_Products, HBox> col_update;    
//    @FXML
//    private TableColumn<StockIN_Products, JFXButton> col_action2;
    //fields
    @FXML
    private ComboBox<?> cbo_supplier;
    @FXML
    public Label label_no;
    @FXML
    public Label label_productCount;
    @FXML
    public Label label_totalPrice;
    
    @FXML
    private CheckBox check_show;
    String table_query = "";    //for stock table..    
    ObservableList<StockIN> stockin;    //observable for table stock
    ObservableList<StockIN_Products> si_products;   //observable for table stock details 
    
    @FXML
    private TextField text_search;
    @FXML
    private TextField text_productname;
    @FXML
    private TextField text_qnty;
    @FXML
    private TextField text_price;
    @FXML
    private JFXButton btn_addproduct;
    @FXML
    private JFXButton btn_updateAll;
    @FXML
    private JFXButton btn_removeAll;
    
    
    
    
    
    @FXML
    private void addProductStock(ActionEvent e){
        
        si_products = Database.getStockInProductsRecords("select *from si_details where reference_id = "+label_no.getText());        
        if(text_qnty.getText().equals("") || text_price.getText().equals("")){
          AlertMessagess.alertWarning("Fill up missing fields.", "INPUT ERROR!", "Warning!");
        }else{
            Database.processSQL("update tbl_stockin set status_id = 1 where stockin_id = "+label_no.getText(), null, null);
            if(si_products.isEmpty()){                
                String prod_id = Database.getValueID(text_productname.getText(), "tbl_product", "productName", "product_id");                                
                //String inventory_id = Database.getValueID(text_productname.getText(), "stocks", "Product", "ID");                
                                
                String supplier_id=Database.getRecord("select *from tbl_supplier where supplierName = '"+cbo_supplier.getSelectionModel().getSelectedItem()+"'","supplier_ID");
                String insert_siDETAILS = "insert into tbl_stockindetails values (null,"+label_no.getText()+","+prod_id+","
                                        +text_qnty.getText()+","+text_price.getText()+","+supplier_id+",1)";                                                        
                Database.processSQL(insert_siDETAILS, text_productname.getText(), "Added");
                initStockTable();
                initDetailsTable();                        
                text_productname.setText("");
                text_qnty.setText("");
                text_price.setText("");                                
                label_productCount.setText(stockin.get(Integer.parseInt(label_no.getText())-1).getTotal_products());
                setLabel(label_totalPrice,"N/A");
            }else{
                for(int r = 0 ; r < si_products.size(); r++){                
                    
                    if(text_productname.getText().equals(si_products.get(r).getProduct_name())){
                        AlertMessagess.alertWarning("", "Product already exist!", "Warning!");
                        break;                        
                    }else if(r == si_products.size()-1 && !text_productname.getText().equals(si_products.get(r).getProduct_name())){
                        String prod_id = Database.getValueID(text_productname.getText(), "tbl_product", "productName", "product_id");
                        //String inventory_id = Database.getValueID(text_productname.getText(), "stocks", "Product", "ID");
                       
                        String supplier_id=Database.getRecord("select *from tbl_supplier where supplierName = '"+cbo_supplier.getSelectionModel().getSelectedItem()+"'","supplier_ID");
                        String insert_siDETAILS = "insert into tbl_stockindetails values (null,"+label_no.getText()+","+prod_id+","
                                        +text_qnty.getText()+","+text_price.getText()+","+supplier_id+",1)";                                                                 
                        Database.processSQL(insert_siDETAILS, text_productname.getText(), "Added");
                        initStockTable();
                        initDetailsTable();                        
                        text_productname.setText("");
                        text_qnty.setText("");
                        text_price.setText("");
                        label_productCount.setText(stockin.get(Integer.parseInt(label_no.getText())-1).getTotal_products());
                        setLabel(label_totalPrice,"N/A");
                        break;
                    }                    
                }
            }
        ObjectController.checkStockStatus();   
        }        
    }
    
    @FXML
    private void showAll(ActionEvent e){
        if(check_show.isSelected()){
            table_query = "select *from Stock_in_Details";
            initStockTable();   
        }else{
            table_query = "select *from Stock_in_Details where status_id = 1";
            initStockTable();   
        }
    }
    
    @FXML
    private void showAddStock(ActionEvent e) throws IOException{
        Dialog.setDialog(rootPane, "src/sad_pis/FormDialogs/AddNewStockinDialog.fxml",JFXDialog.DialogTransition.TOP);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_addproduct.setDisable(true);    //default dapat false
        //initialize table...        
        table_query = "select *from Stock_in_Details where status_id = 1";        
        editableColumns(); 
        initStockTable();   
        ObjectController.stock_in = this;
        Database.loadComboBox("select *from tbl_supplier where status_id = 1", cbo_supplier, "supplierName");        
        cbo_supplier.getSelectionModel().selectFirst();
        filter();
        initTextFieldAutoComplete();
        inputValid();
    }   
    
    public void initStockTable(){
        col_stockno.setCellValueFactory(new PropertyValueFactory<>("stockin_id"));
        col_totalproducts.setCellValueFactory(new PropertyValueFactory<>("total_products"));        
        col_user.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
//        col_viewstock.setCellValueFactory(new PropertyValueFactory<>("view"));               
        col_action.setCellValueFactory(new PropertyValueFactory<>("action_col"));               
        stockin = Database.getStockInRecords(table_query);
        populateTableView(stockin,table_stocks);
        
    }
    public void initDetailsTable(){
        col_productname.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("product_qnty"));
        col_purchaseprice.setCellValueFactory(new PropertyValueFactory<>("purchase_price"));
        col_totalprice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        col_supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        col_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
//        col_action2.setCellValueFactory(new PropertyValueFactory<>("action"));
        si_products = Database.getStockInProductsRecords("select *from si_details where reference_id = "+label_no.getText());
        populateTableView(si_products,table_stockdetails);
    }
    
    private void editableColumns(){
        col_date.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_date.setOnEditCommit(e->{            
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate(e.getNewValue());            
        });        
        col_purchaseprice.setCellFactory(TextFieldTableCell.forTableColumn());
        col_purchaseprice.setOnEditCommit(e->{             
            if(Database.getRecord("select *from tbl_stockindetails where details_id = "+si_products.get(table_stockdetails.getSelectionModel().getFocusedIndex()).getDetails_id(), "status_id").equals("1")){
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPurchase_price(e.getNewValue());                        
                table_stockdetails.refresh();                        
                //Major update sa tbl_stockinDetails....
                String details_query = "update tbl_stockindetails set quantity = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getProduct_qnty()
                                        +",purchaseprice = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getPurchase_price()
                                        +" where details_id = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getDetails_id();                                        
                Database.processSQL(details_query,"Stock in Details","Updated");
                ObjectController.initStockTable();
                label_productCount.setText(stockin.get(Integer.parseInt(label_no.getText())-1).getTotal_products());
                setLabel(label_totalPrice,"N/A");                
            }
            initDetailsTable();
        });    
        col_quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_quantity.setOnEditCommit(e->{  
            if(Database.getRecord("select *from tbl_stockindetails where details_id = "+si_products.get(table_stockdetails.getSelectionModel().getFocusedIndex()).getDetails_id(), "status_id").equals("1")){
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_qnty(e.getNewValue());            
                table_stockdetails.refresh();
                //Major update sa tbl_stockinDetails....
                    String details_query = "update tbl_stockindetails set quantity = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getProduct_qnty()
                                            +",purchaseprice = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getPurchase_price()
                                            +" where details_id = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getDetails_id();                                        
                    Database.processSQL(details_query,"Stock in Details","Updated");
                    ObjectController.initStockTable();
                    label_productCount.setText(stockin.get(Integer.parseInt(label_no.getText())-1).getTotal_products());
                    setLabel(label_totalPrice,"N/A");
            }    
            initDetailsTable();
        });                         
        table_stockdetails.setEditable(true);        
        table_stocks.setEditable(true);
        
    }

    private void populateTableView(ObservableList<?> name,TableView table){
        table.setItems(name);
    }
    private void filter(){
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    populateTableView(stockin,table_stocks);                    
                    return;                
                }
                ObservableList<StockIN> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<StockIN,?>> cols = table_stocks.getColumns();
                for(int r = 0; r < stockin.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(stockin.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_search.textProperty().get().toLowerCase())){
                            tableItems.add(stockin.get(r));
                            break;
                        }
                    }
                    
                }
                populateTableView(tableItems,table_stocks);
                
            }
        
        
        });
    }

    private void initTextFieldAutoComplete(){
        String []data = Database.getGroceryArrayObjects("select *from groceryproductlist where status = 'actived'", "Name");                        
        TextFields.bindAutoCompletion(text_productname, data);
        
        
        
    }    

    @FXML
    private void inputValidation(KeyEvent event) {        
        String []data = Database.getGroceryArrayObjects("select *from groceryproductlist where status = 'actived'", "Name");                        
        //for validation if wala ang product sa list kay mu color red dapat
        
        boolean available = false;
        for(int i = 0 ; i < data.length; i++){
            if(text_productname.getText().equals(data[i])){
                available = true;
            }
        }
        if(available){ //not available...
            text_productname.setStyle("-fx-text-fill: black;" );         
        }else{              
            text_productname.setStyle("-fx-text-fill: red;" );           
        }
                
    }
    private void inputValid(){
        text_productname.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(oldValue){
                    String []data = Database.getGroceryArrayObjects("select *from groceryproductlist where status = 'actived'", "Name");                        
                    //for validation if wala ang product sa list kay mu color red dapat

                    boolean available = false;
                    for(int i = 0 ; i < data.length; i++){
                        if(text_productname.getText().equals(data[i])){
                            available = true;
                        }
                    }
                    if(available){ //not available...
                        text_productname.setStyle("-fx-text-fill: black;" );
                        btn_addproduct.setDisable(false);
                    }else{              
                        text_productname.setStyle("-fx-text-fill: red;" );           
                        btn_addproduct.setDisable(true);
                    }
                }
            }
        
        
        });
    }
    public void setLabel(Label lbl,String null_value){
        String totalamt = Database.getRecord("select sum(quantity * purchaseprice)"
                                                    + " as Total from tbl_stockindetails"
                                                    + " where stockin_id = "+label_no.getText()+"", "Total");
                if(totalamt == null){
                    lbl.setText(null_value);
                }else{
                    lbl.setText(totalamt);
                }    
    }

    @FXML
    private void updateAllMethod(ActionEvent event) {
        if(!label_no.getText().equals("N/A")){                            
            for(int i = 0 ; i < si_products.size(); i++){
                if(Database.getRecord("select *from tbl_stockindetails where details_id = "+si_products.get(i).getDetails_id(),"status_id").equals("1")){
                //meaning wla pa naupdate
                String inventory_id = Database.getRecord("select *from stocks where product = '"+ si_products.get(i).getProduct_name() +"'","ID");
                String query = "update tbl_inventorylist set quantity = quantity + "+si_products.get(i).getProduct_qnty()
                              +" where inventory_id = "+inventory_id;
                Database.processSQL(query, "Stock", "Updated");    
                String status_query = "update tbl_stockindetails set status_id = 2 where details_id = "+si_products.get(i).getDetails_id();
                Database.processSQL(status_query,"Stock in list","UPDATED!");                
                }
            }
            ObjectController.checkStockStatus();
            initStockTable();
            initDetailsTable();
        }
        
    }

    @FXML
    private void removeAllMethod(ActionEvent event) {
        if(!label_no.getText().equals("N/A")){
            for(int i = 0 ; i < si_products.size(); i++){
            if(Database.getRecord("select *from tbl_stockindetails where details_id = "+si_products.get(i).getDetails_id(),"status_id").equals("1")){
                //meaning wla pa na update
                String status_query = "delete from tbl_stockindetails where details_id = "+si_products.get(i).getDetails_id();
                Database.processSQL(status_query,si_products.get(i).getProduct_name(),"Deleted!");                
                
            }else{
                //if naupdate na
                String inventory_id = Database.getRecord("select *from stocks where product = '"+ si_products.get(i).getProduct_name() +"'","ID");
                
                if(Double.parseDouble(Database.getRecord("select *from tbl_inventorylist where inventory_id = "+inventory_id, "quantity")) < Double.parseDouble(si_products.get(i).getProduct_qnty())){
                    //if mas gamay na sya sa current na quantity sa stock item kay i automatic 0 ang value pra dli conflict.
                    String query = "update tbl_inventorylist set quantity = 0 where inventory_id = "+inventory_id;
                    Database.processSQL(query, "Stock", "Updated");    
                }else{
                    String query = "update tbl_inventorylist set quantity = quantity - "+si_products.get(i).getProduct_qnty()
                              +" where inventory_id = "+inventory_id;
                    Database.processSQL(query, "Stock", "Updated");                        
                }                
                String status_query = "delete from tbl_stockindetails where details_id = "+si_products.get(i).getDetails_id();
                Database.processSQL(status_query,si_products.get(i).getProduct_name(),"Deleted!");                                                                
            }
            }
            ObjectController.checkStockStatus();
            initStockTable();
            initDetailsTable();                        
            label_productCount.setText("N/A");
            label_totalPrice.setText("N/A");
        }        
    }

//    @FXML
//    private void addCarcassStock(ActionEvent event) {
//        String live_query = "insert into tbl_livestockin values (null,"+label_no.getText()+","+
//                        Database.getRecord("select *from tbl_supplier where supplierName = '"+cbo_carcasssupplier.getSelectionModel().getSelectedItem()
//                        +"'", "supplier_id")
//                        +","+ObjectController.getComboBoxObjectID(cbo_animal)+","+text_carcassprice.getText()+",1)";
//        Database.processSQL(live_query, "Particular Stock", "Created");
//        initStockTable
//    }

    
}
