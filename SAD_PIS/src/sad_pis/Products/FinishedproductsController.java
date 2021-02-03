
package sad_pis.Products;

import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.textfield.TextFields;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Products;



/**
 * FXML Controller class
 *
 * @author SJ
 */
public class FinishedproductsController implements Initializable {
    
    @FXML
    private Label product_no;
    @FXML
    private TextField text_name;    
    @FXML
    private ComboBox<?> cbo_qnty;
    @FXML
    private TextField text_price;
    @FXML
    private JFXButton btn_addproduct;
    @FXML
    private StackPane mainContainer;
    @FXML
    private TableView productTableView;
    @FXML
    private TableColumn<Products, String> productName;
    @FXML
    private TableColumn<Products, String> productCategory;
    @FXML
    private TableColumn<Products, String> productQuantity;
    @FXML
    private TableColumn<Products, String> markup;    
    @FXML
    private TableColumn<Products, HBox> update;
//    @FXML
//    private TableColumn<Products, JFXButton> status;        
    @FXML
    private TextField text_search;
    @FXML
    private TableColumn<Products, String> sellprice;
    @FXML
    private TextField text_category;
    @FXML
    private Label label_categorycode;
    ObjectController ob = new ObjectController();
    ObservableList<Products> prodlist;
    
    
    
    
    
    public void showAddCategory(ActionEvent e) throws IOException{
        Dialog.setDialog(mainContainer, "src/sad_pis/FormDialogs/addCategoryDialog.fxml",JFXDialog.DialogTransition.TOP);
    }
    @FXML
    public void addProduct(ActionEvent e) throws ClassNotFoundException{
        if(text_name.getText().equals("")||text_category.getText().equals("")||text_price.getText().equals("")){
            AlertMessages.AlertMessagess.alertWarning(null, "Please Fill up missing fields!", "Product Add Warning!");
        }else{
            String duplicate = Database.getRecord("select *from tbl_product where productName = '"+text_name.getText()+"'","product_id");
        
            if(duplicate.isEmpty()){
                if(label_categorycode.getText().equals("n/a")){
                    String cat_query = "insert into tbl_productcategory values (null,'"+text_category.getText()+"')";
                    Database.processSQL(cat_query, text_category.getText(), "Added");
                    label_categorycode.setText(Database.getRecord("select *from tbl_productcategory where title = '"+text_category.getText()+"'", "productcategory_id"));                
                }
                String query = "insert into tbl_product values ("+product_no.getText()+",'"+ text_name.getText() 
                            +"',1,"+ ObjectController.getComboBoxObjectID(cbo_qnty)+","+ text_price.getText() 
                            +","+label_categorycode.getText()+",1)";
                Database.processSQL(query,text_name.getText(),"Added");
                String inv_query = "insert into tbl_inventorylist values (null,"+product_no.getText()+",0)";
                Database.processSQL(inv_query, text_name.getText(), "Added");
                ob.loadMainPanels("Products/finishedproducts.fxml");
                initTableView();               
                  
            }else{
                AlertMessagess.alertError("", "Product already exist!", "Cannot add product!");
            }        
        }
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){        
            label_categorycode.setVisible(false);
            //loads this controller into the class..
            ObjectController.fpc = this;                                    
            Database.loadComboBox("select *from tbl_quantitytype", cbo_qnty, "title");      
            initTextFieldAutoComplete();            
            initInputValid();
            

            
            //setting the product number...
        try {            
            
            product_no.textProperty().set(String.valueOf(Products.incrementProductNo()));
            //call the method initTableView for displaying the data inside the table...
            initTableView();
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(FinishedproductsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(FinishedproductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            //set combobox display item            
            cbo_qnty.getSelectionModel().selectFirst();
            filter();
    }  
  
   
    //init table column and view...
    public void initTableView() throws ClassNotFoundException{               
        //productName.setCellValueFactory(cellData -> cellData.getValue().getProductNameProperty());
        //productCategory.setCellValueFactory(cellData -> cellData.getValue().getProductCategoryProperty());
        //productQuantity.setCellValueFactory(cellData -> cellData.getValue().getQntyTypeProperty());
        //price.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());                        
        
        productName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        productCategory.setCellValueFactory(new PropertyValueFactory<>("product_category"));
        productQuantity.setCellValueFactory(new PropertyValueFactory<>("qnty_type"));
        markup.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        sellprice.setCellValueFactory(new PropertyValueFactory<>("price"));        
        update.setCellValueFactory(new PropertyValueFactory<>("action_col"));            
//        status.setCellValueFactory(new PropertyValueFactory<>("remove"));            
        editableColumns();
 
        prodlist = Database.getProductRecords(Products.showProductType("grocery", "desc"),"grocery");    //desc pra pinakataas ang pinakabago gibutang..                
        populateTable(prodlist);
        
    }
    //editable columns
    private void editableColumns(){
        productName.setCellFactory(TextFieldTableCell.forTableColumn());
        
        productName.setOnEditCommit(e->{            
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_name(e.getNewValue());
            
        });
        productCategory.setCellFactory(ComboBoxTableCell.forTableColumn(Database.getItems("select *from tbl_productcategory")));
        
        productCategory.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_category(e.getNewValue());
        
        });        
        productQuantity.setCellFactory(ComboBoxTableCell.forTableColumn(Database.getItems("select *from tbl_quantitytype")));
        
        productQuantity.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setQnty_type(e.getNewValue());
        
        });
        markup.setCellFactory(TextFieldTableCell.forTableColumn());
        
