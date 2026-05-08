package Taller02;

public class Pokemones {
	private String pokemon;
	private String habitat;
	private Double porcentajeAparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private int statTotal;
	private String estado="Vivo";
	public Pokemones(String pokemon, String habitat, Double porcentajeAparicion, int vida, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, String tipo) {
		super();
		this.pokemon = pokemon;
		this.habitat = habitat;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
	}
	public String getPokemon() {
		return pokemon;
	}
	public String getHabitat() {
		return habitat;
	}
	public Double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}
	public int getVida() {
		return vida;
	}
	public int getAtaque() {
		return ataque;
	}
	public int getDefensa() {
		return defensa;
	}
	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}
	public int getDefensaEspecial() {
		return defensaEspecial;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public String getTipo() {
		return tipo;
	}
	public String getestado() {
		return estado;
		
	}
	public String derrotado() {
		return estado="Debilitado";
	}

	public int getStatTotales() {
		return statTotal=ataque+vida+defensa+ataqueEspecial+defensaEspecial+velocidad;
	}
	public void setPorcentajeAparicion(Double porcentajeAparicion) { //para el porcemtaje total de un habitad sea 1 
		this.porcentajeAparicion = porcentajeAparicion;
	}
	//to String por motivos de testeo
	@Override
	public String toString() {
		return "Pokemones [pokemon=" + pokemon + ", habitat=" + habitat + ", porcentajeAparicion=" + porcentajeAparicion
				+ ", vida=" + vida + ", ataque=" + ataque + ", defensa=" + defensa + ", ataqueEspecial="
				+ ataqueEspecial + ", defensaEspecial=" + defensaEspecial + ", velocidad=" + velocidad + ", tipo="
				+ tipo + ", statTotal=" + statTotal + ", estado=" + estado + "]";
	}
	
	

}
