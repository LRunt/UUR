package cv08;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Soubor {
	
	private StringProperty name = new SimpleStringProperty();
	private ObjectProperty<SouborTyp> typ = new SimpleObjectProperty<>();
	
	public Soubor(String nazev, SouborTyp typ) {
		setName(nazev);
		setTyp(typ);
	}
	
	
	//----------------jmeno-----------------------
	public String getName() {
		return name.get();
	}
	
	public void setName(String newName) {
		name.set(newName);
	}
	
	public StringProperty namePropery() {
		return name;
	}
	//-------------- typ---------------------------
	public SouborTyp getTyp() {
		return typ.get();
	}
	
	public void setTyp(SouborTyp novyTyp) {
		typ.set(novyTyp);
	}
	
	public ObjectProperty<SouborTyp> typProperty() {
		return typ;
	}
	
	@Override
	public String toString() {
		return String.format("%s", getName());
	}
	
}
