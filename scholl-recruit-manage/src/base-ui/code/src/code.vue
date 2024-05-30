<template>
  <div class="hy-code">
    <div class="bg">
      <!-- <div :class="'language'+language" v-html="highlightedCode" />
    </div> -->
    </div>
  </div>
</template>

<script>
import hljs from 'highlight.js'
import 'highlight.js/styles/github.css'
export default {
  props: {
    language: {
      type: String,
      default: 'html'
    },
    code: {
      type: String,
      default: ''
    }
  },

  data () {
    return {
      highlightedCode: ''
    }
  },
  watch: {
    watchEffect () {
      this.highlightedCode = hljs.highlight(this.code, { language: this.language })
    }
  }
  // setup (props) {
  //   watchEffect(() => {
  //     highlightedCode.value = hljs.highlight(props.code, {
  //       language: props.language
  //     }).value
  //   })

  //   return {
  //     highlightedCode
  //   }
  // }
}
</script>

<style lang="scss" scoped>
.bg {
  padding: 10px;
  text-align: left;
  background: #f0f0f0;
}
</style>
