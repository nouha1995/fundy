

	$('.command1').click(function(){
	    if($(this).hasClass('active')){
	        $(this).removeClass('active')
	    } else {
	        $(this).addClass('active')
	        
	    }
	});

	$('.iarea').focusin(function(){
	    if($('.command1').hasClass('active')){
	       
	        $('.iarea').addClass('bold-textarea');
	        $('#prime-form\\:bold-input').val("font-weight-bolder");
	     }
	    else{
	    	 $('.iarea').removeClass('bold-textarea');
	    	 $('#prime-form\\:bold-input').val("font-weight-normal");
	        }
	});
	
	
	
	
$('.command2').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command2').hasClass('active')){
       
        $('.iarea').addClass('italic-textarea');
        $('#prime-form\\:italic-input').val("font-style-italic");
     }
    else{
    	 $('.iarea').removeClass('italic-textarea');
    	 $('#prime-form\\:italic-input').val("font-style-normal");
        }
});





$('.command3').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});

$(".iarea").focusin(function() {
	if($('.command3').hasClass('active')){
    if(document.getElementById('wysihtml5-editor').value === ''){
        document.getElementById('wysihtml5-editor').value +='• ';
        $('#prime-form\\:desordered-input').val("noOrder");
	}
    
	}else{
    	
    }
});
$(".iarea").keyup(function(event){
	if($('.command3').hasClass('active')){
	var keycode = (event.keyCode ? event.keyCode : event.which);
    if(keycode == '13'){
        document.getElementById('wysihtml5-editor').value +='• ';
	}
	var txtval = document.getElementById('wysihtml5-editor').value;
	if(txtval.substr(txtval.length - 1) == '\n'){
		document.getElementById('wysihtml5-editor').value = txtval.substring(0,txtval.length - 1);
		$('#prime-form\\:desordered-input').val("noOrder");
	}
	
	}else{
		
	}
});
$('.iarea').focusin(function(){
    if($('.command3').hasClass('active')){
       
        $('.iarea').addClass('ordered-textarea');
     }
    else{
    	 $('.iarea').removeClass('ordered-textarea');
        }
});




$('.command4').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command4').hasClass('active')){
       
        $('.iarea').addClass('unordered-textarea');
     }
    else{
    	 $('.iarea').removeClass('unordered-textarea');
        }
});



$('.command5').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command5').hasClass('active')){
       
        $('.iarea').addClass('link-textarea');
     }
    else{
    	 $('.iarea').removeClass('link-textarea');
        }
});




$('.command6').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command6').hasClass('active')){
       
        $('.iarea').addClass('image-textarea');
     }
    else{
    	 $('.iarea').removeClass('image-textarea');
        }
});


$('.filter1').click(function(){
    $('#plsForm\\:filter1-input').val("Hunger");

});
$('.filter').click(function(){
    $('#plsForm\\:filter1-input').val("Culture");

});
$('.filter2').click(function(){
    $('#plsForm\\:filter1-input').val("HumanRights");

});
$('.filter3').click(function(){
    $('#plsForm\\:filter1-input').val("Education");

});
$('.filter4').click(function(){
    $('#plsForm\\:filter1-input').val("Technology");

});
$('.filter5').click(function(){
    $('#plsForm\\:filter1-input').val("Health");

});
$('.filter6').click(function(){
    $('#plsForm\\:filter1-input').val("Animals");

});
$('.filter7').click(function(){
    $('#plsForm\\:filter1-input').val("Environment");

});
$('.filter8').click(function(){
    $('#plsForm\\:filter1-input').val("articles");

});

$('.command7').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command7').hasClass('active')){
       
        $('.iarea').addClass('h1-textarea');
        $('#prime-form\\:seize-input').val("font-seize-h1");
     }
    else{
    	 $('.iarea').removeClass('h1-textarea');
        }
});




$('.command8').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command8').hasClass('active')){
       
        $('.iarea').addClass('h2-textarea');
        $('#prime-form\\:seize-input').val("font-seize-h2");

     }
    else{
    	 $('.iarea').removeClass('h2-textarea');
        }
});



$('.command9').click(function(){
    if($('.command9').hasClass('active')){
        $('.command9').removeClass('active')
    } else {
        $('.command9').addClass('active')
        
    }
});


