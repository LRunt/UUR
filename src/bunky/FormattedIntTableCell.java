package bunky;

import gui.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.util.converter.IntegerStringConverter;

/**
 * Vlastni bunka tabulky, pro velikost
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */
public class FormattedIntTableCell<S, T> extends TableCell<S, Integer>{
	
	private final Label renderLB = new Label();
	private final TextField editTF = new TextField();
	private final TextFormatter<Integer> formatovac;
	private int maxValue = Integer.MAX_VALUE;
	private int minValue = 0;
	
	public FormattedIntTableCell() {
		setGraphic(renderLB);
		
		formatovac = new TextFormatter<Integer>(new IntegerStringConverter());
		editTF.setTextFormatter(formatovac);
		formatovac.valueProperty().bindBidirectional(itemProperty());
		
		editTF.setOnKeyReleased(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE)) {
				cancelEdit();
			} else
				if(event.getCode().equals(KeyCode.ENTER)){
					int newValue = formatovac.getValue();
					
					if (newValue > this.maxValue || newValue < this.minValue) {
						Main.zprava.showErrorDialog("Cislo musi byt kladne!");
						cancelEdit();
					}
					else {
						commitEdit(newValue);
					}
				}
		});
	}
	
	@Override
	protected void updateItem(Integer item, boolean empty) {
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
					renderLB.setText("Nenastaveno");
				}
			} else {
				if(isEditing()) {
					editTF.setText(item.toString());
				} else {
					renderLB.setText(item.toString());
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
	public void commitEdit(Integer newValue) {
		super.commitEdit(newValue);
		
		setGraphic(renderLB);
	}
	
}
