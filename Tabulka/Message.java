package cv07;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Trida na vyhazovani vyskakovacich okenek
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */
public class Message {
private Alert alertError = new Alert(AlertType.ERROR);
private Alert alertInfo = new Alert(AlertType.INFORMATION);
	
	/**
	 * Error dialog 
	 */
	public void showErrorDialog(String zprava) {
		alertError.setTitle("Error");
		alertError.setHeaderText(null);
		alertError.setContentText(zprava);
		alertError.showAndWait();
	}
	
	/**
	 * Information dialog
	 */
	public void showInfoDialog(String zprava) {
		alertInfo.setTitle("Info");
		alertInfo.setHeaderText(null);
		alertInfo.setContentText(zprava);
		alertInfo.showAndWait();
	}
}
