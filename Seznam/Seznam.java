package cv06;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Aplikace seznam kontaktu
 * @author Lukas Runt
 * @version 1.1 (2020-03-31)
 */
public class Seznam extends Application {
	
	private static final String OBSAH_TITULKU = "Seznam - Lukas Runt - A20B0226P"; 
	private ListView<Kontakt> seznam;
	private DataModel model = new DataModel();
	private TextField jmenoTF;
	private TextField prijmeniTF;
	private TextField emailTF;
	private TextField uliceTF;
	private TextField obecTF;
	private TextField pscTF;
	private Alert alertInfo = new Alert(AlertType.INFORMATION);
	private Alert alertError = new Alert(AlertType.ERROR);
	private Alert alertVyber = new Alert(AlertType.CONFIRMATION);
	private Button ulozPridejBT;
	private int index;
	
	public void init() {
		System.out.print("Inicializace");
		model.initializeModel();
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(OBSAH_TITULKU);
		
		primaryStage.setScene(getScene());
		
		primaryStage.setMinWidth(480);
		primaryStage.setMinHeight(320);
		primaryStage.setHeight(320);
		primaryStage.show();
	}

	private Scene getScene() {
		Scene scene = new Scene(getRoot());
		return scene;
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setCenter(getSeznam());
		rootBorderPane.setRight(getData());
		rootBorderPane.setBottom(getOvladani());
		
		return rootBorderPane;
	}

	/**
	 * Tlacitka pod seznamem a editacnim panelem
	 * @return
	 */
	private Node getOvladani() {
		HBox tlacitka = new HBox();
		
		Button zobrazBT = new Button("Zobraz");
		zobrazBT.setOnAction(e -> zobraz(e));
		zobrazBT.setPrefWidth(70);
		Button infoBT = new Button("Info");
		infoBT.setOnAction(e -> info(e));
		infoBT.setPrefWidth(70);
		Button smazBT = new Button("Smaz");
		smazBT.setOnAction(e -> smaz(e));
		smazBT.setPrefWidth(70);
		
		tlacitka.getChildren().addAll(zobrazBT, infoBT, smazBT);
		tlacitka.setAlignment(Pos.CENTER);
		tlacitka.setPadding(new Insets(5));
		tlacitka.setSpacing(10);
		
		return tlacitka;
	}
	
