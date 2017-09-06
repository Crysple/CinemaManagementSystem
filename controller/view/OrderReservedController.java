package controller.view;

import controller.model.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OrderReservedController {
	@FXML
	private Label filmName;
	@FXML
	private Label roomID;
	@FXML
	private Label date;
	@FXML
	private Label time;
	@FXML
	private Label seat;
	@FXML
	private Label userName;
	@FXML
	private Label price;
	
	private Stage dialogStage;
	
	private boolean okClicked = false;
	
	@FXML
	private void initialize(){
		
	}
	
	@FXML
    private void handleOk() {
    	okClicked = true;
    	dialogStage.close();
    }
	
	public boolean isOkClicked() {
        return okClicked;
    }
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	public void setOrder(Order order){
		filmName.setText(order.getMovie().movieName().get());
		roomID.setText(order.getSession().getRoomID().get());
		date.setText(order.getSession().getDate().get());
		time.setText(order.getSession().getTime().get());
		seat.setText(order.getSeat());
		userName.setText(order.getUser().getAccount());
		price.setText(""+order.getMovie().price().get());
	}
	
}
