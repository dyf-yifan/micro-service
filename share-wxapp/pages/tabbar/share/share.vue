<template>
	<view class="share_detail">
		<view class="share_title white mar">
			<view class="share-title mar">{{share.title}}</view>
			<view class="title-content white xy-center mar">
				<view class="three">
					作者：{{share.author}}
				</view>
				<view class="three">
					发布人：佚名
				</view>
				<view class="three">
					下载数：{{share.buyCount}}
					</view>
			</view>
		</view>
		<view class="grey">
			
		</view>
		<view class="share_content white mar">
			{{share.summary}}
		</view>
		<view class="share_content white mar">
			下载地址   {{share.downloadUrl}}
		</view>
		<button @click="paste(share.downloadUrl)">复制下载地址</button>
	</view>
</template>

<script>
import {get} from '../../../utils/request.js';
import {SHARE_URL} from '../../../utils/api.js';
	export default {
		data() {
			return {
				share: [],
				id: []
			};
		},
		onLoad(option) {
		  let id = option.id;
		  this.id = id
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
</style>