	/**
	 * Zobrazi v editacnim panelu informace o kontaktu
	 * @param e
	 */
	private void zobraz(ActionEvent e) {
		index = seznam.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			jmenoTF.setText(model.seznam.get(index).getJmeno());
			prijmeniTF.setText(model.seznam.get(index).getPrijmeni());
			emailTF.setText(model.seznam.get(index).getEmail());
			uliceTF.setText(model.seznam.get(index).getUlice());
			obecTF.setText(model.seznam.get(index).getObec());
			pscTF.setText("" + model.seznam.get(index).getPcs());
			ulozPridejBT.setText("Uloz");
		}
		else {
			nebylVybranKontakt();
		}
	}

	/**
	 * Pokud je vybrana polozka, vyskoci okno s informacemi o kontaktu
	 * @param e
	 */
	private void info(ActionEvent e) {
		int index = seznam.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			alertInfo.setTitle("Informace o kontaktu");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText(model.seznam.get(index).toString());
			alertInfo.showAndWait();
			seznam.getSelectionModel().clearSelection();
		}
		else {
			nebylVybranKontakt();
		}
	}
	
	/**
	 * Vyskokovaci okno upozornuje ze neni vybrana zadna polozka
	 */
	private void nebylVybranKontakt() {
			alertError.setTitle("Error");
			alertError.setHeaderText(null);
			alertError.setContentText("Nebyl vybrán žádný kontakt!");
			alertError.showAndWait();
	}

	/**
	 * Smaze vybranou polozku v listu,
	 * pred smazanim se zapta jestli chceme polozku opravdu smazat,
	 * kdyz neni vybrana zadna polozka vyskoci upozorneni
	 * @param e
	 */
	private void smaz(ActionEvent e) {
		int index = seznam.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			alertVyber.setTitle("Smazani");
			alertVyber.setHeaderText(null);
			alertVyber.setContentText("Chcete smazat kontakt " + model.seznam.get(index).getJmeno() + " " + model.seznam.get(index).getPrijmeni());
			Optional<ButtonType> result = alertVyber.showAndWait();
			if(result.get() == ButtonType.CANCEL) {
				seznam.getSelectionModel().clearSelection();
			}
			else { 
				model.seznam.remove(index);
				seznam.getSelectionModel().clearSelection();
				}
		}	
		else {
			nebylVybranKontakt();
		}
	}

	/**
	 * Editacni panel
	 * @return
	 */
	private Node getData() {
		
		Label jmenoLB = new Label("Jmeno");
		jmenoTF = new TextField();
		jmenoTF.setPrefWidth(100);
		
		VBox jmeno = new VBox();
		jmeno.getChildren().addAll(jmenoLB, jmenoTF);
		
		Label prijmeniLB = new Label("Prijmeni");
		prijmeniTF = new TextField();
		prijmeniTF.setPrefWidth(100);
		
		VBox prijmeni = new VBox();
		prijmeni.getChildren().addAll(prijmeniLB, prijmeniTF);
		
		HBox jmenoPrijmeni = new HBox();
		jmenoPrijmeni.getChildren().addAll(jmeno, prijmeni);
		jmenoPrijmeni.setPadding(new Insets(5));
		jmenoPrijmeni.setSpacing(5);
		
		Label emailLB = new Label("E-mail");
		emailTF = new TextField();
		emailTF.setMaxWidth(205);
		
		VBox email = new VBox();
		email.getChildren().addAll(emailLB, emailTF);
		email.setPadding(new Insets(5));
		
		Label uliceLB = new Label("Ulice");
		uliceTF = new TextField();
		uliceTF.setMaxWidth(205);
		
		VBox ulice = new VBox();
		ulice.getChildren().addAll(uliceLB, uliceTF);
		ulice.setPadding(new Insets(5));
		
		Label pscLB = new Label("PSC");
		pscTF = new TextField();
		pscTF.setMaxWidth(60);
		
		VBox psc = new VBox();
		psc.getChildren().addAll(pscLB,pscTF);
		
		Label obecLB = new Label("Obec");
		obecTF = new TextField();
		obecTF.setMaxWidth(140);
		
		VBox obec = new VBox();
		obec.getChildren().addAll(obecLB, obecTF);
		
		HBox pscObec = new HBox();
		pscObec.getChildren().addAll(psc, obec);
		pscObec.setPadding(new Insets(5));
		pscObec.setSpacing(5);
		
		ulozPridejBT = new Button("Pridej");
		ulozPridejBT.setPrefWidth(70);
		ulozPridejBT.setOnAction(e -> pridej(e));
		
		Button zrusBT = new Button("Zrus");
		zrusBT.setPrefWidth(70);
		zrusBT.setOnAction(e -> zrus(e));
		
		HBox uloz = new HBox();
		uloz.getChildren().addAll(zrusBT, ulozPridejBT);
		uloz.setAlignment(Pos.CENTER_RIGHT);
		uloz.setPadding(new Insets(5));
		uloz.setSpacing(5);
		
		VBox celek = new VBox();
		celek.getChildren().addAll(jmenoPrijmeni, email, ulice, pscObec, uloz);
		
		return celek;
	}
	
	/**
	 * Ovladani tlacitka Prdej/Uloz,
	 * pokud jsem zmackli tlacitko zobraz ulkadame kontakt do uz existujiciho kontaktu,
	 * jestli ze vytvarime novy kontakt zaklada se nova instance kontaktu
	 * @param e
	 */
	public void pridej(Event e) {
		try {
			if(ulozPridejBT.getText().equals("Pridej")) {
				Kontakt kontakt = new Kontakt(jmenoTF.getText(), prijmeniTF.getText(), emailTF.getText(), uliceTF.getText(), obecTF.getText(), pscTF.getText());
				model.seznam.add(kontakt);
				razeni();
				zrus(e);
			}
			else {
				model.seznam.set(index, new Kontakt(jmenoTF.getText(), prijmeniTF.getText(), emailTF.getText(), uliceTF.getText(), obecTF.getText(), pscTF.getText()));
				zrus(e);
				razeni();
				ulozPridejBT.setText("Pridej");
			}
		}
		catch(Exception ex) {
			alertError.setHeaderText(null);
			alertError.setContentText("Vstupni data nejsou v poradku!\nZkontrolujte prosim jejich spravnost.\n" + ex.getMessage());
			alertError.showAndWait();
		}	
	}
	
	/**
	 * Razeni seznamu
	 */
	private void razeni() {
		seznam.getItems().sort((a,b) -> {
			 if(a.getJmeno().equals(b.getJmeno())) {
				 return a.getPrijmeni().compareTo(b.getPrijmeni());
			 } else {
				 return a.getJmeno().toLowerCase().compareTo(b.getJmeno().toLowerCase());
			 }
			});
	}
	
	/**
	 * Zruseni zakladani, nebo ukladani kontaktu
	 * @param e
	 */
	private void zrus(Event e) {
		jmenoTF.clear();
		prijmeniTF.clear();
		emailTF.clear();
		uliceTF.clear();
		obecTF.clear();
		pscTF.clear();
		ulozPridejBT.setText("Pridej");
	}

	/**
	 * seznam
	 * @return
	 */
	private Node getSeznam() {
		seznam = new ListView<>(model.seznam.get());
		razeni();
		seznam.setCellFactory(TextFieldListCell.forListView(new StringConverter<Kontakt>() {

			@Override
			public String toString(Kontakt object) {
				return object.getJmeno(); //+ " " + object.getPrijmeni();
			}

			@Override
			public Kontakt fromString(String string) {
				if (string == null || string.length() <= 0) {
					alertError.setHeaderText(null);
					alertError.setContentText("Nebyl zadan zadny text." );
					alertError.showAndWait();
					model.seznam.set(index, new Kontakt(model.seznam.get(index).getJmeno(), model.seznam.get(index).getPrijmeni(), model.seznam.get(index).getEmail(), model.seznam.get(index).getUlice(), model.seznam.get(index).getObec(), "" + model.seznam.get(index).getPcs()));
				}else {
					int index = seznam.getSelectionModel().getSelectedIndex();
				model.seznam.set(index, new Kontakt(string, model.seznam.get(index).getPrijmeni(), model.seznam.get(index).getEmail(), model.seznam.get(index).getUlice(), model.seznam.get(index).getObec(), "" + model.seznam.get(index).getPcs()));
				razeni();
				}
				return null;
			}
		}));
		seznam.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		seznam.setEditable(true);
		BorderPane.setMargin(seznam, new Insets(5));
		
		return seznam;
	}
	
	
	
}
