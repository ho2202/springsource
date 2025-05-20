// enter 받으면 searchForm 가져오기
document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  if (e.keyCode == 13) {
    // alert("엔터");
    const searchForm = document.querySelector("#searchForm");
    searchForm.submit();
  }
});
