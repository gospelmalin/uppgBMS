package se.bms.wearep.controller;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import se.bms.wearep.coords.GetCoords;
import se.bms.wearep.weather.WeatherLauncher;

public class WeatherController{

	@FXML
	protected Pane weather;

	@FXML
	protected TextField city;
	
	@FXML
	public void getWeather(ActionEvent event) throws IOException {
		System.out.println("City: " + city.getText());
		getWeatherData(city.getText(), getLongitude(city.getText()), getLatitude(city.getText()));
		// when data is saved to disk
		// show data in html file in the webview
		//changeToBrowser("http://www.smhi.se/");
		changeToBrowser("weatherOutfile.html");
	}



	private Double[] getCoords(String city) {
		// get coords from city
		return null;
	}
	
	private Double getLongitude(String city) {
		// get lon from city
		GetCoords getcoords = new GetCoords();
		getcoords.run(city);
		Double lon = getcoords.getLongitude();
		return lon;
	}
	
	private Double getLatitude(String city) {
		// get lat from city
		GetCoords getcoords = new GetCoords();
		getcoords.run(city);
		Double lat = getcoords.getLatitude();
		return lat;
	}

	private void getWeatherData(String city, Double lon, Double lat) {
		// get weather data from coords
		File outputFile = new File("weatherOutfile.html");
		outputFile.delete();
		WeatherLauncher.run(city, lon, lat);
	}

	private void saveData() {
		// save the data to disk
	}

	private void changeToBrowser(String url) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/webview.fxml"));

		weather.getChildren().setAll((Parent) loader.load());

		WebViewController webView = loader.getController();
		webView.changeWebpage(url);
		webView.setControls(new FXMLLoader(getClass().getResource("../view/weathercon.fxml")));
	}

}