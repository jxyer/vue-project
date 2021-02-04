<template>
	<div class="ArticleItem">
		<el-card @click.native='toArticle(item.id)' v-for="item in articleList" :key="item.id" :body-style="{padding:'15px'}"
		 shadow="hover" class="article-card">
			<div class="article-card-title">
				<el-row>
					<el-col :span="20">
						{{item.title}}
					</el-col>
					<el-col :span="4">
						<el-row>
							<el-col :span="12">
								<div class="article-card-title-view">
									<i class="el-icon-view"></i>
									{{item.viewcount==null?0:item.viewcount}}
								</div>
							</el-col>
							<el-col :span="12">
								<div class="article-card-title-comment">
									<i class="el-icon-chat-dot-square"></i>
									{{item.userComments==undefined?0:item.userComments.length}}
								</div>
							</el-col>
						</el-row>
					</el-col>
				</el-row>
			</div>
			<div class="article-card-content">
				{{item.summary}}
			</div>
			<div class="article-card-foot">
				<el-row>
					<el-col :span="4">
						<div class="article-card-foot-name">
							<i style="margin-right: 2px;" class="el-icon-user"></i>
							{{item.user==undefined?'用户':item.user.name}}
						</div>
					</el-col>
					<el-col :span="16">
						<div style="width: 100px;height:1px ;"></div>
						<div class="article-card-foot-label">
							<el-tag style="margin-left: 5px;" v-for='label in item.articleTypeList' :key='label.id' size="mini" effect='plain'
							 type="success">{{label.typename}}</el-tag>
						</div>
					</el-col>
					<el-col :span="4">
						<span>{{new Date(item.updateTime).format('M月d日 hh:mm:ss')}}</span>
					</el-col>
				</el-row>

			</div>
		</el-card>
	</div>
</template>

<script>
	export default{
		props:{
			articleList:Array
		},
		methods:{
			toArticle(id) {
				this.$router.push({
					path: `/articleview/${id}`
				});
			},
		}
	}
</script>

<style>
</style>
