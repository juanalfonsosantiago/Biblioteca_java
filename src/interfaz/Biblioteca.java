package interfaz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import biblioteca.Autoria;
import biblioteca.Libro;
import ficheros.GestionaFicheros;

public class Biblioteca {

	static ArrayList<Autoria> listaAutor = new ArrayList();
	static ArrayList<Libro> listaLibro = new ArrayList();
	static Libro[] libro;

	// los Scanner estáticos para introducir líneas y números.
	static Scanner scLine = new Scanner(System.in);
	static Scanner scInt = new Scanner(System.in);

	// el número que vamos a utilizar para el menú o switch a prueba de errores.
	static int valorNUmericoScanner = 1;
	// número con el que vamos a comparar el número del menú para prueba de errores.
	static String valorDePrueba;

	public static void main(String[] args) {

		try {
			listaLibro = GestionaFicheros.importarBinario();
			ArrayList<Autoria> listaAutorAux = new  ArrayList<>();
			for (Libro libro : listaLibro) {
				listaAutorAux.add(libro.getAutorias());
			}
			for (int i = 0; i < listaAutorAux.size(); i++) {
				if (comprobarAutor(listaAutorAux.get(i).getId()) == null) {
					listaAutor.add(listaAutorAux.get(i));
				}
			}
		} catch (Exception e) {
			//Si da error quiere decir que no existía .bin y no hago nada
		}
//comienza el while del menú, si es 0 termina el programa, si es distinto entra en las opciones del menú 
		while (valorNUmericoScanner != 0) {

			// características de lo que pide el menú
			System.out.println("1. Crear autor/a\r\n" + "2. Ver autoras/es\r\n" + "3. Crear libro\r\n"
					+ "4. Mostrar libros\r\n" + "5. Eliminar libro\r\n" + "6. Exportar a fichero de texto\r\n"
					+ "7. Importar de fichero de texto\r\n" + "8. Exportar a binario\r\n" + "9. Importar de binario\r\n"
					+ "0. Salir");

			// bucle del menú a prueba de errores
			while (true) {

				// le damos valor al isNumericInt para que esté a prueba de errores
				valorDePrueba = scLine.nextLine();

				if (isNumericInt(valorDePrueba) == true) {

					valorNUmericoScanner = Integer.valueOf(valorDePrueba);
					// le decimos entre que números queremos que esté el menú y no de error
					if (valorNUmericoScanner >= 0 && valorNUmericoScanner <= 9) {

						break;
					} else {
						// si se pasa de esos valores imprime esto
						System.out.println("número no válido");
					}

				} else {
					// si no cumple con los números y es una letra imprime esto
					System.out.println("caracter no válido");
				}
				// acaba el while true a prueba de errores
			}

			// comienza el menú
			switch (valorNUmericoScanner) {

			case 1:
				introducirAutor();

				break;

			case 2:

				verAutor();

				break;

			case 3:

				introducirLibro();

				break;

			case 4:
				verLibro();

				break;

			case 5:
				borrarLibro();

				break;

			case 6:

				exportarTextoMain();
				break;

			case 7:

				importarTextoMain();
				break;

			case 8:

				exportarBinarioMain();

				break;

			case 9:
				importarBinarioMain();
				break;

			case 0:
				finalizarPrograma();
				break;

			} // llave final del menú o switch

			// cierre del primer while true o bucle principal
		}

	}

	// CASE1
	
