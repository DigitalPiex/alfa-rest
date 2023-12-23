package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.ItemService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilterServiceImplTest {

    @Test
    void test_both_box_and_color_empty() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());


        when(itemService.findAll()).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems("", "");


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_box_empty_color_not_empty() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());
        String color = "red";


        when(itemService.findByColor(color)).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems("", color);


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_color_empty_box_not_empty() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());
        int boxId = 1;


        when(itemService.findByBoxId(boxId)).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems(String.valueOf(boxId), "");


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_both_box_and_color_not_empty() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());
        int boxId = 1;
        String color = "red";


        when(itemService.findByBoxIdAndColor(boxId, color)).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems(String.valueOf(boxId), color);


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_box_not_valid_integer() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());
        String box = "abc";


        when(itemService.findAll()).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems(box, "");


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_color_null() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());


        when(itemService.findAll()).thenReturn(expectedItems);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> filterService.getResultItems("", null));
    }

    @Test
    void test_box_null() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());


        when(itemService.findAll()).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems(null, "");


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_box_empty_color_null() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());


        when(itemService.findAll()).thenReturn(expectedItems);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> filterService.getResultItems("", null));
    }

    @Test
    void test_box_null_color_empty() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());


        when(itemService.findAll()).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems(null, "");


        assertThat(result).isEqualTo(expectedItems);
    }

    @Test
    void test_no_items() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Collections.emptyList();


        when(itemService.findAll()).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems("", "");


        assertThat(result).isEmpty();
    }

    @Test
    void test_no_items_with_color() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Collections.emptyList();
        String color = "blue";


        when(itemService.findByColor(color)).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems("", color);


        assertThat(result).isEmpty();
    }

    @Test
    void test_no_items_in_box() {

        ItemService itemService = mock(ItemService.class);
        FilterServiceImpl filterService = new FilterServiceImpl(itemService);
        List<Item> expectedItems = Collections.emptyList();
        int boxId = 1;


        when(itemService.findByBoxId(boxId)).thenReturn(expectedItems);
        List<Item> result = filterService.getResultItems(String.valueOf(boxId), "");


        assertThat(result).isEmpty();
    }

}