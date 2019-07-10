package statistic.wheat.service;

import org.springframework.stereotype.Service;
import statistic.wheat.dao.ForecastDao;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@Service
public class ForecastService {

    @Resource
    ForecastDao forecastDao;

    public List getToday(Date date) {
        return forecastDao.getToday(date);
    }

    public String getYield() {
        String line;
        Process pr = null;
        try {
            String[] args1=new String[]{"python","D:\\python\\randomforeast.py"};
            pr = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream(), "utf-8"));

            while ((line = in.readLine()) != null) {
            }                System.out.println(line);

            in.close();
            return line;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}