package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Asteroide extends InterfaceJuego {

	double x, y, velocidad, angulo, escala, alto, ancho;
	Image imgAsteroide;
	Entorno e;
	
	public Asteroide(Entorno e, double escala) {
		this.alto = 50;
		this.ancho = 50;
		this.velocidad = Math.random()*10 + 1;
		this.escala = (Math.random() * (0.03 - 0.02)) + 0.02;
		this.x = (Math.random()*(451))+50;
		this.y = -50;
		this.e = e;
		this.angulo = 0;
		imgAsteroide = Herramientas.cargarImagen("asteroide.png");
	}
	
	public void avanzarDer() {
		this.x++;
		this.y++;
		if(this.y > 650 || this.x > 850) {
			this.x = (Math.random()*(451))+50;
			this.y = -10;
		}
	}
	
	public void avanzarIzq() {
		this.x--;
		this.y++;
		if(this.y < -50 || this.x < -50) {
			this.x = (Math.random()*(451))+50;
			this.y = -10;
		}
	}
	
	public void dibujarAsteroide() {
		e.dibujarImagen(imgAsteroide, x, y, angulo, escala);
		
	}
	
	public void dibujarRectangulo() {
		Color color = new Color(218, 166, 98);
		e.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}
}
