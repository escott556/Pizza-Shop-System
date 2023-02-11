package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Login {
	public Login() {
	}
	@FXML
	private Button button;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	public void userLogin(ActionEvent event) throws IOException {
		checkLogin();
	}
	
	private void checkLogin() throws IOException{
		Main m = new Main();
		if(username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
			wrongLogin.setText("Logging in.");
			
			m.changeScene("/ManagerFrame.fxml");
		} else if(username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Enter your username and password.");
		} else {
			wrongLogin.setText("Incorrect username or password.");
		}
	}
}
