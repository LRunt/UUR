package model;

import java.time.LocalTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Osoba {
	
	/** jmeno brigadnika */
	private final StringProperty jmeno = new SimpleStringProperty();
	/** odpracovany cas */
	private final ObjectProperty<LocalTime> cas = new SimpleObjectProperty<>();

	/**
	 * Konstruktor brigadnikqa
	 * @param jmeno jmeno brigadnika
	 * @param cas odpracovany cas
	 */
	public Osoba(String jmeno, LocalTime cas) {
		setJmeno(jmeno);
		setCas(cas);
	}
	
	//-----------jmeno-------------------------------
	public void setJmeno(String noveJmeno) {
		jmeno.set(noveJmeno);
	}
	
	public String getJmeno() {
		return jmeno.get();
	}
	
	public StringProperty jmenoProperty() {
		return jmeno;
	}
	
	//-----------cas----------------------------------
	public void setCas(LocalTime novyCas) {
		cas.set(novyCas);
	}
	
	public LocalTime getCas() {
		return cas.get();
	}
	
	public ObjectProperty<LocalTime> casProperty(){
		return cas;
	}
}
