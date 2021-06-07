package gui;

import bunky.FormattedIntTableCell;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Osoba;
import model.Strom;

public class SeznamStromu extends Stage{

	private TableView<Strom> tabulka;
	
	public void showDialog() {
		this.setTitle("Seznam stromu - Lukas Runt");
		
		this.setScene(new Scene(getRoot()));
		
		this.show();
	}

	private Parent getRoot() {
BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setCenter(getTabulka());
		
		return rootBorderPane;
	}

	private Node getTabulka() {
		tabulka = new TableView<Strom>(Main.model.stromy);
		
		TableColumn<Strom, String> jmenoColumn = new TableColumn<>("Strom");
		jmenoColumn.setCellValueFactory(new PropertyValueFactory<Strom, String>("nazev"));
		jmenoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<Strom, Integer> pocetColunm = new TableColumn<>("Pocet");
		pocetColunm.setCellValueFactory(new PropertyValueFactory<Strom, Integer>("pocet"));
		pocetColunm.setCellFactory(cellData -> new FormattedIntTableCell<>());
		
		tabulka.getColumns().addAll(jmenoColumn, pocetColunm);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}
		
}
