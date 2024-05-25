package dto;

public class Category {
	
	private int id, atributId;
	private String nazivKategorije, nazivAtributa;
	
	public Category() {	}

	public Category(int id, String nazivKategorije, int atributId, String nazivAtributa) {
		super();
		this.id = id;
		this.nazivKategorije = nazivKategorije;
		this.atributId = atributId;
		this.nazivAtributa = nazivAtributa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazivKategorije() {
		return nazivKategorije;
	}

	public void setNazivKategorije(String nazivKategorije) {
		this.nazivKategorije = nazivKategorije;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", nazivKategorije=" + nazivKategorije
				+ ", nazivAtributa=" + nazivAtributa + "]";
	}
	
}
