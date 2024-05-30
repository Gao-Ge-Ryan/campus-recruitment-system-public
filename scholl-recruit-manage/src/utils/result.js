import { get } from 'lodash-es'
export function getResult(res) {
  const flag = get(res.data, 'flag')
  if (flag) {
    const data = get(res, 'data.data')
    return data
  } else {
    const message = get(res, 'data.msg')
    const data = get(res, 'data.data')
    const error = new Error(message + '  ' + (data || ''))
    throw error
  }
}
