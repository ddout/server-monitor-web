package com.cdhy.ei.service.dict.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhy.commons.utils.ParamsUtil;
import com.cdhy.commons.utils.SysConfigUtil;
import com.cdhy.ei.dao.dict.IAccessMapper;
import com.cdhy.ei.service.dict.IAccessService;

@Service
public class AccessServiceImpl implements IAccessService {
    private static final String REGISTPWD = SysConfigUtil.getInstance().getProperites("REGISTPWD");
    @Autowired
    private IAccessMapper mapper;

    @Override
    public boolean saveRegist(Map<String, Object> parm) {
	String rpwd = ParamsUtil.getString4Map(parm, "rpwd");// 注册密码
	// String name = ParamsUtil.getString4Map(parm, "name");// 主机名称
	// String ip = ParamsUtil.getString4Map(parm, "ip");// 主机ip
	// String note = ParamsUtil.getString4Map(parm, "note");// 备注
	String mstate_str = ParamsUtil.getString4Map(parm, "mstate_str");// 备注
	if (!REGISTPWD.equals(rpwd)) {
	    throw new RuntimeException("rpwd is error");
	}
	Map<String, Object> minfo = mapper.getM(parm);
	if (minfo != null && minfo.size() > 0) {
	    mapper.updateLastTime(parm);
	} else {
	    mapper.saveM(parm);
	}
	if (!"".equals(mstate_str)) {
	    mapper.saveMState(parm);
	}
	return true;
    }

    @Override
    public List<Map<String, Object>> viewA(Map<String, Object> parm) {
	return mapper.listM(parm);
    }

    @Override
    public Map<String, Object> viewB(Map<String, Object> parm) {
	return mapper.listMState(parm);
    }

    @Override
    public Map<String, Object> viewNextB(Map<String, Object> parm) {
	return mapper.getNextB(parm);
    }

    @Override
    public Map<String, Object> viewPrevB(Map<String, Object> parm) {
	return mapper.getPrevB(parm);
    }

    @Override
    public boolean authUser(HashMap<String, Object> hashMap) {
	int cnt = mapper.getAuthUser(hashMap);
	if (cnt == 1) {
	    return true;
	} else {
	    return false;
	}
    }

}
