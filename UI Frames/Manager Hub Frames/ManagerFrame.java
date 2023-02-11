//This is dummy code for the logout functionality, not a complete controller

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ManagerFrame {
	
	@FXML
	private Button logout;
	
	public void userLogout(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("/Main.fxml");
	}

}
