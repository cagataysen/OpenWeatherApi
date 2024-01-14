package com.weather.weatherapi.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    //try catch konulacak.
    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    public String getWeatherData(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);

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
