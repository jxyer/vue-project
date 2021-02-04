<template>
	<div id="BaseHeader">
		<el-row>
			<el-col :span="4">
				<el-image style="width: 60px; height: 60px" :src="url"></el-image>
			</el-col>
			<el-col :span="8" :offset='2'>
				<el-menu :router="true" :default-active="activeIndex" mode="horizontal" active-text-color="#C8E6C9" text-color="#FFFFFF" background-color="#4CAF50">
					<el-menu-item index="/">首页</el-menu-item>
					<el-menu-item index="/ArticleType">文章分类</el-menu-item>
					<el-menu-item index="/ArticleLabel">标签</el-menu-item>
					<el-menu-item index="/write">写文章</el-menu-item>
				</el-menu>
			</el-col>
			<el-col :span="6">
				<div id='header-right'>
					<el-autocomplete size='mini' class="query-input" v-model="article" :fetch-suggestions="querySearch" placeholder="搜索文章" @select="handleSelect">
					<i slot="prefix" class="el-input__icon el-icon-search"></i>
					</el-autocomplete>
				</div>
			</el-col>
			<el-col :span="4">
				<div id="header-end">
				<el-row id="header-end-width">
					<el-col :span="24">
						<router-link class='text-white' to="/login" v-show='!isLogin' @click='login'>去登录</router-link>	
					</el-col>
					<div v-show="isLogin">
						<el-col :span="12">
							<i style="margin-right: 2px;" class="el-icon-user"></i>
							<span style="font-size: 0.875rem;">欢迎{{userName}}用户</span>
						</el-col>
						<el-col :span="12">
							<i style="margin-right: 2px;" class="el-icon-switch-button"></i>
							<span @click='logout' style="font-size: 0.875rem;cursor: pointer;">退出</span>
						</el-col>
					</div>
				</el-row>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import {Message} from 'element-ui';
	import {
		getArticles
	} from '@/api/articleApi.js'
	import {getToken} from '@/request/token.js'
	
	
	export default {
		props: {
			activeIndex:{
				type:String,
				default:'/'
			},
			isLogin: {
				type:String,
				default:''
			}
		},
		data() {
			return {
				url: 'https://images.unsplash.com/photo-1567446537708-ac4aa75c9c28?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80',
				article:'',
				articles:['aa','bb'],
				timeout:null,
				userName:'用户',
			}
		},
		methods: {
			login() {
				this.$router.replace({
					name: 'Login'
				})
			},
			querySearch(queryString, cb){
				let list=[{}]
				getArticles(1,999).then(data=>{
					if(data.data.code==200){
						for(let v of data.data.data){
							v.value=v.title
						}
						list=data.data.data
						let results=queryString?list.filter(this.queryFilter(queryString)):list
						cb(results)
					}
				}).catch(error=>{
					console.log(error)
				})
			},
			queryFilter(str){
				return (current)=>{
					return current.value.includes(str)
				}
			},
			handleSelect(item) {
				this.$router.push({path:`articleview/${item.id}`})
			},
			logout(){
				this.$store.dispatch('logout').then(()=>{
					Message({
						type:'success',
						showClose:true,
						message:'退出成功'
					})
					this.$router.replace({name:'Login'})
				}).catch(error=>{
					Message({
						type:'success',
						showClose:true,
						message:error
					})
				})
			}
		},
		mounted(){
			let that=this
			if(getToken()){
				if(that.$store.state.name===''){
					that.$store.dispatch('getUserInfo').then(()=>{
						that.userName=that.$store.state.name
					})
				}else{
					that.userName=that.$store.state.name
				}
			}
		}
	}
</script>

<style>
	#BaseHeader {
		text-align: center;
		height: 3.75rem;
		background-color: #4CAF50;
		box-shadow: 0px 1px 2px 2px rgba(76,175,80,.3);
	}
	#header-right ,#header-end{
		color: #FFFFFF;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: row;
		height: 3.75rem;
	}
	#header-end-width{
		width: 100%;
	}
	.query-input{
		margin-right: 20px;
	}
	.text-white{
		color: #FFFFFF;
	}
	.el-menu.el-menu--horizontal{
		border-bottom: none;
	}
</style>
