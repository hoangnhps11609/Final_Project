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
	
	/*QUẢN LÝ GIỎ HÀNG*/
	$scope.cart = {
		items:[],
		
		//Thêm sản phẩm vào giỏ hàng
		add(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			}
			else {
				$http.get(`/rest/productdetails/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
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
					this.items.splice(item, 1);
					this.saveToLocalStorage();
				}
			}
		},
		
		//Xóa sản phẩm khỏi giỏ hàng
		remove(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		
		//Xóa sạch các mặt hàng trong giỏ
		clear(){
			this.items = []
			this.saveToLocalStorage();
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
	
	$scope.cart.loadFormLocalStorage();




	$scope.order={
		createDate: new Date(),
		address: "",
		account: {username: $("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item => {
				return{
					productDetail: {id: item.id},
					price: item.product.price,
					quantity: item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				//alert("Đặt hàng thành công!");
				Swal.fire({
				  icon: 'success',
				  title: 'Order Success',
				  text: 'Check your order at "My Orders"',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
				$scope.cart.clear();
				location.href = "/productdetail/update/" + resp.data.id;
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
        	 Swal.fire({
				  icon: 'error',
				  title: 'Error image',
				  //text: '',
				  //footer: '<a href="">Why do I have this issue?</a>'
				})
            console.log("Error", error);
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
			  'success'
			)
			location.href = "/product/detail/" + resp.data.product.id + "?sizepro=1";
		}).catch(error => {
			//alert("Lỗi thêm sản phẩm");
			Swal.fire(
			  'Error comment',
			  'error'
			)
			console.log("Error", error);
		});
	}
	
	$scope.colorinitialize = function() {
		//load accounts
		$http.get("/rest/colors").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(coloritem => {
			})
		});
	}
	
	//Khởi tạo
	$scope.colorinitialize();
	
});

