package com.wheat.cloud.timing.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wheat.cloud.timing.entity.JobInfo;
import com.wheat.cloud.timing.service.QuartzJobService;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


///*@Controller
//@RequestMapping("/quartz")*/
public class QuartzController{
	private static final long serialVersionUID = 1L;

	/*@Resource*/
	private QuartzJobService q;

	private static Logger log = LoggerFactory.getLogger(QuartzController.class);

	//加入Qulifier注解，通过名称注入bean


	/*protected void doGet(){
		*//*request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");*//*
		String action = request.getParameter("action");
		if (action.equals("get")) {
			getAll(request, response);
			return;
		} else if (action.equals("delete")) {
			delete(request, response);
		} else if (action.equals("add")) {
			add(request, response);
		} else if (action.equals("startAll")) {
			startAll(request, response);
		} else if (action.equals("modifyJobTime")) {
			modifyJobTime(request, response);
		} else if (action.equals("stopAll")) {
			stopAll(request, response);
		} else if (action.equals("resume")) {
			resume(request, response);
		} else if (action.equals("pause")) {
			pause(request, response);
		}
		getAll(request, response);
	}*/

	@RequestMapping("/resume")
	private ModelAndView resume(String jobName) {
		//String jobName = request.getParameter("jobName");
		q.resumeJob(jobName);
		return new ModelAndView();
	}
	@RequestMapping("/getAll")
	private String getAll(Map<String, Object> map) {
		List<JobInfo> allInfo = q.getAllInfo();
		boolean isStarted = q.isStarted();
		map.put("joblist", allInfo);
		map.put("isStarted", isStarted);
		return "index";
		/*request.setAttribute("isStarted", isStarted);
		request.setAttribute("pageBean", allInfo);
		request.getRequestDispatcher("/src/main/webapp/index.html").forward(request, response);*/
	}
	@RequestMapping("/getAll")
	private void add(JobInfo jobInfo){
		Class<?> cla;
		try {
			/*String jobName = request.getParameter("jobName");
			String triggerName = request.getParameter("triggerName");
			String jobClass = request.getParameter("jobClass");*/
			cla = Class.forName(jobInfo.getJobClass());
			//String cron = request.getParameter("cron");
			//q.addJob(jobName, triggerName, cla, cron);
			q.addJob(jobInfo.getJobName(), jobInfo.getTriggerName(), cla, jobInfo.getCronExpression());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/delete")
	private void delete(JobInfo jobInfo){

		/*String jobName = request.getParameter("jobName");
		String triggerName = request.getParameter("triggerName");*/

		//q.removeJob(jobName, triggerName);
		q.removeJob(jobInfo.getJobName(), jobInfo.getTriggerName());
	}

	@RequestMapping("/startAll")
	private String startAll(){
		q.startJobs();
		return "redirect:/getAll";
	}
	@RequestMapping("/stopAll")
	private void stopAll(){
		q.shutdownJobs();
	}
	@RequestMapping("/modifyJobTime")
	private void modifyJobTime(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String jobName = request.getParameter("jobName");
		String triggerName = request.getParameter("triggerName");
		String cron = request.getParameter("cron");
		cron = cron.replace("？", "?");
		boolean f = q.modifyJobTime(jobName, triggerName, cron);
		if (f) {
			System.out.println(jobName + "任务时间修改完成！");
		} else {
			System.out.println(jobName + "任务时间修改失败！");
		}
	}
	@RequestMapping("/pause")
	private void pause(JobInfo jobInfo) {
		/*String jobName = request.getParameter("jobName");*/
		q.pauseJob(jobInfo.getJobName());
		System.out.println(jobInfo.getJobName() + "任务暂停成功！");
	}

}
