<template>
  <div id="app">
      <Home msg="欢迎光临大美汉服网站！"></Home>
    <img alt="Vue logo" class="logo" src="assets/logo.png">
    <HelloWorld msg="Welcome to beautiful Han-Chinese-clothing website."/>
      <Advertisement></Advertisement>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Home from './components/Home.vue'
import Advertisement from "./components/Advertisement";

export default {
  name: 'app',
  components: {
    HelloWorld,
    Home,Advertisement
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
    .logo{width: 200px;height: 200px;}
</style>
