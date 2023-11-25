package com.weather.weatherapi.service;

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
}
