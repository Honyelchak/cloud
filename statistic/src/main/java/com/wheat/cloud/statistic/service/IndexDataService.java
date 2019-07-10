package com.wheat.cloud.statistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wheat.cloud.statistic.dao.IndexDataDao;

import java.util.List;

@Service
public class IndexDataService {

    @Autowired
    IndexDataDao indexDataDao;

    public List findIndexData(){
        List list=indexDataDao.getToday();
        return list;
    }
}
