package com.natania.uas.model;

import com.google.gson.annotations.SerializedName;

public class PersonItem{

	@SerializedName("date")
	private String date;

	@SerializedName("name")
	private String name;

	@SerializedName("lesson")
	private String lesson;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("class")
	private String jsonMemberClass;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLesson(String lesson){
		this.lesson = lesson;
	}

	public String getLesson(){
		return lesson;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJsonMemberClass(String jsonMemberClass){
		this.jsonMemberClass = jsonMemberClass;
	}

	public String getJsonMemberClass(){
		return jsonMemberClass;
	}
}