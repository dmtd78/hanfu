<template>
    <div id="navigate">
        <div class="logo"><img src="../assets/logo-hanfu.png" height="51" width="120"/></div>
        <a-menu v-if="userId==null"
                theme="dark"
                mode="horizontal"
                :selectedKeys="selectedKeys"
                :style="{ lineHeight: '64px' }"
                @select="changeNavigate">
            <a-menu-item key="/home">闲聊</a-menu-item>
            <a-menu-item key="/photoForum">图文赏析</a-menu-item>
            <a-menu-item key="/littleWhite">小白</a-menu-item>
            <a-menu-item key="/aboutUs">关于我们</a-menu-item>
            <a-menu-item key="/contactUs">联系我们</a-menu-item>
            <a-menu-item key="/register">注册/登录</a-menu-item>
        </a-menu>
        <a-menu v-else
                theme="dark"
                mode="horizontal"
                :selectedKeys="selectedKeys"
                :style="{ lineHeight: '64px' }"
                @select="changeNavigate">
            <a-menu-item key="/home">闲聊</a-menu-item>
            <a-menu-item key="/photoForum">图文赏析</a-menu-item>
            <a-menu-item key="/littleWhite">小白</a-menu-item>
            <a-menu-item key="/aboutUs">关于我们</a-menu-item>
            <a-menu-item key="/contactUs">联系我们</a-menu-item>
            <LoginOut></LoginOut>
        </a-menu>
    </div>
</template>

<script>
    import axios from 'axios';
    import LoginOut from "./LoginOut";
    // eslint-disable-next-line no-irregular-whitespace
    const userId=JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "Navigate",
        components: {LoginOut},
        data() {
            return {
                lrmsg:'注册/登录',
                userId:userId
            }
        },
        props: {
            current:{
                type: String,
                default: '/home'
            }
        },
        computed: {
            selectedKeys() {
                return [this.current]
            }
        },
        mounted() {
            this.getData(res => {
                this.data = res.data;
            });
        },
        methods: {
            changeNavigate({key}) {
                this.$router.push(key).catch(() => {})
            },
            getData() {
                axios.get('/user/info?uid='+userId,{
                    xhrFields: {
                        withCredentials: true
                    },
                }).then((res) => {
                    if (res.data.resultCode == 0) {
                        this.lrmsg = res.data.data.username;
                    } else {
                        this.$message.failure(
                            res.data.resultInfo,
                            10,
                        );
                    }
                })
            },
        }
    }
</script>

<style>

</style>
