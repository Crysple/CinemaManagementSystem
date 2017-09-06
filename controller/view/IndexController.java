package controller.view;

import controller.MainApp;
import controller.model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class IndexController {
	@FXML
    private TableView<Movie> filmTable;
    @FXML
    private TableColumn<Movie, String> filmNameColumn;

    @FXML
    private Label filmID;
    @FXML
    private Label filmNameLabel;
    @FXML
    private Label directorLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label actorsLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label introduction;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public IndexController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the movie table with the two columns.
        filmNameColumn.setCellValueFactory(cellData -> cellData.getValue().movieName());
        
        // Clear movie details.
        showSessionDetails(null);

        // Listen for selection changes and show the movie details when changed.
        filmTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSessionDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    //initialize the mainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        filmTable.setItems(mainApp.getMovieData());
    }
    
    //fill the detailed label
    private void showSessionDetails(Movie movie) {
        if (movie != null) {
            // Fill the labels with info from the person object.
            //firstNameLabel.setText(person.getFirstName());
        	filmID.setText(""+movie.getMovieIndex());
            filmNameLabel.setText(movie.movieName().get());
            directorLabel.setText(movie.director().get());
            priceLabel.setText(""+movie.price().get());
            actorsLabel.setText(movie.mainActors().get());
            durationLabel.setText(movie.duration().get());
            typeLabel.setText(movie.catalog().get());
            introduction.setText(movie.introduction().get());
            // TODO: We need a way to convert the birthday into a String! 
        } else {
            // Person is null, remove all the text.
        	filmNameLabel.setText("");
        	directorLabel.setText("");
            priceLabel.setText("");
            actorsLabel.setText("");
            durationLabel.setText("");
            typeLabel.setText("");
            introduction.setText("");
            filmID.setText("");
        }
    }
    //showSessionDialog
    @FXML
    private void selectSession() {
    	Movie selectedMovie = filmTable.getSelectionModel().getSelectedItem();
    	if(selectedMovie!=null){
    		mainApp.showSessionDialog(selectedMovie);
    	}
    }
}
