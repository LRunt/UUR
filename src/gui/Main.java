package gui;
import java.awt.TextField;
import java.time.LocalDate;

import bunky.FormattedDateTableCell;
import bunky.FormattedDoubleTableCell;
import bunky.FormattedIntTableCell;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
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
		
		primaryStage.setMinHeight(100);
		primaryStage.setMinWidth(200);
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

		return null;
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
