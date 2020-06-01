package com.example.juego;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class Circulo {
	private FloatBuffer bufVertices;
	private int segmentos;
	private float radio;
	
	public Circulo(){
		this.segmentos=360;
		this.radio=0.05f;
		ByteBuffer bufByte=ByteBuffer.allocateDirect(360*2*4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices=bufByte.asFloatBuffer();
		int j=0;
		for (float i = 0; i < 360.0f; i = i + 360.0f/segmentos) {
			bufVertices.put(j++, (float) Math.cos(Math.toRadians(i))*radio);
			bufVertices.put(j++, (float) Math.sin(Math.toRadians(i))*radio);
		}
		bufVertices.rewind();
	}
	
	public void dibuja(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2,  GL10.GL_FLOAT, 0, bufVertices);
		//renderiza las primitivas desde los datos del arreglo de vertices
		gl.glColor4f(203/255f, 203/255f, 203/255f, 0);

		Random rand=new Random();
		float minx=-4, maxx=4;
		float miny=-6, maxy=6;
		
		float x, y;
		for (int i = 0; i < 5; i++) {
			gl.glLoadIdentity();
			x=rand.nextFloat()*(maxx-minx)+minx;
			y=rand.nextFloat()*(maxy-miny)+(miny);
			gl.glTranslatef(x, y, 0);
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0,segmentos);	
		}

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
