const joinOkbtn=document.querySelector('#joinOkbtn');
const email=document.querySelector('#email');
const pw=document.querySelector('#pw');
const nickName=document.querySelector('#nickName');
//const createTime2=document.querySelector('#createTime2');

const memberList=document.querySelector('.memberList');
 const tbody=document.querySelector('.tbody');

const detailForm=document.querySelector('#detail-form');


joinOkbtn.addEventListener('click',(e)=>{
e.preventDefault();
const data={
    'email':email.value,
    'pw':pw.value,
    'nickName':nickName.value
}

const url="/api/member/join";

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
   fetchMemberList();
   }

 /*  data.memberList.forEach(el=>{
    inserData+=`<tr>`

   inserData+=`<td> ${el.id} <td>`
   inserData+=`<td> ${el.email} <td>`
   inserData+=`<td> ${el.pw} <td>`
   inserData+=`<td> ${el.nickname} <td>`
   inserData+=`</tr>`

   })
inserData+=``;

   tbody.innerHTML=inserData;*/

}).catch(err=>{
console.log(err);

})

});

const fetchMemberList=(e)=>{
const url="/api/member/memberList";
fetch(url).
then(res=> res.json()).
then(data=>{
    console.log(data);
   let insertData=``;
   data.memberList.forEach(el=>{
      insertData+=`<tr>`
   insertData+=`<td> ${el.id} <td>`
   insertData+=`<td> ${el.email} <td>`
   insertData+=`<td> ${el.pw} <td>`
   insertData+=`<td> ${el.nickName} <td>`
   insertData+=`<td > <span class="list-td" onClick="memberDetailFn(${el.id})">보기</span> <td>`
   insertData+=`</tr>`

   })
insertData+=``;
   tbody.innerHTML=insertData;

}).catch(err=>{
console.log(err);

})
}

//회원조회 ->
const memberDetailFn=(memberId)=>{
const url=`/api/member/detail/${memberId}`;
fetch(url).
then(res=> res.json()).
then(data=>{
    console.log(data);
    const member=data.member;
   let inserData=`
   <input type="text" value="${member.id}" name="id" id="id2" readOnly><br>
   <input type="text" value="${member.email}" name="email" id="email2"><br>
   <input type="text" value="${member.pw}" name="pw" id="pw2"><br>
   <input type="text" value="${member.nickName}" name="nickName" id="nickName2"><br>
   <input type="text" value="${member.createTime}" name="createTime" id="createTime2" readOnly ><br>
   <button class="updateBtn" onClick="memberUpdateFn(event)">수정</button><br>
   <button class="deleteBtn" onClick="memberDeleteFn(${memberId},event)">삭제</button><br>
   `;
console.log(inserData)
detailForm.innerHTML=inserData;

}).catch(err=>{
console.log(err);

})
}
//회원수정 memberUpdateFn
const memberUpdateFn=(event)=>{

const id2=document.querySelector('#id2');
const email2=document.querySelector('#email2');
const pw2=document.querySelector('#pw2');
const nickName2=document.querySelector('#nickName2');
const createTime2=document.querySelector('#createTime2');

event.preventDefault();
const data={
    'id':id2.value,
    'email':email2.value,
    'pw':pw2.value,
    'nickName':nickName2.value,
    'createTime':createTime2.value
}

const url="/api/member/update";

fetch(url,{
method:"PUT",
body:JSON.stringify(data),//object -> String
headers:{
"Content-type":"application/json"
}
}).
then(res=> res.json()).
then(data=>{
    console.log(data);
 if(data!=null){
    detailForm.innerHTML=``;
        fetchMemberList();
    }
}).catch(err=>{
console.log(err);

})

;
}
//회원삭제 memberDeleteFn
const memberDeleteFn=(memberId,event)=>{
event.preventDefault();


const bool= window.confirm("회원삭제를 진행 하시겠습니까?");

if(!bool) {
    return false;
}


const url=`/api/member/delete/${memberId}`;
fetch(url,{
method:"DELETE",

}).
then(res=> res.json()).
then(data=>{
    console.log(data);

    if(data!=null){
//    detailForm.innerHTML=``;
const id2=document.querySelector('#id2');
const email2=document.querySelector('#email2');
const pw2=document.querySelector('#pw2');
const nickName2=document.querySelector('#nickName2');
const createTime2=document.querySelector('#createTime2');

    id2.value="";
    email2.value="";
    pw2.value="";
    nickName2.value="";
    createTime2.value="";

    fetchMemberList();
    }

}).catch(err=>{
console.log(err);

})

;
}

(()=>{
fetchMemberList();
})();


