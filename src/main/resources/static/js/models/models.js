window.LoginInfo = Backbone.Model.extend({
    urlRoot: "/login",
    defaults: {
        username: "",
        password: "",
        message: ""
    }
	
});

window.Menu = Backbone.Model.extend({
    defaults: {
        page: 1
    }
});

var Country = Backbone.Model.extend();
var CountryCollection = Backbone.Collection.extend({
    model: Country,
    url: "/country"

});


window.User = Backbone.Model.extend({
    urlRoot: "/users",
    defaults: {
        id: null,
        firstName: "",
        lastName: "",
        emailId: ""
       
    }
	
});

window.UserCollection = Backbone.Collection.extend({
    model: User,
    url: "/users"

});

window.Address = Backbone.Model.extend({
    defaults: {
        id: null,
        addressLine1 : '',
		addressLine2: '',
		city: '',
		state: '',
		pin: '',
		country: '',
		type: "RGTD"
       
    }
	
});

window.AddressCollection = Backbone.Collection.extend({
	model: Address
	
});


window.Contact = Backbone.Model.extend({
    defaults: {
        id: null,
        name: '',			
		type: '',
		uniqueId: '',
		dateOfIncorporation: '',
		phone: '',
		email: '',
		addresses: new AddressCollection()
    }, 
    initialize : function(){
    	this.addresses = new AddressCollection(this.get('addresses'));
    	this.addresses.parent = this;
  
    },
    parse : function(data){
        var addresses = new AddressCollection(data.addresses);
       /* $.each( data.addresses, function(index, val) {
        	addresses.push(val);
        });*/
        data.addresses = addresses;
    	return data;
    }
    
	
});

window.ContactCollection = Backbone.Collection.extend({
    model: Contact,
    url: "/contacts",
    parse : function(response){
		return response;
	}

});


window.Bank = Backbone.Model.extend({
    defaults: {
    	id:null,
    	name:'',
    	branch:'',
    	city:'',
    	country:'',
    	routingCode:'',
    	accountNumber:'',
    	swift:'',
    	iban:'',
    	currency:''
    }
	
});

window.BankCollection = Backbone.Collection.extend({
    model: Bank,
    url: "/banks"

});


window.Product = Backbone.Model.extend({
    defaults: {
    	id:null,
    	name:'',
    	branch:'',
    	city:'',
    	country:'',
    	routingCode:'',
    	accountNumber:'',
    	swift:'',
    	iban:'',
    	currency:''
    }
});

window.ProductCollection = Backbone.Collection.extend({
    model: Product,
    url: "/products"

});


