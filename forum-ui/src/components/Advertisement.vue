<template>
    <div>
    <h1>{{title}}</h1>
    <h2>{{content}}</h2>
    </div>
</template>

<script>
    export default {
        name: "Advertisement",
        props:{
            title:{
                type:String,
                default :"求赞助"
            },
            content:{
                type:String,
                default :"为网站建设，求志同道合者一块来完成项目的建设，联系电话：17730212170，QQ：343547566，非诚勿扰！"
            }
        }
    }
</script>

<style scoped>

</style>