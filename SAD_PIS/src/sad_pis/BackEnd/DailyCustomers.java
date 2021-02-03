package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContentDisplay;

/**
 *
 * @author SJ
 */
public class DailyCustomers {
        
    private String customer_id;
    private String customer_name;
    private String total_products;  //incrementation
    private String total_amount;    //incrementation
    private JFXButton btn_view;
    
    public ObservableList<CustomerCartDetails> ccd = FXCollections.observableArrayList(); //cart details 
    

    public DailyCustomers(String customer_id, String customer_name, String total_products, String total_amount) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.total_products = total_products;
        this.total_amount = total_amount;        
        this.setBtn_view(new JFXButton());        
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getTotal_products() {
        return total_products;
    }

    public void setTotal_products(String total_products) {
        int final_val = Integer.parseInt(total_products) + Integer.parseInt(this.total_products);
        this.total_products = String.valueOf(final_val);
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        double final_val = Double.parseDouble(total_amount) + Double.parseDouble(this.total_amount);
        this.total_amount = String.valueOf(final_val);
    }

    public JFXButton getBtn_view() {
        return btn_view;
    }

    private void setBtn_view(JFXButton btn_view) {
        MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.MAGNIFY);
        icon.setSize("20");        
        btn_view.setGraphic(icon);
        btn_view.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btn_view.setOnMouseClicked(e->{                                    
            try {
                Dialog.setDialog(ObjectController.mdc.mainContainer, "src/sad_pis/Cashier/cart/Cart.fxml", JFXDialog.DialogTransition.TOP);
            } catch (IOException ex) {
            //    Logger.getLogger(DailyCustomers.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.btn_view = btn_view;
    }
    
    
    
}
