package com.itacademy.dao;

import com.itacademy.dao.common.BaseDao;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;

import java.util.List;


public interface FlashmobDao extends BaseDao<Flashmob> {
     List<Event> findAllEvents();

    }
