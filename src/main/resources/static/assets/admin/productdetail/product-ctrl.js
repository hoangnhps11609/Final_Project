app.controller("productdetail-ctrl", function ($scope, $http, $window) {
	$scope.items = [];
	$scope.products = [];
	$scope.colors = [];
	$scope.genders = [];
	$scope.sizes = [];

	$scope.form = {};

	$scope.blogcates = [];

	$scope.initialize = function () {
		//load products detail
		$http.get("/rest/productdetails").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
				$scope.reset();
			})
		});

		//load products
		$http.get("/rest/products").then(resp => {
			$scope.products = resp.data;
		});

		//load color
		$http.get("/rest/colors").then(resp => {
			$scope.colors = resp.data;
		});

		//load gender
		$http.get("/rest/genders").then(resp => {
			$scope.genders = resp.data;
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

		$http.get(`/rest/productdetails/get/${statistic.from}`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav a:eq(1)").tab('show');
			document.getElementById("lists").style.display = "block";
			document.getElementById("homes").style.display = "none";
		}).catch(error => {
			//alert('Value is invalid');
			Swal.fire({
			  title: 'Please enter search keyword',
			  showClass: {
			    popup: 'animate__animated animate__fadeInDown'
			  },
			  hideClass: {
			    popup: 'animate__animated animate__fadeOutUp'
			  }
			})
			console.log("Error", error);
		});
	}



	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function () {
		$scope.form = {
			createDate: new Date(),
			image: '5aa47c07.png',
			available: true
		};
	}

	//hiển thị lên form
	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show')
	}

	//Thêm sản phẩm mới
	$scope.create = function () {
		var item = angular.copy($scope.form);
		$http.post(`/rest/productdetails`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			//alert("Thêm mới thành công");
				$scope.initialize();
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'success',
			  title: 'Created in successfully'
			})
			
			$scope.initialize();
		}).catch(error => {
			// alert("Lỗi thêm sản phẩm");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Create failure'
			})
			
			console.log("Error", error);
		});


		var statistic = angular.copy($scope.form);
		$http.put(`/rest/products/get/${statistic.product.id}`, item).then(resp => {
			$scope.initialize();
		}).catch(error => {
			//alert("Lỗi cập nhật sản phẩm");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Update failure'
			})
			
			console.log("Error", error);

		});

	}

	//update sản phẩm mới
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/productdetails/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			//alert("Cập nhật thành công");
				$scope.initialize();
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'success',
			  title: 'Update in successfully'
			})
			
			$scope.initialize();
		}).catch(error => {
			//alert("Lỗi cập nhật sản phẩm");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Update failure'
			})
			
			console.log("Error", error);

		});
	}

	//Xóa sản phẩm mới
	$scope.delete = function (item) {
	var id = item.id;
		Swal.fire({
		  title: 'Are you sure delete "' + id + '"?',
		  text: "You won't be able to revert this!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
		  if (result.isConfirmed) {
		  	$http.delete(`/rest/productdetails/${item.id}`).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items.splice(index, 1);
				$scope.reset();
				$scope.initialize();
		   
		}).catch(error => {
			//alert("Lỗi xóa sản phẩm");
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			Toast.fire({
			  icon: 'warning',
			  title: 'Delete failure'
			})
			console.log("Error", error);
		});
			 Swal.fire(
			      'Deleted!',
			      'Product detail "'+ id +'" has been deleted.',
			      'success'
			    )
			  }
			})
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
			//alert("Lỗi upload hình ảnh");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Upload image failure'
			})
			
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
	
	
	$scope.CreateNewColor = function(){
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/color';
	}
	
	$scope.CreateNewProduct = function(){
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/product';
	}
	
	$scope.CreateNewSize = function(){
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/size';
	}
	
	$scope.CreateNewColorModal = function(){
		$('#createNewColoreModalCenter').appendTo("body").modal('show');
	}
	
	//Thêm color mới
	$scope.createColor = function () {
		var item = angular.copy($scope.form);
		$http.post(`/rest/colors`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			//alert("Thêm mới thành công");
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'success',
			  title: 'Created in successfully'
			})
			
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {
			//alert("Lỗi thêm sản phẩm");
			
			const Toast = Swal.mixin({
			  toast: true,
			  position: 'top-end',
			  showConfirmButton: false,
			  timer: 1500,
			  timerProgressBar: true,
			  didOpen: (toast) => {
			    toast.addEventListener('mouseenter', Swal.stopTimer)
			    toast.addEventListener('mouseleave', Swal.resumeTimer)
			  }
			})
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Create failure'
			})
			
			console.log("Error", error);
		});
		$('#createNewColoreModalCenter').appendTo("body").modal('hide');
		$window.location.reload();
	}
});
