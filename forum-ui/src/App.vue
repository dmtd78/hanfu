<template>
  <div id="app">
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>
  </div>
</template>

<script>
  import router from './router'
export default {
  name: 'app',
  router,
}
</script>
<style>
#app {}
.logo{width: 200px;height: 200px;}
</style>
