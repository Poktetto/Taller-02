package Taller02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
	static String usuario;
	static int medallas;
	static ArrayList<Pokemones> inventarioPC=new ArrayList<>(); //inventarioPC es usado en? 
	public static List<Pokemones> pokedex = new ArrayList<>(); // aqui se crean los objetos por cada pokemon rejistrado
	
	public static void main(String[] args) throws IOException {
		
		menuInicial();
		System.out.println("test fin programa");
	}// main

	private static void menuInicial() throws IOException {
		int opcion=-1;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("1) Continuar");
			System.out.println("2) Nueva Partida");
			System.out.println("3) Salir");
			System.out.print(">");
			String resp = s.nextLine();
			opcion= Integer.parseInt(resp);
			if (opcion==1) {
				continuar();
			}
			if (opcion==2) {
				nuevaPartida();
			}
		}while (opcion!=3);
		
		s.close();
	}// fin menu I

	private static void nuevaPartida() throws IOException {
		Scanner s = new Scanner(System.in);
		
		//library 
		System.out.println("==========================");
		System.out.print("+Ingrese apodo: ");
		String apodo = s.nextLine();
		// creacion del Archivo 
		FileWriter writerRegistro = new FileWriter("txts/Registros.txt");
		BufferedWriter escritor =new BufferedWriter(writerRegistro); 
		escritor.write(apodo+";none");
		escritor.close();
		s.close();
		continuar();
	}//fin nuevaPartida
	
	
	//metodo continuar
	private static void continuar() throws FileNotFoundException {
		cargarRegistro();
		
		//print saludo
		System.out.println("Bienvenido "+ usuario+"\n"+usuario+", que deseas hacer?");
		int opcion=-1;
		Scanner s = new Scanner (System.in);
		
		do {
			// print menu
			System.out.println("1) Revisar equipo\n"
					+ "2) Salir a capturar\n"
					+ "3) Acceso al PC (cambiar Pokémon del equipo)\n"
					+ "4) Retar un gimnasio\n"
					+ "5) Desafío al Alto Mando\n"
					+ "6) Curar Pokémon\n"
					+ "7) Guardar\n"
					+ "8) Guardar y Salir");
			System.out.print(">");
			String resp = s.nextLine();
			opcion= Integer.parseInt(resp);
			
			switch (opcion) {
			
			case 1:
				break;
			case 2:
				salirCapturar();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			default:
				break;
			
			}
			
			
		}while (opcion!=8);
		
		s.close();
	}
	private static void salirCapturar() throws FileNotFoundException {
		
		int opcion=-1;
		//Scanner para Imput
		Scanner s = new Scanner(System.in);
		//File
		cargarHabitats(-1);
		
		System.out.println("Ingrese Zona");
		System.out.print(">");
		String zona= s.nextLine();
		cargarHabitats(Integer.parseInt(zona));
		
	}

	private static String cargarHabitats(int zona) throws FileNotFoundException {
		int cont=0;
		File habitats = new File("txts/Habitats.txt");
		Scanner sHabit = new Scanner(habitats);
		//ciclo para leer habitats
		// if para ver si es la primera vez o ya hay 
		if (zona<=0) {
			while (sHabit.hasNextLine()) {
				String linea =sHabit.nextLine();
				System.out.println(++cont + ") " +linea);
				
			}// fin while 
		} else {
			while (sHabit.hasNextLine()) {
				String linea =sHabit.nextLine();
				++cont;
				if (cont==zona) {
					// si se culmple sigue el codigo conciderando "linea" como el nombre de la zona
					aparecerPokemon(linea);
				}
				
			}// fin while 
		}
		
		return null;
	}

	private static void aparecerPokemon(String zona) throws FileNotFoundException {
		cargarPokedex();
		buscarPokePorZona(zona);
		
		
	}

	

	private static void cargarPokedex() throws FileNotFoundException {
		//variables  de File
		File arcPokedex = new File("txts/Pokedex.txt");
		Scanner scanPokedex = new Scanner(arcPokedex);
		int contT=0;
		//ciclo de carga
		while(scanPokedex.hasNextLine()) {
			String linea = scanPokedex.nextLine();
			String[] pokePartes = linea.split(";");
			Pokemones p = new Pokemones(pokePartes[0],pokePartes[1],Double.parseDouble(pokePartes[2]),Integer.parseInt(pokePartes[3]),Integer.parseInt(pokePartes[4]),Integer.parseInt(pokePartes[5]),Integer.parseInt(pokePartes[6]),Integer.parseInt(pokePartes[7]),Integer.parseInt(pokePartes[8]),pokePartes[9]);//uf que feo pero funciona)
			pokedex.add(p);
		}
		
		
	}

	private static void buscarPokePorZona(String zona) {
		List <Pokemones> pokePosibles = new ArrayList<>();
		
		
		//ciclo de busqueda de porcentaje total
		for (int i=0; i<pokedex.size();i++) {
			if (pokedex.get(i).getHabitat().equals(zona)) {
				pokePosibles.add(pokedex.get(i));
				
			}
		}//fin For
		
		double porcentajeTotal=0;
		//ciclo de normalizacion de probabilidad 
		for (int i=0;i<pokePosibles.size();i++) {
			porcentajeTotal+=pokePosibles.get(i).getPorcentajeAparicion();
		}
		if (porcentajeTotal>0) {
			for (int i=0;i<pokePosibles.size();i++) {
				double nuevoPorcentaje = pokePosibles.get(i).getPorcentajeAparicion()/porcentajeTotal;
				pokePosibles.get(i).setPorcentajeAparicion(nuevoPorcentaje);
			}
		}//fin normalizacion de probabilidad
		
		//actualizar el porcentaje total
		for (int i=0;i<pokePosibles.size();i++) {
			porcentajeTotal+=pokePosibles.get(i).getPorcentajeAparicion();
		}
		//no puede ser  presisamente 1 debido a que los double son medio inexactos xd
		aparicionPokemon(pokePosibles); //se crea la aparicion 
		
	}
	private static void aparicionPokemon(List<Pokemones> pokePosibles) {
		Scanner s  = new Scanner(System.in); //escaner opcion
		Random rand = new Random();
		double pokeRandom = rand.nextDouble(1); //crea un double aleatorio de  0 a 1 con decimales
		double porceAcumulado =0; //para que se sumen los errores hasta llegar a un exito
		
		for (int i=0;i<pokePosibles.size();i++) { 
			
			porceAcumulado+=pokePosibles.get(i).getPorcentajeAparicion();//ejecucion de la suma
			
			
			//comparas las probabilidades
			if(pokeRandom<=porceAcumulado) {
				System.out.println("Oh! ha apararecido un "+pokePosibles.get(i).getPokemon()+" salvaje!");
				break; //Momento de la aparicion...! :D 
			}
			
			
		}
		int opcion=-1;
		do {
			System.out.println("1) capturar");
			System.out.println("2) Huir");
			System.out.print(">");
			opcion= Integer.parseInt(s.nextLine());
			if (opcion==1) {
				almacenarPokemon();
			} 
		} while(opcion!=2 && opcion!=1);
	}
	
	private static void almacenarPokemon() {
		System.out.println("falta poder almacenar pokemon en Registros");
		
	}
	private static void buscarPokemon(String pokemonUsuario, String estadoBatalla) throws FileNotFoundException {
		//variables  de File
		File arcPokedex = new File("txts/Pokedex.txt");
		Scanner scanPokedex = new Scanner(arcPokedex);
		int contT=0;
		//ciclo de carga
		while(scanPokedex.hasNextLine()) {
			String linea = scanPokedex.nextLine();
			String[] pokePartes = linea.split(";");
			if (pokePartes[0].equals(pokemonUsuario)){
				Pokemones p = new Pokemones(pokePartes[0],pokePartes[1],Double.parseDouble(pokePartes[2]),Integer.parseInt(pokePartes[3]),Integer.parseInt(pokePartes[4]),Integer.parseInt(pokePartes[5]),Integer.parseInt(pokePartes[6]),Integer.parseInt(pokePartes[7]),Integer.parseInt(pokePartes[8]),pokePartes[9]);
				inventarioPC.add(p);
				if (estadoBatalla.equals("Debilitado")){
					p.derrotado();
					
				}
				
			}
		
		}
	}

	//Carga del archivo txt de registros
	private static void cargarRegistro() throws FileNotFoundException {
		File arch=new File("txts/registros.txt");
		Scanner s = new Scanner (arch);
		String linea= s.nextLine();
		String partes[]= linea.split(";");
		
		usuario= partes[0];
		if (partes[1].equals("none")) {
		medallas=0;
		}
		
			
		while (s.hasNextLine()) {
			String linea2= s.nextLine();
			String partes2[]= linea2.split(";");
			String pokemonUsuario = partes[0];
			String estadoBatalla= partes[1];
			buscarPokemon(pokemonUsuario,estadoBatalla);
			
			
			
			
			
			
			
			
		}
		
		
		
		s.close();
		
		
		
	}

}
