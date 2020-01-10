package com.accesoadatos.AD03;

public class Empregado {
	String nome;
	String apelidos;
	int horas;
	
	
	public Empregado(String nome, String apelidos) {
		super();
		this.nome = nome;
		this.apelidos = apelidos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelidos() {
		return apelidos;
	}
	public void setApelidos(String apelidos) {
		this.apelidos = apelidos;
	}
	
	

	//Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"nome\" : \"" + this.nome + "\",";
        json = json + "\"apelidos\" : " + this.apelidos + " }";
        return json;
    }
	
	

}
