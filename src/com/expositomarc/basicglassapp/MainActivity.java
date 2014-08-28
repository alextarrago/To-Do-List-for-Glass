package com.expositomarc.basicglassapp;

// Basic Android libraries
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.app.Card;
// Specific Glass libraries for gesture detection
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;


public class MainActivity extends Activity implements OnItemClickListener{
	
	private static final String TAG = "MyActivity";
	private List<Card> mCards;
    private CardScrollView mCardScrollView;;
    private int currentCard = -1;
    private List<ToDoTask> mTaskList;
    private ExampleCardScrollAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
    	
		Log.v(TAG, "Create app");
        createCards();

        mCardScrollView = new CardScrollView(this);
        adapter = new ExampleCardScrollAdapter();
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.setOnItemClickListener(this);
        mCardScrollView.activate();
        setContentView(mCardScrollView);
        
    }
    
    private void createCards() {
    	
		Log.v(TAG, "Create cards");

    	 mCards = new ArrayList<Card>();
    	 ToDoListManager listManager = new ToDoListManager();
    	 mTaskList = listManager.getToDoList();

         Card card;
         
         int sizeList = mTaskList.size();
         for (int i = 0 ; i < sizeList; i++)
         {
        	 card = new Card(this);

             card.setText(mTaskList.get(i).getTask());
             mCards.add(card);
        	 
         }
        

        
        
    }

    private class ExampleCardScrollAdapter extends CardScrollAdapter {
    	
        @Override
        public int getPosition(Object item) {
    		Log.v(TAG, "get position");

            return mCards.indexOf(item);
        }

        @Override
        public int getCount() {
    		Log.v(TAG, "get count");

            return mCards.size();
        }

        @Override
        public Object getItem(int position) {
    		Log.v(TAG, "get item");

            return mCards.get(position);
        }

        @Override
        public int getViewTypeCount() {
    		Log.v(TAG, "get view cont");

            return Card.getViewTypeCount();
        }

        @Override
        public int getItemViewType(int position){
    		Log.v(TAG, "get item view");

            return mCards.get(position).getItemViewType();
        }

        public View getView(int position, View convertView,
                ViewGroup parent) {
    		Log.v(TAG, "get view : "+ position);

            return  mCards.get(position).getView(convertView, parent);
        }
    }

		


    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	// Create options from "/res/menu/main.xml"
    	getMenuInflater().inflate(R.menu.main, menu);
		Log.v(TAG, "create  option menu");

    	return super.onCreateOptionsMenu(menu);
    }

    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
    	// Get id for detect what item was selected
    	int id = item.getItemId();
        
    	switch (id) {
    		
    		case R.id.settings_1 : // Do something for setting 1
    			break;
    		
    		case R.id.settings_2 :
    			
    			// Remove currentCard
    			Log.v(TAG, "Elimino :" + currentCard + " text :" + mTaskList.get(currentCard).getTask() );
    			
    			mTaskList.remove(currentCard);
    			mCards.remove(currentCard); // PER FI!
    			adapter.notifyDataSetChanged();
    			
    			
    			break;
    		
    		default : // Something went wrong?
    			break;
    	
    	}
       
        return super.onOptionsItemSelected(item);
    }

    
    @Override
	public void onResume() 
	{
		super.onResume();
		
		Log.v(TAG, "on resume");

		
		adapter.notifyDataSetChanged();
	}
    
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		currentCard = position;
		
		openOptionsMenu();

		
		
	}
    
}
