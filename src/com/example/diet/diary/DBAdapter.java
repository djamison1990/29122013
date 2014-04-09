package com.example.diet.diary;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * sqlite database helper to create table into SQLite database
 * @author ketan(Visit my <a
 *         href="http://androidsolution4u.blogspot.in/">blog</a>)
 */
public class DBAdapter extends SQLiteOpenHelper {
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	
	//table 1 fields
	public static final String KEY_DATE = "date";
	public static final String KEY_TIME = "time";
	public static final String KEY_FOOD = "food";
	public static final String KEY_PORTION = "portion";
	public static final String KEY_NOTES = "notes";
	//table 2 fields
	public static final String KEY_EXERCISE_DATE = "date";
	public static final String KEY_EXERCISE_TIME = "time";
	public static final String KEY_EXERCISE = "exercise";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_INTENSITY = "intensity";
	// table 3 fields
	public static final String KEY_FOOD_STORE = "food";
	public static final int KEY_CALORIES = 0;
	public static final int KEY_PROTEIN = 0;
	public static final int KEY_CARBS = 0;
	public static final int KEY_WATER = 0;
	public static final int KEY_FAT = 0;
	public static final int KEY_SATURATED_FAT = 0;
	public static final int KEY_IRON = 0;
	public static final int KEY_CALCIUM = 0;
	public static final int KEY_BARCODE = 50201600;
	//table 1 columns
	public static final int COL_DATE = 1;
	public static final int COL_TIME = 2;
	public static final int COL_FOOD = 3;
	public static final int COL_PORTION = 4;
	public static final int COL_NOTES = 5;
	//table 2 columns
	public static final int COL_EXERCISE_DATE = 1;
	public static final int COL_EXERCISE_TIME = 2;
	public static final int COL_EXERCISE = 3;
	public static final int COL_DURATION = 4;
	public static final int COL_INTENSITY = 5;
	
	public static final String[] ALL_FOOD_KEYS = new String[] {KEY_ROWID, KEY_DATE, KEY_TIME, KEY_FOOD, KEY_PORTION, KEY_NOTES};
	
	public static final String[] ALL_EXERCISE_KEYS = new String[] {KEY_ROWID, KEY_EXERCISE_DATE, KEY_EXERCISE_TIME, KEY_DURATION, KEY_DURATION, KEY_INTENSITY};
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE_FOOD = "foodTable";
	public static final String DATABASE_TABLE_EXERCISE = "exerciseTable";
	public static final String DATABASE_TABLE_DATASTORE = "dataStore";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 1;	
	
	
	private static final String DATABASE_CREATE_FOOD_TABLE = 
			
			"create table " + DATABASE_TABLE_FOOD 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_DATE + " string not null, "
			+ KEY_TIME + " string not null, "
			+ KEY_FOOD + " string not null, "
			+ KEY_PORTION + " string not null, "
			+ "notes string "
			
			+ ");";
	
	private static final String DATABASE_CREATE_EXERCISE_TABLE = 
			
			"create table " + DATABASE_TABLE_EXERCISE
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_EXERCISE_DATE + " string not null, "
			+ KEY_EXERCISE_TIME + " string not null, "
			+ KEY_EXERCISE + " string not null, "
			+ KEY_DURATION + " string not null, "
			+ KEY_INTENSITY +" string "
			+ ");";

	

	public DBAdapter(Context context) {
		super(context, DATABASE_NAME, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(DATABASE_CREATE_FOOD_TABLE);
	   	db.execSQL(DATABASE_CREATE_EXERCISE_TABLE);
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FOOD);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_EXERCISE);
		
		onCreate(db);

	}

}

