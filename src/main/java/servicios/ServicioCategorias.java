package servicios;

import java.util.List;

import dominio.Categoria;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.RepositorioCategoriasAdHocJPA;
import repositorios.RepositorioException;
import utils.CargarCategorias;
import utils.ICargarCategorias;

public class ServicioCategorias implements IServicioCategorias{

	RepositorioCategoriasAdHocJPA repositorio = FactoriaRepositorios.getRepositorio(Categoria.class);
		
	@Override
	public boolean cargarCategorias(String ruta) {
		ICargarCategorias cargar = new CargarCategorias();
		Categoria raiz = cargar.cargarCategorias(ruta);
		if (raiz == null) return false;
		
		try {
			try {
				repositorio.getById(raiz.getId());

				System.err.println("La categoría principal " + raiz.getId() + " ya existe. No se cargará.");
				return false; 
			} catch (EntidadNoEncontrada e) {
				repositorio.add(raiz);
				return true;
			}
		} catch (RepositorioException e) {
			System.err.println("Error al añadir la categoría: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modificarCategoria(String idCategoria, String descripcion) {
		try {
			Categoria categoria = repositorio.getById(idCategoria);
			categoria.setDescripcion(descripcion);
	
			repositorio.update(categoria);
			
			return true;
			
		} catch (EntidadNoEncontrada e) {
			System.err.println("Error al modificar: No se encontró la categoría con id " + idCategoria);
			e.printStackTrace();
			return false;
		} catch (RepositorioException e) {
			System.err.println("Error de repositorio al modificar la categoría: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Categoria> recuperarCategoriasRaiz() {
		try {
			return repositorio.getCategoriasRaiz();
		} catch (RepositorioException e) {
			System.err.println("Error al recuperar categorías raíz: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Categoria> recuperarCategoriasDescendentes(String idCategoria) {
		try {
			Categoria categoriaPadre = repositorio.getById(idCategoria);
			return repositorio.getDescendientes(categoriaPadre);
			
		} catch (EntidadNoEncontrada e) {
			System.err.println("Error al buscar descendientes: No se encontró la categoría con id " + idCategoria);
			e.printStackTrace();
			return null;
		} catch (RepositorioException e) {
			System.err.println("Error de repositorio al buscar descendientes: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
