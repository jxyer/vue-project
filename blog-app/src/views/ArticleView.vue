<template>
	<div id="ArticleView">
		<div id="articleview-left">

		</div>
		<div id="articleview-mid">
			<el-container id="article">
				<el-heade>
					<div id="article-title">
						<span>{{article.title}}</span>
					</div>

					<div id='article-info'>
						<div>
							<i style="margin-right: 3px;" class="el-icon-user"></i>
							<span style="font-size: 14px;">{{user.name}}</span>
							<span class="time-color-1" style="margin-left: 20px; font-size: 14px">{{article.createtime | format}}</span>
							<span class="time-color-1" style="margin-left: 20px; font-size: 14px;">阅读:{{article.viewCount}}</span>
							<span class="time-color-1" style="margin-left: 20px; font-size: 14px;">评论:{{article.commentCount}}</span>
							<el-button @click='to_write(articleId)' v-show='isAuthor' style="float: right;position: relative;top: -30px;" size="mini" round>编辑</el-button>
						</div>
						<div>

						</div>
					</div>
					<el-divider class="divid-no-span"></el-divider>
				</el-heade>
				<el-main class='article-content'>
					<Editor id="tinymce" v-model="article.content" :init="init"></Editor>
					<div id="preview">
					</div>
				</el-main>
				<el-footer>
				</el-footer>
			</el-container>
			<div class="article-end">
				<el-divider class="divid-no-span" content-position="center">文章结尾</el-divider>
				<div id="article-cateogry">
					<span style="font-size: 14px;font-weight: bolder;">文章分类:</span>
					<el-button @click='type_btn(articleType.id)' style="margin-left: 10px;" type="success" size="mini" round plain>{{articleType.name}}</el-button>
				</div>
				<div id="article-type">
					<span style="font-size: 14px;font-weight: bolder;">文章标签:</span>
					<el-button @click='label_item_btn(label.name)' v-for='label in labels' :key='label.id' style="margin-left: 10px;" type="success" size="mini" round
					 plain>
						{{label.name}}
					</el-button>
				</div>
			</div>
			<div id="comment">
				<el-input autosize placeholder="输入你的评论....." v-model="comment">
					<template slot="append">
						<el-button @click='postComment(0,0,comment,undefined)' style="float: right;color: #4CAF50;">评论</el-button>
					</template>
				</el-input>
				<div id="comment-count">
					{{article.commentCount}}条评论
					<el-divider></el-divider>
				</div>
				<div v-for="item in comments" :key='item.id' id="comment-info">
					<el-avatar :size='50' icon="el-icon-user-solid"></el-avatar>
					<div class="comment-name">
						{{item.username}}
					</div>
					<div class="comment-time time-color-1">
						<span style="font-size: 14px;">{{item.createtime | format}}</span>
					</div>
					<div class="comment-content">
						<span style="font-size: 16px;">{{item.content}}</span>
					</div>
					<div class="comment-reply">
						<el-link @click='item.isReply=!item.isReply' type="success" icon="el-icon-chat-dot-round">回复</el-link>
					</div>

					<div v-for="item2 in item.children" :key='item2.id' class="comment-reply comment-reply-reply">
						<span style="font-size: 16px;color: #00FF00;">{{item2.username}}@{{item.username}}:</span>
						<span style="font-size: 16px;">{{item2.content}}</span>
					</div>
					<el-input style="margin-top: 10px;" v-show="item.isReply" autosize placeholder="输入你的评论....." v-model="item.preComment">
						<template slot="append">
							<el-button @click='postComment(item.id,item.userid,item.preComment,item)' style="float: right;color: #4CAF50;">评论</el-button>
						</template>
					</el-input>
					<el-divider></el-divider>
				</div>
				<div id="comment-footer"></div>
			</div>
		</div>
		<div id="articleview-right">
		</div>
	</div>
</template>

