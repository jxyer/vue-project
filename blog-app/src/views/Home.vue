<template>
	<div id="home">
		<base-header :isLogin='isLogin' :activeIndex='activeIndex'></base-header>
		<router-view />
	</div>
</template>

<script>
	import BaseHeader from '@/components/BaseHeader.vue'
	
	import {getToken} from '@/request/token.js'
	
	export default {
		name: 'Home',
		components: {
			BaseHeader
		},
		data(){
			return{
				activeIndex:'/'
			}
		},
		computed:{
			isLogin:function(){
				return getToken()
			}
		},
		beforeRouteEnter(to,from,next){
			next(vm=>{
				vm.activeIndex = to.path
			})
		},
		beforeRouteUpdate(to,from,next){
			if(to.path==='/' || to.path==='/ArticleType' || to.path==='/ArticleLabel' || to.path==='/write')
			this.activeIndex=to.path
			next()
		}
	}
</script>


<style>
	#home{
		width: 100%;
		height: 100%;
	}
	
</style>
