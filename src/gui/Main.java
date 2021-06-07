package gui;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
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
		
		TableColumn<Brigada, LocalDate> 
		return null;
	}

	private Node getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
