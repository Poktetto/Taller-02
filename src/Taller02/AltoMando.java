package Taller02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AltoMando {
	private List<Pokemones> equipoEnemigo = new ArrayList<>();//lista de los pokemon de los enemigos
	private int id;
	private String nombre;

	public AltoMando(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	
	
	public void agregarPokemones(String pokemon) throws FileNotFoundException {
		//variables  de File
		File arcPokedex = new File("txts/Pokedex.txt");
		Scanner scanPokedex = new Scanner(arcPokedex);
		int contT=0;
		//ciclo de carga
		while(scanPokedex.hasNextLine()) {
			String linea = scanPokedex.nextLine();
			String[] pokePartes = linea.split(";");
			if (pokePartes[0].equals(pokemon)){
				Pokemones p = new Pokemones(pokePartes[0],pokePartes[1],Double.parseDouble(pokePartes[2]),Integer.parseInt(pokePartes[3]),Integer.parseInt(pokePartes[4]),Integer.parseInt(pokePartes[5]),Integer.parseInt(pokePartes[6]),Integer.parseInt(pokePartes[7]),Integer.parseInt(pokePartes[8]),pokePartes[9]);
				equipoEnemigo.add(p);
					
				}
				
			}
		
		}

	public List<Pokemones> getEquipoEnemigo() {
		return equipoEnemigo;
	}

}
