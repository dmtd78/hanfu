<template>

</template>

<script>

    export default {
        name: "CreateArticle",
        props: ['visible'],
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'form_in_modal'});
        },
        methods:{
            showModal() {
                this.visible = true;
            },

        }
    }
</script>

<style scoped>

</style>