import Vue from 'vue/dist/vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'babel-polyfill';
Vue.use(VueAxios,axios)
//当创建实例时，设置默认配置。
axios.defaults.baseURL = 'http://106.12.61.131:8081';
// axios.defaults.baseURL = 'http://localhost:8081';
axios.defaults.headers = {
    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
}

import Vuex from 'vuex'
Vue.use(Vuex)

// 引用API文件
import api from './api/index.js'
// 将API方法绑定到全局
Vue.prototype.$api = api


import {Button,Checkbox,Icon
    ,Layout,Menu,Breadcrumb,List,Avatar,Card,Form,Input,Radio,Modal,Select,Row,Col,Cascader,Tooltip,Alert,message,Tabs,Popover,Divider
} from "ant-design-vue";
import 'ant-design-vue/dist/antd.css';
import moment from 'moment';
import VueRouter from 'vue-router'
Vue.prototype.$message = message;
Vue.config.productionTip = false;

Vue.use(Button)
Vue.use(Checkbox)
Vue.use(Icon)
Vue.use(Layout);
Vue.use(Breadcrumb);
Vue.use(Menu);
Vue.use(List);
Vue.use(Avatar)
Vue.use(Card)
Vue.use(Form)
Vue.use(Input)
Vue.use(Radio)
Vue.use(Modal)
Vue.use(VueRouter)
Vue.use(Select)
Vue.use(Row)
Vue.use(Col)
Vue.use(Cascader)
Vue.use(Tooltip)
Vue.use(Alert)
Vue.use(Tabs)
Vue.use(Popover)
Vue.use(Divider)
Vue.prototype.$moment = moment
new Vue({
  render: h => h(App),
}).$mount('#app')
