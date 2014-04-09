package com.example.diet.diary;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Diet_Diary extends Activity implements OnClickListener {
	

	private ArrayList<String> date = new ArrayList<String>();
	private ArrayList<String> time = new ArrayList<String>();
	private ArrayList<String> food = new ArrayList<String>();
	private ArrayList<String> portion = new ArrayList<String>();
	
	Spinner spinner;
	TextView tv4, tv2, tv3,tv1;
	EditText et1,et2,et3,et4;
	Button bt1;
	int bSpin = 0;
	private DBAdapter mHelper;
	private SQLiteDatabase dataBase;
	String strTime, strDate, strFood, strPortion, strNote, strID;
	ListView foodList;
private boolean isUpdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_input);
        
        spinner = (Spinner) findViewById(R.id.spinInput);
        tv1 = (TextView) findViewById(R.id.tvTime);
        tv2 = (TextView) findViewById(R.id.tvFood);
        tv3 = (TextView) findViewById(R.id.tvPortion);
        tv4 = (TextView) findViewById(R.id.tvNotes);
       
        et1 = (EditText) findViewById(R.id.etInput1);
        et2 = (EditText) findViewById(R.id.etInput2);
        et3 = (EditText) findViewById(R.id.etInput3);
        et4 = (EditText) findViewById(R.id.etInput4);
        bt1 = (Button) findViewById(R.id.btnSubmit);
        foodList = (ListView) findViewById(R.id.lvFood);
        clearText();
        resetText();
        spinner.setEnabled(true);
        
       
        isUpdate=getIntent().getExtras().getBoolean("update");
        if(isUpdate)
        {
        	int db = getIntent().getExtras().getInt("db");
        	spinner.setSelection(db);
        	strID=getIntent().getExtras().getString("ID");
        	strTime=getIntent().getExtras().getString("Time");
        	strFood=getIntent().getExtras().getString("Food");
        	strPortion=getIntent().getExtras().getString("Portion");
        	strNote=getIntent().getExtras().getString("Notes");
        	et1.setText(strTime);
        	et2.setText(strFood);
        	et3.setText(strPortion);
        	et4.setText(strNote);
        	spinner.setEnabled(false);
        	Toast.makeText(getApplicationContext(), "While editing the selection drop down is locked", Toast.LENGTH_LONG).show();
        	bSpin = spinner.getSelectedItemPosition();
        	if(bSpin==0){
        		updateMeal();
        	}else{
        		updateExercise();
        	}
        }else{
        	 addListenerOnSpinnerItemSelection();
        }
         bt1.setOnClickListener(this);
      
        
         mHelper=new DBAdapter(this);
         
        
 	}
        
    

    public void updateMeal(){
    	tv1.setText("Edit Time");
    	tv2.setText("Edit Food/Drink");
    	tv3.setText("Edit Portion");
    	tv4.setText("Edit Note");
    	
    }
    
    public void updateExercise(){
    	tv1.setText("Edit Time");
    	tv2.setText("Edit Exercise");
    	tv3.setText("Edit Duration");
    	tv4.setText("Edit Intensity");
    	
    }
    
    public void resetText(){
    	tv1.setText("Time");
    	tv2.setText("Food/Drinksfafgasf");
    	tv3.setText("Portion");
    	tv4.setText("Note");
    }
    public void clearText(){
    	et1.setText("1850");
    	et2.setText("Validation data to test sizing");
    	et3.setText("Validation data to test sizing");
    	et4.setText("Validation data to test sizing");
    	
    }
    
  public void addListenerOnSpinnerItemSelection() {
    	
    	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int position = spinner.getSelectedItemPosition();
				switch(position){
				case 0:
					tv2.setText("Food/Drink");
					tv3.setText("Portion Size");
					tv4.setText("Notes");
					bSpin = 0;
					break;
				case 1:
					tv2.setText("Exercise");
					tv3.setText("Duration");
					tv4.setText("Intensity");
					bSpin = 1;
					break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
      }
  
    // saveButton click event 
	public void onClick(View v) {
	

	    
	    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    String date = df.format(Calendar.getInstance().getTime());
	    strDate = date;
		strTime=et1.getText().toString().trim();
		strFood=et2.getText().toString().trim();
		strPortion=et3.getText().toString().trim();
		strNote=et4.getText().toString().trim();
		if(strTime.length()>0 && strFood.length()>0 && strPortion.length()>0)
		{
			saveData();
			clearText();
		}
		else
		{
			AlertDialog.Builder alertBuilder=new AlertDialog.Builder(Diet_Diary.this);
			alertBuilder.setTitle("Invalid Data");
			alertBuilder.setMessage("Please, Enter valid data");
			alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
					
				}
			});
			alertBuilder.create().show();
		}
		
	}

	/**
	 * save data into SQLite
	 */
	private void saveData(){
		dataBase=mHelper.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		if (bSpin == 0){
			
			values.put(DBAdapter.KEY_DATE,strDate);
			values.put(DBAdapter.KEY_TIME,strTime);
			values.put(DBAdapter.KEY_FOOD,strFood );
			values.put(DBAdapter.KEY_PORTION,strPortion);
			values.put(DBAdapter.KEY_NOTES,strNote);
		
		
		}else{
			
			values.put(DBAdapter.KEY_EXERCISE_DATE,strDate);
			values.put(DBAdapter.KEY_EXERCISE_TIME,strTime);
			values.put(DBAdapter.KEY_EXERCISE,strFood );
			values.put(DBAdapter.KEY_DURATION,strPortion);
			values.put(DBAdapter.KEY_INTENSITY,strNote);
			
		}
		
		
		System.out.println("");
		if(isUpdate)
		{    
			
			
			
			//update database with new data 
			if(bSpin==0){
				dataBase.update(DBAdapter.DATABASE_TABLE_FOOD, values, DBAdapter.KEY_ROWID+"="+strID, null);
				Toast.makeText(getApplicationContext(), strTime +" Entry updated!", Toast.LENGTH_LONG).show();
				
			}else{
				dataBase.update(DBAdapter.DATABASE_TABLE_EXERCISE, values, DBAdapter.KEY_ROWID+"="+strID, null);
				Toast.makeText(getApplicationContext(),strTime +" Entry updated!", Toast.LENGTH_LONG).show();
				
				
			}
			finish();
			
				
		}
		else
		{Intent i = new Intent(getApplicationContext(),
					Diary_Output.class);
			//insert data into database
			if(bSpin==0){
				dataBase.insert(DBAdapter.DATABASE_TABLE_FOOD, null, values);
				Toast.makeText(getApplicationContext(), strTime+ " "+ strFood +" logged", Toast.LENGTH_LONG).show();
			}else{
				dataBase.insert(DBAdapter.DATABASE_TABLE_EXERCISE, null, values);
				Toast.makeText(getApplicationContext(), strTime+ " "+ strFood +" logged", Toast.LENGTH_LONG).show();
			}
			i.putExtra("db", spinner.getSelectedItemPosition());
			finish();
			startActivity(i);
			
		}
		//close database
		dataBase.close();
		
		
		
	}
	
}
