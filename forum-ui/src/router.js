import Vue from 'vue'
import VueRouter from 'vue-router'

import Demo from './components/Demo'
import ContactUs from "./components/ContactUs";
import AboutUs from "./components/AboutUs";
import PhotoForum from "./components/PhotoForum";
import Register from "./components/Register";
import Login from "./components/Login";
import LittleWhite from "./components/LittleWhite";
import PersonalInfo from "./components/PersonalInfo";
import BussinessHome from './components/bussiness/Home'
import Forum from './components/Forum'

import Portal from './components/Portal'
import Home from "./components/Home";

Vue.use(VueRouter)

const router = new VueRouter({
    // mode: 'history',
    base: __dirname,
    routes: [
        { path: '/demo', name: 'demo', component: Demo },
        { path: '/aboutUs', name: 'aboutUs', component: AboutUs },
        { path: '/photoForum', name: 'photoForum', component: PhotoForum },
        { path: '/register', name: 'register', component: Register },
        { path: '/littleWhite', name: 'littleWhite', component: LittleWhite },
        { path: '/bussinessHome', name: '/bussiness/home', component: BussinessHome },
        { path: '/personalInfo', name: 'personalInfo', component: PersonalInfo },
        { 
            path: '/', component: Portal,
            children: [
                { path: '', redirect: 'home' },
                { path: 'home/:aid', name: 'home', component: Forum },
                { path: 'home', name: 'home', component: Home },
                { path: 'login', name: 'login', component: Login },
                { path: '/contactUs', name: 'contactUs', component: ContactUs },
            ]
        },
    ]
})
export default router