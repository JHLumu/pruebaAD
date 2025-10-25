package dominio;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import repositorios.Identificable;

/**
 * Representa una categoría de productos en la plataforma.
 * Permite organizar los productos en grupos temáticos, incluyendo subcategorías.
 */
@Entity
public class Categoria implements Identificable{
	/** Identificador único de la categoría */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	/** Nombre de la categoría */
	private String nombre;
	/** Descripción de la categoría */
	@Lob
	private String descripcion;
	/** Ruta asociada a la categoría (puede ser utilizada para navegación o almacenamiento) */
	private String ruta;
	/** Subcategoría asociada, si existe */
	
	@ManyToOne
	@JoinColumn(name="categoria_padre_id")
	private Categoria categoriaPadre;
	
	@OneToMany(mappedBy="categoriaPadre", cascade=CascadeType.ALL)
	private List<Categoria> subcategorias;
	
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;	
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
		
	}
	
}