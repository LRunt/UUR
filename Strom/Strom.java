package cv08;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Aplikace virtualni souborovy system
 * @author Lukas Runt
 * @version 1.0 (2020-04-22)
 */
public class Strom extends Application{
	
	private TreeView<Soubor> fileTreeView;
	private Label cestaLB;
	private TextField nameTF;
	private ChoiceBox<SouborTyp> typCB;
	public static Message zprava = new Message();

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Strom - Lukas Runt - A20B0226P");
		
		primaryStage.setScene(getScene());
		
		primaryStage.setMinWidth(500);
		primaryStage.setMinHeight(300);
		primaryStage.setHeight(400);
		primaryStage.show();
	}

	private Scene getScene() {
		Scene scene = new Scene(getRoot());
		return scene;
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setTop(getCesta());
		rootBorderPane.setCenter(getTree());
		rootBorderPane.setBottom(getOvladani());
		
		return rootBorderPane;
	}

	private Node getOvladani() {
		GridPane ovladani =  new GridPane();
		
		ovladani.setHgap(20);
		ovladani.setVgap(5);
		
		Label nameLB = new Label("Name");
		nameTF = new TextField();
		nameTF.setMinWidth(200);
		
		Label typLB = new Label("Typ souboru");
		typCB = new ChoiceBox<SouborTyp>(FXCollections.observableArrayList(SouborTyp.values()));
		typCB.setValue(SouborTyp.ADRESAR);
		typCB.setMinWidth(100);
		
		Button pridejBT = new Button("Vytvor");
		pridejBT.setPrefSize(100, 20);
		pridejBT.setOnAction(event -> vytvorSoubor(event));
		Button smazBT = new Button("Smaz");
		smazBT.setPrefSize(100, 20);
		smazBT.setOnAction(event -> smazSoubor(event));
		
		ovladani.add(nameLB, 1, 1);
		ovladani.add(nameTF, 1, 2);
		ovladani.add(typLB, 2, 1);
		ovladani.add(typCB, 2, 2);
		ovladani.add(pridejBT, 3, 1);
		ovladani.add(smazBT, 3, 2);
		
		ovladani.setPadding(new Insets(5));
		ovladani.setAlignment(Pos.CENTER);
		
		return ovladani;
	}

	private void smazSoubor(ActionEvent event) {
		FileTreeItem vybrany = (FileTreeItem)fileTreeView.getSelectionModel().getSelectedItem();
		
		if (vybrany == null) {
			zprava.showErrorDialog("Neni vybrany zadny soubor!");
		} else {
			if(zprava.showVyberDialog("Opravdu chcete smazat tento soubor?")){
				vybrany.getParent().getChildren().remove(vybrany);
			}
			fileTreeView.getSelectionModel().clearSelection();
		}
	}

	private void vytvorSoubor(ActionEvent event) {
		FileTreeItem vybrany = (FileTreeItem)fileTreeView.getSelectionModel().getSelectedItem();
		
		if (vybrany == null) {
			zprava.showErrorDialog("Neni vybrany zadny soubor!");
		} else {
			if(vybrany.isLeaf()) {
				zprava.showErrorDialog("Nelze pridat novy soubor, protoze pozadovane misto neni adresar!");
			} else {
				if (nameTF.getText().trim().length() <= 0) {
					zprava.showErrorDialog("Neni vyplneno jmeno souboru!");
				}else {
					Soubor novySoubor = new Soubor(nameTF.getText().trim(), typCB.getValue());
					vybrany.getChildren().add(new FileTreeItem(novySoubor));
					nameTF.setText("");
					sortChildren(vybrany);
					vybrany.setExpanded(true);
				}
			}
			
			
		}
	}

	private Node getTree() {
		fileTreeView = new TreeView<Soubor>();
		
		fileTreeView.setCellFactory(view -> new SouborTreeCell());
		
		createStartItems();
		
		fileTreeView.setEditable(true);
		
		fileTreeView.setOnEditCommit(event -> {
			sortChildren(event.getTreeItem().getParent());
		});
		
		fileTreeView.getSelectionModel().selectedItemProperty().addListener(this::updateCesta);
		
		BorderPane.setMargin(fileTreeView, new Insets(5));
		return fileTreeView;
	}

	private void updateCesta(Observable observable) {
		FileTreeItem vybrany = (FileTreeItem)fileTreeView.getSelectionModel().getSelectedItem();
		
		if (vybrany != null) {
			FileTreeItem parent = (FileTreeItem)vybrany.getParent();
			
			List elementy = new ArrayList();
			elementy.add(vybrany.getValue().getName());
			
			while(parent != null) {
				elementy.add(parent.getValue().getName());
				parent = (FileTreeItem)parent.getParent();
			}
			
			StringBuilder builder = new StringBuilder();
			
			for(int i = elementy.size() - 1; i >= 0; i--) {
				builder.append(elementy.get(i));
				if(i != 0) {
					builder.append("/");
				}
			}
			
			cestaLB.setText(builder.toString());
			
		} else {
			cestaLB.setText("Nevybrano");
		}
	}
	
	private Node getCesta() {
		cestaLB = new Label();
		
		BorderPane.setMargin(cestaLB, new Insets(5));
		
		return cestaLB;
	}
	
	@SuppressWarnings("unchecked")
	private void createStartItems() {
		FileTreeItem d = new FileTreeItem(new Soubor("D:", SouborTyp.ADRESAR));
		
		FileTreeItem ppa = new FileTreeItem(new Soubor("PPA", SouborTyp.ADRESAR));
		FileTreeItem pot = new FileTreeItem(new Soubor("POT", SouborTyp.ADRESAR));
		FileTreeItem uur = new FileTreeItem(new Soubor("UUR", SouborTyp.ADRESAR));
		FileTreeItem upg = new FileTreeItem(new Soubor("UPG", SouborTyp.ADRESAR));
		FileTreeItem fyz = new FileTreeItem(new Soubor("FYI", SouborTyp.ADRESAR));
		
		FileTreeItem ppa1 = new FileTreeItem(new Soubor("SortingTest.java", SouborTyp.SOUBOR));
		FileTreeItem ppa2 = new FileTreeItem(new Soubor("CallDispatching.java", SouborTyp.SOUBOR));
		FileTreeItem fyz1 = new FileTreeItem(new Soubor("Uloha2.pdf", SouborTyp.SOUBOR));
		FileTreeItem fyz2 = new FileTreeItem(new Soubor("Uloha5.pdf", SouborTyp.SOUBOR));
		FileTreeItem upg1 = new FileTreeItem(new Soubor("feep.pgm", SouborTyp.SOUBOR));
		FileTreeItem uur1 = new FileTreeItem(new Soubor("Table.java", SouborTyp.SOUBOR));
		FileTreeItem pot1 = new FileTreeItem(new Soubor("vects.c", SouborTyp.SOUBOR));
		FileTreeItem upg2 = new FileTreeItem(new Soubor("Vlajka.java", SouborTyp.SOUBOR));
		
		ppa.getChildren().addAll(ppa1, ppa2);
		fyz.getChildren().addAll(fyz1, fyz2);
		uur.getChildren().addAll(uur1);
		upg.getChildren().addAll(upg1, upg2);
		pot.getChildren().add(pot1);
		
		d.getChildren().addAll(ppa, pot, uur, upg, fyz);
		sortChildren(d);
		sortChildren(ppa);
		sortChildren(fyz);
		sortChildren(upg);
		sortChildren(pot);
		sortChildren(uur);
		
		fileTreeView.setRoot(d);
	}
	
	private void sortChildren(TreeItem<Soubor> parent) {
		if (parent != null) {
			parent.getChildren().sort((a,b) -> {
				if (a.getValue().getTyp() == SouborTyp.ADRESAR) {
					if (b.getValue().getTyp() == SouborTyp.ADRESAR) {
						return a.getValue().getName().toLowerCase().compareTo(b.getValue().getName().toLowerCase());
					} else {
						return -1;
					}
				} else {
					if (b.getValue().getTyp() == SouborTyp.ADRESAR) {
						return 1;
					} else {
						return a.getValue().getName().toLowerCase().compareTo(b.getValue().getName().toLowerCase());
					}
				}
			});
		}
	}

}
