/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Inventory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Stocks;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class Inventory_MainController implements Initializable {

    @FXML
    private TextField text_search;
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
    private TableView tbl_stocks;
    ObservableList<Stocks> stock;
    ObservableList<Stocks> stock_carcass;
    @FXML
    private TableColumn<Stocks, String> col_unit;
    @FXML
    private TableView table_stockcarcass;
    @FXML
    private TableColumn<Stocks, String>  col_cref;
    @FXML
    private TableColumn<Stocks, String>  col_productcarcass;
    @FXML
    private TableColumn<Stocks, String>  col_unitcarcass;
    @FXML
    private TableColumn<Stocks, String>  col_meatcarcass;
    @FXML
    private TableColumn<Stocks, String>  col_qntycarcass;
    @FXML
    private TextField text_carcass_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        initStockView();        
        initCarcassView();
        filter();
        
    }    
    public void initStockView(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        col_cref.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        col_product.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("product_type"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("product_category"));
        col_qnty.setCellValueFactory(new PropertyValueFactory<>("product_qnty"));
        col_unit.setCellValueFactory(new PropertyValueFactory<>("product_unit"));
        stock = Database.getStocksRecords("select *from stocks WHERE category is not NULL","stocks");
        tbl_stocks.setItems(stock);
    }
    public void initCarcassView(){
        
        col_cref.setCellValueFactory(new PropertyValueFactory<>("cinventory_id"));
        col_productcarcass.setCellValueFactory(new PropertyValueFactory<>("cproduct_name"));
        col_unitcarcass.setCellValueFactory(new PropertyValueFactory<>("cproduct_unit"));
        col_meatcarcass.setCellValueFactory(new PropertyValueFactory<>("cproduct_meat"));
        col_qntycarcass.setCellValueFactory(new PropertyValueFactory<>("cproduct_qnty"));
        stock_carcass = Database.getStocksRecords("select *from carcass_stock", "carcass");        
        table_stockcarcass.setItems(stock_carcass);
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
        text_carcass_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_carcass_search.textProperty().get().isEmpty()){
                    table_stockcarcass.setItems(stock_carcass);
                    return;                
                }
                ObservableList<Stocks> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Stocks,?>> cols = table_stockcarcass.getColumns();
                for(int r = 0; r < stock_carcass.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(stock_carcass.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_carcass_search.textProperty().get().toLowerCase())){
                            tableItems.add(stock_carcass.get(r));
                            break;
                        }
                    }
                    
                }
                table_stockcarcass.setItems(tableItems);
            }
        
        
        });
    }
        
}
