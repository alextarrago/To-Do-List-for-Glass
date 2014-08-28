package com.expositomarc.basicglassapp;

import java.util.ArrayList;
import java.util.List;

import com.google.android.glass.app.Card;

public class ToDoListManager {

	private List<ToDoTask> mToDoList;
	
	public ToDoListManager()
	{
		mToDoList = new ArrayList<ToDoTask>();
		
		// Generate random tasks (just 4)
		ToDoTask task_1 = new ToDoTask("Buy Macbook Pro Retina");
		mToDoList.add(task_1);
		
		ToDoTask task_2 = new ToDoTask("Save money for iPhone 6");
		mToDoList.add(task_2);

		
		ToDoTask task_3 = new ToDoTask("Training archery");

		mToDoList.add(task_3);

		
		ToDoTask task_4 = new ToDoTask("Call dad");

		mToDoList.add(task_4);
		
	}
	
	public List<ToDoTask> getToDoList() {
		
		return mToDoList;
	}
	
}
