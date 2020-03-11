export default {
    setUser(state, user) {
        // eslint-disable-next-line no-console
        console.log('setUser :'+user)
        state.user = user
    },
    clearUser(state) {
        state.user = null
    }
}