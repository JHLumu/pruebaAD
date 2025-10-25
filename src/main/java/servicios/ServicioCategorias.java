package servicios;

import java.util.List;

import dominio.Categoria;
import repositorios.FactoriaRepositorios;
import repositorios.RepositorioCategoriasJPA;
import repositorios.RepositorioException;
import utils.CargarCategorias;
import utils.ICargarCategorias;

public class ServicioCategorias implements IServicioCategorias{

	RepositorioCategoriasJPA repositorio = FactoriaRepositorios.getRepositorio(Categoria.class);
	
	@Override
	public boolean cargarCategorias(String ruta) {
		ICargarCategorias cargar = new CargarCategorias();
		Categoria categoria = cargar.cargarCategorias(ruta);
		if (categoria == null) return false;
		try {
			repositorio.add(categoria);
			return true;
		} catch (RepositorioException e) {
			System.out.println("Error a la hora de añadir la categoría");
			return false;
		}
	}

	@Override
	public boolean modificarCategoria(String idCategoria, String descripcion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Categoria> recuperarCategoriasRaiz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> recuperarCategoriasDescendentes(String idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

}
