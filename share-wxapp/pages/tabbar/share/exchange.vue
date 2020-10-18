<template>
	<view class="share_detail">
		<view class="share_title white mar">
			<view class="share-title mar">{{share.title}}</view>
			<view class="title-content white xy-center mar">
				<view class="three">
					作者：{{share.author}}
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
		<view class="change">
			<view class="score">
				积分：${{share.price}}
			</view>
			
			<view @click="exchange()" class="change-btn" >
				兑换
			</view>
		</view>
		<!-- <view class="share_content white mar">
			所需积分  ${{share.price}}
		</view>
		<button @click="exchange()">兑换</button> -->
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
			exchange() {
				console.log(JSON.stringify(this.share))
				console.log("fefefef:"+this.share.userId)
				uni.request({
				    url: EXCHANGE_URL, //仅为示例，并非真实接口地址。
				    data: {
				        userId: this.share.userId,
				        shareId:this.share.id
				    },
					method: 'POST',
				    header: {
				        'X-Token': uni.getStorageSync('token').token //自定义请求头信息
				    },
				    success: (res) => {
				        uni.navigateTo({
				        	url: '../share/exchangeSuccess?id='+this.share.id
				        });
				    }
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
	width: 49%;
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
	width: 75%;
	border-radius: 8px;
}
</style>
