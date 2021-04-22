package cv08;

public enum SouborTyp {
	SOUBOR,
	ADRESAR;
	
	public String getSymbol() {
		switch (this) {
		case SOUBOR:
			return "📄";
		case ADRESAR:
			return "📁";
		}
		return "\u1F643";
	}
}
