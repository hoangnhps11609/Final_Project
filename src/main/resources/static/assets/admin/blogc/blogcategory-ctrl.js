app.controller("blogcategory-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load blogcategories
		$http.get("/rest/blogcategories").then(resp => {
		if(resp.data.length == 0){
				$('#NoDataModalCenter').appendTo("body").modal('show');
			}else{	
			$scope.items = resp.data;
			}
		});

	}

	//Khởi tạo
	$scope.initialize();

	//Xóa form
	$scope.reset = function() {
		$scope.form = {};
		$scope.isEdit = null;
		$scope.message1 ="";
		$scope.vName = false;
		
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

	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav a:eq(0)").tab('show');
		document.getElementById("homes").style.display = "block";
		document.getElementById("lists").style.display = "none";
				$scope.isEdit = "true";
		$scope.vName = true;
		
		
	}

	//Thêm sản phẩm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var name = item.name;
		
		Swal.fire({
			  title: 'Confirm adding "' + name + '" to the blog category list?',
			  text: "",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.isConfirmed) {
				$http.post(`/rest/blogcategories`, item).then(resp => {
					$scope.items.push(resp.data);
					$scope.reset();
					$scope.initialize();
				Swal.fire(
				      'Successfully!',
				      'Added "'+ name +'" to blog category list.',
				      'success'
				    )
			$(".nav-tabs a:eq(1)").tab('show');
		
		}).catch(error => {			
			Swal.fire(
			      'Create Failure!',
			      'Can not add "'+ name +'" !',
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
			  text: "New information will be saved to the blog category list",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes'
			}).then((result) => {
			  if (result.isConfirmed) {
				$http.put(`/rest/blogcategories/${item.id}`, item).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items[index] = item;
					$scope.reset();
					$scope.initialize();
			
				Swal.fire(
				      'Successfully!',
				      'Updated "'+ name +'" to blog category list.',
				      'success'
			    	)
			
			$(".nav a:eq(1)").tab('show');
		}).catch(error => {
			Swal.fire(
			      'Update Failure!',
			      'Can not update "'+ name +'" !',
			      'error'
			    )
			
			console.log("Error", error);

		});
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
			  	$http.delete(`/rest/blogcategories/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					$scope.reset();
					$scope.initialize();
					Swal.fire(
			      'Deleted!',
			      'Blog category "'+ name +'" has been deleted.',
			      'success'
			    )
		}).catch(error => {
			Swal.fire(
			      'Delete Failure!',
			      'Can not delete "'+ name +'" !',
			      'error'
			    )
			
			console.log("Error", error);

		});
		
			  }
			})
	}

	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		}, first(){
			this.page = 0;
		}, prev(){
			this.page--;
			if(this.page<0){
				this.last();
			}
		}, next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		}, last(){
			this.page = this.count-1;
		}
	}
});