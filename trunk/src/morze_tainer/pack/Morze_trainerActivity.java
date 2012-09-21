package morze_tainer.pack;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;
import java.util.Vector;

import sound.Noise;
import sound.Signal;

public class Morze_trainerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button cmdStart = (Button) findViewById(R.id.button_start);
        final Button cmdStop = (Button) findViewById(R.id.button_stop);
        final Button cmdShowHide = (Button) findViewById(R.id.button_show_hide);
        final Button cmdNoise = (Button) findViewById(R.id.button_noise);

        
        final EditText editText = (EditText) findViewById(R.id.editText_text);
        final EditText editTextLength = (EditText) findViewById(R.id.editText_num);
        final EditText editTextSpeed = (EditText) findViewById(R.id.editText_speed);

        final TextView textView = (TextView) findViewById(R.id.textView_result);
        textView.setText("");
        textType = 0;
       
        
        RadioButton rbNums = (RadioButton) findViewById(R.id.radio_nums);
        RadioButton rbAlpha = (RadioButton) findViewById(R.id.radio_alpha);
        RadioButton rbText = (RadioButton) findViewById(R.id.radio_text);
        
        textIsHide = true;
        flagNoisePlayed = false;

        editText.setFocusableInTouchMode(false);        
        cmdStop.setClickable(false);
        
        
        
        buff = new StringBuffer();

        noisePlayer = new Noise(this, R.raw.noise_m);
        signalPlayer = new Signal();
        noisePlayer.setSignal(signalPlayer);
        
        rbNums.setOnClickListener(new RadioButton.OnClickListener() 
        {
        	public void onClick(View v)
        	{
        		textType = 0;
        		editText.setFocusable(false);
        	}       	
        }
        );

        rbAlpha.setOnClickListener(new RadioButton.OnClickListener() 
        {
        	public void onClick(View v)
        	{
        		textType = 1;
        		editText.setFocusable(false);
        	}       	
        }
        );

        rbText.setOnClickListener(new RadioButton.OnClickListener() 
        {
        	public void onClick(View v)
        	{
        		textType = 2;
        		editText.setFocusableInTouchMode(true);
        	}       	
        }
        );    

        
        cmdStart.setOnClickListener(new Button.OnClickListener() 
        {
        	public void onClick(View v)
        	{
        		if(signalPlayer.played())
        			return;

        		
        		int length;
        		int speed;
        		try
        		{
        			length = Integer.parseInt(editTextLength.getText().toString());
        			speed = Integer.parseInt(editTextSpeed.getText().toString());
        		}
        		catch(java.lang.NumberFormatException exc)
        		{
        			return;
        		}
        		signalPlayer.start(create(length), speed);
  		
        		if(flagNoisePlayed)
        			noisePlayer.start();
        		
        		if(textIsHide == false)
        		{
        			textView.setText(buff.toString());
        		}
        	}
        }
        );
        
        cmdStop.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v)
        	{
        		signalPlayer.stop();
        		flagNoisePlayed = noisePlayer.played();
        		noisePlayer.stop();
        	}
        }
        );

        cmdShowHide.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v)
        	{
        		if(textIsHide)
        		{
        			cmdShowHide.setText(R.string.string_text_hide);
        			textView.setText(buff.toString());	
        			textIsHide = false;
        		}
        		else
        		{
        			cmdShowHide.setText(R.string.string_text_show);
        			textView.setText("");	
        			textIsHide = true;
        		}
        	}
    	
        }
       );
        
        cmdNoise.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v)
        	{
       			if(flagNoisePlayed)
       			{
       				flagNoisePlayed = false;
       				cmdNoise.setText(R.string.string_noise_on);

       			}
       			else
       			{
       				flagNoisePlayed = true;
       				cmdNoise.setText(R.string.string_noise_off);
       			}

       			if(signalPlayer.played())
       			{
       				if(flagNoisePlayed)
       					noisePlayer.start();
       				else
       					noisePlayer.stop();
       			}
        	}
        }
        );
    }


    private Vector<Character> create(int length)
    {
    	Vector<Character> v_out = new Vector<Character>();
    	String alphabet = new String("абвгдежзийклмнопрстуфхцчшщьыэюя");
    	String nums = "0123456789";
    	buff = new StringBuffer();
    	switch(textType)
    	{
    		case 0:
    		
    			for(int i=0, l=0; i<length * 5; i++, l = (l+1) % 5)
    			{
    				char ch = nums.charAt((int) (Math.random() * 10));
    				v_out.add(ch);
    				buff.append(ch);
    				if(l == 4)
    					buff.append(' ');
    			}
    				
    			break;
    		case 1:
    			for(int i=0, l=0; i<length * 5; i++, l = (l+1) % 5)
    			{
    				char ch = alphabet.charAt((int) (Math.random() * 31));
    				v_out.add(ch);
    				buff.append(ch);
    				if(l == 4)
    					buff.append(' ');

    			}

    			break;

    		case 2:
    		{
    	        final EditText editText = (EditText) findViewById(R.id.editText_text);
    			String alpha = editText.getText().toString();
    			alpha = alpha.replace(" ", "");
    			alpha = alpha.replace("\n", "");
    			alpha = alpha.replace("\t", "");
    			for(int i=0, l=0; i<length * 5; i++, l = (l+1) % 5)
    			{
    				char ch = alpha.charAt((int) (Math.random() * alpha.length()));
    				v_out.add(ch);
    				buff.append(ch);
    				if(l == 4)
    					buff.append(' ');

    			}
    		}
    	};
    	return v_out;
    }
    
    private Noise noisePlayer;
    private Signal signalPlayer;
    private boolean textIsHide;
    private int textType;
	StringBuffer buff;
	private boolean flagNoisePlayed;
}
