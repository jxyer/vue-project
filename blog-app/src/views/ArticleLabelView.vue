<template>
	<div id="ArticleLabelView">
		<div class="content">
			<div class="index-left">
				<el-image style='width: 200px;height: 100px;' class="article-type-item-img" :src="articleType.image" fit="contain"></el-image>
				<el-row style="margin-top: 20px;">
					{{articleType.typename}}
				</el-row>
				<el-row style="margin-top: 20px;font-size: 12px;color: #969696;">
					{{articleType.remark}}
				</el-row>
			</div>
			<div class="index-mid">
				<Scroll>
					<article-item :articleList='articles'>
					</article-item>
				</Scroll>
			</div>
			<div class="index-right">
				3
			</div>
		</div>
	</div>
</template>

<script>
	import Scroll from '@/components/Scroll.vue'
	import ArticleItem from '@/components/ArticleItem.vue'
	import {
		getArticleByName,
		getArticleTypeByName
	} from '@/api/articleLabelApi.js'
	import {
		Message
	} from 'element-ui'
	export default{
		components:{
			Scroll,
			ArticleItem
		},
		data(){
			return {
				articles:[{}],
				articleType:{},
			}
		},
		mounted(){
			let label=this.$route.params.label
			getArticleByName(label).then(data => {
				if (data.data.code == 200) {
					this.articles=data.data.data
					for(let v in this.articles){
						if(this.articles[v]==null || this.articles[v]==undefined)
							this.articles.splice(v,1)
					}
					console.log(this.articles)
				} else {
					Message({
						type: 'success',
						showClose: true,
						message: data.data.msg
					})
				}
			}).catch(error => {
				Message({
					type: 'success',
					showClose: true,
					message: error
				})
			})
			getArticleTypeByName(label).then(data => {
				if (data.data.code == 200) {
					this.articleType=data.data.data
					console.log(this.articleType)
				} else {
					Message({
						type: 'success',
						showClose: true,
						message: data.data.msg
					})
				}
			}).catch(error => {
				Message({
					type: 'success',
					showClose: true,
					message: error
				})
			})
		}
		
	}
</script>

<style>
	#ArticleTypeView {
		width: 95%;
	}
	.content{
		flex: 3;
		padding: 20px;
		width: 90%;
		display: flex;
		flex-direction: row;
	}
	.content>div {
		margin-left: 50px;
	}
	.index-left {
		flex: 1;
		text-align: center;
	}
	
	.index-mid {
		flex: 3;
	}
	
	.index-right {
		flex: 2;
	}
	.article-card {
		margin-bottom: 20px;
		z-index: 1000;
	}
	
	.article-card-title {
		color: #212121;
		font-size: 1.25rem;
	}
	
	.article-card-content {
		margin-top: 10px;
		color: #757575;
		font-size: 0.875rem;
	}
	
	.article-card-foot {
		margin-top: 20px;
		color: #BDBDBD;
		font-size: 12px;
	}
	
	.article-card-title-view {
		margin-right: 10px;
		font-size: 12px;
		color: #BDBDBD;
	}
	
	.article-card-title-comment {
		font-size: 12px;
		color: #BDBDBD;
	}
	
	.card-title {
		font-size: 16px;
		font-weight: bold;
	}
	.hot-article-title:hover{
		color: #C8E6C9;
	}
	.hot-article-title {
		font-size: 14px;
		padding: 0px 10px;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		line-height: 34px;
		color: #4CAF50;
		box-sizing: border-box;
		cursor: pointer;
	}
	
	.header-title{
		text-align: center;
	}
	
</style>
