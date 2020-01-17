package com.accesoadatos.AD03;

import java.util.ArrayList;
import java.util.Scanner;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File baseDatos=new File("BaseDeDatos.db");
		
		if(!baseDatos.isFile()) {
			ManexoSQLite.createDatabase("BaseDeDatos.db");
			Connection con =  ManexoSQLite.connectDatabase("BaseDeDatos.db");
			ManexoSQLite.createTableProvincias(con);
			ManexoSQLite.insertProvincias(con);
			ManexoSQLite.createTableTenda(con);
			ManexoSQLite.createTableClientes(con);
			ManexoSQLite.createTableEmpregados(con);
			ManexoSQLite.createTableProducto(con);
			ManexoSQLite.createTableProductoTenda(con);
			ManexoSQLite.createTableEmpregadosTenda(con);
			ManexoSQLite.desconnetDatabase(con);
		}
		 
		Connection con =  ManexoSQLite.connectDatabase("BaseDeDatos.db");
		ManexoSQLite.vacumm(con);
		Scanner entrada= new Scanner(System.in);
		int eleccion;
		 
		// ManexoSQLite.insertProvincias(con);
		
		do {
		System.out.println("1-Engadir unha nova tenda.\r\n" + 
				"2-Mostrar as tendas.\r\n" + 
				"3-Eliminar unha tenda.\r\n" + 
				"4-Engadir un producto.\r\n" + 
				"5-Mostrar os productos da franquicia.\r\n" + 
				"6-Mostrar os productos dunha tenda.\r\n" + 
				"7-Engadir un producto a unha tenda.\r\n" + 
				"8-Actualizar o stock dun producto nunha determinada tenda.\r\n" + 
				"9-Mostrar o stock dun producto dunha tenda.\r\n" + 
				"10-Eliminar un producto dunha determinada tenda.\r\n" + 
				"11-Eliminar un producto.\r\n" + 
				"12-Engadir un cliente.\r\n" + 
				"13-Mostrar os clientes\r\n" + 
				"14-Eliminar un cliente.\r\n" + 
				"15-Ler os titulares do periódico El Pais. (Explícase máis abaixo)\r\n" + 
				"16-Sair da aplicación");
		
		eleccion= entrada.nextInt();
		String salto= entrada.nextLine();
		switch(eleccion) {
		case 1:
			ManexoSQLite.anadirTenda(con);
			break;
		case 2:
			ManexoSQLite.printTendas(con);
			break;
		case 3:
			ManexoSQLite.deleteTenda(con);
			break;
		case 4:
			ManexoSQLite.anadirProducto(con);
			break;
		case 5:
			ManexoSQLite.printProductos(con);
			break;
		case 6:
			System.out.println("Debe elixir tenda");
			ManexoSQLite.printTendas(con);
	     	System.out.println("Inserte  numero da tenda");
	     	int nTendas=ManexoSQLite.numeroTendas(con);
	     	String numerotenda=entrada.nextLine();
	     	String nTen=ManexoSQLite.selectTenda(con,numerotenda);
			ManexoSQLite.printProductosTenda(con,nTen);
			break;
		case 7:
			ManexoSQLite.anadirProductoTenda(con);
			break;
		case 8:
			System.out.println("Debe elixir tenda");
			ManexoSQLite.printTendas(con);
	     	System.out.println("Inserte  numero da tenda");
	     	int nTendas2=ManexoSQLite.numeroTendas(con);
	     	String numerotenda2=entrada.nextLine();
	     	if(Integer.parseInt(numerotenda2)>0 && Integer.parseInt(numerotenda2)<=nTendas2) {
	     	String nTen2=ManexoSQLite.selectTenda(con,numerotenda2);
	    	ManexoSQLite.actualizarStock(con,nTen2);
	     	}else {
	     		System.out.println("numero erroneo");
	     	}
			break;
		case 9:
			System.out.println("Debe elixir tenda");
			ManexoSQLite.printTendas(con);
	     	System.out.println("Inserte  numero da tenda");
	     	int nTendas3=ManexoSQLite.numeroTendas(con);
	     	String numerotenda3=entrada.nextLine();
	     	if(Integer.parseInt(numerotenda3)>0 && Integer.parseInt(numerotenda3)<=nTendas3) {
	     	String nTen3=ManexoSQLite.selectTenda(con,numerotenda3);
	    	ManexoSQLite.mostrarStock(con,nTen3);
	     	}else {
	     		System.out.println("Numero erroneo");
	     	}
			break;
		case 10:
			ManexoSQLite.eliminarProductoTenda(con);
			break;
		case 11:
			ManexoSQLite.deleteProducto(con);
			break;
		case 12:
			ManexoSQLite.anadirCliente(con);
			break;
		case 13:
			ManexoSQLite.printClientes(con);
			break;
		case 14:
			ManexoSQLite.deleteCliente(con);
			break;
		case 15:
			 XMLReader procesadorXML = null;
			 
			 try {

		            //Creamos un parseador de texto e engadimoslle a nosa clase que vai parsear o texto
		            procesadorXML = XMLReaderFactory.createXMLReader();
		            TitularesXML titularesXML = new TitularesXML();
		            procesadorXML.setContentHandler(titularesXML);

		            //Indicamos o texto donde estan gardadas as persoas
		            InputSource arquivo = new InputSource("http://ep00.epimg.net/rss/elpais/portada.xml");
		            procesadorXML.parse(arquivo);

		            //Imprimimos os datos lidos no XML
		            ArrayList<Titular> titulares = titularesXML.getTitulares();
		            
		            for(int i=0;i<titulares.size();i++){
		                Titular titularAux = titulares.get(i);
		                System.out.println("Titular "+(i+1)+": " + titularAux.getTexto() );
		            }

		        } catch (SAXException e) {
		            System.out.println("Ocurriu un erro ao ler o arquivo XML");
		        } catch (IOException e) {
		            System.out.println("Ocurriu un erro ao ler o arquivo XML");
		        }
			break;
		case 16:
			ManexoSQLite.desconnetDatabase(con);
			System.exit(0);
			break;
		default:
			break;
		}
		}while(eleccion!=16);
		
	}
	
	
	
}
