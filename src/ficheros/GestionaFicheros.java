package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import biblioteca.Autoria;
import biblioteca.Libro;




public class GestionaFicheros {

	
	
	
	
	
	static File f = new File ("./files/biblioteca.bin");
	
	
	
	/**
	 * Con este método importamos el texto que hay introducido en los ficheros de texto plano.
	 * Creamos un ArrayList de libros provisionales donde iremos introduciendo con variables temporales todo el texto que leemos del archivo de texto.
	 * Al final del método nos retornará el ArrayList de libros provisionales con todo el texto importado desde el fichero de texto.
	 * Lo metemos todo dentro de un try catch para que nuestro BufferedREader y FileReader con el que leeremos funcione.
	 * @param file es la variable del Objeto de tipo File con el que leeremos los ficheros de texto.
	 * @param libros le decimos que será de tipo ArrayList de la clase Libro.
	 * @return retornará el ArrayList de librosProvisionales con todo el texto añadido anteriormente.
	 * @throws IOException Error de entrada salida con el fichero.
	 * @throws NumberFormatException Se lanza para indicar que la aplicación ha intentado 
	 * convertir una cadena a uno de los tipos numéricos, pero que la cadena no tiene el formato apropiado.
	 */
	static public ArrayList<Libro> importarTexto(File file, ArrayList<Libro> libros) throws NumberFormatException, IOException {
		ArrayList<Libro> librosProvisionales= new ArrayList<Libro>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        	
        	
       
        	String isbnProvisional;
        	while ((isbnProvisional = br.readLine())!=null) {
    		String tituloProvisional = br.readLine();
    		int idAutoriaProvisional = Integer.valueOf(br.readLine());
    		String nombreProvisional = br.readLine();
    		String apellidosProvisional = br.readLine();
    		
			
			
		librosProvisionales.add(new Libro(isbnProvisional,tituloProvisional, new Autoria(idAutoriaProvisional,nombreProvisional,apellidosProvisional)));
				
				
			}
			
			br.close();
			
			
			
			
		
		System.out.println(librosProvisionales);
		return librosProvisionales;
		
		
	}
	
	/**
	 * Con este método exportamos el texto recorriendo todo con un for each y escribimos línea a línea recogiendo los datos de los libros y autores.
	 * Cerramos el PrintWriter para que se guarde la información que exportamos.
	 * Lo metemos todo dentro de un try catch para que nuestro FileWriter y PrintWriter con el que escribiremos funcione.
	 * @param libros de tipo ArrayList lo recorreremos en el método para escribir cada linea dentro del for each.
	 * @param file de tipo File donde especificaremos el archivo que exportaremos con el método
	 * @param concatenar introducimos un booleano para decidir si queremos concatenar o no al escribir con el método.
	 * @throws IOException Error de entrada salida con el fichero.
	 */
	static public void exportarTexto(ArrayList<Libro> libros, File file,boolean concatenar) throws IOException {
		

	
			
			FileWriter fw = new FileWriter(file,concatenar);
			PrintWriter pw = new PrintWriter(fw);
			for(Libro exportarLibros : libros ) {
				pw.println(exportarLibros.getIsbn());
				pw.println(exportarLibros.getTitulo());
				pw.println(exportarLibros.getAutorias().getId());
				pw.println(exportarLibros.getAutorias().getNombre());
				pw.println(exportarLibros.getAutorias().getApellidos());
				
			}
			pw.close();
		
		
	}
		
		
	
	
	/**
	 * Importamos la información que contiene nuestro archivo binario biblioteca.bin.
	 * Para poder importar en binario utilizamos ObjectInputStream y FileInputStream dentro de un try catch.
	 * Introducimos f que será nuestro File donde crearemos nuestra biblioteca.bin
	 * Con el método leeremos el ArrayList de la clase Libro asignando una variable de librosLeidos. 
	 * @return nos retorna el ArrayList de librosLeidos donde se encuentra la informacion de biblioteca.bin
	 * @throws IOException Error de entrada salida con el fichero.
	 * @throws FileNotFoundException No ha encontrado el fichero.
	 * @throws ClassNotFoundException El fichero tiene mal formato.
	 */
	static public ArrayList<Libro> importarBinario() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	
			ArrayList<Libro> librosLeidos = (ArrayList<Libro>) ois.readObject();
			System.out.println(librosLeidos);
			return librosLeidos;
			
			
		}
	
	/**
	 * Exportamos la información de nuestros libros y autores a el archivo biblioteca.bin a través de f creado como tipo File.
	 * Para poder exportar en binario utilizamos ObjectOutputStream y FileOutputStream dentro de un try catch.
	 * Le decimos que escriba a biblioteca.bin del Arraylist del método.
	 * Cerramos el archivo con .close
	 * @param libros el ArrayList que utilizaremos en el método para escribir en binario.
	 * @throws IOException Error de entrada salida con el fichero.
	 * @throws FileNotFoundException No ha encontrado el fichero.
	 */
	static public void exportarBinario(ArrayList<Libro> libros) throws FileNotFoundException, IOException {
		
		
		
		
		
		
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		
				
				oos.writeObject(libros);
		
			//El método write Object() para todo lo que no sean datos primitivos.
			//las clases que quiero escribir tienen que implementar la interfaz Serializable
			
			oos.close();
		}
		
		
	
	
	
	
	
	

	
	
	

}
