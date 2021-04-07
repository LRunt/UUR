package cv07;
/**
 * Enum vyberu rezu pisma
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */
public enum RezPisma {
	NORMAL("Normalni"),
	BOLD("Tucne"),
	ITALIC("Kurziva");
	
	RezPisma(String value){
		this.enumValue = value;
	}
	
	private String enumValue;
	
	public String toString() {
		return enumValue;
	}
}
