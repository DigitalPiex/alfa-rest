package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemServiceImplTest {

    private ItemRepository itemRepository;
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        itemRepository = mock(ItemRepository.class);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void test_saving_new_item_calls_save_method_with_correct_item_object() {

        Item item = new Item(1L, null, "red");

        itemService.save(item);

        verify(itemRepository).save(item);
    }

    @Test
    void test_findAll_returns_list_of_all_items_in_item_repository() {

        List<Item> expectedItems = Arrays.asList(
                new Item(1L, null, "red"),
                new Item(2L, null, "blue"),
                new Item(3L, null, "green")
        );

        when(itemRepository.findAll()).thenReturn(expectedItems);
        List<Item> actualItems = itemService.findAll();

        assertThat(actualItems).isEqualTo(expectedItems);
    }

    @Test
    void test_findByColor_with_valid_color_returns_list_of_items_with_that_color() {

        String color = "red";
        List<Item> expectedItems = Arrays.asList(
                new Item(1L, null, "red"),
                new Item(4L, null, "red"),
                new Item(5L, null, "red")
        );

        when(itemRepository.findByColor(color)).thenReturn(expectedItems);
        List<Item> actualItems = itemService.findByColor(color);

        assertThat(actualItems).isEqualTo(expectedItems);
    }

    @Test
    void test_findByBoxId_with_valid_box_id_returns_list_of_items_in_that_box_and_sub_boxes() {

        Integer boxId = 1;
        List<Item> expectedItems = Arrays.asList(
                new Item(1L, null, "red"),
                new Item(2L, null, "blue"),
                new Item(3L, null, "green")
        );

        when(itemRepository.findByBoxId(boxId)).thenReturn(expectedItems);
        List<Item> actualItems = itemService.findByBoxId(boxId);

        assertThat(actualItems).isEqualTo(expectedItems);
    }

    @Test
    void test_findByBoxIdAndColor_with_valid_box_id_and_color_returns_list_of_items_in_that_box_and_sub_boxes_with_that_color() {

        Integer boxId = 1;
        String color = "red";
        List<Item> expectedItems = Arrays.asList(
                new Item(1L, null, "red"),
                new Item(4L, null, "red"),
                new Item(5L, null, "red")
        );
        when(itemRepository.findByBoxIdAndColor(boxId, color)).thenReturn(expectedItems);

        List<Item> actualItems = itemService.findByBoxIdAndColor(boxId, color);

        assertThat(actualItems).isEqualTo(expectedItems);
    }

    @Test
    void test_saving_existing_item_updates_item_in_item_repository() {
        Item updatedItem = new Item(1L, null, "blue");

        itemService.save(updatedItem);

        verify(itemRepository).save(updatedItem);
    }

    @Test
    void test_findByBoxId_with_nonexistent_box_id_returns_empty_list() {
        Integer boxId = 999;
        List<Item> expectedItems = Collections.emptyList();

        when(itemRepository.findByBoxId(boxId)).thenReturn(expectedItems);
        List<Item> actualItems = itemService.findByBoxId(boxId);

        assertThat(actualItems).isEmpty();
    }

    @Test
    void test_findByBoxIdAndColor_with_nonexistent_box_id_returns_empty_list() {

        Integer boxId = 999;
        String color = "red";
        List<Item> expectedItems = Collections.emptyList();

        when(itemRepository.findByBoxIdAndColor(boxId, color)).thenReturn(expectedItems);
        List<Item> actualItems = itemService.findByBoxIdAndColor(boxId, color);

        assertThat(actualItems).isEmpty();
    }

}