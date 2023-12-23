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
            id = Integer.parseInt(box);
        } catch (NumberFormatException ex) {
            box = "";
        }

        boolean isBoxEmpty = box.isEmpty();
        boolean isColorEmpty = color.isEmpty();

        List<Item> resultItems;

        if (isBoxEmpty && isColorEmpty) {
            resultItems = itemService.findAll();
        } else if (isBoxEmpty) {
            resultItems = itemService.findByColor(color);
        } else if (isColorEmpty) {
            resultItems = itemService.findByBoxId(id);
        } else {
            resultItems = itemService.findByBoxIdAndColor(id, color);
        }

        return resultItems;
    }

}
