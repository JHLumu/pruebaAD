package utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dominio.Categoria;


public class CargarCategorias implements ICargarCategorias {

	public Categoria cargarCategorias(String ruta) {
		
		File f = new File(ruta);
		if (!f.exists()) return null;
		
		JAXBContext contexto;
		try {
			contexto = JAXBContext.newInstance(Categoria.class);
			Unmarshaller unmarsaller = contexto.createUnmarshaller();
			Categoria categoriasRaices = (Categoria) unmarsaller.unmarshal(f);
			return categoriasRaices;
		} catch (JAXBException e) {
			System.err.println("Error tratando de cargar las categorías del xml " + ruta);
			return null;
		}
		
	}
	
}
