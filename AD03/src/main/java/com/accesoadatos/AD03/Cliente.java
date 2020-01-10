package com.accesoadatos.AD03;

public class Cliente {
	String nome;
	String apelidos;
	String email;
	
	
	public Cliente(String nome, String apelidos, String email) {
		super();
		this.nome = nome;
		this.apelidos = apelidos;
		this.email = email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    //Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"nome\" : \"" + this.nome + "\",";
        json = json + "\"apelidos\" : \"" + this.apelidos + "\",";
        json = json + "\"email\" : " + this.email + " }";
        return json;
    }
	
	

}
