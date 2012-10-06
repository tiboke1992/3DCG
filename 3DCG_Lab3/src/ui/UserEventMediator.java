package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import renderer.Camera;

public class UserEventMediator extends KeyAdapter {
	private Camera camera;
	
	public UserEventMediator(Camera camera){
		this.camera = camera;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'f'){
			camera.forward();
		}else if(e.getKeyChar() == 'b'){
			camera.backward();
		}
	}
	
	
	
}
