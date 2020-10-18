<template>
	<view class="success mar">
		<view class="title">
			{{share.title}}
		</view>
		<image :src="share.cover"></image>
		<view class="link">
			下载地址：{{share.downloadUrl}}
		</view>
		<button @click="paste">复制下载地址</button>
	</view>
</template>

<script>
import {get} from '../../../utils/request.js';
import {SHARE_URL} from '../../../utils/api.js';
	export default {
		data() {
			return {
				share: null
				}
			},
		methods:{
			async getDetail(id) {
				// share详情
				let res = await get(SHARE_URL+'/'+id);
				console.log(this.data.share);
				this.share = res.data.share;
			},
			paste() {
				uni.setClipboardData({
					data: this.share.downloadUrl,
					success:function(){
						uni.showToast({
							title:'下载地址已复制'
						})
					}
				})
			}
		},
			onLoad(option) {
			  let id = option.id;
			  console.log(id);
			  this.getDetail(id);
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
.white {
	padding-top: 2%;
	padding-bottom: 2%;
}
.mar {
	margin: 0 auto;
}
.share_detail {
	width: 100%;
	height: 100%;
}
.share_title {
	flex-wrap: wrap;
	width: 100%;
}
.share-title {
	width: 92%;
	font-size: 38upx;
}
.title-content {
	width: 92%;
	display: flex;
	flex-wrap: nowrap;
	font-size: 32upx;
}
.three {
	width: 33.3%;
}
.xy-center {
	display: flex;
	justify-content: center;
	align-items: center;
}
.grey {
	height: 25upx;
	background-color: #dfdfdf;
}
.share_content {
	width: 92%;
	height: 60%;
	font-size: 30upx;
	color: #666666;
}
.change {
	display: flex;
	padding-left: 55%;
	justify-content: right;
	align-items: center;
	margin-top: 100%;
}
.score {
	width: 100%;
	display: flex;
	align-items: center;
}
.change-btn {
	background-color: rgb(236, 84, 77);
	padding-top: 10%;
	padding-bottom: 10%;
	display: flex;
	justify-content: center;
	color: #FFFFFF;
	width: 65%;
}
</style>
