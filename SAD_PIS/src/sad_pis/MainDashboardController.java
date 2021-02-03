/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sad_pis.BackEnd.AlertControllers;
import sad_pis.BackEnd.DailyCustomers;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;
import sad_pis.popUp.Popup;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class MainDashboardController implements Initializable {

    @FXML
    private VBox vbvb;
    @FXML
    public StackPane mainContainer;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton drpbtn_inventory;
    @FXML
    private JFXButton drpbtn_grocery;
    @FXML
    private JFXButton drpbtn_meat;
    @FXML
    private JFXButton drpbtn_in;
    @FXML
    private JFXButton drpbtn_out;
    @FXML
    private JFXButton drpbtn_supplier;
    @FXML
    private JFXButton drpbtn_customer;
    @FXML
    private VBox vb_sideContainer;
    @FXML
    private JFXButton btn_dashboard;
    @FXML
    private VBox vb_inventory;
    @FXML
    private JFXButton drpbtn_carc;
    @FXML
    private VBox vb_inventory1;
    @FXML
    private VBox vb_inventory11;
    @FXML
    private VBox views;
    @FXML
    private TitledPane design;
    @FXML
    private TitledPane design1;
    //cashier transactions
    public ObservableList<DailyCustomers> dc_saved;
    public boolean safetoleave = true;
    //navigation unsa nga panel ko gikan
    String navigation = "";
    
    
    
    ///NAVIGATION THROUGH DIFF. FXML PAGES
    @FXML
    private void showInventoryPage(ActionEvent e) throws IOException{        
        if(safetoleave("Inventory/Inventory_Main.fxml")){
            loadMainPanels("Inventory/Inventory_Main.fxml");   
        }        
        navigation = "inventory";
    }
    @FXML
    private void showFinishProductPage(ActionEvent e) throws IOException{        
        if(safetoleave("Products/finishedproducts.fxml")){
            loadMainPanels("Products/finishedproducts.fxml");
        }        
        navigation = "finishedproduct";
    }
    @FXML
    private void showCarcassProductsPage(ActionEvent e) throws IOException{        
        if(safetoleave("Products/carcassproducts.fxml")){
            loadMainPanels("Products/carcassproducts.fxml");
        }        
        navigation = "carcassproduct";
    }
    @FXML
    private void showStockinPage(ActionEvent e) throws IOException{        
        if(safetoleave("Stocks/stock_in.fxml")){
            loadMainPanels("Stocks/stock_in.fxml");
        }        
        navigation = "stockin";
    }
    @FXML
    private void showStockoutPage(ActionEvent e) throws IOException{        
        if(safetoleave("Stocks/stock_out.fxml")){
            loadMainPanels("Stocks/stock_out.fxml");
        }        
        navigation = "stockout";
    }
    @FXML
    private void showSupplierPage(ActionEvent e) throws IOException{        
        if(safetoleave("Supplier/supplier.fxml")){
            loadMainPanels("Supplier/supplier.fxml");
        }
        navigation = "supplier";        
    }
    @FXML
    private void showCustomerPage(ActionEvent e) throws IOException{        
        if(safetoleave("Customer/customer.fxml")){
            loadMainPanels("Customer/customer.fxml");
        }
        navigation = "customer";
        
    }
    @FXML
    public void showCashierPage(ActionEvent e) throws IOException{        
        if(dc_saved != null){            
            ObjectController.cmc.dc = dc_saved;            
        }
        loadMainPanels("Cashier/cashierMain.fxml");
        navigation = "cashier";
    }
    @FXML
    private void showTransactionPopup(ActionEvent e){
        String[] label = {"Daily Transaction","Sales Transaction","Customer Transaction","Supplier Transaction"};
        Popup.initPopup(label, root,2,2,190);
        
        
    }
    @FXML
    private void showReportsPopup(ActionEvent e){
        String[] label = {"Inventory Report","Sales Report","Stock Report"};
        Popup.initPopup(label, root, 190, 3, 170);
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            //loads the default view...
            loadMainPanels("Dashboard/Dashboard.fxml");
            ObjectController.mdc = this;    //sets the objectcontroller mdc class...
            
        } catch (IOException ex) {
           // Logger.getLogger(MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    //method for loading Panels from diff. FXML...
    public void loadMainPanels(String fxmlPath) throws IOException{
                StackPane pane = FXMLLoader.load(getClass().getResource(fxmlPath)); //goods nani 
                AnchorPane.setTopAnchor(pane, 0.0);     //Manual setting of anchor pra responsive pg minimize og maximize            
                AnchorPane.setBottomAnchor(pane, 0.0);  //Manual setting of anchor pra responsive pg minimize og maximize
                AnchorPane.setRightAnchor(pane, 0.0);  //Manual setting of anchor pra responsive pg minimize og maximize
                AnchorPane.setLeftAnchor(pane, 0.0);  //Manual setting of anchor pra responsive pg minimize og maximize
                root.getChildren().setAll(pane);

    }
    

    @FXML
    private void showLivestockPage(ActionEvent event) throws IOException {        
        if(safetoleave("Stocks/Stock_inCarcass.fxml")){
            loadMainPanels("Stocks/Stock_inCarcass.fxml");
        }
        navigation = "livestock";
    }

    @FXML
    private void showDashboardPage(ActionEvent event) throws IOException {        
        if(safetoleave("Dashboard/Dashboard.fxml")){
            loadMainPanels("Dashboard/Dashboard.fxml");
        } 
        navigation = "dashboard";
    }
    private boolean safetoleave(String load) throws IOException{        
        safetoleave = true;  
            if(ObjectController.cmc != null){
                if(!ObjectController.cmc.dc.isEmpty() && navigation.equals("cashier")){
                safetoleave = false;
                Dialog.setDialog(mainContainer, "src/sad_pis/AlertDialogs/CustomizeDialogs/SaveCashierCustomerDialog.fxml", JFXDialog.DialogTransition.TOP);            
                AlertControllers.save_cashier.btn_Save.setOnAction(e->{
                    try {
                        dc_saved = ObjectController.cmc.dc;
                        Dialog.dialog.close();
                        loadMainPanels(load);                        
                    } catch (IOException ex) {
                        //Logger.getLogger(MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                AlertControllers.save_cashier.btn_dontsave.setOnAction(e->{
                    try {
                        ObjectController.cmc.deleteallTransaction();
                        Dialog.dialog.close();
                        loadMainPanels(load);
                    } catch (IOException ex) {
                        //Logger.getLogger(MainDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                }                        
            }            
                
        return safetoleave;
    }
    
   
    
}
