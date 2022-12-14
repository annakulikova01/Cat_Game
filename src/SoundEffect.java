import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
	Clip clip;
	
	public void setFile(String filepath) {
		try{
		File audioFile = new File(filepath);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
		}
		catch (Exception e) {
			
		}
			
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}

}
