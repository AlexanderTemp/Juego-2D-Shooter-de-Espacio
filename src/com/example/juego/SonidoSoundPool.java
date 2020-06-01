package com.example.juego;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

public class SonidoSoundPool {
	SoundPool soundPool;
	private int sonidoID=-1;
	public SonidoSoundPool(Context contexto, String nombreArchivo){
		///crea soundpool(max, tipo, calidad)
		soundPool=new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		try {
			AssetManager am=contexto.getAssets();
			AssetFileDescriptor afd=am.openFd(nombreArchivo);
			//lee el archifo (descripcion aasset descriptor, prioridad)
			sonidoID=soundPool.load(afd,  1);
		} catch (Exception e) {
			//no se puede cargar sonido
			e.printStackTrace();
		}
	}
	public void play(){
		//reprodue(sonidoid, vol izq, volumen der, prioridad, repeticion, velocida)
		//reproduce(id, [0..1], [0..1], [0..1], [-1 = c/repetición 0 = s/repetición], 1=normal
		soundPool.play(sonidoID, 0.3f, 0.3f, 0, 0, 1);
		
	}
}
