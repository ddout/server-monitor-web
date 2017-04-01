#!/usr/bin/python
# -*- coding:utf-8 -*-

import HttpApi

mon_data = {
    'rpwd' : '64535asdasdasdasd437539',
    'name' : 'server-142',
    'ip' : '192.168.0.102',
    'note' : '前置入口终端142',
    'mstate_str' : ''
}
#date
mon_data['mstate_str'] += '########## server date ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('date')
mon_data['mstate_str'] += '\t\n\t\n'


#df -h
mon_data['mstate_str'] += '########## df -h ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('df -h')
mon_data['mstate_str'] += '\t\n\t\n'
#free -h
mon_data['mstate_str'] += '########## free -h ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('free -h')
mon_data['mstate_str'] += '\t\n\t\n'
###########
#james
mon_data['mstate_str'] += '########## james ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('ps -ef|grep james')
mon_data['mstate_str'] += '\t\n\t\n'
#activemq
mon_data['mstate_str'] += '########## activemq ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('ps -ef|grep activemq')
mon_data['mstate_str'] += '\t\n\t\n'

#/etc/init.d/deploy-app-tomcats deploy-app-tomcats status
mon_data['mstate_str'] += 'deploy-app-tomcats status -h\t\n'
mon_data['mstate_str'] += HttpApi.execComm('/etc/init.d/deploy-app-tomcats status')
mon_data['mstate_str'] += '\t\n\t\n'
#ps -ef|grep memcache
mon_data['mstate_str'] += '########## memcache ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('ps -ef|grep memcache')
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## webSiteStatus ##########\t\n'
if 3 == HttpApi.get('http://web.hydzfp.com/Modular/login/login.html').index('DOCTYPE html'):
    mon_data['mstate_str'] += 'true'
else :
    mon_data['mstate_str'] += 'false'
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /web_site/tomcat_20001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:20001','/ei_access/access/login.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /web_site/tomcat_20002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:20002','/ei_access/access/login.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'

###########
#
mon_data['mstate_str'] += '########## /open_access/tomcat_21001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:21001','/open_access/access/token.open',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_access/tomcat_21002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:21002','/open_access/access/token.open',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_access/tomcat_21003 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:21003','/open_access/access/token.open',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_access/tomcat_21004 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:21004','/open_access/access/token.open',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_access/tomcat_21005 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:21005','/open_access/access/token.open',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_access/tomcat_21006 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:21006','/open_access/access/token.open',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'

###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22001','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22002','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22003 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22003','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22004 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22001','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22005 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22005','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '##########/open_proxy/ tomcat_22006 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22006','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22007 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22007','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /open_proxy/tomcat_22008 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:22008','/open_proxy/open/proxy.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
print mon_data['mstate_str']


print HttpApi.postMonitor(mon_data)