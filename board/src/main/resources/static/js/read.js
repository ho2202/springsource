const replyList = () => {
  axios.get(`/replies/board/${bno}`).then((res) => {
    console.log(res.data);
    const data = res.data;
    let result = "";

    data.forEach((reply) => {
      result += `
        <div class="d-flex justify-content-between my-2 border-bottom reply-row" data-rno="${reply.rno}">
              <div class="p-3">
                <img src="/img/default.png" alt="" class="rounded-circle mx-auto d-block" style="width: 60px; height: 60px"/>
              </div>
    
              <div class="flex-grow-1 align-self-center">
                <div>${reply.replyer}</div>
                <div><span class="fs-5">${reply.text}</span></div>
                <div class="text-muted"><span class="small">${formatDate(reply.createdDate)}</span></div>
              </div>
              <div class="d-flex flex-column align-self-center">
                <div class="mb-2">
                  <button class="btn btn-outline-danger btn-sm">삭제</button>
                </div>
                <div>
                  <button class="btn btn-outline-success btn-sm">수정</button>
                </div>
              </div>
            </div>`;
    });
    document.querySelector(".replyList").innerHTML = result;
  });
};

document.querySelector(".replyList").addEventListener("click", (e) => {
  const btn = e.target;
  const rno = btn.closest(".reply-row").dataset.rno;
  if (btn.classList.contains("btn-outline-danger")) {
    // 삭제 버튼
    if (!confirm("정말 댓글을 삭제하시겠습니까?")) return;
    axios.delete(`/replies/${rno}`).then((res) => {
      console.log(res.data);
      console.log(rno);
      replyList(); // 다시 불러오기
    });
  } else if (btn.classList.contains("btn-outline-success")) {
    // 수정 버튼
    axios.get(`/replies/${rno}`).then((res) => {
      console.log(res.data);
    });
  }
});

// 날짜 포맷 함수
const formatDate = (str) => {
  const date = new Date(str);
  return (
    date.getFullYear() +
    "/" +
    (date.getMonth() + 1) +
    "/" +
    date.getDate() +
    "/" +
    date.getHours() +
    ":" +
    date.getMinutes()
  );
};

// 처음 호출
replyList();
