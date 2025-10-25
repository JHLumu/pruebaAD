package repositorios;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import dominio.Categoria;
import dominio.EstadoProducto;
import dominio.Producto;
import utils.EntityManagerHelper;
import utils.ProductoResumen;

public class RepositorioProductosAdHocJPA extends RepositorioProductosJPA implements RepositorioProductosAdHoc {

	@Override
	public List<ProductoResumen> getHistorialVentas(LocalDate fecha) throws RepositorioException {
		
		try {
			int month = fecha.getMonthValue();
			int year = fecha.getYear();
			EntityManager em = EntityManagerHelper.getEntityManager();
			String queryString = "SELECT p.titulo, p.precio, p.fecha, p.categoria, p.visualizaciones"
					+ "FROM Producto p"
					+ "WHERE MONTH(p.fecha) = month AND YEAR(p.fecha) = year ";
			TypedQuery<ProductoResumen> query = em.createQuery(queryString, ProductoResumen.class);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			
			return query.getResultList();
			
		}catch(RuntimeException e) {
			throw new RepositorioException("Error buscando historialVentas", e);
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}
	
	@Override
	public List<Producto> getByFiltros(Categoria categoria, String textoContenido, EstadoProducto estado,
			double precioMaximo) throws RepositorioException {

		try {
		
			EntityManager em = EntityManagerHelper.getEntityManager();
			
			String queryCategoria = "";
			if (categoria != null) queryCategoria = "AND (p.categoria == idCategoria OR "
					+ "p.categoria IN (SELECT c.subcategorias from Categoria c"
					+ "WHERE c.id == idCategoria))";
			
			String queryTexto = "";
			if (textoContenido != null && textoContenido.isEmpty()) queryTexto = "AND (p.descripcion LIKE CONCAT('%', :texto, '%'))";
			
			String queryEstado = "";
			if (estado != null) queryEstado = "AND p.estado == estado";
			
			String queryString = "SELECT p "
					+ "FROM Producto p"
					+ "WHERE 1=1"
					+ queryCategoria
					+ queryTexto
					+ queryEstado;
			
			TypedQuery<Producto> query = em.createQuery(queryString, Producto.class);
			if (!queryCategoria.isEmpty()) query.setParameter("idCategoria", categoria.getId());
			if (!queryTexto.isEmpty()) query.setParameter("texto", textoContenido);
			if (!queryEstado.isEmpty()) query.setParameter("estado", estado);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			
			return query.getResultList();
			
		}catch(RuntimeException e){
			throw new RepositorioException("Error buscando by filtros", e);
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}

}
