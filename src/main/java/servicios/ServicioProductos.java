package servicios;

import java.time.LocalDate;
import java.util.List;

import dominio.Categoria;
import dominio.EstadoProducto;
import dominio.Producto;
import dominio.Usuario;
import repositorios.FactoriaRepositorios;
import repositorios.RepositorioProductosJPA;
import repositorios.RepositorioUsuariosJPA;
import utils.ProductoResumen;

public class ServicioProductos implements IServicioProductos{

	public RepositorioProductosJPA repositorioProductos = FactoriaRepositorios.getRepositorio(Producto.class);
	//public RepositorioCategoriasJPA repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);
	
	@Override
	public String registrarProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			String idCategoria, String disponibilidadEnvio, String IDVendedor) {
		
		if (titulo == null || titulo.isEmpty() || descripcion == null || descripcion.isEmpty() || estado == null ||
			idCategoria == null || idCategoria.isEmpty() || disponibilidadEnvio == null || disponibilidadEnvio.isEmpty() ||
			IDVendedor == null || IDVendedor.isEmpty()) return null;
		
			
			//if (repositorioCategorias.getById(idCategoria))
		
		return null;
	}

	@Override
	public String asignarLugarRecogida(String idProducto, int longitud, int latitud, String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificarProducto(Producto producto, double precio, String descripcion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addVisualizacion(String idProducto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductoResumen> getHistorialVentas(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getProductoByFiltros(Categoria categoria, String textoContenido, EstadoProducto estado,
			double precioMaximo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}