/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Cashier;


import com.gn.GNCarousel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXPopup;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PropertySheet.Item;
import sad_pis.Alert.AlertDialogController;
import sad_pis.BackEnd.DailyCustomers;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Stocks;





/**
 * FXML Controller class
 *
 * @author SJ
 */
public class CashierMainController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane root2;
    @FXML
    public TableView table_cart;
    //table observable list//
    public ObservableList<DailyCustomers> dc = FXCollections.observableArrayList(); //customer cart
    public ObservableList<Stocks> stock; //productlist
    @FXML
    private TableColumn<DailyCustomers, String> col_customer;
    @FXML
    private TableColumn<DailyCustomers, Integer> col_totalprod;
    @FXML
    private TableColumn<DailyCustomers, Double> col_amount;
    @FXML
    private TableColumn<DailyCustomers, JFXButton> col_view;
    @FXML
    private TableView table_productlist;
    @FXML
    private TableColumn<Stocks, String> col_product;
    @FXML
    private TableColumn<Stocks, String> col_unit;
    @FXML
    private TableColumn<Stocks, String> col_type;
    @FXML
    private TableColumn<Stocks, String> col_category;
    @FXML
    private TableColumn<Stocks, String> col_price;
    public int index = -1;  //index sa customer...
    public int prod_index;  //index sa product
    String ref_product; //reference sa product index..
    @FXML
    public Label label_totalproducts;
    @FXML
    public Label label_totalamount;
    @FXML
    private TextField text_cashamount;
    @FXML
    private TextField text_search;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObjectController.cmc = this;
       initProductTableView();       
       callSaveCustomerTableData();
       filter();
       
    }    

    @FXML
    private void addNewCustomer(ActionEvent event) throws IOException {                
        String id = String.valueOf(dc.size()+1);
        dc.add(new DailyCustomers(id,initCustomerName(),"0","0.0"));
        initCustomerTableView();
        
    }
    private String initCustomerName(){
        String customer_name = "Customer "+String.valueOf(dc.size()+1);
        for(int i = 0 ; i < dc.size() ; i++){
            if(dc.get(i).getCustomer_name().equals(customer_name)){
                customer_name += i;
            }
        }
        return customer_name;
    }
            
    
    public void initCustomerTableView(){
        col_customer.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        col_totalprod.setCellValueFactory(new PropertyValueFactory<>("total_products"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
        col_view.setCellValueFactory(new PropertyValueFactory<>("btn_view"));
        table_cart.setItems(dc);    
        editableColumns();
    }
    public void initProductTableView(){
        col_product.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_unit.setCellValueFactory(new PropertyValueFactory<>("product_unit"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("product_type"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("product_category"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        stock = Database.getProductListRecords();
        table_productlist.setItems(stock);
        System.out.println("Hello");
    }
    private void editableColumns(){
        col_customer.setCellFactory(TextFieldTableCell.forTableColumn());
        col_customer.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomer_name(e.getNewValue());        
        });
        table_cart.setEditable(true);
        
    }

    @FXML
    private void showQntyAlert(MouseEvent event) throws IOException { 
        
        if(index == -1){    //meaning wlay nka focus na table row
            
            
        }else{  //focus na sa table row                                    
            initProductIndex();
            if(stock.get(prod_index).getProduct_price().equals("N/A")){
                //wala pay stock...
            }else{
                boolean exist = false;
                for(int i = 0 ; i < dc.get(index).ccd.size(); i ++){
                    if(dc.get(index).ccd.get(i).getProduct_name().equals(ref_product)){
                        exist = true;
                        break;                        
                    }
                }
                if(exist){
                    AlertMessages.AlertMessagess.alertError(null, "PRODUCT ALREADY IN CART\nCheck the cart.", null);
                }else{
                    Dialog.setDialog(ObjectController.mdc.mainContainer, "src/sad_pis/Alert/AlertDialog.fxml",DialogTransition.TOP);                                            
                }
                
                
            }
            
        }
    }

    @FXML
    private void customerNumber(MouseEvent event) {
        index = table_cart.getFocusModel().getFocusedCell().getRow(); //customer index
        label_totalamount.setText(dc.get(index).getTotal_amount());
        label_totalproducts.setText(dc.get(index).getTotal_products());
    }
    
    private void filter(){
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    table_productlist.setItems(stock);                    
                    return;                
                }
                ObservableList<Stocks> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Stocks,?>> cols = table_productlist.getColumns();
                for(int r = 0; r < stock.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(stock.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_search.textProperty().get().toLowerCase())){
                            tableItems.add(stock.get(r));
                            break;
                        }
                    }
                    
                }
                table_productlist.setItems(tableItems);
                
            }
        
        
        });
    }
    public void initProductIndex(){
        TablePosition pos = (TablePosition) table_productlist.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        TableColumn col = (TableColumn) table_productlist.getColumns().get(0);
        ref_product = String.valueOf(col.getCellObservableValue(table_productlist.getItems().get(row)).getValue());
        for(int i = 0 ; i < stock.size(); i++){
            if(ref_product.equals(stock.get(i).getProduct_name())){
                prod_index = i;
                break;
            }
        }
    }

    @FXML
    private void removeAllTransactions(ActionEvent event) {
        for(int i = 0 ; i < dc.size() ; i++){            
            for(int c = 0 ; c < dc.get(i).ccd.size() ; c++){
                String query = "update tbl_inventorylist set quantity = quantity + "+dc.get(i).ccd.get(c).getQnty()+ " where product_id = "+dc.get(i).ccd.get(c).getProduct_id();
                Database.processSQL(query, null, null);
            }            
        }
        this.dc.removeAll(dc);
        label_totalproducts.setText("0");
        label_totalamount.setText("0.0");
    }

    @FXML
    private void cancelSelectedTransaction(ActionEvent event) {
        if(index != -1 && !dc.isEmpty()){            
            for(int i = 0 ; i < dc.get(index).ccd.size() ; i++){
                String query = "update tbl_inventorylist set quantity = quantity + "+dc.get(index).ccd.get(i).getQnty()+ " where product_id = "+dc.get(index).ccd.get(i).getProduct_id();
                Database.processSQL(query, null, null);
            }
            this.dc.remove(index);            
            index = -1;
            label_totalproducts.setText("0");
            label_totalamount.setText("0.0");            
        }        
    }

    @FXML
    private void checkOut(ActionEvent event) {
        
        
    }
    private void callSaveCustomerTableData(){
        if(ObjectController.mdc.dc_saved != null){
            dc = ObjectController.mdc.dc_saved;
            initCustomerTableView();            
        }
    }
    public void deleteallTransaction(){
        for(int i = 0 ; i < dc.size() ; i++){            
            for(int c = 0 ; c < dc.get(i).ccd.size() ; c++){
                String query = "update tbl_inventorylist set quantity = quantity + "+dc.get(i).ccd.get(c).getQnty()+ " where product_id = "+dc.get(i).ccd.get(c).getProduct_id();
                Database.processSQL(query, null, null);
            }            
        }
        this.dc.removeAll(dc);
    }
    
    
    
    
   
    
}
