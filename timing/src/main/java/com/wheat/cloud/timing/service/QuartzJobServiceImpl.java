package com.wheat.cloud.timing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wheat.cloud.timing.entity.JobInfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class QuartzJobServiceImpl implements QuartzJobService {


	/*@Autowired
	@Qualifier("Scheduler")*/
	private Scheduler sched;

	// 默认的JobGroupName和TriggerGroupName
	private static String DEFAULT_JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
	private static String DEFAULT_TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";

	@Override
	public void addJob(String jobName, String triggerName, Class jobClass,
			String cron) {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();

			TriggerKey triggerKey = TriggerKey.triggerKey(jobName,
					DEFAULT_JOB_GROUP_NAME);

			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null || trigger.equals("")) {
				// 任务名，任务组，任务执行类
				JobDetail jobDetail = JobBuilder.newJob(jobClass)
						.withIdentity(jobName, DEFAULT_JOB_GROUP_NAME).build();
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder
						.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName,
						DEFAULT_TRIGGER_GROUP_NAME);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(
						cron).withMisfireHandlingInstructionDoNothing());
				// 创建Trigger对象
				CronTrigger t = (CronTrigger) triggerBuilder.build();

				// 可以向datamap中添加键值对数据
				// 以后可以横向扩展 创建任务时可以添加参数
				// jobDetail.getJobDataMap().put(key, value);

				// 调度容器设置JobDetail和Trigger
				sched.scheduleJob(jobDetail, t);
			} else {
				// Trigger已存在，那么更新相应的定时设置
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(cron);

				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();

				// 按新的trigger重新设置job执行
				sched.rescheduleJob(triggerKey, trigger);
			}

			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addSimpleJob(String jobName, String triggerName,
			Class jobClass, String startTime) {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName,
					DEFAULT_JOB_GROUP_NAME);

			SimpleTrigger trigger = (SimpleTrigger) sched
					.getTrigger(triggerKey);
			if (trigger == null || trigger.equals("")) {
				// 任务名，任务组，任务执行类
				JobDetail jobDetail = JobBuilder.newJob(jobClass)
						.withIdentity(jobName, DEFAULT_JOB_GROUP_NAME).build();
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder
						.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName,
						DEFAULT_TRIGGER_GROUP_NAME);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(SimpleScheduleBuilder
						.simpleSchedule());
				// 创建Trigger对象
				SimpleTrigger t = (SimpleTrigger) triggerBuilder.build();

				// 可以向datamap中添加键值对数据
				// 以后可以横向扩展 创建任务时可以添加参数
				// jobDetail.getJobDataMap().put(key, value);

				// 调度容器设置JobDetail和Trigger
				sched.scheduleJob(jobDetail, t);
			} else {
				// Trigger已存在，那么更新相应的定时设置
				SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
						.simpleSchedule();

				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();

				// 按新的trigger重新设置job执行
				sched.rescheduleJob(triggerKey, trigger);
			}

			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean modifyJobTime(String jobName, String triggerName, String cron) {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,
					DEFAULT_TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				return false;
			}

			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				/** 方式一 ：调用 rescheduleJob 开始 */
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder
						.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName,
						DEFAULT_TRIGGER_GROUP_NAME);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder
						.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 方式一 ：修改一个任务的触发时间
				// sched.rescheduleJob(triggerKey, trigger);
				/** 方式一 ：调用 rescheduleJob 结束 */

				/** 方式二：先删除，然后在创建一个新的Job */
				JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName,
						DEFAULT_JOB_GROUP_NAME));
				Class<? extends Job> jobClass = jobDetail.getJobClass();
				removeJob(jobName, triggerName);
				addJob(jobName, triggerName,
						jobClass, cron);
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	// 删除某一个任务
	@Override
	public void removeJob(String jobName, String triggerName) {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();

			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,
					DEFAULT_TRIGGER_GROUP_NAME);

			sched.pauseTrigger(triggerKey);// 停止触发器
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(JobKey.jobKey(jobName, DEFAULT_JOB_GROUP_NAME));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void startJobs() {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void shutdownJobs() {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void modifyJobTime(String triggerName, String cron) {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();
			CronTrigger t = (CronTrigger) sched.getTrigger(TriggerKey
					.triggerKey(triggerName, DEFAULT_TRIGGER_GROUP_NAME));
			if (t == null) {
				return;
			}
			String oldCron = t.getCronExpression();
			if (!oldCron.equalsIgnoreCase(cron)) {
				t.getTriggerBuilder()
						.withSchedule(CronScheduleBuilder.cronSchedule(cron))
						.build();
				sched.resumeTrigger(TriggerKey.triggerKey(triggerName,
						DEFAULT_TRIGGER_GROUP_NAME));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pauseJob(String jobName) {
		try {
			//Scheduler sched = schedulerFactory.getScheduler();
			sched.pauseJob(JobKey.jobKey(jobName, DEFAULT_JOB_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resumeJob(String jobName) {
		try {
			//sched = schedulerFactory.getScheduler();
			sched.resumeJob(JobKey.jobKey(jobName, DEFAULT_JOB_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<JobInfo> getAllInfo() {
		ArrayList<JobInfo> list;
		try {
			list = new ArrayList<JobInfo>();
			//sched = schedulerFactory.getScheduler();
			List<String> triggerGroupNames = sched.getTriggerGroupNames();
			for (String triggerGroupName : triggerGroupNames) {
				Set<TriggerKey> triggerKeys = sched.getTriggerKeys(GroupMatcher
						.triggerGroupEquals(triggerGroupName));
				for (TriggerKey triggerKey : triggerKeys) {
					Trigger t = sched.getTrigger(triggerKey);
					if (t instanceof CronTrigger) {
						CronTrigger trigger = (CronTrigger) t;
						JobKey jobKey = trigger.getJobKey();
						JobDetail jd = sched.getJobDetail(jobKey);
						JobInfo jobInfo = new JobInfo();

						jobInfo.setMisFireType(trigger.getMisfireInstruction());
						;
						jobInfo.setPriority(trigger.getPriority());
						jobInfo.setJobName(jobKey.getName());
						jobInfo.setJobGroup(jobKey.getGroup());
						jobInfo.setTriggerName(triggerKey.getName());
						jobInfo.setTriggerGroup(triggerKey.getGroup());
						jobInfo.setCronExpression(trigger.getCronExpression());
						jobInfo.setNextExecuteTime(trigger.getNextFireTime());
						jobInfo.setPreviousExecuteTime(trigger
								.getPreviousFireTime());
						jobInfo.setStartTime(trigger.getStartTime());
						jobInfo.setEndTime(trigger.getEndTime());
						jobInfo.setJobClass(jd.getJobClass().getCanonicalName());
						// jobInfo.setDuration(Long.parseLong(jd.get));
						Trigger.TriggerState triggerState = sched
								.getTriggerState(trigger.getKey());
						jobInfo.setTrigggerState(triggerState.toString());// NONE无,
						list.add(jobInfo);
						System.out.println(jobInfo.toString());
					}
				}
			}
			return list;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean isShutDown() {
		//Scheduler sched;
		try {
			//sched = schedulerFactory.getScheduler();
			return sched.isShutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isStarted() {
		//Scheduler sched;
		try {
			//sched = schedulerFactory.getScheduler();
			return sched.isStarted();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}
}
