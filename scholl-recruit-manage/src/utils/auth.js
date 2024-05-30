// import Cookies from 'js-cookie'
import { Base64 } from 'js-base64'
const TokenKey = 'recruit-Token'
const UserInfo = 'userInfo'

export function getToken() {
  return localStorage.getItem(TokenKey)
  // return Cookies.get(TokenKey)
}

export function setToken(token) {
  localStorage.setItem(TokenKey, token)

  const token1 = token.split('.')
  const userInfo = Base64.decode(token1[1].replace(/-/g, '+').replace(/_/g, '/'))
  localStorage.setItem(UserInfo, userInfo)
}

export function removeToken() {
  localStorage.removeItem(TokenKey)
  localStorage.removeItem(UserInfo)
  // return Cookies.remove(TokenKey)
}
