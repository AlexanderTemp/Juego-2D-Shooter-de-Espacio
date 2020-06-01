package com.example.juego;



import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.content.Context;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;


public class Renderiza implements Renderer{
	
	
	Context contexto;
	private MainActivity ma;
	private Flecha flecha;
	private Circulo circulo;
	private Enemigo enemigo, nuevo, nuevo1, nuevo2, nuevo3, nuevo4;
	private Elfinal elfin;
	///sonido
	SonidoSoundPool sonidoDisp, sonidoFin;	
		
	/* Desplazamientos */
	float despLineas;
	float despLineasEnemigo;
	float despX_avion;
	float despY_avion;
	float rango;
	float puntos;
	float movimiento;
	float posien;
	float eneX, eneY;
	float sube;
	/* Variable del acelerómetro */
	public float acelerometroX = 0;
	public float acelerometroY=0;
	
	
	public boolean sw;
	public Renderiza(MainActivity mac){
	 this.ma=mac;	
	}	
		
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		sonidoDisp=new SonidoSoundPool(ma, "bullet2.ogg");
		sonidoFin=new SonidoSoundPool(ma, "boom_m.ogg");
		flecha=new Flecha();
		circulo=new Circulo();
		enemigo = new Enemigo();
		nuevo=new Enemigo();
		nuevo1=new Enemigo();
		nuevo2=new Enemigo();
		nuevo3=new Enemigo();
		nuevo4=new Enemigo();

		elfin=new Elfinal();
		movimiento=11;
		despLineas=0;
		despLineasEnemigo=0;
		despX_avion=0;
		despY_avion=0;
		puntos=0;
		eneX=0;
		eneY=0;
		sube=0.05f;
		posien=6;
		rango=0.5f;
		
		sw=false;
		
		gl.glClearColor(53/255f, 71/255f, 91/255f, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		if(sw==false){
			
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//entorno no interacutado
		gl.glLoadIdentity();
		if(eneX>=4)sube*=-1;
		else if(eneX<=-4)sube*=(-1);
		
		eneX+=sube;
		gl.glTranslatef(eneX, 0, 0);
		enemigo.dibuja(gl, despLineasEnemigo);
		gl.glLoadIdentity();
		circulo.dibuja(gl);
		gl.glLoadIdentity();
		if(puntos >= 5){
			gl.glTranslatef(eneX+3, -1, 0);	
			nuevo.dibuja(gl, despLineasEnemigo);
			gl.glLoadIdentity();
		}
		if(puntos >= 10){
			gl.glTranslatef(eneX+2, -2, 0);	
			nuevo1.dibuja(gl, despLineasEnemigo);
			gl.glLoadIdentity();
		}
		if(puntos >= 20){
			gl.glTranslatef(eneX-1, -3, 0);	
			nuevo2.dibuja(gl, despLineasEnemigo);
			gl.glLoadIdentity();
		}
		if(puntos >= 30){
			gl.glTranslatef(eneX-3, -4, 0);	
			nuevo3.dibuja(gl, despLineasEnemigo);
			gl.glLoadIdentity();
		}
		 
		if(puntos >= 50){
			gl.glTranslatef(eneX+1, -5, 0);	
			nuevo.dibuja(gl, despLineasEnemigo);
			gl.glLoadIdentity();
		}
		//ma.actualizaTexto(despLineasEnemigo+" - " +despY_avion);		
		if((eneX<=despX_avion+0.1)&&(eneX>=despX_avion-0.1)&&(despLineas>=5)){
			sonidoFin.play();
			puntos+=1;
			sube+=0.02f;
			ma.actualizaTexto("Puntaje: "+puntos);
			despLineasEnemigo+=0.05;
			gl.glLoadIdentity();
			enemigo.dibuja(gl, despLineasEnemigo);
			gl.glLoadIdentity();
			rango+=0.03f;
			
		}
		if((despLineasEnemigo+despY_avion)<=rango&&(despLineasEnemigo+despY_avion>0)&&(eneX>=despX_avion-0.15)&&eneX<=despX_avion+0.15){
			sonidoFin.play();
			sw=true;
		}
		
		
		////movimiento nave y disparo
		gl.glLoadIdentity();
		if(acelerometroX>0.5)despX_avion-=0.08f;
		else if(acelerometroX<-0.5)despX_avion+=0.08f;
		if(acelerometroY>0.5){despY_avion-=0.07f;movimiento+=0.07;}
		else if(acelerometroY<-0.5){despY_avion+=0.07f;movimiento-=0.07;}
		if(despX_avion>4)despX_avion=4f;
		else if(despX_avion<-4)despX_avion=-4f;
		if(despY_avion> 11)despY_avion=11f;
		else if(despY_avion < -1)despY_avion=-1f;
		gl.glTranslatef(despX_avion, despY_avion, 0);
		flecha.dibuja(gl, despLineas);
		gl.glLoadIdentity();
		//modifica la velocidad de disparo

	
		despLineas+=0.40;
		if(despLineas > 12){despLineas=0;	sonidoDisp.play();}	
		despLineasEnemigo-=0.1;
		if(despLineasEnemigo <-12){despLineasEnemigo=0;sonidoDisp.play();}
		
		}else {
			gl.glLoadIdentity();
			elfin.dibuja(gl);
			ma.actualizaTexto("\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\tPierdes!!" +"\n\n\n"+
					"Tu Puntaje Final: "+(int)puntos);	
		}
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}
}
