package dominio;

import javax.persistence.Embeddable;

/**
 * Representa un lugar de recogida para un producto.
 * Incluye una descripción y coordenadas geográficas.
 */
@Embeddable
public class LugarDeRecogida {

	/** Descripción del lugar de recogida */
	private String recogida;
	/** Longitud geográfica */
	private int longitud;
	/** Latitud geográfica */
	private int latitud;
	
	
	/**
	 * Constructor por defecto, para JPA.
	 */
	public LugarDeRecogida() {};
	
	
	
	
	public LugarDeRecogida(String recogida, int longitud, int latitud) {
		this.recogida = recogida;
		this.longitud = longitud;
		this.latitud = latitud;
	}
}