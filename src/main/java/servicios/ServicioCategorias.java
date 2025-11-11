package servicios;

import java.util.List;

import dominio.Categoria;
import dominio.Usuario;
import repositorios.EntidadNoEncontrada;
import repositorios.FactoriaRepositorios;
import repositorios.RepositorioCategoriasJPA;
import repositorios.RepositorioUsuariosJPA;
import repositorios.RepositorioException;
import utils.CargarCategorias;
import utils.ICargarCategorias;

public class ServicioCategorias implements IServicioCategorias{

	RepositorioCategoriasJPA repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);
	RepositorioUsuariosJPA repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);
	
	@Override
	public boolean cargarCategorias(String ruta, String idUsuario) {
		
		
		try {
			Usuario usuario = repositorioUsuarios.getById(idUsuario);
			if (!usuario.esAdministrador()) return false;
			
			ICargarCategorias cargar = new CargarCategorias();
			Categoria raiz = cargar.cargarCategorias(ruta);
			if (raiz == null) return false;
			
			repositorioCategorias.add(raiz);
			return true;
			
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
			return false;
		} catch (RepositorioException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modificarCategoria(String idCategoria, String descripcion, String idUsuario) {
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
