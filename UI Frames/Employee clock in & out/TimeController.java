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

	/* Starts the clock and will call updateClock to continue updating the time. */
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
    
    //Formatting for the real-time clock to format in hours:minutes:seconds.
    private void updateClock() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        clockText.setText(formattedTime);
    }
    
    /* This is for clock-in button reaction, ClockIntime for outputting the time the employee clocks in. */
    @FXML
    public Label ClockInResult;

    @FXML
    public void ClockIntime(ActionEvent event) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        ClockInResult.setText("You clocked in at: " + formattedTime);
    }
    
    /* This is for clock-out button reaction, ClockOutTime for outputting the time the employee clocks out. */
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
