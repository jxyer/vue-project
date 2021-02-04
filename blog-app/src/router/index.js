import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import {Message} from 'element-ui';
Vue.use(VueRouter)

const routes = [{
		path: '/',
		name: 'Home',
		component: () => import('@/views/Home'),
		children: [{
			path: '',
			component: () => import('@/views/index'),
			meta:{
				requireLogin:true
			}
		},
		{
			path: '/write/:writeId?',
			name:'Write',
			component: () => import('@/views/write'),
			meta:{
				requireLogin:true
			}
		},
		{
			path: '/ArticleType',
			component: () => import('@/views/ArticleType'),
			meta:{
				requireLogin:true
			}
		},
		{
			path: '/ArticleLabel',
			component: () => import('@/views/ArticleLabel'),
			meta:{
				requireLogin:true
			}
		},
		{
			path:'/articleview/:articleId',
			name:'ArticleView',
			component:()=>import('@/views/ArticleView'),
			meta:{
				requireLogin:true
			}
		},
		{
			path: '/ArticleType/:typeId',
			component: () => import('@/views/ArticleTypeView'),
			meta:{
				requireLogin:true
			}
		},
		{
			path: '/ArticleLabel/:label',
			component: () => import('@/views/ArticleLabelView'),
			meta:{
				requireLogin:true
			}
		},
		]
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('@/views/Login')
	},
	{
		path: '/register',
		name: 'Register',
		component: () => import('@/views/Register')
	}
]

const router = new VueRouter({
	routes,
	mode: 'history'
})

router.beforeEach((to, from, next) => {
	console.log(from)
	if (localStorage.token) {
		if (to.path === '/login') {
			next({
				path: '/'
			})
		} else {
			if (store.state.name.length === '') {
				store.dispatch('getUserInfo').then(data => {
					console.log(data)
					next()
				}).catch(error => {
					console.log(error)
					next({path:'/'})
				})
			}else{
				next()
			}
		}
	}else{
		if(to.matched.some(r=>r.meta.requireLogin)){
			Message({
				type:'warning',
				showClose:true,
				message:'请先登录'
			})
			next({path:'/login'})
		}else{
			next()
		}
	}
})

export default router
