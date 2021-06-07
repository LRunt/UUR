package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.SimpleIntProperty;

public class Strom {
	
	/** nazev druhu stromu */
	private final StringProperty nazev = new SimpleStringProperty();
	/** pocet vysazenych stromu */
	private final IntegerProperty pocet = new SimpleIntProperty();

	/**
	 * Konstruktor stromu
	 * @param nazev nazev druhu stromu
	 * @param pocet pocet vysazenych stromu druhu
	 */
	public Strom(String nazev, int pocet) {
		setNazev(nazev);
		setPocet(pocet);
	}
	
	//-------------nazev------------------------
	public void setNazev(String novyNazev) {
		nazev.set(novyNazev);
	}
	
	public String getNazev() {
		return nazev.get();
	}
	
	public StringProperty nazevProperty() {
		return nazev;
	}
	
	//------------pocet--------------------------
	public void setPocet(int novyPocet) {
		pocet.set(novyPocet);
	}
	
	public int getPocet() {
		return pocet.get();
	}
	
	public IntegerProperty pocetProperty() {
		return pocet;
	}
}
