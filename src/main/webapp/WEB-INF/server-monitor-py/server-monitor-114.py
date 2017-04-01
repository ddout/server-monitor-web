#!/usr/bin/python
# -*- coding:utf-8 -*-

import HttpApi

mon_data = {
    'rpwd' : '64535asdasdasdasd437539',
    'name' : 'server-114',
    'ip' : '192.168.0.114',
    'note' : '主要Oracle服务器114',
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
#ps -ef|grep memcache
mon_data['mstate_str'] += '########## memcache ##########\t\n'
mon_data['mstate_str'] += HttpApi.execComm('ps -ef|grep oracle')
mon_data['mstate_str'] += '\t\n\t\n'
###########

print mon_data['mstate_str']


print HttpApi.postMonitor(mon_data)