
package sad_pis.BackEnd;

import java.io.IOException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import sad_pis.Cashier.CashierMainController;
import sad_pis.Customer.CustomerController;
import sad_pis.MainDashboardController;
import sad_pis.Products.CarcassproductsController;
import sad_pis.Products.FinishedproductsController;
import sad_pis.Stocks.Stock_inCarcassController;
import sad_pis.Stocks.Stock_inController;
import sad_pis.Stocks.Stock_outController;
import sad_pis.Supplier.SupplierController;

public class ObjectController {
    
    
    public static FinishedproductsController fpc;   //controller
    public static CarcassproductsController cpc;
    public static MainDashboardController mdc;  //controller
    public static SupplierController sc;
    public static CustomerController cc;
    public static Stock_inController stock_in;
    public static Stock_outController stock_out;
    public static Stock_inCarcassController si_carcass;
    public static CashierMainController cmc;
    
   
    public static void initGroceryTableView() throws ClassNotFoundException{
        fpc.initTableView();
    }
    public static void initMeatTableView() throws ClassNotFoundException{        
        cpc.initBeefTableView();
        cpc.initChickenTableView();
        cpc.initPorkTableView();
        cpc.initAllTableView();
    }
    public static void initDetailsTable(){
        stock_in.initDetailsTable();
    }
    public static void initStockTable(){
        stock_in.initStockTable();
    }
    
    public static void initSupplierTableView(){
        sc.initTableView();
    }
    public static void initCustomerTableView(){
        cc.initTableView();
    }
    public void loadMainPanels(String path){
        try {
            mdc.loadMainPanels(path);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: OC.loadMainPanels \n"+ex.getMessage());
            //Logger.getLogger(ObjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //returns the index value of the combobox ..increment by 1 kay 0 mg sugod ang index
    public static int getComboBoxObjectID(ComboBox cb){
        return cb.getSelectionModel().getSelectedIndex() + 1;
    }
    public static void setLabel(Label lbl,String null_value){
        stock_in.setLabel(lbl, null_value);
    }
    public static void checkStockStatus(){
        if(!Database.getRecord("select count(*) as Result from tbl_stockindetails where stockin_id = "+ObjectController.stock_in.label_no.getText(), "Result").equals("0")){
            if(!Database.getRecord("select count(*) as Result from tbl_stockindetails where status_id = 1 and stockin_id = "+ObjectController.stock_in.label_no.getText(), "Result").equals("0")){
            String query = "update tbl_stockin set status_id = 1 where stockin_id = "+ObjectController.stock_in.label_no.getText();
            Database.processSQL(query, "Stock Reference", "Retrieve");            
            }else{
            String query = "update tbl_stockin set status_id = 2 where stockin_id = "+ObjectController.stock_in.label_no.getText();
            Database.processSQL(query, "Stock Reference", "Retrieve");            
            }
        }else{
            String query = "update tbl_stockin set status_id = 1 where stockin_id = "+ObjectController.stock_in.label_no.getText();
            Database.processSQL(query, "Stock Reference", "Retrieve");            
        }
        
    }
    
    
}
