package cv06;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Lukas Runt
 * @version 1.1 (2020-03-31)
 */
public class Kontakt {
	

	private StringProperty jmeno = new SimpleStringProperty();
	private StringProperty prijmeni = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty ulice = new SimpleStringProperty();
	private StringProperty obec = new SimpleStringProperty();
	private IntegerProperty pcs = new SimpleIntegerProperty();
	
	/**
	 * Konstruktor kontaktu
	 */
	public Kontakt(String jmeno, String prijmeni, String email, String ulice, String obec, String pcs) {
		setJmeno(jmeno);
		setPrijmeni(prijmeni);
		setEmail(email);
		setUlice(ulice);
		setObec(obec);
		setPcs(pcs);
	}
	
	/**
	 * @return the jmeno
	 */
	public String getJmeno() {
		return jmeno.getValue();
	}

	/**
	 * @return the prijmeni
	 */
	public String getPrijmeni() {
		return prijmeni.getValue();
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email.getValue();
	}

	/**
	 * @return the ulice
	 */
	public String getUlice() {
		return ulice.getValue();
	}

	/**
	 * @return the obec
	 */
	public String getObec() {
		return obec.getValue();
	}

	/**
	 * @return the pcs
	 */
	public int getPcs() {
		return pcs.getValue();
	}

	/**
	 * @param jmeno the jmeno to set
	 */
	public void setJmeno(String jmeno) {
		if (jmeno == null || jmeno.length() <= 0) {
			throw new NullPointerException("Policko pro vyplneni jmena je prazdne.");
		}
		else {
			this.jmeno.set(jmeno);
		}
	}

	/**
	 * @param prijmeni the prijmeni to set
	 */
	public void setPrijmeni(String prijmeni) {
		if (prijmeni == null || prijmeni.length() <= 0) {
			throw new NullPointerException("Policko pro vyplneni prijmeni je prazdne.");
		}
		else {
			this.prijmeni.set(prijmeni);;
		}
	}
	

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if (email == null || email.length() <= 0) {
			throw new NullPointerException("Policko pro vyplneni E-mailu je prazdne.");
		}
		else {
			this.email.set(email);;
		}
	}

	/**
	 * @param ulice the ulice to set
	 */
	public void setUlice(String ulice) {
		if (ulice == null || ulice.length() <= 0) {
			throw new NullPointerException("Policko pro vyplneni ulice je prazdne.");
		}
		else {
			this.ulice.set(ulice);;
		}
	}

	/**
	 * @param obec the obec to set
	 */
	public void setObec(String obec) {
		if (obec == null || obec.length() <= 0) {
			throw new NullPointerException("Policko pro vyplneni obce je prazdne.");
		}
		else {
			this.obec.set(obec);;
		}
	}

	/**
	 * @param pcs2 the pcs to set
	 */
	public void setPcs(String pcs) {
		if (pcs == null || pcs.length() <= 0) {
			throw new NullPointerException("Policko pro vyplneni PCS je prazdne.");
		}
		else if (pcs.matches(".*[a-z].*") ||  pcs.matches(".*[A-Z].*") || pcs.matches(".*\\p{Punct}.*")) {
			throw new IllegalArgumentException("PCS musi byt cislo.");
		}
		else {
			String bezMezery = pcs.replace(" ", "");
			this.pcs.set(Integer.parseInt(bezMezery));;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Jmeno: %s\nPrijmeni: %s\nE-mail: %s\nAdresa: %s, %s %d",getJmeno(), getPrijmeni(), getEmail(), getUlice(), getObec(), getPcs());
	}

}
