
package sad_pis.BackEnd;


import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;




public class Database {
//Connection objects
    private static Connection conn = null;  
    private static Statement st = null;
    private static ResultSet rs = null;
    
    //private String strQuery = "";
    private String strConn = "jdbc:mysql://localhost:3306/";
    
    //method for establishing connection
    public void DB_Connection(String db_name){  
        try{
			strConn += db_name;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(strConn,"root","");
                        
			
	} catch (Exception e){
			System.out.println("ERROR: DB_Connection\n"+e.getMessage());
	} //end of try-catch
    }
    
    //method for login...
    public static boolean loginBypass(String query) throws SQLException{
        int result = 0;
        st = conn.createStatement();
        rs = st.executeQuery(query);
        while(rs.next()){
            result++;
        }
        if(result != 0){
            return true;
        }else{
            return false;
        }
    }
    //loads data into comboBoxes..
    public static void loadComboBox(String query,ComboBox cbo,String column){
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                cbo.getItems().add(rs.getString(column));
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: loadComboBox", "Error!!!");
        }
    }
    //gets the specific data...
    public static String getValueID(String value_name,String table,String column_name,String column){
        String value = "";
        try {            
            st = conn.createStatement();
            rs = st.executeQuery("select *from "+table+" where "+column_name+"='"+value_name+"'");            
            while(rs.next()){
                value = rs.getString(column);
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getValueID", "Error!!!");
        }
        return value;
    }
    public static String getRecord(String query,String column){
        String record = "";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                record = rs.getString(column);
            }
            
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getRecord", "Error!!!");
        }
        return record;
    }
    
    //process insert,update and delete..
    public static void processSQL(String query, String data, String method){
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            //AlertMessagess.alertInformation(data+" Successfully "+method+"!", "SUCCESS!", "Information");
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "processSQL", "Error!!!");
            
        } 
    }
    //returns an array of data..
    public static String[] getGroceryArrayObjects(String query,String column){
        String []data = new String[Integer.parseInt(Database.getRecord("select count(name) as Total from groceryproductlist where status = 'actived'", "Total"))];
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int count = 0;
            while(rs.next()){                
                data[count] = rs.getString(column);                
                count++;                                
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getArrayObjects", "Error!!!");
        }
        return data;        
    }
    public static String[] getArrayObjects(String query,String column,String table){
        String []data = new String[Integer.parseInt(Database.getRecord("select count(title)as Total from "+table, "Total"))];
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int count = 0;
            while(rs.next()){                
                data[count] = rs.getString(column);                
                count++;                                
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getArrayObjects", "Error!!!");
        }
        return data;        
    }
    
    //returns an observablelist...
    public static ObservableList<String> getItems(String query){
        ObservableList list = FXCollections.observableArrayList();
        try {            
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                list.add(rs.getString("title"));
            }
            
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getItems", "Error!!!");
        }
        return list;        
    }
    
    //                                      PRODUCTS                                        //
    //returns tableview for products
    public static ObservableList<Products> getProductRecords(String query,String productType) throws ClassNotFoundException{
        ObservableList<Products> prodlist = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            prodlist = getProductsObjects(rs,productType);
            
            
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getProductRecords", "Error!!!");
        } 
        
        return prodlist;
    }
    
    //process objects to tableview
    private static ObservableList<Products> getProductsObjects(ResultSet rs,String productType) throws ClassNotFoundException{
        ObservableList<Products> prodlist= FXCollections.observableArrayList();
        try {                
            while(rs.next()){
                if(productType.equalsIgnoreCase("grocery")){
                    Products prod = new Products();
                    prod.setProduct_id(rs.getInt("product_id"));
                    prod.setProduct_name(rs.getString("Name"));
                    prod.setProduct_type(rs.getString("Type"));
                    prod.setQnty_type(rs.getString("Quantity"));
                    prod.setPrice(rs.getString("sellprice"));                    
                    prod.setProduct_price(rs.getString("price"));
                    prod.setProduct_category(rs.getString("Category"));                    
                    prod.setUpdate(new JFXButton("Update"));
                    prod.setRemove(new JFXButton(rs.getString("Status")));
                    prod.setAction_col(new HBox(),prod.getUpdate(),prod.getRemove());
                    //System.out.println(prod.button().getText());
                    
                    prodlist.add(prod); //adds the class properties into the observablelist
                }else if(productType.equalsIgnoreCase("meat")){
                    Products prod = new Products();
                    prod.setProduct_id(rs.getInt("product_id"));
                    prod.setProduct_name(rs.getString("Name"));
                    prod.setMeat_type(rs.getString("meat"));
                    prod.setUpdate(new JFXButton("Update"));
                    prod.setRemove(new JFXButton(rs.getString("Status")));
                    prod.setAction_col(new HBox(),prod.getUpdate(),prod.getRemove());
                    prod.setProduct_price(rs.getString("price"));                                    
                    
                    prodlist.add(prod); //adds the class properties into the observablelist
                }
                
            }
            
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getProductObjects", "Error!!!");
        }
        
        
        return prodlist;
    }
    
    
    //                                      SUPPLIER                                        //
    //returns tableview for supplier
    public static ObservableList<Supplier> getSupplierRecords(String query){
        ObservableList<Supplier> supplist = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            supplist = getSupplierObjects(rs);
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getSupplierRecords", "Error!!!");
        }
        return supplist;
    }
    
    //process objects to tableview...
    private static ObservableList<Supplier> getSupplierObjects(ResultSet rs) {
        ObservableList<Supplier> supplist = FXCollections.observableArrayList();
        try{
            while(rs.next()){
                Supplier supp = new Supplier();
                supp.setSupplier_ID(rs.getInt("ID"));
                supp.setSupplier_Name(rs.getString("Name"));
                supp.setSupplier_Contact(rs.getString("contact"));
                supp.setSupplier_Email(rs.getString("email"));
                supp.setSupplier_Address(rs.getString("address"));
                supp.setUpdate(new JFXButton("Update"));
                supp.setStatus(new JFXButton(rs.getString("Status")));
                supp.setAction_col(new HBox(), supp.getUpdate(), supp.getStatus());
                supplist.add(supp);                
            }
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getSupplierObjects", "Error!!!");
        }
        return supplist;
    }
    
    
    //                                      CUSTOMER                                        //
    //returns tableview for customer
    public static ObservableList<Customer> getCustomerRecords(String query){
        ObservableList<Customer> customerlist = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            customerlist = getCustomerObjects(rs);
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getCustomerRecords", "Error!!!");
        }
        return customerlist;
    }
    
    //process objects to tableview..
    private static ObservableList<Customer> getCustomerObjects(ResultSet rs){
        ObservableList<Customer> customerlist = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                Customer cust = new Customer();
                cust.setCustomer_ID(rs.getInt("ID"));
                cust.setCustomer_name(rs.getString("Name"));
                cust.setCustomer_contact(rs.getString("Contact"));
                cust.setCustomer_address(rs.getString("Address"));
                cust.setCustomer_gender(rs.getString("Gender"));                
                cust.setOrders(new JFXButton("View Orders"));
                cust.setUpdate(new JFXButton("Update"));
                cust.setStatus(new JFXButton(rs.getString("Status")));     
                cust.setAction_col(new HBox(),cust.getOrders(), cust.getUpdate(), cust.getStatus());
                customerlist.add(cust);
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getProductObjects", "Error!!!");
        }
        
        return customerlist;
    }
    
    
    //                                      STOCKS                                        //
    public static ObservableList<StockIN> getStockInRecords(String query){
        ObservableList<StockIN> stocks = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            stocks = getStockInObjects(rs);
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getCustomerRecords", "Error!!!");
        }
        return stocks;
        
    }
    private static ObservableList<StockIN> getStockInObjects(ResultSet rs) {
        ObservableList<StockIN> stocks = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                StockIN si = new StockIN();
                si.setStockin_id(rs.getString("ID"));
                si.setEmployee_name(rs.getString("Employee"));
                si.setTotal_products(getResultSetObject(rs,"products"));                
                si.setTotal_quantity(getResultSetObject(rs,"quantity"));
                si.setDate(rs.getString("Date"));
                int status = rs.getInt("status_id");
                switch (status) {
                    case 1:
                        si.setStatus(new JFXButton("Pending"));
                        break;
                    case 2:
                        si.setStatus(new JFXButton("Done"));
                        break;
                    case 3:
                        si.setStatus(new JFXButton("Canceled"));
                    break;
                }
                si.setView(new JFXButton("View Stock"));    
                si.setAction_col(new HBox(), si.getView(), si.getStatus());
                stocks.add(si);                
            }
        } catch (SQLException ex) {
               AlertMessagess.alertError(ex.getMessage(), "Database: getStockInObjects", "Error!!!");
        }
        return stocks;
    }
    public static ObservableList<StockIN_Products> getStockInProductsRecords(String query){
        ObservableList<StockIN_Products> si_products = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            si_products = getStockInProductsObjects(rs);
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getStockInProductRecords", "Error!!!");
        }
        return si_products;
        
    }
    private static ObservableList<StockIN_Products> getStockInProductsObjects(ResultSet rs){
        ObservableList<StockIN_Products> si_products = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                StockIN_Products prodlist = new StockIN_Products();
                prodlist.setDetails_id(rs.getString("details_id"));
                prodlist.setProduct_name(rs.getString("Product"));
                prodlist.setProduct_qnty(rs.getString("quantity"));
                prodlist.setPurchase_price(rs.getString("price"));                
                prodlist.setUnit(rs.getString("unit"));
                if(rs.getString("status").equals("Actived")){
                    prodlist.setUpdate(new JFXButton("Add to Stock"));
                }else{
                    prodlist.setUpdate(new JFXButton("Added"));
                }
                prodlist.setAction(new JFXButton("Remove"));
                prodlist.setAction_col(new HBox(), prodlist.getUpdate(), prodlist.getAction());
                prodlist.setTotal_price();
                prodlist.setSupplier(rs.getString("Supplier"));
                
                si_products.add(prodlist);
                
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getStockInProductObjects", "Error!!!");
        }
        return si_products;
    }    
    private static String getResultSetObject(ResultSet rs,String column) throws SQLException{
        String value = "";
        if(rs.getString(column) == null){
            value = "N/A";            
        }else{
            value = rs.getString(column);
        }        
        return value;            
    }
    
    public static ObservableList<Stocks> getStocksRecords(String query,String list){
        ObservableList<Stocks> stock = null;        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            stock = getStockObjects(rs,list);
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getStockInProductRecords", "Error!!!");
        }
        return stock;        
    }
    
    private static ObservableList<Stocks> getStockObjects(ResultSet rs,String list) {
        ObservableList<Stocks> stocklist = FXCollections.observableArrayList();
        
        try {
            if(list.equalsIgnoreCase("stocks")){
            while(rs.next()){
                Stocks stock = new Stocks();
                stock.setInventory_id(rs.getString("ID"));
                stock.setProduct_name(rs.getString("Product"));
                stock.setProduct_type(rs.getString("Type"));
                stock.setProduct_category(getResultSetObject(rs,"Category"));
                stock.setProduct_qnty(rs.getString("Qnty"));
                stock.setProduct_unit(rs.getString("Unit"));
                stock.setEdit(new JFXButton("Edit"));                
                stocklist.add(stock);
            }    
            }
            else{
            while(rs.next()){
                Stocks stock = new Stocks();
                stock.setCinventory_id(rs.getString("ID"));
                stock.setCproduct_name(rs.getString("Product"));
                stock.setCproduct_unit(rs.getString("Unit"));
                stock.setCproduct_meat(rs.getString("Meat"));
                stock.setCproduct_qnty(rs.getString("Qnty"));
                stocklist.add(stock);
            }    
            }
            
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getStockObjects", "Error!!!");
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stocklist;
    }

    public static ObservableList<Livestockin> getLivestockRecords(String query){
        ObservableList<Livestockin> livestock = null;        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            livestock = getLivestockObjects(rs);
        }catch(SQLException ex){
            AlertMessagess.alertError(ex.getMessage(), "Database: getLivestockRecords", "Error!!!");
        }
        return livestock;        
    }
    private static ObservableList<Livestockin> getLivestockObjects(ResultSet rs){
        ObservableList<Livestockin> livestock = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                Livestockin live = new Livestockin();
                live.setLivestockin_id(rs.getString("ID"));
                live.setSupplier_name(rs.getString("Supplier"));
                live.setAnimal_name(rs.getString("Animal"));
                live.setQnty(rs.getString("Qnty"));
                live.setTotal_kgs(getResultSetObject(rs,"Total_Kgs"));
                live.setPrice(rs.getString("Price"));
                live.setTotal_price(getResultSetObject(rs,"TotalPrice"));
                live.setEmployee(rs.getString("Employee"));
                live.setDate(rs.getString("Date"));
                live.setView(new JFXButton("View"));
                live.setStatus(new JFXButton(rs.getString("Status")));
                live.setCol_action(new HBox(), live.getView(), live.getStatus());
                livestock.add(live);
                
                
            }
        } catch (SQLException ex) {
            AlertMessagess.alertError(ex.getMessage(), "Database: getLivestockObjects", "Error!!!");
        }
        return livestock;
    }
    public static ObservableList<Carcass> getCarcassRecords(String query){
        ObservableList<Carcass> carcass = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            carcass = getCarcassObjects(rs);
            
        } catch (SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carcass;
    }
    private static ObservableList<Carcass> getCarcassObjects(ResultSet rs) throws SQLException{
        ObservableList<Carcass> carcass = FXCollections.observableArrayList();
        while(rs.next()){
            Carcass c = new Carcass();
            c.setProduct_id(rs.getString("product_id"));
            c.setProduct_name(rs.getString("Name"));            
            c.setQuantity(Database.getRecord("select *from tbl_livestockin_carcass where product_id = "+c.getProduct_id()+" and livestockin_id = "+ ObjectController.si_carcass.label_no.getText(), "quantity"));
            carcass.add(c);
        }
        return carcass;               
    }
    public static ObservableList<Particular> getParticularRecords(String query){
        ObservableList<Particular> particular = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            particular = getParticularObjects(rs);
            
        } catch (SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return particular;
    }
    private static ObservableList<Particular> getParticularObjects(ResultSet rs) throws SQLException{
        ObservableList<Particular> part = FXCollections.observableArrayList();
        while(rs.next()){
            Particular particular = new Particular();
            particular.setParticular_id(rs.getString("particular_id"));
            particular.setLivestockin_id(rs.getString("livestockin_id"));
            particular.setQuantity(rs.getString("quantity"));
            particular.setRemove(new JFXButton("Remove"));
            part.add(particular);
        }
        return part;
    }
    public static ObservableList<Stocks> getProductListRecords(){
        ObservableList<Stocks> stock = null;
        try {            
            
            st = conn.createStatement();   
            rs = st.executeQuery("select i.inventory_id as ID,p.product_id as product_id,p.productName as Product,q.title as Unit,t.title as Type,c.title as Category,i.quantity,p.status_id from tbl_inventorylist i "
                            + "join tbl_product p using(product_id) "
                            + "left join tbl_quantitytype q on p.qntytype_id = q.qntytype_id "
                            + "left join tbl_producttype t on p.producttype_id = t.producttype_id "
                            + "left join tbl_productcategory c on p.productcategory_id = c.productcategory_id");
            stock = getProductListObjects(rs);
            
        } catch (SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }
    private static ObservableList<Stocks> getProductListObjects(ResultSet rs) throws SQLException{
        ObservableList<Stocks> stock = FXCollections.observableArrayList();
        
        while(rs.next()){
            if(rs.getString("status_id").equals("1")){
                Stocks s = new Stocks();
                s.setInventory_id(rs.getString("ID"));
                s.setProduct_id(rs.getString("product_id"));
                s.setProduct_name(rs.getString("Product"));
                s.setProduct_unit(rs.getString("Unit"));
                s.setProduct_type(rs.getString("Type"));
                s.setProduct_qnty(rs.getString("quantity"));
                s.setProduct_category(getResultSetObject(rs,"Category"));
                String price_record = "N/A";
                if(s.getProduct_type().equals("Grocery")){                
                    price_record = getRecord("select *from groceryproductlist where product_id = "+s.getProduct_id(), "SellPrice");;
                    if(price_record == null){
                        price_record = "N/A";
                    }
                    s.setProduct_price(price_record);
                }else{
                    price_record = getRecord("select *from meatproductlist where product_id = "+s.getProduct_id(),"Price");
                    if(price_record == null){
                        price_record = "N/A";
                    }
                    s.setProduct_price(price_record);
                }            
                stock.add(s);                
            }
            
        }
        return stock;
    }

    
}
