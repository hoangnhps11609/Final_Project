app.controller("brand-ctrl", function ($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function () {
		//load brand
		$http.get("/rest/brands").then(resp => {
			$scope.items = resp.data;
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
		$http.get(`/rest/brands/${statistic.from}`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav a:eq(1)").tab('show');
			document.getElementById("homes").style.display = "none";
			document.getElementById("lists").style.display = "block";
		}).catch(error => {
			//alert('Error');
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
		$scope.form = {};
	}

	//hiển thị lên form
	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show')
	}

	//Thêm sản phẩm mới
	$scope.create = function () {
		var item = angular.copy($scope.form);
		$http.post(`/rest/brands`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			// alert("Thêm mới thành công");
			
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
	}

	//update sản phẩm mới
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/brands/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			//alert("Cập nhật thành công");
			
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
			
			$(".nav-tabs a:eq(1)").tab('show');
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
		$http.delete(`/rest/brands/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			//alert("Xóa  thành công");
			
			Swal.fire({
			  title: 'Are you sure?',
			  text: "You won't be able to revert this!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.isConfirmed) {
			    Swal.fire(
			      'Deleted!',
			      'Your file has been deleted.',
			      'success'
			    )
			  }
			})
			
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
	}

	$scope.pager = {
		page: 0,
		size: 10,
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
});