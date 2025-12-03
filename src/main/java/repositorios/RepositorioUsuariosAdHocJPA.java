package repositorios;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import dominio.dto.UsuarioDTO;
import utils.EntityManagerHelper;


public class RepositorioUsuariosAdHocJPA extends RepositorioUsuariosJPA implements RepositorioUsuariosAdHoc  {

	@Override
	public boolean checkEmailAndTelefono(String correo, String telefono) throws RepositorioException {
		
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			String queryString = "SELECT id"
					+ "FROM Usuario u "
					+ "WHERE u.correo == :correo AND u.telefono == :telefono";
			
			TypedQuery<String> query = em.createNamedQuery(queryString, String.class);
			query.setParameter("correo", correo);
			query.setParameter("telefono", telefono);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.getSingleResult();
			//En caso de que getSingleResult no haya encontrado nada o haya encontrado más de un valor, lanza excepciones.
			return true;
		
		}catch(NoResultException e) {
			return false;
			
		}catch(RuntimeException e) {
			throw new RepositorioException("Error buscando usuarios por correo y teléfono", e);
		
		}finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}

	@Override
	public UsuarioDTO getByEmailAndPassword(String email, String clave) throws EntidadNoEncontrada, RepositorioException {
		
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			String queryString = "SELECT NEW dominio.dto.UsuarioDTO(u.id, u.nombre, u.apellidos, u.correo, "
			+ "u.fechaNacimiento, u.telefono, u.esAdministrador)"
			+ " FROM Usuario u" 
			+ "WHERE u.email == :email AND u.clave == :clave";
			TypedQuery<UsuarioDTO> query = em.createNamedQuery(queryString, UsuarioDTO.class);
			query.setParameter("email", email);
			query.setParameter("clave", clave);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			return query.getSingleResult();
			
		}catch(NoResultException e) {
			throw new EntidadNoEncontrada("No se ha encontrado al usuario con el correo" + email + " y contraseña " + clave);
		}catch(RuntimeException e) {
			throw new RepositorioException("Error buscando usuarios por correo y teléfono", e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}

}
