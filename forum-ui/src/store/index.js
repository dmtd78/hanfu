import vue from 'vue'

import Vuex from 'vuex'
import auth from './auth'

vue.use(Vuex)

const store = new Vuex.Store({
    modules:{
      auth
    },
    state: {
        currentUser: null,
        isLogin: false,
        token: "",
    },
})

export default store