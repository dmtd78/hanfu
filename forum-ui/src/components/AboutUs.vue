<template>
    <a-layout id="components-layout-demo-fixed">
        <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
            <div class="logo"><img src="../assets/logo-hanfu.png" height="51" width="120"/></div>
            <a-menu
                    theme="dark"
                    mode="horizontal"
                    :defaultSelectedKeys="['/aboutUs']"
                    :style="{ lineHeight: '64px' }"
                    :openKeys.sync="openKeys"
                    @click="changeNavigate">
                <a-menu-item key="/home">论坛</a-menu-item>
                <a-menu-item key="/aboutUs">关于我们</a-menu-item>
                <a-menu-item key="/contactUs">联系我们</a-menu-item>
                <a-menu-item key="/demo">Demo</a-menu-item>
            </a-menu>

        </a-layout-header>
        <a-layout>
            <a-layout style="padding: 0 24px 24px">
                <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
                    <a-breadcrumb :style="{ margin: '16px 0' }">
                        <a-breadcrumb-item>Home</a-breadcrumb-item>
                        <a-breadcrumb-item>List</a-breadcrumb-item>
                        <a-breadcrumb-item>关于我们</a-breadcrumb-item>
                    </a-breadcrumb>
                    <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
                        <a-card hoverable style="width: 240px">
                            大美汉服，致力于发扬中国传统文化。
                        </a-card>
                    </div>
                </a-layout-content>
            </a-layout>
            <a-layout-sider width="300" :style="{background: '#fff',padding:'84px 1px 0px 1px',overflow: 'auto', right: 0 }">
                <InformationCard></InformationCard>
            </a-layout-sider>
        </a-layout>
        <a-layout-footer :style="{ textAlign: 'center' }">
            大美汉服 Design ©2019 Created by Dmtd
        </a-layout-footer>
    </a-layout>
</template>
<script>
    import InformationCard from "./ImformationCard";
    export default {
        name: "AboutUs",
        components: {InformationCard},
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
