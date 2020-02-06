<template>
    <a-layout class="layout">
        <a-layout-header class="header">
            <a-menu v-if="isLogin" class="user" theme="dark" mode="horizontal" :selectable="false">
                <a-menu-item key="user">
                    <a-dropdown>
                        <span class="username">123</span>
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
                <a-menu-item key="contactUs">联系我们</a-menu-item>
                <a-menu-item key="login" v-if="!isLogin">注册/登录</a-menu-item>      
            </a-menu>
        </a-layout-header>
        <a-layout class="center">
            <a-layout-content class="main">
                <router-view></router-view>
            </a-layout-content>
            <a-layout-sider class="sider">
                <a-card>
                    <img alt="example" src="../assets/hanfu-hf.jpg" slot="cover" />
                    <template class="ant-card-actions" slot="actions">
                        <a-tooltip placement="topLeft" title="设置">
                            <a-icon type="setting"/>
                        </a-tooltip>
                        <a-tooltip placement="topLeft" title="修改">
                            <a-icon type="edit"/>
                        </a-tooltip>
                        <a-tooltip placement="topLeft" title="个人中心">
                            <a-icon type="ellipsis"/>
                        </a-tooltip>
                    </template>
                </a-card>
            </a-layout-sider>
        </a-layout>
        <a-layout-footer class="footer">
            大美汉服 Design ©2019 Created by Dmtd
        </a-layout-footer>
    </a-layout>
</template>
<script>
    import { mapGetters, mapActions } from 'vuex'
    export default {
        name: "Portal",
        data() {
            return {
                selectedKeys: [],
            }
        },
        computed: {
            ...mapGetters({
                isLogin: 'auth/isLogin',
                user: 'auth/user'
            }),
        },
        methods: {
            navPortal({ key }) {
                let path = `/${key}`
                this.$router.push(path).catch(() => {})
            },
            setSelectedKeys() {
                this.selectedKeys = [ this.$route.path.substring(1) ]
            },
            ...mapActions({
                checkLogin: 'auth/checkLogin'
            }),
            handlePortalNav() {
                this.setSelectedKeys()
                this.checkLogin().then(() => {
                    /* eslint-disable no-console */
                    console.log('已登陆')
                }).catch(() => {
                    /* eslint-disable no-console */
                    console.log('未登陆或登陆已过期')
                })
            },
            logout() {
                if (!this.isLogin) {
                    return
                }
                this.$api.logout({
                    uid: this.user.uid
                }).then(() => {
                    this.$store.commit('auth/clearUser')
                }).catch((res) => {
                    this.$message.error(res.resultInfo)
                })
            }
        },
        beforeRouteUpdate (to, from, next) {
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
</style>>
