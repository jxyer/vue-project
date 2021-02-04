import request from '@/request'

export function getArticleType(){
	return request({
		url:'/getArticleType',
		method:'get'
	})
}

export function getArticleById(id){
	return request({
		url:`/getArticleById/${id}`,
		method:'get'
	})
}

export function getArticleTypeById(id){
	return request({
		url:`/getArticleTypeById/${id}`,
		method:'get'
	})
}