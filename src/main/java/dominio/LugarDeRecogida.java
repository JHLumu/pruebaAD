package dominio;

import javax.persistence.Embeddable;

/**
 * Representa un lugar de recogida para un producto.
 * Incluye una descripción y coordenadas geográficas.
 */
@Embeddable
public class LugarDeRecogida {

	/** Descripción del lugar de recogida */
	private String descripcion;
	/** Longitud geográfica */
	private int longitud;
	/** Latitud geográfica */
	private int latitud;
}