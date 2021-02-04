<template>
	<div id="Login">
		<el-card class="login-card">
			<el-row>
				<el-col class="login-left" :span="12">
					{{content}}
				</el-col>
				<el-col class="login-right" :span="12">
					<el-form :rules="rules" ref="formLabelAlign" label-position="right" :model="formLabelAlign">
						<el-form-item label="用户名" prop="name">
							<input type="text" placeholder="请输入内容" class="login-input" v-model="formLabelAlign.name">
						</el-form-item>
						<el-form-item label="密码" prop="password">
							<input type="password" placeholder="请输入内容" class="login-input" v-model="formLabelAlign.password">
						</el-form-item>
						<el-form-item>
							<el-button type="primary" size='mini' @click="onSubmit('formLabelAlign')">立即登录</el-button>
							<el-button type="primary" size='mini' @click="onRegister">立即注册</el-button>
						</el-form-item>
					</el-form>
				</el-col>
			</el-row>
		</el-card>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				formLabelAlign: {
					name: '',
					password: ''
				},
				rules: {
					name: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}]
				},
				content: '欢迎登录'
			}
		},
		methods: {
			onSubmit(formName) {
				let that = this
				this.$refs[formName].validate((valid) => {
					if (valid) {
						that.$store.dispatch('logins', that.formLabelAlign)
						.then(()=>{
							that.$router.replace({path:'/'});
						}).catch(error=>{
							that.$message({message: error, type: 'error', showClose: true});
						})
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			onRegister() {
				this.$router.push({
					name: 'Register'
				})
			}
		}
	}
</script>

<style>
	#Login {
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.login-card {
		width: 50rem;
		height: 25rem;
		display: flex;
		flex-direction: column;
	}

	.login-left {
		height: 22.5rem;
		background-color: #42B983;
		color: #FFFFFF;
		font-size: 22px;
		text-align: center;
		line-height: 22.5rem;
	}

	.login-right {
		height: 22.5rem;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.login-input {
		border: none;
		outline: none;
		border-bottom: 1px solid #4CAF50;
		padding-bottom: 5px;
		width: 100%;
	}
</style>
