import { getEmploymentList, addEmployment, updateEmployment, deleteEmployment, queryEmployment, getSingleEmploymentList } from '@/api/employment.js'

const employment = {
  state: {
    employmentList: [],
    singleEmploymentList: []
  },

  mutations: {
    SET_EMPLOYMENTLIST: (state, value) => {
      state.employmentList = value
    },
    SET_SINGLE_EMPLOYMENT_LIST:
    (state, value) => {
      state.singleEmploymentList = value
    }
  },

  actions: {
    // 获取职位列表
    getEmploymentList({ commit }, params) {
      return getEmploymentList(params).then(res => {
        commit('SET_EMPLOYMENTLIST', res.list)
        return res
      }
      )
    },
    /* 获取单个公司职位列表 */
    getSingleEmploymentList({ commit }, params) {
      return getSingleEmploymentList(params).then(res => {
        console.log(res)
        commit('SET_SINGLE_EMPLOYMENT_LIST', res.list)
        return res
      }
      )
    },
    // 添加用户
    addEmployment({ commit }, params) {
      return addEmployment(params).then(res => {})
    },
    // 修改用户
    updateEmployment({ commit }, params) {
      return updateEmployment(params)
    },
    // 删除职位
    deleteEmployment({ commit }, params) {
      return deleteEmployment(params)
    },
    // 获取用户列表
    queryEmployment({ commit }, params) {
      return queryEmployment(params).then(res => {
        commit('SET_EMPLOYMENTLIST', res.list)
        return res
      }
      )
    }
  }
}

export default employment
