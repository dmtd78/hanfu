<template>
    <a-layout id="components-layout-demo-fixed">
        <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
            <Navigate current="/contactUs"></Navigate>
        </a-layout-header>
        <a-layout>
            <a-layout style="padding: 0 24px 24px">
                <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
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
    import InformationCard from "./InformationCard";
    import Navigate from "./Navigate";
    export default {
        name: "ContactUs",
        components: {Navigate, InformationCard},
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
