package Taller02;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
	static String usuario;
	static String medallas;

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
		escritor.write(apodo);
		escritor.close();
		s.close();
		continuar();
	}//fin nuevaPartida

	private static void continuar() {
		cargarRegistro();
		
		
		// print menu
		System.out.println("Bienvenido "+ usuario+"\n"+usuario+", que deseas hacer?");
		System.out.println("1) Revisar equipo\n"
				+ "2) Salir a capturar\n"
				+ "3) Acceso al PC (cambiar Pokémon del equipo)\n"
				+ "4) Retar un gimnasio\n"
				+ "5) Desafío al Alto Mando\n"
				+ "6) Curar Pokémon\n"
				+ "7) Guardar\n"
				+ "8) Guardar y Salir");
		
	}

	private static void cargarRegistro() throws FileNotFoundException {
		File arch=new File("registros.txt");
		Scanner s = new Scanner (arch);
		String linea= s.nextLine();
		String partes[]= linea.split(";");
		
		usuario= partes[0];
		medallas=partes[1];
		
		while (s.hasNextLine()) {
			String linea2= s.nextLine();
			String partes2[]= linea2.split(";");
			String pokemonUsuario = partes[0];
			String estadoBatalla= partes[1];
			
		}
		
		
		
		
		
		
	}

}
