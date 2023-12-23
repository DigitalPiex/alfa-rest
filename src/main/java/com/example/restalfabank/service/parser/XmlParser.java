package com.example.restalfabank.service.parser;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class XmlParser {

    private static final SortedSet<Box> BOXES = new TreeSet<>(Comparator.comparing(Box::getId));
    private static final SortedSet<Item> ITEMS = new TreeSet<>(Comparator.comparing(Item::getId));
    private static final Deque<Long> DEQUE = new ArrayDeque<>();

    public static SortedSet<Box> getBoxes() {
        return BOXES;
    }
    public static SortedSet<Item> getItems() {
        return ITEMS;
    }

    public static class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String elementName, Attributes attributes) {
            Long containedIn;

            if (DEQUE.isEmpty()) {
                containedIn = null;
            } else {
                containedIn = DEQUE.peek();
            }

            if (elementName.equals("Box")) {
                Long id = Long.parseLong(attributes.getValue("id"));
                BOXES.add(new Box(id, containedIn));
                DEQUE.push(id);
            }

            if (elementName.equals("Item")) {
                Long id = Long.parseLong(attributes.getValue("id"));
                String color = attributes.getValue("color");

                Box box = BOXES.stream()
                        .filter(boxes -> Objects.equals(containedIn, boxes.getId()))
                        .findAny()
                        .orElse(null);

                ITEMS.add(new Item(id, box, color));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("Box")) {
                DEQUE.pop();
            }
        }
    }

}
