package com.example.restalfabank.service.impl;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.repository.BoxRepository;
import com.example.restalfabank.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;

    public void save(Box box) {
        boxRepository.save(box);
    }
}
