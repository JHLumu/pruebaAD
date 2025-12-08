package dominio.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import dominio.Categoria;
import dominio.EstadoProducto;
import dominio.Producto;
import servicios.FactoriaServicios;
import servicios.IServicioCategorias;
import servicios.IServicioProductos;

@Named
@ViewScoped
public class ProductosWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String textoBusqueda;
    private String categoriaId;
    private EstadoProducto estado;
    
    // Importante: Usamos Double (objeto) para que pueda ser null sin romper JSF
    private Double precioMaximo; 

    private List<Producto> productos;
    private List<Categoria> categorias;

    private IServicioProductos servicioProductos;
    private IServicioCategorias servicioCategorias;

    public ProductosWeb() {
        this.servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);
        this.servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);
    }

    @PostConstruct
    public void init() {
        this.categorias = servicioCategorias.recuperarCategoriasRaiz();
        
        this.precioMaximo = 2000.0; 
        
        buscar();
    }

    public void buscar() {
        // Lógica de seguridad: Si precioMaximo es null, usamos un valor alto por defecto.
        // Esto evita el NullPointerException al pasarlo al servicio.
        double precioFiltro = (precioMaximo != null) ? precioMaximo : 100000.0;
        
        this.productos = servicioProductos.getProductoByFiltros(
            categoriaId, 
            textoBusqueda, 
            estado, 
            precioFiltro 
        );
    }

    // --- Getters y Setters ---

    public String getTextoBusqueda() { return textoBusqueda; }
    public void setTextoBusqueda(String textoBusqueda) { this.textoBusqueda = textoBusqueda; }

    public String getCategoriaId() { return categoriaId; }
    public void setCategoriaId(String categoriaId) { this.categoriaId = categoriaId; }

    public EstadoProducto getEstado() { return estado; }
    public void setEstado(EstadoProducto estado) { this.estado = estado; }

    public Double getPrecioMaximo() { return precioMaximo; }
    public void setPrecioMaximo(Double precioMaximo) { this.precioMaximo = precioMaximo; }

    public List<Producto> getProductos() { return productos; }
    public List<Categoria> getCategorias() { return categorias; }
    
    public EstadoProducto[] getEstadosPosibles() {
        return EstadoProducto.values();
    }
}