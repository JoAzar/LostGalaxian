package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Powerup extends InterfaceJuego{
	double x, y, alto, ancho;
	double angulo, escala;
	Image imgPowerUp;
	Entorno e;
	
	Powerup(Entorno e, double angulo){
		this.ancho = 30;
		this.alto = 30;
		this.x = (Math.random()*(561))+20;
		this.y = -50;
		this.angulo = angulo;
		this.e = e;
		this.escala  = 0.5;
		imgPowerUp = Herramientas.cargarImagen("powerUp.png");
	}
	
	public void dibujarPowerUp() {
		e.dibujarImagen(imgPowerUp, x, y, angulo, 0.05);
	}
	
	public void avanzarEnPantalla() {
		this.y++;
	}
	
	public boolean estaEnEntorno() {
		if(this.x > 800 || this.y > 600) {
			return false;
		}
		return true;
	}
	
	public void dibujarRectangulo() {
		Color color = new Color(98, 120, 218);
		e.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}
}
