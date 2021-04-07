package cv06;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
/**
 * Datovy model aplikace
 * @author Lukas Runt
 * @version 1.0 (2020-03-30)
 */
public class DataModel {
	public ListProperty<Kontakt> seznam = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	/**
	 * Inicializace
	 */
	public void initializeModel() {
		seznam.clear();
		seznam.add(new Kontakt("Karel", "Cerny", "k.cerny@gmail.com", "Dlouha 658", "Kralovice", "45646"));
		seznam.add(new Kontakt("Jakub", "Krejci", "memer598@gmail.com", "U Globusu 65", "Pardubice", "35987"));
		seznam.add(new Kontakt("Katerina", "Mlada", "kaculinek.poulinek@gmail.com", "Zahradni 341", "Brezno", "43145"));
		seznam.add(new Kontakt("Marie", "Vesela", "mar.vesel@seznam.cz", "Zaluzanskeho 1192/15", "Ostrava", "70300"));
		seznam.add(new Kontakt("Petr", "Maly", "petrmaly666@seznam.cz", "Stefanikova 75/8", "Brno", "60200"));
		seznam.add(new Kontakt("Lucie", "Svobodova", "lucinkaberuska@centrum.cz", "Technicka 8", "Plzen", "30100"));
	}
}
