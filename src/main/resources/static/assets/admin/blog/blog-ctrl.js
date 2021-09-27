app.controller("blog-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get("/rest/blog").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
				$scope.reset();
			})
		});

	}
	$http.get("/rest/blogcategories").then(resp => {
		$scope.cates = resp.data;
	});
	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'user.png',
		};
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show')
	}

	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/blog`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			$scope.initialize();
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
		$http.put(`/rest/blog/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
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
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
	

	//Xóa sản phẩm mới
	$scope.delete = function(item) {
		$http.delete(`/rest/blog/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa  thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);

		});
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