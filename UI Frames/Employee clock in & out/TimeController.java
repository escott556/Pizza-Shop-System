package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeController {
	
	@FXML
    public Text clockText;

    public void initialize() {
        updateClock();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateClock();
            }
        }).start();
    }
    
    //Real-time clock tracking
    private void updateClock() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        clockText.setText(formattedTime);
    }
    
    /* This is for clock-in button reaction */
    @FXML
    public Label ClockInResult;

    @FXML
    public void ClockIntime(ActionEvent event) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        ClockInResult.setText("You clocked in at: " + formattedTime);
    }
    
    /* This is for clock-out button reaction */
    @FXML
    public Label ClockOutResult;

    @FXML
    public void ClockOutTime(ActionEvent event) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        ClockInResult.setText("You clocked out at: " + formattedTime);
    }

}
