#!/usr/bin/python
# -*- coding:utf-8 -*-

import HttpApi

mon_data = {
    'rpwd' : '64535asdasdasdasd437539',
    'name' : 'server-201',
    'ip' : '192.168.0.201',
    'note' : '应用服务器201',
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
#/etc/init.d/deploy-app-tomcats deploy-app-tomcats status
mon_data['mstate_str'] += 'deploy-app-tomcats status -h\t\n'
mon_data['mstate_str'] += HttpApi.execComm('/etc/init.d/deploy-app-tomcats status')
mon_data['mstate_str'] += '\t\n\t\n'
########
#
mon_data['mstate_str'] += '########## /ei_buss_interface/tomcat_23001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:23001','/ei_buss_interface/InvoiceHandle/queryStock.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
########
#
mon_data['mstate_str'] += '########## /ei_buss_interface/tomcat_23002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:23002','/ei_buss_interface/InvoiceHandle/queryStock.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########


#
mon_data['mstate_str'] += '########## /ei_data_process/tomcat_24001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:24001','/ei_data_process/data/process/einvoicedata.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
########
#
mon_data['mstate_str'] += '########## /ei_data_process/tomcat_24002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:24002','/ei_data_process/data/process/einvoicedata.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
###########


#
mon_data['mstate_str'] += '########## /ei_sk_interface/tomcat_25001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:25001','/ei_sk_interface/skinterface/queryInfo.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
########
#
mon_data['mstate_str'] += '########## /ei_sk_interface/tomcat_25002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:25002','/ei_sk_interface/skinterface/queryInfo.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########


#
mon_data['mstate_str'] += '########## /ei_pdf/tomcat_26001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:26001','/ei_pdf/pdf/getPdfImgByte.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_pdf/tomcat_26002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:26002','/ei_pdf/pdf/getPdfImgByte.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_pdf/tomcat_26003 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:26003','/ei_pdf/pdf/getPdfImgByte.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_pdf/tomcat_26004 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:26004','/ei_pdf/pdf/getPdfImgByte.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_pdf/tomcat_26005 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:26005','/ei_pdf/pdf/getPdfImgByte.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_pdf/tomcat_26006 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:26006','/ei_pdf/pdf/getPdfImgByte.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########


#
mon_data['mstate_str'] += '########## /ei_sk_data/tomcat_28001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:28001','/ei_sk_data/skdata/queryItemInfo.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_sk_data/tomcat_28002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:28002','/ei_sk_data/skdata/queryItemInfo.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########


#
mon_data['mstate_str'] += '########## ei_site_buss /site_app/tomcat_29001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:29001','/ei_site_buss/IHandleConf/getByIdTax.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## ei_site_buss /site_app/tomcat_29002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:29002','/ei_site_buss/IHandleConf/getByIdTax.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########


#
mon_data['mstate_str'] += '########## open_site /site_app/tomcat_29001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:29001','/open_site/IBaseAuth/handleInterface.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## open_site /site_app/tomcat_29002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:29002','/open_site/IBaseAuth/handleInterface.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########



#
mon_data['mstate_str'] += '########## /ei_rpt/tomcat_30001 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:30001','/ei_rpt/invoice/rpt1.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########
#
mon_data['mstate_str'] += '########## /ei_rpt/tomcat_30002 ##########\t\n'
mon_data['mstate_str'] += HttpApi.postJSON('127.0.0.1:30002','/ei_rpt/invoice/rpt1.do',{'a':'test'})
mon_data['mstate_str'] += '\t\n\t\n'
###########

print mon_data['mstate_str']


print HttpApi.postMonitor(mon_data)