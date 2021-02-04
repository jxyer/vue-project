<template>
	<div id="Register">
		<el-card class="login-card">
			<el-row>
				<el-col class="login-left" :span="12">
					{{content}}
				</el-col>
				<el-col class="login-right" :span="12">
					<el-form :rules="rules" ref="formLabelAlign" label-position="right" :model="formLabelAlign">
						<el-form-item label="用户名" prop="name">
							<input placeholder="请输入内容" class="login-input" v-model="formLabelAlign.name">
						</el-form-item>
						<el-form-item label="密码" prop="password">
							<input placeholder="请输入内容" class="login-input" v-model="formLabelAlign.password">
						</el-form-item>
						<el-form-item>
							<el-button type="primary" size='mini' @click="onRegister('formLabelAlign')">立即注册</el-button>
						</el-form-item>
					</el-form>
				</el-col>
			</el-row>
		</el-card>
	</div>
</template>

<script>
	import {
		register
	} from '@/api/login.js'
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
				content: '欢迎注册'
			}
		},
		methods: {
			onRegister(formName) {
				let that = this
				this.$refs[formName].validate((valid) => {
					if (valid) {
						register(that.formLabelAlign.name, that.formLabelAlign.password)
							.then((data) => {
								console.log(data)
								if (data.data.code == 200) {
									that.$message({
										message: data.data.msg,
										type: 'success',
										showClose: true
									});
									that.$router.go(-1)
								} else {
									that.$message({
										message: data.data.msg,
										type: 'error',
										showClose: true
									});
								}
							}).catch(error => {
								console.log(error)
								that.$message({
									message: error,
									type: 'error',
									showClose: true
								});
							})
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			}
		}
	}
</script>

<style>
	#Register {
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
</style>
