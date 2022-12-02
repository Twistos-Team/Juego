package com.mygdx.autitos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import patterndesign.DefObstaculo;
import patterndesign.ObsBuilder;

public class ListaObstaculos{
	private Array<Obstaculo> lista;
	
	public ListaObstaculos() {
		lista = new Array<Obstaculo>();
	}
	
	public void clear() {
		lista.clear();
	}
	
	public void crearObstaculo() {
		int rd = MathUtils.random(0,50);
		// Corazon 0, Moneda 1, Enemigo 2
		/*
		Enemigos:
		2 - Azul
		3 - Amarillo
		4 - Negro
		5 - Camion
		 */
		
		if (rd > 18) rd = 2;
		else if (rd != 0) rd = 1;
		
		if (rd == 2)
			rd = MathUtils.random(2,5);
		
		DefObstaculo defO = new DefObstaculo();
		ObsBuilder builder = new ObsBuilder();
		
		switch(rd) {
			case 0: defO.createHeart(builder);
				    break;
		
			case 1: defO.createCoin(builder);
				    break;
		
			case 2: defO.createBlueCar(builder);
				    break;
		
			case 3: defO.createYellowCar(builder);
				    break;
				
			case 4: defO.createBlackCar(builder);
		 		    break;
		
			case 5: defO.createTruck(builder);
				    break;
		}
		
		
		Obstaculo oo = builder.getObstaculo();
		lista.add(oo);
	}
	
	public void actualizarMovimiento(Ferrari ferrari) {
		// revisar si las gotas cayeron al suelo o chocaron con el tarro
		   for (int i=0; i < lista.size; i++ ) {
			  Obstaculo oo = lista.get(i);
			  
			  if (oo.actualizarMovimiento())
				lista.removeIndex(i);
			  
		      if(oo.choque(ferrari))
		    	lista.removeIndex(i);
		   } 
	}
	
	public void actualizarDibujoObstaculos(SpriteBatch batch) {
		for (int i=0; i < lista.size; i++ ) {
			  Entidad oo = lista.get(i);
		      batch.draw(oo.getSprite(), oo.getHitbox().x, oo.getHitbox().y); 
		   }
	}
}
