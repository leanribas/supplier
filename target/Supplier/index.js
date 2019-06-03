var app = angular.module("supplierManager", []);

app.controller("supplierController", function($scope, $http) {

	_getSupplierData();

	
	$scope.supplierForm = {
		id : -1,
		name: "",
		email: "",
		cnpj: "",
		comment: ""
	};
	
	$scope.titleModal = "";
	$scope.supplier = null;

	$scope.submitSupplier = function() {
		
		var method = "";

		if ($scope.supplierForm.id == -1) {
			method = "POST";
			url = 'rest/supplierlist';
		} else {
			method = "PUT";
			url = 'rest/supplierlist';
		}

		$http({
			method : method,
			url : url,
			data : angular.toJson($scope.supplierForm),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(_success, _error);
	};

	
	$scope.registerSupplier = function(){
		_clearFormData();
		$scope.titleModal = "Inserir Fornecedor";
		$("#supplierModal").modal("show");
		
	};
	
	
	$scope.updateSupplier = function(supplier){
		_clearFormData();
		$scope.titleModal = "Editar Fornecedor";
		$scope.supplierForm = Object.assign({},supplier);
		$("#supplierModal").modal("show");
		
	};	
	
	$scope.confirmDeleteSupplier = function(supplier){
		$scope.supplier = supplier;
		$("#deleteSupplierModal").modal("show");
	};	

	
	$scope.deleteSupplier = function() {
		$("#deleteSupplierModal").modal("hide");
		$http({
			method : 'DELETE',
			url : 'rest/supplierlist',
			data : angular.toJson($scope.supplier),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(){
		       $scope.supplier=null;
		       _getSupplierData();
		},_error);
		
	};
	
	
	function _getSupplierData() {
		$http({
			method : 'GET',
			url : 'rest/supplierlist'
		}).then(function successCallback(response) {
			$scope.supplierlist = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}

	function _success(response) {
		$("#supplierModal").modal("hide");
		_getSupplierData();				
		_clearFormData();
	}

	function _error(response) {
		Utils.notifyDanger(response.data.message);
	}

	function _clearFormData() {
		$scope.supplierForm.id = -1;
		$scope.supplierForm.name = "";
		$scope.supplierForm.email = "";
		$scope.supplierForm.cnpj = "";
		$scope.supplierForm.comment = "";
	}		

});