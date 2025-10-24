package dominio;
import javax.persistence.Lob;

import repositorios.Identificable;

/**
 * Representa una categoría de productos en la plataforma.
 * Permite organizar los productos en grupos temáticos, incluyendo subcategorías.
 */
public class Categoria{
	/** Identificador único de la categoría */
	private String id;
	/** Nombre de la categoría */
	private String nombre;
	/** Descripción de la categoría */
	@Lob
	private String descripcion;
	/** Ruta asociada a la categoría (puede ser utilizada para navegación o almacenamiento) */
	private String ruta;
	/** Subcategoría asociada, si existe */
	private Categoria subcategoria;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
		
	}
	
}