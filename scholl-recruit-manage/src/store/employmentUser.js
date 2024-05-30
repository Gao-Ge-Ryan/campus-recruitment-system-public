import { getEmploymentUserList, addEmploymentUser, updateEmploymentUser, deleteEmploymentUser, queryEmploymentUser } from '@/api/employmentUser'

const employmentUser = {
  state: {
    employmentUserList: []
  },

  mutations: {
    SET_EMPLOYMENYUSERLIST: (state, value) => {
      state.employmentUserList = value
    }
  },

  actions: {
    // 获取用户列表
    getEmploymentUserList({ commit }, params) {
      return getEmploymentUserList(params).then(res => {
        commit('SET_EMPLOYMENYUSERLIST', res.list)
        return res
      }
      )
    },
    // 添加用户
    addEmploymentUser({ commit }, params) {
      return addEmploymentUser(params).then(res => {})
    },
    // 修改用户
    updateEmploymentUser({ commit }, params) {
      return updateEmploymentUser(params)
    },
    // 删除用户
    deleteEmploymentUser({ commit }, params) {
      return deleteEmploymentUser(params)
    },
    // 获取用户列表
    queryEmploymentUser({ commit }, params) {
      return queryEmploymentUser(params).then(res => {
        commit('SET_EMPLOYMENYUSERLIST', res.list)
        return res
      }
      )
    }
  }
}

export default employmentUser
