<template>
    <div id="navigate">
        <div class="logo"><img src="../assets/logo-hanfu.png" height="51" width="120"/></div>
        <a-menu
                theme="dark"
                mode="horizontal"
                :defaultSelectedKeys="['/home']"
                :style="{ lineHeight: '64px' }"
                :openKeys.sync="openKeys"
                @click="changeNavigate">
            <a-menu-item key="/home">论坛</a-menu-item>
            <a-menu-item key="/aboutUs">关于我们</a-menu-item>
            <a-menu-item key="/contactUs">联系我们</a-menu-item>
        </a-menu>
    </div>
</template>

<script>
    export default {
        name: "Navigate",
        data() {
            return {
                current: ['mail'],
                openKeys: ['/home'],
            };
        },
        methods: {
            changeNavigate({key}) {
                this.$router.push(key)
            }
        },
        watch: {
            openKeys(val) {
                // eslint-disable-next-line no-console
                console.log('openKeys', val);
            },
        },
    }
</script>

<style>

</style>