	/**
	 * Introducimos los autores creando variables provisionales para luego asignarles los datos introducidos.
	 * Usamos el boolean comprobacionID para comprobar si ese ID ya existe.
	 * Si el número de autores es distinto de 0 comprobamos si el autor existe con el método de comparacion hecho de forma estático.
	 * Si se puede crear cambia el estado a true.
	 * Añade todos los datos al ArrayList de la lista de autores con los datos que le pedimos.
	 */
	static private void introducirAutor() {

		// creamos variables temporales de usar y tirar para los datos que vamos a
		// introducir
		int idProvisional;
		String nombreProvisional;
		String apellidoProvisional;
		String apellido2Provisional;

		// comenzamos con las preguntas que queremos introducir
		System.out.println("Introduzca el ID del autor o autora");

		// y le decimos que el próximo número introducido será esa elección.

		boolean comprobacionID = false;
		idProvisional = 0;
		while (comprobacionID == false) {
			idProvisional = numeroPruebaErrores();
			
				
			if (listaAutor.size() != 0) {

				if (comprobarAutor(idProvisional) == null) {
					comprobacionID = true;
				} else {
					System.out.println("ID ya en uso, introduzca otro");
				}

			} else {
				comprobacionID = true;
			}
		}

		System.out.println("Introduzca el nombre?");
		// utilizamos la variable temporal para el nombre
		nombreProvisional = scLine.nextLine();

		System.out.println("Introduzca el primer apellido");
		// utilizamos la variable temporal para el apellido
		apellidoProvisional = scLine.nextLine();

		System.out.println("Introduzca el segundo apellido");
		// utilizamos la variable temporal para el segundo apellido
		apellido2Provisional = scLine.nextLine();

		String apellidos = apellidoProvisional + " " + apellido2Provisional;

		// recogemos todos los datos introducidos y se lo metemos a
		// un autor provisional que más adelante añadiremos al ArrayList.
		Autoria autorProvisional = new Autoria(idProvisional, nombreProvisional, apellidos);

		// añadimos el autor provisional al ArrayList
		listaAutor.add(autorProvisional);

		System.out.println("");
		// utilizamos esto para volver al menú anterior
		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

		// cerramos el método 1.
	}
	
	
	

	// CASE 2
	
	/**
	 * Muestra los autores recorriendo el ArrayList de la lista de autores creados.
	 * Si no hay autores lo avisa.
	 */
	static private void verAutor() {

		for (int i = 0; i < listaAutor.size(); i++) {

			System.out.println(listaAutor.get(i).getId() + " " + listaAutor.get(i).getNombre() + " "
					+ listaAutor.get(i).getApellidos());
		}
		if(listaAutor.isEmpty()) {
			System.out.println("no hay autores");
		}
		System.out.println("");
		// utilizamos esto para volver al menú anterior
		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

	}
	
	
	
	

	// CASE 3
	
	/**
	 * Utilizamos variables temporales en las que introduciremos todos los datos del libro que queremos crear relacionándolo con el ID del autor.
	 * Usamos el boolean checkaAutor para comprobar si ese Autor existe.
	 * Si el número de libros es distinto de 0 comprobamos si el libro existe con el método de comparacion hecho de forma estático.
	 * Comprueba si el isbn ya está en uso, si se puede crear el libro cambia el estado a true.
	 * Añade todos los datos al ArrayList de la lista de libros con los datos que le pedimos.
	 */
	static private void introducirLibro() {

		// creamos variables temporales de usar y tirar para los datos que vamos a
		// introducir
		String isbnProvisional = null;
		String tituloProvisional = null;
		int idAutoriaProvisional;

		Libro libroProvisional = new Libro();
		boolean checkeaAutor = false;

		// comenzamos con las preguntas que queremos introducir
		System.out.println("Introduzca el ISBN del libro");

		// y le decimos que el próximo número introducido será esa elección.

		boolean comprobacionISBN = false;
		isbnProvisional = "";
		while (comprobacionISBN == false) {
			isbnProvisional = scLine.nextLine();
			if (listaLibro.size() != 0) {

				if (comprobarLibro(isbnProvisional) == null) {
					comprobacionISBN = true;
				} else {
					System.out.println("ISBN ya en uso, introduzca otro");
				}

			} else {
				comprobacionISBN = true;
			}
		}

		System.out.println("Introduzca el título?");
		// utilizamos la variable temporal para el titulo
		tituloProvisional = scLine.nextLine();

		while (checkeaAutor == false) {
			String autoriaProv = "";

			do {
				System.out.println("Introduzca el ID del autor o autora del libro que va a crear: ");
				autoriaProv = scLine.nextLine();
				// A prueba de errores para que no puedas meter un String o un número menor a 0.
			}while (isNumericInt(autoriaProv) == false || Integer.valueOf(autoriaProv) <= 0);

			idAutoriaProvisional = Integer.valueOf(autoriaProv);

			for (Autoria idAutores : listaAutor) {
				if (idAutores.getId() == idAutoriaProvisional) {
					libroProvisional.setIsbn(isbnProvisional);
					libroProvisional.setTitulo(tituloProvisional);
					libroProvisional.setAutorias(idAutores);
					// añadimos el libro provisional al ArrayList
					listaLibro.add(libroProvisional);
					System.out.println("El libro " + tituloProvisional + " se ha creado correctamente");
					System.out.println(libroProvisional);
					checkeaAutor = true;

				}

			}
			if (checkeaAutor == false) {
				System.out.println("El ID no existe");
			}
			// BREAK PARA ROMPER EL BUCLE WHILE Y EN CASO DE DE QUE NO EXISTA EL AUTOR PODER
			// VOLVER AL MENÚ
			break;

		}

		System.out.println("");
		// utilizamos esto para volver al menú anterior
		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

		// cerramos el método 3.
	}

