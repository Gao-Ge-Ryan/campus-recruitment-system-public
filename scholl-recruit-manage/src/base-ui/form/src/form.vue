<template>
  <div class="hy-form">
    <div class="header">
      <slot name="header"></slot>
    </div>
    <el-form :label-width="labelWidth">
      <el-row>
        <template v-for="(item, index) in formItems" :key="index">
          <el-col v-bind="colLayout">
            <el-form-item :label="item.label" :rules="item.rules" class="form-item" :style="itemStyle" v-if="!item.isHidden">
              <template v-if="item.type === 'input' || item.type === 'password'">
                <el-input v-model="formData[`${item.field}`]" :placeholder="item.placeHolder" :show-password="item.type === 'password'" />
              </template>
              <template v-else-if="item.type === 'select'">
                <el-select v-model="formData[`${item.field}`]" :placeholder="item.placeHolder" style="width: 100%">
                  <el-option v-for="option in item.options" :key="option.value" :value="option.value">{{ option.label }}</el-option>
                </el-select>
              </template>
              <template v-else-if="item.type === 'datepicker'">
                <el-date-picker v-model="formData[`${item.field}`]" v-bind="item.otherOption" style="width: 100%">
                </el-date-picker>
              </template>
            </el-form-item>
          </el-col>
        </template>
      </el-row>
    </el-form>
    <div class="footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType, ref, watch } from 'vue'
import { IFormItem } from '../types'

export default defineComponent({
  props: {
    modelValue: {
      type: Object,
    },
    labelWidth: {
      type: String,
      default: () => '80px',
    },
    formItems: {
      type: Array as PropType<IFormItem[]>,
      default: () => [],
    },
    itemStyle: {
      type: Object,
      default: () => ({ padding: '10px 40px' }),
    },
    colLayout: {
      type: Object,
      default: () => ({
        xl: 6, // ≥1920px
        lg: 8, // ≥1200px
        md: 12, // ≥992px
        sm: 24, // ≥768px
        xs: 24, // <768px
      }),
    },
  },
  emit: ['update:modelValue'],
  setup(props, { emit }) {
    const formData = ref({ ...props.modelValue })

    watch(
      formData,
      (newValue) => {
        emit('update:modelValue', newValue)
      },
      { deep: true }
    )

    return {
      formData,
    }
  },
})
</script>

<style scoped lang="scss">
.hy-form {
  padding: 20px 20px 0;
}
</style>
