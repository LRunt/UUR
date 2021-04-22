package cv08;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.input.KeyCode;

public class SouborTreeCell extends TreeCell<Soubor> {
	private TextField editor = new TextField();

	public SouborTreeCell() {
		
		editor.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				cancelEdit();
			}
			if (event.getCode() == KeyCode.ENTER) {
				String name = editor.getText();
				
				if(name.trim().length() == 0) {
					Strom.zprava.showErrorDialog("Slozka nemuze mit prazdne jmeno!");
					cancelEdit();
				} else {
					Soubor soubor = getItem();
					soubor.setName(editor.getText());
					commitEdit(soubor);
				}
			}
		});
	}
	
	@Override
	protected void updateItem(Soubor item, boolean empty) {
		super.updateItem(item, empty);
		setGraphic(null);
		setText(null);
		
		if (!empty && item!=null) {
			if (isEditing()) {
				editor.setText(item.getName());
			} else {
				setText(item.getTyp().getSymbol() + " " + item.getName());
			}
		}
		else {
			setGraphic(null);
		}
	}
	
	@Override
	public void startEdit() {
		super.startEdit();
		
		setGraphic(editor);
		editor.setText(getItem().getName());
		setText(null);
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		
		setGraphic(null);
	}
	
	
	@Override
	public void commitEdit(Soubor newValue) {
		super.commitEdit(newValue);
		
		setGraphic(null);
	}
	
}
