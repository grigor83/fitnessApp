package dto;

public class Atribut {
	private int atributId;
	private String nazivAtributa;

	public Atribut() { }

	public Atribut(int atributId, String nazivAtributa) {
		super();
		this.atributId = atributId;
		this.nazivAtributa = nazivAtributa;
	}

	public int getAtributId() {
		return atributId;
	}

	public void setAtributId(int atributId) {
		this.atributId = atributId;
	}

	public String getNazivAtributa() {
		return nazivAtributa;
	}

	public void setNazivAtributa(String nazivAtributa) {
		this.nazivAtributa = nazivAtributa;
	}
	
}
