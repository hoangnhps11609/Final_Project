app.controller("productdetail-ctrl", function($scope, $http, $window) {
	$scope.items = [];
	$scope.products = [];
	$scope.colors = [];
	$scope.genders = [];
	$scope.sizes = [];

	$scope.form = {};

	$scope.blogcates = [];

	$scope.initialize = function() {
		//load products detail
		$http.get("/rest/productdetails").then(resp => {
			if(resp.data.length == 0){
			$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
				$scope.reset();
				$scope.message = "";
				$scope.statistic.from = "";
			})
			}
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
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.statistic();

		}
	});

	$scope.statistic = function() {
		var statistic = angular.copy($scope.statistic);

		$http.get(`/rest/productdetails/get/${statistic.from}`).then(resp => {
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
			document.getElementById("lists").style.display = "block";
			document.getElementById("homes").style.display = "none";
			}
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



	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: '5aa47c07.png',
			available: true
		};
		$scope.message1 = "";
		$scope.vProduct = false;
		$scope.vSize = false;
		$scope.vColor = false;
		$scope.vQuantity = false;
		
		$scope.isEdit = null;
		
	}
	$scope.vProduct = false;
	$scope.validateProduct = function(p) {
		if(!p == 0){
			$scope.vProduct = true;
		}
		
	}
	
	$scope.vSize = false;
	$scope.validateSize = function(s) {
		if(!s == 0){
			$scope.vSize = true;
		}
		
	}
	
	$scope.vColor = false;
	$scope.validateColor = function(c) {
		if(!c == 0){
			$scope.vColor = true;
		}
		
	}
	
	$scope.vQuantity = false;
	$scope.validateQuantity = function(q) {
		const characterRegex = /^\d+$/; 
		if(q == null){
			$scope.message1 = "Quantity not allow null";
			$scope.vQuantity = false;
		}else if(!q.match(characterRegex)){
			$scope.message1 = "Number only";
			$scope.vQuantity = false;
		}else{
			$scope.message1 = "";
			$scope.vQuantity = true;
		}
		
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
		$scope.isEdit = "true";
		$scope.vQuantity = true;
		
	}

	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var id = item.id;

		Swal.fire({
			title: 'Confirm adding "' + id + '" to the product detail list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/productdetails`, item).then(resp => {
					resp.data.createDate = new Date(resp.data.createDate)
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();

					Swal.fire(
						'Successfully!',
						'Added "' + id + '" to product detail list.',
						'success'
					)
					$(".nav-tabs a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Create Failure!',
						'Can not add "' + id + '" !',
						'error'
					)

					console.log("Error", error);
				});
			}
		})


		var statistic = angular.copy($scope.form);
		$http.put(`/rest/products/get/${statistic.product.id}`, item).then(resp => {
			$scope.initialize();
		}).catch(error => {
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
		var id = item.id;
		Swal.fire({
			title: 'Confirm edit information "' + id + '" !',
			text: "New information will be saved to the product detail list",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/productdetails/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Updated "' + id + '" to product detail list.',
						'success'
					)

					$(".nav-tabs a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update "' + id + '" !',
						'error'
					)
					console.log("Error", error);

				});
			}
		})
	}

	//Xóa sản phẩm mới
	$scope.delete = function(item) {
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
					Swal.fire(
						'Deleted!',
						'Product detail "' + id + '" has been deleted.',
						'success'
					)
				}).catch(error => {
					Swal.fire(
						'Delete Failure!',
						'Can not delete "' + id + '" !',
						'error'
					)
					console.log("Error", error);
				});

			}
		})
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


	$scope.CreateNewColor = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/color';
	}

	$scope.CreateNewProduct = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/product';
	}

	$scope.CreateNewSize = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/size';
	}

	$scope.CreateNewCategory = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/category';
	}

	$scope.CreateNewOrder = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/order';
	}

	$scope.CreateNewBrand = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/brand';
	}

	$scope.CreateNewProductDetail = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/productdetail';
	}

	$scope.CreateNewAccount = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/account';
	}

	$scope.CreateNewBlog = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/blog';
	}

	$scope.CreateNewBlogCategory = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/blogcategory';
	}




	$scope.CreateNewColorModal = function() {
		$('#createNewColoreModalCenter').appendTo("body").modal('show');
	}

	//Thêm color mới
	$scope.createColor = function() {
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
			//load color
			$http.get("/rest/colors").then(resp => {
				$scope.colors = resp.data;
			});
			$('#createNewColoreModalCenter').appendTo("body").modal('hide');
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
});
