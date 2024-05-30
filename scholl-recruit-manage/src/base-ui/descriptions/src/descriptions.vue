<template>
  <div class="description">
    <div>{{ title }}</div>
    <el-table class="margin-top" :title="title" :column="column" border :data="tableDatas">
      <el-table-column align="center" label="name" prop="name" />
      <el-table-column align="center" label="version" prop="description">
        <!-- <el-tag size="small">
          {{ description }}
        </el-tag> -->
      </el-table-column>
      <!-- <div v-for="(itemData, index) in tableDatas" :key="index">
        <el-table-column>
          <template #label>
            {{ itemData.name }}
          </template>
          <el-tag size="small">
            {{ itemData.description }}
          </el-tag>
        </el-table-column>
      </div> -->
    </el-table>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    title: {
      type: String,
      default: ''
    },
    column: {
      type: Number,
      default: 1
    },
    tableDatas: {
      type: Array,
      default: () => []
    }
  }
}
</script>

<style scoped>
.description {
  margin-bottom: 20px;
}
</style>
