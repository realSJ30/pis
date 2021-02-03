
package sad_pis.popUp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Popup {
    
    
    public static void initPopup(String[] label,AnchorPane rootPane,double left,double top,double prefWidth) {
        VBox vb = new VBox();
        for(int i= 0; i < label.length; i++){
           JFXButton btn = new JFXButton(label[i]);
           btn.setPrefSize(prefWidth, 15);//pref width,pref height
           
           btn.setFont(Font.font("Century Gothic", 12));
           btn.setAlignment(Pos.CENTER_LEFT);
           vb.getChildren().add(btn);
        }
        vb.setPrefWidth(prefWidth);
        JFXPopup pop = new JFXPopup(vb);
        pop.setPopupContent(vb);
        pop.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT,left,top);
    }
    
    
}
