package com.cdhy.ei.service.job.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhy.commons.utils.ParamsUtil;
import com.cdhy.commons.utils.SysConfigUtil;
import com.cdhy.commons.utils.sms.LinkSMSClient;
import com.cdhy.ei.dao.dict.IAccessMapper;
import com.cdhy.ei.service.job.IJobService;
import com.cdhy.ei.utils.JmailUtil;

@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    private IAccessMapper mapper;
    public static final String EMAIL_NAME = SysConfigUtil.getInstance().getProperites("email_name");

    @Override
    public void delteClear() {
	mapper.deleteClear();
    }

    @Override
    public void monitor() {
	List<Map<String, Object>> resultList = mapper.listLastStates();
	List<Map<String, Object>> errorList = new ArrayList<Map<String, Object>>();
	for (Map<String, Object> map : resultList) {
	    String mstatestr = ParamsUtil.getString4Map(map, "mstatestr");
	    if (mstatestr.indexOf("error") > -1) {
		errorList.add(map);
	    }
	}
	if (errorList.size() > 0) {
	    String title = "服务器:";
	    String htmlFile = "";
	    for (Map<String, Object> map : errorList) {
		String name = ParamsUtil.getString4Map(map, "name");
		String mstatestr = ParamsUtil.getString4Map(map, "mstatestr");
		String ctime = ParamsUtil.getString4Map(map, "ctime");
		title += "[" + name + ":" + ctime + "]";
		htmlFile += mstatestr + "------------------------<br/>";
	    }
	    htmlFile = title + "<br/>" + htmlFile;
	    List<Map<String, Object>> users = mapper.listSendUsers();
	    for (Map<String, Object> user : users) {
		String email = ParamsUtil.getString4Map(user, "email");
		sendEmail(email, htmlFile);
		String phone = ParamsUtil.getString4Map(user, "phone");
		sendSMS(phone, title + " [出现故障]");
	    }
	}
    }

    private void sendSMS(String phone, String title) {
	if (null == phone || null == title || "".equals(phone) || "".equals(title)) {
	    return;
	}
	try {
	    // 发送短信
	    String netResult = LinkSMSClient.send(phone, title);
	    System.out.println(netResult);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private static void sendEmail(String email, String htmlFile) {
	if (null == email || null == htmlFile || "".equals(email) || "".equals(htmlFile)) {
	    return;
	}
	try {
	    JmailUtil ms = new JmailUtil();
	    ms.setSubject(new String("沪友电票-服务状态".getBytes(), "utf-8"));// 主题
	    htmlFile += "<br/> http://test.hydzfp.com:55555";
	    ms.setContent(new String(htmlFile.getBytes(), "utf-8"), "text/html;charset=GBK");
	    ms.setFrom("沪友电票平台", EMAIL_NAME);
	    ms.setRecipients(email);
	    ms.setSentDate();
	    boolean netResult = ms.sendMail();
	    System.out.println("结束email:[" + netResult + "]");
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public void monitorJob() {
	String monitorJobTime = SysConfigUtil.getInstance().getProperites("monitorJob-time");
	if (null == monitorJobTime || "".equals(monitorJobTime.trim())) {
	    monitorJobTime = "10:00:00";
	}
	final String monitorJobTimeStr = monitorJobTime;
	Map<String, Object> logInfo = mapper.getISLog(new HashMap<String, Object>() {
	    {
		put("monitorJobTimeStr", monitorJobTimeStr);
	    }
	});
	int a = ParamsUtil.getInt4Map(logInfo, "a");
	int b = ParamsUtil.getInt4Map(logInfo, "b");
	if (!(a > 0 && b == 0)) {
	    return;
	}

	List<Map<String, Object>> users = mapper.listSendUsers();
	for (Map<String, Object> user : users) {
	    String email = ParamsUtil.getString4Map(user, "email");
	    sendEmail(email, "定期检测");
	    String phone = ParamsUtil.getString4Map(user, "phone");
	    sendSMS(phone, "定期检测");
	}
	mapper.saveLog();
    }
}
