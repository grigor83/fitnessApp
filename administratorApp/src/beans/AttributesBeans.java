package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dto.Atribut;

public class AttributesBeans implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Atribut> atributes;
	
	
	public ArrayList<Atribut> getAtributes() {
		return atributes;
	}
	
	public void setAtributes(ArrayList<Atribut> atributes) {
		this.atributes = atributes;
	}

}
