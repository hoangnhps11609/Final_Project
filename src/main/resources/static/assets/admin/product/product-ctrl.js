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
				$scope.message = "";
				$scope.statistic.from = "";
				$scope.to = null;
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

	//Kh???i t???o
	$scope.initialize();
	$scope.ODitems = [];

	//X??a form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: '5aa47c07.png',
			available: true
		};
		$scope.message1 = "";
		$scope.message2 = "";
		$scope.message3 = "";
		$scope.message4 = "";
		
		$scope.messageColorName = "";
		$scope.messageRed = "";
		$scope.messageGreen = "";
		$scope.messageBlue = "";
		
		
		$scope.vColorName = false;
		$scope.vRed = false;
		$scope.vGreen = false;
		$scope.vBlue = false;
		$scope.vName = false;
		$scope.vPrice = false;
		$scope.vCategory = false;
		$scope.vDiscount = false;
		$scope.vGender = false;
		$scope.vBrand = false;
		$scope.vMota = false;
		
		
		
		
		$scope.isEdit = null;	
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
	
	$scope.vPrice = false;
	$scope.validatePrice = function(p) {
		const characterRegex = /^\d+$/; 
		if(p == null || p == 0){
			$scope.message2 = "Price Invalid";
			$scope.vPrice = false;
		}else if(!p.match(characterRegex)){
			$scope.message2 = "Number only";
			$scope.vPrice = false;
		}else{
			$scope.message2 = "";
			$scope.vPrice = true;
		}
		
	}
	
	$scope.vCategory = false;
	$scope.validateCategory = function(c) {
		if(!c == 0){
			$scope.vCategory = true;
		}
		
	}
	
	$scope.vGender = false;
	$scope.validateGender = function(g) {
		if(!g == 0){
			$scope.vGender = true;
		}
		
	}
	
	$scope.vBrand = false;
	$scope.validateBrand = function(b) {
		if(!b == 0){
			$scope.vBrand = true;
		}
		
	}
	
	$scope.vDiscount = false;
	$scope.validateDiscount = function(p) {
		const characterRegex = /^\d+$/; 
		if(p == null || p < 0 || p > 99){
			$scope.message3 = "Discount Invalid";
			$scope.vDiscount = false;
		}else if(!p.match(characterRegex)){
			$scope.message3 = "Number only";
			$scope.vDiscount = false;
		}else{
			$scope.message3 = "";
			$scope.vDiscount = true;
		}
		
	}
	
	$scope.vMota = false;
	$scope.validateMota = function(m) {
		if(m.length == 0){
			$scope.message4 = "Description not allow null";
			$scope.vMota = false;
		}else if(m.length > 255){
			$scope.message4 = "Description not over 255 characters";
			$scope.vMota = false;
		}else{
			$scope.message4 = "";
			$scope.vMota = true;
		}
		
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

	//hi???n th??? l??n form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
		$scope.isEdit = "true";
		
		$scope.vName = true;
		$scope.vPrice = true;
		$scope.vCategory = true;
		$scope.vDiscount = true;
		$scope.vGender = true;
		$scope.vBrand = true;
		$scope.vMota = true;
		
	}
	
	$scope.topProduct = function(){
		$('#TopProductModalCenter').appendTo("body").modal('show');
		$http.get("/rest/products/top").then(resp => {
			$scope.tops = resp.data;
		}).catch(error => {
			//alert("L???i c???p nh???t s???n ph???m");
			
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
			$scope.isEdit = false;
			$scope.resetD();
		});
		$('#exampleModalCenter1').appendTo("body").modal('show');
		$('#viewAllModalCenter').appendTo("body").modal('hide');
		document.getElementById('fee').value = '2';
		
	}
	
	$scope.findbyCategory = function(c) {
		$http.get(`/rest/products/findCategoryName/${c}`).then(resp => {
			$scope.message = "Find By Category: " + resp.data.name;
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
			$('#viewAllModalCenter').appendTo("body").modal('show');
		}).catch(error => {
			//alert("L???i c???p nh???t s???n ph???m");

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
		$('#exampleModalCenter1').appendTo("body").modal('hide');
		$scope.isEdit = false;
		$('#exampleModalCenter1').appendTo("body").modal('show');
	}

	$scope.createD = function() {
		var item = angular.copy($scope.form2);
		$http.post(`/rest/productdetails`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$http.put(`/rest/products/get/${item.product.id}`, item).then(resp => {
				$scope.initialize();
			})
			$scope.ODitems.push(resp.data);
			$scope.resetD();
			//alert("Th??m m???i th??nh c??ng");
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
			//alert("L???i th??m s???n ph???m");
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

	//update s???n ph???m m???i
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
			//alert("L???i c???p nh???t s???n ph???m");

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




	//Th??m s???n ph???m m???i
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

	//update s???n ph???m m???i
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
						//alert("C???p nh???t th??nh c??ng");
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

	//X??a s???n ph???m m???i
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

	//Upload h??nh
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			//alert("L???i upload h??nh ???nh");

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
			//alert("L???i upload h??nh ???nh");

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

	//X??a s???n ph???m m???i
	$scope.deletePrDe = function(item) {
		$http.delete(`/rest/productdetails/${item.id}`).then(resp => {
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
					$scope.initialize();
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$('#viewAllModalCenter').appendTo("body").modal('hide');
				}
			})
		}).catch(error => {
			//alert("L???i x??a s???n ph???m");
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

	$scope.editPrDe = function(item) {
		$scope.form2 = angular.copy(item);
		$scope.isEdit = true;
		$('#exampleModalCenter1').appendTo("body").modal('show');
		$('#viewAllModalCenter').appendTo("body").modal('hide');
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

	//Th??m color m???i
	$scope.createColor = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/colors`, item).then(resp => {
			$scope.items.push(resp.data);
			//alert("Th??m m???i th??nh c??ng");
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
			$('#exampleModalCenter1').appendTo("body").modal('show');
			$('#createNewColoreModalCenter').appendTo("body").modal('hide');
		}).catch(error => {
			//alert("L???i th??m s???n ph???m");

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
	
	$scope.vColorName = false;
	$scope.ValidateColorName = function(c) {
		if(c.length < 2 || c.length > 50){
			$scope.messageColorName = "Name must more than 2 & less than 50 characters";
			$scope.vColorName = false;	
		}else{
			$scope.messageColorName = "";
			$scope.vColorName = true;		
		}
	}
	$scope.vRed = false;
	
	
	$scope.ValidateRed = function(r) {
	const characterRegex = /^\d+$/; 
	const specialRegex = /^\d+$/;
		if(r == null){
			$scope.messageRed = "Please not null";	
			$scope.vRed = false;			
		}else if (!r.match(characterRegex) || !r.match(specialRegex)){
			$scope.messageRed = "Only number please!";	
			$scope.vRed = false;			
		}else if(r > 255 || r < 0){
			$scope.messageRed = "Number from 0 to 255";	
			$scope.vRed = false;			
		}else{
				$scope.vRed = true;			
				$scope.messageRed = "";	
			}
	}
	
	
	$scope.vGreen = false;
	$scope.ValidateGreen = function(g) {
	const characterRegex = /^\d+$/; 
	const specialRegex = /^\d+$/;
		if(g == null){
			$scope.messageGreen = "Please not null";	
			$scope.vGreen = false;
		}else if (!g.match(characterRegex) || !g.match(specialRegex)){
			$scope.messageGreen = "Only number please!";	
			$scope.vGreen = false;
		}else if(g > 255 || g < 0){
			$scope.messageGreen = "Number from 0 to 255";	
				$scope.vGreen = false;
		}else{
				$scope.vGreen = true;
				$scope.messageGreen = "";	
			}
	}
	
	$scope.vBlue = false;
	$scope.ValidateBlue = function(b) {
	const characterRegex = /^\d+$/; 
	const specialRegex = /^\d+$/;
		if(b == null){
			$scope.messageBlue = "Please not null";	
			$scope.vBlue = false;
		}else if (!b.match(characterRegex) || !b.match(specialRegex)){
			$scope.messageBlue = "Only number please!";	
			$scope.vBlue = false;
		}else if(b > 255 || b < 0){
			$scope.messageBlue = "Number from 0 to 255";	
			$scope.vBlue = false;
		}else{
			$scope.vBlue = true;
				$scope.messageBlue = "";	
			}
	}
	
	$scope.vQuantity = false;
	$scope.validateQuantity = function(q) {
		const characterRegex = /^\d+$/; 
		if(q == null || q == 0){
			$scope.message1 = "Quantity not allow null or equal 0";
			$scope.vQuantity = false;
		}else if(!q.match(characterRegex)){
			$scope.message1 = "Number only";
			$scope.vQuantity = false;
		}else{
			$scope.message1 = "";
			$scope.vQuantity = true;
		}
		
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
});
