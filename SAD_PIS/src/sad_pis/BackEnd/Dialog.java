
package sad_pis.BackEnd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class Dialog {
    
    public static JFXDialog dialog;
    
    
    public static void setDialog(StackPane rootPane,String str_url,DialogTransition dt) throws IOException{
        URL url = new File(str_url).toURL();
        //AnchorPane container = new AnchorPane();
        AnchorPane pane = FXMLLoader.load(url);
        dialog = new JFXDialog(rootPane,pane,JFXDialog.DialogTransition.CENTER);
        dialog.setTransitionType(dt);
        //container.getChildren().setAll(pane);
//        AnchorPane.setBottomAnchor(pane, 0.0);
//        AnchorPane.setBottomAnchor(dialog, 0.0);        
//        
        dialog.show();
    }
    
    
    
}
