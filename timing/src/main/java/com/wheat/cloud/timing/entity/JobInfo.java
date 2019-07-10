package com.wheat.cloud.timing.entity;

import java.util.Date;

import org.quartz.JobDataMap;
import org.springframework.boot.autoconfigure.domain.EntityScan;

public class JobInfo {

	private int jobID;
	private String jobType;
	private String jobName;
	private String jobGroup;
	private String triggerName;
	private String triggerGroup;
	private String cronExpression;
	private Date nextExecuteTime;
	private Date previousExecuteTime;
	private Date startTime;
	private Date endTime;
	private long runTimes;
	private long duration;
	private String jobClass;
	private String jobMethod;
	private int count;
	private String trigggerState;
	private JobDataMap jobDataMap;
	private int misFireType;
	private int priority;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getMisFireType() {
		return misFireType;
	}

	public void setMisFireType(int misFireType) {
		this.misFireType = misFireType;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Date getNextExecuteTime() {
		return nextExecuteTime;
	}

	public void setNextExecuteTime(Date nextExecuteTime) {
		this.nextExecuteTime = nextExecuteTime;
	}

	public Date getPreviousExecuteTime() {
		return previousExecuteTime;
	}

	public void setPreviousExecuteTime(Date previousExecuteTime) {
		this.previousExecuteTime = previousExecuteTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getRunTimes() {
		return runTimes;
	}

	public void setRunTimes(long runTimes) {
		this.runTimes = runTimes;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobMethod() {
		return jobMethod;
	}

	public void setJobMethod(String jobMethod) {
		this.jobMethod = jobMethod;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTrigggerState() {
		return trigggerState;
	}

	public void setTrigggerState(String trigggerState) {
		this.trigggerState = trigggerState;
	}

	public JobDataMap getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}


    public JobInfo() {
    }

    @Override
	public String toString() {
		return "JobInfo [jobID=" + jobID + ", jobType=" + jobType
				+ ", jobName=" + jobName + ", jobGroup=" + jobGroup
				+ ", triggerName=" + triggerName + ", triggerGroup="
				+ triggerGroup + ", cronExpression=" + cronExpression
				+ ", nextExecuteTime=" + nextExecuteTime
				+ ", previousExecuteTime=" + previousExecuteTime
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", runTimes=" + runTimes + ", duration=" + duration

				+ ", jobClass=" + jobClass + ", jobMethod=" + jobMethod
				+ ", count=" + count + ", trigggerState=" + trigggerState
				+ ", jobDataMap=" + jobDataMap + "]";
	}

}
