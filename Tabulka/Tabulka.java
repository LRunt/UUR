package cv07;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Lukas Runt
 * @version 1.0 (2020-04-06)
 */
public class Tabulka extends Application {
	
	private static final String OBSAH_TITULKU = "Tabulka - Lukas Runt - A20B0226P";
	private DataModel model = new DataModel();
	private TableView<Pismo> tabulka;
	private Alert alertError = new Alert(AlertType.ERROR);
	private Alert alertVyber = new Alert(AlertType.CONFIRMATION);
	
	/**
	 * Inicializacni blok
	 */
	public void init() {
		System.out.print("Inicializace");
		model.initializeModel();
	}
	
	/**
	 * Vstupni bod programu
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(OBSAH_TITULKU);
		
		primaryStage.setScene(getScene());
		
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(400);
		primaryStage.setHeight(600);
		primaryStage.setWidth(1000);
		primaryStage.show();
	}

	private Scene getScene() {
		Scene scene = new Scene(getRoot());
		return scene;
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setCenter(getTabulka());
		rootBorderPane.setBottom(getOvladani());
		
		return rootBorderPane;
	}

	/**
	 * Tlacitka pod seznamem
	 * @return tlacitka
	 */
	private Node getOvladani() {
		HBox tlacitka = new HBox();
		
		Button pridejBT = new Button("Pridej");
		pridejBT.setOnAction(e -> pridej(e));
		pridejBT.setPrefWidth(70);
		
		Button smazBT = new Button("Smaz");
		smazBT.setOnAction(e -> smaz(e));
		smazBT.setPrefWidth(70);
		
		tlacitka.getChildren().addAll(pridejBT, smazBT);
		tlacitka.setSpacing(5);
		tlacitka.setPadding(new Insets(5));
		tlacitka.setAlignment(Pos.CENTER);
		
		return tlacitka;
	}

	/**
	 * Smaze vybranou polozku,
	 * pred smazanim se zapta jestli chceme polozku opravdu smazat,
	 * kdyz neni vybrana zadna polozka vyskoci upozorneni
	 * @param e kliknuti na tlacitko
	 */
	private void smaz(ActionEvent e) {
		int index = tabulka.getSelectionModel().getSelectedIndex();
		if(index >= 0) {
			alertVyber.setTitle("Smazani");
			alertVyber.setHeaderText(null);
			alertVyber.setContentText("Chcete smazat vybrane pismo?");
			Optional<ButtonType> result = alertVyber.showAndWait();
			if(result.get() == ButtonType.CANCEL) {
				tabulka.getSelectionModel().clearSelection();
			}
			else { 
				model.typyPisma.remove(index);
				tabulka.getSelectionModel().clearSelection();
				}
		}
		else {
			nebyloVybranoPismo();
		}
	}

	/**
	 * Vyskakovaci okno upozornuje ze neni vybrana zadna polozka
	 */
	private void nebyloVybranoPismo() {
		alertError.setTitle("Error");
		alertError.setHeaderText(null);
		alertError.setContentText("Nebylo vybrano zadne pismo!");
		alertError.showAndWait();
	}

	/**
	 * Pridani defaultniho typu pisma
	 * @param e akce zmacknuti tlacitka
	 */
	private void pridej(ActionEvent e) {
		model.typyPisma.add(new Pismo("Calibri", Color.BLACK, RezPisma.NORMAL, 12, true));
	}

	/**
	 * Tabulka v centru obrazovky
	 * @return tabulka
	 */
	private Node getTabulka() {
		tabulka = new TableView<Pismo>(model.typyPisma.get());
		tabulka.setEditable(true);
		
		TableColumn<Pismo, String> fontColumn = new TableColumn<>("Font");
		fontColumn.setCellValueFactory(new PropertyValueFactory<Pismo, String>("font"));
		fontColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<Pismo, Color> colorColumn = new TableColumn<>("Barva");
		colorColumn.setCellValueFactory(new PropertyValueFactory<Pismo, Color>("barva"));
		colorColumn.setCellFactory(cellData -> new FormattedColorTableCell<>());
		
		TableColumn<Pismo, RezPisma> rezColumn = new TableColumn<>("Rez pisma");
		rezColumn.setCellValueFactory(new PropertyValueFactory<Pismo, RezPisma>("rez"));
		rezColumn.setCellFactory(ComboBoxTableCell.forTableColumn(RezPisma.values()));
		
		TableColumn<Pismo, Integer> velikostColumn = new TableColumn<>("Velikost");
		velikostColumn.setCellValueFactory(new PropertyValueFactory<Pismo, Integer>("velikost"));
		velikostColumn.setCellFactory(cellData -> new FormattedIntTableCell<>(5, 80));
		
		TableColumn<Pismo, Boolean> viditelnostColumn = new TableColumn<>("Viditelnost");
		viditelnostColumn.setCellValueFactory(new PropertyValueFactory<Pismo, Boolean>("viditelnost"));
		viditelnostColumn.setCellFactory(ComboBoxTableCell.forTableColumn(true, false));
		
		TableColumn<Pismo, Label> vysledekColumn = new TableColumn<>("Nahled");
		vysledekColumn.setCellValueFactory(cellData -> cellData.getValue().textProperty());
		
		tabulka.getColumns().addAll(fontColumn, colorColumn, rezColumn, velikostColumn, viditelnostColumn, vysledekColumn);
		
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}

}
