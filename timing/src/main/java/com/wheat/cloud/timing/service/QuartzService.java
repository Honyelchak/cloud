package com.wheat.cloud.timing.service;

import java.util.List;

import com.wheat.cloud.timing.entity.JobInfo;

public interface QuartzService {

	/**
	 * 添加一个定时任务
	 * 
	 * @Description: 添加一个定时任务
	 * @param jobName
	 * @param triggerName
	 * @param jobClass
	 * @param cron
	 */
	public void addJob(String jobName, String triggerName, Class jobClass,
                       String cron);

	/**
	 * 添加一个简单任务
	 * 
	 * @Description: 添加一个简单任务
	 * @param jobName
	 * @param triggerName
	 * @param jobClass
	 * @param start
	 */
	public void addSimpleJob(String jobName, String triggerName,
                             Class jobClass, String start);

	/**
	 * 暂停指定的任务
	 * 
	 * @param jobName
	 */
	public void pauseJob(String jobName);

	/**
	 * 恢复指定的任务
	 * 
	 * @param jobName
	 */
	public void resumeJob(String jobName);

	/**
	 * 修改指定任务调度时间
	 * 
	 * @param jobName
	 * @param triggerName
	 * @param cron
	 *            | cron表达式
	 */
	public boolean modifyJobTime(String jobName, String triggerName, String cron);

	/**
	 * 修改触发器调度时间
	 * 
	 * @param triggerName
	 * @param cron
	 *            | cron表达式
	 */
	public void modifyJobTime(String triggerName, String cron);

	/**
	 * 移除一个任务
	 * 
	 * @Description: 移除一个任务
	 * @param jobName
	 * @param triggerName
	 */
	public void removeJob(String jobName, String triggerName);

	/**
	 * 启动所有定时任务
	 * 
	 * @Description:启动所有定时任务
	 */
	public void startJobs();

	/**
	 * 关闭所有定时任务
	 * 
	 * @Description:关闭所有定时任务
	 */
	public void shutdownJobs();

	/**
	 * 获取所有的任务
	 * 
	 * @Description: 获取所有的任务
	 */
	public List<JobInfo> getAllInfo();
	
	/**
	 * 调度器是否关闭
	 * @return boolean
	 */
	public boolean isShutDown();
	
	/**
	 * 调度器是否启动
	 * @return boolean
	 */
	public boolean isStarted();
}
