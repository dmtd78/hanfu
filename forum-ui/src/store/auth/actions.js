import api from '../../api'
export default {
    checkLogin({ commit, state }) {
        if (state.user) {
            api.checkLogin({username: 1, password: '1'}).then(({ data }) => {
                commit('setUser', data)
                return Promise.resolve()
            }).catch(() => {
                return Promise.reject()
            })
        } else {
            return Promise.reject()
        }
    },
}