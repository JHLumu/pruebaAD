package repositorios;

import dominio.Usuario;
import dominio.dto.UsuarioDTO;

public interface RepositorioUsuariosAdHoc extends RepositorioString<Usuario> {

	public boolean checkEmailAndTelefono(String correo, String telefono) throws RepositorioException;
	
	public UsuarioDTO getByEmailAndPassword(String email, String clave) throws EntidadNoEncontrada, RepositorioException;	
}
