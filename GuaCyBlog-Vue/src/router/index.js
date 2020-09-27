import Vue from 'vue'
import VueRouter from 'vue-router'

import 'mavon-editor/dist/css/index.css'
import mavonEditor from 'mavon-editor'

import Login from '../components/Login.vue'
import success from '../components/Success.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/admin/User.vue'
import Article from '../components/Article.vue'
import Mail from '../components/admin/Mail.vue'
import Page from '../components/Page.vue'
import Friend from '../components/admin/Friend.vue'
import Editor from '../components/Editor.vue'
import Register from '../components/register.vue'
import Show from '../components/showArticle.vue'
import tag from '../components/admin/tag.vue'
import follower from '../components/admin/follower.vue';
import following from  '../components/admin/following.vue';

Vue.use(mavonEditor)
Vue.use(VueRouter)

const routes = [
  {
    path: '/success',
    component: success
  },
  {
    path: '/login',
    component: Login
  },
  {
    path:'/register',
    component: Register
  },
  {
    path: '/page',
    component: Page,
    redirect: '/welcome',
    children:[
      { path:'/mail',component: Mail},
      { path:'/article',component: Article},
      { path:'/welcome', component: Welcome},
      { path:'/mail',component: Mail},
      { path:'/friend', component: Friend},
      { path:'/user', component: User},
      { path:'/github'},
      { path:'/editor',component: Editor},
      { path:'/show',component: Show},
      { path:'/tag',component:tag},
      { path:'/following',component:following},
      { path:'/follower',component: follower},
    ]
  }
]

const router = new VueRouter({
  routes
})
router.beforeEach((to, from, next) => {
  if (to.path == '/login') return next();
  if(to.path=='/register') return next();
  const flagStr = window.sessionStorage.getItem("flag");
  if (!flagStr) return next('/login');
  next();
})
export default router
