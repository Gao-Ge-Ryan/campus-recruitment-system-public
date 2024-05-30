import { getCompanyList, addCompany, updateCompany, deleteCompany, queryCompany } from '@/api/company'

const company = {
  state: {
    companyList: []
  },

  mutations: {
    SET_COMPANYLIST: (state, value) => {
      state.companyList = value
    }
  },

  actions: {
    // 获取用户列表
    getCompanyList({ commit }, params) {
      return getCompanyList(params).then(res => {
        commit('SET_COMPANYLIST', res.list)
        return res
      }
      )
    },
    // 添加用户
    addCompany({ commit }, params) {
      return addCompany(params).then(res => {})
    },
    // 修改用户
    updateCompany({ commit }, params) {
      return updateCompany(params)
    },
    // 删除用户
    deleteCompany({ commit }, params) {
      return deleteCompany(params)
    },
    // 获取用户列表
    queryCompany({ commit }, params) {
      return queryCompany(params).then(res => {
        commit('SET_COMPANYLIST', res.list)
        return res
      }
      )
    }
  }
}

export default company
