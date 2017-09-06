package controller.view;

import controller.MainApp;
import controller.model.Movie;
import controller.model.Order;
import controller.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class SessionController {


    @FXML
    private TableView<Session> sessionTable;
    @FXML
    private TableColumn<Session, String> filmIDColumn;
    @FXML
    private TableColumn<Session, String> sessionIDColumn;
    @FXML
    private TableColumn<Session, String> dataColumn;
    @FXML
    private TableColumn<Session, String> timeColumn;
    @FXML
    private TableColumn<Session, String> RoomIDColumn;
    @FXML
    private GridPane gp;

    private CheckBox[][] checkbox;
    
    private Stage dialogStage;
    private MainApp mainapp;
    
    private Movie movie;
    private boolean okClicked = false;
    

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	checkbox = new CheckBox[5][8];
    	for(int i=0;i<5;++i){
    		for(int j=0;j<8;++j){
    			checkbox[i][j] = new CheckBox();
    			checkbox[i][j].setPrefWidth(24.0);
    			checkbox[i][j].setPrefHeight(18);
    			checkbox[i][j].setText("");
    			GridPane.setRowIndex(checkbox[i][j], i);
    			GridPane.setColumnIndex(checkbox[i][j], j);
    			gp.getChildren().addAll(checkbox[i][j], new Label());
    		}
    	}
    	showSeat(null);
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setMainapp(MainApp mainapp){
    	this.mainapp = mainapp;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setFilm(Movie movie) {
        this.movie = movie;
        sessionTable.setItems(movie.getSessionData());
        
        filmIDColumn.setCellValueFactory(cellData -> cellData.getValue().getFilmID());
        sessionIDColumn.setCellValueFactory(cellData -> cellData.getValue().getSessionID());
        dataColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTime());
        RoomIDColumn.setCellValueFactory(cellData -> cellData.getValue().getRoomID());
        
        sessionTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)->showSeat(newValue));
        
    }
    
    private void showSeat(Session session){
    	if(session==null){
    		for(int i=0;i<5;++i)
        		for(int j=0;j<8;++j){
        			checkbox[i][j].setVisible(false);
        		}
    	}
    	else{
    		int[][] nowseat = session.getSeat();
        	for(int i=0;i<5;++i)
        		for(int j=0;j<8;++j){
        			checkbox[i][j].setVisible(true);
        			if(nowseat[i][j]==1){
        				checkbox[i][j].setDisable(true);
        			}
        			else{
        				checkbox[i][j].setDisable(false);
        				checkbox[i][j].setSelected(false);
        			}
        		}
    	}
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	okClicked = true;
    	Session session = sessionTable.getSelectionModel().getSelectedItem();
    	if(session==null) return;
    	int row=-1,column=-1;
    	for(int i=0;i<5;i++)
    		for(int j=0;j<8;j++){
    			if(checkbox[i][j].isSelected()){
    				row = i;
    				column = j;
    				Order neworder = new Order(movie,session,mainapp.getUserOnline(),row,column);
    		    	mainapp.modifySession(session, row, column);
    		    	mainapp.addOrder(neworder);
    		    	mainapp.showOrderReservedController(neworder);
    			}
    		}
    	if(row!=-1) handleCancel();
    	
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
}
