import request from '@/request'

export function getLabel(){
	return request({
		url: '/getLabel',
		method: 'get'
	})
}

export function getArticleByName(label){
	 return request({
		url: `/getArticleByName/${label}`,
		method: 'get'
	})
}

export function getArticleTypeByName(label){
	 return request({
		url: `/getArticleTypeByName/${label}`,
		method: 'get'
	})
}