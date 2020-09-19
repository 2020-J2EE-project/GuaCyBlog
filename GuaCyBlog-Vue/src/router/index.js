import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '../components/Login.vue'
import success from '../components/Success.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/admin/User.vue'
import Article from '../components/Article.vue'
import Mail from '../components/admin/Mail.vue'
import Page from '../components/Page.vue'
import Friend from '../components/admin/Friend.vue'
import Editor from '../components/Editor.vue'
import Register from '../components/register.vue'
import 'mavon-editor/dist/css/index.css'
import Show from '../components/showArticle.vue'
import mavonEditor from 'mavon-editor'

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
      { path:'/show',component: Show}
    ]
  }
]

const router = new VueRouter({
  routes
})
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to:将要访问的路径
  // from:从哪里访问的路径
  // next:之后要做的任务，是一个函数
  //    next（）放行， next（'/URL'）强制跳转的路径。
  if (to.path == '/login') return next();// 访问路径为登录
  if(to.path=='/register') return next();
  // 获取flag
  const flagStr = window.sessionStorage.getItem("flag");// session取值
  if (!flagStr) return next('/login');// 没登录去登录
  next();
})

export default router// 暴露出去
