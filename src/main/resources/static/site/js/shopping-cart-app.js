const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl", function($scope, $http){
	// alert("WELCOME TO FPT POLYTECHNIC")
//	Swal.fire({
//	  title: 'WELCOME TO FASHISHOP!',
//	  text: 'Team 17 FPT Polytechnic',
//	  imageUrl: 'https://caodang.fpt.edu.vn/wp-content/uploads/OK4-1-768x638.png',
//	  imageWidth: 468,
//	  imageHeight: 338,
//	  imageAlt: 'Custom image',
//	})

	$scope.form = {};
	
	
	/*QUẢN LÝ GIỎ HÀNG*/
	$scope.cart = {
		items:[],
		
		//Thêm sản phẩm vào giỏ hàng
		add(id){
			var item = this.items.find(item => item.id == id);
					if(item){
						$http.get(`/rest/productdetails/${id}`).then(resp => {
							$scope.proDe = resp.data;
							if(item.qty < $scope.proDe.quantity){
								item.qty++;
								this.saveToLocalStorage();
							}else{
								item.qty;
								this.saveToLocalStorage();
								//alert("Out-Of-Stock Product!")
								Swal.fire('Out-Of-Stock Product')
							}
						})
					}
					else {
						$http.get(`/rest/productdetails/${id}`).then(resp => {
						$scope.proDe = resp.data;
							if($scope.proDe.quantity > 0){
								resp.data.qty = 1;
								this.items.push(resp.data);
								this.saveToLocalStorage();
							}else{
								//alert("Out-Of-Stock Product!")
								Swal.fire('Out-Of-Stock Product')
							}
						})
					}
			//alert("Thêm sản phẩm vào giỏ hàng thành công")
			Swal.fire(
			  'Thanks!',
			  'Add to cart successfully!',
			  'success'
			)
		},
		
		//Cộng sản phẩm có sẵn trong giỏ hàng
		addcart(id){
			var item = this.items.find(item => item.id == id);
					if(item){
						$http.get(`/rest/productdetails/${id}`).then(resp => {
							$scope.proDe = resp.data;
							if(item.qty < $scope.proDe.quantity){
								item.qty++;
								this.saveToLocalStorage();
							}else{
								item.qty;
								this.saveToLocalStorage();
								//alert("Out-Of-Stock Product!")
								Swal.fire('Out-Of-Stock Product')
							}
						})
					}
					else {
						$http.get(`/rest/productdetails/${id}`).then(resp => {
						$scope.proDe = resp.data;
							if($scope.proDe.quantity > 0){
								resp.data.qty = 1;
								this.items.push(resp.data);
								this.saveToLocalStorage();
							}else{
								//alert("Out-Of-Stock Product!")
								Swal.fire('Out-Of-Stock Product')
							}
						})
					}
				
		},
		
		//Trừ sản phẩm có sẵn trong giỏ hàng
		subcart(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				if(item.qty>1){
					item.qty--;
					this.saveToLocalStorage();
				}else{
					Swal.fire('Min Quantity is 1');
					this.saveToLocalStorage();
				}
			}
		},
		
		//Xóa sản phẩm khỏi giỏ hàng
		remove(id){
			var index = this.items.findIndex(item => item.id == id);
			Swal.fire({
				title: 'Are you sure delete?',
				text: "You won't be able to revert this!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
				if (result.isConfirmed) {
					this.items.splice(index, 1);
					this.saveToLocalStorage();
					location.href = "http://localhost:8080/cart/view";
				}
			})	
			
		},
		
		//Xóa sản phẩm khỏi giỏ hàng header
		removeHD(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		
		//Xóa sạch các mặt hàng trong giỏ
		clear(){
			Swal.fire({
				title: 'Are you sure clear Cart?',
				text: "You won't be able to revert this!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
				if (result.isConfirmed) {
					this.items = []
					this.saveToLocalStorage();
					location.href = "http://localhost:8080/cart/view";
				}
			})	
		},
		
		
		
		clearPM(){
			this.items = []
			this.saveToLocalStorage();
			if(this.items >= 1){
				this.items = []
			}
		},
		
		//Tính thành tiền của một 1 sản phẩm
		amt_of(item){},
		
		//Tính tổng số lượng các mặt hàng trong giỏ
		get count(){
			return this.items
				.map(item => item.qty)
				.reduce((total,qty) => total += qty, 0);
		},
		
		//Tổng thành tiền các mặt hàng trong giỏ
		get amount(){
			return this.items
				.map(item => item.qty * item.product.price*(1-item.product.discount*0.01))
				.reduce((total,qty) => total += qty, 0);
		},
		
		//Lưu giỏ hàng vào Local Storge
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		
		//Đọc giỏ hàng bằng local Storage
		loadFormLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json):[];
		}
		
		
	}
	
		//load voucher
	$http.get("/rest/vouchers").then(resp => {
			$scope.vouchers = resp.data;
	});
	
	$scope.cart.loadFormLocalStorage();

	$scope.showPayment = function(){
		$('#PaymentModalCenter').appendTo("body").modal('show');
	}
	
	$scope.vUsername = false;
	$scope.validateRegisterUsername = function(u) {
		const specialRegex = /^[A-Za-z0-9 ]+$/;
		if( u.length < 6 || u.length > 25){
			$scope.message1 = "Username must between 6 to 25 characters";
			$scope.vUsername = false;	
		}else if(!u.match(specialRegex)){
			$scope.message1 = "Username not allow special character or symbol";
			$scope.vUsername = false;
		}else{
			$http.get(`/rest/accounts/getValidation/${u}`).then(resp => {
			if(resp.data.length == 0){
				$scope.message1 = "";
				$scope.vUsername = true;
			}else{
				$scope.message1 = "Username was registered";
				$scope.vUsername = false;
			}
			})
		}
	}
	
	
	$scope.vPassword = false;
	$scope.validateRegisterPassword = function(p) {
		if(p.length < 6 || p.length > 25){
			$scope.message2 = "Password must between 6 to 25 characters";
			$scope.vPassword = false;
		}else{
			$scope.message2 = "";
			$scope.vPassword = true;
		}
	}

	$scope.vFullname = false;
	$scope.validateRegisterFullname = function(f) {
		const specialRegex = /^[^*?&!@#$%()^_+={}|\:";'<>?,.0-9~`]{0,30}$/;
		if(f.length == 0 || f.length > 30){
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
	 $('#tooltip-demo').tooltip()
	
	
	$scope.OrderStatus = false;
	$scope.orderStatus = function(s) {
		$scope.OrderStatus = true;
	}
	
	$scope.vEmail = false;
	$scope.validateRegisterEmail = function(e) {
		const emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if(e == null){
			$scope.message4 = "Email cannot null";
			$scope.vEmail = false;
		}else if (!e.match(emailRegex)){
			$scope.message4 = "Invalid Email";
			$scope.vEmail = false;
		}else{
			$http.get(`/rest/accounts/getValidation/${e}`).then(resp => {
			if(resp.data.length == 0){
				$scope.message4 = "";
				$scope.vEmail = true;
			}else{
				$scope.message4 = "Email was registered";
				$scope.vEmail = false;
			}
			})
		}
	}
	
	$scope.vPhone = false;
	$scope.validateRegisterPhone = function(p) {
		const phoneRegex = /^(032|033|034|035|036|037|038|039|070|079|077|076|078|083|084|085|081|082|050|051|052|053|054|055|056|057|058|059|086|096|097|098|088|091|094|089|090|093|092|099)+([0-9]{7})$/;
		const numberRegex = /^\d+$/;
	
		if( !p.match(phoneRegex) || !p.match(numberRegex)){
			$scope.message5 = "Invalid Phonenumber";
			$scope.vPhone = false;
		}else{
			$http.get(`/rest/accounts/getValidation/${p}`).then(resp => {
				if(resp.data.length == 0){
					$scope.message5 = "";
					$scope.vPhone = true;
				}else{
					$scope.message5 = "Number Phone was registered";
					$scope.vPhone = false;
				}
			})
		}
	}
	
	$scope.validateCheckoutPhone = function(p) {
		const phoneRegex = /^(032|033|034|035|036|037|038|039|070|079|077|076|078|083|084|085|081|082|050|051|052|053|054|055|056|057|058|059|086|096|097|098|088|091|094|089|090|093|092|099)+([0-9]{7})$/;
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
	$scope.validateRegisterAddress = function(a) {	
		if( a == null || a.length == null || a.length <= 0 || a.length > 200){
			$scope.message6 = "Address invalid";
			$scope.vAddress = false;
		}else{
			$scope.message6 = "";
			$scope.vAddress = true;
			}
	}


	$scope.voucherValue = function(){
		var vouchername = angular.copy($scope.form);
		if($scope.form.name == null){
			Swal.fire('Voucher Code is not Null');
		}else{
			$http.get(`/rest/orders/vouchers/${vouchername.name}`).then(resp => {
				if(resp.data.length == 0){
					Swal.fire('Voucher Invalid');
				}else if($scope.cart.amount < resp.data.value*5){
					Swal.fire('This voucher is not use with bill less $' + resp.data.value*5);
				}else{
					$scope.voucher = resp.data;
					Swal.fire('Voucher $' + $scope.voucher.value + ' is added!');
				}
			});
		}
	}
	
	$scope.deleteVoucher = function(){
		$scope.voucher = null;
		$scope.form.name = "";
	}

	$scope.order={
		createDate: new Date(),
		address: "",
		account: {username: $("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item => {
				return{
					productDetail: {id: item.id},
					price: item.product.price*(1 - item.product.discount*0.01),
					quantity: item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this); 
			var voucher = $scope.voucher;
			$http.post("/rest/orders", order).then(resp => {
				var orderId = resp.data.id;
				if(voucher == null){
					$http.put(`/rest/orders/info/cashOnDelivery`, orderId).then(resp =>{
						//alert("Đặt hàng thành công!");
						Swal.fire({
						  icon: 'success',
						  title: 'Order Success',
						  text: 'Check your order at "My Orders"',
						  //footer: '<a href="">Why do I have this issue?</a>'
						})
						$scope.cart.clearPM();
						location.href = "/productdetail/update/" + resp.data.id;
					})			
				}else{
					$http.put(`/rest/orders/info/cashOnDelivery/${voucher.name}`, orderId).then(resp =>{
						//alert("Đặt hàng thành công!");
						Swal.fire({
						  icon: 'success',
						  title: 'Order Success',
						  text: 'Check your order at "My Orders"',
						  //footer: '<a href="">Why do I have this issue?</a>'
						})
						$scope.cart.clearPM();
						location.href = "/productdetail/update/" + resp.data.id;
					})			
				}
			}).catch(error => {
				//alert("Đặt hàng thất bại!")
				Swal.fire({
				  icon: 'error',
				  title: 'Order error',
				  text: 'Please check the information again!',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
				console.log(error)
			})
		},
		
		
		purchasePayment(){
			var order = angular.copy(this);
			var voucher = $scope.voucher;
			$http.post("/rest/orders/paypal", order).then(resp => {
				var orderId = resp.data.id;
				if(voucher == null){
					$http.put(`/rest/orders/info/paypal`, orderId).then(resp =>{
						location.href = "/pay/" + resp.data.id;
						$scope.cart.clearPM();
					})	
				}else{
					$http.put(`/rest/orders/info/paypal/${voucher.name}`, orderId).then(resp =>{
						location.href = "/pay/" + resp.data.id;
						$scope.cart.clearPM();
					})	
				}
				
			}).catch(error => {
				//alert("Đặt hàng thất bại!")
				Swal.fire({
				  icon: 'error',
				  title: 'Order error',
				  text: 'Please check the information again!',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
				console.log(error)
			})
		}
	}
	
	$scope.vMin = false;
	$scope.validateMin = function(mi){
		if(mi == null){
			$scope.vMin = false;
		}
		$scope.sMin = mi;
	}
	
	$scope.vMax = false;
	$scope.validateMax = function(ma){
	alert('min');
		if(ma == null){
			$scope.vMax = false;
		}
		$scope.sMax = ma;
	}
	
	//Thêm account mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			//alert("Thêm mới thành công");
			Swal.fire({
				  icon: 'success',
				  title: 'Successfully',
				  text: 'Account has been created successfully',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
			location.href = "/";
		}).catch(error => {
			//alert("Lỗi thêm sản phẩm");
			Swal.fire({
				  icon: 'error',
				  title: 'Account error',
				  text: 'Please check the information again!',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
			console.log("Error", error);
		});
	}
	
	$scope.ordernoaccount = {	
		createDate: new Date(),
		address: "",
		account: {username: $("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item => {
				return {
					productDetail: {id:item.id},
					price: item.product.price,
					quantity: item.qty
				}
			});
		},
		
		purchase(){
			var order = angular.copy(this);
			//Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => {
				//alert("Đặt hàng thành công");
				Swal.fire({
				  icon: 'success',
				  title: 'Order Success',
				  text: 'Check your order at "My Orders"',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				//alert("Đặt hằng thất bại")
				Swal.fire({
				  icon: 'error',
				  title: 'Order error',
				  text: 'Please check the information again!',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
				console.log(error)
			})
			
		}
	}

	$scope.edit = function(item){
        $scope.form = angular.copy(item);
        
    }
    
    $scope.items =[];
    $scope.form = {};
    
    
   $scope.create =function(){
        var item = angular.copy($scope.form);
        $http.post(`/rest/accounts`, item).then(resp =>{
            $scope.items.push(resp.data);
            //alert("Đăng ký thành công");
            Swal.fire({
				  icon: 'success',
				  title: 'Successfully',
				  text: 'Account has been created successfully',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
           location.assign ("http://localhost:8080/");
        }).catch(error =>{
            //alert("Lỗi đăng ký");
            Swal.fire({
				  icon: 'error',
				  title: 'Account error',
				  text: 'Please check the information again!',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
            console.log("Error, error");
        });
    }
    
    $scope.imageChanged = function(files){
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data,{
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp =>{
            $scope.form.photo = resp.data.name;
        }).catch(error => {
//        	 Swal.fire({
//				  icon: 'error',
//				  title: 'Error image',
//				  //text: '',
//				  //footer: '<a href="">Why do I have this issue?</a>'
//				})
//            console.log("Error", error);
        })
    }
    
    //Thêm Cmt mới
	$scope.createCmt = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/comments`, item).then(resp => {
			$scope.items.push(resp.data);
			//alert("Thêm mới thành công");
			Swal.fire(
			  'Comment successfully',
			  '',
			  'success'
			)
			location.href = "/product/detail/" + resp.data.product.id + "?sizepro=1";
		}).catch(error => {
			//alert("Lỗi thêm sản phẩm");
			Swal.fire(
			  'Error comment',
			  '',
			  'error'
			)
			console.log("Error", error);
		});
	}
	
	$scope.colorinitialize = function() {
		//load accounts
		$http.get("/rest/colors/top10").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(coloritem => {
			})
		});
	}
	
	//Khởi tạo
	$scope.colorinitialize();
	
	
	$scope.showWebcam = function(){
		url = "http://localhost:8080/webcam"
		window.open(url, '_blank', "toolbar=yes,scrollbars=yes,resizable=yes,top=200,left=-1300,width=680,height=580");
	}
	
	$scope.viewDetailInCart = function(item){
		location.href = "/product/detail/" + item + "?sizepro=1";
	}
	
	
	$scope.ChangeOrderInfo = function(orderId){
		alert(orderId);
		alert("dasds");
	}
});

