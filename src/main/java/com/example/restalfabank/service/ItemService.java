package com.example.restalfabank.service;

import com.example.restalfabank.model.Item;

import java.util.List;

public interface ItemService {

    void save(Item item);

    List<Item> findAll();

    List<Item> findByColor(String color);

    List<Item> findByBoxId(Integer id);

    List<Item> findByBoxIdAndColor(Integer id, String color);

}
