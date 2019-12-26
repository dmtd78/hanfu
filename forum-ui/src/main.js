import Vue from 'vue/dist/vue'
import App from './App.vue'
import {Button,Checkbox,Icon
    ,Layout,Menu,Breadcrumb,List,Avatar,Card,Form,Input,Radio,Modal
} from "ant-design-vue";
import 'ant-design-vue/dist/antd.css';
import moment from 'moment';
import VueRouter from 'vue-router'

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
Vue.prototype.$moment = moment
new Vue({
  render: h => h(App),
}).$mount('#app')
