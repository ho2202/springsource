const fileInput = document.querySelector("[name='file']");

const showUploadImages = (arr) => {
  const output = document.querySelector(".uploadResult ul");
  console.log("arr ", arr);

  let tags = "";
  arr.forEach((element, idx) => {
    tags += `<li data-name=${element.fileName}" data-path=${element.folderPath} data-uuid="${element.uuid}">`;
    tags += `<img src='/upload/display?fileName=${element.thumbnailURL}'/>`;
    tags += `<a href="${element.imageURL}"><i class="fa-solid fa-circle-xmark"></i></a>`;
    tags += "</li>";
  });
  output.insertAdjacentHTML("beforeend", tags);
};
// X 버튼을 클릭시 이동 중지, 파일명 가져오기
document.querySelector(".uploadResult").addEventListener("click", (e) => {
  e.preventDefault();

  const aTag = e.target.closest("a");
  const divTag = e.target.closest("li");

  const fileName = aTag.getAttribute("href");

  let form = new FormData();
  form.append("fileName", fileName);

  if (!confirm("정말 삭제하시겠습니까?")) return;
  axios
    .post(`/upload/removeFile`, form, {
      headers: {
        "X-CSRF-TOKEN": csrf,
      },
    })
    .then((res) => {
      console.log(res.data);
      if (res.data) {
        divTag.remove();
      }
    });
});

fileInput.addEventListener("change", (e) => {
  // const inputFile = document.querySelector("[name='uploadFiles']");
  // console.log(inputFile);
  const inputFile = e.target;

  const files = inputFile.files;
  console.log(files);

  let form = new FormData();
  for (let i = 0; i < files.length; i++) {
    form.append("uploadFiles", files[i]);
  }

  axios
    .post(`/upload/files`, form, {
      headers: {
        "X-CSRF-TOKEN": csrf,
      },
    })
    .then((res) => {
      showUploadImages(res.data);
    });
});
