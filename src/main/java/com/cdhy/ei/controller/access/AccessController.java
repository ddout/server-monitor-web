package com.cdhy.ei.controller.access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhy.commons.utils.ParamsUtil;
import com.cdhy.commons.utils.exception.BizException;
import com.cdhy.commons.utils.framework.MD5;
import com.cdhy.commons.utils.model.Result;
import com.cdhy.ei.service.dict.IAccessService;
import com.cdhy.ei.utils.ContextHolderUtils;

@Controller
@RequestMapping("/access")
public class AccessController {
    private static final Logger log = Logger.getLogger(AccessController.class);

    public static final String SESSION_USER = "session_user";

    @Autowired
    private IAccessService service;

    private boolean authUser(final String user, String pwd) {
	final String pwds = MD5.MD5Encode(user + "_" + pwd);
	return service.authUser(new HashMap<String, Object>() {
	    private static final long serialVersionUID = -1364578724725624630L;

	    {
		put("user", user);
		put("pwd", pwds);
	    }
	});
    }

    private boolean checkAuthUser() {
	// return true;
	Object user = ContextHolderUtils.getSession().getAttribute(SESSION_USER);
	if (null != user && !"".equals(user)) {
	    return true;
	}
	return false;
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Object auth(@RequestParam Map<String, Object> parm) {
	Result obj = new Result();
	try {
	    String user = ParamsUtil.getString4Map(parm, "user");
	    if (true == authUser(user, ParamsUtil.getString4Map(parm, "pwd"))) {
		ContextHolderUtils.getSession().setAttribute(SESSION_USER, user);
	    } else {
		throw new BizException("auth not valid");
	    }
	} catch (BizException e) {
	    log.debug("获取失败", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(e.getMessage());
	} catch (Exception e) {
	    log.error("获取异常", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(Result.RESULT_ERROR_MSG);
	}
	return obj;
    }

    @RequestMapping("/viewA")
    @ResponseBody
    public Object viewA(@RequestParam Map<String, Object> parm) {
	Result obj = new Result();
	try {
	    if (true == checkAuthUser()) {
		List<Map<String, Object>> serverInfo = service.viewA(parm);
		obj.setRows(serverInfo);
	    } else {
		throw new BizException("auth not valid");
	    }
	} catch (BizException e) {
	    log.debug("获取失败", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(e.getMessage());
	} catch (Exception e) {
	    log.error("获取异常", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(Result.RESULT_ERROR_MSG);
	}
	return obj;
    }

    @RequestMapping("/viewB")
    @ResponseBody
    public Object viewB(@RequestParam Map<String, Object> parm) {
	Result obj = new Result();
	try {
	    if (true == checkAuthUser()) {
		Map<String, Object> serverInfo = service.viewB(parm);
		obj.setRows(serverInfo);
	    } else {
		throw new BizException("auth not valid");
	    }
	} catch (BizException e) {
	    log.debug("获取失败", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(e.getMessage());
	} catch (Exception e) {
	    log.error("获取异常", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(Result.RESULT_ERROR_MSG);
	}
	return obj;
    }

    @RequestMapping("/viewNextB")
    @ResponseBody
    public Object viewNextB(@RequestParam Map<String, Object> parm) {
	Result obj = new Result();
	try {
	    if (true == checkAuthUser()) {
		Map<String, Object> serverInfo = service.viewNextB(parm);
		obj.setRows(serverInfo);
	    } else {
		throw new BizException("auth not valid");
	    }
	} catch (BizException e) {
	    log.debug("获取失败", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(e.getMessage());
	} catch (Exception e) {
	    log.error("获取异常", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(Result.RESULT_ERROR_MSG);
	}
	return obj;
    }

    @RequestMapping("/viewPrevB")
    @ResponseBody
    public Object viewPrevB(@RequestParam Map<String, Object> parm) {
	Result obj = new Result();
	try {
	    if (true == checkAuthUser()) {
		Map<String, Object> serverInfo = service.viewPrevB(parm);
		obj.setRows(serverInfo);
	    } else {
		throw new BizException("auth not valid");
	    }
	} catch (BizException e) {
	    log.debug("获取失败", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(e.getMessage());
	} catch (Exception e) {
	    log.error("获取异常", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(Result.RESULT_ERROR_MSG);
	}
	return obj;
    }

    @RequestMapping("/regist")
    @ResponseBody
    public Object regist(@RequestParam Map<String, Object> parm) {
	Result obj = new Result();
	try {
	    boolean flag = service.saveRegist(parm);
	    obj.setRows(flag);
	} catch (BizException e) {
	    log.debug("获取失败", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(e.getMessage());
	} catch (Exception e) {
	    log.error("获取异常", e);
	    obj.setResult(Result.RESULT_ERROR);
	    obj.setMsg(Result.RESULT_ERROR_MSG);
	}
	return obj;
    }

}
