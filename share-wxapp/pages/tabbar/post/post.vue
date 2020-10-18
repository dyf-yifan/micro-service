<template>
	<view class="container">
		<view class="contribute" v-for="(item,index) in contributes" :key="index">
			<!-- <image :src="item.cover" mode=""class="img"></image> -->
			<view class="top-con">
				<view class="titel">
					{{item.title}}
				</view>
				<view class="jifen">
					积分：{{item.price}}
				</view>
			</view>
			<view class="con-author">
				作者：{{item.author}}
			</view>
			<view class="jianjie">
				简介：{{item.summary}}
			</view>
			<view class="url">
				下载链接：{{item.downloadUrl}}
			</view>
			
			
		</view>
		<button @click="contribute">投稿</button>
	</view>
</template>
<script>
import { request, get } from '@/utils/request';
import { USER_CONTRIBUTION_URL } from '@/utils/api';
export default {
	data() {
		return {
			contributes:[]
		};
	},
	onLoad(){
		this.getContributes()
	},
	methods: {
		
		contribute() {
			uni.navigateTo({
				url: '../share/contribute?userId='+uni.getStorageSync('user').id
			})
		},
		async getContributes(){
			let res = await get(USER_CONTRIBUTION_URL+'/'+uni.getStorageSync('user').id)
			this.contributes = res.data
			console.log(JSON.stringify(this.contributes))
		}
	}
};
</script>
<style>
.contribute {
	width: 90%;

	margin: 0 auto;
	margin-top: 5%;
	padding-top: 2%;
	padding-bottom: 2%;
	padding-left: 2%;
	padding-right: 2%;
	box-shadow:6px 6px 2px 1px rgba(184, 195, 231, 0.2);
	align-items: center;
}
.img {
	height: 50px;
	width: 50px;
	border-radius: 10px;
}
.top-con {
	height: 30%;
	display: flex;
	flex-wrap: nowrap;
	align-items: center;
	justify-content: space-between;
}
.con-author {
	height: 15%;
	align-items: center;
}
.jianjie {
	height: 15%;
	align-items: center;
}
.url {
	height: 40%;
	color: #8bbeff;
	font-size: 17px;
	align-items: center;
}
.titel {
	width: auto;
	font-size: 19px;
}
.jifen {
	width: auto;
	font-size: 17px;
}
button {
	color: #ec8173;
	background-color: #FFFFFF;
}
</style>
