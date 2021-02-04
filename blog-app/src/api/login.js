import request from '@/request'

export function login(name, password) {
	const data = {
		name,
		password
	}
	return request({
		url: '/login',
		method: 'post',
		data
	})
}
export function register(name, password) {
	const data = {
		name,
		password
	}
	return request({
		url: '/register',
		method: 'post',
		data
	})
}

export function getUserInfo(){
	return request({
		url:'/getCurrentUserInfo',
		method:'get'
	})
}

export function logout(){
	return request({
		url:'/logout',
		method:'get'
	})
}

export function getArticleInfo(){
	return request({
		url:'getArticleInfo',
		method:'get'
	})
}

export function addArticleInfo(Article, ArticleCategory,ArticleTypes) {
	console.log(Article)
	console.log(ArticleCategory)
	console.log(ArticleTypes)
	const data = {
		Article,
		ArticleCategory,
		ArticleTypes
	}
	return request({
		url: '/addArticle',
		method: 'post',
		data
	})
}
