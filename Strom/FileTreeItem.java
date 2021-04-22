package cv08;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class FileTreeItem extends TreeItem<Soubor> {
	
	public FileTreeItem(Soubor file) {
		super(file);
		
		if (file == null) {
			throw new NullPointerException("Prichozi data nesmeji byt prazdna");
		}
	}
	
	@Override
	public boolean isLeaf() {
		return getValue().getTyp() == SouborTyp.SOUBOR;
	}
	
	@Override
	public ObservableList<TreeItem<Soubor>> getChildren() {
		return super.getChildren();
	}
	
	
}
