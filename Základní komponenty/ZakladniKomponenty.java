/**
 * 
 */
package cv03;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.FlowPane;

/**
 * Trida zakladni komponenty obsahuje pet tlacitek,
 * kde prvni zobrazi vsechny ostatni,
 * druhe vypise do konzole kratky text (anti-vtip),
 * treti zmeni titulek, ctvrte prida label,
 * posledni skryje vsechna tlacitka krome prvniho.
 * 
 * @author Lukas Runt
 * @version 1.0 (2020-02-28)
 */
public class ZakladniKomponenty extends Application {
	
	
	private static final String OBSAH_TITULKU_1 = "Zakladni komponenty - Lukas Runt - A20B0226P";
	private static final String OBSAH_TITULKU_2 = "Zapadoceska univerzita - Fakulta aplikovanych ved";
	private static final String VTIP = "Co rekne zemedelec kdyz hleda svuj traktor? Kde je muj traktor.";
	private static final String LABEL_TEXT = "chleba, chleba s maslem, chleba s maslem a se salamem, rohlik, rohlik s maslem, rohlik s maslem a se salamem, houska, houska s maslem, houska s maslem a se salamem, bageta, bageta s maslem, bageta s maslem a se salamem, bulka, bulka s maslem, bulka s maslem a se salamem, dalamanek, dalamanek s maslem, dalamanek s maslem a se salamem, croissant, croissant s maslem, croissant s maslem a se salamem, zemle a zemle s maslem";
	
	private String[] chleba = LABEL_TEXT.split(" ");
	private int i = 0;

	/**
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

	/**
	 * Vytvoreni GUI, ktere se ukaze uzivateli
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		/// nastaveni titulku
		primaryStage.setTitle(OBSAH_TITULKU_1);
		
		Button tlacitko01 = new Button();
		Button tlacitko02 = new Button();
		Button tlacitko03 = new Button();
		Button tlacitko04 = new Button();
		Button tlacitko05 = new Button();
		
		//pole tlacitek ktera se budou skryvat a zobrazovat
		ArrayList<Button> tlacitka = new ArrayList<>();
		tlacitka.add(tlacitko02);
		tlacitka.add(tlacitko03);
		tlacitka.add(tlacitko04);
		tlacitka.add(tlacitko05);
		
		FlowPane panel = new FlowPane();
		
		//Tlacitko 1 zobrazi skryta tlacitka
		tlacitko01.setText("Zobraz");
        tlacitko01.setOnAction(event -> tlacitka.forEach(t -> t.setVisible(true)));
             
        //Tlacitko 2 vypise kratky text
        tlacitko02.setText("Vypis");
        tlacitko02.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println(VTIP);
				
			}
        	
        }
        		);
        //tlacitko02.setOnAction(event -> System.out.println(VTIP));
        tlacitko02.setVisible(false);
        
        //Tlacitko 3 zmeni titulek aplikace
        tlacitko03.setText("Zmena titulku");
        //Vypsani pomoci obsluhy metody handle
        tlacitko03.setOnAction(new EventHandler<ActionEvent>() {
    
			@Override
			public void handle(ActionEvent event) {
				if (primaryStage.getTitle().equals(OBSAH_TITULKU_1)) {
    				primaryStage.setTitle(OBSAH_TITULKU_2);
    			}
    			else {
    				primaryStage.setTitle(OBSAH_TITULKU_1);
    			}
			}
        });
        tlacitko03.setVisible(false);
        
        //Tlacitko 4 pridava labely
        tlacitko04.setText("Pridej Label");
        tlacitko04.setOnAction(event -> vypisLabelu(event, panel));
        tlacitko04.setVisible(false);
        
        //Tlaciko 5 skryje tlacitka 2 až 5
        tlacitko05.setText("Skryj");
        tlacitko05.setVisible(false);
        tlacitko05.setOnAction(event -> tlacitka.forEach(t -> t.setVisible(false)));
        
        panel.getChildren().add(tlacitko01);
        panel.getChildren().addAll(tlacitka);
        
        primaryStage.setScene(new Scene(panel, 450, 300));
		//ukazani okna
		primaryStage.show();
		
		
		
	}		
		private void vypisLabelu(Event event, FlowPane panel) {
			if(i == chleba.length) {
				i = 0;
			}
			panel.getChildren().add(new Label(chleba[i] + " "));
			i++;
		}
}
