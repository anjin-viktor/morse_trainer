package sound;

import java.util.Iterator;
import java.util.Vector;

import android.media.*;


public class Signal {
	public Signal()
	{
		toneGen = new ToneGenerator(android.media.AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
		flagStop = true;	
		tone = ToneGenerator.TONE_DTMF_4;
	}


	public void start(Vector<Character> v, int speed)
	{
		if(flagStop == false || v.size() == 0)
			return;		


		vect = convert(v);
		flagStop = false;
	
		final double k = (double) (1) / speed;
		
        new Thread() {
            public void run() 
            {
        		play(k);
            }
        }.start();
	}

	
	
	public void stop()
	{
		flagStop = true;
	}

	public boolean played()
	{
		return !flagStop;
	}
	
	private void play(double sl)
	{

		int sleep_time = (int) (sl * 1000);
		Iterator<Integer> itr = vect.iterator();
		for(;itr.hasNext() && flagStop == false;)
		{
			try
			{
				switch(itr.next())
				{
				case 1:
					toneGen.startTone(tone);
					Thread.sleep(sleep_time, 0);
					toneGen.stopTone();
					Thread.sleep(sleep_time/3*2, 0);
					break;
				case 2:
					toneGen.startTone(tone);
					Thread.sleep(sleep_time * 3, 0);
					toneGen.stopTone();
					Thread.sleep(sleep_time/3*2, 0);					
					break;
				case 3:
					Thread.sleep(sleep_time * 3 / 2, 0);
					break;
				case 4:
					Thread.sleep(5 * sleep_time, 0);
					break;
				};
			}
			catch (InterruptedException ie) 
			{
				ie.printStackTrace();
				flagStop = true;
			}
		}

		flagStop = true;
	}
	
	private Vector<Integer> convert (Vector <Character>v_in)
	{
		Vector<Integer> v_out = new Vector<Integer>();
	
		v_out.add(1);
		v_out.add(1);
		v_out.add(1);
		v_out.add(2);
		v_out.add(3);
		
		v_out.add(1);
		v_out.add(1);
		v_out.add(1);
		v_out.add(2);
		v_out.add(3);

		
		v_out.add(1);
		v_out.add(1);
		v_out.add(1);
		v_out.add(2);
		v_out.add(3);

		
		v_out.add(2);
		v_out.add(1);
		v_out.add(1);
		v_out.add(1);
		v_out.add(2);
		v_out.add(4);
		

		Iterator<Character> itr = v_in.iterator();

		int k = 0;
		
		while(itr.hasNext())
		{
			char ch =  itr.next();
			switch(ch)
			{
			case '0':
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				break;
			case '1':
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				break;

			case '2':
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				break;

			case '3':
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				break;
			case '4':
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				break;
			case '5':
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				break;
			case '6':
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
			break;
			case '7':
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
			break;
			case '8':
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
			break;

			case '9':
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				break;	
			case 'а':
				v_out.add(1);
				v_out.add(2);
				break;
			case 'б':
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				break;
			case 'в':
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				break;

			case 'г':
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				
				break;
			case 'д':
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);

				break;
			case 'е':
				v_out.add(1);
				break;

			case 'ж':
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				break;

			case 'з':
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);

				break;

			case 'и':
				v_out.add(1);
				v_out.add(1);				
				break;
			case 'й':
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				break;
			case 'к':
				v_out.add(2);
				v_out.add(1);
				v_out.add(2);
				break;
			case 'л':
				v_out.add(1);
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
				break;
			case 'м':
				v_out.add(2);
				v_out.add(2);
				break;
			case 'н':
				v_out.add(2);
				v_out.add(1);

				break;
			case 'о':
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				break;
			case 'п':
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				break;
			case 'р':
				v_out.add(1);
				v_out.add(2);
				v_out.add(1);
				break;
			case 'с':
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				break;
			case 'т':
				v_out.add(2);
				break;
			case 'у':
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				break;
			case 'ф':
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				v_out.add(1);
				break;
			case 'х':
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				v_out.add(1);
				break;
			case 'ц':
				v_out.add(2);
				v_out.add(1);
				v_out.add(2);
				v_out.add(1);
				break;
			case 'ч':
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				break;
			case 'ш':
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				v_out.add(2);
				break;
			case 'щ':
				v_out.add(2);
				v_out.add(2);
				v_out.add(1);
				v_out.add(2);

				break;
			case 'ь':
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				break;
			case 'ы':
				v_out.add(2);
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);

				break;
			case 'э':
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				v_out.add(1);
				v_out.add(1);
				break;
			case 'ю':
				v_out.add(1);
				v_out.add(1);
				v_out.add(2);
				v_out.add(2);
				break;
			case 'я':
				v_out.add(1);
				v_out.add(2);
				v_out.add(1);
				v_out.add(2);
				break;
			};
			
			k++;
			if((k % 5) == 0)
			{
				v_out.add(4);
				k = 0;
			}
			else
				v_out.add(3);			
		}
		
		
		
		v_out.add(2);
		v_out.add(1);
		v_out.add(2);

		return v_out;

	}

	
	private boolean flagStop;
	private ToneGenerator toneGen;
	private Vector<Integer> vect;
	private int tone;
}
