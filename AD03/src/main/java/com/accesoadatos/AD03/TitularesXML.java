package com.accesoadatos.AD03;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class TitularesXML extends DefaultHandler {

	//Aqui imos gardar os datos de todalos titulares do XML
    private ArrayList<Titular> titulares;

    //E un atributo auxiliar para ir gardando os datos dos Titulares do XML
    private Titular titularAux;

    //E un atributo auxiliar po texto que hai entre as etiquetas
    private String cadeaTexto;

    public TitularesXML(){
        super();
    }
    
    /*
    Este e o metodo que se executa ao comezo, antes de parsear nada
    Non o imos utilizar neste caso, poderiase utilizar se fose necesario
     */
    @Override
    public void startDocument() throws SAXException {}
    
    /*
    Este e o m�todo que se executa ao finalizar o  parseo
    Non o imos utilizar neste caso, poderiase utilizar se fose necesario
     */
    @Override
    public void endDocument() throws SAXException {}
    
    /*
    Este metodo executase ao comezar a ler unha etiqueta
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        //Se atopamos a etiqueta channel creamos un novo arrayList
        if(localName == "channel"){
            this.titulares = new ArrayList<Titular>();
        }
        //Se atopamos a etiqueta title, creamos o obxecto auxiliar de Titulares onde gardaremos todolos datos
        else if(localName == "title"){
            this.titularAux = new Titular();
        }
    }
	
    /*
    Este m�todo executase cando se finaliza de ler unha etiqueta
     */
    @Override
    public void endElement(String uri, String localName,String qName) throws SAXException {
        //Finalizamos de ler a etiqueta title, polo que podemos gardar o texto que hai entre as etiquetas
        if(localName == "title"){
            this.titularAux.setTexto(cadeaTexto);
        }

        //Finalizamos de ler a etiqueta item, polo que podemos gardar o obxecto auxiliar de Titular no ArrayList
        else if(localName == "item"){
            this.titulares.add(this.titularAux);
        }
    }
    
    /*
    Este metodo executase cando se le unha cadea de texto
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //Gardamos todo o texto entre caracteres na cadea de texto auxiliar
        this.cadeaTexto = new String(ch,start,length);
    }
    
    public ArrayList<Titular> getTitulares() {
        return titulares;
    }
	
}
