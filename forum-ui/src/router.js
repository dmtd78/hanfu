import VueRouter from 'vue-router'
import Home from './components/Home'
import Demo from './components/Demo'
import ContactUs from "./components/ContactUs";
import AboutUs from "./components/AboutUs";

const router = new VueRouter({
    mode: 'history',
    base: __dirname,
    routes: [
        { path: '/home', name: 'home', component: Home },
        { path: '/demo', name: 'demo', component: Demo },
        { path: '/contactUs', name: 'contactUs', component: ContactUs },
        { path: '/aboutUs', name: 'aboutUs', component: AboutUs },
        { path: '/', redirect: '/home' }
    ]
})
export default router