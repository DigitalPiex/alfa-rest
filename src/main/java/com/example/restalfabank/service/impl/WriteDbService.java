package com.example.restalfabank.service.impl;

import com.example.restalfabank.service.BoxService;
import com.example.restalfabank.service.ItemService;
import com.example.restalfabank.service.parser.GetArgument;
import com.example.restalfabank.service.parser.Task;
import com.example.restalfabank.service.parser.TaskFactory;
import com.example.restalfabank.service.parser.XmlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteDbService {

    private final BoxService boxService;
    private final ItemService itemService;

    public void loadDataFromXmlToDb(String[] args) {
        TaskFactory taskFactory = new TaskFactory();
        String argument = GetArgument.getArgument(args);
        Task task = taskFactory.createTask(argument);

        if (!argument.isEmpty()) {
            task.execute(argument.split("=")[1]);
        }

        XmlParser.getBoxes().forEach(boxService::save);
        XmlParser.getItems().forEach(itemService::save);
    }

}
