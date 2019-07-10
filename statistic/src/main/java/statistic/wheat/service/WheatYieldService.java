package statistic.wheat.service;


import org.springframework.stereotype.Service;
import statistic.wheat.dao.WheatYieldDao;
import statistic.wheat.entity.WheatYield;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WheatYieldService {

    @Resource
    WheatYieldDao wheatYieldDao;

    public List<WheatYield> findAll() {
        return wheatYieldDao.findAll();
    }

    //返回总产量
    public List findYield(String  cityNmae){
        return wheatYieldDao.findYield(cityNmae);
    };
    //返回亩均产量
    public List findAverageProduct(String  cityNmae){
        return wheatYieldDao.findAverageProduct(cityNmae);
    };
    //返回种植面积
    public List findArea(String  cityNmae){
        return wheatYieldDao.findArea(cityNmae);
    };
    //返回总产量增长率
    public List findYieldGrowthRate(String  cityNmae){
        return wheatYieldDao.findYieldGrowthRate(cityNmae);
    };
    //返回亩均产量增长值
    public List findaverageProductGrowthRate(String  cityNmae){
        return wheatYieldDao.findaverageProductGrowthRate(cityNmae);
    };
    //返回种植面积增长值
    public List findAreaGrowthRate(String  cityNmae){
        return wheatYieldDao.findAreaGrowthRate(cityNmae);
    };


}
