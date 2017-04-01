package com.cdhy.ei.service.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAccessService {

    /**
     * 服务注册
     * @param parm
     * @return
     */
    public boolean saveRegist(Map<String, Object> parm);

    public List<Map<String, Object>> viewA(Map<String, Object> parm);

    public Map<String, Object> viewB(Map<String, Object> parm);

    public Map<String, Object> viewNextB(Map<String, Object> parm);

    public Map<String, Object> viewPrevB(Map<String, Object> parm);

    public boolean authUser(HashMap<String, Object> hashMap);

}
