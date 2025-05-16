// 이미지 모달 찾기
const imgModal = document.querySelector("#imgModal");
// 모달 창에 data-file 값 가져오기
if (imgModal) {
  imgModal.addEventListener("show.bs.modal", (e) => {
    // 모달 창과 연결된 요소 가져오기
    const thumLi = e.relatedTarget;
    // Extract info from data-bs-* attributes
    // li에 data-* 에 담은 파일 url 가져오기
    const file = thumLi.getAttribute("data-file");
    // If necessary, you could initiate an Ajax request here
    // and then do the updating in a callback.

    // Update the modal's content.
    const modalTitle = imgModal.querySelector(".modal-title");
    const modalBodyInput = imgModal.querySelector(".modal-body");

    // 모달에 보여줄 내용 삽입
    modalTitle.textContent = `${title}`;
    modalBodyInput.innerHTML = `<img src='/upload/display?fileName=${file}&size=1'>`;
  });
}
