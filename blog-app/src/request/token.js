export function getToken(){
	return localStorage.token
}
export function setToken(token){
	localStorage.token=token;
	return localStorage;
}
export function removeToken(){
	return localStorage.removeItem('token')
}