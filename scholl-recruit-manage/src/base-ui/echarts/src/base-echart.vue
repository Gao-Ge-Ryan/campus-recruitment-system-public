<template>
  <div class="base-echart">
    <div class="echart" ref="echartDivRef" :style="{ width: width, height: height }"></div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, defineProps, withDefaults, watchEffect } from 'vue'
import { useEcharts } from '../hooks/useEcharts'
import { EChartsOption } from 'echarts'

const props = withDefaults(
  defineProps<{
    options: EChartsOption
    width?: string
    height?: string
  }>(),
  {
    options: () => ({}),
    width: '100%',
    height: '350px'
  }
)

const echartDivRef = ref<HTMLElement>()
onMounted(() => {
  const [setOptions] = useEcharts(echartDivRef.value!)
  watchEffect(() => {
    setOptions(props.options)
  })
})
</script>

<style scoped>
.echart {
  background-color: #fff;
}
</style>
