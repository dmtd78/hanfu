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
            <a-menu-item key="/bussinessHome">汉服推荐</a-menu-item>
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
            <a-menu-item key="/bussinessHome">汉服推荐</a-menu-item>
            <a-menu-item key="/aboutUs">关于我们</a-menu-item>
            <a-menu-item key="/contactUs">联系我们</a-menu-item>
            <a-menu-item key=""><LoginOut></LoginOut></a-menu-item>
        </a-menu>
    </div>
</template>

<script>
    import LoginOut from "./LoginOut";
    // eslint-disable-next-line no-irregular-whitespace
    const userId = JSON.parse(sessionStorage.getItem("userId"));
    export default {
        name: "Navigate",
        components: {LoginOut},
        beforeCreate() {
            this.userId = JSON.parse(sessionStorage.getItem("userId"));
        },
        data() {
            return {
                userId: userId
            }
        },
        props: {
            current: {
                type: String,
                default: '/home'
            }
        },
        computed: {
            selectedKeys() {
                return [this.current]
            }
        },
        methods: {
            changeNavigate({key}) {
                this.$router.push(key).catch(() => {
                })
            },
        }
    }
</script>

<style>

</style>
