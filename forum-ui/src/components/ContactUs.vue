<template>
    <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb :style="{ margin: '16px 0' }">
            <a-breadcrumb-item>首页</a-breadcrumb-item>
            <a-breadcrumb-item>联系我们</a-breadcrumb-item>
        </a-breadcrumb>
        <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <a-card hoverable style="width: 240px">
                <img src="../assets/logo-hanfu.png" alt="合肥汉服社" width="240px" slot="cover"/>
                <a-card-meta title="合肥汉服社">
                    <template slot="description">339819261</template>
                </a-card-meta>
                <a-card-meta title="合肥汉服社2群">
                    <template slot="description">658735265</template>
                </a-card-meta>
                <a-card-meta title="合肥汉服社3群">
                    <template slot="description">570544539</template>
                </a-card-meta>
            </a-card>
        </div>

    </a-layout>
</template>
<script>
    export default {
        name: "ContactUs",
        components: {},
        data() {
            return {
                current: ['mail'],
                openKeys: ['/contactUs'],
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
