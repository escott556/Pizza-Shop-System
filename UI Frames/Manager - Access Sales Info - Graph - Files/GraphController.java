package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class GraphController {
	//create the lineChart to start graphing
	@FXML 
	private LineChart lineChart;
	
	
	/* All Graph data entries below */
	@FXML
	public void GrandTotal(ActionEvent actionEvent) { //Grand total graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		series.setName("Grand Total"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the GrandTotal type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 20));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 60));
		series.getData().add(new XYChart.Data<>("May", 80));
		series.getData().add(new XYChart.Data<>("Jun", 100));
		series.getData().add(new XYChart.Data<>("Jul", 120));
		series.getData().add(new XYChart.Data<>("Aug", 140));
		series.getData().add(new XYChart.Data<>("Sep", 160));
		series.getData().add(new XYChart.Data<>("Oct", 180));
		series.getData().add(new XYChart.Data<>("Nov", 200));
		series.getData().add(new XYChart.Data<>("Dec", 220));
		
		//adding series to lineChart
		lineChart.getData().add(series);
		
	}	
	@FXML
	public void InHouse(ActionEvent actionEvent) { //In-house graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		series.setName("In-house"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the InHouse type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 60));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 120));
		series.getData().add(new XYChart.Data<>("May", 814));
		series.getData().add(new XYChart.Data<>("Jun", 450));
		series.getData().add(new XYChart.Data<>("Jul", 700));
		series.getData().add(new XYChart.Data<>("Aug", 230));
		series.getData().add(new XYChart.Data<>("Sep", 5));
		series.getData().add(new XYChart.Data<>("Oct", 809));
		series.getData().add(new XYChart.Data<>("Nov", 546));
		series.getData().add(new XYChart.Data<>("Dec", 375));
		
		//adding series to lineChart
		lineChart.getData().add(series);
		
	}
	@FXML
	public void Takeout(ActionEvent actionEvent) { //Take-out graph data
	
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		series.setName("Takeout"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the Takeout type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 20));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 60));
		series.getData().add(new XYChart.Data<>("May", 80));
		series.getData().add(new XYChart.Data<>("Jun", 100));
		series.getData().add(new XYChart.Data<>("Jul", 120));
		series.getData().add(new XYChart.Data<>("Aug", 140));
		series.getData().add(new XYChart.Data<>("Sep", 160));
		series.getData().add(new XYChart.Data<>("Oct", 180));
		series.getData().add(new XYChart.Data<>("Nov", 200));
		series.getData().add(new XYChart.Data<>("Dec", 220));
		
		//adding series to lineChart
		lineChart.getData().add(series);
		
	}
	@FXML
	public void Delivery(ActionEvent actionEvent) { //Delivery graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		series.setName("Delivery"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the Delivery type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 20));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 60));
		series.getData().add(new XYChart.Data<>("May", 80));
		series.getData().add(new XYChart.Data<>("Jun", 100));
		series.getData().add(new XYChart.Data<>("Jul", 120));
		series.getData().add(new XYChart.Data<>("Aug", 140));
		series.getData().add(new XYChart.Data<>("Sep", 160));
		series.getData().add(new XYChart.Data<>("Oct", 180));
		series.getData().add(new XYChart.Data<>("Nov", 200));
		series.getData().add(new XYChart.Data<>("Dec", 220));
		
		//adding series to lineChart
		lineChart.getData().add(series);
		
	}
	
	@FXML
	public void clearData() { //for clearing data on the graph
		lineChart.getData().clear();
	}
	
}
