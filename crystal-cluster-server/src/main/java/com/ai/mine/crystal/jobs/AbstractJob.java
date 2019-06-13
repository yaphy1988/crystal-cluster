/*
 * 文 件 名:  AbstractJob
 * 版    权:  AsiaInfo Technologies (Nanjing), Inc. Copyright 1993-2016,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  gejun
 * 修改时间:  2017年08月15日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.mine.crystal.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * AbstractJob
 *
 */
public abstract class AbstractJob implements Job {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    public void execute(JobExecutionContext context) throws JobExecutionException {
        boolean pro = proDO();
        log.debug(getClass().getSimpleName() + ":前置任务完成...result:" + pro);
        if(pro) {
            doTask();
            log.debug(getClass().getSimpleName() + ":执行任务完成...");
            afterDO();
            log.debug(getClass().getSimpleName() + ":后置任务完成...");
        }
    }

    /**
     * 具体任务内容，该方法不要抛出任何异常，全部内部捕获
     */
    protected abstract void doTask();

    /**
     * 定时任务前置工作，返回true则开始执行任务，返回false则终止本次任务。
     * 基类中空实现，默认返回true，如子类业务需要，重写该方法即可
     */
    protected boolean proDO() {
        return true;
    }

    /**
     * 定时任务后置工作，处理任务执行后需要做的扫尾工作。
     * 基类中空实现，如子类业务需要，重写该方法即可
     */
    protected void afterDO() {

    }
}
