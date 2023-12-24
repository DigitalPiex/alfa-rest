package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.repository.BoxRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BoxServiceImplTest {


    @Test
    void test_box_object_saved_successfully() {

        BoxRepository boxRepository = mock(BoxRepository.class);
        BoxServiceImpl boxService = new BoxServiceImpl(boxRepository);
        Box box = new Box(1L, 2L);


        when(boxRepository.save(box)).thenReturn(box);
        Box savedBox = boxService.save(box);


        assertThat(savedBox).isEqualTo(box);
        verify(boxRepository).save(box);
    }


    @Test
    void test_box_object_with_null_parentBox_saved_successfully() {

        BoxRepository boxRepository = mock(BoxRepository.class);
        BoxServiceImpl boxService = new BoxServiceImpl(boxRepository);
        Box box = new Box(1L, 2L);
        box.setParentBox(null);


        when(boxRepository.save(box)).thenReturn(box);
        Box savedBox = boxService.save(box);


        assertThat(savedBox).isEqualTo(box);
        verify(boxRepository).save(box);
    }


    @Test
    void test_box_object_with_null_subBoxes_saved_successfully() {

        BoxRepository boxRepository = mock(BoxRepository.class);
        BoxServiceImpl boxService = new BoxServiceImpl(boxRepository);
        Box box = new Box(1L, 2L);
        box.setSubBoxes(null);


        when(boxRepository.save(box)).thenReturn(box);
        Box savedBox = boxService.save(box);


        assertThat(savedBox).isEqualTo(box);
        verify(boxRepository).save(box);
    }


    @Test
    void test_box_object_with_null_items_saved_successfully() {

        BoxRepository boxRepository = mock(BoxRepository.class);
        BoxServiceImpl boxService = new BoxServiceImpl(boxRepository);
        Box box = new Box(1L, 2L);
        box.setItems(null);


        when(boxRepository.save(box)).thenReturn(box);
        Box savedBox = boxService.save(box);


        assertThat(savedBox).isEqualTo(box);
        verify(boxRepository).save(box);
    }


    @Test
    void test_box_object_with_empty_items_set_saved_successfully() {

        BoxRepository boxRepository = mock(BoxRepository.class);
        BoxServiceImpl boxService = new BoxServiceImpl(boxRepository);
        Box box = new Box(1L, 2L);
        box.setItems(new HashSet<>());


        when(boxRepository.save(box)).thenReturn(box);
        Box savedBox = boxService.save(box);


        assertThat(savedBox).isEqualTo(box);
        verify(boxRepository).save(box);
    }


    @Test
    void test_box_object_with_empty_subBoxes_set_saved_successfully() {

        BoxRepository boxRepository = mock(BoxRepository.class);
        BoxServiceImpl boxService = new BoxServiceImpl(boxRepository);
        Box box = new Box(1L, 2L);
        box.setSubBoxes(new HashSet<>());


        when(boxRepository.save(box)).thenReturn(box);
        Box savedBox = boxService.save(box);


        assertThat(savedBox).isEqualTo(box);
        verify(boxRepository).save(box);
    }

}