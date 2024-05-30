import { request } from '@/utils/request'
import { getResult } from '@/utils/result'

/**
* 获取用户列表
*/
export function getEmploymentList(params) {
  return request({
    url: `employment/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: {}
  }).then(res => getResult(res))
}
/**
* 获取用户列表
*/
export function getSingleEmploymentList(params) {
  return request({
    url: `employment/username/${params.pageNum}/${params.pageSize}`,
    method: 'get',
    params: params.params
  }).then(res => getResult(res))
}

/**
* 新增用户信息
*/
export function addEmployment(params) {
  return request({
    url: `employment`,
    method: 'post',
    data: params
  }).then(res => getResult(res))
}

/**
* 修改用户信息
*/
export function updateEmployment(params) {
  return request({
    url: `employment`,
    method: 'put',
    data: params
  }).then(res => getResult(res))
}

/**
* 修改职位
*/
export function deleteEmployment(id) {
  return request({
    url: `employment/${id}`,
    method: 'delete'
  }).then(res => getResult(res))
}

/**
* 按条件信息
*/
export function queryEmployment(params) {
  return request({
    url: `employment/${params.pageNum}/${params.pageSize}`,
    method: 'post',
    data: params.condition
  }).then(res => getResult(res))
}

