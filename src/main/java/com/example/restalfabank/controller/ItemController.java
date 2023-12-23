package com.example.restalfabank.controller;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.FilterService;
import com.example.restalfabank.service.impl.FilterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    private final FilterService filterService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getAllItems(
            @RequestParam(defaultValue = "", required = false) String box,
            @RequestParam(defaultValue = "", required = false) String color
    ) {

        List<Item> items = filterService.getResultItems(box, color);
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Long> ids = items.stream().map(Item::getId).toList();

        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

}
