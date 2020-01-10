package com.accesoadatos.AD03;

import java.util.ArrayList;

public class Compania {
	ArrayList<Tenda> tendas ;
	ArrayList<Cliente> clientes;
	
	public Compania( ) {
		tendas = new ArrayList<Tenda>();
		clientes = new ArrayList <Cliente>();
		}
	public ArrayList<Tenda> getTendas() {
		return tendas;
	}
	public void setTendas(ArrayList<Tenda> tendas) {
		this.tendas = tendas;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	

	

}
