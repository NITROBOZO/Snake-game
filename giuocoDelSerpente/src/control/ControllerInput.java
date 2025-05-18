package control;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class ControllerInput {
	ControllerManager controllers;
	ControllerState input;
	Thread t = new Thread(()->{
		while (true) {
            input = controllers.getState(0);  // First controller

            if (input.isConnected) {
                if (input.dpadDown) {
                    System.out.println("down button is pressed!");
                }
            } else {
                System.out.println("Controller not connected.");
            }

            }
		});
    public ControllerInput(){
        controllers = new ControllerManager();
        controllers.initSDLGamepad();  // Must call this first
        // controllers.quitSDLGamepad();  // Call this when closing
    }
    public void start() {
    	t.start();
    }
    public boolean isDpadDown() {
    	return input.dpadDown;
    }
    public boolean isDpadUp() {
    	return input.dpadUp;
    }
    public boolean isDpadLeft() {
    	return input.dpadLeft;
    }
    public boolean isDpadRight() {
    	return input.dpadRight;
    }
}
