import Vue from 'vue'
import router from './router'
import store from './store/index.js'
import api from './api/index.js'
import App from './App.vue'

import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import {Button,Checkbox,Icon,Layout,Menu,Breadcrumb,List,Avatar,Card,Form,Input,Radio,Modal,Select,Row,Col,Cascader,Popconfirm,
    Tooltip,Alert,message,Tabs,Popover,Divider,Collapse,Comment,notification,Calendar,Steps,Dropdown,Tag} from "ant-design-vue";
import 'ant-design-vue/dist/antd.css';
import moment from 'moment';

Vue.use(Button)
Vue.use(Tag)
Vue.use(Dropdown)
Vue.use(Checkbox)
Vue.use(Icon)
Vue.use(Layout);
Vue.use(Steps);
Vue.use(Breadcrumb);
Vue.use(Menu);
Vue.use(List);
Vue.use(Avatar)
Vue.use(Card)
Vue.use(Form)
Vue.use(Input)
Vue.use(Radio)
Vue.use(Modal)
Vue.use(Select)
Vue.use(Row)
Vue.use(Col)
Vue.use(Cascader)
Vue.use(Tooltip)
Vue.use(Alert)
Vue.use(Tabs)
Vue.use(Popover)
Vue.use(Divider)
Vue.use(Comment)
Vue.use(Collapse)
Vue.use(Calendar)
Vue.use(VueQuillEditor)
Vue.use(Popconfirm)

Vue.prototype.$api = api
Vue.prototype.$message = message;
Vue.prototype.$notification = notification;
Vue.prototype.$moment = moment

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
