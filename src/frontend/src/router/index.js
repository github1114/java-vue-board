import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Board from '../views/BoardList.vue'
import NotFound from '../components/NotFound.vue';

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes:[
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/board',
      name: 'Board',
      component: Board,
      beforeEnter: (to, from, next) => {
        let token = localStorage.getItem('token');

        if(token != 'null') {
          next();
        } else{
          next('/');
        }
      }
    },
    {
      path: '/*',
      name: 'NotFound',
      component: NotFound
    }
  ]
})

export default router
