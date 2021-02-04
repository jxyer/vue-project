<template>
	<div id="Write">
		<div id="write-content">
			<div id="write-left">
				<el-card shadow="never" :body-style="{padding:'0px'}" class="box-card">
					<div class="preview-title preview-title-left" slot="header">
						<span>编辑内容</span>
						<el-link @click='clear' style="float: right;" type="text" icon="el-icon-circle-close">取消</el-link>
						<el-link @click='getArticle' style="float: right; margin-right: 10px;" icon='el-icon-share' type="text">发布</el-link>
					</div>
					<Editor id="tinymce" v-model="tinymceHtml" :init="init"></Editor>
					<div id="editview">
					</div>
				</el-card>
			</div>
			<div id="divid">
				<el-divider direction="vertical"></el-divider>
			</div>
			<div id="write-right">
				<el-card shadow="never" class="box-card">
					<div class="preview-title" slot="header">
						<span>预览内容</span>
					</div>
					<div class="preview-conten-content">
						<Editor id="tinymce2" v-model="tinymceHtml" :init="init2"></Editor>
						<div id="preview">
						</div>
					</div>
				</el-card>
			</div>
		</div>
		<el-dialog title="完善信息" :visible.sync="dialogVisible" center>
			<div>
				<el-form :model="form">
					<el-form-item>
						<el-input v-model="form.title" placeholder="请输入标题" autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item>
						<el-input type="textarea" :rows="2" placeholder="请输入摘要" v-model="form.summary">
						</el-input>
					</el-form-item>
					<el-form-item label="文章分类">
						<el-select v-model="article_type" placeholder="请选择">
							<el-option v-for="item in article_types" :key="item.value" :label="item.label" :value="item.value">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="文章类别">
						  <el-checkbox-group v-model="checked_label">
						    <el-checkbox v-for="article_label in article_labels" :label="article_label.label" :key="article_label.id">{{article_label.label}}</el-checkbox>							  
						  </el-checkbox-group>
					</el-form-item>
				</el-form>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="onSubmit">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import tinymce from 'tinymce/tinymce'
	import 'tinymce/themes/silver/theme'
	import 'tinymce/icons/default'
	import "tinymce/plugins/code"
	import "tinymce/plugins/preview"
	import 'tinymce/plugins/wordcount' // 字数统计插件
	import 'tinymce/plugins/codesample'
	import 'tinymce/plugins/autosave'
	import 'tinymce/plugins/save'
	import 'tinymce/plugins/image'
	import 'tinymce/plugins/imagetools'
	import 'tinymce/plugins/link'
	import 'tinymce/plugins/quickbars'
	import 'tinymce/plugins/autoresize'
	import Editor from '@tinymce/tinymce-vue'
	
	import {getArticleInfo,addArticleInfo} from '@/api/login.js'
	import {getCurrentArticle} from '@/api/articleApi.js'
	import {Message} from 'element-ui'
	
	export default {
		name: 'tinymce',
		props:{
			tinymceHtml:String
		},
		data() {
			return {
				articleId:this.$route.params.writeId,
				dialogVisible: false,
				loading:true,
				form: {
					title: '',
					summary:''
				},
				summary:'',
				article_types:[
					{
						value:'1',
						label:'sad'
					},
				],
				article_type:'',
				article_labels:[],
				checked_label:[],
				init: {
					selector: '#editview',
					inline: false,
					language_url: `/tinymce/langs/zh_CN.js`,
					language: 'zh_CN',
					skin_url: '/tinymce/skins/ui/oxide',
					height: this.calcHeight(),
					width: this.calcWidth(),
					plugins: 'autoresize code wordcount codesample autosave save image imagetools link quickbars',
					browser_spellcheck: true, // 拼写检查
					toolbar: 'save bold italic underline strikethrough code restoredraft link| fontsizeselect | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent blockquote | undo redo | link unlink image | removeformat',
					branding: false,
					toolbar_mode: 'floating',
					autosave_interval: '10s', //存稿的时间间隔
					autosave_restore_when_empty: true,
				},
				init2: {
					selector: '#preview',
					inline: true,
					language_url: `/tinymce/langs/zh_CN.js`,
					language: 'zh_CN',
					skin_url: '/tinymce/skins/ui/oxide',
					height: this.calcHeight(),
					width: this.calcWidth(),
					plugins:'codesample',
					browser_spellcheck: true, // 拼写检查
					readonly: true
				}
			}
		},
		mounted() {
			tinymce.init({})
			tinymce.editors['tinymce2'].setMode('readonly');
			
			if(this.articleId){
				getCurrentArticle(this.articleId).then(data=>{
					if(data.data.code==200){
						this.tinymceHtml=data.data.article.content
					}else{
						Message({
							type:'error',
							showClose:true,
							message:data.data.msg
						})	
					}
				}).catch(error=>{
					Message({
						type:'error',
						showClose:true,
						message:error
					})	
				})
			}
		},
		methods: {
			calcWidth() {
				return '100%'
			},
			calcHeight() {
				return document.body.clientHeight / 2
			},
			clear(){
				this.$router.go(-1)
			},
			getArticle(){
				this.dialogVisible=true
				getArticleInfo().then(data=>{
					if(data.data.code==200){
						this.article_types=[]
						this.article_labels=[]
						for(let item of data.data.articleCategories){
							this.article_types.push(
							{
								value:item.id,
								label:item.name
							})
						}
						for(let item of data.data.articleTypes){
							this.article_labels.push(
							{
								id:item.id,
								label:item.typename
							})
						}
					}else{
						Message({
							type:'error',
							showClose:true,
							message:data.data.msg
						})	
					}
				}).catch(error=>{
					Message({
						type:'error',
						showClose:true,
						message:error
					})	
				})
			},
			onSubmit() {
				let Article={
					title:this.form.title,
					content:this.tinymceHtml,
					summary:this.form.summary
				}
				addArticleInfo(Article,this.article_type,this.checked_label)
				.then(data=>{
					if(data.data.code==200){
						let path=`articleview/${data.data.article_id}`
						this.$router.push({path:path})
						Message({
							type:'success',
							showClose:true,
							message:data.data.msg
						})
					}else{
						Message({
							type:'error',
							showClose:true,
							message:data.data.msg
						})	
					}
				}).catch(error=>{
					Message({
						type:'error',
						showClose:true,
						message:error
					})	
				})
			}
		},
		components: {
			Editor
		},
	}
</script>

<style>
	#Write {
		width: 100%;
		height: calc(100%-3.75rem);
	}

	#write-head {
		margin: 10px 30% 10px 30%;
		text-align: center;
	}

	#write-content {
		margin: 20px;
		display: flex;
		flex-direction: row;
		height: calc(100%-3.75rem);
	}

	#write-left,
	#write-right {
		flex: 20;
		width: 50%;
		height: 100%;
	}

	#divid {
		flex: 1;
		height: 100%;
	}

	#write-left {
		margin-right: 20px;
	}

	#write-right {}

	.el-card__header {
		background-color: #4CAF50;
	}

	.preview-title {
		color: #FFFFFF;
		text-align: center;
	}

	.preview-title-left {
		text-align: left;
	}

	.preview-conten-content {}
</style>
