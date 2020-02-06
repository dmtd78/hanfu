<template>
    <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb :style="{ margin: '16px 0' }">
            <a-breadcrumb-item>首页</a-breadcrumb-item>
            <a-breadcrumb-item>关于我们</a-breadcrumb-item>
        </a-breadcrumb>
        <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <a-card hoverable style="width: 240px">
                大美汉服，致力于发扬中国传统文化。
            </a-card>
        </div>
    </a-layout>
</template>
<script>
    export default {
        name: "AboutUs",
        components: {},
        data() {
            return {
                current: ['mail'],
                openKeys: ['/aboutUs'],
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
