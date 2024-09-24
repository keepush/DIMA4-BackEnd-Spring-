/**
 * 	방명록 관련 ajax 코드
 */

$(function(){
	init();
	$('#save').on('click', input);
});


// 전체 데이터를 다시 로딩
function init(){
	$.ajax({
		url: 'retrieveAll'
		, method : 'GET'
		, success: output
	})
}


// 로딩된 데이터 출력 함수
function output(resp){		// resp는 배열 함수일 것
	let tag = '';
	$.each(resp, function(index, item){
		tag +=`
		      <tr>
		       	<td class="idx">${index+1}</td>
		        <td class="name">${item["name"]}</td>
		        <td class="content">${item["content"]}</td>
		        <td class="date">${item["createDate"]}</td>
		        <td class="btn"><input type="button" class="btn" data-seq="${item["seq"]}" value="삭제">
				</td> 
			</tr>`;
	});
	
	$('tbody').html(tag);
	$('.btn').on('click', deleteItem);
}

// 데이터를 삭제하는 함수
function deleteItem(e){
	let seq = $(this).attr('data-seq');			// attr - 값 얻어오는 용도, 두 개 넣으면 값 바꿈
	let pwd = prompt("비밀번호를 입력하세요");
	
	let answer = confirm("삭제하시겠습니까?");
	e.stopPropagation();
	
	if (!answer) return;
	
	$.ajax({
		url: 'deleteGuestbook'
		, method: 'POST'
		, data : {"seq": seq, "pwd":pwd}
		// , async : false
		, success: init
	})
	// alert("asdfASDF");
}
 

// 방명록 데이터를 수집해서 ajax 기반으로 DB에 저장
function input(){
	let name = $('#name').val();
	let pwd = $('#pwd').val();
	let content = $('#content').val();
	
	let sendData = {"name":name, "pwd":pwd, "content":content};		// JSON 형태
	
	$.ajax({
		url: 'inputGuestbook'
		, method: 'POST'
		, data: sendData
		, success: function(){
			init();
			clearAll();
		}
	})	// ajax -> 중괄호 넣어 객체 형태로 데이터 전송
};


// 입력 상자의 데이터를 삭제
function clearAll(){
	$('#name').val("");
	$('#pwd').val("");
	$('#content').val("");
}