        markup.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProduct_price(e.getNewValue());
        
        });
        
        productTableView.setEditable(true);
        
        
    }
    
    //populate the tableview
    private void populateTable(ObservableList<Products> prodist){        
        productTableView.setItems(prodist);
    }    
    private void filter(){
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    populateTable(prodlist);                    
                    return;                
                }
                ObservableList<Products> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Products,?>> cols = productTableView.getColumns();
                for(int r = 0; r < prodlist.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(prodlist.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_search.textProperty().get().toLowerCase())){
                            tableItems.add(prodlist.get(r));
                            break;
                        }
                    }
                    
                }
                productTableView.setItems(tableItems);
                
            }
        
        
        });
    }
    
    private void initTextFieldAutoComplete(){
        String []data = Database.getArrayObjects("select *from tbl_productcategory", "Title","tbl_productcategory");                        
        TextFields.bindAutoCompletion(text_category, data);        
    }
    private void initInputValid(){
        
        text_category.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(oldValue){
                    String []data = Database.getArrayObjects("select *from tbl_productcategory", "Title","tbl_productcategory");                        
                    //for validation if wala ang product sa list kay mu color red dapat

                    boolean available = false;
                    for(int i = 0 ; i < data.length; i++){
                        if(text_category.getText().equals(data[i])){
                            available = true;
                        }
                    }
                    if(available){ //not available...
                        text_category.setStyle("-fx-text-fill: black;" );
                        label_categorycode.setText(Database.getRecord("select *from tbl_productcategory where title = '"+text_category.getText()+"'", "productcategory_id"));
                    }else{              
                        text_category.setStyle("-fx-text-fill: red;" );           
                        label_categorycode.setText("n/a");
                        
                    }
                }
            }        
        });
    }

    @FXML
    private void initInputValidation(KeyEvent event) {
         String []data = Database.getArrayObjects("select *from tbl_productcategory", "Title","tbl_productcategory");
        //for validation if wala ang product sa list kay mu color red dapat
        
        boolean available = false;
        for(int i = 0 ; i < data.length; i++){
            if(text_category.getText().equals(data[i])){
                available = true;
            }
        }
        if(available){ //not available...
            text_category.setStyle("-fx-text-fill: black;" );         
        }else{              
            text_category.setStyle("-fx-text-fill: red;" );           
        }
    }
    
}
