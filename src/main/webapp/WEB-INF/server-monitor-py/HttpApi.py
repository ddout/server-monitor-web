#!/usr/bin/python
# -*- coding:utf-8 -*-

import urllib
import httplib
import json
import commands

def get(url) :
    try :
        response = urllib.urlopen(url)
        result = response.read()
        returnStr = json.dumps(result)
    except :
        return 'error'
    else :
        return returnStr



def postJSON(host,url,parm) :
    try :
        test_data = json.dumps(parm)
        requrl = url
        headerdata = {"Content-Type": "application/json"}
        conn = httplib.HTTPConnection(host)
        conn.request(method="POST", url=requrl, body=test_data, headers=headerdata)
        response = conn.getresponse()
        res = response.read()
        resJSON = json.loads(res)
        returnResStr = resJSON['msg']
        returnRes = ''
        if returnResStr != '' :
            returnRes = 'true'
        else :
            returnRes = 'false'
    except :
        return 'error'
    else:
        return returnRes

def postMonitor(parm) :
    try :
        body_value = urllib.urlencode(parm)
        headerdata = {"Content-Type": "application/x-www-form-urlencoded;charset=utf-8"}
        conn = httplib.HTTPConnection('test.hydzfp.com:55555')
        conn.request(method="POST", url='/access/regist.do', body=body_value, headers=headerdata)
        response = conn.getresponse()
        res = response.read()
    except :
        return 'error'
    else:
        return res


def execComm(_cmd) :
    try :
        (status, output) = commands.getstatusoutput(_cmd)
    except :
        return 'error'
    else:
        return output


