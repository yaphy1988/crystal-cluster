package com.ai.mine.crystal.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseInformationJob extends AbstractJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doTask() {

        logger.warn("CaseInformationJob，自动任务开始执行……");
    }
}
