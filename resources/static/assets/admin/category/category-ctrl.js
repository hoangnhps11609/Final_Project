app.controller("category-ctrl", function ($scope, $http, $window) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function () {
		//load categories
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
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
		$scope.form = {};
	}

	//hiển thị lên form
	$scope.edit = function (item) {
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
			  title: 'Confirm adding "' + name + '" to the category list?',
			  text: "",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.isConfirmed) {
				$http.post(`/rest/categories`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
					
				Swal.fire(
			      'Successfully!',
			      'Added "'+ name +'" to category list.',
			      'success'
			    )
				$(".nav-tabs a:eq(1)").tab('show');
		$http.post(`/rest/categories`, item).then(resp => {
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
			  title: 'Create in successfully "' + name + '" category'
			})
			
			$(".nav a:eq(1)").tab('show');
		}).catch(error => {

			Swal.fire(
			      'Create Failure!',
			      'Can not add "'+ name +'" !',
			      'error'
			    )
			
			console.log("Error", error);
		});
		
			})}
			})
	}

	//update sản phẩm mới
	$scope.update = function () {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			  title: 'Confirm edit information "' + name + '" !',
			  text: "New information will be saved to the category list",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.isConfirmed) {
				$http.put(`/rest/categories/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();
					
					Swal.fire(
				      'Successfully!',
				      'Updated "'+ name +'" to category list.',
				      'success'
			    	)
			   $(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {			
			Swal.fire(
			      'Update Failure!',
			      'Can not update "'+ name +'" !',
			      'error'
			    )
		$http.put(`/rest/categories/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.reset();
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
			  title: 'Update in successfully "' + name + '" category'
			})
			
			$(".nav a:eq(1)").tab('show');
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
			});
			
			Toast.fire({
			  icon: 'warning',
			  title: 'Update failure'
			});
			console.log("Error", error);
		});
			})}
			})
	}

	//Xóa sản phẩm mới
	$scope.delete = function (item) {
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
			    $http.delete(`/rest/categories/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					$scope.initialize();
					Swal.fire(
					      'Deleted!',
					      'Category "'+ name +'" has been deleted.',
					      'success'
					    )
		}).catch(error => {
			Swal.fire(
			      'Delete Failure!',
			      'Can not delete "'+ name +'" !',
			      'error'
			    )
			console.log("Error", error);

		});
		
			  }
			})
	}
	
	
	$scope.viewProductToCate = function (item) {
			$http.get(`/rest/products/category/${item.id}`).then(resp => {
				$scope.ProCateItems = resp.data;
				$scope.category = item;
			$('#exampleModalCenter').appendTo("body").modal('show');
			$http.get(`/rest/products/category/count/${item.id}`).then(resp => {
				$scope.sumProInCate = resp.data;
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
	
	$scope.pager2 = {
		page: 0,
		size: 3,
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
	
	
	$scope.topCategory = function(){
		$http.get(`/rest/products/category/${item.id}`).then(resp => {
				$scope.ProCateItems = resp.data;
				$scope.category = item;
			$('#TopCategoryModalCenter').appendTo("body").modal('show');
			
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
	
	$scope.inventoryProduct = function(){
		$('#InventoryCategoryModalCenter').appendTo("body").modal('show');
		$http.get("/rest/categories/inventory").then(resp => {
			$scope.inventories = resp.data;
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
	
	$scope.topCategory = function(){
		$('#TopCategoryModalCenter').appendTo("body").modal('show');
		$http.get("/rest/categories/top").then(resp => {
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
	
	$scope.proDetail = function(item){
		$('#exampleModalCenter').appendTo("body").modal('hide');
		$http.get(`/rest/products/product/${item.id}`).then(resp => {
				$scope.product = resp.data;
			});
		$http.get(`/rest/products/productdetail/count/${item.id}`).then(resp => {
				$scope.countProDetail = resp.data;
		});
		$http.get(`/rest/productdetails/getdetail/${item.id}`).then(resp => {
			$scope.ProDetailitems = resp.data;
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
	
	$scope.closeProDetail = function(){
		$('#exampleModalCenter').appendTo("body").modal('show');
	}
	
	$scope.CreateNewProduct = function(){
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/product';
	}
	$scope.CreateNewCategory = function(){
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/category';
	}
	$scope.CreateNewBrand = function(){
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/brand';
	}
});

