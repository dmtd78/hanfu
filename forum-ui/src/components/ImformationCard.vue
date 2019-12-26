<template>
    <a-card hoverable style="width: 240px">
        <img
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
                slot="cover"
        />
        <template class="ant-card-actions" slot="actions">
            <a-icon type="setting" />
            <a-icon type="edit" />
            <a-icon type="ellipsis" />
        </template>
        <a-card-meta title="Card title" description="This is the description">
            <a-avatar
                    slot="avatar"
                    src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
        </a-card-meta>
    </a-card>
</template>

<script>
    export default {
        name: "InformationCard"
    }
</script>

<style scoped>

</style>