	// CASE 4

	/**
	 * Muestra los libros recorriendo el ArrayList de la lista de libros creados.
	 * Si no hay libros lo avisa.
	 */
	static private void verLibro() {

		System.out.println(listaLibro.size() + " Es el numero de libros");
		if (listaLibro.size() != 0) {
			System.out.println(listaLibro);
		} else {
			System.out.println("No hay libros");
		}

		System.out.println("");

		// utilizamos esto para volver al menú anterior
		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

	}

	// CASE 5
	
	/**
	 * Recorremos la lista de libros creados
	 * El isbn que hemos introducido para borrar lo compara y si existe lo borra, si no existe lo avisa.
	 */
	static private void borrarLibro() {
		
		// creamos una variable numérica para borrar
		String paraBorrar;
		// hacemos un bucle con un listado donde mostramos los libros
		for (int i = 0; i < listaLibro.size(); i++) {

			// imprimimos el número ordenado con cada libro
			// como el Array empieza en 0 se le suma 1 a la i para que el listado empiece en
			// 1
			System.out.print(i + 1 + " ");

			// imprimimos los ISBN
			System.out.println(listaLibro.get(i).getIsbn());

			// llave de cierre del for
		}

		System.out.println("Introduzca el ISBN del libro que desea borrar");

		// le decimos que el próximo número introducido es igual a la variable creada antes.
		// y le quitamos -1 ya que el Array empieza en 0 y será siempre un número menor
		// a la elección
		// así haremos que coincida nuestro número elegido con la posición del Array.
		paraBorrar = scLine.nextLine();
		Libro ejemplo = comprobarLibro(paraBorrar);

		if (ejemplo == null) {
			System.out.println("No existe");
		} else {
			listaLibro.remove(ejemplo);
			System.out.println("El libro se ha borrado correctamente");
		}

		// utilizamos esto para volver al menú anterior
		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

	}

	// CASE 6
	
	/**
	 * Pregunta a que fichero queremos exportar el texto y los datos introducidos se le asignan a un fichero tipo File.
	 * Crea el fichero en la ruta indicada en el método y si existe pregunta si desea sobrescribir o concatenar los datos introducidos.
	 * Si se pulsa 1 concatena y el boolean del método es true, si se introduce una letra informa de que el caracter no es válido.
	 * Se exporta la lista de libros con sus autores al fichero relacionándolo con el método creado en GestionaFicheros.  
	 */
	static private void exportarTextoMain() {

		boolean concatenar = false;
		System.out.println("¿A que fichero quiere exportar los libros?");
		String nombreFichero = scLine.nextLine();
		File f = new File("./" + nombreFichero);
		if (f.exists()) {
			System.out.println("Desea sobrescribir o concatenar");
			System.out.println("Para concatenar pulse 1, si no introduzca otro número y sobreescribirá");
			int existe = numeroPruebaErrores();
					//scInt.nextInt();
			if (existe == 1) {
				concatenar = true;

			}
			

		}

		try {
			GestionaFicheros.exportarTexto(listaLibro, f, concatenar);
		} catch (IOException e) {
			System.err.println("Error de entrada salida con el fichero " + e.getMessage());
			
		}

	}
	
	

