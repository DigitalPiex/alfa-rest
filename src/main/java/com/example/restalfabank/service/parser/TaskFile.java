package com.example.restalfabank.service.parser;

import java.io.File;

public class TaskFile implements Task {

    @Override
    public void execute(String path) {

        File file = new File(path);

        TaskInit.init(file);

    }
}
