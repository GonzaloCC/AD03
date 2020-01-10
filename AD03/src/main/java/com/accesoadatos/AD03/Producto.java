package com.accesoadatos.AD03;

public class Producto {
	String identificador;
	String descripcion;
	double prezo;
	int cantidade;
	
	
	public Producto(String identificador, String descripcion, double prezo, int cantidade) {
		super();
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.prezo = prezo;
		this.cantidade = cantidade;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrezo() {
		return prezo;
	}
	public void setPrezo(double prezo) {
		this.prezo = prezo;
	}
	public int getCantidade() {
		return cantidade;
	}
	public void setCantidade(int cantidade) {
		this.cantidade = cantidade;
	}
	
    //Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"identificador\" : \"" + this.identificador + "\",";
        json = json + "\"descripcion\" : \"" + this.descripcion + "\",";
        json = json + "\"prezo\" : \"" + this.prezo + "\",";
        json = json + "\"cantidade\" : " + this.cantidade + " }";
        return json;
    }

	
	

}
