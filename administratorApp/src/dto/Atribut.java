package dto;

public class Atribut {
	private int id;
	private String attributeName;

	public Atribut() { }

	public Atribut(int id, String attributeName) {
		super();
		this.id = id;
		this.attributeName = attributeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	
}
