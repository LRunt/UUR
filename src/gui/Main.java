package gui;
import java.time.LocalDate;

import bunky.FormattedDateTableCell;
import bunky.FormattedDoubleTableCell;
import bunky.FormattedIntTableCell;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Brigada;
import model.DataModel;
import model.Osoba;
import model.Strom;
import utils.Message;

/**
 * @author Lukas Runt
 * @version 1.0 (2021-06-07)
 */
public class Main extends Application{
	
	private TableView<Brigada> tabulka;
	private DataModel model = new DataModel();
	public static Message zprava = new Message();
	
	private ComboBox osobaCB;
	private DatePicker datumDP;
	private ComboBox stromCB;
	private TextField pocetTF;
	private TextField casTF;
	
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
		primaryStage.setMinWidth(600);
		primaryStage.show();
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
		osobaCB = new ComboBox();
		osobaCB.getItems().setAll(model.brigadnici);
		osobaCB.setMinWidth(100);
		ovladani.add(osobaCB, 1, 1);
		
		Label stromLB = new Label("Strom");
		ovladani.add(stromLB, 2, 0);
		stromCB = new ComboBox();
		stromCB.getItems().setAll(model.stromy);
		stromCB.setMinWidth(100);
		ovladani.add(stromCB, 2, 1);
		
		Label pocetLB = new Label("Pocet");
		ovladani.add(pocetLB, 3, 0);
		pocetTF = new TextField();
		//pocetTF.setMaxWidth(100);
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
		ovladani.add(zrusBT, 6, 1);
		
		ovladani.setPadding(new Insets(5));
		ovladani.setAlignment(Pos.CENTER);
		return ovladani;
	}

	private void pridej(ActionEvent e) {
		int pocet;
		String casSTR = casTF.getText();
		casSTR.replace(',', '.');
		double cas;
		if(datumDP == null || osobaCB == null || stromCB == null || pocetTF == null || casTF == null) {
			zprava.showErrorDialog("Nejsou vyplneny vsechny udaje pro vytvoreni!");
			return;
		}
		try {
			pocet = Integer.parseInt(pocetTF.getText());
		}catch(Exception ex) {
			zprava.showErrorDialog("Udaj pocet neni cislo!");
			return;
		}
		try {
			cas = Double.parseDouble(casTF.getText());
		}catch(Exception ex) {
			zprava.showErrorDialog("Udaj o casu neni cislo!");
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

	private void zrus() {
		datumDP.setValue(null);
		osobaCB.setValue(null);
		stromCB.setValue(null);
		casTF.setText(null);
		pocetTF.setText(null);
	}

	private Node getTabulka() {
		tabulka = new TableView<Brigada>(model.brigady.get());
		tabulka.setEditable(true);
		
		TableColumn<Brigada, LocalDate> datumColumn = new TableColumn<>("Datum");
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		datumColumn.setCellFactory(cellData -> new FormattedDateTableCell<>());
		
		TableColumn<Brigada, Osoba> osobaColumn = new TableColumn<>("Osoba");
		osobaColumn.setCellValueFactory(cellData -> cellData.getValue().osobaProperty());
		osobaColumn.setCellFactory(ComboBoxTableCell.forTableColumn(model.brigadnici));
		
		TableColumn<Brigada, Strom> stromColumn = new TableColumn<>("Strom");
		stromColumn.setCellValueFactory(cellData -> cellData.getValue().stromProperty());
		stromColumn.setCellFactory(ComboBoxTableCell.forTableColumn(model.stromy));
		
		TableColumn<Brigada, Integer> pocetColumn = new TableColumn<>("Pocet");
		pocetColumn.setCellValueFactory(new PropertyValueFactory<>("pocet"));
		pocetColumn.setCellFactory(cellData -> new FormattedIntTableCell<>());
		
		TableColumn<Brigada, Double> casColumn = new TableColumn<>("Cas");
		casColumn.setCellValueFactory(new PropertyValueFactory<>("cas"));
		casColumn.setCellFactory(cellData -> new FormattedDoubleTableCell<>(" hod", 0.5, 0.8));
		
		TableColumn<Brigada, String> efektivitaColumn = new TableColumn<>("Efektivita");
		efektivitaColumn.setCellValueFactory(cellData -> cellData.getValue().efektivitaProperty());
		efektivitaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		efektivitaColumn.setEditable(false);
		
		tabulka.getColumns().addAll(datumColumn, osobaColumn, stromColumn, pocetColumn, casColumn, efektivitaColumn);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}

	private Node getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
