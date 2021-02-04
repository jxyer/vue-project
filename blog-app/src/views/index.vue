<template>
	<div id="index">
		<div class="index-left">
			1
		</div>
		<div class="index-mid">
			<Scroll :isArriveBottom='isLoading' @onLoadArticle='onLoadArticle'>			
				<article-item :articleList='articleList'>
				</article-item>
			</Scroll>
			<div v-show="isLoading" style="text-align: center;">加载中...</div>
			<div v-show="!isLoading" style="text-align: center;">~已经到底了~</div>
		</div>
		<div class="index-right">
			<el-card id="my-present" class="article-card">
				<div style="font-weight: bold; text-align: center; padding-bottom: 10px; border-bottom:1px solid #4CAF50;" id="index-right-name">
					MORTY
				</div>
				<div style="margin-top: 10px;" id='my-present-content'>
					<el-link :underline='false' icon="el-icon-location-outline">湖北</el-link>
					<el-link style='float: right;' :underline='false' icon="el-icon-s-custom">java工程师</el-link>

					<div style="display: flex; justify-content: center;margin-top: 20px;">
						<div id="icon-qq" style="margin-right: 100px;">
							<el-image class='qq-img' fit="contain" src="../tencent_QQ_119.65914786967px_1188698_easyicon.net.png"></el-image>
						</div>
						<div>
							<el-image class='qq-img' fit="contain" src="../Github_128px_1230449_easyicon.net.png"></el-image>
						</div>
					</div>
				</div>
			</el-card>
			<el-card id="hot-label-card" class="article-card">
				<div slot="header" id="hot-label-title" class="card-title">
					<span>最热标签</span>
					<el-button @click='label_btn' style="float: right; padding: 3px 0" type="text">查看更多</el-button>
				</div>
				<span v-for="o in labels" :key="o.id">
					<el-button @click='label_item_btn(o.typename)' style="margin-left: 10px; margin-bottom: 10px;" type='success' plain>
						{{o.typename}}
					</el-button>
				</span>
			</el-card>
			<el-card id="hot-type-card" class="article-card">
				<div slot="header" id="hot-label-title" class="card-title">
					<span>最热文章</span>
				</div>
				<div v-for="o in hotArticle" :key="o.id" class="text item">
					<span @click='hotArticle_btn(o.id)' class="hot-article-title">{{o.title}}</span>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	import {
		Message
	} from 'element-ui'

	import {
		getArticles,
		getHotLabel,
		getHotArticle
	} from '@/api/articleApi.js'

	import Scroll from '@/components/Scroll.vue'
	import ArticleItem from '@/components/ArticleItem.vue'

	export default {
		components: {
			Scroll,
			ArticleItem
		},
		data() {
			return {
				articleList: [{}],
				labels: [{}],
				hotArticle: [{}],
				isLoading: false,
				isFinish: false,
				page: 1
			}
		},
		methods: {
			init() {
				this.isLoading = false,
					this.isFinish = false,
					this.page = 1
			},
			label_btn() {
				this.$router.push({path:'/ArticleLabel'})
			},
			label_item_btn(typename){
				this.$router.push({path:`/ArticleLabel/${typename}`})
			},
			hotArticle_btn(id){
				this.$router.push({path:`/articleview/${id}`})
			},
			onLoadArticle() {
				if (!this.isFinish)
					this.isLoading = true
				else return
				this.page++
				getArticles(this.page, 5).then(data => {
					if (data.data.code == 200) {
						this.isLoading = false
						if (data.data.data.length < 5) this.isFinish = true
						for (let v of data.data.data)
							this.articleList.push(v)

					}
				})
			}
		},
		created() {
			this.init()
		},
		mounted() {
			getArticles(this.page, 9).then(data => {
				if (data.data.code == 200) {
					this.articleList = data.data.data
				}
			}).catch(error => {
				Message({
					type: 'error',
					showClose: true,
					message: error
				})
			})
			getHotLabel(5).then(data => {
				if (data.data.code == 200) {
					this.labels = data.data.data
				}
			}).catch(error => {
				Message({
					type: 'error',
					showClose: true,
					message: error
				})
			})
			getHotArticle().then(data => {
				if (data.data.code == 200) {
					console.log(data.data.data)
					this.hotArticle = data.data.data
				}
			}).catch(error => {
				Message({
					type: 'error',
					showClose: true,
					message: error
				})
			})
		}
	}
</script>

<style>
	#index {
		padding: 20px;
		width: 95%;
		display: flex;
		flex-direction: row;
	}

	#index>div {
		margin-left: 50px;
	}

	.index-left {
		flex: 1;
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
	.qq-img{
		width: 30px;height: 30px;
	}
</style>
