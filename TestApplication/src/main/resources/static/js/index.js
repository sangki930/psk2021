 $(document).ready(function(){
            let buttons=$(".button").val();

          static_ext();
          custom_ext();

          $("#add").click(function(){

            let str=$("#input-ext").val();
            let m=str.toUpperCase().match( /^[A-Za-z0-9+]{1,20}$/);

            if(!m || str.toUpperCase().match(/^[0-9]/g))
            {
                
                alert('올바른 확장자 양식이 아닙니다.')
                $("input[name=input-ext]").val('');
                console.log(str);
                return;
            }
            //커스텀 확장자 배열
            let a=$.makeArray($(".button").map(function(){
                return $(this).attr("name");
            }));
            //고정 확장자 배열
            let b=$.makeArray($("input[name=input-ext]").map(function(){
                return $(this).attr("name");
            }));
            
            if(a.length>=200){
            	alert('최대의 개수가 200개입니다.')
            	return;
            }
            	
            if(a.includes(str) || b.includes(str))
            	{
            	alert("이미 존재한 확장자입니다.");
            	$("input[name=input-ext]").val('');
                console.log(str);
                return;
            	}
           	

            //AJAX (확장자 추가 요청->성공했을 때, 데이터를 받음, id값 및 확장자 이름)
            var id="";
            var ext=str;
            var type=0;
            var checked=false;
            d={
            	"ext":ext,
            	"type":type,
            	checked:false
            }
            $.ajax({
       	        url : '/add',
       	        type : 'post',
       	        data : d,
       	        success : function(data){
       	            id=data.id;
       	            ext=data.ext;
       	            type=data.type;
       	            checked=data.checked;
       	         $("#ext-container").append(`<div class="button" id="ext-${id}" name='${ext}' value="${ext}">${str}
                         <button id="ext-delete-${id}" name="${str}" class="btn info">X</button>
                     </div>`);
                  enroll_del();//삭제 이벤트 등록
       	            
       	        }
       	    	});
			
            
            
            $("input[name=input-ext]").val('');
           $("#ext-cnt").html(`(${$(".button").length+1} / 200)`)
          });

          
        
        });


        const enroll_checked=function(){
            $(".fixed-ext").click(
              function(){
            	let id=this.id.substring(4);
               let checked=$("#"+this.id).is(":checked")
               
               let ext=this.value;
               let d={
            		   "id":id,
            		   "ext":ext,
            		   "type":1,
            			"checked":checked
            			
               };
               $.ajax({
       	        url : '/update',
       	        type : 'put',
       	        data : d,
       	        success : function(data){
       	            console.log(data);
       	        }
       	    	});
               
              }
          );
        }

        const enroll_del=function(){
            $(".btn").click(function(){
                console.log("this : "+this);
                // console.log($(".button")[0]);
                console.log($(this).parent().attr('id'));
                let id=$(this).parent().attr('id').substring(4);
                console.log(id);
                d={"id":id}
                $.ajax({
           	        url : '/delete',
           	        type : 'delete',
           	        data : d,
           	        success : function(data){
           	            console.log(data);
           	        }
           	    	});
                
               $(this).parent().remove();
               //AJAX
				
              $("#ext-cnt").html(`(${$(".button").length} / 200)`)
          });
        }

        const static_ext=function(){
            // $("td[name=static-ext]").html(``);
            $.ajax({ 
	        type: "GET", 
	        url: "/getStatic", 
	    success: (data) => 
            { 
            	let content='';
            	for(let i of data){
            		let ch=i.checked?"checked":"";
            		let a=`<label><input type="checkbox" class="fixed-ext" id="ext-${i.id}" name="fixed-${i.ext}" value="${i.ext}" ${ch}> ${i.ext}</label>`;
            		content+=a+' '
            	}
            	
      			$("td[name=static-ext]").html(content);
            	enroll_checked();
            } 
	}); 
            
        }
        
        const custom_ext=function(){
        	$.ajax({ 
    	        type: "GET", 
    	        url: "/getRest", 
    	    success: (data) => 
                { 
                	let content='';
                	for(let i of data){
                		let ch=i.checked?"checked":"";
                		let id=i.id;
                		let ext=i.ext;
                		let a=`<div class="button" id="ext-${id}" name='${ext}' value="${ext}">${ext}
                        <button id="ext-delete-${id}" name="${ext}" class="btn info">X</button>
                        </div>`;
                		content+=a;
                	}
                	
          			$("div[name=ext-container]").html(content);
          			$("#ext-cnt").html(`(${$(".button").length} / 200)`)
                	enroll_del();
                } 
    	});
        	 
        };