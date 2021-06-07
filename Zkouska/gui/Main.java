package gui;
import java.time.LocalDate;

import bunky.FormattedDateTableCell;
import bunky.FormattedDoubleTableCell;
import bunky.FormattedIntTableCell;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Brigada;
import model.DataModel;
import model.Osoba;
import model.Strom;
import utils.Message;

/**
 * Rozhranni k databazi brigad
 * @author Lukas Runt
 * @version 1.0 (2021-06-07)
 */
public class Main extends Application{
	
	public static TableView<Brigada> tabulka;
	public static DataModel model = new DataModel();
	public static Message zprava = new Message();
	private SeznamOsob osoby= new SeznamOsob();
	private SeznamStromu stromy = new SeznamStromu();
	
	private ComboBox<Osoba> osobaCB;
	private DatePicker datumDP;
	private ComboBox<Strom> stromCB;
	private TextField pocetTF;
	private TextField casTF;
	
	/**
	 * Inicializace dat
	 */
	public void init() {
		System.out.println("Inicializace");
		model.initializeModel();
	}

	/**
	 * Vstupni cast programu
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Zkouska - Lukas Runt - A20B0226P");
		
		primaryStage.setScene(getScene());
		
		primaryStage.setMinHeight(300);
		primaryStage.setMinWidth(800);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {Platform.exit();});
	}

	private Scene getScene() {
		Scene scene = new Scene(getRoot());
		
		return scene;
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setTop(getMenu());
		rootBorderPane.setCenter(getTabulka());
		rootBorderPane.setBottom(getOvladani());
		
		return rootBorderPane;
	}

	/**
	 * Ovladani ve spodni casti obrazovky
	 * @return
	 */
	private Node getOvladani() {
		GridPane ovladani = new GridPane();
		ovladani.setHgap(10);
		ovladani.setVgap(5);
		
		Label datumLB = new Label("Datum");
		ovladani.add(datumLB, 0, 0);
		datumDP = new DatePicker();
		ovladani.add(datumDP, 0, 1);
		
		Label osobaLB = new Label("Osoba");
		ovladani.add(osobaLB, 1, 0);
		osobaCB = new ComboBox<Osoba>();
		osobaCB.getItems().setAll(model.brigadnici);
		osobaCB.setMinWidth(100);
		ovladani.add(osobaCB, 1, 1);
		model.brigadnici.addListener((ListChangeListener<Osoba>) change -> osobaCB.getItems().setAll(model.brigadnici));
		
		Label stromLB = new Label("Strom");
		ovladani.add(stromLB, 2, 0);
		stromCB = new ComboBox<Strom>();
		stromCB.getItems().setAll(model.stromy);
		stromCB.setMinWidth(100);
		ovladani.add(stromCB, 2, 1);
		model.stromy.addListener((ListChangeListener<Strom>) change -> stromCB.getItems().setAll(model.stromy));
		
		Label pocetLB = new Label("Pocet");
		ovladani.add(pocetLB, 3, 0);
		pocetTF = new TextField();
		ovladani.add(pocetTF, 3, 1);
		
		Label casLB = new Label("Cas");
		ovladani.add(casLB, 4, 0);
		casTF = new TextField();
		//casTF.setMaxWidth(100);
		ovladani.add(casTF, 4, 1);
		
		Button pridejBT = new Button("Pridej");
		pridejBT.setMinWidth(100);
		pridejBT.setOnAction(e -> pridej(e));
		ovladani.add(pridejBT, 5, 1);
		Button zrusBT = new Button("Zrus");
		zrusBT.setMinWidth(100);
		zrusBT.setOnAction(e -> zrus());
		ovladani.add(zrusBT, 5, 0);
		
		Button smazBT = new Button("Smaz");
		smazBT.setMinWidth(100);
		smazBT.setOnAction(e -> smaz(e));
		ovladani.add(smazBT, 8, 0);
		
		Button zavriBT = new Button("Zavri");
		zavriBT.setMinWidth(100);
		zavriBT.setOnAction(e -> Platform.exit());
		ovladani.add(zavriBT, 8, 1);
		
		ovladani.setPadding(new Insets(10));
		ovladani.setAlignment(Pos.CENTER);
		return ovladani;
	}

	/**
	 * Smazani vybranych prvku
	 * @param e akce (kliknuti, nebo klavesova zkratka)
	 */
	private void smaz(ActionEvent e) {
		ObservableList<Brigada> selectedItems = tabulka.getSelectionModel().getSelectedItems();
		if(selectedItems.size() > 0) {
			if(selectedItems.size() == 1) {
				if(Main.zprava.showVyberDialog("Chcete smazat vybranou akci?")) {
					model.brigady.removeAll(selectedItems);
					tabulka.getSelectionModel().clearSelection();
				}
			}else {
				if(Main.zprava.showVyberDialog("Chcete smazat vybrane akce?")) {
					model.brigady.removeAll(selectedItems);
					tabulka.getSelectionModel().clearSelection();
				}
			}
		}else {
			Main.zprava.showErrorDialog("Nebylo nic vybrano.");
		}
		tabulka.getSelectionModel().clearSelection();
	}

