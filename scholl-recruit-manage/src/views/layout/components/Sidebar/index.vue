<template>
  <el-scrollbar wrap-class="scrollbar-wrapper">
    <el-menu :show-timeout="200" :default-active="$route.path" :collapse="isCollapse" :background-color="variables.menuBg" :text-color="variables.menuText" :active-text-color="variables.menuActiveText" mode="vertical">
      <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" />
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from 'vuex'
import variables from '@/styles/variables.scss'
import SidebarItem from './SidebarItem'

export default {
  components: { SidebarItem },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes () {
      let list
      if (JSON.parse(localStorage.getItem('userInfo')).role[0].authority === 'Admin') {
        list = this.$router.options.routes.filter(item => {
          return (item.meta.role && item.meta.role === 'admin') || (item.meta.role && item.meta.role === 'user')
        })
      }
      if (JSON.parse(localStorage.getItem('userInfo')).role[0].authority === 'Enterprise_User') {
        list = this.$router.options.routes.filter(item => {
          return (item.meta.role && item.meta.role === 'company') || (item.meta.role && item.meta.role === 'user')
        })
      }
      // return this.$router.options.routes
      return list
    },
    variables () {
      return variables
    },
    isCollapse () {
      return !this.sidebar.opened
    }
  }
}
</script>
