document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  // 생성된 파일 정보
  const output = document.querySelectorAll(".uploadResult li");

  let result = "";
  // 요소와 인덱스
  output.forEach((obj, idx) => {
    console.log(obj.dataset.uuid);
    result += `
          <input type="hidden" name="movieImages[${idx}].path" value="${obj.dataset.path}"/>
          <input type="hidden" name="movieImages[${idx}].uuid" value="${obj.dataset.uuid}"/>
          <input type="hidden" name="movieImages[${idx}].imgName" value="${obj.dataset.name}"/>
        `;
  });
  e.target.insertAdjacentHTML("beforeend", result);
  e.target.submit();
});
