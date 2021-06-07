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

public class SeznamOsob extends Stage{
	
	private TableView<Osoba> tabulka;
	
	public void showDialog() {
		this.setTitle("Seznam osob - Lukas Runt");
		
		this.setScene(new Scene(getRoot()));
		
		this.show();
	}

	private Parent getRoot() {
		BorderPane rootBorderPane = new BorderPane();
		
		rootBorderPane.setCenter(getTabulka());
		
		return rootBorderPane;
	}

	private Node getTabulka() {
		tabulka = new TableView<Osoba>(Main.model.brigadnici);
		
		TableColumn<Osoba, String> jmenoColumn = new TableColumn<>("Jmeno");
		jmenoColumn.setCellValueFactory(new PropertyValueFactory<Osoba, String>("jmeno"));
		jmenoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<Osoba, Integer> pocetColunm = new TableColumn<>("Pocet");
		pocetColunm.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("pocet"));
		pocetColunm.setCellFactory(cellData -> new FormattedIntTableCell<>());
		
		tabulka.getColumns().addAll(jmenoColumn, pocetColunm);
		tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		return tabulka;
	}
}
