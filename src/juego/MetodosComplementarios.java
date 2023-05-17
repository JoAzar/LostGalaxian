package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class MetodosComplementarios extends InterfaceJuego {	
	Proyectil misil;
	ProyectilEnemigo misilEnemigo;
	Destructor[] destructor;
	Asteroide[] asteroide;
	AstroShip astroship;
	Powerup powerup;
	boolean colision;
	int cont, score, limite, impactos;
	int cantEnemigosEliminados;
	double angulo;
	Image explota;
	Image fondo;
	
	public void crearObjetos(Entorno entorno) {
		angulo = 0;
		colision = false;
		cont = 0;
		score = 0;	//los puntos qe suma durante el juego
		limite = 3;	//este valor se modifica para cambiar el limite de score necesario para ganar el juego
		impactos = 0;	//impactos es un contador de 2 para darle cierta resistencia a los asteroides
		cantEnemigosEliminados = 0;
		destructor = new Destructor[5]; 
		asteroide = new Asteroide[5];
		astroship = new AstroShip(entorno, 0);
		powerup = new Powerup(entorno, 0.1);
		explota = Herramientas.cargarImagen("explota.png");
		Herramientas.cargarSonido("musicaFondo2.wav").start();
		
		for (int i = 0; i < destructor.length; i++) {
			destructor[i] = new Destructor(entorno, 0.1);
			misilEnemigo = new ProyectilEnemigo(entorno, destructor[i].x, destructor[i].y, 0, destructor[i].velocidad);
			asteroide[i] = new Asteroide(entorno, 0);	//el metodo me obliga a crear dos valores minimos
		}
	}
	
	public void correrMetodos(Entorno entorno) {
		for (int i = 0; i < this.destructor.length-1; i++) {
			controlarPuntos(entorno, i);
			dibujarTodo(entorno, i);					//dibuja la nave y el destructor
			todasLasColisiones(entorno, i);			//todas las colisiones asteroide misil nave destructor
			detenerObjetos(entorno, i);
		}
	}
	
	public boolean colision(double x1, double x2, double y1, double y2, double alto1, double alto2, double ancho1, double ancho2) {	//CALCULO DE COLISIONES 
        //return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) < dist*dist; //anulado no funciona bien
		ancho1 += x1;
		alto1 += y1;
		ancho2 += x2;
		alto2 += y2;
        return (ancho1 >= x2 && x1 <= ancho2  && alto1 >= y2 && y1 <= alto2);
    }

	public void controlarPuntos(Entorno entorno, int i) {		//CONTROLAR SCORE, DEVUELVE UN BOOLEANO TRUE IF SCORE=5 SINO FALSE
		if(score == limite) {
			Color colorLetra = new Color(250, 250, 250);
			Color colorLetra2 = new Color(255, 255, 255);
			double x = 450;
			double y = 575; 
			double ancho = 250;
			double alto = 50;
			double angulo = 0; 
			Color colorBorde = new Color(250, 250, 250);
			Color colorRelleno = new Color(0, 0, 0);		
			entorno.dibujarRectangulo(x, y, ancho+5, alto+5, angulo, colorBorde);
			entorno.dibujarRectangulo(x, y, ancho, alto, angulo, colorRelleno);
			entorno.cambiarFont("ARIAL", 60, colorLetra);
			entorno.escribirTexto("-= YOU WIN =-",  200, 300);
			entorno.cambiarFont("ARIAL", 20, colorLetra);
			entorno.escribirTexto("Pulse ENTER para continuar",  290, 400);
			entorno.cambiarFont("Verdana", 18, colorLetra);
			entorno.escribirTexto("PUNTAJE: " + score,  351, 570);
			entorno.cambiarFont("Verdana", 18, colorLetra2);
			entorno.escribirTexto("PUNTAJE: " + score,  350, 570);
			entorno.cambiarFont("Verdana", 18, colorLetra);
			entorno.escribirTexto("Enemigos eliminados: " + cantEnemigosEliminados,  351, 590);
			entorno.cambiarFont("Verdana", 18, colorLetra2);
			entorno.escribirTexto("Enemigos eliminados: " + cantEnemigosEliminados,  350, 590);	
			//HAY QUE CREAR UN METODO PARA HACER NULL TODOS LOS OBJETOS DE PANTALLA
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				nuevoJuego(entorno);
			}
		}else if(cont > 5){
			Color colorLetra = new Color(250, 250, 250);
			Color colorLetra2 = new Color(255, 255, 255);
			double x = 450;
			double y = 575; 
			double ancho = 250;
			double alto = 50;
			double angulo = 0; 
			Color colorBorde = new Color(250, 250, 250);
			Color colorRelleno = new Color(0, 0, 0);
			entorno.cambiarFont("ARIAL", 60, colorLetra);
			entorno.escribirTexto("-= GAME OVER =-",  140, 300);
			entorno.cambiarFont("ARIAL", 20, colorLetra);
			entorno.escribirTexto("Pulse ENTER para continuar",  290, 400);
			entorno.dibujarRectangulo(x, y, ancho + 5, alto + 5, angulo, colorBorde);
			entorno.dibujarRectangulo(x + 1, y, ancho, alto, angulo, colorRelleno);
			entorno.cambiarFont("Verdana", 18, colorLetra);
			entorno.escribirTexto("PUNTOS:" + score,  351, 578);
			entorno.cambiarFont("Verdana", 18, colorLetra2);
			entorno.escribirTexto("PUNTOS:" + score,  350, 579);
			entorno.cambiarFont("Verdana", 18, colorLetra);
			entorno.escribirTexto("ELIMINADOS: " + cantEnemigosEliminados,  351, 595);
			entorno.cambiarFont("Verdana", 18, colorLetra2);
			entorno.escribirTexto("ELIMINADOS: " + cantEnemigosEliminados,  350, 595);
			//HAY QUE CREAR UN METODO PARA HACER NULL TODOS LOS OBJETOS DE PANTALLA
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				nuevoJuego(entorno);
			}
		}else {
			Color colorLetra = new Color(250, 250, 250);
			Color colorLetra2 = new Color(255, 255, 255);
			double x = 450;
			double y = 575; 
			double ancho = 250;
			double alto = 50;
			double angulo = 0; 
			Color colorBorde = new Color(250, 250, 250);
			Color colorRelleno = new Color(0, 0, 0);
			entorno.dibujarRectangulo(x, y, ancho + 5, alto + 5, angulo, colorBorde);
			entorno.dibujarRectangulo(x + 1, y, ancho, alto, angulo, colorRelleno);
			entorno.cambiarFont("Verdana", 18, colorLetra);
			entorno.escribirTexto("PUNTOS:" + score,  351, 578);
			entorno.cambiarFont("Verdana", 18, colorLetra2);
			entorno.escribirTexto("PUNTOS:" + score,  350, 579);
			entorno.cambiarFont("Verdana", 18, colorLetra);
			entorno.escribirTexto("ELIMINADOS: " + cantEnemigosEliminados,  351, 595);
			entorno.cambiarFont("Verdana", 18, colorLetra2);
			entorno.escribirTexto("ELIMINADOS: " + cantEnemigosEliminados,  350, 595);
			//HAY QUE CREAR UN METODO PARA HACER NULL TODOS LOS OBJETOS DE PANTALLA
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				nuevoJuego(entorno);
			}
		}
	}

	public void nuevoJuego(Entorno entorno) {	//IF FINDELJUEGO MUESTRA EN PANTALLA UN CARTEL Y SI SE OPRIME ENTER LLAMA A REINICIARVALORES();
		Color colorLetra = new Color(250, 250, 250);
		Color colorLetra2 = new Color(255, 255, 255);
		double empieza = 400;
		double y = 290; 
		double ancho = 500;
		double alto = 30;
		double angulo = 0; 
		Color colorBorde = new Color(250, 250, 250);
		Color colorRelleno = new Color(0, 0, 0);	
		entorno.dibujarRectangulo(empieza, y, ancho, alto, angulo, colorBorde);
		entorno.dibujarRectangulo(empieza, y, ancho-2, alto-2, angulo, colorRelleno);
		entorno.cambiarFont("Verdana", 18, colorLetra);
		entorno.escribirTexto("< OPRIMA ENTER PARA JUGAR DE NUEVO >", 201, 300);
		entorno.cambiarFont("Verdana", 18, colorLetra2);
		entorno.escribirTexto("< OPRIMA ENTER PARA JUGAR DE NUEVO >", 200, 300);
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			reiniciarValores(entorno);
		}
	}
	
	public void reiniciarValores(Entorno entorno) {	//SE LLAMAN A LOS VALORES DEL CONSTRUCTOR !!NO SE PUEDE LLAMAR AL CONSTRUCTOR PORQUE INICIA UN NUEVO CUADRO
		cont = 0;
		score = 0;
		impactos = 0;
		cantEnemigosEliminados = 0;
		fondo = Herramientas.cargarImagen("fondo6.jpg");
		this.explota = Herramientas.cargarImagen("explota.png");
		destructor = new Destructor[4];
		asteroide = new Asteroide[4];
		for (int i = 0; i < destructor.length; i++) {
			destructor[i] = new Destructor(entorno, 0.1);
			misilEnemigo = new ProyectilEnemigo(entorno, this.destructor[i].x, this.destructor[i].y, 0, this.destructor[i].velocidad);
			asteroide[i] = new Asteroide(entorno, 1);
		}
		astroship = new AstroShip(entorno, 0);
		entorno.iniciar();		
	}
	
	public void girarFondo() {	//HACE GIRAR EL FONDO EN SU PROPIO EJE gira en un sentido y en el otro atrapado en el tiempo
		angulo = angulo + 0.0005;
		if(angulo >= 3.141516) {			//oh si he creado el universo infinito xD
			angulo *= -1;
		}
	}
	
	public void direccionAsteroide(int i) {	//USA EL I DEL CICLO SI ES PAR TOMA UN RUMBO SINO OTRO, ME PARECÍA LO MÁS SIMPLE SIN USAR RANDOM
		if(i%2==0) {
			asteroide[i].avanzarDer();
		}else{
			asteroide[i].avanzarIzq();
		}
	}
	
	public void movimientoDestructor(int i) {	//LLAMA AL METODO ZIGZAG DEL OBJETO DESTRUCTOR
		if(destructor[i]!= null) {
			destructor[i].moverZigZag();
		}
	}
	
	public void colisionesIones(Entorno entorno, int i) { //MISIL ENEMIGO
		
		if(!misilEnemigo.estaEnEntorno()) {
			misilEnemigo = null;
			misilEnemigo = new ProyectilEnemigo(entorno, destructor[i].x, destructor[i].y, 0, destructor[i].velocidad);
		}
	}
	
	public void colisionesAstroship(Entorno entorno, int i) {	//colisiones de astroship
		if(astroship.activarAura() == false && misilEnemigo != null && astroship != null && colision(misilEnemigo.x, astroship.x, misilEnemigo.y, astroship.y, misilEnemigo.alto, astroship.alto, misilEnemigo.ancho, astroship.ancho)) {
			misilEnemigo = null;
			misilEnemigo = new ProyectilEnemigo(entorno, destructor[i].x, destructor[i].y, 0, destructor[i].velocidad);
			score--;
			colision = true;
			cont++;
			astroship.vidaDeLaNave(colision, cont);
		}
		
		//COLISION CON AURA DE ASTROSHIP CON DISPAROS DE DESTRUCTORES	"NO HACE DAÑO Y NO BAJA SCORE"
		if(astroship.activarAura() && misilEnemigo != null && astroship != null && colision(misilEnemigo.x, astroship.x, astroship.y, misilEnemigo.y, astroship.alto, misilEnemigo.alto, astroship.ancho, misilEnemigo.ancho)) {
			misilEnemigo = null;
			misilEnemigo = new ProyectilEnemigo(entorno, destructor[i].x, destructor[i].y, 0, destructor[i].velocidad);
		}
				
		//COLISION SIN AURA DE ASTROSHIP CON DESTRUCTORES	"HACE DAÑO Y BAJA SCORE"
		if(astroship.activarAura() == false && astroship != null && destructor[i] != null && colision(astroship.x, destructor[i].x, astroship.y, destructor[i].y, astroship.alto, destructor[i].alto, astroship.ancho, destructor[i].ancho)) {
			destructor[i] = null;
			destructor[i] = new Destructor(entorno, 0.1);
			score--;
			colision = true;
			cont++;
			astroship.vidaDeLaNave(colision, cont);
		}
		
		//COLISION CON AURA DE ASTROSHIP CON DESTRUCTORES 	"NO HACE DAÑO PERO BAJA SCORE"
		if(astroship.activarAura() && astroship != null && destructor[i] != null && colision(astroship.x, destructor[i].x, astroship.y, destructor[i].y, astroship.alto, destructor[i].alto, astroship.ancho, destructor[i].ancho)) {
			destructor[i] = null;
			score--;
			destructor[i] = new Destructor(entorno, 0.1);
		}
		
		//COLISION SIN AURA DE ASTROSHIP CON ASTEROIDES 	"HACE DAÑO NO BAJA SCORE"
		if(astroship.activarAura() == false && astroship != null && asteroide[i] != null && colision(astroship.x, asteroide[i].x, astroship.y, asteroide[i].y, astroship.alto, asteroide[i].alto, astroship.ancho, asteroide[i].ancho)) {
			asteroide[i] = null;
			asteroide[i] = new Asteroide(entorno, 1);
			colision = true;
			cont++;
			astroship.vidaDeLaNave(colision, cont);
		}
		
		//COLISION CON AURA DE ASTROSHIP CON ASTEROIDES "BAJA SCORE"
		if(astroship.activarAura() && astroship != null && asteroide[i] != null && colision(astroship.x, asteroide[i].x, astroship.y, asteroide[i].y, astroship.alto, asteroide[i].alto, astroship.ancho, asteroide[i].ancho)) {
			asteroide[i] = null;
			score--;
			asteroide[i] = new Asteroide(entorno, 1);
		}
	}
	
	public void colisionesAsteroide(Entorno entorno, int i) {	//COLISION DE MISIL CON ASTEROIDES	"SUBE SCORE EN 1"
		if(misil != null && asteroide[i] != null && colision(misil.x, asteroide[i].x, misil.y, asteroide[i].y, misil.alto, asteroide[i].alto, misil.ancho, asteroide[i].ancho)) {
			misil = null;
			score++;
			//LOS ATEROIDES TIENEN 2 VIDAS/IMPACTOS PARA SER DESTRUIDOS Y GENERAN SALUD EN 1 PUNTO POR CADA IMPACTO
			impactos++;
			if(impactos == 2) {
				asteroide[i] = null;
				asteroide[i] = new Asteroide(entorno, 0);
				impactos = 0;
			}
			if(cont > 0) {
				cont--;
			}
		}
		if(asteroide[i] != null && colision(asteroide[i].x, asteroide[i+1].x, asteroide[i].y, asteroide[i+1].y, asteroide[i].alto, asteroide[i+1].alto, asteroide[i].ancho, asteroide[i+1].ancho)) {
			asteroide[i] = null;
			asteroide[i] = new Asteroide(entorno, 0);
		}
	}
	
	public void colisionesDestructor(Entorno entorno, int i) {	//colisiones, sonidos, suma cont y cant, null y crear destructores //COLISION DE MISIL CON DESTRUCTORES	"SUBE SCORE EN 1"
		//COLISION ENTRE MISIL Y DESTRUCTORES
		if(misil != null && destructor[i] != null && colision(misil.x, destructor[i].x, misil.y, destructor[i].y, misil.alto, destructor[i].alto, misil.ancho, destructor[i].ancho)) {
			Herramientas.cargarSonido("explosionSound.wav").start();
			misil = null;
			entorno.dibujarImagen(explota, destructor[i].x, destructor[i].y, 0, 0.05);
			destructor[i] = null;
			destructor[i] = new Destructor(entorno, 0.1);
			score++;
			cantEnemigosEliminados++;
		}
		//COLISION ENTRE DESRTUCTORES
		if(destructor[i] != null && destructor[i+1] != null && colision(destructor[i].x, destructor[i+1].x, destructor[i].y, destructor[i+1].y, destructor[i].alto, destructor[i+1].alto, destructor[i].ancho, destructor[i+1].ancho)) {	//destructor.length y length-1
			entorno.dibujarImagen(explota, destructor[i].x, destructor[i].y, 0, 0.05);
			destructor[i] = null;
			destructor[i] = new Destructor(entorno, 0.1);
			destructor[i+1] = null;
			destructor[i+1] = new Destructor(entorno, 0.1);
		}
		//COLISION CON ASTEROIDES
		if(asteroide[i] != null && destructor[i] != null && destructor[i] != null && colision(destructor[i].x, asteroide[i].x, destructor[i].y, asteroide[i].y, destructor[i].alto, asteroide[i].alto, destructor[i].ancho, asteroide[i].ancho)) {
			entorno.dibujarImagen(explota, destructor[i].x, destructor[i].y, 0, 0.05);
			destructor[i] = null;
			destructor[i] = new Destructor(entorno, 0.1);
		}
	}
	
	public void colisionesPowerup(Entorno entorno, int i) {	//COLISION SIN AURA DE ASTROSHIP CON POWER UP CURA VIDA
		if(astroship.activarAura() == false && astroship != null && powerup != null && colision(astroship.x, powerup.x, astroship.y, powerup.y, astroship.alto, powerup.alto, astroship.ancho, powerup.ancho)) {
			powerup = null;
			powerup = new Powerup(entorno, 0);
			if(cont > 0) {
				cont--;
			}
		}
		//COLISION CON AURA DE ASTROSHIP CON POWER UP
		if(astroship.activarAura() && astroship != null && powerup != null && colision(astroship.x, powerup.x, astroship.y, powerup.y, astroship.alto, powerup.alto, astroship.ancho, powerup.ancho)) {
			powerup = null;
			powerup = new Powerup(entorno, 0);
			astroship.vidaDeLaNave(colision, cont);
			if(cont > 0) {
				cont--;
			}
		}
	}
	
	public void todasLasColisiones(Entorno entorno,int i) {	//llama a todos los métodos de colisiones
		colisionesAstroship(entorno, i);
		colisionesDestructor(entorno, i);
		colisionesIones(entorno, i);
		colisionesAsteroide(entorno, i);
		colisionesPowerup(entorno, i);
	}
	
	public void verificarNulidadIon(int i) {		//ESTE METODO DEBE VERIFICAR LA NULIDAD DE ION
		if(destructor[i] != null) {
			misilEnemigo.dibujarIon();
			misilEnemigo.dibujarRectangulo();
			misilEnemigo.avanzar();
		}
	}
	
	public void verificarNulidadPowerUp(Entorno entorno) {		//ESTE METODO DEBE VERIFICAR LA NULIDAD DE POWER UP
		if(powerup != null) {
			powerup.dibujarPowerUp();
			powerup.dibujarRectangulo();
			powerup.avanzarEnPantalla();
		}
		if(!powerup.estaEnEntorno()) {			//FALTA REVISAR ESTA PARTE, DISPARA FUERA DE JUEGO
			powerup = null;						//FALTA REVISAR ESTA PARTE, DISPARA FUERA DE JUEGO
			powerup = new Powerup(entorno, 0);	//FALTA REVISAR ESTA PARTE, DISPARA FUERA DE JUEGO
		}
	}
	
	public void verificarNulidadDestructor(Entorno entorno, int i) {		//ESTE METODO DEBE VERIFICAR LA NULIDAD DE DESTRUCTORES
		if(destructor[i] != null) {
			destructor[i].dibujarDestructor();
			destructor[i].dibujarRectangulo();
		}
		if(!destructor[i].estaEnEntorno()) {	
			  destructor[i] = null;
			  destructor[i] = new Destructor(entorno, 0.1);	//AL ANULAR EL DESTRUCTOR TENGO QUE CREAR UNOS MAS PARA QUE NO TIRE ERROR
		}
	}
	
	public void verificarNulidadAstroship() {		//ESTE METODO DEBE VERIFICAR LA NULIDAD DE ASTROSHIP
		if(astroship != null) {
			astroship.dibujarAstroship();
			astroship.dibujarRectangulo();
		}
	}
	
	public void verificarNulidadAsteroide(int i) {		//ESTE METODO DEBE VERIFICAR LA NULIDAD DE ASTEROIDES
		if(asteroide[i] != null) {
			asteroide[i].dibujarAsteroide();
			asteroide[i].dibujarRectangulo();
		}
	}
	
	public void verificarNulidadMisil(Entorno entorno) {		//ESTE METODO DEBE VERIFICAR LA NULIDAD DE MISIL
		if(misil != null) {
			dispararNave(entorno);
			misil.dibujarMisil();
			misil.dibujarRectangulo();
			misil.avanzar();
		}
		if(misil != null && misil.y < 0) {			//FALTA REVISAR ESTA PARTE, DISPARA FUERA DE JUEGO
			misil = null;							//FALTA REVISAR ESTA PARTE, DISPARA FUERA DE JUEGO
		}
	}
	
	public void dispararNave(Entorno entorno) {	//DISPAROS DE LA NAVE APRENTANDO BARRA ESPACIADORA 1 TIRO POR PULSACIÓN
		if(entorno.sePresiono(entorno.TECLA_ESPACIO) && misil == null) {
			misil = new Proyectil(entorno, astroship.x, astroship.y, 10, 2);
			Herramientas.cargarSonido("lazer2.wav").start();
		}
	}
	
	public void dibujarTodo(Entorno entorno, int i) {		//llama al método VerificarNulidad de cada objeto que verifica y dibuja
			verificarNulidadMisil(entorno);
			verificarNulidadAstroship();
			verificarNulidadDestructor(entorno, i);
			verificarNulidadAsteroide(i);
			verificarNulidadPowerUp(entorno);
			colisionesIones(entorno, i);
	//		verificarNulidadBoss();		//no implementado aun
	}

	public void detenerObjetos(Entorno entorno, int i) {		//CONTROLA QUE SI EL JUEGO TERMINA LOS OBJETOS SE DETENGAN
		if(score < limite && cont <= 5) {
			dibujarTodo(entorno, i);
			direccionAsteroide(i);
			dispararNave(entorno);
			movimientoDestructor(i);
		}
	}
	
}
