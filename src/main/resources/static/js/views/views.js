window.LoginView = Backbone.View.extend({
	className:'row main-content',
	
	events: {
		"change" : "change",
        "click button#submit"   : "login"
	},
	
    initialize: function () {
    	this.render();
    },
    
    change: function (event) {
        // Apply the change to the model
        var target = event.target;
        var change = {};
        change[target.name] = target.value;
        this.model.set(change);
    },
    
    render: function () {	
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
    
    login: function(e){
    	e.preventDefault();
    	var self = this;
     	var data = {'username': this.model.get('username'), 'password': this.model.get('password'), "_csrf": $.cookie('XSRF-TOKEN') };
        $.ajax({
            data: data,
            timeout: 1000,
            headers: {
                "X-CSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },
            type: 'POST',
            url: '/login',
            global: false //prevent from triggering global ajax events

        }).done(function(data, textStatus, jqXHR) {
        	 app.navigate("#page/1?"+new Date().getTime(), {trigger: true});
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	self.model.set('message', 'Invalid login details');
    		$(document.body).html( new LoginView({model: self.model}).el);
        });
    	
    	
    }
    
});

window.AppView = Backbone.View.extend({
	className: 'container-fluid',
	events: {
        "click a#logout"   : "logout"
	},
	
    initialize: function () {
    	this.render();
    },
    render: function () {	
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
    
    logout: function(e){
		e.preventDefault();
	 	$.ajax({
	        method: 'POST',
	        url: '/logout',
	        data: { "_csrf": $.cookie('XSRF-TOKEN') }
	    }).done(function(data, textStatus, jqXHR) {
	    	$.removeCookie('XSRF-TOKEN', { path: '/' });
	    	app.navigate("#login", {trigger: true});
	    	
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert('Booh! Wrong credentials, try again!');
        });
    }
});


window.ContactListView = Backbone.View.extend({
    initialize: function () {
        this.render();
        vent.on('user:edit', this.editUser, this);
    },

    render: function () {
        this.collection.each(this.addOne, this);
        return this;
    },
    
    addOne:function(contact){
    	var contactView = new ContactView({model: contact});
    	this.$el.append(contactView.render().el);
    },
    
    editUser:function(contact){
    	var contactEditView = new ContactEditView({model: contact});
    	this.$el.html(contactEditView.el);
   
    }
    
});



window.ContactView = Backbone.View.extend({
	events: {
        "click .edit"   : "editUser",
        "click .next-page" : "gotoNextPage"
	},
	
    initialize: function () {
    	this.model.on('destroy', this.unrender, this);
        this.model.on('change', this.render, this);
    },
    
    render: function () {	
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
    
    editUser:function(e){
    	e.preventDefault();
    	vent.trigger('user:edit', this.model);  
    },
    gotoNextPage: function(e) {
        e.preventDefault();
        app.navigate("#page/2", {trigger: true, replace: true});
    },
    
    deleteUser:function(){
    	this.model.destroy();
    },
    
    unrender:function(){
    	this.remove();
    }
    
});



window.ContactEditView = Backbone.View.extend({
    initialize: function () {
    	this.render();
    },
    
    events: {
        "change"        : "change",
        'click a.save'  : 'beforeSave',
        'click button.cancel': 'cancel'
    },

    change: function (event) {
     
        // Apply the change to the model
        var target = event.target;
        var change = {};
        if($(target).data('address')  === undefined){
        	change[target.name] = target.value;
        	 this.model.set(change);
        }else{
        	change[$(target).data('address')] = target.value;
        	this.model.get('addresses').at(0).set(change);
        	
        }
    },
    
    beforeSave: function(e) {
        e.preventDefault();
        this.model.save(null,{success:function(){
        	 app.navigate("#page/1?"+new Date().getTime(), {trigger: true});
        }});
    },
    
    cancel: function() {
    	this.firstName.val(''); 
    	this.lastName.val('');
    	this.emailId.val('');
    },
    
    render: function () {	
        this.$el.html(this.template(this.model.toJSON()));
        var self = this;
        //var countries = new CountryCollection();
        var options ='';
        countries.fetch().done(function(){
        	countries.each(function(country, index){
        		if( country.get('code') == self.model.get('country')){
        			options += "<option value='" + country.get('code') + "' selected >" + country.get('desc') + "</option>";
        		}else{
        			options += "<option value='" + country.get('code') + "'>" + country.get('desc') + "</option>";
        		}
        	}, this);
        	$('.country-select' ).append(options);
	    });
       
   
      
        
        return this;
    }
    
});

/* *************************************************** */
//Bank
/* **************************************************** */
window.BankListView = Backbone.View.extend({

    initialize: function () {
        this.render();
        vent.on('user:edit', this.editUser, this);
    },

    render: function () {
        this.collection.each(this.addOne, this);
        return this;
    },
    
    addOne:function(bank){
    	var bankView = new BankView({model: bank});
    	this.$el.append(bankView.render().el);
    },
    
    editUser:function(bank){
    	var bankEditView = new BankEditView({model: bank});
    	this.$el.html(bankEditView.render().el);
   
    }
    
});



window.BankView = Backbone.View.extend({
	events: {
        "click .edit"   : "editUser",
        "click .delete" : "deleteUser"
	},
	
    initialize: function () {
    	this.model.on('destroy', this.unrender, this);
        this.model.on('change', this.render, this);
    },
    
    render: function () {	
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
    
    editUser:function(){
    	vent.trigger('user:edit', this.model);
   
    },
    
    deleteUser:function(){
    	this.model.destroy();
    },
    
    unrender:function(){
    	this.remove();
    }
    
});



window.BankEditView = Backbone.View.extend({
    initialize: function () {
    	this.render();
    },
    
    events: {
        "change"        : "change",
        'click a.submit': 'save',
        'click a.save-and-next': 'saveAndGotoNext',
        'click button.cancel': 'cancel'
    },

    change: function (event) {
     
        // Apply the change to the model
        var target = event.target;
        var change = {};
        change[target.name] = target.value;
        this.model.set(change);

       console.log(this.model.toJSON());
    },
    
    submit: function(e) {
        e.preventDefault();
        this.model.save();
      
    },
    
    saveAndGotoNext: function(e) {
        e.preventDefault();
        this.model.save(this.model, {success:function(){
        	app.navigate("#page/2", {trigger: true, replace: true});
        }});
       
    },

    cancel: function() {
    	this.firstName.val(''); 
    	this.lastName.val('');
    	this.emailId.val('');
    },
    
    render: function () {	
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }
    
});



/* *************************************************** */
//Requested Service
/* **************************************************** */


window.ProductEditView = Backbone.View.extend({
  initialize: function () {
  	this.render();
  },
  
  events: {
      "change"        : "change",
      'click a.submit': 'save',
      'click a.save-and-next': 'saveAndGotoNext',
      'click button.cancel': 'cancel'
  },

  change: function (event) {
   
      // Apply the change to the model
      var target = event.target;
      var change = {};
      change[target.name] = target.value;
      this.model.set(change);

  },
  
  submit: function(e) {
      e.preventDefault();
      this.model.save();
    
  },
  
  saveAndGotoNext: function(e) {
      e.preventDefault();
      this.model.save(this.model, {success:function(){
      	app.navigate("#page/2", {trigger: true, replace: true});
      }});
     
  },

  cancel: function() {
  	this.firstName.val(''); 
  	this.lastName.val('');
  	this.emailId.val('');
  },
  
  render: function () {	
      this.$el.html(this.template());
      return this;
  }
  
});


window.ConfirmView = Backbone.View.extend({
	className:'row',
    initialize: function () {
    	this.render();
    },
    render: function () {	
        this.$el.html(this.template());
        return this;
    }
    
});




var ItemView = Backbone.View.extend({
    tagName: 'option',
    initialize:function(){        
        this.template= _.template($('#menu_item_view').html());    
    },    
    render:function(){        
        this.$el.html(this.template(this.model.toJSON()));        
        return this;        
    }
});

var CollectionView = Backbone.View.extend({
    tagName: 'select',
    initialize:function(){        
        this.collection = new ItemCollection();            
        this.collection.on('sync',this.render,this);            
        this.collection.fetch();
    },    
    render:function(){        
        _.each(this.collection.models,function( item ){            
            this.$el.append(new ItemView({model:item}).render().el );        
        },this);      
        return this;        
    }
});
