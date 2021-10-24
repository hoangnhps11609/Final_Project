app.controller("color-ctrl", function ($scope, $http, $route) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function () {
		//load categories
		$http.get("/rest/colors").then(resp => {
			$scope.items = resp.data;
		});

	}
	
	$scope.topColor = function(){
		$('#TopColorModalCenter').appendTo("body").modal('show');
		$http.get("/rest/colors/top").then(resp => {
			$scope.tops = resp.data;
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
	
		$scope.search = function () {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/categories/${statistic.from}`).then(resp => {
			$scope.items = resp.data;
			$(".nav a:eq(1)").tab('show');
			document.getElementById("lists").style.display = "block";
			document.getElementById("homes").style.display = "none";
		}).catch(error => {
			//alert();
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
	
		var input = document.getElementById("myInput");
	input.addEventListener("keyup", function (event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.search();

		}
	});
	
	
	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function () {
		$route.reload();
	}

	//hiển thị lên form
	$scope.edit = function (item) {
		$scope.oldColor = "Old Color: rgb(" + item.red + ", " + item.green + ", " + item.blue + ")";
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
	}
	


	//Thêm sản phẩm mới
	$scope.create = function () {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			  title: 'Confirm adding "' + name + '" to the color list?',
			  text: "",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.isConfirmed) {
				$http.post(`/rest/colors`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
				Swal.fire(
				      'Successfully!',
				      'Added "'+ name +'" to color list.',
				      'success'
				    )
			
			$(".nav a:eq(1)").tab('show');
		}).catch(error => {
			Swal.fire(
			      'Create Failure!',
			      'Can not add "'+ name +'" !',
			      'error'
			    )
			
			console.log("Error", error);
		});
			}
			})
	}

	//update sản phẩm mới
	$scope.update = function () {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			  title: 'Confirm edit information "' + name + '" !',
			  text: "New information will be saved to the color list",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.isConfirmed) {
				$http.put(`/rest/colors/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					//alert("Cập nhật thành công");
					$scope.initialize();
					$scope.reset();
			
				Swal.fire(
					      'Successfully!',
					      'Updated "'+ name +'" to color list.',
					      'success'
				    	)
			
			$(".nav a:eq(1)").tab('show');
		}).catch(error => {
			Swal.fire(
			      'Update Failure!',
			      'Can not update "'+ name +'" !',
			      'error'
			    )
			
			console.log("Error", error);

		});
			}
			})
	}
	
	

	//Xóa sản phẩm mới
	$scope.delete = function (item) {
			var id = item.username;
			var name = item.name;
			
			Swal.fire({
			  title: 'Are you sure delete "' + name + '"?',
			  text: "You won't be able to revert this!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.isConfirmed) {
			  		$http.delete(`/rest/colors/${item.id}`).then(resp => {
						var index = $scope.items.findIndex(p => p.id == item.id);
						$scope.items.splice(index, 1);
						$scope.reset();
						$scope.initialize();
			
		}).catch(error => {
			// alert("Lỗi xóa sản phẩm");
			
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
			      'Color "'+ name +'" has been deleted.',
			      'success'
			    )
			  }
			})
	}
	
	
	$scope.viewProductFromColor = function (item) {
		$http.get(`/rest/productdetails/color/count/${item.id}`).then(resp => {
			$scope.sumProDet = resp.data;
		})
		$http.get(`/rest/products/color/${item.id}`).then(resp => {
			$scope.ProCateItems = resp.data;
			$scope.countPro = resp.data.length;
			$scope.color = item;
			$scope.rgb = "rgb(" + item.red + ", " + item.green + ", " + item.blue + ")"
			$('#exampleModalCenter69').appendTo("body").modal('show');			
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
	
	$scope.viewProductToColorTrenTop = function (item) {
			$http.get(`/rest/productdetails/color2/${item.id}`).then(resp => {
				$scope.ProColorItems = resp.data;
				$scope.color = item;
			$('#exampleModalCenterColor22').appendTo("body").modal('show');
			$http.get(`/rest/productdetails/color/count/${item.id}`).then(resp => {
				$scope.sumProInColor = resp.data;
			})
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

	
	$scope.viewProDetail = function(item){
		$('#ProDetailModalCenter').appendTo("body").modal('show');
		$http.get(`/rest/products/product/${item.id}`).then(resp => {
				$scope.product = resp.data;
			});
		$http.get(`/rest/products/productdetail/count/${item.id}`).then(resp => {
				$scope.countProDetail = resp.data;
		});
		$http.get(`/rest/productdetails/getdetail/${item.id}`).then(resp => {
			$scope.ProDetailitems = resp.data;
			$scope.countProDet = resp.data.length;
			$('#ProDetailModalCenter').appendTo("body").modal('show');
			
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
	
	
	$scope.pager = {
		page: 0,
		size: 8,
		size: 5,
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
	
	$scope.pager3 = {
		page: 0,
		size: 5,
		get ProColorItems() {
			var start = this.page * this.size;
			return $scope.ProColorItems.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ProColorItems.length / this.size);
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
		page: 0,
		size: 5,
		get ProCateItems() {
			var start = this.page * this.size;
			return $scope.ProCateItems.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ProCateItems.length / this.size);
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
	
		$scope.pagerTopColor = {
		page: 0,
		size: 3,
		get tops() {
			var start = this.page * this.size;
			return $scope.tops.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.tops.length / this.size);
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
	
	
	$scope.pagerProDet = {
		page: 0,
		size: 3,
		get ProDetailitems() {
			var start = this.page * this.size;
			return $scope.ProDetailitems.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ProDetailitems.length / this.size);
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
