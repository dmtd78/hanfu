import vue from 'vue'

import Vuex from 'vuex'

vue.use(Vuex)

const auth = {
    namespaced: true,
    state: {
        user: null,
        username:"",
        isLogin: false,
        userToken: "",
    },
    actions: {
        checkLogin({commit, state}) {
            // eslint-disable-next-line no-console
            console.log("check login username :"+state.username)
            commit('setUser', state.user)
        },
    },
    getters: {
        isLogin: state => state.isLogin,
        user: state => state.user
    },
    mutations: {
        setUser(state, user) {
            // eslint-disable-next-line no-console
            console.log('setUser username:' + user.username)
            state.username = user.username;
            state.isLogin = true
        },
        clearUser(state) {
            // eslint-disable-next-line no-console
            console.log('clear username:' + state.username)
            state.user = null
            state.isLogin = false
        }
    }
}

const store = new Vuex.Store({
    modules: {
        auth
    },
})

export default store