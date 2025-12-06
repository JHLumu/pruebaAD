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
	public boolean checkEmail(String correo) throws RepositorioException {
		
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			String queryString = "SELECT COUNT(u)"
					+ " FROM Usuario u "
					+ " WHERE u.correo = :correo";
			
			TypedQuery<Long> query = em.createQuery(queryString, Long.class);
			query.setParameter("correo", correo);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			Long resultado = query.getSingleResult();
			return resultado.equals(0L);
			
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
			+ " WHERE u.correo = :email AND u.clave = :clave";
			TypedQuery<UsuarioDTO> query = em.createQuery(queryString, UsuarioDTO.class);
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
