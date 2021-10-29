app.controller("brand-ctrl", function($scope, $http, $window) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load brand
		$http.get("/rest/brands").then(resp => {
				$scope.message = "";
				$scope.to = null;
			$scope.items = resp.data;
		});

	}
	var input = document.getElementById("myInput");
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.statistic();

		}
	});

	$scope.statistic = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/brands/${statistic.from}`).then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.message = "Search by Keyword: " + statistic.from;
			$scope.to = null;
			$scope.statistic.from = "";
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav a:eq(1)").tab('show');
			document.getElementById("homes").style.display = "none";
			document.getElementById("lists").style.display = "block";
			}
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
	$scope.reset = function() {
		$scope.form = {};
		$scope.isEdit = null;
		
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
				$scope.isEdit = "true";
		
	}


	$scope.proDetail = function(item) {
		$('#exampleModalCenterBrand22').appendTo("body").modal('hide');
		$('#exampleModalCenter').appendTo("body").modal('hide');
		$http.get(`/rest/products/product/${item.id}`).then(resp => {
			$scope.product = resp.data;
		});
		$http.get(`/rest/products/productdetail/count/${item.id}`).then(resp => {
			$scope.countProDetail = resp.data;
		});
		$http.get(`/rest/productdetails/getdetail/${item.id}`).then(resp => {
			$scope.ProDetailitems = resp.data;
			$('#ProDetailModalCenterBrand').appendTo("body").modal('show');

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

	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var name = item.name;

		Swal.fire({
			title: 'Confirm adding "' + name + '" to the brand list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/brands`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();

					Swal.fire(
						'Successfully!',
						'Added "' + name + '" to brand list.',
						'success'
					)

					$(".nav a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Create Failure!',
						'Can not add "' + name + '" !',
						'error'
					)

					console.log("Error", error);
				});
			}
		})
	}
	
	$scope.closeProductInBrand = function(){
		$('#exampleModalCenterBrand22').appendTo("body").modal('hide');
		$('#InventoryBrandModalCenter').appendTo("body").modal('show');
	}

	$scope.viewProductToBrandTrenTop = function(item) {
		$http.get(`/rest/products/brand/${item.id}`).then(resp => {
			$scope.ProBrandItems = resp.data;
			$scope.brand = item;
			$('#exampleModalCenterBrand22').appendTo("body").modal('show');
			$('#InventoryBrandModalCenter').appendTo("body").modal('hide');
			$http.get(`/rest/products/brand/count/${item.id}`).then(resp => {
				$scope.sumProInBrand = resp.data;
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
	
	
	
	$scope.viewProductTopBrand = function (item) {
			$http.get(`/rest/products/topBrandSale/${item.id}`).then(resp => {
				if(resp.data.length == 0){
					$('#NoDataModalCenter').appendTo("body").modal('show');
				}else{
					$scope.ProTopBrandItems = resp.data;
					$scope.category = item;
					$('#ProductTopBrandModalCenter').appendTo("body").modal('show');
					$('#TopBrandModalCenter').appendTo("body").modal('hide');
					$http.get(`/rest/products/brand/count/${item.id}`).then(resp => {
						$scope.sumProInCate = resp.data;
				})
			}
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
	
		$scope.closeOrderDetail = function(){
		$('#ProductTopBrandModalCenter').appendTo("body").modal('hide');
		$('#TopBrandModalCenter').appendTo("body").modal('show');
	}

	$scope.topBrand = function() {
		$('#TopBrandModalCenter').appendTo("body").modal('show');
		$http.get("/rest/brands/top").then(resp => {
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

	//update sản phẩm mới
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			title: 'Confirm edit information "' + name + '" !',
			text: "New information will be saved to the brand list",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/brands/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();

					Swal.fire(
						'Successfully!',
						'Updated "' + name + '" to brand list.',
						'success'
					)

					$(".nav a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update "' + name + '" !',
						'error'
					)

					console.log("Error", error);

				});
			}
		})
	}

	//Xóa sản phẩm mới
	$scope.delete = function(item) {
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
				$http.delete(`/rest/brands/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					Swal.fire(
						'Deleted!',
						'Brand "' + name + '" has been deleted.',
						'success'
					)
					$scope.initialize();
				}).catch(error => {
					Swal.fire(
						'Delete Failure!',
						'Can not delete "' + name + '" !',
						'error'
					)

					console.log("Error", error);

				});

			}
		})
	}

	$scope.viewProductToBrand = function(item) {
		$http.get(`/rest/products/brand/${item.id}`).then(resp => {
			$scope.ProBrandItems = resp.data;
			$('#exampleModalCenter').appendTo("body").modal('show');
			$scope.brandName = item.name;
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
		size: 7,
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

	
	
	$scope.inventoryBrand = function(){
		$('#InventoryBrandModalCenter').appendTo("body").modal('show');
		$http.get("/rest/brands/inventory").then(resp => {
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

	$scope.pager2 = {
		page: 0,
		size: 3,
		get ProBrandItems() {
			var start = this.page * this.size;
			return $scope.ProBrandItems.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ProBrandItems.length / this.size);
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

	$scope.pagerTopBrand = {
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

	$scope.pagerBrandInventory = {
		page: 0,
		size: 3,
		get inventories() {
			var start = this.page * this.size;
			return $scope.inventories.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.inventories.length / this.size);
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
	
	$scope.pagerProTopBrand = {
		page: 0,
		size: 3,
		get ProTopBrandItems() {
			var start = this.page * this.size;
			return $scope.ProTopBrandItems.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ProTopBrandItems.length / this.size);
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

	$scope.CreateNewProduct = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/product';
	}
	$scope.closeProDetail = function() {
		$('#exampleModalCenterBrand22').appendTo("body").modal('show');
	}
});