package com.example.dashika.fuelbuddy;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by dashika on 30/05/17.
 */

public class AppFuelBuddy extends Application {

    public static List<Element> getElements() {
        return elements;
    }

    private static List<Element> elements = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        elements = Arrays.asList(
                new Element(
                        35.5f, new Date().getTime(), "default",
                        "Автозаправка Shell", "ул.Садовническая, 57",
                        0.3f, Constant.latLngMoscowCenter),
                new Element(
                        35.5f, new Date().getTime(), "default",
                        "Газпром", "ул.Карла-Маркса,112",
                        1.3f, Constant.latLngMoscowCenter),
                new Element(
                        35.5f, new Date().getTime(), "default",
                        "Газпром", "ул.Первомайская,33",
                        5.3f, Constant.latLngMoscowCenter),
                new Element(
                        35.5f, new Date().getTime(), "default",
                        "Газпром", "Шоссу энтузиастов,51",
                        12f, Constant.latLngMoscowCenter)
        );
    }
}
