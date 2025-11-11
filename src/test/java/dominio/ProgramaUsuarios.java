package dominio;

import java.time.LocalDate;
import java.util.Optional;

import servicios.FactoriaServicios;
import servicios.IServicioUsuarios;

public class ProgramaUsuarios {

	public static void main(String[] args) {

		IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);
		
		//Creación de Usuario
		Optional<String> id = servicioUsuarios.registrarUsuario("Jesús", "Sánchez", "wjesus.sanchez@um.es", "123", LocalDate.of(2004, 8, 5), "123456789");
		System.out.println("ID: " + id.orElse("null"));
		servicioUsuarios.modificarUsuario(id.get(), null, "Sánchez Pardo", null, null, null, null);
			
	}

}