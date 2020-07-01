import axios from 'axios'
import router from '../router'

const DOMAIN = 'http://localhost:8000/api/v1'
const UNAUTHORIZED = 401
const onUnauthorized = () => {
  router.push(`/login?rPath=${encodeURIComponent(location.pathname)}`)
}

const request = (method, url, data) => {
  return axios({
    method, 
    url: DOMAIN + url, 
    data
  }).then(result => result.data)
    .catch(result => {
      const {status} = result.response
      if (status === UNAUTHORIZED) onUnauthorized()
      throw result.response
    })
}

export const setAuthInHeader = token => {
  axios.defaults.headers.common['Authorization'] = token ? `Bearer ${token}` : null;
}

export const auth = {
  login(userId, password) {
    return request('post', '/auth', {userId, password}) 
  },
  logout() {
    return axios.get('http://localhost:8080')
  }
}