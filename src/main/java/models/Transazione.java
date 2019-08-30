package models;

public class Transazione {

	private String data;
	private String descrizione;
	private String categoria;
	private int importo;
	
	public Transazione(String data, String descrizione, String categoria, int importo) {
		this.data = data;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.importo = importo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getImporto() {
		return importo;
	}

	public void setImporto(int importo) {
		this.importo = importo;
	}

	@Override
	public String toString() {
		return "Transazione [data=" + data + ", descrizione=" + descrizione + ", categoria=" + categoria + ", importo="
				+ importo + "]";
	}
	
	
}
