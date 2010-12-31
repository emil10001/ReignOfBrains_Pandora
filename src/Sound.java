import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound implements Runnable {

	private final int bufSize = 16384;
	private final int MONO = 1;
	private final int STEREO = 2;
	private AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, MONO, 2, 44100, true);
	private AudioInputStream audioInputStream;
	private SourceDataLine line;
	private Thread thread;
	private String fileName;
	private URL soundFile;
	double duration;
	private boolean done = false;
	
	public Sound (String si)
	{

		this.soundFile = this.getClass().getClassLoader().getResource(si);
		
		if (this.soundFile == null) {
			System.out.println("Can't find ref: "+si);
		}
		
		this.fileName=si;
	}
	
	public boolean running(){
		if (thread == null && !done) {
			start();
			return true;
		}
		if (thread != null) {
			return true;
		}
		return false;
	}
	
	public String getFileName(){
		return soundFile.getFile();
	}
	
	public void start()
	{
		thread = new Thread(this);
		thread.setName("Playback");
		done = false;
		thread.start();
	}
	
	public void stop()
	{
		done = true;
		thread = null;
	}
	
	
	public void pause(){
		done = false;
		thread = null;
	}
	
	private void shutDown (String message)
	{
		if (thread != null) {
			thread = null;
		}
	}

	public void run()
	{
		// Make sure we have something to play
		if (soundFile != null ) {	
			// Set an AudioInputStream of the desired format for playback
			try {
				audioInputStream = AudioSystem.getAudioInputStream(soundFile.openStream());
				format = audioInputStream.getFormat();
				//System.out.println("Now playing: "+fileName);
				long milliseconds = (long)((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat().getFrameRate());
				duration = milliseconds / 1000.0;
			}
			catch (Exception ex) {
				System.err.println("Error with creating audio input stream!");
			}
			try {
				AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, audioInputStream);
				soundFile = this.getClass().getClassLoader().getResource(fileName);
				if (playbackInputStream == null) {
					shutDown("Unable to convert stream of format " + audioInputStream + " to format " + format);
					return;
				}
				
				// Define the required attributes for our line, and make sure a compatible line is supported.
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
				if (!AudioSystem.isLineSupported(info)) {
					shutDown("Line matching " + info + " not supported.");
					return;
				}
				
				// Get and open the source data line for playback.
				try {
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(format, bufSize);
				}
				catch (LineUnavailableException ex) {
					shutDown("Unable to open the line: " + ex);
					return;
				}
				
				// Play back the captured audio data
				int frameSizeInBytes = format.getFrameSize();
				int bufferLengthInFrames = line.getBufferSize() / 8;
				int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
				byte[] data = new byte[bufferLengthInBytes];
				int numBytesRead = 0;
					
				// Start the source data line
				line.start();
				while (thread != null) {
					try{
						if ((numBytesRead = playbackInputStream.read(data)) == -1) {
							break;
						}
						int numBytesRemaining = numBytesRead;
						while (numBytesRemaining > 0 ) {
							numBytesRemaining -= line.write(data, 0, numBytesRemaining);
						}
					}
					catch (Exception e) {
						shutDown("Error during playback: " + e);
						break;
					}
				}
				
				// We reached the end of the stream.  let the data play out, then stop and close the line.
				if (thread != null) {
					line.drain();
				}
				line.stop();
				line.close();
				line = null;
				
				shutDown(null);
			}
			catch (Exception e) {
				shutDown("Unable to create playback steam: "+e);
			}
		}

	}
	
}
