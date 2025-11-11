package utils;
import servicios.FactoriaServicios;
import servicios.IServicioCategorias;


public class ProgramaCargarCategorias {
	
	public static void main(String[] args) {
		IServicioCategorias servicio =  FactoriaServicios.getServicio(IServicioCategorias.class);
		servicio.cargarCategorias("C:\\AADD\\segundum\\Categorias\\Arte_y_ocio.xml");
		
	}

}
