const writeOkbtn=document.querySelector('#writeOkbtn');

const title=document.querySelector('#title');
const content=document.querySelector('#content');
const memberId=document.querySelector('#memberId');
const boardList=document.querySelector('.boardList');
 const tbody=document.querySelector('.tbody');
 const detailForm=document.querySelector('#detail-form');



writeOkbtn.addEventListener('click',(e)=>{
e.preventDefault();
const data={
        'title':title.value,
        'content':content.value,
        'memberId':memberId.value
        }
const url="api/board/write";
fetch(url,{
method:"post",
body:JSON.stringify(data),//object -> String
headers:{
"Content-type":"application/json"
}
}).
then(res=> res.json()).
then(data=>{
    console.log(data);
   let inserData=``;

   if(data!=null){
   fetchBoardList();
   }
}).catch(err=>{
console.log(err);

})
});
const fetchBoardList=(e)=>{
const url="/api/board/boardList";
fetch(url).
then(res=> res.json()).
then(data=>{
    console.log(data);
   let insertData=``;
   data.boardList.forEach(el=>{
      insertData+=`<tr>`
   insertData+=`<td> ${el.id} <td>`
   insertData+=`<td> ${el.title} <td>`
   insertData+=`<td> ${el.content} <td>`
   insertData+=`<td> ${el.hit} <td>`
   insertData+=`<td> ${el.memberId} <td>`
   insertData+=`<td> ${el.createTime} <td>`

   insertData+=`<td > <span class="list-td" onClick="boardDetailFn(${el.id})">보기</span> <td>`
   insertData+=`</tr>`

   })
insertData+=``;
   tbody.innerHTML=insertData;

}).catch(err=>{
console.log(err);

})
}
const boardDetailFn=(boardId)=>{
const url=`/api/board/detail/${boardId}`;
fetch(url).
then(res=> res.json()).
then(data=>{
    console.log(data);
    const board=data.board;
    let insertData=`
    <input type="text" value="${board.id}" name="id" id="id2" readOnly><br>
    <input type="text" value="${board.title}" name="title" id="title2" ><br>
    <input type="text" value="${board.content}" name="content" id="content2" ><br>
    <input type="text" value="${board.hit}" name="hit" id="hit2" readOnly><br>
    <input type="text" value="${board.createTime}" name="createTime" id="createTime2" readOnly><br>
    <input type="text" value="${board.memberId}" name="memberId" id="memberId2" readOnly><br>
    <button class"updateBtn" onClick="boardUpdateFn(event)">수정</button><br>
    <button class"deleteBtn" onClick="boardDeleteFn(${boardId},event)">삭제</button><br>
    `;
    console.log(insertData)
detailForm.innerHTML=insertData;
}).catch(err=>{
console.log(err);

})
}
const boardUpdateFn=(event)=>{

const id2=document.querySelector('#id2');
const title2=document.querySelector('#title2');
const content2=document.querySelector('#content2');
const hit2=document.querySelector('#hit2');
const memberId2=document.querySelector('#memberId2');
const createTime2=document.querySelector('#createTime2');

event.preventDefault();
const data={
    'id':id2.value,
    'title':title2.value,
    'content':content2.value,
    'hit':hit2.value,
    'memberId':memberId2.value,
    'createTime':createTime2.value,
}

const url="api/board/update";

fetch(url,{
method:"PUT",
body:JSON.stringify(data),//object -> String
headers:{
"Content-type":"application/json"
}
}).
then(res=>res.json()).
then(data=>{
    console.log(data);
    if(data!=null){
    detailForm.innerHTML=``;

    fetchBoardList();
    }
}).catch(err=>{
console.log(err);

});
}










const boardDeleteFn=(boardId,event)=>{
event.preventDefault();


const bool= window.confirm("게시글 삭제를 진행 하시겠습니까?");

if(!bool) {
    return false;
}


const url=`/api/board/delete/${boardId}`;
fetch(url,{
method:"DELETE",

}).
then(res=> res.json()).
then(data=>{
    console.log(data);

    if(data!=null){
//    detailForm.innerHTML=``;
const id2=document.querySelector('#id2');
const title2=document.querySelector('#title2');
const content2=document.querySelector('#content2');
const hit2=document.querySelector('#hit2');
const memberId2=document.querySelector('#memberId2');
const createTime2=document.querySelector('#createTime2');

    id2.value="";
    title2.value="";
    content2.value="";
    nickName2.value="";
    hit2.value="";
    memberId2.value="";
    createTime2.value="";

    fetchBoardList();
    }

}).catch(err=>{
console.log(err);

})

;
}


(()=>{
fetchBoardList();
})();