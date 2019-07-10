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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hour24")
public class Hour24Crawling {

	private static Logger log = LoggerFactory.getLogger(Hour24Crawling.class);

	@Resource
	JdbcTemplate jdbcTemplate;
	/**
	 * 定时爬取24小时数据
	 */

	// HTTPClient请求，利用Jsoup解析html网页
	@RequestMapping("/execute")
	public void executeHour24() {
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
				url = "http://www.weather.com.cn/weather1d/" + city[j]
						+ ".shtml";
				uri = new URIBuilder(url);
				hg = new HttpGet(uri.build());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// 设置请求头
			hg.setHeader(
					"Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			hg.setHeader("Accept-Encoding", "gzip, deflate");
			hg.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			hg.setHeader("Cache-Control", "no-cache");
			hg.setHeader("Connection", "keep-alive");
			hg.setHeader("Host", "www.weather.com.cn");
			hg.setHeader("Upgrade-Insecure-Requests", "1");
			hg.setHeader(
					"Cookie",
					"UM_distinctid=1677bacc36c318-06864df6ba12d8-50422618-13c680-1677bacc36e326; "
							+ "vjuids=9377f5f84.1677bacc68a.0.c5cf929c2799d; userNewsPort0=1; "
							+ "f_city=%E9%83%91%E5%B7%9E%7C101180101%7C; "
							+ "Hm_lvt_36dcc28125c1b7e65fa2190352951396=1546071468; Wa_lvt_13=1546071468;"
							+ " zs=101180101%7C%7C%7Cyd-uv; Wa_lvt_2=1546072400; "
							+ "CNZZDATA1257969847=1421622329-1543967425-https%253A%252F%252Fwww.baidu.com%252F%7C1546332806;"
							+ " CNZZDATA5652381=cnzz_eid%3D126216725-1543964774-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1546328474; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1546072202,1546333187,1547280297,1547344681; vjlast=1543968180.1547344682.11; Wa_lvt_1=1546333187,1547280297,1547344682,1547344682; CNZZDATA1262608253=2132717208-1546067788-https%253A%252F%252Fwww.baidu.com%252F%7C1547344007; defaultCty=101180101; defaultCtyName=%u90D1%u5DDE; "
							+ "Hm_lpvt_080dabacb001ad3dc8b9b9049b36d43b=1547344697; Wa_lpvt_1=1547344697");
			hg.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
			hg.setHeader("Referer", "http://www.weather.com.cn/");
			// 发送请求
			HttpEntity entity = null;
			String line, page = "";

			try {
				response = h.execute(hg);
				// 获取请求结果
				entity = response.getEntity();
				inputstream = entity.getContent();
				BufferedReader bufferedreader = new BufferedReader(
						new InputStreamReader(inputstream, "UTF-8"));
				while ((line = bufferedreader.readLine()) != null) {
					page += line + "\n";
				}

				Document doc = Jsoup.parse(page);

				Element scriptNode = doc.select(
						"body > div.con.today.clearfix > div.left.fl > script")
						.get(0);

				Node observe24h = scriptNode.childNode(0);

				String data_24h = observe24h.toString();

				data_24h = data_24h.trim();
				data_24h = data_24h.substring(data_24h.indexOf("=") + 1,
						data_24h.length() - 1);

				JSONObject jsonObject = (JSONObject) JSON.parse(data_24h);
				show(jsonObject.getJSONObject("od"), city[j]);

			} catch (ClientProtocolException e1) {

				System.out.println("请求超时等问题");
				e1.printStackTrace();
			} catch (IOException e1) {

				System.out.println("I/O问题");
				e1.printStackTrace();
			} finally {
				try {
					inputstream.close();
					response.close();
				} catch (IOException e) {

					System.out.println("I/O、response流关闭");
					e.printStackTrace();
				}
			}
		}

		if (j == city.length) {
			log.info("过去24小时天气信息爬取完成");
		}

	}

	// 将JSON数据转化成object数组(用于存储)
	public void show(JSONObject jsonObject, String cityId) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		String format = new String("yyyyMMddHHmmss");
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		String dataTime = jsonObject.getString("od0");
		Date date = null;
		try {
			date = sdf.parse(dataTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal.setTime(date);

		JSONArray data25d = jsonObject.getJSONArray("od2");

		int flag = 0;
		for (int i = 0; i < data25d.size(); i++) {

			JSONObject data1d = (JSONObject) data25d.get(i);

			// 获取当前小时
			int hour = Integer.parseInt(data1d.getString("od21"));

			String timeString = "";

			if (i == 0) {
				flag = hour;
			}
			if (flag < 0) {
				timeString = cal.get(Calendar.YEAR)
						+ String.format("%02d", cal.get(Calendar.MONTH) + 1)
						+ String.format("%02d", cal.get(Calendar.DATE) - 1)
						+ String.format("%02d", hour) + "0000";
			} else {
				timeString = cal.get(Calendar.YEAR)
						+ String.format("%02d", cal.get(Calendar.MONTH) + 1)
						+ String.format("%02d", cal.get(Calendar.DATE))
						+ String.format("%02d", hour) + "0000";
			}
			flag--;

			Date time = null;
			try {
				time = sdf.parse(timeString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String temperature = data1d.getString("od22");
			// 该项未知
			String other = data1d.getString("od23");
			String windDirection = data1d.getString("od24");
			// 获取当前风速等级
			String windPower = data1d.getString("od25");
			// 获取当前湿度
			String humidity = data1d.getString("od27");
			String rain = data1d.getString("od26");
			String aqi_pm25 = data1d.getString("od28");
			// 构造当前主键
			String id = cityId + time.getTime();

			save(new Object[] { id, cityId, time, temperature, windPower,
					windDirection, rain, humidity, aqi_pm25, other });
		}
	}

	// 将数据存入数据库
	public void save(Object[] args) {
		String sql = "replace INTO weather_24d " + "(id, city_id, time,"
				+ " temperature, wind_power, "
				+ "wind_direction, rain, humdity," + " aqi_pm25, other) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int m = jdbcTemplate.update(sql, args);
		if (m <= 0) {
			log.info(args[1].toString() + ": 该地区的天气数据爬取失败！");
		} else {
			log.info(args[1].toString() + ": 爬取成功!");
		}
	}

}
