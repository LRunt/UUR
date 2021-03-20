/**
 * 
 */
package cv04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Lukas Runt
 * @version 1.0 (2020-03-06)
 */
public class Layouty extends Application{
	
	private static final String OBSAH_TITULKU_1 = "Layouty - Lukas Runt - A20B0226P";

	/**
	 * Vstupni bod programu, spusteni okna
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	
	/**
	 * Inicializace
	 */
	public void init() throws Exception {
		System.out.println("Inicializace");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(OBSAH_TITULKU_1);
		
	    primaryStage.setScene(getScene());
		
	    //Minimalni velikost okna
	    primaryStage.setMinHeight(450);
		primaryStage.setMinWidth(550);
		primaryStage.show();
	}
	
	private Scene getScene() {
		Scene scene = new Scene(getRoot());
		return scene;
	}
	
	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setTop(getMenu());
		rootBorderPane.setLeft(getBoxPanel());
		rootBorderPane.setCenter(getTextBox());
		rootBorderPane.setRight(getInformace());
		rootBorderPane.setBottom(getSpodek());
		
		return rootBorderPane;
	}
	
	/**
	 * Vytvoreni menu na horni casti rozhranni
	 * @return menu (top)
	 */
	private Node getMenu() {
		MenuBar menu = new MenuBar();
		
		//Menu pro stazeni dat, aktualizaci software a upload souboru do robota 
		Menu souborMenu = new Menu("Soubor");
		MenuItem stazeni = new MenuItem("Stazeni");
		MenuItem aktualizace = new MenuItem("Aktualizace");
		MenuItem upload = new MenuItem("Upload");
		souborMenu.getItems().addAll(stazeni, aktualizace, upload);
		
		//Menu pro zapnuti a vypnuti ovladaciho programu
		Menu vypZap = new Menu("Zap/Vyp");
		RadioMenuItem zap = new RadioMenuItem("Zapnuto");
		RadioMenuItem vyp = new RadioMenuItem("Vypnuto");
		ToggleGroup grupa = new ToggleGroup();
		grupa.getToggles().addAll(zap, vyp);
		vyp.setSelected(true); //ovladaci program bude pri spusteni aplikace vypnuty
		vypZap.getItems().addAll(zap, vyp); 
		
		menu.getMenus().addAll(souborMenu, vypZap);
		return menu;
	}
	
	/**
	 * Vytvoreni ovladani v leve casti okna
	 * @return levy panel
	 */
	private Node getBoxPanel() {
		VBox zdrojEnergie = new VBox();
		
		//Volba, jaky zdroj  energie je vyuzivan (solarni, baterie, RTG)
		Label druhEnergie = new Label("Druh zdroje: ");
		ToggleGroup zdroje = new ToggleGroup();
		RadioButton solar = new RadioButton("Solarni");
		RadioButton batery = new RadioButton("Baterie");
		RadioButton rtg = new RadioButton("RTG");
		zdroje.getToggles().addAll(solar, batery, rtg);
		
		//Volbu, jake senzory jsou aktivni (akusticky, opticky, tepelny, tlakovy, radarovy)
		Label senzory = new Label("Volba senzoru: ");
		CheckBox akustika = new CheckBox("Akusticky");
		CheckBox optika = new CheckBox("Opticky");
		CheckBox teplo = new CheckBox("Tepelny");
		CheckBox tlak = new CheckBox("Tlakovy");
		CheckBox radar = new CheckBox("Radarovy");
		
		zdrojEnergie.getChildren().addAll(druhEnergie,solar, batery, rtg,new Separator(), senzory, akustika, optika, teplo, tlak, radar);
		zdrojEnergie.setSpacing(5);
		zdrojEnergie.setPadding(new Insets(5));
		
		return zdrojEnergie;
	}
	
	/**
	 * Textova oblast, do ktere bude vypisovan aktualni stav robota a jakekoliv hlaseni, ktere chce aplikace sdelit uzivateli
	 * @return textove pole uprostred
	 */
	private Node getTextBox() {
		TextArea textBox = new TextArea("Aktualni stav robota...");
		
		textBox.setPrefColumnCount(20);
		
		return textBox;
	}
	
	/**
	 * Vypisuje aktualni stav robota do textBoxu
	 * @return prava postranni lista
	 */
	private Node getInformace() {
		VBox informace = new VBox();
		
		//Informace o aktualnim natoceni a rychlosti
		Label natoceni = new Label("Aktualni natoceni a rychlost");
		TextArea rychlost = new TextArea("Natoceni: 40° \nRychlost: 10m/s");
		rychlost.setPrefWidth(50);
		rychlost.setPrefHeight(50);
		
		//ctyri stavove indikatory - popisek a pole s hodnotou (stav baterie v %; ujete metry; doba, po jakou je aktivovan; jestli je zapnuty nebo ne)
		Label stavBaterieL = new Label("Stav baterie");
		TextField stavBaterieT = new TextField("21%");
		stavBaterieT.setPrefColumnCount(3);
		Label ujeteMetryL = new Label("Ujete metry");
		TextField ujeteMetryT = new TextField("165m");
		Label dobaSpusteniL = new Label("Doba spusteni");
		TextField dobaSpusteniT = new TextField("1658s");
		Label zapnutoL = new Label("Zapnuto");
		TextField zapnutoT = new TextField("ANO");
		zapnutoT.setPrefColumnCount(3);
		
		informace.getChildren().addAll(natoceni, rychlost, new Separator(), stavBaterieL,stavBaterieT, ujeteMetryL,ujeteMetryT, dobaSpusteniL,dobaSpusteniT, zapnutoL, zapnutoT);
		informace.setPadding(new Insets(5));
		informace.setSpacing(5);
		
		return informace;
	}
	
	/**
	 * Vlacitka ovladani pohybu a tlacitka napajeni
	 * @return tlacitka ve spodni casti aplikace
	 */
	private Node getSpodek() {
		GridPane spodek = new GridPane();
		
		//Ctyri tlacitka pro kontrolu pohybu (dopredu, dozadu, otoc vlevo, otoc vpravo)
		spodek.setHgap(10);
		spodek.setVgap(10);
		Button vpred = new Button("Vpred");
		vpred.setMaxWidth(100);
		spodek.add(vpred, 3, 1);
		Button zpet = new Button("Zpet");
		zpet.setMaxWidth(100);
		spodek.add(zpet, 3, 2);
		Button doleva = new Button("Vlevo");
		doleva.setMaxWidth(100);
		spodek.add(doleva, 2, 2);
		Button doprava = new Button("Vpravo");
		doprava.setMaxWidth(100);
		spodek.add(doprava, 4, 2);
		
		//Tlacitka pro zapnuti, vypnuti a restart zarizeni.
		Button zap = new Button("Zapnout");
		spodek.add(zap, 12, 2);
		Button vyp = new Button("Vypnout");
		spodek.add(vyp, 16, 2);
		Button res = new Button("Restartovat");
		spodek.add(res, 14, 2);
		Label zarizeni = new Label("Napajeni zarizeni");
		spodek.add(zarizeni, 13, 1, 2, 1);
		
		spodek.setPadding(new Insets(5));
		//spodek.setGridLinesVisible(true);
		spodek.setAlignment(Pos.CENTER);
		
		return spodek;
	}

}
