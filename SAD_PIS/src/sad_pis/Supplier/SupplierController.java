
package sad_pis.Supplier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.Dialog;
import sad_pis.BackEnd.ObjectController;
import sad_pis.BackEnd.Supplier;



public class SupplierController implements Initializable {

    @FXML
    private StackPane mainContainer;
    @FXML
    private TableView supplierTable;
    @FXML
    private TableColumn<Supplier, Integer> supplierID;
    @FXML
    private TableColumn<Supplier, String> supplierName;
    @FXML
    private TableColumn<Supplier, String> supplierContact;
    @FXML
    private TableColumn<Supplier, String> supplierEmail;
    @FXML
    private TableColumn<Supplier, String> supplierAddress;
    @FXML
    private TableColumn<Supplier, JFXButton> col_update;
//    @FXML
//    private TableColumn<Supplier, JFXButton> col_status;
    @FXML
    private TextField text_search;
    ObservableList<Supplier> supplist;

    
    
    
    @FXML
    public void showAddSupplierDialog(ActionEvent e) throws IOException{
        Dialog.setDialog(mainContainer, "src/sad_pis/FormDialogs/addSupplierDialog.fxml",JFXDialog.DialogTransition.TOP);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //calling of init method for table...
        initTableView();
        ObjectController.sc = this;
        filter();
    }    
    
    //init table column and view
    public void initTableView(){
        supplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_ID"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplier_Name"));
        supplierContact.setCellValueFactory(new PropertyValueFactory<>("supplier_Contact"));
        supplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplier_Email"));
        supplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplier_Address"));
        col_update.setCellValueFactory(new PropertyValueFactory<>("action_col"));
//        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        editableColumns();
        
        supplist = Database.getSupplierRecords("select *from Supplier_Details");
        supplierTable.setItems(supplist);
    }
    private void editableColumns(){
        supplierName.setCellFactory(TextFieldTableCell.forTableColumn());        
        supplierName.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setSupplier_Name(e.getNewValue());
        });
        supplierContact.setCellFactory(TextFieldTableCell.forTableColumn());        
        supplierContact.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setSupplier_Contact(e.getNewValue());
        });
        supplierEmail.setCellFactory(TextFieldTableCell.forTableColumn());        
        supplierEmail.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setSupplier_Email(e.getNewValue());
        });
        supplierAddress.setCellFactory(TextFieldTableCell.forTableColumn());        
        supplierAddress.setOnEditCommit(e->{
        e.getTableView().getItems().get(e.getTablePosition().getRow()).setSupplier_Address(e.getNewValue());
        });
        
        supplierTable.setEditable(true);
    }
    
    private void filter(){
        //filter
        text_search.textProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                if(text_search.textProperty().get().isEmpty()){
                    supplierTable.setItems(supplist);
                    return;                
                }
                ObservableList<Supplier> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Supplier,?>> cols = supplierTable.getColumns();
                for(int r = 0; r < supplist.size(); r++){
                    
                    for(int c = 0; c < cols.size(); c++){
                        
                        TableColumn col = cols.get(c);
                        String cellvalue = col.getCellData(supplist.get(r)).toString();
                        cellvalue = cellvalue.toLowerCase();                        
                        if(cellvalue.contains(text_search.textProperty().get().toLowerCase())){
                            tableItems.add(supplist.get(r));
                            break;
                        }
                    }
                    
                }
                supplierTable.setItems(tableItems);
                
            }
        
        
        });
    }
}
