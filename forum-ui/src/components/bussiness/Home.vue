<template>
    <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb :style="{ margin: '16px 0' }">
            <a-breadcrumb-item>首页</a-breadcrumb-item>
            <a-breadcrumb-item>汉服商家专区</a-breadcrumb-item>
        </a-breadcrumb>
        <div>
            <AddForum></AddForum>
        </div>
        <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <ImgList></ImgList>
        </div>
    </a-layout>
</template>
<script>
    import AddForum from "../AddForum";
    import ImgList from "./ImgList";

    export default {
        name: "BussinessHome",
        components: {ImgList, AddForum},
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
