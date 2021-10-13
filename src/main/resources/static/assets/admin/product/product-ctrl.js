app.controller("product-ctrl", function ($scope, $http) {



		$scope.colors = [];
	$scope.genders = [];
	$scope.sizes = [];
	$scope.items = [];
	$scope.products = [];

	$scope.cates = [];
	$scope.brands = [];
	$scope.genders = [];
	$scope.form = {};
		$scope.form2 = {};
				
		
	

	$scope.blogcates = [];




	$scope.initialize = function () {
		//load products
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
				$scope.reset();
			})
		});

		//load category
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});

		//load brand
		$http.get("/rest/brands").then(resp => {
			$scope.brands = resp.data;
		});
		
			//load products
		$http.get("/rest/products").then(resp => {
			$scope.products = resp.data;
		});

		//load gender
		$http.get("/rest/genders").then(resp => {
			$scope.genders = resp.data;
		});
		
			//load color
		$http.get("/rest/colors").then(resp => {
			$scope.colors = resp.data;
		});


		//load size
		$http.get("/rest/sizes").then(resp => {
			$scope.sizes = resp.data;
		});

	}

	var input = document.getElementById("myInput");
	input.addEventListener("keyup", function (event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.statistic();

		}
	});

	$scope.statistic = function () {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/products/${statistic.from}`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav a:eq(1)").tab('show');
			document.getElementById("homes").style.display = "none";
			document.getElementById("lists").style.display = "block";
		}).catch(error => {
			alert();
			console.log("Error", error);
		});
	}

	//Khởi tạo
	$scope.initialize();
		$scope.ODitems = [];
	
	//Xóa form
	$scope.reset = function () {
		$scope.form = {
			createDate: new Date(),
			image: 'user.png',
			available: true
		};
	}

	//hiển thị lên form
	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show')
	}

	
	$scope.adddetail = function (item) {
		alert(item.name);
		$scope.form2 = angular.copy(item);
		$('#exampleModalCenter1').appendTo("body").modal('show');

		


	}

	$scope.viewall = function (item) {
			$http.get(`/rest/productdetails/getdetail/${item.id}`).then(resp => {
				$scope.ODitems = resp.data;
			
			$('.bd-example-modal-lg').appendTo("body").modal('show');
			
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);

		});
		
		


	}
	
	
		$scope.createDetail = function () {
			var item = angular.copy($scope.form2);
			$http.post(`/rest/productdetails`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			resp.data.product.id = item.id
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi thêm sản phẩm a");
			console.log("Error", error);
		});


		var statistic = angular.copy($scope.form2);
		$http.put(`/rest/products/get/${statistic.id}`, item).then(resp => {
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);

		});

	}


	//Thêm sản phẩm mới
	$scope.create = function () {
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi thêm sản phẩm");
			console.log("Error", error);
		});
	}

	//update sản phẩm mới
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi cập nhật sản phẩm");
			console.log("Error", error);

		});
	}

	//Xóa sản phẩm mới
	$scope.delete = function (item) {
		$http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa  thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);

		});
	}

	//Upload hình
	$scope.imageChanged = function (files) {
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

	$scope.pager = {
		page: 0,
		size: 4,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		}, first() {
			this.page = 0;
		}, prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		}, next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		}, last() {
			this.page = this.count - 1;
		}
	}
	
			$scope.pager2 = {
		page2: 0,
		size2: 4,
		get ODitems() {
			var start = this.page2 * this.size2;
			return $scope.ODitems.slice(start, start + this.size2);
		},
		get count(){
			return Math.ceil(1.0 * $scope.ODitems.length / this.size2);
		}, first(){
			this.page2 = 0;
		}, prev(){
			this.page2--;
			if(this.page2<0){
				this.last();
			}
		}, next(){
			this.page2++;
			if(this.page2 >= this.count){
				this.first();
			}
		}, last(){
			this.page2 = this.count-1;
		}
	}
});
