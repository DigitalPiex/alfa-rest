package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.BoxService;
import com.example.restalfabank.service.ItemService;
import com.example.restalfabank.service.parser.Task;
import com.example.restalfabank.service.parser.TaskFactory;
import com.example.restalfabank.service.parser.XmlParser;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class WriteDbServiceTest {

    @Test
    void test_loadDataFromXmlToDb_with_valid_arguments() {
        BoxService boxService = mock(BoxService.class);
        ItemService itemService = mock(ItemService.class);
        WriteDbService writeDbService = new WriteDbService(boxService, itemService);

        String[] args = {"--classpath=test.xml"};

        TaskFactory taskFactory = mock(TaskFactory.class);
        Task task = mock(Task.class);

        when(taskFactory.createTask(anyString())).thenReturn(task);

        SortedSet<Box> boxes = new TreeSet<>(Comparator.comparing(Box::getId));
        Box box = new Box(1L, null);
        boxes.add(box);

        SortedSet<Item> items = new TreeSet<>(Comparator.comparing(Item::getId));
        Item item = new Item(1L, box, "red");
        items.add(item);
        try (MockedStatic<XmlParser> parser = Mockito.mockStatic(XmlParser.class)) {
            parser.when(XmlParser::getBoxes).thenReturn(boxes);
            parser.when(XmlParser::getItems).thenReturn(items);

            writeDbService.loadDataFromXmlToDb(args);

            verify(boxService).save(box);
            verify(itemService).save(item);
        }
    }

    @Test
    void test_loadDataFromXmlToDb_with_no_arguments() {
        BoxService boxService = mock(BoxService.class);
        ItemService itemService = mock(ItemService.class);
        WriteDbService writeDbService = new WriteDbService(boxService, itemService);

        String[] args = {};

        writeDbService.loadDataFromXmlToDb(args);

        verifyNoInteractions(boxService);
        verifyNoInteractions(itemService);
    }

    @Test
    void test_loadDataFromXmlToDb_with_invalid_arguments() {
        BoxService boxService = mock(BoxService.class);
        ItemService itemService = mock(ItemService.class);
        WriteDbService writeDbService = new WriteDbService(boxService, itemService);

        String[] args = {"--invalid"};

        writeDbService.loadDataFromXmlToDb(args);

        verifyNoInteractions(boxService);
        verifyNoInteractions(itemService);
    }

    @Test
    void test_loadDataFromXmlToDb_with_file_path_argument() {
        BoxService boxService = mock(BoxService.class);
        ItemService itemService = mock(ItemService.class);
        WriteDbService writeDbService = new WriteDbService(boxService, itemService);

        String[] args = {"--file=test.xml"};

        TaskFactory taskFactory = mock(TaskFactory.class);
        Task task = mock(Task.class);

        when(taskFactory.createTask(anyString())).thenReturn(task);

        SortedSet<Box> boxes = new TreeSet<>(Comparator.comparing(Box::getId));
        Box box = new Box(1L, null);
        boxes.add(box);

        SortedSet<Item> items = new TreeSet<>(Comparator.comparing(Item::getId));
        Item item = new Item(1L, box, "red");
        items.add(item);
        try (MockedStatic<XmlParser> parser = Mockito.mockStatic(XmlParser.class)) {
            parser.when(XmlParser::getBoxes).thenReturn(boxes);
            parser.when(XmlParser::getItems).thenReturn(items);

            writeDbService.loadDataFromXmlToDb(args);

            verify(boxService).save(box);
            verify(itemService).save(item);
        }
    }

    @Test
    void test_loadDataFromXmlToDb_with_invalid_file_path_argument() {
        BoxService boxService = mock(BoxService.class);
        ItemService itemService = mock(ItemService.class);
        WriteDbService writeDbService = new WriteDbService(boxService, itemService);

        String[] args = {"--file=invalid.xml"};

        writeDbService.loadDataFromXmlToDb(args);

        verifyNoInteractions(boxService);
        verifyNoInteractions(itemService);
    }

    @Test
    void test_loadDataFromXmlToDb_with_empty_file_path_argument() {
        BoxService boxService = mock(BoxService.class);
        ItemService itemService = mock(ItemService.class);
        WriteDbService writeDbService = new WriteDbService(boxService, itemService);

        String[] args = {"--file=empty.xml"};

        writeDbService.loadDataFromXmlToDb(args);

        verifyNoInteractions(boxService);
        verifyNoInteractions(itemService);
    }

}