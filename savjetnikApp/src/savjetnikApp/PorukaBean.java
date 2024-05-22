package savjetnikApp;

import java.io.Serializable;
import org.json.JSONObject;

public class PorukaBean implements Serializable {

	private static final long serialVersionUID = -6222944152142327826L;
	private int id;
	private SavjetnikBean posiljalac;
	private String tekst, datumSlanja;
	private boolean procitana;

	public PorukaBean() {
		
	}
	
	public PorukaBean(int id, SavjetnikBean posiljalac, String tekst, String datumSlanja, boolean procitana) {
		super();
		this.id = id;
		this.posiljalac = posiljalac;
		this.tekst = tekst;
		this.datumSlanja = datumSlanja;
		this.procitana = procitana;
	}

	public PorukaBean(int id, JSONObject jsonObject, String tekst, String datumSlanja, boolean procitana) {
		super();
		this.id = id;
		this.posiljalac = new SavjetnikBean(jsonObject.getInt("korisnikId"), jsonObject.getString("ime"), 
				jsonObject.getString("korisnickoIme"), null, jsonObject.getString("mejl"), jsonObject.getBoolean("verifikovan"));
		this.tekst = tekst;
		this.datumSlanja = datumSlanja;
		this.procitana = procitana;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SavjetnikBean getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(SavjetnikBean posiljalac) {
		this.posiljalac = posiljalac;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getDatumSlanja() {
		return datumSlanja;
	}

	public void setDatumSlanja(String datumSlanja) {
		this.datumSlanja = datumSlanja;
	}

	public boolean isProcitana() {
		return procitana;
	}

	public void setProcitana(boolean procitana) {
		this.procitana = procitana;
	}

	@Override
	public String toString() {
		return "PorukaBean [id=" + id + ", posiljalac.userName=" + posiljalac.getUserName() + ", posiljalac.email=" + posiljalac.getEmail() 
		+ ", tekst=" + tekst + ", datumSlanja=" + datumSlanja + ", procitana=" + procitana + "]";
	}

}
