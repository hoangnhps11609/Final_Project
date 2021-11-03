app.controller("account-ctrl", function($scope, $http, $window) {


	$scope.items = [];
	$scope.form = {};

	$scope.ODitems = [];

	var input = document.getElementById("myInput");
	input.addEventListener("keyup", function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.search();
		}
	});

	$scope.initialize = function() {
		//load accounts
		$http.get("/rest/accounts").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
				$scope.items = resp.data;
				$scope.count = resp.data.length;
				$scope.message = "";
				$scope.to = null;
				$scope.items.forEach(item => {
				})
			}	
		});
	}

	$scope.search = function() {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/accounts/${statistic.from}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.items = resp.data;
				$(".nav a:eq(1)").tab('show');
				$scope.message = "Search by Keyword: " + statistic.from;
				$scope.to = null;
				$scope.statistic.from = "";
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

	//Khởi tạo
	$scope.initialize();

	//Xóa form	
	$scope.reset = function() {
		$scope.initialize();
		$scope.form = {
			photo: '5aa47c07.png'
		};
		$scope.isEdit = null;
	}
	
	$scope.vUsername = false;
	$scope.validateUsername = function(u) {
		const specialRegex = /^[A-Za-z0-9 ]+$/;
		if( u.length < 6 || u.length > 25){
			$scope.message1 = "Username must between 6 to 25 characters";
			$scope.vUsername = false;	
		}else if(!u.match(specialRegex)){
			$scope.message1 = "Username not allow special character or symbol";
			$scope.vUsername = false;
		}else{
				$scope.message1 = "";
				$scope.vUsername = true;
			
		}
	}
	
	$scope.vPassword = false;
	$scope.validatePassword = function(p) {
		if(p.length < 6 || p.length > 25){
			$scope.message2 = "Password must between 6 to 25 characters";
			$scope.vPassword = false;
		}else{
			$scope.message2 = "";
			$scope.vPassword = true;
		}
	}

	$scope.vFullname = false;
	$scope.validateFullname = function(f) {
		const specialRegex = /^[_A-z]*((-|\s)*[_A-z])*$/g;
		if(f == null || f.length > 30){
			$scope.message3 = "Name must be not null and less than 30 character";
			$scope.vFullname = false;
		}else if (f.match(specialRegex)){
			$scope.vFullname = true;
			$scope.message3 = "";	
		}else{
			$scope.message3 = "Name must not contain symbol or number";
			$scope.vFullname = false;	
		}
	}
	
	$scope.vEmail = false;
	$scope.validateEmail = function(e) {
		const emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if(e == null){
			$scope.message4 = "Email cannot null";
			$scope.vEmail = false;
		}else if (!e.match(emailRegex)){
			$scope.message4 = "Invalid Email";
			$scope.vEmail = false;
		}else{
			$scope.message4 = "";
			$scope.vEmail = true;	
		}
	}
	
	$scope.vPhone = false;
	$scope.validatePhone = function(p) {
		const phoneRegex = /(09|01[2|6|8|9])+([0-9]{8})\b/;
		const numberRegex = /^\d+$/;
	
		if( !p.match(phoneRegex) || !p.match(numberRegex)){
			$scope.message5 = "Invalid Phonenumber";
			$scope.vPhone = false;
		}else{
			$scope.message5 = "";
			$scope.vPhone = true;
			}
	}
	
	$scope.vAddress = false;
	$scope.validateAddress = function(a) {	
		if( a == null || a.length == null){
			$scope.message6 = "Address cannot null";
			$scope.vAddress = false;
		}else{
			$scope.message6 = "";
			$scope.vAddress = true;
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
	
	

	//Thêm account mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var username = item.username;

		Swal.fire({
			title: 'Confirm adding "' + username + '" to the account list?',
			text: "",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.post(`/rest/accounts`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
					Swal.fire(
						'Successfully!',
						'Added "' + username + '" to account list.',
						'success'
					)

					$(".nav a:eq(1)").tab('show')
				}).catch(error => {

					Swal.fire(
						'Create Failure!',
						'Can not add "' + username + '" !',
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
		var username = item.username;

		Swal.fire({
			title: 'Confirm edit information "' + username + '" !',
			text: "New information will be saved to the account list",
			icon: 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.username == item.username);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();

					Swal.fire(
						'Successfully!',
						'Updated "' + username + '" to account list.',
						'success'
					)

					$(".nav a:eq(1)").tab('show');
				}).catch(error => {
					Swal.fire(
						'Update Failure!',
						'Can not update "' + username + '" !',
						'error'
					)

					console.log("Error", error);

				});
			}
		})
	}


	//Xóa sản phẩm mới
	$scope.delete = function(item) {
		var username = item.username;
		Swal.fire({
			title: 'Are you sure delete "' + username + '"?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/rest/accounts/${item.username}`).then(resp => {
					var index = $scope.items.findIndex(p => p.username == item.username);
					$scope.items.splice(index, 1);
					$scope.initialize();
					$scope.reset();
					Swal.fire(
						'Deleted!',
						'Account "' + username + '" has been deleted.',
						'success'
					)
				}).catch(error => {
					Swal.fire(
						'Delete Failure!',
						'Can not delete "' + username + '" !',
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
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			// alert("Lỗi upload hình ảnh");

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

	$scope.GoldenCustomer = function() {
		$http.get("/rest/accounts/goldencustomer").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.countOrders = resp.data;
				$scope.title = "Rank Customer"
				$('#AccountMuaNhieuNhatModalCenter').appendTo("body").modal('show');
			}
		});
	}

	$scope.test1 = function() {
		$http.get("/rest/accounts/test").then(resp => {
			$scope.test = resp.data;
		});
	}

	$scope.SilverCustomer = function() {
		$http.get("/rest/accounts/goldencustomer/2").then(resp => {
			$scope.countOrders = resp.data;
			$scope.title = "Silver Customer"
			$('#AccountMuaNhieuNhatModalCenter').appendTo("body").modal('show');
		});
	}

	$scope.LoyalCustomer = function() {
		$http.get("/rest/accounts/loyalcustomer").then(resp => {
		if(resp.data.length == 0){
			$('#NoDataModalCenter').appendTo("body").modal('show');
		}else{	
			$scope.items = resp.data;
			$scope.message = "Loyal Customer: " + resp.data.length + " Accounts"
			$scope.to = null;
		}
		});
	}

	$scope.findbyDate = function() {
		$('#AccountDuocTaoModalCenter').appendTo("body").modal('show');
	}

	$scope.detailOrder = function(account) {
		$http.get(`/rest/orders/getorder/${account.username}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$('#MyOrderModalCenter').appendTo("body").modal('show');
				$('#AccountMuaNhieuNhatModalCenter').appendTo("body").modal('hide');
				$scope.myorders = resp.data;
				$scope.account = account;
				$scope.countOrder = resp.data.length;
				$http.get(`/rest/orders/getTotalPro/${account.username}`).then(resp => {
					$scope.totalPro = resp.data;
				});
				$http.get(`/rest/orders/getTotalBill/${account.username}`).then(resp => {
					$scope.totalBill = resp.data;
				});
			}
		});
	}

	$scope.closeOrder = function() {
		$('#MyOrderModalCenter').appendTo("body").modal('hide');
		$('#AccountMuaNhieuNhatModalCenter').appendTo("body").modal('show');
	}

	$scope.closeOrderDetail = function() {
		$('#MyOrderModalCenter').appendTo("body").modal('show');
		$('#MyOrderDetailModalCenter').appendTo("body").modal('hide');
	}

	$scope.viewOrderDetail = function(item) {
		$http.get(`/rest/orders/myorder/${item.id}`).then(resp => {
			$scope.ODitems = resp.data;
			$scope.order = item;
			$('#MyOrderDetailModalCenter').appendTo("body").modal('show');
			$('#MyOrderModalCenter').appendTo("body").modal('hide');
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


	$scope.duoctao = function() {
		var duoctao = angular.copy($scope.duoctao);
		$http.get(`/rest/accounts/duoctao/${duoctao.from}/${duoctao.to}`).then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
				$('#AccountDuocTaoModalCenter').appendTo("body").modal('hide');
			}else{
				$scope.items = resp.data;
				$scope.from = duoctao.from;
				$scope.to = duoctao.to;
				$scope.countDate = resp.data.length;
				$scope.message = null;
				$('#AccountDuocTaoModalCenter').appendTo("body").modal('hide');
			}
		}).catch(error => {
			//alert("Lỗi tìm đơn hàng");

			Swal.fire({
				title: 'Please select a date',
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

	$scope.pager1 = {
		page: 0,
		size: 4,
		get countOrders() {
			var start = this.page * this.size;
			return $scope.countOrders.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.countOrders.length / this.size);
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
		size: 8,
		get myorders() {
			var start = this.page * this.size;
			return $scope.myorders.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.myorders.length / this.size);
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
	
	

	$scope.showWebcam = function(){
		url = "http://localhost:8080/webcam"
		window.open(url, '_blank', "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=-300,width=680,height=580");
	}
});
