package com.proyect.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Links extends Base{
	
	By linkLocator = By.tagName("a");

	public Links(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean revisionLink(){
		List<WebElement> link = findElements(linkLocator);
		String  url = "";                       //vamos guardando la url de Link
		//String  clase = "";    //YOOOOOOOO
		List<String> linksRotos = new ArrayList<String>();
		List<String> linksOk = new ArrayList<String>();
		
		HttpURLConnection conection = null;//permite realizar conexion http la inicializamos en null
		int errorCode = 200;         //inicializamos en 200 esto es para comparar el codigo de error que pueda enviar
		Iterator<WebElement> itera = link.iterator(); //va a ir recorriendo los links
		
		while (itera.hasNext()) {  //mientras haya mas enlaces
			url = itera.next().getAttribute("href"); // obtenga la url q esta en href y la guarda en (url)
			//clase = itera.next().getAttribute("class"); //YOOOOOOOOO
					
			if(url==null || url.isEmpty()) { //esto es para revisar la etiqueta a no tiene href o esta vacio
				System.out.println(url +  "No esta configurada o esta vacia"); // YOOOOOO puse clase
				continue;
			}
			try {//establecemos la conexion http en este bloque de codigo
				conection = (HttpURLConnection)(new URL(url).openConnection()); //convertimos lo q nos devulve a HttpURLConnection para abrir la conexion
				conection.setRequestMethod("HEAD"); //solicitamos el encabezado de la pagina para ver el codigo de estado
				conection.connect(); //establecemos la conexion
				errorCode = conection.getResponseCode(); //solicitamos codigo de respuesta
				
				if (errorCode>400) {// de 400 en adelante errores de conexion por el cliente o servidor
					System.out.println("ERROR link roto: -- "+ url);
					linksRotos.add(url); // vamos guardando en la lista linksrotos las url q presenten ese tipo de error
					
				} else {
					
					System.out.println("Enlaces Validos: -- "+ url);
					linksOk.add(url);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); // para imprimir en consola la excepcion que se envie para ver q es lo q esta mal
			}
			
		}
		
		System.out.println("Links validos "+ linksOk.size());
		System.out.println("Links Invalidos "+ linksRotos.size());
		
		if (linksRotos.size()>0) {
			System.out.println("****ERROR-----------------links rotos");
			for (int i = 0; i < linksRotos.size(); i++) {
				System.out.println(linksRotos.get(i)); //vamos a ir imprimiendo el enlace q este en esa posicion (i) que esta roto
			}
			
			return false;
			
		} else {
			return true;
		}
		
		
		
		
	}

}
