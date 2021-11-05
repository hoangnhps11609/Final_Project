app.controller("size-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load categories
		$http.get("/rest/sizes").then(resp => {
			$scope.message = "";
			$scope.to = null;
			$scope.items = resp.data;
		});

	}

	$scope.search = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/sizes/${statistic.from}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.message = "Search by Keyword: " + statistic.from;
			$scope.statistic.from = "";
			$scope.items = resp.data;
			$(".nav a:eq(1)").tab('show');
			document.getElementById("lists").style.display = "block";
			document.getElementById("homes").style.display = "none";
			}
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
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.search();

		}
	});
	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function() {
		$scope.form = {};
		$scope.isEdit = null;
		$scope.message1 = "";
		$scope.message2 ="";
		$scope.vName = false;
		$scope.vMota = false;
		
	}
	$scope.vName = false;
	$scope.validateName = function(n) {
	const specialRegex = /^[A-Za-z0-9 ]+$/;
		if(n == null){
			$scope.vName = false;
			$scope.message1 = "Name not allow null";	
		}else if(!n.match(specialRegex)){
			$scope.vName = false;
			$scope.message1 = "Name not allow special characters";	
		}else{
			$scope.vName = true;
			$scope.message1 = "";	
		}
		
	}
	
	$scope.vMota = true;
	$scope.validateMota = function(m) {
		if(m.length > 250){
			$scope.message2 = "Description do not over 250 character";	
			$scope.vMota = false;	
		}else{
			$scope.vMota = true;
			$scope.message2 = "";	
		}
			
		
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
				$scope.isEdit = "true";
		
	}



	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			title: 'Confirm adding ' + name + ' to the size list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/sizes`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Added ' + name + ' to size list.',
						'success'
					)

					$(".nav-tabs a:eq(1)").tab('show');

				}).catch(error => {
					Swal.fire(
						'Create Failure!',
						'Can not add ' +  name + ' !',
						'error'
					)

					console.log("Error", error);
				});
						}
		})
	}

	$scope.topSize = function() {
		$('#TopSizeModalCenter').appendTo("body").modal('show');
		$http.get("/rest/sizes/top").then(resp => {
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

	$scope.viewProductfromSize = function(item) {
		$http.get(`/rest/productdetails/size/${item.id}`).then(resp => {
			$scope.ProSizeItems = resp.data;
			$scope.color = item;
			$('#exampleModalCenterSize22').appendTo("body").modal('show');
			$http.get(`/rest/productdetails/size/count/${item.id}`).then(resp => {
				$scope.sumProInSize = resp.data;
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
	
	$scope.viewProductToSizeTrenTop = function(item) {
		$http.get(`/rest/productdetails/topSize/${item.id}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.ProSizeItems = resp.data;
				$scope.color = item;
				$('#exampleModalCenterSize69').appendTo("body").modal('show');
				$('#TopSizeModalCenter').appendTo("body").modal('hide');
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

	//update sản phẩm mới
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			title: 'Confirm edit information ' + name + ' !',
			text: "New information will be saved to the size list",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/sizes/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Updated ' + name + ' to size list.',
						'success'
					)
					$(".nav-tabs a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update '  + name + ' !',
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
			title: 'Are you sure delete ' + name + '?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/rest/sizes/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					Swal.fire(
						'Deleted!',
						'Size '  + name + ' has been deleted.',
						'success'
					)
					$scope.initialize();
				}).catch(error => {
					Swal.fire(
						'Delete Failure!',
						'Can not delete ' + name + ' !',
						'error'
					)

					console.log("Error", error);

				});

			}
		})

	}

	$scope.inventorySize = function() {
		$('#InventorySizeModalCenter').appendTo("body").modal('show');
		$http.get("/rest/sizes/inventory").then(resp => {
			$scope.inventories = resp.data;
		}).catch(error => {
			alert(error);

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


	$scope.TimKiemTatCaSanPhamThuocSize = function(item) {
		$http.get(`/rest/productdetails/size/${item.id}`).then(resp => {
			$scope.ProCateItems = resp.data;
			$scope.category = item;
			$('#exampleModalCenterSize22').appendTo("body").modal('show');

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

	$scope.pager3 = {
		page: 0,
		size: 3,
		get ProSizeItems() {
			var start = this.page * this.size;
			return $scope.ProSizeItems.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ProSizeItems.length / this.size);
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

	$scope.pager = {
		page: 0,
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

	$scope.pagerTopSize = {
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

	$scope.InventorySizeModalCenter = {
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
});
