package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;
	MetodosComplementarios complemento;
	int ANCHO = 800;
	int ALTO = 600;
	
	Juego() {
		complemento = new MetodosComplementarios();
		entorno = new Entorno(this, "Zalazar compa1 compa2 - Grupo 1", ANCHO, ALTO);
		complemento.fondo = Herramientas.cargarImagen("fondo6.jpg");			
		complemento.crearObjetos(entorno);
		this.entorno.iniciar();	
	}
	
	public void tick() {
		entorno.dibujarImagen(complemento.fondo, ANCHO - 200, ALTO - 300, complemento.angulo, 1);
		complemento.girarFondo();
		complemento.astroship.vidaDeLaNave(complemento.colision, complemento.cont);
		complemento.astroship.moverNave();
		complemento.dispararNave(this.entorno); 
		complemento.correrMetodos(this.entorno);
		
	}
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}