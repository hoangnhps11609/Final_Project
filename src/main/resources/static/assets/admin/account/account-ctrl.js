app.controller("account-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};

		$scope.ODitems = [];
	
	$scope.initialize = function() {
		//load accounts
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
		});
	}
	
		$scope.statistic = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/accounts/get/${statistic.from}`).then(resp => {
			$scope.items = resp.data;
			$(".nav-tabs a:eq(2)").tab('show');
		}).catch(error => {
	
			console.log("Error", error);
		});
	}

	//Khởi tạo
	$scope.initialize();

	//Xóa form	
	$scope.reset = function() {
		$scope.form = {
			photo: 'user.png'
		};
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show')
	}

	//Thêm account mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {
			alert("Lỗi thêm sản phẩm");
			console.log("Error", error);
		});
	}

	//update sản phẩm mới
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);

		});
	}
	
	
	//Xóa sản phẩm mới
	$scope.delete = function(item) {
		$http.delete(`/rest/accounts/${item.username}`).then(resp => {
			var index = $scope.items.findIndex(p => p.username == item.username);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa  thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);

		});
	}

	//Upload hình
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		}, first(){
			this.page = 0;
		}, prev(){
			this.page--;
			if(this.page<0){
				this.last();
			}
		}, next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		}, last(){
			this.page = this.count-1;
		}
	}
});