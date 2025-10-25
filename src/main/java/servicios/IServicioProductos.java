package servicios;

import java.time.LocalDate;
import java.util.List;

import dominio.Categoria;
import dominio.EstadoProducto;
import dominio.Producto;
import utils.ProductoResumen;

public interface IServicioProductos {

	public String registrarProducto(String titulo, String descripcion, double precio, EstadoProducto estado, String idCategoria, String disponibilidadEnvio, String IDVendedor);
	
	public String asignarLugarRecogida(String idProducto, int longitud, int latitud, String descripcion);
	
	public boolean modificarProducto(Producto producto, double precio, String descripcion);
	
	public boolean addVisualizacion(String idProducto);
	
	public List<ProductoResumen> getHistorialVentas(LocalDate fecha);
	
	public List<Producto> getProductoByFiltros(Categoria categoria, String textoContenido, EstadoProducto estado, double precioMaximo);
}