	/**
	 * Pridani zaznamu o brigade
	 * @param e akce (kliknuti, nebo klavesova zkratka)
	 */
	private void pridej(ActionEvent e) {
		int pocet;
		double cas;
		if(datumDP.getValue() == null || osobaCB.getValue() == null || stromCB.getValue() == null || pocetTF.getText() == null || casTF.getText() == null) {
			zprava.showErrorDialog("Nejsou vyplneny vsechny udaje pro vytvoreni!");
			return;
		}
		String casSTR = casTF.getText();
		casSTR = casSTR.replace(',', '.');
		try {
			pocet = Integer.parseInt(pocetTF.getText());
		}catch(Exception ex) {
			zprava.showErrorDialog("Udaj pocet neni cislo!");
			return;
		}
		try {
			cas = Double.parseDouble(casSTR);
		}catch(Exception ex) {
			zprava.showErrorDialog("Udaj o casu neni cislo!");
			return;
		}
		if(cas < 0.5 || cas > 0.8) {
			zprava.showErrorDialog("Cas muze byt jen v rozsahu 0.5 - 0.8");
			return;
		}
		try {
			model.brigady.add(new Brigada((Osoba)osobaCB.getValue(), (Strom)stromCB.getValue(), datumDP.getValue(), pocet, cas));
			zrus();
			return;
		} catch(Exception ex) {
			zprava.showErrorDialog("Nevalidni data");
		}
	}

	/**
	 * Zruseni vstupu - vymazou se vsechny zadavaci pole
	 */
	private void zrus() {
		datumDP.setValue(null);
		osobaCB.setValue(null);
		stromCB.setValue(null);
		casTF.setText(null);
		pocetTF.setText(null);
	}

	/**
	 * Metoda vytvarejici tabulku
	 * @return tabulka
	 */
	@SuppressWarnings("unchecked")
	private Node getTabulka() {
		tabulka = new TableView<Brigada>(model.brigady.get());
		tabulka.setEditable(true);
		tabulka.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//-------------datum--------------------------
		TableColumn<Brigada, LocalDate> datumColumn = new TableColumn<>("Datum");
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		datumColumn.setCellFactory(cellData -> new FormattedDateTableCell<>());
		//-------------osoba-----------------------------
		TableColumn<Brigada, Osoba> osobaColumn = new TableColumn<>("Osoba");
		osobaColumn.setCellValueFactory(cellData -> cellData.getValue().osobaProperty());
		osobaColumn.setCellFactory(ComboBoxTableCell.forTableColumn(model.brigadnici));
		//------------strom------------------------------
		TableColumn<Brigada, Strom> stromColumn = new TableColumn<>("Strom");
		stromColumn.setCellValueFactory(cellData -> cellData.getValue().stromProperty());
		stromColumn.setCellFactory(ComboBoxTableCell.forTableColumn(model.stromy));
		//------------pocet vysazeni-------------------------
		TableColumn<Brigada, Integer> pocetColumn = new TableColumn<>("Pocet");
		pocetColumn.setCellValueFactory(new PropertyValueFactory<>("pocet"));
		pocetColumn.setCellFactory(cellData -> new FormattedIntTableCell<>());
		//------------cas sazeni-----------------------------
		TableColumn<Brigada, Double> casColumn = new TableColumn<>("Cas");
		casColumn.setCellValueFactory(new PropertyValueFactory<>("cas"));
		casColumn.setCellFactory(cellData -> new FormattedDoubleTableCell<>(" hod", 0.5, 0.8));
		//------------efektivita sazeni----------------------
		TableColumn<Brigada, String> efektivitaColumn = new TableColumn<>("Efektivita");
		efektivitaColumn.setCellValueFactory(cellData -> cellData.getValue().efektivitaProperty());
		efektivitaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		efektivitaColumn.setEditable(false);
		
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
		
		tabulka.getColumns().addAll(datumColumn, osobaColumn, stromColumn, pocetColumn, casColumn, efektivitaColumn);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}

	/**
	 * Metoda vytvari menu a zadavi klavesove zkratky
	 * @return menu
	 */
	private Node getMenu() {
		MenuBar menu = new MenuBar();
		
		Label osobaLB = new Label("Osoby");
		osobaLB.setOnMouseClicked(e -> osoby.showDialog());
		Menu osobaM = new Menu("", osobaLB); 
		
		Label stromLB = new Label("Stromy");
		stromLB.setOnMouseClicked(e -> stromy.showDialog());
		Menu stromM = new Menu("", stromLB);
		
		Menu ovladaniM = new Menu("Ovladani");
		
		MenuItem pridejMI = new MenuItem("Pridej");
		pridejMI.setOnAction(e -> pridej(e));
		pridejMI.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCodeCombination.CONTROL_DOWN));
		MenuItem smazMI = new MenuItem("Smaz");
		smazMI.setOnAction(e -> smaz(e));
		smazMI.setAccelerator(new KeyCodeCombination(KeyCode.DELETE, KeyCodeCombination.CONTROL_DOWN));
		MenuItem zavriMI = new MenuItem("Zavri");
		zavriMI.setOnAction(e -> Platform.exit());
		zavriMI.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCodeCombination.CONTROL_DOWN));
		
		ovladaniM.getItems().addAll(pridejMI, smazMI, zavriMI);
		menu.getMenus().addAll(osobaM, stromM, ovladaniM);
		
		return menu;
	}

}
