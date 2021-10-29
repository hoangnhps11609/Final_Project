app.controller("comment-ctrl", function ($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function () {
		$http.get("/rest/comments").then(resp => {
			$scope.message = "";		
			$scope.items = resp.data;
			
		});

	}
	
	
	$scope.XemTatCaCommentChuaDoc = function () {
		$http.get("/rest/comments/chuadoc").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.message = "Search by: Unread";
			
			$scope.items = resp.data;
			}
		});

	}
	
		$scope.XemContent = function (item) {		
			$('#ContentModalCenter').appendTo("body").modal('show');
			$scope.comment = item;
	}

	
	
	
		$scope.XemTatCaCommentDaDoc = function () {
		//load categories
		$http.get("/rest/comments/dadoc").then(resp => {
			if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
			$scope.message = "Search by: Read";
			
			$scope.items = resp.data;
			}
		});

	}
	
	
		$scope.search = function () {
		var statistic = angular.copy($scope.statistic);
		$http.get(`/rest/comments/${statistic.from}`).then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{
				$scope.message = "Search by Keyword: " + statistic.from;
				$scope.to = null;
				$scope.statistic.from = "";
			$scope.items = resp.data;
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
	input.addEventListener("keyup", function (event) {
		if (event.keyCode === 13) {
			event.preventDefault();
			$scope.search();

		}
	});
	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function () {
		$scope.form = {};
	}
	
	
	$scope.test = function () {
		alert('Test');
	}

	//hiển thị lên form
	$scope.edit = function (item) {
		
			$http.put(`/rest/comments/get/${item.id}`, item).then(resp => {
			$scope.initialize();
		}).catch(error => {	
			alert('Loi');		
			console.log("Error", error);

		});
		url = "http://localhost:8080/product/detail/"+item.product.id+"?sizepro=1"
		window.open(url, '_blank');
		$scope.initialize();
		
	}
	

	$scope.pager = {
		page: 0,
		size: 10,
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
		page: 0,
		size: 5,
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
