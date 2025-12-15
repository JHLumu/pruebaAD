package dominio.web;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.Categoria;
import dominio.EstadoProducto;
import servicios.FactoriaServicios;
import servicios.IServicioCategorias;
import servicios.IServicioProductos;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CrearProductoWeb implements Serializable{

	private String titulo;
	private String descripcion;
	private double precio;
	private int estado;
	private boolean envio;
	private String categoria;
	private String subcategoria;
	private String vendedor;
	private String ubicacionDescripcion;
	private Double latitud;
	private Double longitud;
	
	@Inject
	private AutenticacionWeb beanAutenticacion; //Necesario para recuperar el id del usuario actual
	
	@Inject
	private FacesContext facesContext;
	
	private IServicioCategorias servicioCategorias;
	private IServicioProductos servicioProductos;
	
	public CrearProductoWeb() {
		this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
		this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
	};
	

	public void publicarProducto() {
		vendedor = beanAutenticacion.getId();
		if (vendedor == null || vendedor.isEmpty()) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debes iniciar sesión para publicar un producto."));
			return;
		} 
		
		Optional<String> resultado = servicioProductos.registrarProducto(titulo, descripcion, precio , EstadoProducto.values()[estado], categoria, Boolean.toString(envio), vendedor);
		
		if (resultado.isPresent()) {
			
			String idProducto = resultado.get();
			
			if (ubicacionDescripcion != null && !ubicacionDescripcion.trim().isEmpty()) {
	            double lat = (latitud != null) ? latitud : 0.0;
	            double lon = (longitud != null) ? longitud : 0.0;
	            
	            boolean ubicacionGuardada = servicioProductos.asignarLugarRecogida(idProducto, ubicacionDescripcion, lon, lat);
	            
	            if (!ubicacionGuardada) {
	                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Producto creado, pero hubo un error al guardar la ubicación."));
	            }
	        }
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Se ha publicado el producto con éxito."));
		}
		
		else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Publicación fallida."
					+ " Por favor, revise los campos."));
			
		}
		
		
		
	}
	
	public List<Categoria> completarCategoria(String query) {
		String queryLowerCase = query.toLowerCase();
		return servicioCategorias.recuperarCategoriasRaiz().stream()
				.filter(c -> c.getNombre().contains(queryLowerCase))
				.toList();
		
	}
	
	
	public List<Categoria> completarSubcategoria(String query) {
		String queryLowerCase = query.toLowerCase();
		return servicioCategorias.recuperarCategoriasDescendentes(categoria).stream()
				.filter(c -> c.getNombre().contains(queryLowerCase))
				.toList();
					
	}
	
	
	public String getTitulo() {
		System.out.println("getTitulo() called, value=" + titulo);
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		System.out.println("getDescripcion() called, value=" + descripcion);
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		System.out.println("getPrecio() called, value=" + precio);
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getEstado() {
		System.out.println("getEstado() called, value=" + estado);
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public boolean getEnvio() {
		System.out.println("getEnvio() called, value=" + envio);
		return this.envio;
	}
	
	public void setEnvio(boolean envio) {
		this.envio = envio;
	}
	
	public String getCategoria() {
		System.out.println("getCategoria() called, value=" + categoria);
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getSubcategoria() {
		System.out.println("getSubategoria() called, value=" + subcategoria);
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
	public String getUbicacionDescripcion() {
        return ubicacionDescripcion;
    }

    public void setUbicacionDescripcion(String ubicacionDescripcion) {
        this.ubicacionDescripcion = ubicacionDescripcion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

	
	
}
