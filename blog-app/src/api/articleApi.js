import request from '@/request'

export function getArticles(page,num){
	return request({
		url: `/getArticles/${page}/${num}`,
		method: 'get'
	})
}
export function getHotLabel(num){
	return request({
		url: `/getHotLabel/${num}`,
		method: 'get'
	})
}

export function getHotArticle(){
	return request({
		url: `/getHotArticle`,
		method: 'get'
	})
}

export function getCurrentArticle(id){
	return request({
		url: `/getCurrentArticle/${id}`,
		method: 'get'
	})
}

export function updateComment(id){
	return request({
		url: `/updateComment/${id}`,
		method: 'put',
	})
}
export function updateView(id){
	return request({
		url: `/updateView/${id}`,
		method: 'put',
	})
}

export function postComment(comment){
	const data={
		articleid:comment.articleid,
		userid:comment.userid,
		createTime:comment.createtime,
		content:comment.content,
		parentId:comment.parentId,
		touserid:comment.touid
	}
	return request({
		url:'/addComment',
		method:'post',
		data
	})
}