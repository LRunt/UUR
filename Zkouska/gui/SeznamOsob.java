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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Osoba;

/**
 * Seznam osob - tabulka
 * @author Lukas Runt
 * @version 1.0 (2021-07-06)
 */
public class SeznamOsob extends Stage{
	
	private TableView<Osoba> tabulka;
	private TextField osobaTF;
	
	public void showDialog() {
		this.setTitle("Seznam osob - Lukas Runt");
		
		this.setScene(new Scene(getRoot()));
		
		this.setMinHeight(200);
		this.setMinWidth(400);
		this.setWidth(500);
		this.setHeight(300);
		
		this.show();
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setCenter(getTabulka());
		rootBorderPane.setBottom(getOvladani());
		
		return rootBorderPane;
	}

	/**
	 * Metoda vytvari ovladaci panel
	 * @return ovladani panel
	 */
	private Node getOvladani() {
		GridPane grid = new GridPane();
		
		grid.setHgap(10);
		grid.setVgap(5);
		
		Label osobaLB = new Label("Osoba");
		grid.add(osobaLB, 0, 0);
		osobaTF = new TextField();
		grid.add(osobaTF, 0, 1);
		
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
	 * Pridani osoby
	 * @param e akce
	 */
	private void pridej(ActionEvent e) {
		if(osobaTF.getText() != null) {
			Main.model.brigadnici.add(new Osoba(osobaTF.getText(), 0, 0));
			osobaTF.clear();
		}else {
			Main.zprava.showErrorDialog("Nebylo nic zadano!");
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
	 * Smazani brigadnika
	 * @param e akce
	 */
	private void smaz(ActionEvent e) {
		int index = tabulka.getSelectionModel().getSelectedIndex();
		if(index >= 0) {
			if(Main.model.brigadnici.get(index).getPocet() != 0 || Main.model.brigadnici.get(index).getCas() != 0) {
				if(Main.zprava.showVyberDialog("Tento brigadnik ma za sebou par brigad.\nJeho smezani bude vest i ke smazani nekolika zaznamu brigad.\nOpravdu chcete smazat tuto osobu?")) {
					Main.model.brigady.removeIf(a -> a.getOsoba().equals(Main.model.brigadnici.get(index)));
					Main.model.brigadnici.remove(index);
				} else {
					tabulka.getSelectionModel().clearSelection();
				}	
		} else {
			if(Main.zprava.showVyberDialog("Chcete smazat vybranou osobu?")) {
				Main.model.brigadnici.remove(index);
				tabulka.getSelectionModel().clearSelection();
			}
		}
	}else {
		Main.zprava.showErrorDialog("Nebylo nic vybrano.");
		}
	}

	/**
	 * Metoda vytvari tabulku
	 * @return tabulka
	 */
	@SuppressWarnings("unchecked")
	private Node getTabulka() {
		tabulka = new TableView<Osoba>(Main.model.brigadnici);
		tabulka.setEditable(true);
		
		TableColumn<Osoba, String> jmenoColumn = new TableColumn<>("Jmeno");
		jmenoColumn.setCellValueFactory(new PropertyValueFactory<Osoba, String>("jmeno"));
		jmenoColumn.setCellFactory(dataCell -> new MyFormattedStringTableCell<>());
		
		TableColumn<Osoba, Integer> pocetColunm = new TableColumn<>("Pocet");
		pocetColunm.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("pocet"));
		pocetColunm.setCellFactory(cellData -> new FormattedIntTableCell<>());
		pocetColunm.setEditable(false);
		
		TableColumn<Osoba, String> casColunm = new TableColumn<>("Cas");
		casColunm.setCellValueFactory(cellData -> cellData.getValue().casSTRProperty());
		casColunm.setCellFactory(TextFieldTableCell.forTableColumn());
		casColunm.setEditable(false);
		
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
		
		tabulka.getColumns().addAll(jmenoColumn, pocetColunm, casColunm);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}
}
