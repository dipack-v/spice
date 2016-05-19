$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader("X-CSRF-TOKEN", $.cookie('XSRF-TOKEN'));
	//xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
});

$(document).ajaxSuccess(function(e, xhr, options) {
	//console.log("ajaxSuccess ->"+xhr.status)
});

$(document).ajaxError(function(e, xhr, options) {
	if(xhr.status == 401){
		var loginInfo = new LoginInfo();
		$(document.body).html( new LoginView({model: loginInfo}).$el);
	}
});

$(document).ajaxComplete(function(e, xhr, options) {
	//console.log("ajaxComplete ->"+xhr)
});
$(function(){
	window.vent = _.extend({}, Backbone.Events);
	var AppRouter = Backbone.Router.extend({
	
	    routes: {
	        ""                  : "beforeShow",
	        "page/:page"	    : "beforeShow",
	        "login"	            : "showLogin"
	    },
	
	    initialize: function () {
		  
	    },
	    
	    beforeShow : function(page){
	    	 var self = this;
	    	 $.get('alive', function(){
	    		 self.showPage(page);
	    	 });
	    },
	    
	    showLogin : function(){
	    	var loginInfo = new LoginInfo();
			$(document.body).html( new LoginView({model: loginInfo}).$el);
	    },
	    
	
	    showPage: function(page) {
	        var p = page ? parseInt(page, 10) : 1;
	        $( document.body ).html( new AppView({model:new Menu({'page':page})}).$el);
	        switch (p) {
	            case 1:                    	
	                var contacts = new ContactCollection();
	        	    contacts.fetch().done(function(){
	        	    	var contactListView =  new ContactListView({collection: contacts});
	        	    	$( '#content' ).html( contactListView.$el);
	        	    	
	        	    });
	                break;
	            case 2 : 
	                var banks = new BankCollection();
	        	    banks.fetch().done(function(){
	        	    	var bankListView =  new BankListView({collection: banks});
	        	    	$( '#content' ).html( bankListView.$el);
	        	    	
	        	    });
	                break;
	            case 3:
		        	$( '#content' ).html( new ProductEditView().$el);
		            break;
	            case 4:
	            	$( '#content' ).html( new ConfirmView().$el);
	                break;
	            default:
	            	console.log('dafault');
	                break;
	        }
	        
	    }
	   
	
	});
	
	utils.loadTemplate(['LoginView', 'AppView', 'ContactView', 'ContactEditView', 'BankView', 'BankEditView', 'ProductEditView', 'ConfirmView'], function() {
	   
		app = new AppRouter();
 	    Backbone.history.start();
 	    window.countries = new CountryCollection();

		
	});
	
	
	
});