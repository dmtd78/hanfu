<template>
    <nav>
        <ul>
            <li width="80"><img src="../assets/logo-hanfu.png" height="50" width="70"/></li>
            <li v-for="(item, i) in navData" :class="{ nav_li : active == i }" :key="i"
                @click="$router.push({ path: item.path }), active = i">
                 {{item.name}}
            </li>
        </ul>
    </nav>
</template>

<script>
    export default {
        name: "Navigate",
        data() {
            return {
                active: 0,
                navData: [
                    {name: '论坛', path: '/配置的路由'},
                    {name: '联系我们', path: 'http://www.baidu.com'},
                    {name: '登录/注册', path: '/配置的路由'}
                ]
            }
        }
    }
</script>

<style scoped>
    ul {
        height: 50px;
        line-height: 50px;
        margin: 0 auto;
        background: #3b2d50;
        color: #fff;
        font-size: 18px;
        display: flex;
        justify-content: space-between;
    }

    li {
        text-align: center;
    }

    .nav_li {
        background-color: #dedede;
        color: #353535;
    }
</style>