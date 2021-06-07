package gui;
import java.time.LocalDate;

import bunky.FormattedDateTableCell;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Brigada;
import model.DataModel;
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
		primaryStage.setTitle("Zkouska");
		
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
		
		return rootBorderPane;
	}

	private Node getTabulka() {
		tabulka = new TableView<Brigada>(model.brigady.get());
		tabulka.setEditable(true);
		
		TableColumn<Brigada, LocalDate> datumColumn = new TableColumn<>("Datum");
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		datumColumn.setCellFactory(cellData -> new FormattedDateTableCell<>());
		
		tabulka.getColumns().addAll(datumColumn);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}

	private Node getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
