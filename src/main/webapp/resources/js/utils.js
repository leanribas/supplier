var Utils = function() {

	var notifyInfo = function(message) {
		$.notify({
			message : message
		}, {
			allow_dismiss : true,
			type : 'info',
			delay : 4000,
			offset : {
				x : 20,
				y : 60
			},
			mouse_over : 'pause',
			placement : {
				from : "top",
				align : "center"

			}
		});
	};	
	
	var notifyDanger = function(message) {
		$.notify({
			message : message
		}, {
			allow_dismiss : true,
			type : 'danger',
			delay : 4000,
			offset : {
				x : 20,
				y : 60
			},
			mouse_over : 'pause',
			placement : {
				from : "top",
				align : "center"

			}
		});
	};

	var notifySuccess = function(message) {
		$.notify({
			message : message
		}, {
			allow_dismiss : true,
			type : 'success',
			delay : 2000,
			// delay: 0,
			offset : {
				x : 20,
				y : 60
			},
			mouse_over : 'pause',
			placement : {
				from : "top",
				align : "center"

			}
		});
	};
	
	return {
		
		notifyInfo : notifyInfo,
		notifyDanger : notifyDanger,
		notifySuccess : notifySuccess		
	}

}();
