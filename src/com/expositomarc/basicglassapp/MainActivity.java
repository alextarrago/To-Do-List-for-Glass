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
	
	// Define tag for debugging
	private static final String TAG = "MyActivity"; 
	
	// List of cards
	private List<Card> mCards;
	
	// ScrollView for holding cards
    private CardScrollView mCardScrollView;
    
    // Current card selected
    private int currentCard = -1;
    
    private List<ToDoTask> mTaskList;
    
   
    private ExampleCardScrollAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
    	
    	// Create the cards
        createCards();
        
        // Instantiate and set
        mCardScrollView = new CardScrollView(this);
        adapter = new ExampleCardScrollAdapter();
        
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.setOnItemClickListener(this);
        mCardScrollView.activate();
        
        // Set CardScrollView as content view
        setContentView(mCardScrollView);
        
    }
    
    // Instantiate ToDoListManager and get list
    private void createCards() {
    	

    	 mCards = new ArrayList<Card>();  	 
    	 ToDoListManager listManager = new ToDoListManager();
    	 
    	 mTaskList = listManager.getToDoList();

         Card card;
         
         int sizeList = mTaskList.size();
         
         // For all the cards
         for (int i = 0 ; i < sizeList; i++) {
        	 
        	 card = new Card(this);
             card.setText(mTaskList.get(i).getTask());
             
             // Set in the arrayList
             mCards.add(card);
        	 
         }
      
    }

    // Class to manage CardScrollView
    private class ExampleCardScrollAdapter extends CardScrollAdapter {
    	
        @Override
        public int getPosition(Object item) {

            return mCards.indexOf(item);
        }

        @Override
        public int getCount() {

            return mCards.size();
        }

        @Override
        public Object getItem(int position) {

            return mCards.get(position);
        }

        @Override
        public int getViewTypeCount() {

            return Card.getViewTypeCount();
        }

        @Override
        public int getItemViewType(int position){

            return mCards.get(position).getItemViewType();
        }

        public View getView(int position, View convertView,
                ViewGroup parent) {

            return  mCards.get(position).getView(convertView, parent);
        }
    }

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	// Create options from "/res/menu/main.xml"
    	getMenuInflater().inflate(R.menu.main, menu);

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
    			mTaskList.remove(currentCard); 
    			mCards.remove(currentCard); 	// Remove the current card selected from the list of Cards
    			adapter.notifyDataSetChanged(); // Notify the adapter that needs to update the data
    			
    			
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
		
		adapter.notifyDataSetChanged();
	}
    
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		// Set current Card for menu option handle
		currentCard = position;
		
		openOptionsMenu();

		
		
	}
    
}
