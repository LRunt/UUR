package model;

import java.time.LocalDate;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import utils.MySimpleDoubleProperty;
import utils.SimpleIntProperty;

public class Brigada {
	
	/** brigadnik, ktery sazel stromy */
	private final ObjectProperty<Osoba> brigadnik = new SimpleObjectProperty<>();
	/** druh stromu, ktery byl sazen */
	private final ObjectProperty<Strom> strom = new SimpleObjectProperty<>();
	/** datum kdy se sazelo */
	private final ObjectProperty<LocalDate> datum = new SimpleObjectProperty<>();
	/** pocet vysazenych stromu */
	private final IntegerProperty pocet = new SimpleIntProperty();
	/** cas jaky sadba zabrala */
	private final DoubleProperty cas = new MySimpleDoubleProperty(0.5, 0.8);
	/** efektivita sazeni */
	private final ObjectBinding<String> efektivita = new ObjectBinding<String>() {
		{
			bind(cas, pocet);
		}
		@Override
		protected String computeValue() {
		if ((pocet != null) && (cas != null)) {
			double jedenStrom = cas.get()/pocet.get();
			int minuty = (int)(jedenStrom * 60);
			int sekundy = (int)(((jedenStrom * 60) - minuty) * 60);
			return String.format("00:%02d:%02d", minuty, sekundy);
		}
		else {
			return null;
		}
	  };
	};

	/**
	 * Konstruktor brigady
	 * @param brigadnik prcovnik, ktery sazi stromy
	 * @param druh druh stromu, ktery se sazi
	 * @param datum datum dazeni
	 * @param pocet pocet vysazenych stromu
	 * @param cas cas sazeni
	 */
	public Brigada(Osoba brigadnik, Strom druh, LocalDate datum, int pocet, double cas) {
		setOsoba(brigadnik);
		setStrom(druh);
		setDatum(datum);
		setPocet(pocet);
		setCas(cas);
	}
	
	//-----------brigadnik-------------------------------
	public void setOsoba(Osoba novaOsoba) {
		brigadnik.set(novaOsoba);
	}
	
	public Osoba getOsoba() {
		return brigadnik.get();
	}
	
	public ObjectProperty<Osoba> osobaProperty(){
		return brigadnik;
	}
	//----------strom-------------------------------------
	public void setStrom(Strom novyStrom) {
		strom.set(novyStrom);
	}
	
	public Strom getStrom() {
		return strom.get();
	}
	
	public ObjectProperty<Strom> stromProperty(){
		return strom;
	}
	//---------datum--------------------------------------
	public void setDatum(LocalDate novyDatum) {
		datum.set(novyDatum);
	}
	
	public LocalDate getDatum() {
		return datum.get();
	}
	
	public ObjectProperty<LocalDate> datumProperty(){
		return datum;
	}
	//---------pocet--------------------------------------
	public void setPocet(int novyPocet) {
		pocet.set(novyPocet);
	}
	
	public int getPocet() {
		return pocet.get();
	}
	
	public IntegerProperty pocetProperty() {
		return pocet;
	}
	//--------cas----------------------------------------
	public void setCas(double novyCas) {
		cas.set(novyCas);
	}
	
	public double getCas() {
		return cas.get();
	}
	
	public DoubleProperty casProperty() {
		return cas;
	}
	//-------efektivita----------------------------------
	public String getEfektivita() {
		return efektivita.get();
	}
	
	public ObjectBinding<String> efektivitaProperty(){
		return efektivita;
	}
}
