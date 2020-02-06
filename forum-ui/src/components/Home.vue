<template>
    <a-layout id="components-layout-demo-fixed">
        <a-layout>
            <a-layout style="padding: 0 24px 24px">
                <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
                    <a-breadcrumb :style="{ margin: '16px 0' }">
                        <a-breadcrumb-item>首页</a-breadcrumb-item>
                        <a-breadcrumb-item>闲聊</a-breadcrumb-item>
                    </a-breadcrumb>
                    <div><AddForum></AddForum></div>
                    <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
                        <ForumListNoImg></ForumListNoImg>
                    </div>
                </a-layout-content>
            </a-layout>
        </a-layout>
    </a-layout>
</template>
<script>
    import AddForum from "./AddForum";
    // import InformationCard from "./InformationCard";
   // import Navigate from "./Navigate";
    import ForumListNoImg from "./ForumListNoImg";

    export default {
        name: "Demo",
        components: {ForumListNoImg, AddForum},
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
