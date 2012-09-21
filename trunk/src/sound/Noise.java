package sound;


import android.media.MediaPlayer;
import java.io.IOException;
import android.content.Context;

import sound.Signal;

public class Noise {
	public Noise(Context context, int resid)
	{
		flagStop = true;
		playerNoise = new MediaPlayer();
		playerNoise = MediaPlayer.create(context, resid);
		
		try {
			playerNoise.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerNoise.setLooping(true);
	
		new Thread() {
            public void run() 
            {
        		playNoise();
            }
        }.start();
	}

	public void start()
	{
		flagStop = false;
	}

	public void stop()
	{
		flagStop = true;
	}
	
	public boolean played()
	{
		return !flagStop;
	}
	
	public void setSignal(Signal sig)
	{
		signal = sig;
	}
	
	private void playNoise()
	{
			boolean f = flagStop;
			if(flagStop == false)
				playerNoise.start();
			for(;;)
			{
				if(flagStop != f)
				{
					if(flagStop)
						playerNoise.pause();
					else
						playerNoise.start();
					f = flagStop;
				}
				if(signal != null && signal.played() == false)
					flagStop = true;
			}
	}

	private boolean flagStop;
	private MediaPlayer playerNoise;
	private Signal signal;
}
