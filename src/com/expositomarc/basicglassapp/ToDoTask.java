package com.expositomarc.basicglassapp;

public class ToDoTask {
	
	private String mTask = "Hola";
	
	public ToDoTask (String stringTask) {
		
		mTask = stringTask;
	
	}
	// Get task as String
	public String getTask() {
		
		return mTask;
		
	}
	
	// Set task as string
	public void setTask(String task) {
		
		mTask = task;
		
	}
	
	

}
