/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlertMessages;

import javafx.scene.control.Alert;


/**
 *
 * @author SJ
 */
public class AlertMessagess {

    public AlertMessagess() {
    }
    

    public static void alertWarning(String content,String header, String title){
        Alert msg = new Alert(Alert.AlertType.WARNING);
        msg.setContentText(content);
        msg.setHeaderText(header);
        msg.setTitle(title);
        msg.showAndWait();
    }
    public static void alertInformation(String content,String header, String title){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setContentText(content);
        msg.setHeaderText(header);
        msg.setTitle(title);        
        msg.showAndWait();
    }
    public static void alertError(String content,String header, String title){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setContentText(content);
        msg.setHeaderText(header);
        msg.setTitle(title);
        msg.showAndWait();
    }
    
    
    
}
