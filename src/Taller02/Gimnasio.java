package Taller02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gimnasio {
	private List<Pokemones> equipoEnemigo = new ArrayList<>();//lista de los pokemon de los enemigos
	private int id;
	private String nombre;
	private String estado;
	
	public List<Pokemones> getEquipoEnemigo() {
		return equipoEnemigo;
	}
	
	public Gimnasio(int id, String nombre, String estado) {
		
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEstado() {
		return estado;
	}
	public String derrotado() {
		return estado="Derrotado";
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

	@Override
	public String toString() {
		return id+") " + nombre + " - Estado:" + estado ;
	}

	
	}

