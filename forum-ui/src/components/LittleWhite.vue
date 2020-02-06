<template>
    <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb :style="{ margin: '16px 0' }">
            <a-breadcrumb-item>首页</a-breadcrumb-item>
            <a-breadcrumb-item>小白</a-breadcrumb-item>
        </a-breadcrumb>
        <div>
            <AddForum></AddForum>
        </div>
        <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <LittleWhiteList></LittleWhiteList>
        </div>
    </a-layout>
</template>
<script>
    import LittleWhiteList from "./LittleWhiteList";
    import AddForum from "./AddForum";

    export default {
        name: "LittleWhite",
        components: {AddForum, LittleWhiteList},
        data() {
            return {
                current: ['mail'],
                openKeys: ['/littleWhite'],
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
    #components-layout-demo-fixed .logo {
        width: 120px;
        height: 31px;
        background: rgba(255, 255, 255, 0.2);
        margin: 16px 24px 16px 0;
        float: left;
    }
</style>
