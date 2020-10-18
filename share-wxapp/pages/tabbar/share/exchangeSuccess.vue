<template>
	<view class="success mar">
		<view class="title">
			{{share.title}}
		</view>
		<view class="link">
			链接:{{share.downloadUrl}}
		</view>
		<button @click="paste(share.downloadUrl)">复制下载地址</button>
	</view>
</template>

<script>
	import {get} from '../../../utils/request.js';
	import {SHARE_URL,EXCHANGE_URL} from '../../../utils/api.js';
		export default {
			data() {
				return {
					share: [],
					id: null
				};
			},
			onLoad(option) {
			  this.id = option.id;
			  this.getDetail();
			  
			},
			methods:{
				async getDetail() {
					// share详情
					let res = await get(SHARE_URL+'/'+this.id);
					this.share = res.data.share;
				},
				paste(value) {
							uni.setClipboardData({
								data: value
							}),
							uni.showToast({
								title:'下载地址已复制'
							});
				}
			},
				
		}
</script>

<style>
.success {
	width: 90%;
}
.title {
	padding-top: 3%;
	padding-bottom: 3%;
	font-size: 20px;
}
.link {
	color: #666666;
	font-size: 16px;
	padding-bottom: 3%;
}
button {
	background-color: rgb(103, 184, 54);
	color: #FFFFFF;
}
</style>
