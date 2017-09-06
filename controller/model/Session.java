package controller.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Session {
	private final StringProperty filmID;
	private final StringProperty sessionID;
	private final StringProperty date;
	private final StringProperty time;
	private final StringProperty roomID;
	private int[][] seat;
	
	public Session(String filmID, String sessionID, String date, String time,String roomID) {
		this.filmID = new SimpleStringProperty(filmID);
		this.sessionID = new SimpleStringProperty(sessionID);
		this.date = new SimpleStringProperty(date);
		this.time = new SimpleStringProperty(time);
		this.roomID = new SimpleStringProperty(roomID);
		seat = new int[5][8];
	}
	
	public void setSeat(int row, int column){
		seat[row][column]=1;
	}

	public StringProperty getFilmID() {
		return filmID;
	}

	public StringProperty getSessionID() {
		return sessionID;
	}

	public StringProperty getDate() {
		return date;
	}

	public StringProperty getTime() {
		return time;
	}

	public StringProperty getRoomID() {
		return roomID;
	}

	public int[][] getSeat() {
		return seat;
	}

	
}
