/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.Stocks;

import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import sad_pis.BackEnd.Carcass;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.Livestockin;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Particular;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class Stock_inCarcassController implements Initializable {

    @FXML
    public ComboBox cbo_animal;
    @FXML
    public TextField text_carcassprice;
    @FXML
    public ComboBox cbo_carcasssupplier;
    @FXML
    private JFXButton btn_addparticular;
    @FXML
    private TableView table_particular;
    @FXML
    private TableColumn<Livestockin, String> col_partsupp;
    @FXML
    private TableColumn<Livestockin, String> col_partpart;
    @FXML
    private TableColumn<Livestockin, String> col_partqnty;
    @FXML
    private TableColumn<Livestockin, String> col_parttotalkgs;
    @FXML
    private TableColumn<Livestockin, String> col_partprice;
    @FXML
    private TableColumn<Livestockin, String> col_partTP;
    @FXML
    private TableColumn<Livestockin, HBox> col_particularaction;
    
    @FXML
    private TableColumn<Livestockin, String> col_employee;
    @FXML
    private TableColumn<Livestockin, String> col_date;
    @FXML
    private TableView table_particulardetails;
    @FXML
    private TableColumn<Particular, String> col_particularno;
    @FXML
    private TableColumn<Particular, String> col_partkgs;
    @FXML
    private TableView table_carcass;
    @FXML
    private TableColumn<Carcass, String> col_carcassproduct;
    @FXML
    private TableColumn<Carcass, String> col_carcasskgs;
    @FXML
    private Label label_productCount1;
    @FXML
    private Label label_totalPrice1;
    @FXML
    private StackPane rootPane;    
    @FXML
    private TableColumn<Particular, JFXButton> col_partremovepart;
    @FXML
    public Label label_no;    
    public ObservableList<Carcass> carcass;
    @FXML
    private TextField text_particularkgs;
    double qnty = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_no.setVisible(false);
        // TODO
        ObjectController.si_carcass = this;
        Database.loadComboBox("select *from tbl_animal", cbo_animal, "title");
        Database.loadComboBox("select *from tbl_supplier", cbo_carcasssupplier, "supplierName");
        cbo_animal.getSelectionModel().selectFirst();
        cbo_carcasssupplier.getSelectionModel().selectFirst();
        initParticularTable();
        
    }    

    @FXML
    private void addCarcassStock(ActionEvent event) throws IOException {
        Dialog.setDialog(rootPane, "src/sad_pis/FormDialogs/AddNewCarcassDialog.fxml",JFXDialog.DialogTransition.TOP);
    }
    
    
    public void initParticularTable(){
        col_partsupp.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        col_employee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_partpart.setCellValueFactory(new PropertyValueFactory<>("animal_name"));
        col_partqnty.setCellValueFactory(new PropertyValueFactory<>("Qnty"));
        col_parttotalkgs.setCellValueFactory(new PropertyValueFactory<>("total_kgs"));
        col_partprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_partTP.setCellValueFactory(new PropertyValueFactory<>("total_price"));     
        col_particularaction.setCellValueFactory(new PropertyValueFactory<>("col_action"));     
        ObservableList<Livestockin> livestock = Database.getLivestockRecords("select *from livestockin");
        table_particular.setItems(livestock);                
        editableColumns();
    }
    
    public void initCarcassTable(){
        col_carcassproduct.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        col_carcasskgs.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        //carcass = Database.getCarcassRecords("select *from meatproductlist where meat = '"+meattype+"'");
        table_carcass.setItems(carcass);
    }
