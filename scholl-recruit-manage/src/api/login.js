import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

export function login(params) {
  return request({
    url: '/user/login',
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

/* 查询企业信息 */
export function getCompanyInfo(params) {
  return request({
    url: '/company/user/name',
    method: 'get'
  }).then(res => getResult(res))
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
