import vue from 'vue'

import Vuex from 'vuex'
import api from "../api";

vue.use(Vuex)

const auth = {
    namespaced: true,
    actions: {
        checkLogin({commit, state}) {
            // eslint-disable-next-line no-console
            console.log("check login username :"+state.user.username)
            if (state.user) {
                api.checkLogin({username: state.user.username, password: state.user.password}).then(({data}) => {
                    commit('setUser', data)
                    return Promise.resolve()
                }).catch(() => {
                    return Promise.reject()
                })
            } else {
                return Promise.reject()
            }
        },
    },
    getters: {
        isLogin: state => !!state.user,
        user: state => state.user
    },
    mutations: {
        setUser(state, user) {
            // eslint-disable-next-line no-console
            console.log('setUser username:' + user.username)
            state.user = user
        },
        clearUser(state) {
            state.user = null
        }
    }
}

const store = new Vuex.Store({
    modules: {
        auth
    },
    state: {
        user: null,
        isLogin: false,
        userToken: "",
    },
})

export default store