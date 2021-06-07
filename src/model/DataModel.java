package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class DataModel {
	
	public ListProperty<Brigada> brigady = new SimpleListProperty<>(FXCollections.observableArrayList());
	public ListProperty<Osoba> brigadnici = new SimpleListProperty<>();
	public ListProperty<Strom> stromy = new SimpleListProperty<>();
}