//    private void numbersOnly(){
//        text_particularkgs.textProperty().addListener(new ChangeListener<String>(){
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if(newValue.matches("\\d*") || newValue.matches(".")){
//                    
//                }else{
//                    text_particularkgs.setText(newValue.replaceAll("[^\\d]",""));
//                }
//            }
//        
//        });
//    }
    public void initParticularDetailsTable(){
        col_particularno.setCellValueFactory(new PropertyValueFactory<>("particular_id"));
        col_partkgs.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_partremovepart.setCellValueFactory(new PropertyValueFactory<>("remove"));
        ObservableList<Particular> part = Database.getParticularRecords("select *from tbl_livestockin_particulars where livestockin_id = "+label_no.getText());        
        table_particulardetails.setItems(part);        
        
    }

    @FXML
    private void addParticularDetails(ActionEvent event) {
        Database.processSQL("update tbl_livestockin set status_id = 1 where livestockin_id = "+label_no.getText(), null, null);
        String query = "insert into tbl_livestockin_particulars values (null,"+label_no.getText()+","+text_particularkgs.getText()+")";
        Database.processSQL(query, "Data", "Success");
        text_particularkgs.setText(null);
        initParticularDetailsTable();
        initParticularTable();
        setLabels();
    }
    public void setLabels(){
        if(Database.getRecord("select sum(quantity) as qnty from tbl_livestockin_carcass where livestockin_id = "+label_no.getText(), "qnty") == null){
            label_totalPrice1.setText("N/A");
        }else{
            label_totalPrice1.setText(Database.getRecord("select sum(quantity) as qnty from tbl_livestockin_carcass where livestockin_id = "+label_no.getText(), "qnty"));
        }        
        label_productCount1.setText(Database.getRecord("select *from livestockin where id = "+label_no.getText(), "Total_Kgs"));
    }
    private void editableColumns(){
        col_partprice.setCellFactory(TextFieldTableCell.forTableColumn());
        col_partprice.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrice(e.getNewValue());
            
            String query = "update tbl_livestockin set purchasePrice = "+e.getNewValue()+" where livestockin_id = "+
                            e.getTableView().getItems().get(e.getTablePosition().getRow()).getLivestockin_id();
            Database.processSQL(query, query, query);
            initParticularTable();
        });
        col_partsupp.setCellFactory(ComboBoxTableCell.forTableColumn(Database.getItems("select supplierName as title from tbl_supplier where status_id = 1")));
        col_partsupp.setOnEditCommit(e->{
            String supplier_id = Database.getRecord("select *from tbl_supplier where supplierName = '"+e.getNewValue()+"'", "supplier_id");
            String query = "update tbl_livestockin set supplier_id = "+supplier_id+" where livestockin_id = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getLivestockin_id();
            Database.processSQL(query, query, query);
            initParticularTable();        
        });
        col_date.setCellFactory(TextFieldTableCell.forTableColumn());
        col_date.setOnEditCommit(e->{
            String query = "update tbl_livestockin set date = '"+e.getNewValue()+"' where livestockin_id = "+
                            e.getTableView().getItems().get(e.getTablePosition().getRow()).getLivestockin_id();
            Database.processSQL(query, query, query);
            initParticularTable();        
        });
        col_partpart.setCellFactory(ComboBoxTableCell.forTableColumn(Database.getItems("select *from tbl_animal")));
        col_partpart.setOnEditCommit(e->{
            String particulars_query = "delete from tbl_livestockin_particulars where livestockin_id = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getLivestockin_id();
            String carcass_query = "delete from tbl_livestockin_carcass where livestockin_id = "+e.getTableView().getItems().get(e.getTablePosition().getRow()).getLivestockin_id();
            String livestockin_query = "update tbl_livestockin set animal_id = "+Database.getRecord("select *from tbl_animal where title = '"+e.getNewValue()+"'", "animal_id");
            Database.processSQL(particulars_query, null, null);
            Database.processSQL(carcass_query, null, null);
            Database.processSQL(livestockin_query, null, null);
            initParticularDetailsTable();            
            initCarcassTable();
            initParticularTable();            
        });
        col_partkgs.setCellFactory(TextFieldTableCell.forTableColumn());
        col_partkgs.setOnEditCommit(e->{
            if(Database.getRecord("select *from tbl_livestockin where livestockin_id = "+label_no.getText(), "status_id").equals("1")){                
                String query = "update tbl_livestockin_particulars set quantity = "+e.getNewValue()+" where particular_id = "+
                                e.getTableView().getItems().get(e.getTablePosition().getRow()).particular_id;
                Database.processSQL(query, null, null);
                initParticularTable();
                initParticularDetailsTable();
                setLabels();                
            }
        });
        col_carcasskgs.setCellFactory(TextFieldTableCell.forTableColumn());
        
        col_carcasskgs.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setQuantity(e.getNewValue());                    
            System.out.println(e.getTableView().getItems().get(e.getTablePosition().getRow()).getQuantity());
            for(int i = 0 ; i < carcass.size(); i++){
                qnty += carcass.get(i).getQuantityD();
            }
            label_totalPrice1.setText(new DecimalFormat("##.##").format(qnty));
            qnty = 0;
        });
        table_particular.setEditable(true);
        table_particulardetails.setEditable(true);        
        table_carcass.setEditable(true);
    }

    @FXML
    private void saveAddCarcassDetails(ActionEvent event) {
        double est_kgs = Double.parseDouble(label_totalPrice1.getText());
        double tot_kgs = Double.parseDouble(label_productCount1.getText());
        if(est_kgs > tot_kgs){
            AlertMessagess.alertWarning(null, "Warning! Estimated kilograms are higher than total kilograms\n"
                                        + "Please check your table carefully!", null);
        }else{
            for(int i = 0 ; i < carcass.size(); i++){
                if(!carcass.get(i).getQuantity().equals("0")){
                    String query = "";
                    String inv_query = ""; 
                    String details_id = Database.getRecord("select *from tbl_livestockin_carcass where product_id = "+carcass.get(i).getProduct_id()+" and livestockin_id = "+label_no.getText(), "details_id");
                    if(Database.getRecord("select *from tbl_livestockin_carcass where product_id = "+carcass.get(i).getProduct_id()+" and livestockin_id = "+label_no.getText(), "product_id").equals("")){
                        query = "insert into tbl_livestockin_carcass values (null,"+label_no.getText()+","+carcass.get(i).getProduct_id()
                                    +","+carcass.get(i).getQuantity()+")";                    
                    }else{
                        query = "update tbl_livestockin_carcass set quantity = "+carcass.get(i).getQuantity()+" where details_id = "+details_id;                
                    }
                    System.out.println(query);                
                    Database.processSQL(query, null, null);
                    inv_query = "update tbl_inventorylist set quantity = "+carcass.get(i).getQuantity()+" where product_id = "+carcass.get(i).getProduct_id();
                    Database.processSQL(inv_query, null, null);
                }

            }            
        }
        String livestockin_status_query = "update tbl_livestockin set status_id = 2 where livestockin_id = "+label_no.getText();
        Database.processSQL(livestockin_status_query, null, null);
        initParticularTable();
    }
    
}
