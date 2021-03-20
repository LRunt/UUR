/**
 * 
 */
package cv05;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * @author Lukas Runt
 * @version 1.2 (2020-03-09)
 */
public class PozorovaniModelu extends Application {

	private static final String OBSAH_TITULKU_1 = "Pozorovani Modelu - Lukas Runt - A20B0226P"; 
	private static final int MIN = 0;
	private static final int MAX = 255;
	private static final int INIT = 0;
	public Rectangle ctverec = new Rectangle(0, 0, 100, 100);
	
	DataModel redDM = new DataModel(INIT, MIN, MAX);
	DataModel greenDM = new DataModel(INIT, MIN, MAX);
	DataModel blueDM = new DataModel(INIT, MIN, MAX);
	
	/**
	 * Filter povoli psat do textFieldu jen cislice 0-255
	 */
	final UnaryOperator<TextFormatter.Change> filter = c -> {
		String newText = c.getControlNewText();
		if (newText.isEmpty()) {
			return null;
		} else if(newText.matches("\\d{0,9}")) { //jen kladne cislice
			if(Integer.parseInt(newText) <= MAX) 
			return c;
			else {
				return null;
			}
		} else {
			return null;
		}
	};
	
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
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(OBSAH_TITULKU_1);
		
		primaryStage.setScene(getScene());
		
		primaryStage.setWidth(450);
		primaryStage.setHeight(300);
		primaryStage.show();
		//Ukonceni aplikace po zavreni okna
		primaryStage.setOnCloseRequest(e -> {Platform.exit(); System.exit(0);});
	}

	private Scene getScene() {
		Scene scene = new Scene(getRoot());
		return scene;
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setLeft(getSliderPanel());
		rootBorderPane.setCenter(getBarva());
		
		return rootBorderPane;
	}

	/**
	 * Metoda nastavuje barvu ctverce, prebarveni je pomoci timeru
	 * @return barevny ctverec
	 */
	private Node getBarva() {
		Timer tm = new Timer();
		tm.schedule(new TimerTask() {
			@Override
			public void run() {
				Color color = Color.rgb(redDM.getIntegerData(),greenDM.getIntegerData(),blueDM.getIntegerData());
				ctverec.setFill(color);
			}
		}, 0, 20);
		return ctverec;
	}

	private Node getSliderPanel() {
		StringConverter<Number> converter = new NumberStringConverter();
		
		VBox slidery = new VBox();
		
		//-----------------red-------------------
		Label r = new Label("RED");
		
		HBox redBX = new HBox();
		
		Button redBMinus = new Button("-");
		redBMinus.setPrefWidth(25);
		redBMinus.setOnAction(event -> redDM.minus());
		
		Button redBPlus = new Button("+");
		redBPlus.setPrefWidth(25);
		redBPlus.setOnAction(event -> redDM.plus());
		
		TextField redTF = new TextField();
		TextFormatter<Number> formatterRed = new TextFormatter<Number>(converter, 0, filter);
		redTF.setTextFormatter(formatterRed);
		formatterRed.valueProperty().bindBidirectional(redDM.integerDataProperty());
		redTF.setPrefWidth(40);
		
		redBX.getChildren().addAll(redBMinus, redTF, redBPlus);
		redBX.setAlignment(Pos.CENTER);
		redBX.setSpacing(5);
		
		Slider redSL = new Slider(MIN, MAX, INIT);
		redSL.setShowTickLabels(true);
		redSL.setShowTickMarks(true);
		redSL.valueProperty().bindBidirectional(redDM.integerDataProperty());
		
		//-----------------green-------------------------
		Label g = new Label("GREEN");
		
		HBox greenBX = new HBox();
		
		Button greenBMinus = new Button("-");
		greenBMinus.setPrefWidth(25);
		greenBMinus.setOnAction(event -> greenDM.minus());
		
		Button greenBPlus = new Button("+");
		greenBPlus.setPrefWidth(25);
		greenBPlus.setOnAction(event -> greenDM.plus());
		
		TextField greenTF = new TextField();
		TextFormatter<Number> formatterGreen = new TextFormatter<Number>(converter, 0, filter);
		greenTF.setTextFormatter(formatterGreen);
		formatterGreen.valueProperty().bindBidirectional(greenDM.integerDataProperty());
		greenTF.setPrefWidth(40);
		
		greenBX.getChildren().addAll(greenBMinus, greenTF, greenBPlus);
		greenBX.setAlignment(Pos.CENTER);
		greenBX.setSpacing(5);
		
		//Slider zelena
		Slider greenSL = new Slider(MIN, MAX, INIT);
		greenSL.setShowTickLabels(true);
		greenSL.setShowTickMarks(true);
		greenSL.valueProperty().bindBidirectional(greenDM.integerDataProperty());
		
		//----------------blue---------------------------
		Label b = new Label("BLUE");
		
		HBox blueBX = new HBox();
		
		Button blueBMinus = new Button("-");
		blueBMinus.setPrefWidth(25);
		blueBMinus.setOnAction(event -> blueDM.minus());
		
		Button blueBPlus = new Button("+");
		blueBPlus.setPrefWidth(25);
		blueBPlus.setOnAction(event -> blueDM.plus());
		
		TextField blueTF = new TextField();
		TextFormatter<Number> formatterBlue = new TextFormatter<Number>(converter, 0, filter);
		blueTF.setTextFormatter(formatterBlue);
		formatterBlue.valueProperty().bindBidirectional(blueDM.integerDataProperty());
		blueTF.setPrefWidth(40);
		
		blueBX.getChildren().addAll(blueBMinus, blueTF, blueBPlus);
		blueBX.setAlignment(Pos.CENTER);
		blueBX.setSpacing(5);
		
		Slider blueSL = new Slider(MIN, MAX, INIT);
		blueSL.setShowTickLabels(true);
		blueSL.setShowTickMarks(true);
		blueSL.valueProperty().bindBidirectional(blueDM.integerDataProperty());
		
		slidery.getChildren().addAll(r, redBX, redSL, g, greenBX, greenSL, b, blueBX, blueSL);
		slidery.setPadding(new Insets(5));
		slidery.setAlignment(Pos.CENTER_LEFT);
		return slidery;
	}
	
	
}

