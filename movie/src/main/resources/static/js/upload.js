const showUploadImages = (arr) => {
  const output = document.querySelector("#output");
  arr.forEach((element) => {
    output.insertAdjacentElement("beforeend", `<img src="/upload/display?fileName=${element.imageURL}"/>`);
  });
};

document.querySelector("button").addEventListener("click", () => {
  const inputFile = document.querySelector("[name='uploadFiles']");
  console.log(inputFile);

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
      console.log(res.data);
      showUploadImages(res.data);
    });
});
