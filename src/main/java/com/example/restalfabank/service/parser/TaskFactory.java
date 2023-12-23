package com.example.restalfabank.service.parser;

public class TaskFactory {

    public Task createTask(String argument) {

        Task task = null;

        if (argument.contains("--classpath=") || argument.contains("--file=")) {
            task = new TaskFile();
        }
        if (argument.contains("--url=")) {
            task = new TaskUrl();
        }

        return task;
    }

}
