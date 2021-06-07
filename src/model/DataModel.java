package model;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class DataModel {
	
	public ListProperty<Brigada> brigady = new SimpleListProperty<>(FXCollections.observableArrayList());
	public ListProperty<Osoba> brigadnici = new SimpleListProperty<>();
	public ListProperty<Strom> stromy = new SimpleListProperty<>();
	
	public void initializeModel() {
		brigadnici.clear();
		brigadnici.add(new Osoba("Petr Maly", 0));
		brigadnici.add(new Osoba("Pavel Kraus", 0));
		
		stromy.clear();
		stromy.add(new Strom("Smrk", 0));
		stromy.add(new Strom("Dub", 0));
		stromy.add(new Strom("Buk", 0));
		
		brigady.clear();
		brigady.add(new Brigada(brigadnici.get(0), stromy.get(0), LocalDate.of(2020, 1, 8), 10, 0.5));
		brigady.add(new Brigada(brigadnici.get(1), stromy.get(2), LocalDate.of(2021, 4, 25), 20, 0.8));
		brigady.add(new Brigada(brigadnici.get(1), stromy.get(1), LocalDate.of(2020, 6, 6), 15, 0.6));
	}
}
