<template>
	<view class="con mar">
		<view class="intro" >
			注意：投稿审核通过会有积分奖励:资源被下载会有积分奖励;提交的资源不得包括广告、侵权信息，百度网盘的话一定要带地址哦
		</view>
	    <view class="con-top">
		    <radio-group name="original" @change="radioChange" class="radio-group">
				<label>
					<radio value="true" color="#EC544D" checked="checked" /><text>原创</text>
					<radio value="false" color="#EC544D" class="radio" /><text>转载</text>
				</label>
			</radio-group>
	    </view>
		<view class="one">
			标题
			<input type="text" class="one-text" v-model="title">
		</view>
		<view class="one">
			作者
			<input type="text" class="one-text" v-model="author">
		</view>
		<view class="one">
			积分
			<input type="text" class="one-text" v-model="price">
		</view>
		<view class="one">
			简介
			<input type="text" class="one-text" v-model="summary">
		</view>
		<view class="one">
			下载地址
			<input type="text" class="one-text" v-model="downloadUrl">
		</view>
		<button @click="confirm()">提交</button>
	</view>
</template>

<script>
	import {CONTRIBUTE_URL} from '../../../utils/api.js';
	import { request } from '@/utils/request';
	export default {
		data() {
			return {
				title: null,
				userId:null,
				author:null,
				downloadUrl:null,
				isOriginal:true,
				price:null,
				summary:null,
				title:null,
				cover:'https://wanghuanle.oss-cn-beijing.aliyuncs.com/avatar/a%20%2882%29.jpg'
			};
		},
		onLoad(option){
			this.userId = option.userId
		},
		methods: {
			radioChange: function(evt) {
					this.isOriginal = evt.detail.value
			},
			confirm(){
				request(CONTRIBUTE_URL, 'POST', {
					userId: this.userId,
					author: this.author,
					downloadUrl: this.downloadUrl,
					isOriginal: this.isOriginal,
					price: this.price,
					summary: this.summary,
					title: this.title,
					cover:this.cover
				}).then(res =>{
					console.log("res:"+JSON.stringify(res))
					if(res.succ){
						uni.showToast({
							duration:1000,
							title:"投稿成功"
						})
						uni.navigateTo({
							url: '../home/home'
						});
					} else{
						uni.showToast({
							duration:1000,
							title:"投稿失败"
						})
					}
				});
				
			}
		}
	};
	
</script>

<style>
.con-top {
	margin-top: 10px;
}
.radio-group{
	width: 90%;
	margin: 0 auto;
}
.radio {
	padding-left: 5%;
}
.con {
	width: 95%;
	height: auto;
}
.intro {
	/* margin-top: 10px; */
	width: 100%;
	color: #808080;
	margin: 0 auto;
	margin-left: 2%;
}
.one {
	width: 90%;
	height: 50px;
	margin: 0 auto;
	border-bottom: 1px solid #ebebeb;
	margin-top: 6px;
	display: flex;
	align-items: center;
}
.one-text{
	margin-left: 4%;
}
button {
	width: 80%;
	margin-top: 30px;
	color: #EC544D;
}
</style>
