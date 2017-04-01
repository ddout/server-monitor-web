package com.cdhy.ei.service.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FlushPendTaskJob {

    public static Logger log = Logger.getLogger(FlushPendTaskJob.class);

    @Autowired
    private IJobService service;

    public void exec() {
	log.debug("BEGIN------------- FlushPendTaskJob");
	// 清理垃圾数据
	service.delteClear();
	// 清理垃圾数据
	// 检测报警
	service.monitor();
	// 检测报警
	// 定期检测
	service.monitorJob();
	// 定期检测
	log.debug("END---------------- FlushPendTaskJob");
    }

}