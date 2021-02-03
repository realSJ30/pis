/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author SJ
 */
public class Stocks {
    //data properties for grocery or finished products...
    private String inventory_id;
    private String product_name;
    private String product_type;
    private String product_category;
    private String product_qnty;
    private String product_unit;
    //for POS na data properties...
    private String product_price;
    private String product_id;
    
    //data properties for meat or carcass products...
    private String cinventory_id;
    private String cproduct_name;
    private String cproduct_unit;
    private String cproduct_meat;
    private String cproduct_qnty;


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
    
    public String getCinventory_id() {
        return cinventory_id;
    }

    public void setCinventory_id(String cinventory_id) {
        this.cinventory_id = cinventory_id;
    }

    public String getCproduct_name() {
        return cproduct_name;
    }

    public void setCproduct_name(String cproduct_name) {
        this.cproduct_name = cproduct_name;
    }

    public String getCproduct_unit() {
        return cproduct_unit;
    }

    public void setCproduct_unit(String cproduct_unit) {
        this.cproduct_unit = cproduct_unit;
    }

    public String getCproduct_meat() {
        return cproduct_meat;
    }

    public void setCproduct_meat(String cproduct_meat) {
        this.cproduct_meat = cproduct_meat;
    }

    public String getCproduct_qnty() {
        return cproduct_qnty;
    }

    public void setCproduct_qnty(String cproduct_qnty) {
        this.cproduct_qnty = cproduct_qnty;
    }    

    public String getProduct_unit() {
        return product_unit;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }
    private JFXButton edit;

    public JFXButton getEdit() {
        return edit;
    }

    public void setEdit(JFXButton edit) {
        edit.setPrefWidth(100);
        edit.setStyle("-fx-background-color: #38424A;"
                        + "-fx-text-fill: white;" 
                        + "-fx-border-radius: 4;");
        edit.setOnAction(e->{
        ObjectController.stock_out.label_no.setText(this.inventory_id);
        ObjectController.stock_out.label_product.setText(this.product_name);
        ObjectController.stock_out.current_val = Double.parseDouble(this.product_qnty);
        ObjectController.stock_out.label_qnty.setText(this.product_qnty);
        ObjectController.stock_out.label_category.setText(this.product_category);
        ObjectController.stock_out.label_type.setText(this.product_type);
        
        });
        this.edit = edit;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_qnty() {
        return product_qnty;
    }

    public void setProduct_qnty(String product_qnty) {
        this.product_qnty = product_qnty;
    }
    
    
}
