package com.weather.weatherapi.controller;

// WeatherController.java

import com.weather.weatherapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city, Model model) {
        String formattedWeatherData = weatherService.getFormattedWeatherData(city);
        model.addAttribute("weatherData", formattedWeatherData);
        return "weather";
    }

    @GetMapping("/api/weather/{city}")
    public ResponseEntity<String> getWeatherData(@PathVariable String city) {
        String formattedWeatherData = weatherService.getFormattedWeatherData(city);
        return ResponseEntity.ok(formattedWeatherData);
    }
}