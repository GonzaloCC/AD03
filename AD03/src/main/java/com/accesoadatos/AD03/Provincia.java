package com.accesoadatos.AD03;

public class Provincia {
	String id;
	String nome;
	
	
	public Provincia(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
