/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_pis.login;

import AlertMessages.AlertMessagess;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sad_pis.BackEnd.Database;
import sad_pis.BackEnd.EmployeeLogin;

/**
 * FXML Controller class
 *
 * @author SJ
 */
public class LoginController implements Initializable {

    @FXML
    private TextField text_username;
    @FXML
    private TextField text_password;
    @FXML
    private JFXButton btn_login;

    
    @FXML
    public void loginBtn(ActionEvent e) throws SQLException, IOException{    //login handler event
        EmployeeLogin.setUsernamePassword(text_username.getText(), text_password.getText());
        
        if(Database.loginBypass("select *from tbl_accounts where username = '"+ EmployeeLogin.getUsername()+"' and password = '"+ EmployeeLogin.getPassword()+"'")){
            AlertMessagess.alertInformation("", "Login Successful!", "Michaels Meatshop");
            //redirect...
            URL url = new File("src/sad_pis/MainDashboard.fxml").toURL();
            StackPane root = FXMLLoader.load(url);    
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            
        }else{
            AlertMessagess.alertWarning("Username or Password is Incorrect!", "WRONG CREDENTIALS", "Michaels Meatshop");
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
}
