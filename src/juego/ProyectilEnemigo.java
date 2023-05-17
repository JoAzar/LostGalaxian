package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class ProyectilEnemigo extends InterfaceJuego {

	double x, y, velocidad, angulo, escala, alto, ancho;
	Image imgMisilEnemigo;
	Entorno e;
	
	public ProyectilEnemigo(Entorno e, double x, double y, double escala, double velocidad) {
		this.alto = 15;
		this.ancho = 15;
		this.x = x;
		this.y = y;
		this.e = e;
		this.velocidad = velocidad;
		this.escala = 0.04;
		imgMisilEnemigo = Herramientas.cargarImagen("disparoEnemigo.png");
	}
	
	public void avanzar() {
		this.y += this.velocidad;
	}
	
	public boolean estaEnEntorno() {
		if(this.x > 800 || this.y > 600) {
			return false;
		}
		return true;
	}
	
	public void dibujarIon() {
		e.dibujarImagen(imgMisilEnemigo, x, y, 0, escala);
	}
	
	public void dibujarRectangulo() {
		Color color = new Color(218, 98, 120);
		e.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}	
}
