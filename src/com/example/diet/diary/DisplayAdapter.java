package com.example.diet.diary;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * adapter to populate listview with data
 * @author ketan(Visit my <a
 *         href="http://androidsolution4u.blogspot.in/">blog</a>)
 */
public class DisplayAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> date;
	private ArrayList<String> time;
	private ArrayList<String> food;
	private ArrayList<String> portion;
	private ArrayList<String> notes;
	
	public DisplayAdapter(Context c, ArrayList<String> date,ArrayList<String> time, ArrayList<String> food, ArrayList<String> portion,ArrayList<String> notes) {
		this.mContext = c;

		this.date = date;
		this.time = time;
		this.food = food;
		this.portion = portion;
		this.notes = notes;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return date.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int pos, View child, ViewGroup parent) {
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.listcell, null);
			mHolder = new Holder();
			mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
			mHolder.txt_time = (TextView) child.findViewById(R.id.txt_time);
			mHolder.txt_food = (TextView) child.findViewById(R.id.txt_food);
			mHolder.txt_portion = (TextView) child.findViewById(R.id.txt_portion);
			mHolder.txt_notes = (TextView) child.findViewById(R.id.txt_notes);
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}
		mHolder.txt_id.setText(date.get(pos));
		mHolder.txt_time.setText(time.get(pos));
		mHolder.txt_food.setText(food.get(pos));
		mHolder.txt_portion.setText(portion.get(pos));
		mHolder.txt_notes.setText(notes.get(pos));
		return child;
	}

	public class Holder {
		TextView txt_notes;
		TextView txt_portion;
		TextView txt_id;
		TextView txt_food;
		TextView txt_time;
	}

}
