package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.MySimpleDoubleProperty;

public class Osoba {
	
	/** jmeno brigadnika */
	private final StringProperty jmeno = new SimpleStringProperty();
	/** odpracovany cas */
	private final DoubleProperty cas = new MySimpleDoubleProperty();

	/**
	 * Konstruktor brigadnikqa
	 * @param jmeno jmeno brigadnika
	 * @param cas odpracovany cas
	 */
	public Osoba(String jmeno, double cas) {
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
	public void setCas(double novyCas) {
		cas.set(novyCas);
	}
	
	public double getCas() {
		return cas.get();
	}
	
	public DoubleProperty casProperty(){
		return cas;
	}
}
