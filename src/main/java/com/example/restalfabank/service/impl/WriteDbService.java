package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.BoxService;
import com.example.restalfabank.service.ItemService;
import com.example.restalfabank.service.parser.GetArgument;
import com.example.restalfabank.service.parser.XmlParser;
import com.example.restalfabank.service.parser.Task;
import com.example.restalfabank.service.parser.TaskFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.SortedSet;

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

        SortedSet<Box> boxes = XmlParser.getBoxes();

        SortedSet<Item> items = XmlParser.getItems();

        createDbRows(boxes, items);
    }

    private void createDbRows(Set<Box> boxes, Set<Item> items) {
        if (!boxes.isEmpty()) {
            for (Box box : boxes) {
                boxService.save(box);
            }
        }

        if (!items.isEmpty()) {
            for (Item item : items) {
                itemService.save(item);
            }
        }
    }

}
