package statistic.wheat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import statistic.wheat.dao.IndexDataDao;

import java.util.List;
import java.util.Map;

@Service
public class IndexDataService {

    @Autowired
    IndexDataDao indexDataDao;

    public List findIndexData(){
        List list=indexDataDao.getToday();
        return list;
    }
}