	// CASE 7
	
	/**
	 * Crea ArrayList donde introducir los libros y los autores que queremos importar para extraer la información.
	 * Pregunta por el nombre del fichero que queremos importar y si existe introduce en un ArrayList los datos de los libros y autores.
	 * Utiliza el método de importarTexto de la clase GestionaFicheros, si el fichero no existe lo avisa.
	 * Recorre los libros importados y añade a ese ArrayList los libros y los autores.
	 * Recorre los libros introducidos al ArrayList y los añade al ArrayList principal donde mostramos si hay libros en el menú.
	 * Recorre los autores introducidos al ArrayList y los añade al ArrayList principal donde mostramos si hay autores en el menú.
	 */
	static private void importarTextoMain() {
		ArrayList<Autoria> autoresIntroducir = new ArrayList<Autoria>();
		ArrayList<Libro> librosIntroducir = new ArrayList<Libro>();

		ArrayList<Libro> librosImportados = new ArrayList<Libro>();

		System.out.println("¿De que fichero quiere importar el texto?");
		String nombreFichero = scLine.nextLine();

		File f = new File("./" + nombreFichero);
		if (f.exists()) {

			try {
				librosImportados = GestionaFicheros.importarTexto(f, listaLibro);
			} catch (NumberFormatException e) {
				System.err.println("Se lanza para indicar que la aplicación ha intentado convertir una cadena a uno de los tipos numéricos,"
						+ " pero que la cadena no tiene el formato apropiado." + e.getMessage());
			} catch (IOException e) {
				System.err.println("Error de entrada salida con el fichero " + e.getMessage());
			}
		} else {
			System.out.println("el fichero no existe\n");

		}

		for (Libro libro : librosImportados) {
			librosIntroducir.add(libro);
			autoresIntroducir.add(libro.getAutorias());
		}

		for (int i = 0; i < librosIntroducir.size(); i++) {

			if (comprobarLibro(librosIntroducir.get(i).getIsbn()) == null) {

				listaLibro.add(librosIntroducir.get(i));

			}

		}

		for (int i = 0; i < autoresIntroducir.size(); i++) {

			if (comprobarAutor(autoresIntroducir.get(i).getId()) == null) {

				listaAutor.add(autoresIntroducir.get(i));

			}

		}

		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

	}

	// CASE 8
	
	/**
	 * Exporta en binario a la ruta que le hemos indicado en el método exportarBinario en la clase GestionaFicheros.
	 * Directamente exporta a biblioteca.bin el ArrayList de la lista de libros que tengamos.
	 */
	static private void exportarBinarioMain() {

		try {
			GestionaFicheros.exportarBinario(listaLibro);
		} catch (FileNotFoundException e) {
			
			System.err.println("Error al importar el fichero binario " + e.getMessage());
			
		} catch (IOException e) {
			System.err.println("Error de entrada salida con el fichero " + e.getMessage());
		}

		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

	}

	// CASE 9
	
