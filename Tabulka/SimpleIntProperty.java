package cv07;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Upravena integer property, tak aby se nedala vlozit hodnota mimo limity
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */
public class SimpleIntProperty extends SimpleIntegerProperty{
	public int maxValue = 80;
	public int minValue = 5;

	@Override
	public void set(int newValue) {
		setInternal(newValue);
	}
	
	@Override
	public void setValue(Number v) {
		if (v == null) {
			throw new NullPointerException("Hodnota nemuze byt NULL");
		}
		setInternal(v.intValue());
	}
	
	
	private void setInternal(int v) {
		if (v < minValue || v > maxValue) {
			throw new IllegalArgumentException("Nepovolena hodnota.");
		}
		else {
			super.set(v);
		}
	}
	
}
