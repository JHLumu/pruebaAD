package dominio.dto;

import java.time.LocalDateTime;

import dominio.Categoria;
import dominio.EstadoProducto;

public class ProductoDTO {

	private int id;
	private String titulo;
	private double precio;
	private LocalDateTime fechaPublicacion;
	private EstadoProducto estado;
	private Categoria categoria;
	private int visualizaciones;
	
	public ProductoDTO(String id, String titulo, double precio, LocalDateTime fechaPublicacion, EstadoProducto estado,Categoria categoria, int visualizaciones) {
		this.id = Integer.parseInt(id) ;
		this.titulo = titulo;
		this.precio = precio;
		this.fechaPublicacion = fechaPublicacion;
		this.estado = estado;
		this.categoria = categoria;
		this.visualizaciones = visualizaciones;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public EstadoProducto getEstado() {
		return estado;
	}
	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getVisualizaciones() {
		return visualizaciones;
	}
	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}
	
}
