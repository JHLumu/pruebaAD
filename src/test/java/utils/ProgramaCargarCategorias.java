package utils;
import dominio.Categoria;
import servicios.FactoriaServicios;
import servicios.IServicioCategorias;
import servicios.IServicioUsuarios;

public class ProgramaCargarCategorias {
	
	public static void main(String[] args) {
		IServicioCategorias servicio =  FactoriaServicios.getServicio(IServicioCategorias.class);
		servicio.cargarCategorias("C:\\AADD\\segundum\\Categorias\\Arte_y_ocio.xml");
	}

}
