package gui;

import bunky.FormattedIntTableCell;
import bunky.MyFormattedStringTableCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Strom;

/**
 * Seznam stromu - tabuka, s poctem vysazeni
 * @author Lukas Runt
 * @version 1.0 (2021-06-07)
 */
public class SeznamStromu extends Stage{

	private TableView<Strom> tabulka;
	private TextField stromTF;
	
	public void showDialog() {
		this.setTitle("Seznam stromu - Lukas Runt");
		
		this.setMinHeight(200);
		this.setMinWidth(400);
		this.setWidth(500);
		this.setHeight(300);
		
		this.setScene(new Scene(getRoot()));
		
		this.show();
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setCenter(getTabulka());
		rootBorderPane.setBottom(getOvladani());
		
		return rootBorderPane;
	}

	/**
	 * Ovladani ve spodni casti obrazovky
	 * @return ovladaci panel
	 */
	private Node getOvladani() {
		GridPane grid = new GridPane();
		
		grid.setHgap(10);
		grid.setVgap(5);
		
		Label stromLB = new Label("Strom");
		grid.add(stromLB, 0, 0);
		stromTF = new TextField();
		grid.add(stromTF, 0, 1);
		
		Button pridejBT = new Button("Pridej");
		pridejBT.setOnAction(e -> pridej(e));
		pridejBT.setMinWidth(100);
		grid.add(pridejBT, 1, 1);
		
		Button zavriBT = new Button("Zavri");
		grid.add(zavriBT, 4, 1);
		zavriBT.setMinWidth(100);
		zavriBT.setOnAction(e -> zavri(e));
		Button smazBT = new Button("Smaz");
		smazBT.setOnAction(e -> smaz(e));
		grid.add(smazBT, 3, 1);
		smazBT.setMinWidth(100);
		
		grid.setPadding(new Insets(10));
		grid.setAlignment(Pos.CENTER);
		
		return grid;
	}

	/**
	 * Pridani druhu stromu
	 * @param e akce
	 */
	private void pridej(ActionEvent e) {
		if(stromTF.getText() != null) {
			Main.model.stromy.add(new Strom(stromTF.getText(), 0));
			stromTF.clear();
		}else {
			Main.zprava.showErrorDialog("Nebylo nic zadano!");
		}
	}

	/**
	 * Smazani stromu
	 * @param e akce
	 */
	private void smaz(ActionEvent e) {
		int index = tabulka.getSelectionModel().getSelectedIndex();
		if(index >= 0) {
			if(Main.model.stromy.get(index).getPocet() != 0) {
				Main.zprava.showInfoDialog("Tato polozka nejde smazat. Jeji pocet neni 0.");
				tabulka.getSelectionModel().clearSelection();
			} else {
				if(Main.zprava.showVyberDialog("Chcete smazat vybrany strom?")) {
					Main.model.stromy.remove(index);
					tabulka.getSelectionModel().clearSelection();
				} 
			}
		} else {
			Main.zprava.showErrorDialog("Nebylo nic vybrano.");
		}
	}

	/**
	 * Zavreni okna
	 * @param e akce
	 */
	private void zavri(ActionEvent e) {
		this.close();
	}

	/**
	 * Metoda vytvari tabulku
	 * @return tabulka
	 */
	@SuppressWarnings("unchecked")
	private Node getTabulka() {
		tabulka = new TableView<Strom>(Main.model.stromy);
		tabulka.setEditable(true);
		//-------------jmeno-------------------------
		TableColumn<Strom, String> jmenoColumn = new TableColumn<>("Strom");
		jmenoColumn.setCellValueFactory(new PropertyValueFactory<Strom, String>("nazev"));
		jmenoColumn.setCellFactory(cellData -> new MyFormattedStringTableCell<>());
		//-------------pocet zasazeni----------------
		TableColumn<Strom, Integer> pocetColunm = new TableColumn<>("Pocet");
		pocetColunm.setCellValueFactory(new PropertyValueFactory<Strom, Integer>("pocet"));
		pocetColunm.setCellFactory(cellData -> new FormattedIntTableCell<>());
		pocetColunm.setEditable(false);
		
		ContextMenu cm = new ContextMenu();
		MenuItem smazMI = new MenuItem("Smaz");
		cm.getItems().add(smazMI);
		smazMI.setOnAction(e -> smaz(e));
		
		tabulka.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY) {
					cm.show(tabulka, event.getScreenX(), event.getScreenY());
				}
			};
		});
		
		tabulka.getColumns().addAll(jmenoColumn, pocetColunm);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}
		
}
