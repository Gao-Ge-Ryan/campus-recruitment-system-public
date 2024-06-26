import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

/**
* 获取用户列表
*/
export function getUserList(params) {
  return request({
    url: `user/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: {}
  }).then(res => getResult(res))
}

/**
* 新增用户信息
*/
export function addUser(params) {
  return request({
    url: `user`,
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

/**
* 修改用户信息
*/
export function updateUser(params) {
  return request({
    url: `user`,
    method: 'put',
    data: params
  }).then(res => getResult(res))
}

/**
* 修改删除信息
*/
export function deleteUser(id) {
  return request({
    url: `user/${id}`,
    method: 'delete'
  }).then(res => getResult(res))
}

/**
* 按条件信息
*/
export function queryUser(params) {
  return request({
    url: `user/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: params.condition
  }).then(res => getResult(res))
}

export function getInfo() {
  return request({
    url: 'user/username',
    method: 'get'
  }).then(res => getResult(res))
}

/* 获取验证码 */
export function getAuthCode(params) {
  return request({
    url: '/email/getAuthCode',
    method: 'get',
    params: params
  })
}

export function updatePassword(params) {
  return request({
    url: 'user/password',
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

