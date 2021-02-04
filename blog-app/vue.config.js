module.exports = {
	devServer: {
		proxy: {
			'/api': {
				target:'http://localhost:8888',
				changeOrigin: true
			}
		}
	},
	lintOnSave:false //关闭eslint检查
}
