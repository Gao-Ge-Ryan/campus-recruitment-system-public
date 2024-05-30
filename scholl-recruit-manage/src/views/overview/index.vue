<template>
  <div class="overview">
    <Card title="关于">
      <div class="c-left">
        在线校园招聘系统 是基于 Vue、Vuex、VueRouter、vue-cli、 ElementUI等技术。
      </div>
    </Card>
    <Card title="技术栈" class="card">
      <h2>后端技术</h2>
      <TextLink :text-arrs="backend" />
      <h2>前端技术</h2>
      <TextLink :text-arrs="technologyStacks" />
    </Card>
    <!-- <Descriptions title="生产环境依赖" :column="2" :table-datas="dependencies" /> -->
    <Card title="项目规范">
      <descriptions title="文件命名规范" :column="1" :table-datas="[{ name: '文件夹', description: '统一小写, 多个单词使用-分割' },{ name: '文件(.ts .vue .json .d.ts)', description: '统一小写, 多个单词使用-分割' }]" />
    </Card>

    <Card title="Git提交规范">
      <Descriptions :column="1" :table-datas="[{ name: 'add 操作', description: 'git add ' },{ name: 'commit 操作', description: 'git commit ' },{ name: 'pull 操作', description: 'git pull ' },{ name: 'push 操作', description: 'git push ' }]" />
    </Card>
  </div>
</template>

<script>
import Descriptions from '@/base-ui/descriptions'
import TextLink from '@/base-ui/text-link'
import Card from '@/base-ui/card'

export default {
  components: {
    Descriptions,
    Card,
    TextLink
  },
  data () {
    return {
      backend: [
        {
          title: '开发工具',
          desp: 'IDEA'
        },

        {
          title: '编程语言',
          desp: 'Java'
        },

        {
          title: '后端框架',
          desp: 'Spring Boot'
        },

        {
          title: '持久层框架',
          desp: 'Mybatis'
        },

        {
          title: '数据库',
          desp: 'MySql'
        },

        {
          title: 'Id工具',
          desp: 'IdWorker'
        },

        {
          title: '身份验证',
          desp: 'Spring Security'
        },

        {
          title: '临时存储',
          desp: 'Redis'
        }
      ],
      technologyStacks: [
        {
          title: '开发工具',
          desp: 'Visual Studio Code'
        },

        {
          title: '编程语言',
          desp: 'JavaScript'
        },

        {
          title: '构建工具',
          desp: 'Webpack'
        },

        {
          title: '前端框架',
          desp: 'Vue'
        },

        {
          title: '路由工具',
          desp: 'Vue Router'
        },

        {
          title: '状态管理',
          desp: 'Vuex'
        },

        {
          title: 'UI 框架',
          desp: 'Element UI'
        },

        {
          title: '工具库',
          desp: ' lodash-js'
        },

        {
          title: 'CSS 预编译',
          desp: 'Sass'
        },

        {
          title: 'HTTP 工具',
          desp: 'Axios'
        },

        {
          title: '代码规范',
          desp: 'Prettier + ESLint'
        }
      ],
      dependencies: [
        {
          name: 'vue',
          description: '^2.0.5'
        },
        {
          name: 'vue-router',
          description: '^4.0.6'
        },
        {
          name: 'vuex',
          description: '^4.0.0'
        },
        {
          name: 'axios',
          description: '^0.21.1'
        },
        {
          name: 'element-ui',
          description: '^2.1.15'
        },

        {
          name: 'scss',
          description: '^0.2.4'
        }
      ],
      devDependencies: [
        {
          name: 'webpack',
          description: '5'
        },

        {
          name: 'eslint',
          description: '^7.25.0'
        },
        {
          name: 'prettier',
          description: '^2.2.1'
        },
        {
          name: 'stylelint',
          description: '^13.13.0'
        },
        {
          name: 'lint-staged',
          description: '^10.5.4'
        },
        {
          name: 'rimraf',
          description: '^3.0.2'
        },
        {
          name: '@commitlint/cli',
          description: '^12.1.1'
        },
        {
          name: '@commitlint/config-conventional',
          description: '^12.1.1'
        },
        {
          name: '@types/node',
          description: '^14.14.41'
        },
        {
          name: '@typescript-eslint/eslint-plugin',
          description: '^4.22.0'
        },
        {
          name: '@typescript-eslint/parser',
          description: '^4.22.0'
        },
        {
          name: '@vitejs/plugin-vue',
          description: '^1.2.2'
        },
        {
          name: '@vue/compiler-sfc',
          description: '^3.0.5'
        },
        {
          name: 'commitizen',
          description: '^4.2.3'
        },
        {
          name: 'cz-conventional-changelog',
          description: '3.3.0'
        },
        {
          name: 'eslint-config-airbnb-base',
          description: '^14.2.1'
        },
        {
          name: 'eslint-config-prettier',
          description: '^8.3.0'
        },
        {
          name: 'eslint-plugin-import',
          description: '^2.22.1'
        },
        {
          name: 'eslint-plugin-prettier',
          description: '^3.4.0'
        },
        {
          name: 'eslint-plugin-vue',
          description: '^7.9.0'
        },
        {
          name: 'stylelint-config-prettier',
          description: '^8.0.2'
        },
        {
          name: 'stylelint-config-standard',
          description: '^22.0.0'
        },
        {
          name: 'stylelint-order',
          description: '^4.1.0'
        },
        {
          name: 'vite-plugin-mock',
          description: '^2.5.0'
        },
        {
          name: 'vue-tsc',
          description: '^0.0.8'
        }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
.overview {
  .card {
  }
  .c-left {
    text-align: left;
  }

  .el-card {
    margin-bottom: 20px;
    // ::v-deep 重写 element-plus 样式
    &:deep(.el-card__header span) {
      // ::v-deep .el-card__header span {
      font-weight: 700;
    }
  }

  .description {
    // ::v-deep 重写 element-plus 样式
    &:deep(.el-descriptions__title) {
      // ::v-deep .el-descriptions__title {
      font-weight: 400;
    }
  }
}
</style>
