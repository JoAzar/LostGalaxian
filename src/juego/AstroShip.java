package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class AstroShip extends InterfaceJuego {
	int x, y, alto, ancho;
	double angulo, escala;
	Image imgAstroship, imgAura;
	boolean aura;
	Entorno e;
	boolean astroAura = false;
	
	public AstroShip(Entorno e, double angulo) {
		this.alto = 70;
		this.ancho = 70;
		this.escala = 0.1;
		this.x = 400;
		this.y = 500;
		this.e = e;
        this.angulo = 1/2*Math.PI;
		this.imgAstroship = Herramientas.cargarImagen("astronave2.png");
		this.imgAura = Herramientas.cargarImagen("aura3.png");
	}

	//metodo para desplazar la nave
	public void desplazar(int d, double angulo) {
		this.x+= d;
		this.angulo+= angulo;
		
		if(this.angulo > 1.5) {
			this.angulo = 1.5;
		}
		if(this.angulo < -1.5) {
			this.angulo = -1.5;
		}
		if(this.x > 750) {
			this.x = 750;
		}
		if (this.x < 50) {
			this.x = 50;
		}	
	}
	
	//apunta para arriba la nave poniendo el angulo en 0
	public void apuntarArriba() {
		this.angulo = 0;
	}
	
	public void vidaDeLaNave(boolean colision, int cont) {
		if(cont == 0) {
			Color colorLetra = new Color(0, 128, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 50, 5, 0, colorLetra);
		}
		if(colision && cont == 1) {
			Color colorLetra = new Color(0, 128, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 40, 5, 0, colorLetra);
		}
		if(colision && cont == 2) {
			Color colorLetra = new Color(255, 255, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 30, 5, 0, colorLetra);
		}
		if(colision && cont == 3) {
			Color colorLetra = new Color(255, 255, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 20, 5, 0, colorLetra);
		}
		if(colision && cont == 4) {
			Color colorLetra = new Color(255, 0, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 10, 5, 0, colorLetra);
		}
		if(colision && cont == 5) {
			Color colorLetra = new Color(255, 0, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 1, 5, 0, colorLetra);
		}
	}
	
	public boolean activarAura() {
		if(e.estaPresionada(e.TECLA_SHIFT)) {
			this.escala += 0.001;
			if(this.escala > 0.5 ) {
				this.escala *= -1;
			}
			e.dibujarImagen(imgAura, x, y, angulo, escala);
			return astroAura = true;
		}else{
			return astroAura = false;
		}	
	}
	
	public void moverNave() {
		if(e.estaPresionada(e.TECLA_DERECHA)) {
			desplazar(7, 0.1);
		}
		//APRETAR TECLA IZQUIERDA
		else if(e.estaPresionada(e.TECLA_IZQUIERDA)) {
			desplazar(-7, -0.1);
		}else{
			apuntarArriba();
		}
	}
	
	public void dibujarAstroship() {
		e.dibujarImagen(imgAstroship, x, y, angulo, 0.05);
	}
	
	public void dibujarRectangulo() {
		Color color = new Color(166, 98, 218);
		e.dibujarRectangulo(x, y, ancho, alto, angulo, color);
	}
	
}
