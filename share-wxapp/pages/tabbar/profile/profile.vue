<template>
	<view class="container">
		<view v-if="!user" class="box">
			<img src="https://i.loli.net/2020/10/10/mFxrTZNwPG6CBta.png" class="avatar" />
			<p class="info">登录，享受技术之旅吧！</p>
			<button open-type="getUserInfo" @getuserinfo="bindGetUserInfo" @click="appLogin">微信账号登录</button>
		</view>
		<view v-else class="box">
			<img :src="user.avatarUrl" class="avatar" />
			<p class="info">{{ user.wxNickname }}</p>
			<p class="info">当前积分：{{ user.bonus }}</p>
			<view class="center">
				<view class="qiandao xy-center" @click="signIn()">
				签到
			</view>
			</view>
			<view class="my-view mar">
				我的兑换
			</view>
			<view class="my-view mar">
				积分明细
			</view>
			<view class="my-view mar">
				我的投稿
			</view>
			<button @click="logout">退出</button>
		</view>
	</view>
</template>

<script>
import { request } from '@/utils/request';
import { LOGIN_URL,SIGN_URL } from '@/utils/api';
export default {
	data() {
		return {
			user: []
		};
	},
	onLoad() {
		if (uni.getStorageSync('user')) {
			this.user = uni.getStorageSync('user');
		}
	},
	methods: {
		bindGetUserInfo(e) {
			let platform = uni.getSystemInfoSync().platform;
			//微信开发者工具调试适用
			if (platform === 'devtools') {
				//此处e.mp事件适用于mini program小程序
				if (e.mp.detail.rawData) {
					//用户按了允许授权按钮
					this.wxLogin(e);
				} else {
					//用户按了拒绝按钮
					uni.showToast({
						title: '授权失败'
					});
				}
			}
		},
		wxLogin(e) {
			let self = this;
			let userInfo = e.mp.detail.userInfo;
			// console.log(userInfo);
			wx.login({
				success(res) {
					// console.log(res);
					if (res.errMsg === 'login:ok') {
						let loginDTO = {
							loginCode: res.code,
							wxNickname: userInfo.nickName,
							avatarUrl: userInfo.avatarUrl
						};
						//统一调用登录方法
						self.userLogin(loginDTO);
					} else {
						console.log('登录失败！' + res.errMsg);
					}
				}
			});
		},
		appLogin() {
			let self = this;
			uni.login({
				provider: 'weixin',
				success: function(loginRes) {
					uni.getUserInfo({
						provider: 'weixin',
						success: infoRes => {
							//统一的登录请求参数
							let loginDTO = {
								openId: infoRes.userInfo.openId,
								wxNickname: infoRes.userInfo.nickName,
								avatarUrl: infoRes.userInfo.avatarUrl
							};
							//统一调用登录方法
							self.userLogin(loginDTO);
						}
					});
				}
			});
		},
		userLogin(loginDTO) {
			console.log(loginDTO);
			request(LOGIN_URL, 'POST', loginDTO).then(res => {
				uni.showToast({
					title: '登录成功',
					duration: 1000
				});
				console.log(JSON.stringify(res.data))
				this.user = res.user
				uni.setStorageSync('user', res.user)
				console.log("user:"+JSON.stringify(res.user))
				uni.setStorageSync('token', res.token)
			});
		},
		logout() {
			this.user = null
			uni.clearStorageSync()
		},
		signIn(){
			uni.request({
			    url: SIGN_URL, //仅为示例，并非真实接口地址。
			    data: {
			        userId: this.user.id,
			    },
				method: 'POST',
			    header: {
			        'X-Token': uni.getStorageSync('token').token //自定义请求头信息
			    },
			    success: (res) => {
			        console.log("fefefse:"+JSON.stringify(res.data))
					if(res.data.succ === true){
						uni.showToast({
							title:res.data.msg
						});
					} else {
						uni.showToast({
							title:res.data.msg
						});
					}
			    }
			});
		}
	}
};
</script>

<style lang="scss">
.center {
	width: 100%;
	display: flex;
	justify-content: center;
}
.qiandao {
	width: 20%;
	height: 35px;
	color: #FFFFFF;
	background-color: #EC544D;
	padding-top: 1%;
	padding-bottom: 1%;
	border-radius: 5%;
}
.my-view {
	width: 90%;
	height: 40px;
	display: flex;
	justify-content: left;
	align-items: center;
	border-bottom: 1px solid #e2e2e2;
}
.container {
	text-align: center;
	.box {
		margin-top: 20px;
		.avatar {
			width: 150px;
			height: 150px;
			border-radius: 50%;
		}
		.info {
			font-size: 16px;
			margin-bottom: 30px;
		}
	}
}
button {
	margin-top: 20px;
	background-color: #EC544D;
	width: 80%
}
</style>
