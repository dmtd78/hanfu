import api from '../../api'
export default {
    checkLogin({ commit, state }) {
        if (state.user) {
            api.checkLogin({username: state.user.username,password:state.user.password}).then(({ data }) => {
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