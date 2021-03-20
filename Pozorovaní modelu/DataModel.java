/**
 * 
 */
package cv05;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @author Lukas Runt
 * @version 1.0 (2020-03-11)
 */
public class DataModel {
	private IntegerProperty integerData = new SimpleIntegerProperty();
	
	private int max, min;
	
	public DataModel (int cislo, int min, int max) {
		if (max <= min) {
			System.err.printf("Minimum je vetsi nez maximum!\n");
		}
		else if((cislo < min) || (cislo > max)){
			System.err.print("Cislo neni v intervalu!");
		}
		else {
			this.min = min;
			this.max = max;
			integerData.set(cislo);
			}
		
		//listener, kontroluje jestli hodnota, ktera prijde je OK, po pridani filtru by spatna hodnota nemela prijit, co by kdyby...
		integerData.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() < min) {
					integerData.setValue(min);
					return;
				}
				else if (newValue.intValue() > max) {
					integerData.setValue(max);
					return;
				}
				else {
					setIntegerData(newValue.intValue());
				}
			}
		});
	}
	
	public int getIntegerData() {
		return integerData.get();
	}
	
	public void setIntegerData(int integerData) {
		if(integerData < min) {
			this.integerData.set(min);
		}
		else if(integerData > max) {
			this.integerData.set(max);
		}
		else {
			this.integerData.set(integerData);
		}
	}
	
	public IntegerProperty integerDataProperty() {
		return integerData;
	}
	
	public int minus() {
		if (integerData.get()> min) {
			integerData.set(integerData.get()-1);
			return integerData.get();
		}
		else {
			return integerData.get();
		}
	}
	
	public int plus() {
		if (integerData.get() < max) {
			integerData.set(integerData.get()+1);
			return integerData.get();
		}
		else {
			return integerData.get();
		}
	}
	
}
