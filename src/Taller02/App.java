package Taller02;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class App {

	public static void main(String[] args) throws IOException {
		//hi
		//hiii :D
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
		continuar();
	}//fin nuevaPartida

	private static void continuar() {
		// No hay nada por ahora 
		
	}

}
