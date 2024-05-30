import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

/**
* 获取用户列表
*/
export function getEmploymentUserList(params) {
  console.log(params.params)
  return request({
    url: `employmentUser/company/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: params.params
  }).then(res => getResult(res))
}

/**
* 新增用户信息
*/
export function addEmploymentUser(params) {
  return request({
    url: `employmentUser`,
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

/**
* 修改用户信息
*/
export function updateEmploymentUser(params) {
  return request({
    url: `employmentUser`,
    method: 'put',
    data: params
  }).then(res => getResult(res))
}

/**
* 修改删除信息
*/
export function deleteEmploymentUser(id) {
  return request({
    url: `employmentUser/${id}`,
    method: 'delete'
  }).then(res => getResult(res))
}

/**
* 按条件信息
*/
export function queryEmploymentUser(params) {
  return request({
    url: `employmentUser/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: params.condition
  }).then(res => getResult(res))
}

