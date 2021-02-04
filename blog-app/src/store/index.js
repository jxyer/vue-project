import Vue from 'vue'
import Vuex from 'vuex'
import {
	SET_ID,
	SET_NAME,
	SET_TOKEN
} from './mutation-types.js'

import {
	login,
	getUserInfo,
	logout
} from '@/api/login.js'

import {setToken} from '@/request/token.js'
Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		id: '',
		name: '',
		token: ''
	},
	mutations: {
		[SET_ID](state, id) {
			state.id = id
		},
		[SET_NAME](state, name) {
			state.name = name
		},
		[SET_TOKEN](state, token) {
			state.token = token
		}
	},
	actions: {
		logins({
			commit
		}, user) {
			return new Promise((resolve, reject) => {
				login(user.name, user.password)
					.then(successResponse => {
						console.log(successResponse);
						if (successResponse.data.code == 200) {
							commit('SET_ID', successResponse.data.data.id)
							commit('SET_NAME', successResponse.data.data.name)
							commit('SET_TOKEN', successResponse.data.token.token)
							setToken(successResponse.data.token.token,successResponse.data.data.id)
							resolve()
						} else {
							reject(successResponse.data.msg)
						}
					}).catch(error => {
						reject(error)
					})
			})
		},
		getUserInfo({
			commit
		}) {
			return new Promise((resolve, reject) => {
				getUserInfo().then(data => {
					console.log(data)
					if (data.data.code == 200) {
						commit('SET_ID', data.data.data.id)
						commit('SET_NAME', data.data.data.name)
						commit('SET_TOKEN', data.data.token.token)
						resolve()
					} else {
						reject(data.data.msg)
					}
				}).catch(error => {
					reject(error)
				})
			})
		},
		fedLogOut({
			commit
		}) {
			commit(SET_ID, '')
			commit(SET_NAME, '')
			commit(SET_TOKEN, '')
			localStorage.removeItem('token');
		},
		logout({
			commit
		}) {
			return new Promise((resolve, reject) => {
				logout().then(data => {
					console.log(data)
					if (data.data.code == 200) {
						commit(SET_ID, '')
						commit(SET_NAME, '')
						commit(SET_TOKEN, '')
						localStorage.removeItem('token');
						resolve()
					}
				}).catch(error => {
					reject(error)
				})
			})
		}
	},
	modules: {}
})
