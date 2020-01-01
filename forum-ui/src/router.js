import VueRouter from 'vue-router'
import Home from './components/Home'
import Demo from './components/Demo'
import ContactUs from "./components/ContactUs";
import AboutUs from "./components/AboutUs";
import PhotoForum from "./components/PhotoForum";
import Register from "./components/Register";
import LittleWhite from "./components/LittleWhite";
import PersonalInfo from "./components/PersonalInfo";

const router = new VueRouter({
    mode: 'history',
    base: __dirname,
    routes: [
        { path: '/home', name: 'home', component: Home },
        { path: '/demo', name: 'demo', component: Demo },
        { path: '/contactUs', name: 'contactUs', component: ContactUs },
        { path: '/aboutUs', name: 'aboutUs', component: AboutUs },
        { path: '/photoForum', name: 'photoForum', component: PhotoForum },
        { path: '/register', name: 'register', component: Register },
        { path: '/littleWhite', name: 'littleWhite', component: LittleWhite },
        { path: '/personalInfo', name: 'personalInfo', component: PersonalInfo },
        { path: '/', redirect: '/home' }
    ]
})
export default router