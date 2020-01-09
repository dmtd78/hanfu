<template>
    <a-card hoverable style="width: 240px">
        <a-card-meta>
            <div :style="{ width: '240px', border: '1px solid #d9d9d9', borderRadius: '4px' }">
                
            </div>
        </a-card-meta>

        <a-card-meta title="" description="我的积分：500">

        </a-card-meta>

    </a-card>
</template>

<script>
    export default {
        name: "SignModule",
        methods:{
            onPanelChange(value, mode) {
                // eslint-disable-next-line no-console
                console.log(value, mode);
            }
        }
    }
</script>

<style scoped>

</style>