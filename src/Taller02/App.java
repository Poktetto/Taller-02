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
	public static ArrayList<Pokemones> inventarioPC=new ArrayList<>(); 
	public static Gimnasio lideres[]= new Gimnasio[8];
	public static Gimnasio lider;
	public static Gimnasio liderAnterior;
	public static List<Pokemones> equipoLider = new ArrayList<>();
	public static int statsEnemigo;
	public static int statsJugador;
	public static String tipoPokemon[]={"Normal","Fuego","Agua","Planta","Electrico","Hielo","Lucha","Veneno","Tierra","Volado","Psiquico","Bicho","Roca","Fantasma","Dragon","Acero","Siniestro","Hada"};
	public static int indJugador;
	public static int indLider;
	
	
	
	public static void main(String[] args) throws IOException {
		menuInicial();
		System.out.println("test fin programa");
	}// main
	//menu Inicial
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
	//opcion 2 menu Inicial
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
		
		continuar();
	}//fin nuevaPartida
	
	
	//metodo continuar
	private static void continuar() throws NumberFormatException, IOException {
		cargarRegistro();
		
		//print saludo		
		
		System.out.println("Bienvenido "+ usuario+"\n"+usuario+", que deseas hacer?");
		int opcion=-1;
		Scanner s = new Scanner (System.in);
		
		do {
			// print menu
			System.out.println();
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
				revisarEquipo();
				break;
			case 2:
				salirCapturar();
				break;
			case 3:
				abirPC();
				break;
			case 4:
				retarGimnasio();
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
		
		
	}
	//opcion 4
	private static void retarGimnasio() throws FileNotFoundException {
		
		cargarGimnasio();
		int opcion=-1;
		
		Scanner s = new Scanner (System.in);  
		for (int i=0;i<lideres.length;i++) { //imprime los lideres pokemon
	
			System.out.println(lideres[i]);
		}
		System.out.println("9) Volver al menu.");
		String resp = s.nextLine();
		opcion= Integer.parseInt(resp); //ingresa "id" del entrenador (tecnicamente no, pero aparenta eso)
		for (int i=0;i<lideres.length;i++) {
			if (opcion==1) { // si es el primer lider
				lider= lideres[0];
				menuCombate();
				break;
			}else {
				lider=lideres[opcion-1]; // los demas lideres
				liderAnterior=lideres[opcion-2];
				if (liderAnterior.getEstado().equals("Sin derrotar")){
					System.out.println("Calmado Entrenador!!! No puedes retar a " +lider.getNombre()+ " sin haber derrotado a los lideres anteriores!!");
					break;
				}else {
					menuCombate();
					break;
				}
				
				
				
				
			}
			
			
		}
		
		
		

	
		
	}
	private static void menuCombate() {
		equipoLider= lider.getEquipoEnemigo(); //se crea el el equipo pokemon del lider
		
		System.out.println("Desafiando a "+lider.getNombre()+"!!");
		int pokemonLider=0; //para buscar pokemon que peleara
		int pokemonJugador=0; //para buscar pokemon que peleara
		
		System.out.println(lider.getNombre()+" saca a "+equipoLider.get(pokemonLider).getPokemon()+"!!");
		System.out.println(usuario+" saca a "+inventarioPC.get(pokemonJugador).getPokemon()+"!!");
		int opcion=-1;
		Scanner s = new Scanner (System.in);
		
		do {
			System.out.println("1) Atacar.");
			System.out.println("2) Cambiar Pokemon");
			System.out.println("3) Rendirse.");
			String resp = s.nextLine();
			opcion= Integer.parseInt(resp);
			
			switch (opcion) {
			case 1:
				atacar(pokemonLider,pokemonJugador);
				break;
			case 2:
				cambiarPokemon();
				break;
			default:
				break;
			
			}
			
		}while (opcion!=3 && opcion!=2 && opcion!=1);
		
	}
	private static void cambiarPokemon() {
		// TODO Auto-generated method stub
		
	}
	private static void atacar(int pokemonLider,int pokemonJugador) {
		statsEnemigo=equipoLider.get(pokemonLider).getStatTotales();// puntaje del pokemon del lider
		statsJugador=inventarioPC.get(pokemonJugador).getStatTotales();// puntaje inicial del pokemon del jugador
		
		System.out.println(inventarioPC.get(pokemonLider).getPokemon()+" ->"+statsJugador);
		System.out.println(equipoLider.get(pokemonJugador).getPokemon()+" ->"+statsEnemigo);
		
		for (int i=0; i< tipoPokemon.length;i++) {
			if (equipoLider.get(pokemonLider).getTipo().equals(tipoPokemon[i])) {
				
				indLider=i;
			}
			if (inventarioPC.get(pokemonJugador).getTipo().equals(tipoPokemon[i])) { 
				indJugador=i;
			}
		}
		
		statsJugador= (int) (statsJugador*TablaTipos.getEfectividad(pokemonLider, pokemonJugador)); //multiplicara el stat por la efectividad
		if (TablaTipos.getEfectividad(pokemonLider, pokemonJugador)==0.0||TablaTipos.getEfectividad(pokemonLider, pokemonJugador)==0.5){ //imprime en caso que no sea efectivo
			System.out.println(inventarioPC.get(pokemonLider).getPokemon()+" no es efectivo contra "+equipoLider.get(pokemonLider).getPokemon());
			System.out.println(inventarioPC.get(pokemonJugador).getPokemon()+" ->"+statsJugador);
			System.out.println(equipoLider.get(pokemonLider).getPokemon()+" ->"+statsEnemigo);
		}else if (TablaTipos.getEfectividad(pokemonLider, pokemonJugador)==2.0) { //imprime en caso que SI sea efectivo
			System.out.println(inventarioPC.get(pokemonLider).getPokemon()+"es efectivo contra "+equipoLider.get(pokemonLider).getPokemon()+"!!");
			System.out.println(inventarioPC.get(pokemonJugador).getPokemon()+" ->"+statsJugador);
			System.out.println(equipoLider.get(pokemonLider).getPokemon()+" ->"+statsEnemigo);
		}
		if (statsJugador>statsEnemigo) {// si el pokemon del jugador es mas fuerte
			System.out.println("Ha ganado "+inventarioPC.get(pokemonJugador).getPokemon()+"!"+equipoLider.get(pokemonLider).getPokemon()+" ha sido derrotado...");
			//no se si hacer el cambio de pokemon del lider aqui o afuera del metodo
		}else {//si el pokemon del lider es mas fuerte
			System.out.println("Ha ganado "+equipoLider.get(pokemonLider).getPokemon()+"!"+inventarioPC.get(pokemonJugador).getPokemon()+" ha sido derrotado...");
			inventarioPC.get(pokemonLider).derrotado();
			//falta el cambio de pokemon
		}
		
		
	}
	//abrir txt op 4
	private static void cargarGimnasio() throws FileNotFoundException {
		
		int cont=0;
		File archGimnasios=new File("txts/Gimnasios.txt");
		Scanner sGyms = new Scanner (archGimnasios);
		while (sGyms.hasNextLine()) {
		String linea= sGyms.nextLine();
		String partes[]= linea.split(";");
		int id= Integer.parseInt(partes[0]);
		String nombre = partes[1];
		String estado= partes[2];
		
		Gimnasio entrenador = new Gimnasio(id,nombre,estado); //crea entrenador
		
		int cantPokemones= Integer.parseInt(partes[3]);
		for (int i =4;i<cantPokemones+1;i++) {
			entrenador.agregarPokemones(partes[i]); //guarda el equipo de pokemon del entrenador
		}
		
		lideres[cont]=entrenador;
		
		cont++;
		
		
		
		
		}
		
	}
	//opcion  3
	private static void abirPC() throws IOException {
		Scanner s =new Scanner(System.in);
		revisarPC();
		System.out.println();
		int respuesta1 =-1;
		do {
			System.out.println("cual pokemon quiere cambiar (ingrese indice)");
			respuesta1 = Integer.parseInt(s.nextLine());
		}while (respuesta1>inventarioPC.size()+1|| respuesta1<0); //comprobar que no coloca un dato invalido 
		int respuesta2 =-1;
		do {
			System.out.println("Ahora con cual quiere cambiar el primero (ingrese indice)");
			respuesta2 = Integer.parseInt(s.nextLine());
		}while (respuesta2>inventarioPC.size()+1|| respuesta2<0); //comprobar que no coloca un dato invalido 
		intercambiarPosiciones(respuesta1, respuesta2);
		
		
		
	}
	//logica  de intercambio
	private static void intercambiarPosiciones(int respuesta1, int respuesta2) throws IOException {
		
		//intercambio en la lista
		Pokemones temp = inventarioPC.get(respuesta1-1);
		inventarioPC.set(respuesta1-1, inventarioPC.get(respuesta2-1));
		inventarioPC.set(respuesta2-1,temp);
		
		//almacenaje primera linea
		File regist = new File("txts/Registros.txt");
		Scanner scanReg = new Scanner(regist);
		String primeraLinea = scanReg.nextLine();
		//intercambio en el txt
		FileWriter writerRegistro = new FileWriter("txts/Registros.txt"); // se resetea el achivo 
		BufferedWriter escritor =new BufferedWriter(writerRegistro); //y se procede a recrear con el cambio de posiciones
		escritor.write(primeraLinea);
		escritor.close();
		for (int i=1;i<inventarioPC.size()+1;i++) {
			addPokeRegistros(i);
		}

		
	}
	//imprime el PC (todos los pokemones guardados en Registros)
	private static void revisarPC() {
		int cont =1;
		if (!inventarioPC.isEmpty()) {
			System.out.println("==========================");
			for(int i =0; i<inventarioPC.size();i++) { 
				System.out.println(cont++ + ") " + inventarioPC.get(i));
			}
			System.out.println("==========================");
		} else {
			System.out.println("no posee Pokemones");
		}
		
	}
	//imprime los primeros 6 pokemones en Registro (equipo)
	private static void revisarEquipo() {
		int cont =1;
		if (!inventarioPC.isEmpty()) {
			System.out.println("==========================");
			if (inventarioPC.size()>6) {
				for(int i =0; i<6;i++) { // en caso que se tengan más de 6 registrados
					System.out.println(cont++ + ") " + inventarioPC.get(i));
				}
			} else {
				for(int i =0; i<inventarioPC.size();i++) {
					System.out.println(cont++ + ") " + inventarioPC.get(i));
				}
				
			}
			System.out.println("==========================");
			
		} else {
			System.out.println("no posee Pokemones");
		}
		
	}
	//opcion 2
	private static void salirCapturar() throws NumberFormatException, IOException {
		
		
		
		int opcion=-1;
		//Scanner para Imput
		Scanner s = new Scanner(System.in);
		//File
		cargarHabitats(-414);
		String zona;
		do {
			System.out.println("Ingrese Zona");
			System.out.print(">");
			zona= s.nextLine();
		} while(Integer.parseInt(zona)<=0 || Integer.parseInt(zona)>contarTxt("txts/Habitats.txt"));
		cargarHabitats(Integer.parseInt(zona));
		
	}
	//cuenta cuantos datos hay en un txt
	private static int contarTxt(String txt) throws FileNotFoundException {
		int cont=0;
		File arc = new  File(txt);
		Scanner sArc = new Scanner(arc);
		while (sArc.hasNextLine()) {
			cont++;
			sArc.nextLine();
		}
		return cont;
	}
	//carga Habitats
	private static String cargarHabitats(int zona) throws IOException {
		int cont=0;
		File habitats = new File("txts/Habitats.txt");
		Scanner sHabit = new Scanner(habitats);
		//ciclo para leer habitats
		// if para ver si es la primera vez o ya hay 
		if (zona==-414) {
			System.out.println("==========================");
			while (sHabit.hasNextLine()) {
				String linea =sHabit.nextLine();
				System.out.println(++cont + ") " +linea);
				
			}// fin while
			System.out.println("==========================");
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
	//Entrada a los procesos
	private static void aparecerPokemon(String zona) throws IOException {
		List<Pokemones> pokedex = new ArrayList<>(); // aqui se crean los objetos por cada pokemon rejistrado
		cargarPokedex(pokedex);
		buscarPokeEnInventario(pokedex);
		buscarPokePorZona(zona, pokedex);
		
		
	}
	//busca si ya se tiene
	private static void buscarPokeEnInventario(List<Pokemones> pokedex) {
		for (int i=0;i<inventarioPC.size();i++) {
			for (int j=0;j<pokedex.size();j++) {
				if (inventarioPC.get(i).getPokemon().equals(pokedex.get(j).getPokemon())) {
					pokedex.remove(j);
				}
				
			}
		}
		
	}
	//carga los pokemones
	private static void cargarPokedex(List<Pokemones> pokedex) throws FileNotFoundException {
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
	//busca los pokemones por la zona ingresada
	private static void buscarPokePorZona(String zona, List<Pokemones> pokedex) throws IOException {
		List <Pokemones> pokePosibles = new ArrayList<>();
		
		
		//ciclo de busqueda de porcentaje total
		for (int i=0; i<pokedex.size();i++) {
			if (pokedex.get(i).getHabitat().equals(zona)) {
				pokePosibles.add(pokedex.get(i));
				
			}
		}//fin For
		if (!pokePosibles.isEmpty()) {
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
				
		} else {
			System.out.println("Ya no quedan pokemones en la zona");
		}
		
	}
	//logica deaparicion
	private static void aparicionPokemon(List<Pokemones> pokePosibles) throws IOException {
		Scanner s  = new Scanner(System.in); //escaner opcion
		Random rand = new Random();
		double pokeRandom = rand.nextDouble(1); //crea un double aleatorio de  0 a 1 con decimales
		double porceAcumulado =0; //para que se sumen los errores hasta llegar a un exito
		
		int pokeSalvaje=-1;
		
		for (int i=0;i<pokePosibles.size();i++) { 
			
			porceAcumulado+=pokePosibles.get(i).getPorcentajeAparicion();//ejecucion de la suma
			
			
			//comparas las probabilidades
			if(pokeRandom<=porceAcumulado) {
				System.out.println("Oh! ha apararecido un "+pokePosibles.get(i).getPokemon()+" salvaje!");
				pokeSalvaje=i; //registro de inice del pokemom aparecido
				break; //Momento de la aparicion...! :D 
			}
			
			
		}
		//menu de captura
		int opcion=-1;
		do {
			System.out.println("1) capturar");
			System.out.println("2) Huir");
			System.out.print(">");
			opcion= Integer.parseInt(s.nextLine());
			if (opcion==1) {
				almacenarPokemon(pokePosibles, pokeSalvaje);
			} 
		} while(opcion!=2 && opcion!=1);
	}
	//añade el pokemon a inventarioPC
	private static void almacenarPokemon(List<Pokemones> pokePosibles, int pokeSalvaje) throws IOException {
	
		inventarioPC.add(pokePosibles.get(pokeSalvaje)); //lo hace
		//se añade a Resitros como metodo de seguidad ( lo mejor seria que tambien se vea cuando se sale del PC)
		addPokeRegistros(inventarioPC.size()); //no se si esto deberia ir aquí 
	}
	//añade pokemon a Registros
	private static void addPokeRegistros(int indice) throws IOException {
		FileWriter pokeRegistros = new FileWriter("txts/Registros.txt", true);
		BufferedWriter writRegistros = new BufferedWriter(pokeRegistros);
		writRegistros.newLine();
		writRegistros.write(inventarioPC.get(indice-1).getPokemon()+";Vivo");
		writRegistros.close();
		
	}
	//añade pokemones de Registros a inventarioPC
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
		File arch=new File("txts/Registros.txt");
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
			String pokemonUsuario = partes2[0];
			String estadoBatalla= partes2[1];
			buscarPokemon(pokemonUsuario,estadoBatalla); //busca el pokemon en pokedex.txt y lo agrega, verificando antes si esta vivo o debilitado
			//creo que hay que cambiar esto el pokemon no se repite
			
			
			
			
			
			
			
		}
		
		
		
		s.close();
		
		
		
	}

}
