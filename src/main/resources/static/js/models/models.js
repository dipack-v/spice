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

window.Country = Backbone.Model.extend({
    defaults: {
        code:'',
        desc:''
    }
});

window.CountryCollection = Backbone.Collection.extend({
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



window.Contact = Backbone.Model.extend({
    defaults: {
        id: null,
        name: '',			
		type: '',
		uniqueId: '',
		dateOfIncorporation: '',
		addressLine1:'',
		addressLine2:'',
		city:'',
		pin:'',
		state:'',
		country : 0,
		phone: '',
		email: '',
		country : 0,
		addresses: [
//		             {
//			addressLine1 : '',
//			addressLine2: '',
//			city: '',
//			state: '',
//			pin: '',
//			country: 0,
//			type: "RGTD"
//		},
//		{
//			addressLine1 : '',
//			addressLine2: '',
//			city: '',
//			state: '',
//			pin: '',
//			country: 0,
//			type: "COMM"
//		}
		]
       
    }
	
});

window.ContactCollection = Backbone.Collection.extend({
    model: Contact,
    url: "/contacts"

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


