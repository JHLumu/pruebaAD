package repositorios;

import dominio.Usuario;
import dominio.dto.UsuarioDTO;

public interface RepositorioUsuariosAdHoc extends RepositorioString<Usuario> {

	public boolean checkEmail(String correo) throws RepositorioException;
	
	public UsuarioDTO getByEmailAndPassword(String email, String clave) throws EntidadNoEncontrada, RepositorioException;	
}
