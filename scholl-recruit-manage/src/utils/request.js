import axios from 'axios'
// import store from '../store'
// import { getToken } from '@/utils/auth'

export function request (config) {
  // 创建axios的实例
  const instance = axios.create({
    // baseURL: 'http://192.168.0.103:3030',
    baseURL: 'http://localhost:3030',
    timeout: 10000
  })
  // axios.interceptors  全局拦截
  // 请求拦截
  instance.interceptors.request.use(config => {
    if (localStorage.getItem('recruit-Token')) {
      config.headers['Authorization'] = localStorage.getItem('recruit-Token') // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    // Promise.reject(error)
    console.log(error)
  })
  // 响应拦截
  instance.interceptors.response.use(res => {
    return res
  }, err => {
    console.log(err + 123213213)
  })
  // 3.发送真正的网络请求
  return instance(config)
}
