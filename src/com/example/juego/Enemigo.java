package com.example.juego;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Enemigo {
	private float vertices []=new float[]{
			//cuerpo
			-0.13f, -0.13f+5f,///1 
			-0.13f, 0.13f+5f,///1 
			0.13f, -0.13f+5f,///1 

			0.13f, -0.13f+5f,///1 
			-0.13f, 0.13f+5f,///1 
			0.13f, 0.13f+5f,///1 

			//punta
			0.13f, -0.13f+5f,///1 
			-0.13f, -0.13f+5f,///1 
			0.0f, -0.3f+5f,///1 

			///ala derecha
			0.13f, -0f+5f,
			0.13f, -0.1f+5f,
			0.23f,-0.1f+5f,

			0.13f, -0.1f+5f,
			0.23f,-0.1f+5f,
			0.13f, -0.3f+5f,


			//ala izquierda
			-0.13f, 0f+5f,
			-0.13f, -0.1f+5f,
			-0.23f,-0.1f+5f,

			-0.13f, -0.1f+5f,
			-0.23f,-0.1f+5f,
			-0.13f, -0.3f+5f,


	};
	public float bala[]=new float[]{
			0.05f, -0.3f+5f,///1
			-0.05f, -0.3f+5f,///1
			0.05f, -0.3f+4.7f,///1
			-0.05f, -0.3f+4.7f///1
	};
	FloatBuffer bufVertices, bufVerticesBala;
	public Enemigo(){
		//lee los vertices
		ByteBuffer bufByte=ByteBuffer.allocateDirect(vertices.length*4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices=bufByte.asFloatBuffer();
		bufVertices.put(vertices).rewind();
		
		///leee la bala
		bufByte=ByteBuffer.allocateDirect(bala.length*4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVerticesBala=bufByte.asFloatBuffer();
		bufVerticesBala.put(bala).rewind();
	}
	
	public void dibuja(GL10 gl, float desplazamiento){
		/* Se habilita el acceso al arreglo de vértices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		/* Se especifica los datos del arreglo de vértices */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		/* Se establece el color en (r,g,b,a) */
		
		gl.glColor4f(0/255f, 0/255f, 0/255f, 0);
		/* Dibuja el rectángulo */
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
		
		gl.glColor4f(0/255f, 0/255f, 216/255f, 0);
		/* Dibuja el rectángulo */
		gl.glDrawArrays(GL10.GL_TRIANGLES, 6, 3);
		
		
		gl.glColor4f(255/255f, 0/255f, 0/255f, 0);
		/* Dibuja el rectángulo */
		gl.glDrawArrays(GL10.GL_TRIANGLES, 9, 12);
		
		
		/* Se deshabilita el acceso al arreglo de vértices */
		
		gl.glPushMatrix();
		gl.glTranslatef(0, desplazamiento, 0);
		gl.glColor4f(1, 1, 1, 1);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesBala);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
}
