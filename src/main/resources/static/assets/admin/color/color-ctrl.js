app.controller("color-ctrl", function($scope, $http, $route) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load categories
		$http.get("/rest/colors").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
				$scope.items = resp.data;
				$scope.message = "";
				$scope.statistic.from = "";
			}
		});

	}

	$scope.inventoryColor = function() {
		$('#InventoryColorModalCenter').appendTo("body").modal('show');
		$http.get("/rest/colors/inventory").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
				$scope.inventories = resp.data;
			}
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

	$scope.topColor = function() {
		$('#TopColorModalCenter').appendTo("body").modal('show');
		$http.get("/rest/colors/top").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
				$scope.tops = resp.data;
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

	$scope.search = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/colors/${statistic.from}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
				$scope.items = resp.data;
				$(".nav a:eq(1)").tab('show');
				document.getElementById("lists").style.display = "block";
				document.getElementById("homes").style.display = "none";
				$scope.statistic.from = "";
				$scope.message = "Search by Keyword: " + statistic.from;
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
		$route.reload();
		$scope.isEdit = null;
		$scope.vName = false;
		$scope.vRed = false;
		$scope.vGreen = false;
		$scope.vBlue = false;
		$scope.message1 = "";
		$scope.message2 = "";
		$scope.message3 = "";
		$scope.message4 = "";
		
	}
	

	$scope.vName = false;
	$scope.ValidateName = function(c) {
		if(c.length < 2 || c.length > 50){
			$scope.message1 = "Name must more than 2 & less than 50 characters";
			$scope.vName = false;	
		}else{
			$scope.message1 = "";
			$scope.vName = true;		
		}
	}
	$scope.vRed = false;
	
	
	$scope.ValidateRed = function(r) {
	const characterRegex = /^\d+$/; 
	const specialRegex = /^\d+$/;
		if(r == null){
			$scope.message2 = "Please not null";	
			$scope.vRed = false;			
		}else if (!r.match(characterRegex) || !r.match(specialRegex)){
			$scope.message2 = "Only number please!";	
			$scope.vRed = false;			
		}else if(r > 255 || r < 0){
			$scope.message2 = "Number from 0 to 255";	
			$scope.vRed = false;			
		}else{
				$scope.vRed = true;			
				$scope.message2 = "";	
			}
	}
	
	
	$scope.vGreen = false;
	$scope.ValidateGreen = function(g) {
	const characterRegex = /^\d+$/; 
	const specialRegex = /^\d+$/;
		if(g == null){
			$scope.message3 = "Please not null";	
			$scope.vGreen = false;
		}else if (!g.match(characterRegex) || !g.match(specialRegex)){
			$scope.message3 = "Only number please!";	
			$scope.vGreen = false;
		}else if(g > 255 || g < 0){
			$scope.message3 = "Number from 0 to 255";	
				$scope.vGreen = false;
		}else{
				$scope.vGreen = true;
				$scope.message3 = "";	
			}
	}
	
	$scope.vBlue = false;
	$scope.ValidateBlue = function(b) {
	const characterRegex = /^\d+$/; 
	const specialRegex = /^\d+$/;
		if(b == null){
			$scope.message4 = "Please not null";	
			$scope.vBlue = false;
		}else if (!b.match(characterRegex) || !b.match(specialRegex)){
			$scope.message4 = "Only number please!";	
			$scope.vBlue = false;
		}else if(b > 255 || b < 0){
			$scope.message4 = "Number from 0 to 255";	
			$scope.vBlue = false;
		}else{
			$scope.vBlue = true;
				$scope.message4 = "";	
			}
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.oldColor = "Old Color: rgb(" + item.red + ", " + item.green + ", " + item.blue + ")";
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
		$scope.isEdit = "true";
		$scope.vName = true;
		$scope.vRed = true;
		$scope.vGreen = true;
		$scope.vBlue = true;
	}



	//Thêm sản phẩm mới
	$scope.create = function() {
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
						'Added "' + name + '" to color list.',
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

	//update sản phẩm mới
	$scope.update = function() {
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
						'Updated "' + name + '" to color list.',
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
					$(".nav a:eq(1)").tab('show');
					$scope.initialize();
					Swal.fire(
						'Deleted!',
						'Color "' + name + '" has been deleted.',
						'success'
					)
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


	$scope.viewProductFromColor = function(item) {
		$http.get(`/rest/products/color/${item.id}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$http.get(`/rest/productdetails/color/count/${item.id}`).then(resp => {
					$scope.sumProDet = resp.data;
				})
				$scope.ProCateItems = resp.data;
				$scope.countPro = resp.data.length;
				$scope.color = item;
				$scope.rgb = "rgb(" + item.red + ", " + item.green + ", " + item.blue + ")"
				$('#exampleModalCenter69').appendTo("body").modal('show');
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

	$scope.viewProductToColorTrenTop = function(item) {
		$http.get(`/rest/productdetails/topColor/${item.id}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.ProColorItems = resp.data;
				$scope.color = item;
				$('#exampleModalCenterColor22').appendTo("body").modal('show');
				$('#TopColorModalCenter').appendTo("body").modal('hide');
				$http.get(`/rest/productdetails/color/count/${item.id}`).then(resp => {
					$scope.sumProInColor = resp.data;
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
	
	$scope.closeProductTop = function(){
		$('#exampleModalCenterColor22').appendTo("body").modal('hide');
		$('#TopColorModalCenter').appendTo("body").modal('show');
	}


	$scope.viewProDetail = function(item) {
		$('#ProDetailModalCenter').appendTo("body").modal('show');
		$('#exampleModalCenter69').appendTo("body").modal('hide');
		$http.get(`/rest/productdetails/getdetail/${item.id}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.ProDetailitems = resp.data;
				$scope.countProDet = resp.data.length;
				$('#ProDetailModalCenter').appendTo("body").modal('show');
				$http.get(`/rest/products/product/${item.id}`).then(resp => {
					$scope.product = resp.data;
				});
				$http.get(`/rest/products/productdetail/count/${item.id}`).then(resp => {
					$scope.countProDetail = resp.data;
				});
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
	
	$scope.CloseProDetail = function(){
		$('#ProDetailModalCenter').appendTo("body").modal('hide');
		$('#exampleModalCenter69').appendTo("body").modal('show');
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
		size: 3,
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

	$scope.InventoryColorModalCenter = {
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
});
