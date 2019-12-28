<template>
    <a-layout id="components-layout-demo-fixed">
        <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
            <Navigate current="/register"></Navigate>
        </a-layout-header>
        <a-layout>
            <a-layout style="padding: 0 24px 24px">
                <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
                    <a-breadcrumb :style="{ margin: '16px 0' }">
                        <a-breadcrumb-item>注册/登录</a-breadcrumb-item>
                    </a-breadcrumb>
                    <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
                        <RegisterSubmit></RegisterSubmit>
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
    import RegisterSubmit from "./RegisterSubmit";

    export default {
        name: "Register",
        components: {RegisterSubmit, Navigate, InformationCard},
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
