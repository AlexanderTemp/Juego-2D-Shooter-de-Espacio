package com.example.juego;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Elfinal {
	public float vertices []=new float[]{
			-4,-6,
			4,-6,
			4,6,
			-4,6,
	};
	FloatBuffer bufVertices;
	public Elfinal(){
		//lee los vertices
		ByteBuffer bufByte=ByteBuffer.allocateDirect(vertices.length*4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices=bufByte.asFloatBuffer();
		bufVertices.put(vertices).rewind();
	}

	public void dibuja(GL10 gl){
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);

		gl.glColor4f(0, 0, 0, 0);

		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

	}

}
