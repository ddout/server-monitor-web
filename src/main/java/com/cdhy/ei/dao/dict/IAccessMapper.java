package com.cdhy.ei.dao.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IAccessMapper {

    Map<String, Object> getM(Map<String, Object> parm);

    void saveM(Map<String, Object> parm);

    void updateLastTime(Map<String, Object> parm);

    void saveMState(Map<String, Object> parm);

    List<Map<String, Object>> listM(Map<String, Object> parm);

    Map<String, Object> listMState(Map<String, Object> parm);

    Map<String, Object> getNextB(Map<String, Object> parm);

    Map<String, Object> getPrevB(Map<String, Object> parm);

    void deleteClear();

    int getAuthUser(HashMap<String, Object> hashMap);

    List<Map<String, Object>> listLastStates();

    List<Map<String, Object>> listSendUsers();

    void saveLog();

    Map<String, Object> getISLog(Map<String, Object> map);

}
