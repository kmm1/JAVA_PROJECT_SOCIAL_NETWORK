package com.itacademy.service;

import com.itacademy.dao.FlashmobDao;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FlashmobServiceImpl implements FlashmobService {

    private final FlashmobDao flashmobDao;

    @Autowired
    public FlashmobServiceImpl(FlashmobDao flashmobDao) {
        this.flashmobDao = flashmobDao;
    }


    @Override
    public Flashmob findById(Long id) {
        return flashmobDao.findById(id);
    }

    @Override
    public Long save(Flashmob flashmob) {
        return flashmobDao.save(flashmob);
    }

    @Override
    public void update(Flashmob flashmob) {
        flashmobDao.update(flashmob);
    }

    @Override
    public void delete(Flashmob flashmob) {
        flashmobDao.delete(flashmob);
    }

    @Override
    public List<Flashmob> findAll() {
        return flashmobDao.findAll();
    }

    @Override
    public List<Event> findAllEvents() {
        return flashmobDao.findAllEvents();
    }
}