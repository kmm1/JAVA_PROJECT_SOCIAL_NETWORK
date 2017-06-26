package com.itacademy.service;

import com.itacademy.dao.FlashmobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlashmobServiceImpl implements FlashmobService {

    private final FlashmobDao flashmobDao;

    @Autowired
    public FlashmobServiceImpl(FlashmobDao flashmobDao) {
        this.flashmobDao = flashmobDao;
    }


}