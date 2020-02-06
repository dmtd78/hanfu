<template>
<div class="forum">
    <a-breadcrumb class="breadcrumb">
    <a-breadcrumb-item>首页</a-breadcrumb-item>
    <a-breadcrumb-item>闲聊</a-breadcrumb-item>
    <a-breadcrumb-item>{{title}}</a-breadcrumb-item>
    </a-breadcrumb>
    <div class="content" v-html="content">
    </div>
</div>
</template>
<script>
    export default {
        name: "Home",
        data() {
            return {
                title: '',
                content: ''
            }
        },
        methods: {
            getForumInfo() {
                this.title = this.content = this.$route.params.aid
            }
        },
        created() {
            this.getForumInfo()
        }
    }
</script>
<style>
    .forum .breadcrumb {
        margin: 16px 0;
    }
</style>
