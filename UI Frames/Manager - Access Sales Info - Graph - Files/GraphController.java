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

public class GraphController{
	@FXML 
	private LineChart lineChart;
	
	@FXML
	public void drawChart(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");
		
		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		
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
	public void drawChart2(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");
		
		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		
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
	public void drawChart3(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");
		
		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		
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
	public void drawChart4(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");
		
		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		
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
	public void drawChart5(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");
		
		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		
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
	public void drawChart6(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");
		
		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create the series for data to be implemented.
		
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
}