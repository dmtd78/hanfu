<template>
    <a-layout class="layout">
        <a-layout-header class="header">
            <a-menu v-if="isLogin" class="user" theme="dark" mode="horizontal" :selectable="false">
                <a-menu-item key="user">
                    <a-dropdown>
                        <span class="username">{{username}}</span>
                        <a-menu slot="overlay">
                            <a-menu-item key="1">个人中心</a-menu-item>
                            <a-menu-item key="2" @click="logout">退出</a-menu-item>
                        </a-menu>
                    </a-dropdown>
                </a-menu-item>
            </a-menu>
            <div class="logo"><img src="../assets/logo-hanfu.png" height="50" width="120"/></div>
            <a-menu class="nav" theme="dark" mode="horizontal" v-model="selectedKeys" @select="navPortal">
                <a-menu-item key="home">闲聊</a-menu-item>
                <a-menu-item key="photoForum">图文赏析</a-menu-item>
                <a-menu-item key="littleWhite">小白</a-menu-item>
                <a-menu-item key="bussinessHome">汉服推荐</a-menu-item>
                <a-menu-item key="aboutUs">关于我们</a-menu-item>
                <a-menu-item key="contactUs">联系我们</a-menu-item>
                <a-menu-item key="login" v-if="!isLogin">注册/登录</a-menu-item>
            </a-menu>
        </a-layout-header>
        <a-layout class="center">
            <a-layout-content class="main">
                <router-view></router-view>
            </a-layout-content>
            <a-layout-sider width="300"
                            :style="{background: '#fff',padding:'84px 1px 0px 1px',overflow: 'auto', right: 0 }">
                <InformationCard></InformationCard>
            </a-layout-sider>
        </a-layout>
        <a-layout-footer class="footer">
            大美汉服 Design ©2019 Created by Dmtd
        </a-layout-footer>
    </a-layout>
</template>
<script>
    import {mapGetters, mapActions} from 'vuex'
    import InformationCard from "./InformationCard";
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "Portal",
        components: {InformationCard},
        data() {
            return {
                selectedKeys: [],
                username: '12'
            }
        },
        computed: {
            ...mapGetters({
                isLogin: 'auth/isLogin',
                user: 'auth/user'
            }),
        },
        mounted() {
            this.getUserInfo(res =>{
                res.then(res => {
                    // eslint-disable-next-line no-console
                    console.log("1get user info Yay! " + res.data.username);
                    this.username = res.data.username;
                })
            })
        },
        methods: {
            navPortal({key}) {
                let path = `/${key}`
                this.$router.push(path).catch(() => {
                })
            },
            setSelectedKeys() {
                this.selectedKeys = [this.$route.path.substring(1)]
            },
            ...mapActions({
                checkLogin: 'auth/checkLogin'
            }),
            handlePortalNav() {
                this.setSelectedKeys()
                // this.checkLogin(userId).then(() => {
                //     /* eslint-disable no-console */
                //     console.log('已登陆')
                // }).catch(() => {
                //     /* eslint-disable no-console */
                //     console.log('未登陆或登陆已过期')
                // })
                if(userId!=null){
                    // eslint-disable-next-line no-console
                    console.log('已登陆')
                    let values = {
                        uid: userId,
                    };
                    this.$api.getUserInfo(values).then(res => {
                        // eslint-disable-next-line no-console
                        console.log("2get user info Yay! " + res.data.username);
                        this.username = res.data.username;
                    })
                }
            },
            getUserInfo(callback) {
                let values = {
                    uid: userId,
                };
                callback(this.$api.getUserInfo(values))
            },
            logout() {
                if (!this.isLogin) {
                    return
                }
                this.$api.logout({
                    uid: this.user.uid
                }).then(() => {
                    this.$store.commit('auth/clearUser')
                    sessionStorage.clear();
                }).catch((res) => {
                    this.$message.error(res.resultInfo)
                })
            }
        },
        beforeRouteUpdate(to, from, next) {
            next()
            this.handlePortalNav()
        },
        created() {
            this.handlePortalNav()
        },
    }
</script>
<style>
    .layout .header {
        position: fixed;
        width: 100%;
        z-index: 1000
    }

    .layout .logo {
        float: left;
        margin-right: 10px;
    }

    .layout .nav {
        line-height: 64px;
    }

    .layout .user {
        float: right;
    }

    .layout .user .username {
        line-height: 64px;
    }

    .layout .center {
        margin-top: 64px;
        min-height: 280px;
    }

    .layout .main {
        padding: 0 50px;
        background: #fff;
    }

    .layout .sider {
        padding: 25px;
        background: #fff;
    }

    .layout .footer {
        text-align: center;
    }

    #components-layout-demo-fixed .logo {
        width: 120px;
        height: 31px;
        background: rgba(255, 255, 255, 0.2);
        margin: 16px 24px 16px 0;
        float: left;
    }
</style>>
