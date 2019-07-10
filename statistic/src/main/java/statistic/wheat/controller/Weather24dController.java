package statistic.wheat.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import statistic.wheat.service.Weather24dService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("weather24d")
public class Weather24dController {

    @Resource
    Weather24dService weather24dService;

    @RequestMapping("/week")
    @ResponseBody
    public ModelAndView findOneWeek(@RequestBody String cityId) throws Exception {
        HashMap map = new HashMap();
        Date date=new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
        //System.out.println(calendar.getTime());
        //System.out.println(cityId);
        String city = cityId.substring(0,9);
        //System.out.println(city);
        List list = weather24dService.findOneWeeek(date, city);
        List listtime = weather24dService.findOneWeeek1(date, city);
        List<String> date1 = new ArrayList<String>();
        List<String> date2 = new ArrayList<String>();
        //List<Integer> listweektime = new ArrayList<Integer>();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");//设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("HH");//设置小时日期格式
        String[] da = null;
        int i = 0;
        while (i<listtime.size()){
            date1.add(df.format(listtime.get(i)));
            String cd = sdf.format(listtime.get(i));
            String c = cd.substring(0,1);
            //System.out.println(c);
            if(c.equals("0")){
                //System.out.println("111111111");
                cd = cd.substring(1);
            }
            date2.add(cd);
            //String T = String.valueOf(listtime.get(i));
            //System.out.println(T);
            //int ab = weather24dService.dayForWeek(T);
            //System.out.println(ab);
            //listweektime.add(ab);
            i++;
        }
        List<List<Integer>> myarr = new ArrayList<List<Integer>>() ;
        int DC=0;
        List<Object> aa = new ArrayList<Object>();
        Object ab = date1.get(0);
        aa.add(ab);
        for ( int m = 0; m < listtime.size(); m++) {
            List<Integer> list11 = new ArrayList<Integer>();
            if (date1.get(m).equals(ab)) {
            }else{
                ab = date1.get(m);
                aa.add(ab);
                DC++;
            }
            for ( int j = 0; j < 3; j++) { //二维长度为3
                if (j==0) {
                    list11.add(DC); // 赋值，每个数组元素的值为i+j
                }
                if (j==1){
                    list11.add(Integer.parseInt(date2.get(m)));
                }
                if (j==2){
                    if (String.valueOf(list.get(m)).equals("null")||list.get(m)==null) {
                        list11.add(0);
                    }else{
                        list11.add(Integer.parseInt(String.valueOf(list.get(m))));
                    }
                }
            }
            myarr.add(list11);
        }
        System.out.println(aa);
        System.out.println(myarr);
        map.put("y",aa);
        map.put("temperature", myarr);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/rain")
    @ResponseBody
    public ModelAndView findOneWeek2(@RequestBody String cityId) throws Exception {
        HashMap map = new HashMap();
        Date date=new Date();
        //System.out.println(calendar.getTime());
        String city = cityId.substring(0,9);
        System.out.println(city);
        List list = weather24dService.findOneWeeek2(date, city);
        List listtime = weather24dService.findOneWeeek1(date, city);
        List<String> date1 = new ArrayList<String>();
        List<String> date2 = new ArrayList<String>();
        //List<Integer> listweektime = new ArrayList<Integer>();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd");//设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("HH");//设置小时日期格式

        int i = 0;
        while (i<listtime.size()){
            date1.add(df.format(listtime.get(i)));
            String cd = sdf.format(listtime.get(i));
            String c = cd.substring(0,1);
            if(c.equals("0")){
                cd = cd.substring(1);
            }
            date2.add(cd);
            i++;
        }
        List<List<Object>> myarr = new ArrayList<List<Object>>();
        int DC=0;
        List<Object> aa = new ArrayList<Object>();
        Object ab = date1.get(0);
        aa.add(ab);
        for ( int m = 0; m < listtime.size(); m++) {
            List<Object> list11 = new ArrayList<Object>();
            if (date1.get(m).equals(ab)) {
            }else{
                ab = date1.get(m);
                aa.add(ab);
                DC++;
            }
            for ( int j = 0; j < 3; j++) { //二维长度为3
                if (j==0) {
                    list11.add(DC); // 赋值，每个数组元素的值为i+j
                }
                if (j==1){
                    list11.add(Integer.parseInt(date2.get(m)));
                }
                if (j==2){
                    if (String.valueOf(list.get(m)).equals("null")||list.get(m)==null) {
                        list11.add(0.0);
                    }else{
                        list11.add(Double.parseDouble(String.valueOf(list.get(m))));
                    }
                }
            }
            myarr.add(list11);
        }
        List<Object> bb = new ArrayList<Object>();
        for (int s=aa.size()-1;s>=0;s--){
            bb.add(aa.get(s));
        }

        map.put("y",bb);
        map.put("rain", myarr);
        return new ModelAndView(new MappingJackson2JsonView(), map);


    }



}