$('.red').click(function(){
    if($('.command9').hasClass('red-active')){
        $('.command9').removeClass('red-active')
    } else {
        $('.command9').addClass('red-active')
        
    }
});
$('.silver').click(function(){
    if($('.command9').hasClass('silver-active')){
        $('.command9').removeClass('silver-active')
    } else {
        $('.command9').addClass('silver-active')
        
    }
});
$('.gray').click(function(){
    if($('.command9').hasClass('gray-active')){
        $('.command9').removeClass('gray-active')
    } else {
        $('.command9').addClass('gray-active')
        
    }
});
$('.maroon').click(function(){
    if($('.command9').hasClass('maroon-active')){
        $('.command9').removeClass('maroon-active')
    } else {
        $('.command9').addClass('maroon-active')
        
    }
});
$('.purple').click(function(){
    if($('.command9').hasClass('purple-active')){
        $('.command9').removeClass('purple-active')
    } else {
        $('.command9').addClass('purple-active')
        
    }
});
$('.green').click(function(){
    if($('.command9').hasClass('green-active')){
        $('.command9').removeClass('green-active')
    } else {
        $('.command9').addClass('green-active')
        
    }
});
$('.olive').click(function(){
    if($('.command9').hasClass('olive-active')){
        $('.command9').removeClass('olive-active')
    } else {
        $('.command9').addClass('olive-active')
        
    }
});
$('.navy').click(function(){
    if($('.command9').hasClass('navy-active')){
        $('.command9').removeClass('navy-active')
    } else {
        $('.command9').addClass('navy-active')
        
    }
});
$('.blue').click(function(){
    if($('.command9').hasClass('blue-active')){
        $('.command9').removeClass('blue-active')
    } else {
        $('.command9').addClass('blue-active')
        
    }
});
$('.iarea').focusin(function(){
	
    if($('.command9').hasClass('red-active') ){
    	
        $('.iarea').addClass('red-textarea');
        $('#prime-form\\:color-input').val("font-color-red");
     }
    else{
    	 $('.iarea').removeClass('red-textarea');
    	 
        }
    
    if($('.command9').hasClass('silver-active') ){
    	
        $('.iarea').addClass('silver-textarea');
        $('#prime-form\\:color-input').val("font-color-silver");
     }
    else{
    	 $('.iarea').removeClass('silver-textarea');
    	 
        }
    
    
    if($('.command9').hasClass('gray-active') ){
    	
        $('.iarea').addClass('gray-textarea');
        $('#prime-form\\:color-input').val("font-color-gray");
     }
    else{
    	 $('.iarea').removeClass('gray-textarea');
    	
        }
    
    
    if($('.command9').hasClass('maroon-active') ){
    	
        $('.iarea').addClass('maroon-textarea');
        $('#prime-form\\:color-input').val("font-color-maroon");
     }
    else{
    	 $('.iarea').removeClass('maroon-textarea');
    	 
        }
    
    
    if($('.command9').hasClass('purple-active') ){
    	
        $('.iarea').addClass('purple-textarea');
        $('#prime-form\\:color-input').val("font-color-purple");
     }
    else{
    	 $('.iarea').removeClass('purple-textarea');
    	
        }
    
    
    if($('.command9').hasClass('green-active') ){
    	
    		
        $('.iarea').addClass('green-textarea');
        $('#prime-form\\:color-input').val("font-color-green");
     }
    else{
    	 $('.iarea').removeClass('green-textarea');
    	
        }
    
    if($('.command9').hasClass('olive-active') ){
    	
        $('.iarea').addClass('olive-textarea');
        $('#prime-form\\:color-input').val("font-color-olive");
     }
    else{
    	 $('.iarea').removeClass('olive-textarea');
    	
        }
    
    if($('.command9').hasClass('navy-active') ){
    	
        $('.iarea').addClass('navy-textarea');
        $('#prime-form\\:color-input').val("font-color-navy");
     }
    else{
    	 $('.iarea').removeClass('navy-textarea');
    	
        }
    
    if($('.command9').hasClass('blue-active') ){
   
        $('.iarea').addClass('blue-textarea');
        $('#prime-form\\:color-input').val("font-color-blue");
     }
    else{
    	 $('.iarea').removeClass('blue-textarea');
    	         }
});




$('.command10').click(function(){
    if($(this).hasClass('active')){
        $(this).removeClass('active')
    } else {
        $(this).addClass('active')
        
    }
});
$('.iarea').focusin(function(){
    if($('.command10').hasClass('active')){
       
        $('.iarea').addClass('dark-textarea');
        $('#prime-form\\:color-input').val("font-color-black");
     }
    else{
    	 $('.iarea').removeClass('dark-textarea');
        }
});



