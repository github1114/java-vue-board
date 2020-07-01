import {setAuthInHeader} from '../api'
import jwt from 'jsonwebtoken'

const mutations = {
  LOGIN (state, token) {
    if (!token) return 
    state.token = token
    localStorage.setItem('token', token)
    setAuthInHeader(token)
    let decodedToken = jwt.decode(token);
    if(decodedToken) state.userId = decodedToken.userId;
  },
  LOGOUT (state) {
    state.token = null;
    state.userId = '';
    localStorage.setItem('token', null)
  },
}

export default mutations