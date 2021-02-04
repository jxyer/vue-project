<template>
	<div id="Editor">
		<Editor v-model="html" :init="init"></Editor>
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
	
	export default{
		name:'ArticleEditor',
		components: {
			Editor
		},
		data(){
			return{
				html:this.tinymceHtml
			}
		},
		props:{
			tinymceHtml:{
				type:String,
				default:''
			},
			init:Object,
			readonly:String
		},
		mounted() {
			tinymce.init({})
			//tinymce.editors['tinymce2'].setMode(this.readonly);
		},
		watch:{
			tinymceHtml(val){
				this.html=val
			}
		}
	}
</script>

<style>
</style>
