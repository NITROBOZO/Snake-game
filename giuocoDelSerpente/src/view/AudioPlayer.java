package view;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
public class AudioPlayer {
	private Clip suono;
	private String path;
	public AudioPlayer(String pathSuono) {
        try {
        	path = pathSuono;
            URL suonoURL = getClass().getResource(pathSuono);
            suono = AudioSystem.getClip();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(suonoURL);
            suono.open(audioInput);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
	public void play() {
		if(suono != null) {
			suono.setFramePosition(0);
		}
		suono.start();
	}
	/*il play() normale
	a volte non funzionava 
	usato ripetutamente */
	public void playSfx() {
		
		new Thread(()->{
			try {
				URL sfxURL = getClass().getResource(path);
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(sfxURL);
	        	Clip sfx = AudioSystem.getClip();
				sfx.open(audioInput);
				sfx.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}).start();
		
        
	}
	public Clip getClip() {
		return suono;
	}
	public void loop() {
		if(suono != null) {
			suono.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public void stop() {
		if(suono != null) {
			suono.stop();
		}
	}
	
}
