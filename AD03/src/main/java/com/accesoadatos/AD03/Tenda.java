package com.accesoadatos.AD03;


import java.util.ArrayList;

public class Tenda  {
	String nome;
	String cidade;
	ArrayList<Producto> productos;
	ArrayList<Empregado> empregados;
	
	public Tenda(String nome, String cidade) {
		this.nome = nome;
		this.cidade = cidade;
		productos=new ArrayList<Producto>();
		empregados=new ArrayList<Empregado>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public ArrayList<Empregado> getEmpregados() {
		return empregados;
	}
	public void setEmpregados(ArrayList<Empregado> empregados) {
		this.empregados = empregados;
	}
	
    //Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"nome\" : \"" + this.nome + "\",";
        json = json + "\"cidade\" : \"" + this.cidade + "\",";
        json = json + "\"productos\" : \"" + this.productos + "\",";
        json = json + "\"empregados\" : " + this.empregados + " }";
        return json;
    }


}
