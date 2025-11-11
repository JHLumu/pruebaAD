package repositorios;

import java.util.List;

import dominio.Categoria;

public interface RepositorioCategoriasAdHoc {

	public List<Categoria> getCategoriasRaiz() throws RepositorioException;
	
	public List<Categoria> getDescendientes(Categoria categoriaPadre) throws RepositorioException;
	
}
