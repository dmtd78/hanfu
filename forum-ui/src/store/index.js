import vue from 'vue'

import vuex from 'vuex'

vue.use(vuex)

const store = new vuex.store({
    state: {
        currentUser: null,
        isLogin: false,
        token: "",
    },
})

export default store