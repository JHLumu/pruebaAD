package repositorios;

import java.time.LocalDate;
import java.util.List;

import dominio.Categoria;
import dominio.EstadoProducto;
import dominio.Producto;
import utils.ProductoResumen;

public interface RepositorioProductosAdHoc {

	public List<ProductoResumen> getHistorialVentas(LocalDate fecha) throws RepositorioException;
	
	public List<Producto> getByFiltros(Categoria categoria, String textoContenido, EstadoProducto estado, double precioMaximo) throws RepositorioException;
	
}
