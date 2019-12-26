<template>
    <a-layout id="components-layout-demo-fixed">
        <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
            <Navigate></Navigate>
        </a-layout-header>
        <a-layout>
            <a-layout style="padding: 0 24px 24px">
                <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
                    <a-breadcrumb :style="{ margin: '16px 0' }">
                        <a-breadcrumb-item>Home</a-breadcrumb-item>
                        <a-breadcrumb-item>List</a-breadcrumb-item>
                        <a-breadcrumb-item>论坛</a-breadcrumb-item>
                    </a-breadcrumb>
                    <div><AddForum></AddForum></div>
                    <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
                        <ForumList></ForumList>
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
    import ForumList from "./ForumList";
    import AddForum from "./AddForum";
    import InformationCard from "./InformationCard";
    import Navigate from "./Navigate";

    export default {
        name: "Demo",
        components: {Navigate, InformationCard, AddForum, ForumList},
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
