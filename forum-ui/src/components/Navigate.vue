<template>
    <div id="navigate">
        <div class="logo"><img src="../assets/logo-hanfu.png" height="51" width="120"/></div>
        <a-menu
                theme="dark"
                mode="horizontal"
                :selectedKeys="selectedKeys"
                :style="{ lineHeight: '64px' }"
                @select="changeNavigate">
            <a-menu-item key="/home">论坛</a-menu-item>
            <a-menu-item key="/photoForum">图文赏析</a-menu-item>
            <a-menu-item key="/aboutUs">关于我们</a-menu-item>
            <a-menu-item key="/contactUs">联系我们</a-menu-item>
            <a-menu-item key="/register">注册</a-menu-item>
        </a-menu>
    </div>
</template>

<script>
    export default {
        name: "Navigate",
        data() {
            return {}
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
        methods: {
            changeNavigate({key}) {
                this.$router.push(key).catch(() => {})
            }
        }
    }
</script>

<style>

</style>
