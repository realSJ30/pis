
package sad_pis.BackEnd;

/**
 *
 * @author SJ
 */
public class Carcass {
    
    private String product_id;
    private String product_name;
    private String quantity;
    

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

    public String getQuantity() {
        return quantity;
    }
    
    public void setQuantity(String quantity) {
        String livestockin_id = ObjectController.si_carcass.label_no.getText();
        if(quantity.equals("")){
            this.quantity = "0";            
        }else{
            this.quantity = quantity;
        }        
    }
    
    public double getQuantityD(){
        return Double.parseDouble(quantity);
    }
    
    
}
