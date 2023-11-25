package com.weather.weatherapi.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather";

    public String getWeatherData(String city) {
        String url = String.format("%s?q=%s&appid=%s", apiUrl, city, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getFormattedWeatherData(String city) {
        String weatherData = getWeatherData(city);
        JSONObject jsonObject = new JSONObject(weatherData);

        JSONObject mainObject = jsonObject.getJSONObject("main");
        double temp = mainObject.getDouble("temp");
        double feelsLike = mainObject.getDouble("feels_like");
        int humidity = mainObject.getInt("humidity");

        JSONObject sysObject = jsonObject.getJSONObject("sys");
        String country = sysObject.getString("country");

        String formattedData = String.format("Temperature: %.2f°C, Feels Like: %.2f°C, Humidity: %d%% in %s", temp, feelsLike, humidity, country);
        return formattedData;
    }
}