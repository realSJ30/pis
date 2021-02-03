/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Stocks;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Stocks;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class Stock_outController implements Initializable {

    @FXML
    private TextField text_search;
    @FXML
    private TableView tbl_stocks;
    @FXML
    private TableColumn<Stocks, String> col_id;
    @FXML
    private TableColumn<Stocks, String> col_product;
    @FXML
    private TableColumn<Stocks, String> col_type;
    @FXML
    private TableColumn<Stocks, String> col_category;
    @FXML
    private TableColumn<Stocks, String> col_qnty;
    @FXML
    private TableColumn<Stocks, JFXButton> col_edit;
    @FXML
    public Label label_no;
    @FXML
    public Label label_product;
    @FXML
    public Label label_type;
    @FXML
    public Label label_category;
    @FXML
    private TextField text_remarks;
    @FXML
    public Label label_qnty;
    @FXML
    private ComboBox cbo_adjust;
    @FXML
    private JFXButton btn_update;
    ObservableList<Stocks>stock;
    @FXML
    private TextField text_qnty;
    public double current_val = 0;

    
    @FXML
    private void addProductChange(ActionEvent e) throws IOException{
        String adjust_query = "insert into tbl_adjuststock values (null,1,'"+getDate()+"',"+ObjectController.getComboBoxObjectID(cbo_adjust)
                        +","+Database.getValueID(label_product.getText(), "tbl_product", "productName", "product_id")+","+text_qnty.getText()+",'"+text_remarks.getText()+"')";
        Database.processSQL(adjust_query, "Product", "Created");
        String inventory_query = "update tbl_inventorylist set quantity = "+label_qnty.getText()+" where inventory_id = "+label_no.getText();
        Database.processSQL(inventory_query, "Stock", "Updated");
        ObjectController.mdc.loadMainPanels("Stocks/stock_out.fxml");
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObjectController.stock_out = this;
        Database.loadComboBox("select *from tbl_adjusttype", cbo_adjust, "Title");
        cbo_adjust.getSelectionModel().selectFirst();
        initStockView();
        filter();
        inputValid();
        numbersOnly();
        
    }    
    public void initStockView(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        col_product.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("product_type"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("product_category"));
        col_qnty.setCellValueFactory(new PropertyValueFactory<>("product_qnty"));
        col_edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        stock = Database.getStocksRecords("select *from stocks","stocks");
        tbl_stocks.setItems(stock);
    }
    public void filter(){
        
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    tbl_stocks.setItems(stock);
                    return;                
                }
                ObservableList<Stocks> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Stocks,?>> cols = tbl_stocks.getColumns();
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
                tbl_stocks.setItems(tableItems);
                
            }
        
        
        });
    }

    private void inputValid(){
        text_qnty.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {                
                if(oldValue){
                    if(label_qnty.getText().equals("N/A")){
                        text_qnty.setText("");
                    }else if(text_qnty.getText().equals("")){
                           label_qnty.setText(String.valueOf(current_val));                        
                    }    
                    else{
                        int adjusttype = ObjectController.getComboBoxObjectID(cbo_adjust);
                        if(adjusttype == 1){
                            //add
                            double final_value = current_val+Double.parseDouble(text_qnty.getText());
                            label_qnty.setText(String.valueOf(final_value));

                        }else{
                            //deduct
                            double final_value = current_val-Double.parseDouble(text_qnty.getText());
                            label_qnty.setText(String.valueOf(final_value));
                        }            
                    } 
                    
                }
            }
        
        
        });        
    }
    private void numbersOnly(){
        text_qnty.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    text_qnty.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        
        });
    }
    
    //method for getting the current date...
    private String getDate(){
        String value = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date();  
        value = formatter.format(date);  
        return value;
    }

    @FXML
    private void adjustQntybyComboBox(ActionEvent event) {
        int adjusttype = ObjectController.getComboBoxObjectID(cbo_adjust);
                        if(adjusttype == 1){
                            //add
                            double final_value = current_val+Double.parseDouble(text_qnty.getText());
                            label_qnty.setText(String.valueOf(final_value));

                        }else{
                            //deduct
                            double final_value = current_val-Double.parseDouble(text_qnty.getText());
                            label_qnty.setText(String.valueOf(final_value));
                        }   
    }
    
}
