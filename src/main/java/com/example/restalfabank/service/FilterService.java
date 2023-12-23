package com.example.restalfabank.service;

import com.example.restalfabank.model.Item;

import java.util.List;

public interface FilterService {

    List<Item> getResultItems(String box, String color);

}
