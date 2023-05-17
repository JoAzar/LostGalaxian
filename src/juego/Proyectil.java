package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Proyectil extends InterfaceJuego {

	double x, y, velocidad, angulo, alto, ancho;
	Image imgMisil;
	Entorno e;
	Proyectil bala;
	
	public Proyectil(Entorno e, double x, double y, double velocidad, double angulo) {
		this.alto = 10;
		this.ancho = 10;
		this.x = x;
		this.y = y;
		this.e = e;
		this.velocidad = velocidad;
		this.angulo = angulo;
		imgMisil = Herramientas.cargarImagen("cohete2.png");
	}
	
	public void avanzar() {
		this.y -= this.velocidad;
	}
	
	public void dibujarMisil() {
		e.dibujarImagen(imgMisil, x, y, angulo, 0.1);
	}
	
	public void dibujarRectangulo() {
		Color color = new Color(166, 98, 218);
		e.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}
	
}
