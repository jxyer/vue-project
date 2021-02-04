<template>
	<div id="ArticleLabel">
		<div id="articletype-content">
			<el-card @click.native='card_btn(label.typename)' v-for="label in labels" :key='label.id' :body-style="{ padding: '0px' }" shadow='never' class="article-type-item">
				<el-image style='width: 200px;height: 100px;' class="article-type-item-img" :src="label.image" fit="contain"></el-image>
				<el-row style="margin-top: 20px;">
					{{label.typename}}
				</el-row>
				<el-row style="margin-top: 20px;font-size: 12px;color: #969696;">
					{{label.a-1}}文章
				</el-row>
			</el-card>
		</div>
	</div>
</template>

<script>
import {
		getLabel,
	} from '@/api/articleLabelApi.js'
	import {
		Message
	} from 'element-ui'
	export default {
		data() {
			return {
				labels: [{}],
				url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
			}
		},
		methods:{
			card_btn(typename){
				this.$router.push({path:`/ArticleLabel/${typename}`})
			}
		},
		mounted() {
			getLabel().then(data => {
				if (data.data.code == 200) {
					this.labels=data.data.data
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
	#ArticleLabel {
		margin: 40px;
		width: 90%;
	}

	#articletype-content {
		width: 90%;
		height: 100%;
		margin-left: 10%;
		margin-right: 10%;
		
	}

	.article-type-item {
		display: inline-block;
		text-align: center;
		width: 200px;
		height: 200px;
		margin-right: 50px;
		margin-bottom: 30px;
		cursor:pointer;
	}

	.article-type-item-img {
		width: 100%;
	}
</style>