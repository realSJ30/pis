
package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.Color;

/**
 *
 * @author SJ
 */
public class CustomerCartDetails {   
    
    private String product_id;
    private String product_name;
    private String qnty;
    private String price;
    private String total_amount;
    private JFXButton remove;

    public JFXButton getRemove() {
        return remove;
    }

    private void setRemove(JFXButton remove) {
        MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.DELETE);
        icon.setSize("20");        
        remove.setGraphic(icon);
        remove.setOnAction(e->{                    
            int customer_index = ObjectController.cmc.index;            
            ObjectController.cmc.dc.get(customer_index).ccd.remove(this);
            ObjectController.cmc.dc.get(customer_index).setTotal_amount("-"+this.total_amount);
            ObjectController.cmc.dc.get(customer_index).setTotal_products("-1");
            ObjectController.cmc.initCustomerTableView();
            String t_product = ObjectController.cmc.dc.get(customer_index).getTotal_products();
            String t_amount = ObjectController.cmc.dc.get(customer_index).getTotal_amount();
            ObjectController.cmc.label_totalproducts.setText(t_product);
            ObjectController.cmc.label_totalamount.setText(t_amount);
            String query = "update tbl_inventorylist set quantity = quantity + "+this.qnty+" where product_id = "+this.product_id;
            Database.processSQL(query, null, null);
        });
        this.remove = remove;
    }
    
    public CustomerCartDetails(String product_id, String product_name, String qnty, String price, String total_amount) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.qnty = qnty;
        this.price = price;
        this.total_amount = total_amount;
        setRemove(new JFXButton());
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQnty() {
        return qnty;
    }

    public void setQnty(String qnty) {
        this.qnty = qnty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_amount() {
        double final_value = Double.parseDouble(this.qnty)*Double.parseDouble(this.price);
        return String.valueOf(final_value);
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
    
    
}
