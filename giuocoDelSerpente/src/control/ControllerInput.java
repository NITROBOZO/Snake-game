package control;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class ControllerInput {
	ControllerManager controllers;
	ControllerState inputP1;
	ControllerState inputP2;
	Thread t = new Thread(()->{
		while (true) {
            inputP1 = controllers.getState(0);// First controller
            inputP2 = controllers.getState(1);
		}});
    public ControllerInput(){
        controllers = new ControllerManager();
        controllers.initSDLGamepad();  // Must call this first
        // controllers.quitSDLGamepad();  // Call this when closing
    }
    public void start() {
    	t.start();
    }
    public boolean isP1DpadDown() {
    	return inputP1.dpadDown;
    }
    public boolean isP1DpadUp() {
    	return inputP1.dpadUp;
    }
    public boolean isP1DpadLeft() {
    	return inputP1.dpadLeft;
    }
    public boolean isP1DpadRight() {
    	return inputP1.dpadRight;
    }
    public boolean isP2DpadDown() {
    	return inputP2.dpadDown;
    }
    public boolean isP2DpadUp() {
    	return inputP2.dpadUp;
    }
    public boolean isP2DpadLeft() {
    	return inputP2.dpadLeft;
    }
    public boolean isP2DpadRight() {
    	return inputP2.dpadRight;
    }
}
