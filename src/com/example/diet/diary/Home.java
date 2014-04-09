package com.example.diet.diary;






import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Home extends Activity {

	
	Button bt1,bt2,bt3,bt4,bt5;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        bt1 = (Button) findViewById(R.id.btnInput);
        bt2 = (Button) findViewById(R.id.btnLog);
        bt3 = (Button) findViewById(R.id.btnUserData);
        bt4 = (Button) findViewById(R.id.btnEmail);
        bt5 = (Button) findViewById(R.id.btnBluetooth);
        addButtonClickListener(bt1);
        addButtonClickListener(bt2);
        addButtonClickListener(bt3);
        addButtonClickListener(bt4);
        addButtonClickListener(bt5);
    }

  
    
    

			
		
      
   
    public void addButtonClickListener(final Button x){
    	x.setOnClickListener(new View.OnClickListener() {
          int y=0;
    		public void onClick(View v) {
    		if(x==bt1){
    			y=0;
    			
    		}else if(x==bt2){
    			y=1;
    		}else if(x==bt3){
    			y=2;
    		}else if(x==bt4){
    			y=3;
    		}else{
    			y=4;
    		}
    			
    			switch(y){
				case 0:
					Intent i = new Intent(Home.this, Diet_Diary.class);
    				
					i.putExtra("update", false);
    		        startActivity(i);
					break;
				case 1:
					
					Intent sd = new Intent(Home.this, Diary_Output.class);
					sd.putExtra("db", "0");
    				
    		        startActivity(sd);
					break;
				case 2:
					Toast.makeText(getApplicationContext(), "Input user data", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(getApplicationContext(), "Email data", Toast.LENGTH_SHORT).show();		 
					break;
				case 4:
					Toast.makeText(getApplicationContext(), "Bluetooth data", Toast.LENGTH_SHORT).show();
					break;
				}
    			
    				
    		        
        			
    			}    
    	}) ;
    	}  
    }


