package controller;

import java.io.IOException;

import controller.model.Cinema;
import controller.model.Movie;
import controller.model.Order;
import controller.model.Session;
import controller.model.User;
import controller.view.IndexController;
import controller.view.OrderReservedController;
import controller.view.SessionController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	//about UI level
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //about Model level
    private Cinema cinema;

    public ObservableList<Movie> getMovieData() {
		return cinema.getMovieData();
	}
    
	public MainApp() {
		cinema = Cinema.getInstance();
    }
    
	public void addOrder(Order order){
		cinema.addOrder(order);
	}
	
	public User getUserOnline(){
		return cinema.getUserOnline();
	}
	
	public void modifySession(Session session ,int row, int column){
		cinema.modifySession(session, row, column);
	}
    
	//control UI
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Film Order");

        initRootLayout();

        showFilmOverview();
    }
    
    //initial the Root Layout
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //initial the index dialog
    public void showFilmOverview() {
        try {
            // Load index.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Index.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(personOverview);
            
            IndexController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //open the session dialog
    public boolean showSessionDialog(Movie movie) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Session.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Session");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            SessionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFilm(movie);
            controller.setMainapp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showOrderReservedController(Order order){
    	try{
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrderReserved.fxml"));
            VBox page = (VBox) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("OrderReserved");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            OrderReservedController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrder(order);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    		return false;
    	}
    }
    
    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}