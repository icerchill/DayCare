package edu.neu.csye6200;

public class Classroom {
	private String roomID;
	private int capacity;
	private String type;
	//getters and setters
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//
	public Classroom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classroom(String roomID, int capacity, String type) {
		super();
		this.roomID = roomID;
		this.capacity = capacity;
		this.type = type;
	}
	@Override
	public String toString() {
		return "Classroom [roomID=" + roomID + ", capacity=" + capacity + ", type=" + type + "]";
	}
	
	

}
