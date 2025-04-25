// 삭제 버튼을 클릭
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  const form = document.querySelector("#actionForm");
  form.submit();
});
