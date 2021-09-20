const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl", function($scope, $http){
	// alert("WELCOME TO FPT POLYTECHNIC")
	
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
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
			alert("Thêm sản phẩm vào giỏ hàng thành công")
		},
		
		//Cộng sản phẩm có sẵn trong giỏ hàng
		addcart(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			}
			else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
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
				.map(item => item.qty * item.price)
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
					product: {id: item.id},
					price: item.price,
					quantity: item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng thất bại!")
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
			alert("Thêm mới thành công");
			$(".nav-tabs a:eq(1)").tab('show');
		}).catch(error => {
			alert("Lỗi thêm sản phẩm");
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
					product: {id:item.id},
					price: item.price,
					quantity: item.qty
				}
			});
		},
		
		purchase(){
			var order = angular.copy(this);
			//Thực hiện đặt hàng
			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hằng thất bại")
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
            alert("Đăng ký thành công");
           location.assign ("http://localhost:8080/");
        }).catch(error =>{
            alert("Lỗi đăng ký");
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
            alert("Lỗi upload hình ảnh!");
            console.log("Error", error);
        })
    }
});

