package dominio;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import repositorios.Identificable;

/**
 * Representa un producto publicado en la plataforma.
 * Contiene información relevante como título, descripción, precio, estado, fecha de publicación, categoría, visualizaciones,
 * disponibilidad de envío, lugar de recogida y vendedor.
 */
@Entity
public class Producto implements Identificable {

	/** Identificador único del producto */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;
	/** Título del producto */
	private String titulo;
	/** Descripción detallada del producto */
	@Lob
	private String descripcion;
	/** Precio del producto */
	private double precio;
	/** Estado actual del producto */
	@Enumerated(EnumType.STRING)
	private EstadoProducto estado;
	/** Fecha en la que se publicó el producto */
	private LocalDateTime fechaPublicacion;
	/** Categoría a la que pertenece el producto */
	private int categoria;
	/** Número de visualizaciones del producto */
	private int visualizaciones;
	/** Indica si el envío está disponible para el producto */
	private boolean envioDisponible;
	/** Lugar de recogida del producto */
	@Embedded
	private LugarDeRecogida recogida;
	/** Usuario que vende el producto */
	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Usuario vendedor;
	
	/**
	 * Constructor sin argumentos, utilizado por JPA.
	 */
	public Producto() {}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}
	
}