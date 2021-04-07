package cv07;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Trida vytvari instance pisem
 * @author Lukas Runt
 * @version 1.0 (2021-04-06)
 */
public class Pismo {
	
	private StringProperty font = new SimpleStringProperty();
	private ObjectProperty<Color> barva = new SimpleObjectProperty<>();
	private ObjectProperty<RezPisma> rez = new SimpleObjectProperty<>();
	private IntegerProperty velikost = new SimpleIntProperty();
	private BooleanProperty viditelnost = new SimpleBooleanProperty();
	/** nahled pisma*/
	private ObjectBinding<Label> vysledek = new ObjectBinding<Label>() {
		{
			bind(font, barva, rez, velikost, viditelnost);
		}
		@Override
		protected Label computeValue() {
			if ((font.getValue() != null) && (barva.getValue() != null) && (rez.getValue() != null) && (velikost.getValue() != null) && (viditelnost.getValue() != null)) {
				Label pismo = new Label("Example");
				pismo.setTextFill(barva.getValue());
				if(rez.getValue().equals(RezPisma.ITALIC)) {
					pismo.setFont(Font.font(font.getValue(), FontPosture.ITALIC, velikost.getValue()));
				}
				else if(rez.getValue().equals(RezPisma.BOLD)) {
					pismo.setFont(Font.font(font.getValue(), FontWeight.BOLD, velikost.getValue()));
				}
				else {
					pismo.setFont(Font.font(font.getValue(), FontWeight.NORMAL, velikost.getValue()));
				}
				pismo.setVisible(viditelnost.getValue());
				return pismo;
			}
			else {
				return null;
			}
		}
	};
	
	public Pismo(String font, Color barva, RezPisma rez, int velikost, boolean viditelnost) {
		setFont(font);
		setBarva(barva);
		setRez(rez);
		setVelikost(velikost);
		setViditelnost(viditelnost);
	}
	//------------------font-----------------------------
	public void setFont(String novyFont) {
		font.set(novyFont);
	}
	
	public String getFont() {
		return font.get();
	}
	
	public StringProperty fontProperty() {
		return font;
	}
	//----------------barva---------------------------------
	public void setBarva(Color novaBarva) {
		barva.set(novaBarva);
	}
	
	public Color getBarva() {
		return barva.get();
	}
	
	public ObjectProperty<Color> barvaProperty(){
		return barva;
	}
	//----------------rez-----------------------------------
	public void setRez(RezPisma novyRez) {
		rez.setValue(novyRez);
	}
	
	public RezPisma getRez() {
		return rez.get();
	}
	
	public ObjectProperty<RezPisma> rezProperty(){
		return rez;
	}
	//----------------velikost------------------------------
	public void setVelikost(int novaVelikost) {
		velikost.setValue(novaVelikost);
	}
	
	public int getVelikost() {
		return velikost.get();
	}
	
	public IntegerProperty velikostProperty() {
		return velikost;
	}
	//----------------viditelnost---------------------------
	public void setViditelnost(Boolean novaViditelnost) {
		viditelnost.setValue(novaViditelnost);
	}
	
	public boolean getViditelnost() {
		return viditelnost.get();
	}
	
	public BooleanProperty viditelnostProperty() {
		return viditelnost;
	}
	
	//--------------nahled-----------------------------------
	
	public ObjectBinding<Label> textProperty() {
		return vysledek;
	}
	
	//-------------tostring-----------------------------------
	public String toString() {
		return String.format("%s [%d]", font.getValue(), velikost.getValue());
	}
}
