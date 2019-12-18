import Vue from 'vue'
import App from './App.vue'
import {Button,Checkbox,Icon
    ,Layout,Menu,Breadcrumb
} from "ant-design-vue";
import 'ant-design-vue/dist/antd.css';

Vue.config.productionTip = false;

Vue.use(Button)
Vue.use(Checkbox)
Vue.use(Icon)
Vue.use(Layout);
Vue.use(Breadcrumb);
Vue.use(Menu);

new Vue({
  render: h => h(App),
}).$mount('#app')
