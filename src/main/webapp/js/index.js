var app = new Vue({
		el : '#app-main',
		data : {
			errorMsg:'',
			userInfo : {
				user : '',
				pwd : '',
				auth : false,
				authMsg : ''
			},
			viewA :[],
			viewB : {
				isLoading:false,
				isActive:false,
				id:0,
				name:'',
				ctime:'',
				cnt:0,
				mstatestr:''
			}
		},
		methods : {
			authUserShow : function() {
				$('#auth-Modal').modal('show');
				this.userInfo.authMsg = '';
			},
			authUser:function(event){
				var _this = this;
				$(event.target).button('loading');
				var data = {
						user:_this.userInfo.user,
						pwd:_this.userInfo.pwd
				};
				$.getJSON('access/auth.do',data,function(res){
					$(event.target).button('reset');
					if(res['result'] == 'SUCCESS'){
						_this.userInfo.auth = true;
						_this.userInfo.pwd = '';
						$('#auth-Modal').modal('hide');
						app.loadInfo();
					} else {
						_this.userInfo.auth = false;
						_this.userInfo.authMsg = res['msg'];
					}
				})
			},
			loadInfo:function(event){
				var _this = this;
				if(event){
					$(event.target).button('loading');
				}
				_this.viewB.isLoading=false;
				_this.viewB.isActive=false;
				$.getJSON('access/viewA.do',function(res){
					if(res['result'] == 'SUCCESS'){
						_this.viewA = res['rows'];
					} else {
						_this.errorMsg = res['msg'];
					}
					if(event){
						$(event.target).button('reset');
					}
					_this.viewB = {
						isLoading:false,
						isActive:false,
						id:0,
						name:'',
						ctime:'',
						cnt:0,
						mstatestr:''
					};
				});
			},
			
			showViewB:function(name){
				var _this = this;
				_this.viewB.isLoading=true;
				_this.viewB.isActive=false;
				var url = encodeURI('access/viewB.do?name='+name+'&t='+new Date().getTime());
				$.getJSON(url,function(res){
					if(res['result'] == 'SUCCESS'){
						$.extend(_this.viewB, res['rows']);
					} else {
						_this.errorMsg = res['msg'];
					}
					_this.viewB.isLoading=false;
					_this.viewB.isActive=true;
				});
			},
			viewNextB:function(){
				var _this = this;
				_this.viewB.isLoading=true;
				_this.viewB.isActive=false;
				var url = encodeURI('access/viewNextB.do?name='+_this.viewB.name+'&id='+_this.viewB.id+'&t='+new Date().getTime());
				$.getJSON(url,function(res){
					if(res['result'] == 'SUCCESS'){
						$.extend(_this.viewB, res['rows']);
					} else {
						_this.errorMsg = res['msg'];
					}
					_this.viewB.isLoading=false;
					_this.viewB.isActive=true;
				});
			},
			viewPrevB:function(){
				var _this = this;
				_this.viewB.isLoading=true;
				_this.viewB.isActive=false;
				var url = encodeURI('access/viewPrevB.do?name='+_this.viewB.name+'&id='+_this.viewB.id+'&t='+new Date().getTime());
				$.getJSON(url,function(res){
					if(res['result'] == 'SUCCESS'){
						$.extend(_this.viewB, res['rows']);
					} else {
						_this.errorMsg = res['msg'];
					}
					_this.viewB.isLoading=false;
					_this.viewB.isActive=true;
				});
			}
		}
	});

