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
		<view class="change">
			<view class="score">
				积分：${{share.price}}
			</view>
			
			<view @click="exchange" class="change-btn" >
				兑换
			</view>
		</view>
	</view>

</template>

<script>
import { get, request } from '@/utils/request';
import { SHARE_URL, EXCHANGE_URL, USER_URL } from '@/utils/api';
export default {
	data() {
		return {
			share: null,
			wxNickname: ''
		};
	},
	onLoad(option) {
		let id = option.id;
		this.getShare(id);
	},
	methods: {
		async getShare(id) {
			let res = await get(SHARE_URL + '/' + id);
			console.log(res.data.share);
			this.share = res.data.share;
			this.wxNickname = res.data.wxNickname;
		},
		exchange() {
			console.log('资源id：' + this.share.id);
			console.log('用户id：' + uni.getStorageSync('user').id);
			request(EXCHANGE_URL, 'POST', {
				userId: uni.getStorageSync('user').id,
				shareId: this.share.id
			}).then(res => {
				console.log(JSON.stringify(res) + '>>>>>>>>>>>>>');
				if (res.succ === true) {
					uni.showToast({
						title: '兑换成功',
						duration: 2000
					});
					//重新请求用户数据
					get(USER_URL + '/' + uni.getStorageSync('user').id).then(res => {
						//移除原有用户缓存数据，存入新的数据
						uni.removeStorageSync('user');
						uni.setStorageSync('user', res.data);
						//跳回首页
						uni.switchTab({
							url: '/pages/tabbar/home/home'
						});
					});
				}
			});
		}
	}
};
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
