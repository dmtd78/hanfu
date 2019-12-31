<template>
    <a-tabs defaultActiveKey="1" @change="callback">
        <a-tab-pane tab="登录" key="1" forceRender>
            <LoginForm></LoginForm>
        </a-tab-pane>
        <a-tab-pane tab="注册" key="2">
            <RegisterForm></RegisterForm>
        </a-tab-pane>

    </a-tabs>
</template>

<script>
    import LoginForm from "./LoginForm";
    import RegisterForm from "./RegisterForm";
    export default {
        name: "RegisterSubmit",
        components: {RegisterForm, LoginForm},
        methods:{
            callback(key) {
                // eslint-disable-next-line no-console
                console.log(key);
            },
        }
    };
</script>