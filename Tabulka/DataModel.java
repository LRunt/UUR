package cv07;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

/**
 * Datovy model aplikace
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */

public class DataModel {
	
	public ListProperty<Pismo> typyPisma = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	/**
	 * Metoda na vlozeni prvnich par stylu pisem, po startu aplikace
	 */
	public void initializeModel() {
		typyPisma.clear();
		typyPisma.add(new Pismo("Serif", Color.DARKTURQUOISE, RezPisma.NORMAL, 20, true));
		typyPisma.add(new Pismo("Arial", Color.BROWN, RezPisma.ITALIC, 30, true));
		typyPisma.add(new Pismo("New Times Roman", Color.DARKBLUE, RezPisma.NORMAL, 8, true));
		typyPisma.add(new Pismo("Calibri", Color.DARKSALMON, RezPisma.BOLD, 15, false));
		typyPisma.add(new Pismo("Papyrus", Color.CHOCOLATE, RezPisma.ITALIC, 23, true));
		typyPisma.add(new Pismo("BiauKai", Color.HOTPINK, RezPisma.BOLD, 36, false));
	}

}
