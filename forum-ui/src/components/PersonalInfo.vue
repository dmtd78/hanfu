<template>
    <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb :style="{ margin: '16px 0' }">
            <a-breadcrumb-item>首页</a-breadcrumb-item>
            <a-breadcrumb-item>个人中心</a-breadcrumb-item>
        </a-breadcrumb>
        <div id="forum" :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <PersonalCenter></PersonalCenter>
        </div>
    </a-layout>

</template>
<script>
    import PersonalCenter from "./PersonalCenter";

    export default {
        name: "PersonalInfo",
        components: {PersonalCenter},
        data() {},
        methods: {},
    }
</script>
<style>

</style>
