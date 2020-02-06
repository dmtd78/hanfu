<template>
    <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb :style="{ margin: '16px 0' }">
            <a-breadcrumb-item>首页</a-breadcrumb-item>
            <a-breadcrumb-item>图文赏析</a-breadcrumb-item>
        </a-breadcrumb>
        <div>
            <AddForum></AddForum>
        </div>
        <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <ForumListImg></ForumListImg>
        </div>
    </a-layout>
</template>
<script>
    import AddForum from "./AddForum";
    import ForumListImg from "./ForumListImg";

    export default {
        name: "PhotoForum",
        components: {ForumListImg, AddForum},
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
