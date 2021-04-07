package cv07;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Vlastni bunka tabulky,
 * pro cteni label a pro upravu dat colorPicker
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */
public class FormattedColorTableCell<S, T> extends TableCell<S, Color>{

	private final Label renderLB = new Label();
	private final ColorPicker editCP = new ColorPicker();
	
	public FormattedColorTableCell() {
		setGraphic(renderLB);
		
		editCP.setOnKeyReleased(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE)) {
				cancelEdit();
			} else
			if(event.getCode().equals(KeyCode.ENTER)){
				Color newValue = editCP.getValue();
					
				commitEdit(newValue);
			}
		});
	}
	
	@Override
	protected void updateItem(Color item, boolean empty) {
		super.updateItem(item, empty);
		editCP.valueProperty().bindBidirectional(itemProperty());
		
		if (empty) {
			editCP.setValue(null);
			renderLB.setText("");
		}
		else 
		if (item == null) {
			if (isEditing()) {
				editCP.setValue(null);
			}
			else {
				renderLB.setText("Nenastaveno");
			}	
		}else {
			if(isEditing()) {
				editCP.setValue(item);
			}
			else {
				renderLB.setText(item.toString());
			}
		}
	}
	
	@Override
	public void startEdit() {
		super.startEdit();
		
		setGraphic(editCP);
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		
		setGraphic(renderLB);
	}
	
	@Override
	public void commitEdit(Color newValue) {
		super.commitEdit(newValue);
		
		setGraphic(renderLB);
	}
}
