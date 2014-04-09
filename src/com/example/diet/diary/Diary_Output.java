package com.example.diet.diary;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * activity to display all records from SQLite database
 * @author ketan(Visit my <a
 *         href="http://androidsolution4u.blogspot.in/">blog</a>)
 */
public class Diary_Output extends Activity {

	private DBAdapter mHelper;
	private SQLiteDatabase dataBase;

	private ArrayList<String> userId = new ArrayList<String>();
	private ArrayList<String> date = new ArrayList<String>();
	private ArrayList<String> time = new ArrayList<String>();
	private ArrayList<String> food = new ArrayList<String>();
	private ArrayList<String> portion = new ArrayList<String>();
	private ArrayList<String> notes = new ArrayList<String>();
	private ListView userList;
	private AlertDialog.Builder build;
	Spinner spinner;
	Button btClose;
	Integer bSpin=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_output);
		
		btClose = (Button) findViewById(R.id.btnClose);
		userList = (ListView) findViewById(R.id.lvOutput);
		spinner = (Spinner) findViewById(R.id.spinner1);
		mHelper = new DBAdapter(this);
		int db = getIntent().getExtras().getInt("db");
    	spinner.setSelection(db);
		addListenerOnSpinnerItemSelection();
		addButtonClickListener();
		
		
		Toast.makeText(getApplicationContext(), "Tap to edit, hold to delete.", Toast.LENGTH_LONG).show();
		
		userList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent i = new Intent(getApplicationContext(),
						Diet_Diary.class);
				i.putExtra("Time", time.get(arg2));
				i.putExtra("Food", food.get(arg2));
				i.putExtra("Portion", portion.get(arg2));
				i.putExtra("Notes", notes.get(arg2));
				i.putExtra("ID", userId.get(arg2));
				i.putExtra("update", true);
				i.putExtra("db", spinner.getSelectedItemPosition());
				startActivity(i);

			}
		});
		
		//long click to delete data
		userList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				
				build = new AlertDialog.Builder(Diary_Output.this);
				build.setTitle("Delete " + time.get(arg2) + " "
						+ food.get(arg2));
				build.setMessage("Do you want to delete ?");
				build.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

								Toast.makeText(
										getApplicationContext(),
										time.get(arg2) + " "
												+ food.get(arg2)
												+ " is deleted.", Toast.LENGTH_LONG).show();
								if (bSpin==0){
									dataBase.delete(
										DBAdapter.DATABASE_TABLE_FOOD,
										DBAdapter.KEY_ROWID + "="
												+ userId.get(arg2), null);
								}else{
									dataBase.delete(
									DBAdapter.DATABASE_TABLE_EXERCISE,
									DBAdapter.KEY_ROWID + "="
											+ userId.get(arg2), null);
								}
								
								displayData();
								dialog.cancel();
							}
						});

				build.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				AlertDialog alert = build.create();
				alert.show();

				return true;
			}
		});
	}

	@Override
	protected void onResume() {
		displayData();
		super.onResume();
	}

	/**
	 * displays data from SQLite
	 */
	private void displayData() {
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor;
		if (bSpin==0){
			
			mCursor = dataBase.rawQuery("SELECT * FROM "
					+ DBAdapter.DATABASE_TABLE_FOOD, null);

			date.clear();
			userId.clear();
			time.clear();
			food.clear();
			portion.clear();
			notes.clear();
			
			
			if (mCursor.moveToFirst()) {
				do {
					
					userId.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_ROWID)));
					date.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_DATE)));
					time.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_TIME)));
					food.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_FOOD)));
					portion.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_PORTION)));
					notes.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_NOTES)));
				} while (mCursor.moveToNext());
			}
			
		}else{
			
			mCursor = dataBase.rawQuery("SELECT * FROM "
					+ DBAdapter.DATABASE_TABLE_EXERCISE, null);
			date.clear();
			userId.clear();
			time.clear();
			food.clear();
			portion.clear();
			notes.clear();
			
			if (mCursor.moveToFirst()) {
				do {
					
					userId.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_ROWID)));
					date.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_EXERCISE_DATE)));
					time.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_EXERCISE_TIME)));
					food.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_EXERCISE)));
					portion.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_DURATION)));
					notes.add(mCursor.getString(mCursor.getColumnIndex(DBAdapter.KEY_INTENSITY)));
				} while (mCursor.moveToNext());
			}
			
		}
		
		DisplayAdapter disadpt = new DisplayAdapter(Diary_Output.this,date, time, food,portion,notes);
		userList.setAdapter(disadpt);
		mCursor.close();
	}

	  public void addListenerOnSpinnerItemSelection() {
	    	
	    	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					int position = spinner.getSelectedItemPosition();
					switch(position){
					case 0:
						
						bSpin = 0;
						break;
					case 1:
						
						bSpin = 1;
						break;
					}
					displayData();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	      }

	  public void addButtonClickListener(){
	    	btClose.setOnClickListener(new View.OnClickListener() {
	          
	    		public void onClick(View v) {
	    		
	    			
	    			
	    			finish();
	    			
	    			}    
	    	
	    	});  
	    }
	  
}