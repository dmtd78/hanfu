<template>
    <h2 class="red">{{msg}}</h2>
</template>

<script>
    export default {
        name: "Home",
        props:{
            msg:{
                type:String,
                default :"欢迎光临大美汉服网站！"
            }
        },
        methods:{

        },
        data(){
            return {
            }
        }
    }
</script>

<style scoped>
    .red{color:red;}

</style>