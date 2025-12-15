package dominio.web;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dominio.Producto;
import dominio.dto.ProductoDTO;
import servicios.FactoriaServicios;
import servicios.IServicioProductos;

@Named
@ViewScoped
public class MisProductosWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProductoDTO> productos;
    
    private ProductoDTO productoEditar;
    
    // CORRECCIÓN: Usamos 'Double' (objeto) en lugar de 'double' (primitivo)
    // para evitar el NullPointerException en el InputNumber.
    private Double precioEdicion; 
    
    private String descripcionEdicion;

    @Inject
    private AutenticacionWeb autenticacionWeb;
    
    @Inject
    private FacesContext facesContext;

    private IServicioProductos servicioProductos;

    public MisProductosWeb() {
        this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
    }

    @PostConstruct
    public void init() {
        cargarProductos();
    }
    
    public void cargarProductos() {
        if (autenticacionWeb != null && autenticacionWeb.getId() != null) {
            this.productos = servicioProductos.getProductosEnVenta(autenticacionWeb.getId());
        }
    }

    public void prepararEdicion(ProductoDTO producto) {
        this.productoEditar = producto;
        
        Optional<Producto> p = servicioProductos.recuperarProducto(String.valueOf(producto.getId()));
        
        if (p.isPresent()) {
            this.precioEdicion = p.get().getPrecio();
            this.descripcionEdicion = p.get().getDescripcion();
        } else {
            this.precioEdicion = producto.getPrecio();
            this.descripcionEdicion = "";
        }
    }

    public void guardarEdicion() {
        if (productoEditar == null) return;

        // Evitamos pasar un null al servicio si el campo se dejó vacío
        double precioFinal = (precioEdicion != null) ? precioEdicion : 0.0;

        boolean exito = servicioProductos.modificarProducto(
            String.valueOf(productoEditar.getId()), 
            precioFinal, 
            descripcionEdicion
        );

        if (exito) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto actualizado correctamente."));
            cargarProductos(); 
            org.primefaces.PrimeFaces.current().executeScript("PF('editDialog').hide();");
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el producto."));
        }
    }

    // --- Getters y Setters ---

    public List<ProductoDTO> getProductos() { return productos; }
    
    public ProductoDTO getProductoEditar() { return productoEditar; }
    public void setProductoEditar(ProductoDTO productoEditar) { this.productoEditar = productoEditar; }
    
    // Getter corregido a Double
    public Double getPrecioEdicion() { return precioEdicion; }
    // Setter corregido a Double
    public void setPrecioEdicion(Double precioEdicion) { this.precioEdicion = precioEdicion; }
    
    public String getDescripcionEdicion() { return descripcionEdicion; }
    public void setDescripcionEdicion(String descripcionEdicion) { this.descripcionEdicion = descripcionEdicion; }
}