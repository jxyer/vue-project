import axios from 'axios'
import store from '@/store'
import {
	Message
} from 'element-ui'
import {getToken} from './token.js'

const serivce = axios.create({
	baseURL: 'http://localhost:8888/api',
	timeout: 10000
})
serivce.interceptors.request.use(config => {
	console.log(store)
	if (getToken()) {
		config.headers['token'] = getToken()
	}
	return config
}, error => {
	Promise.reject(error)
})
serivce.interceptors.response.use(reponse => {
	if (reponse.data.code == 403) {
		store.dispatch('fedLogOut')
		Message({
			type: 'warning',
			showClose: true,
			message: '登录超时，请重新登录哦'
		})
		
	} else {
		return reponse
	}
})

export default serivce
