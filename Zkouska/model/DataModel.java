package model;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
/**
 * Datovy model
 * @author Lukas Runt
 * @version 1.0 (2021-06-07)
 */
public class DataModel {
	
	/** list brigad*/
	public ListProperty<Brigada> brigady = new SimpleListProperty<>(FXCollections.observableArrayList());
	/** list osob */
	public ListProperty<Osoba> brigadnici = new SimpleListProperty<>(FXCollections.observableArrayList());
	/** list stromu */
	public ListProperty<Strom> stromy = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	/**
	 * Konstruktor s posluchacem zmen v kolekci
	 */
	public DataModel() {
		brigady.addListener((ListChangeListener<Brigada>) change -> prepocti());
	}

	/**
	 * Vlozeni dat pri spusteni
	 */
	public void initializeModel() {
		brigadnici.clear();
		brigadnici.add(new Osoba("Petr Maly", 0.0, 0));
		brigadnici.add(new Osoba("Pavel Kraus", 0.0, 0));
		brigadnici.add(new Osoba("Jakub Pleva", 0.0, 0));
		
		stromy.clear();
		stromy.add(new Strom("Smrk", 0));
		stromy.add(new Strom("Dub", 0));
		stromy.add(new Strom("Buk", 0));
		
		brigady.clear();
		brigady.add(new Brigada(brigadnici.get(0), stromy.get(0), LocalDate.of(2020, 8, 1), 10, 0.5));
		brigady.add(new Brigada(brigadnici.get(0), stromy.get(0), LocalDate.of(2020, 8, 10), 24, 0.8));
		brigady.add(new Brigada(brigadnici.get(1), stromy.get(2), LocalDate.of(2021, 4, 25), 20, 0.8));
		brigady.add(new Brigada(brigadnici.get(1), stromy.get(1), LocalDate.of(2020, 6, 6), 15, 0.6));
		brigady.add(new Brigada(brigadnici.get(2), stromy.get(2), LocalDate.of(2021, 7, 5), 12, 0.6));
		brigady.add(new Brigada(brigadnici.get(2), stromy.get(0), LocalDate.of(2020, 8, 17), 15, 0.5));
	}
	
	/**
	 * Prepocitani odpracovanych hodin a zasazenych stromu
	 */
	public void prepocti() {
		for(Osoba brigadnik : brigadnici) {
			int sum = (brigady.stream().filter(a -> a.getOsoba().equals(brigadnik)).map(a -> a.getPocet()).reduce(0, Integer::sum));
			brigadnik.setPocet(sum);
			double sum2 = (brigady.stream().filter(a -> a.getOsoba().equals(brigadnik)).map(a -> a.getCas()).reduce(0.0, Double::sum));
			brigadnik.setCas(sum2);
		}
		
		for(Strom strom : stromy) {
			int sum = (brigady.stream().filter(a -> a.getStrom().equals(strom)).map(a -> a.getPocet()).reduce(0,  Integer::sum));
			strom.setPocet(sum);
		}
	}
}
