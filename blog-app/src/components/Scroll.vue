<template>
	<div style="overflow: auto;" id='Scroll'>
		<slot></slot>
	</div>
</template>

<script>
	export default {
		props:{
			isArriveBottom:{
				type:Boolean,
				default:false
			}
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll, true)
		},
		methods: {
			handleScroll() {
				let scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
				let clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
				let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
				if (!this.isArriveBottom) {
					if (scrollTop + clientHeight === scrollHeight) {
						this.$emit('onLoadArticle',this.isArriveBottom)
					}
				}
			}
		},
	}
</script>

<style>
</style>
