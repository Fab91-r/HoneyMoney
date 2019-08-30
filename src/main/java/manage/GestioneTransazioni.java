package manage;

import java.util.ArrayList;
import java.util.List;

import models.Transazione;

public class GestioneTransazioni {

	private List <Transazione> transazioniPositive;
	private List <Transazione> transazioniNegative;
	private List<String> listaCategorie;
	

	public GestioneTransazioni() {

		this.transazioniPositive = new ArrayList<Transazione>();
		this.transazioniNegative = new ArrayList<Transazione>();
		this.listaCategorie = new ArrayList<String>();
		listaCategorie.add("Trasporto");
		listaCategorie.add("Bollette");
		listaCategorie.add("Alimentare");
		listaCategorie.add("Altro");
	}

	public String addCategoria(String categoria) {
		listaCategorie.add(categoria);

		return categoria;
	}

	public List<Transazione> getTransazioniPositive() {
		return transazioniPositive;
	}

	public void setTransazioniPositive(List<Transazione> transazioniPositive) {
		this.transazioniPositive = transazioniPositive;
	}

	public List<Transazione> getTransazioniNegative() {
		return transazioniNegative;
	}

	public void setTransazioniNegative(List<Transazione> transazioniNegative) {
		this.transazioniNegative = transazioniNegative;
	}

	public List<String> getListaCategorie() {
		return listaCategorie;
	}

	public void setListaCategorie(List<String> listaCategorie) {
		this.listaCategorie = listaCategorie;
	}


	
	
}
