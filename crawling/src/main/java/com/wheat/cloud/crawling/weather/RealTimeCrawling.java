package com.wheat.cloud.crawling.weather;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("realTime")
public class RealTimeCrawling {

	private static Logger log = LoggerFactory.getLogger(RealTimeCrawling.class);

	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 定时爬取一条数据
	 */
	@RequestMapping("/execute")
	public void executeRealTime() {
		String[] city = new String[124];
		// 读取城市ID
		int i = 0;
		String str = "";
		try {
			URL resource = this.getClass().getResource("/cityId.txt");
			String path = resource.getPath();// 获取文件的绝对路径
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path)));
			while ((str = br.readLine()) != null) {
				city[i] = str;
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000)
				.setConnectionRequestTimeout(60000)
				.setStaleConnectionCheckEnabled(true).build();
		// 创建httpClient对象
		CloseableHttpClient h = HttpClients.custom()
				.setDefaultRequestConfig(defaultRequestConfig).build();

		// 创建并设置URI
		URIBuilder uri = null;
		// 创建Get请求
		HttpGet hg = null;
		String url = "";
		// 创建响应对象
		CloseableHttpResponse response = null;
		InputStream inputstream = null;
		int j = 0;
		for (; j < city.length; j++) {

			try {
				url = "http://d1.weather.com.cn/sk_2d/" + city[j] + ".html?_="
						+ System.currentTimeMillis();
				uri = new URIBuilder(url);
				hg = new HttpGet(uri.build());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// 设置请求头
			hg.setHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			hg.setHeader("Accept-Encoding", "gzip, deflate");
			hg.setHeader("Accept-Language",
					"zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
			hg.setHeader("Cache-Control", "no-cache");
			hg.setHeader("Connection", "keep-alive");
			hg.setHeader("Host", "d1.weather.com.cn");
			hg.setHeader("Upgrade-Insecure-Requests", "1");
			hg.setHeader("Cookie",
					"f_city=%E9%83%91%E5%B7%9E%7C101180101%7C; Hm_"
							+ "lvt_080dabacb001ad3dc8b9b9049b36d"
							+ "43b=1546482322; Hm_lpvt_080dabacb001a");
			hg.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
			hg.setHeader("Referer",
					"http://www.weather.com.cn/weather1dn/101180101.shtml");
			// 发送请求
			HttpEntity entity = null;
			String line, Sline = "";

			try {
				response = h.execute(hg);
				// 获取请求结果
				entity = response.getEntity();
				inputstream = entity.getContent();
				BufferedReader bufferedreader = new BufferedReader(
						new InputStreamReader(inputstream, "UTF-8"));
				while ((line = bufferedreader.readLine()) != null) {

					Sline += line + "\n";
				}

				Sline = Sline.substring(Sline.indexOf('{'));
				log.info(Sline);
				JSONObject jsonObject = JSON.parseObject(Sline);
				show(jsonObject);

			} catch (ClientProtocolException e1) {

				log.error("请求超时等问题");
				e1.printStackTrace();
			} catch (IOException e1) {

				log.error("I/O问题");
				e1.printStackTrace();
			} finally {
				try {
					inputstream.close();
					response.close();
				} catch (IOException e) {

					log.error("I/O、response流关闭");
					e.printStackTrace();
				}
			}
		}
		if (j == city.length) {
			log.info(" 实时天气信息爬取完成");
		}

	}

	/**
	 * 样例对照表
	 * <p>
	 * var dataSK = { "nameen":"zhengzhou", "cityname":"郑州", "city":"101180101",
	 * "temp":"1", 摄氏度 "tempf":"33", 华氏度 "WD":"西南风", 风向 "wde":"SW", 风向英文
	 * "WS":"1级", 风力等级 "wse":"<12km/h", 风速 "SD":"52%", 湿度 "time":"11:25",
	 * "weather":"晴", "weathere":"Sunny", "weathercode":"d00", "qy":"1019", 气压
	 * "njd":"4.94km", 能见度 "sd":"52%", 湿度 "rain":"0.0", 降雨量 "rain24h":"0",
	 * "aqi":"214", "limitnumber":"", "aqi_pm25":"214", pm2.5
	 * "date":"01月03日(星期四)" }
	 */

	public void show(JSONObject jsonObject) {
		// 获取城市编号
		String cityId = jsonObject.getString("city");
		// System.out.println(cityId);

		String cityName = jsonObject.getString("cityname");
		// System.out.println(cityName);

		// 获取当前气温
		String temperature = jsonObject.getString("temp");
		// System.out.println("当前气温" + ":" + temperature);

		// 获取当前风向
		String windDirection = jsonObject.getString("WD");
		// System.out.println("风向：" + windDirection);
		// 获取当前风向
		String windDirectionEn = jsonObject.getString("wde");
		// System.out.println("风向符号：" + windDirectionEn);

		// 获取当前风速等级
		String windPower = jsonObject.getString("WS");
		// System.out.println("风力：" + windPower);

		// 获取当前风速
		String windSpeed = jsonObject.getString("wse").replaceAll("&lt;", "");
		// System.out.println("风力：" + windSpeed);

		// 获取当前湿度
		String humidity = jsonObject.getString("SD");
		// System.out.println("湿度：" + humidity);

		String time = jsonObject.getString("time");
		// System.out.println("时间：" + time);

		String weather = jsonObject.getString("weather");
		// System.out.println("天气中文：" + weather);

		String weatherEn = jsonObject.getString("weathere");
		// System.out.println("天气英文：" + weatherEn);

		String weatherCode = jsonObject.getString("weathercode");
		// System.out.println("天气代号：" + weatherCode);

		String airPressure = jsonObject.getString("qy");
		// System.out.println("气压：" + airPressure);

		String visibility = jsonObject.getString("njd");
		// System.out.println("能见度：" + visibility);

		String rain = jsonObject.getString("rain");
		// System.out.println("降雨量：" + rain);

		String rain24h = jsonObject.getString("rain24h");
		// System.out.println("日降雨量" + rain24h);

		String aqi_pm25 = jsonObject.getString("aqi");
		// System.out.println("PM2.5：" + aqi_pm25);

		String date = jsonObject.getString("date").substring(0, 6);
		// System.out.println("时间" + date);

		Calendar cal = Calendar.getInstance();
		// 时间转换
		String format = new String("hh:mm-MM月dd日yy");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date updateTime = null;
		
		try {
			updateTime = sdf.parse(time + "-" + date + cal.get(Calendar.YEAR));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String id = cityId + new Date().getTime();

		String sql = "INSERT INTO weather_sk " + "(`id`, `city_id`, `update_time`, "
				+ "`temperature`, `wind_speed`, `wind_power`, "
				+ "`wind_direction_en`, `wind_direction`, `humidity`, "
				+ "`weather`, `weather_en`, `weather_code`, `visibility`, "
				+ "`air_pressure`, `rain24h`, `rain`, `aqi_pm25`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int m = jdbcTemplate.update(sql, id, cityId, updateTime, temperature, windSpeed,
				windPower, windDirectionEn, windDirection, humidity,
				weather, weatherEn, weatherCode, visibility, airPressure,
				rain24h, rain, aqi_pm25);
			/*m = qr.update(con, sql, id, cityId, updateTime, temperature, windSpeed,
					windPower, windDirectionEn, windDirection, humidity,
					weather, weatherEn, weatherCode, visibility, airPressure,
					rain24h, rain, aqi_pm25);*/
		if (m <= 0) {
			log.info(cityName + ": 该地区的天气数据爬取失败！");
		}
	}

}
