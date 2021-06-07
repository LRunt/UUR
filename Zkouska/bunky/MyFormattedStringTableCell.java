package bunky;



import gui.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class MyFormattedStringTableCell<S, T> extends TableCell<S, String>{
	private final Label renderLB = new Label();
	private final TextField editTF = new TextField();
	
	public MyFormattedStringTableCell() {
		setGraphic(renderLB);
		
		editTF.setOnKeyReleased(event -> {
			if(event.getCode().equals(KeyCode.ESCAPE)) {
				cancelEdit();
			} else if(event.getCode().equals(KeyCode.ENTER)) {
				String newValue = editTF.getText();
				
				commitEdit(newValue);
			}
		});
	}
	
	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		
		if(empty) {
			editTF.setText(null);
			renderLB.setText("");
		} 
		else if (item == null) {
			if (isEditing()) {
				editTF.setText(null);
			}
			else {
				renderLB.setText("Nenastaveno");
			}
		} else {
			if(isEditing()) {
				editTF.setText(item.toString());
			}
			else {
				renderLB.setText(item);
			}
		}
	}
	
	@Override
	public void startEdit() {
		super.startEdit();
		
		setGraphic(editTF);
	}
	
	@Override
	public void commitEdit(String newValue) {
		super.commitEdit(newValue);
		
		Main.tabulka.refresh();
		setGraphic(renderLB);
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		
		setGraphic(renderLB);
	}
}
