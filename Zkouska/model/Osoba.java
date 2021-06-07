package model;


import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.MySimpleDoubleProperty;

public class Osoba {
	
	/** jmeno brigadnika */
	private final StringProperty jmeno = new SimpleStringProperty();
	/** odpracovany cas */
	private final DoubleProperty cas = new MySimpleDoubleProperty();
	/** pocet vysazenych stromku */
	private final IntegerProperty pocet = new SimpleIntegerProperty();
	/** textova repreyentace casu*/
	private final ObjectBinding<String> casSTR = new ObjectBinding<String>() {
		{
			bind(cas);
		}
		@Override
		protected String computeValue() {
		if (cas != null) {
			int hodiny = (int)(cas.get());
			int minuty = (int)((cas.get()- hodiny) * 60) ;
			int sekundy = (int)((((cas.get()- hodiny) * 60) - minuty) * 60);
			return String.format("%02d:%02d:%02d", hodiny, minuty, sekundy);
		}
		else {
			return null;
		}
	  };
	};

	/**
	 * Konstruktor brigadnikqa
	 * @param jmeno jmeno brigadnika
	 * @param cas odpracovany cas
	 * @param pocet pocet vysazenych stromku
	 */
	public Osoba(String jmeno, double cas, int pocet) {
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
	//----------pocet----------------------------------
	public void setPocet(int novyPocet) {
		pocet.set(novyPocet);
	}
	
	public int getPocet() {
		return pocet.get();
	}
	
	public IntegerProperty pocetProperty() {
		return pocet;
	}
	//-------textova reprezentace casu----------------------------------
		public String getCasSTR() {
			return casSTR.get();
		}
		
		public ObjectBinding<String> casSTRProperty(){
			return casSTR;
		}
	//----toString-------------------------------------
	@Override
	public String toString() {
		return String.format("%s", jmeno.get());
	}
	
}
