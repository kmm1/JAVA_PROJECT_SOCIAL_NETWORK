package com.itacademy.service;


import com.itacademy.entity.Blog;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;

import java.util.List;

public interface FlashmobService {

    Flashmob findById(Long id);

    Long save(Flashmob flashmob);

    void update(Flashmob flashmob);

    void delete(Flashmob flashmob);

    List<Flashmob> findAll();

    List<Event> findAllEvents();


}