<script>
	import tinymce from 'tinymce/tinymce'
	import 'tinymce/themes/silver/theme'
	import 'tinymce/icons/default'
	import Editor from '@tinymce/tinymce-vue'
	import 'tinymce/plugins/codesample'


	import {
		getCurrentArticle,
		postComment,
		updateComment,
		updateView
	} from '@/api/articleApi.js'
	import {
		Message
	} from 'element-ui'

	export default {
		components: {
			Editor
		},
		data() {
			return {
				articleId: this.$route.params.articleId,
				article: {
					title: '与标题',
					createtime: '2017-06-20',
					viewCount: '1232',
					commentCount: '13',
					content: 'asfds'
				},
				user: {
					name: 'h阿斯弗'
				},
				articleType: {
					id: 0,
					name: '生活'
				},
				labels: [{
					id: '',
					name: ''
				}],
				comments: [{
					id: 0,
					userid:0,
					createtime: 0,
					username: '',
					content: '',
					parentId: 0,
					children: [],
					touid: 0,
					isReply: false,
					preComment: ''
				}],
				isAuthor: false,
				init: {
					selector: '#preview',
					inline: true,
					language_url: `/tinymce/langs/zh_CN.js`,
					language: 'zh_CN',
					skin_url: '/tinymce/skins/ui/oxide',
					height: this.calcHeight(),
					width: this.calcWidth(),
					plugins: 'codesample',
					readonly: true
				},
				comment: ''
			}
		},
		methods: {
			calcWidth() {
				return '100%'
			},
			calcHeight() {
				return document.body.clientHeight / 2
			},
			to_write(id){
				this.$router.push({path:`/write/${id}`})
			},
			type_btn(id){
				this.$router.push({path:`/ArticleType/${id}`})
			},
			label_item_btn(typename){
				this.$router.push({path:`/ArticleLabel/${typename}`})
			},
			addComment(comment){
				if(comment.parentId==0){
					comment.children=[]
					this.comments.push(comment)
				}else{
					for(let v of this.comments){
						console.log(v)
						if(v.children==undefined)continue
						if(comment.parentId==v.id){
							v.children.push(comment)
							break
						}
					}
				}
			},
			postComment(parentId, touid, comment2,item) {
				const comment = {
					articleid: this.articleId,
					userid: this.$store.state.id,
					username: this.$store.state.name,
					createtime: new Date().format('yyyy-MM-dd hh:mm:ss'),
					content: comment2,
					parentId: parentId,
					touid: touid,
					isReply: false,
					preComment: ''
				}
				if(item)item.isReply=false
				postComment(comment).then(data => {
					console.log(data)
					
					if (data.data.code == 200) {
						Message({
							type: 'success',
							showClose: true,
							message: data.data.msg
						})
						comment.id=data.data.id;
						this.addComment(comment)
						updateComment(this.articleId).then(data => {
							if (data.data.code == 200) {
								this.article.commentCount = data.data.data
							}
						})
					} else {
						Message({
							type: 'error',
							showClose: true,
							message: data.data.msg
						})
					}
				}).catch(error => {
					Message({
						type: 'error',
						showClose: true,
						message: error
					})
				})
			}
		},
		created() {
			tinymce.init({})
			
		},
		mounted() {
			tinymce.editors['tinymce'].setMode('readonly')
			//获取当前的文章
			getCurrentArticle(this.articleId).then(data => {
				if (data.data.code == 200) {
					//console.log(data.data.article.userid,this.$store.state.id)
					this.isAuthor=data.data.article.userid==this.$store.state.id
					this.article = {
						
						title: data.data.article.title,
						createtime: data.data.article.createTime,
						viewCount: data.data.article.viewcount == undefined ? 0 : data.data.article.viewcount,
						commentCount: data.data.userComments.length,
						content: data.data.article.content,
						userId: data.data.article.userid,
					}
					this.user = {
						id: data.data.user.id,
						name: data.data.user.name
					}
					this.labels = []
					for (let v of data.data.article.articleTypeList) {
						this.labels.push({
							id: v.id,
							name: v.typename
						})
					}
					this.articleType = {
						id: data.data.articleCategory.id,
						name: data.data.articleCategory.name
					}
					this.comments = []
					let comment_data = data.data.userComments
					for (let i = 0; i < comment_data.length; i++) {
						if (comment_data[i].parentId == 0) {
							this.comments.push({
								id: comment_data[i].id,
								userid: comment_data[i].userid,
								createtime: comment_data[i].createTime,
								username: comment_data[i].user.name,
								content: comment_data[i].content,
								parentId: 0,
								children: [],
								touid: comment_data[i].touserid,
								isReply: false,
								preComment: ''
							})
						} else {
							for (let j = 0; j < comment_data.length; j++) {
								if (comment_data[i].parentId == comment_data[j].id) {
									if(this.comments[j]==undefined){
										this.comments.push({
											id: comment_data[j].id,
											userid: comment_data[j].userid,
											createtime: comment_data[j].createTime,
											username: comment_data[j].user.name,
											content: comment_data[j].content,
											parentId: 0,
											children: [],
											touid: comment_data[j].touserid,
											isReply: false,
											preComment: ''
										})
									}
									this.comments[j].children.push({
										id: comment_data[i].id,
										userid: comment_data[i].userid,
										createtime: comment_data[i].createTime,
										username: comment_data[i].user.name,
										content: comment_data[i].content,
										parentId: 0,
										touid: comment_data[i].touserid,
										preComment: ''
									})
								}
							}
						}
					}
					
				} else {
					Message({
						type: 'error',
						showClose: true,
						message: data.data.msg
					})
				}
			}).catch(error => {
			})
			
			//更新阅读数
			updateView(this.articleId).then(data=>{
				if(data.data.code==200){
					this.article.viewCount=data.data.data
				}
			}).catch(error=>{
				Message({
					type: 'error',
					showClose: true,
					message: error
				})
			})
		},
	}
</script>

<style>
	#ArticleView {
		width: 100%;
		margin-top: 50px;
		display: flex;

	}

	#articleview-left,
	#articleview-right {
		flex: 2;
	}

	#articleview-mid {
		height: 100%;
		flex: 4;
	}

	#article-title {
		font-size: 30px;
		font-weight: bolder;
	}

	#article-info {
		margin-top: 40px;
	}

	.divid-no-span {
		margin: 1px;
		left: -10px;
		width: calc(100% + 20px);
	}

	.time-color-1 {
		color: #757575;
	}

	.article-content {
		overflow: hidden;
	}

	.article-end,
	#comment {
		position: relative;
		left: 0;
		right: 0;
		bottom: -200px;
	}

	#article-cateogry,
	#article-type {
		line-height: 20px;
		margin-top: 10px;
		border-left: #448AFF solid 5px;
		border-radius: 2px;
		padding-left: 10px;
	}

	#comment {
		margin-top: 40px;
	}

	#comment-count {
		margin-top: 30px;
		font-size: 18px;
		font-weight: bold;
		font-style: italic;
	}

	.comment-name {
		display: inline-block;
		position: absolute;
		left: 60px;
	}

	.comment-time {
		display: inline-block;
		position: relative;
		left: 10px;
		bottom: 3px;
	}

	.comment-content {
		margin-top: 20px;
	}

	.comment-reply {
		margin-top: 10px;
	}

	.comment-reply-reply {
		height: 20px;
		border-left: 1px solid #448AFF;
		padding-left: 20px;
	}

	#comment-footer {
		height: 100px;
	}
</style>
