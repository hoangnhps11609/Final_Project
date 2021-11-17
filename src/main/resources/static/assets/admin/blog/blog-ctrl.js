app.controller("blog-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get("/rest/blog").then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.message = "";
				$scope.to = null;
				$scope.statistic.from = "";
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
				$scope.reset();
			})
			}
		});

	}
	var input = document.getElementById("myInput");
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.statistic();

		}
	});

	$http.get("/rest/blogcategories").then(resp => {
		$scope.cates = resp.data;
	});
	//Khởi tạo
	$scope.initialize();

	$scope.statistic = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/blog/${statistic.from}`).then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.message = "Search by Keyword: " + statistic.from;
					$scope.to = null;
				$scope.statistic.from = "";
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);

			})
			
			$(".nav a:eq(1)").tab('show');
			document.getElementById("lists").style.display = "block";
			document.getElementById("homes").style.display = "none";
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

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			images: '5aa47c07.png',
		};
		$scope.isEdit = null;
		$scope.message1 ="";
		$scope.message2 ="";
		$scope.vName = false;
		$scope.vCategory = false;
		$scope.vContent = false;
		
		
	}
	$scope.vName = false;
	$scope.validateName = function(n) {
		if(n == null){
			$scope.message1 = "Name not allow null";
			$scope.vName = false;	
		}else if(n.length > 50){
			$scope.message1 = "Name not over 50 characters";
			$scope.vName = false;	
		}else{
			$scope.message1 = "";
			$scope.vName = true;	
		}
		
	}
	
	$scope.vContent = false;
	$scope.validateContent = function(c) {
		if(c.length ==0 ){
			$scope.message2 = "Content not allow null";
			$scope.vContent = false;
		}else{
			$scope.message2 = "";
			$scope.vContent = true;
			
		}
			
	}
	
	$scope.vCategory = false;
	$scope.validateCategory = function(ca) {
		if(!ca == 0){
			$scope.vCategory = true;
		}
			
	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
				$scope.isEdit = "true";
		$scope.vName = true;
		$scope.vCategory = true;
		$scope.vContent = true;
		
	}

	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var name = item.name;

		Swal.fire({
			title: 'Confirm adding "' + name + '" to the blog list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/blog`, item).then(resp => {
					resp.data.createDate = new Date(resp.data.createDate)
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Added "' + name + '" to blog list.',
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
			text: "New information will be saved to the blog list",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/blog/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Updated "' + name + '" to blog list.',
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


	//Upload hình
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.images = resp.data.name;
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
				$http.delete(`/rest/blog/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					Swal.fire(
						'Deleted!',
						'Blog "' + name + '" has been deleted.',
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

	$scope.pager = {
		page: 0,
		size: 3,
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