	/**
	 * Crea ArrayList donde introducir los libros y los autores que queremos importar para extraer la información.
	 * Utiliza el método de importarBinario de la clase GestionaFicheros, si el fichero no existe lo avisa.
	 * Recorre los libros importados y añade a ese ArrayList los libros y los autores.
	 * Recorre los libros introducidos al ArrayList y los añade al ArrayList principal donde mostramos si hay libros en el menú.
	 * Recorre los autores introducidos al ArrayList y los añade al ArrayList principal donde mostramos si hay autores en el menú.
	 */
	static private void importarBinarioMain() {
		
		ArrayList<Autoria> autoresIntroducir = new ArrayList<Autoria>();
		ArrayList<Libro> librosIntroducir = new ArrayList<Libro>();

		ArrayList<Libro> librosImportados = new ArrayList<Libro>();

		try {
			librosImportados = listaLibro = GestionaFicheros.importarBinario();
		} catch (FileNotFoundException e) {
			
			
		System.err.println("Error al importar el fichero binario " + e.getMessage());
		
		} catch (ClassNotFoundException e) {
			
			System.err.println("El fichero tiene mal formato " + e.getMessage());
			
		} catch (IOException e) {
			System.err.println("Error de entrada salida con el fichero " + e.getMessage());
		
		}

		for (Libro libro : librosImportados) {
			librosIntroducir.add(libro);
			autoresIntroducir.add(libro.getAutorias());
		}

		for (int i = 0; i < librosIntroducir.size(); i++) {

			if (comprobarLibro(librosIntroducir.get(i).getIsbn()) == null) {

				listaLibro.add(librosIntroducir.get(i));

			}

		}

		for (int i = 0; i < autoresIntroducir.size(); i++) {

			if (comprobarAutor(autoresIntroducir.get(i).getId()) == null) {

				listaAutor.add(autoresIntroducir.get(i));

			}

		}

		System.out.println("Pulse enter para volver al menu principal");
		scLine.nextLine();

	}
	
	
	
	
	// CASE 0
	
	/**
	 * Finaliza el programa e informa de ello.
	 */
	static private void finalizarPrograma() {
		System.out.println("Programa finalizado");
		exportarBinarioMain();
	}
	
	
	
	
	/**
	 * Nos servirá para comprobar si existe ese autor a través del id que es único de cada autor
	 * recorriendo el ArrayList de los autores a través de su índice y comparándolo con el id.
	 * @param numeroComprobado para comprobar si es igual al id del autor del ArrayList.
	 * @return retorna el índice de la lista de los autores.
	 */
	static private Autoria comprobarAutor(int numeroComprobado) {

		for (int i = 0; i < listaAutor.size(); i++) {
			if (listaAutor.get(i).getId() == numeroComprobado) {
				return listaAutor.get(i);
			}
		}

		return null;
	}
	/**
	 * Nos servirá para comprobar si existe ese libro a través del isbn que es único de cada libro
	 * recorriendo el ArrayList de los libros a través de su índice y comparándolo con el isbn.
	 * @param isbnComprobado para comprobar si es igual al isbn del libro del ArrayList.
	 * @return  retorna el índice de la lista de libros.
	 */
	static private Libro comprobarLibro(String isbnComprobado) {

		for (int i = 0; i < listaLibro.size(); i++) {
			if (listaLibro.get(i).getIsbn().equals(isbnComprobado)) {
				return listaLibro.get(i);
			}
		}

		return null;
	}

	
	
	/**
	 * Nos servirá para que cada vez que introduzcamos un caracter String y queramos un número esté a prueba de errores
	 * Está en forma de bucle hasta que introduzcamos un número valido, sino retornará false.
	 * @return nos dará el número que introducimos a prueba de errores para que no sea algo distinto a un número.
	 */
	static private int numeroPruebaErrores() {
		while(true) {
		String provisional = scInt.nextLine();
		if(isNumericInt(provisional)==true) {
			return Integer.valueOf(provisional);
		}else {
			System.out.println("caracter no válido");
		}
		}
	}
	
	/**
	 * Método para poner a prueba de errores el código.
	 * @param x el String que utilizaremos para introducir en el Integer a probar.
	 * @return si se produce error retorna un boolean false, sino retorna true.
	 */
	static private boolean isNumericInt(String x) {

		try {

			Integer.valueOf(x);
		} catch (NumberFormatException e) {
			System.err.println("Se lanza para indicar que la aplicación ha intentado"
					+ " convertir una cadena a uno de los tipos numéricos, pero que la cadena no tiene el formato apropiado.");
			return false;

		}
		return true;
	}

}
