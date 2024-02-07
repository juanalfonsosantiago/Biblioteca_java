
package biblioteca;

import java.io.Serializable;

public class Libro implements Serializable {
	
	String isbn;
	String titulo;
	Autoria autorias;
	
	
	
	public Libro(String isbn, String titulo, Autoria autorias) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autorias = autorias;
	}
	
	
	


	public Libro() {
		super();
	}





	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public Autoria getAutorias() {
		return autorias;
	}



	public void setAutorias(Autoria autorias) {
		this.autorias = autorias;
	}





	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor o autora=" + autorias.getNombre() + " apellidos=" + autorias.getApellidos()+ "]";
	}




	
	
	
	

}
