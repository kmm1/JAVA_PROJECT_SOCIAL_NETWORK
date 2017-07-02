package com.itacademy.dao;

import com.itacademy.dao.common.BaseDaoImpl;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlashmobDaoImpl extends BaseDaoImpl<Flashmob> implements FlashmobDao {

    @Override
    public List<Event> findAllEvents() {
        return getSessionFactory().getCurrentSession()
                .createQuery("from Event ", Event.class)
                .getResultList();
    }

}