app.controller("product-ctrl", function($scope, $http, $window) {

	var so;

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
	$scope.form3 = {};
	$scope.products2 = [];
	$scope.blogcates = [];

	$scope.initialize = function() {
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
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.statistic();

		}
	});

	$scope.statistic = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/products/${statistic.from}`).then(resp => {
			if(resp.data.length == 0){
			$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
			$scope.items = resp.data;
			$scope.items.forEach(item => {
			})
			$(".nav a:eq(1)").tab('show');
			document.getElementById("homes").style.display = "none";
			document.getElementById("lists").style.display = "block";
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

	//Khởi tạo
	$scope.initialize();
	$scope.ODitems = [];

	//Xóa form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: '5aa47c07.png',
			available: true
		};
	}

	$scope.reset2 = function() {
		$scope.form2 = {
			createDate: new Date(),
			image: '5aa47c07.png',
			available: true
		};

		$http.get(`/rest/products/getid/${a}`).then(resp => {
			$scope.products2 = resp.data;
		});

	}

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
	}
	
	$scope.topProduct = function(){
		$('#TopProductModalCenter').appendTo("body").modal('show');
		$http.get("/rest/products/top").then(resp => {
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
	
	

	$scope.adddetail = function(item) {
		//load products		
		var a = item.id
		$http.get(`/rest/products/getid/${a}`).then(resp => {
			$scope.products2 = resp.data;
			$scope.product = item;
		});
		$('#exampleModalCenter1').appendTo("body").modal('show');
		$scope.resetD();
		$('.bd-example-modal-lg2').appendTo("body").modal('hide');
		document.getElementById('fee').value = '2';


	}
	
	$scope.findbyCategory = function(c) {
			$http.get("/rest/products/findCateoryName/${c}").then(resp => {
			$scope.message = resp.data;
		});
			
			$http.get(`/rest/products/productCate/${c}`).then(resp => {
			$scope.items = resp.data;
			
		}).catch(error => {
			alert(error);
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


	$scope.viewall = function(item) {
	
		$http.get(`/rest/products/productdetail/count/${item.id}`).then(resp => {
					$scope.sumPro = resp.data;
			});
		
		$http.get(`/rest/products/product/${item.id}`).then(resp => {
			$scope.product = resp.data;
		});		
	
		$http.get(`/rest/productdetails/getdetail/${item.id}`).then(resp => {
			$scope.ODitems = resp.data;

			$('.bd-example-modal-lg2').appendTo("body").modal('show');
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

	$scope.resetD = function() {
		$scope.form2 = {
			createDate: new Date(),
			image: '5aa47c07.png',
			available: true
		};
	}

	$scope.createD = function() {
		var item = angular.copy($scope.form2);
		$http.post(`/rest/productdetails`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.ODitems.push(resp.data);
			$scope.resetD();
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
			$scope.initialize();
		}).catch(error => {
			//alert("Lỗi thêm sản phẩm");
			//alert(error);

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
	$scope.updateD = function() {
		var item = angular.copy($scope.form2);
		var id = item.id;
		$http.put(`/rest/productdetails/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.resetD();

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
				title: 'Update in successfully "' + id + '" product detail'
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




	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			title: 'Confirm adding "' + name + '" to the product list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/products`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();

					Swal.fire(
						'Successfully!',
						'Added "' + name + '" to product list.',
						'success'
					)

					$scope.initialize();
				}).catch(error => {
					Swal.fire(
						'Create Failure!',
						'Can not add "' + name + '" !',
						'error'
					)

					$http.post(`/rest/products`, item).then(resp => {
						$scope.items.push(resp.data);
						$scope.reset();
						$scope.initialize();

						//						Swal.fire(
						//							'Successfully!',
						//							'Added "' + name + '" to product list.',
						//							'success'
						//						)

						$scope.initialize();
					}).catch(error => {
						//						Swal.fire(
						//							'Create Failure!',
						//							'Can not add "' + name + '" !',
						//							'error'
						//						)

						console.log("Error", error);
					});
				})
			}
		})
	}

	//update sản phẩm mới
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var name = item.name;
		Swal.fire({
			title: 'Confirm edit information "' + name + '" !',
			text: "New information will be saved to the product list",
			on: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/products/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Updated "' + name + '" to product list.',
						'success'
					)

					$(".nav-tabs a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update "' + name +  '" !',
						'error'
					)


					$http.put(`/rest/products/${item.id}`, item).then(resp => {
						var index = $scope.items.findIndex(p => p.id == item.id);
						$scope.items[index] = item;
						//alert("Cập nhật thành công");
						$scope.initialize();

//						Swal.fire(
//							'Successfully!',
//							'Updated "' + name + '" to product list.',
//							'success'
//						)

						$scope.initialize();
					}).catch(error => {
//						Swal.fire(
//							'Update Failure!',
//							'Can not update "' + name + '" !',
//							'error'
//						)

						console.log("Error", error);

					});
				})
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
				$http.delete(`/rest/products/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					Swal.fire(
					'Deleted!',
					'Product "' + name + '" has been deleted.',
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
	$scope.imageChanged2 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form2.image = resp.data.name;
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

	$scope.pager2 = {
		page2: 0,
		size2: 3,
		get ODitems() {
			var start = this.page2 * this.size2;
			return $scope.ODitems.slice(start, start + this.size2);
		},
		get count() {
			return Math.ceil(1.0 * $scope.ODitems.length / this.size2);
		}, first() {
			this.page2 = 0;
		}, prev() {
			this.page2--;
			if (this.page2 < 0) {
				this.last();
			}
		}, next() {
			this.page2++;
			if (this.page2 >= this.count) {
				this.first();
			}
		}, last() {
			this.page2 = this.count - 1;
		}
	}

	//Xóa sản phẩm mới
	$scope.deletePrDe = function(item) {
		$http.delete(`/rest/productdetails/${item.id}`).then(resp => {
			$scope.initialize();
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$('.bd-example-modal-lg2').appendTo("body").modal('hide');
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
		$scope.viewall();
	}

	$scope.editPrDe = function(item) {
		$scope.form2 = angular.copy(item);
		$scope.isEdit = "edit";
		$('#exampleModalCenter1').appendTo("body").modal('show');
		$('.bd-example-modal-lg2').appendTo("body").modal('hide');
	}

	$scope.CreateNewCategory = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/category';
	}

	$scope.CreateNewBrand = function() {
		$window.location.href = 'http://localhost:8080/assets/admin/index.html#!/brand';
	}

	$scope.showCategory = function() {
		$('#showCategoryModalCenter').appendTo("body").modal('show');
	}

	$scope.ProductInCategory = function(c) {
		alert(c.name);
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
	
	$scope.pagerTopProduct = {
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
});
