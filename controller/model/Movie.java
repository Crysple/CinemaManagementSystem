package controller.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Movie {
	private int movieIndex;
	private final StringProperty movieName;
	private final StringProperty director;
	private final StringProperty mainActors;
	private final DoubleProperty price;
	private final StringProperty duration;
	private final StringProperty catalog;
	private final StringProperty introduction;
	
	private ObservableList<Session> sessionData = FXCollections.observableArrayList();
	
	
	public ObservableList<Session> getSessionData() {
		return sessionData;
	}

	public void setSessionData(ObservableList<Session> sessionData) {
		this.sessionData = sessionData;
	}

	public Movie(int movieIndex, String movieName, String director, String mainActors,
			Double price, String duration, String catalog, String introduction) {
		super();
		this.movieIndex = movieIndex;
		this.movieName = new SimpleStringProperty(movieName);
		this.director = new SimpleStringProperty(director);
		this.mainActors = new SimpleStringProperty(mainActors);
		this.price = new SimpleDoubleProperty(price);
		this.duration = new SimpleStringProperty(duration);
		this.catalog = new SimpleStringProperty(catalog);
		this.introduction = new SimpleStringProperty(introduction);
		
		sessionData.add(new Session(""+movieIndex,"1","2015/6/10","17:30","1号厅"));
		sessionData.add(new Session(""+movieIndex,"2","2015/6/10","20:00","2号厅"));
		sessionData.add(new Session(""+movieIndex,"3","2015/6/10","22:30","3号厅"));
	}

	public int getMovieIndex() {
		return movieIndex;
	}

	public void setMovieIndex(int movieIndex) {
		this.movieIndex = movieIndex;
	}

	public StringProperty movieName() {
		return movieName;
	}

	public StringProperty director() {
		return director;
	}

	public StringProperty mainActors() {
		return mainActors;
	}

	public DoubleProperty price() {
		return price;
	}

	public StringProperty duration() {
		return duration;
	}

	public StringProperty catalog() {
		return catalog;
	}

	public StringProperty introduction() {
		return introduction;
	}
	
}
