import state from './state'
import getters from './getters'
import mutations from './mutations'
import actions from './actions'

export default {
    namespaced: true,  //多出的一行
    state,
    getters,
    mutations,
    actions
}