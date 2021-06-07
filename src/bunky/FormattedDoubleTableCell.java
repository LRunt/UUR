/**
 * 
 */
package bunky;

import gui.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.util.converter.DoubleStringConverter;

/**
 * Vlastni bunka tabulky, pro cisla
 * @author Lukas Runt
 * @version 1.0 (2021-04-14)
 */
public class FormattedDoubleTableCell<S, T> extends TableCell<S, Double> {
	
	private final Label renderLB = new Label();
	private final TextField editTF = new TextField();
	private final TextFormatter<Double> formatovac;
	private Double maxValue;
	private Double minValue;
	private String units;
	
	public FormattedDoubleTableCell(String units, Double minValue, Double maxValue) {
		if(minValue > maxValue) {
			throw new IllegalArgumentException("Minimalni hodnota nemuze byt vetsi nez maximalni!");
		}
		this.maxValue = maxValue;
		this.minValue = minValue;
		setGraphic(renderLB);
		this.units = units;
		
		formatovac = new TextFormatter<Double>(new DoubleStringConverter());
		editTF.setTextFormatter(formatovac);
		formatovac.valueProperty().bindBidirectional(itemProperty());
		
		editTF.setOnKeyReleased(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE)) {
				cancelEdit();
			} else
				if(event.getCode().equals(KeyCode.ENTER)){
				double newValue = formatovac.getValue();
					
					if (newValue > this.maxValue || newValue < this.minValue) {
						Main.zprava.showErrorDialog("Cislo musi byt v rozsahu " + minValue + " - " + maxValue + ".");
						cancelEdit();
					}
					else {
						commitEdit(newValue);
					}
				}
		});
	}
	
	@Override
	protected void updateItem(Double item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setText("");
			renderLB.setText("");
			editTF.setText("");
		} else {
			if (item == null) {
				if (isEditing()) {
					editTF.setText("");
				} else {
					renderLB.setText("0");
				}
			} else {
				String itemSTR = item.toString();
				itemSTR.replace('.', ',');
				if(isEditing()) {
					editTF.setText(item.toString());
				} else {
					renderLB.setText(itemSTR + units);
				}
			}
		}
	}
	
	@Override
	public void startEdit() {
		super.startEdit();
		
		setGraphic(editTF);
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		
		setGraphic(renderLB);
	}
	
	@Override
	public void commitEdit(Double newValue) {
		super.commitEdit(newValue);
		
		setGraphic(renderLB);
	}
	
}
