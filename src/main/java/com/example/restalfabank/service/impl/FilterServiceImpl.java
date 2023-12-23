package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.FilterService;
import com.example.restalfabank.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final ItemService itemService;

    public List<Item> getResultItems(String box, String color) {

        Integer id = null;
        try {
            id = Integer.valueOf(box);
        } catch (NumberFormatException ex) {
            box = "";
        }

        if (box.isEmpty() && color.isEmpty()) {
            return itemService.findAll();
        }

        if (box.isEmpty()) {
            return itemService.findByColor(color);
        }

        if (color.isEmpty()) {
            return itemService.findByBoxId(id);
        }

        return itemService.findByBoxIdAndColor(id, color);
    }

}
