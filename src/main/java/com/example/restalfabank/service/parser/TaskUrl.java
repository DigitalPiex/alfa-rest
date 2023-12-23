package com.example.restalfabank.service.parser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class TaskUrl implements Task {

    @Override
    public void execute(String path) {

        URL url = null;

        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        File file = null;

        try {
            file = Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        TaskInit.init(file);

    }
}
