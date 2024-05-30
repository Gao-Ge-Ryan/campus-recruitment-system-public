import { getEnum } from '@/api/enum'

const enumList = {

  state: {
    roleList: [],
    companyNatureEnum: [],
    companyStatusEnum: [],
    companyCategoryEnum: [],
    employmentEducationEnum: [],
    employmentReplyStatus: [],
    employmentUserStatus: [],
    feedBackStatusEnum: [],
    feedBackTypeEnum: []
  },

  mutations: {
    SET_USERROLELIST: (state, value) => {
      state.roleList = value
    },
    SET_COMPANYNATURE: (state, value) => {
      state.companyNatureEnum = value
    },
    SET_COMPANYSTATUS: (state, value) => {
      state.companyStatusEnum = value
    },
    SET_COMPANYCATEGORY: (state, value) => {
      state.companyCategoryEnum = value
    },
    SET_EMPLOYMENTEDUCATION: (state, value) => {
      state.employmentEducationEnum = value
    },
    SET_EMPLOYMENTREPLYSTATUS: (state, value) => {
      state.employmentReplyStatus = value
    },
    SET_EMPLOYMENTUSERSTATUS: (state, value) => {
      state.employmentUserStatus = value
    },
    SET_FEEDBACKSTATUS: (state, value) => {
      state.feedBackStatusEnum = value
    },
    SET_FEEDBACKTYPE: (state, value) => {
      state.feedBackTypeEnum = value
    }
  },

  actions: {
    // 获取用户列表
    getUserRoleList({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_USERROLELIST', res)
      }
      )
    },
    getCompanyNatureEnum({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_COMPANYNATURE', res)
      }
      )
    },
    getCompanyStatusEnum({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_COMPANYSTATUS', res)
      }
      )
    },
    getCompanyCategoryEnum({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_COMPANYCATEGORY', res)
      }
      )
    },
    getEmploymentEducationEnum({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_EMPLOYMENTEDUCATION', res)
      }
      )
    },
    getEmploymentReplyStatus({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_EMPLOYMENTREPLYSTATUS', res)
      }
      )
    },
    getEmploymentUserStatus({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_EMPLOYMENTUSERSTATUS', res)
      }
      )
    },
    getFeedBackStatusEnum({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_FEEDBACKSTATUS', res)
      }
      )
    },
    getFeedBackTypeEnum({ commit }, params) {
      return getEnum(params).then(res => {
        commit('SET_FEEDBACKTYPE', res)
      }
      )
    }
  }
}

export default enumList

