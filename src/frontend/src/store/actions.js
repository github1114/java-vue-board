import * as api from '../api'

const actions = {
LOGIN ({commit}, {userId, password}) {
    return api.auth.login(userId, password)
        .then((result)=> commit('LOGIN',result))
},
LOGOUT({commit}) {
    return api.auth.logout().then((data)=> commit('LOGOUT',data))
},
}

export default actions