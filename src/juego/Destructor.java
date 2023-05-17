package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Destructor extends InterfaceJuego {
	double x, y, angulo, escala, velocidad, ancho, alto;;
	Image imgDestructor;
	Entorno e;
	int contMov, dirMov;
	
	public Destructor(Entorno e, double escala) {
		this.alto = 50;
		this.ancho = 50;
		this.contMov = 0;
		this.dirMov = 1;
		this.velocidad = Math.random()*5 + 1;
		this.escala = escala;
		this.x = (Math.random()*(561))+20;
		this.y = -50;
		this.e = e;
		imgDestructor = Herramientas.cargarImagen("destructor3.png");
	}
	
	public boolean estaEnEntorno() {
		if(this.x > 800 || this.y > 600) {
			return false;
		}
		return true;
	}

	public void moverAdelante() {
		this.y += 2 + this.velocidad;
	}
	
	public void moverZigZag() {
        this.y += 2 + this.velocidad;
        contMov++;
        if(contMov >= 20) {
            dirMov *= -1;
            contMov = 0;
        }
        this.x += dirMov * 2;
	}
	
	public void moverVertical() {
		this.y += Math.cos(this.y)*2 + 10;
		this.x += Math.sin(this.x)*2 + 10;
	}
	
	public void dibujarDestructor() {
		e.dibujarImagen(imgDestructor, x, y, angulo, escala);
	}
	
	public void dibujarRectangulo() {
		Color color = new Color(218, 98, 120);
		e.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}
	
}
