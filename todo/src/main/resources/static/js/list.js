// checkbox 클릭
document.querySelector(".list-group").addEventListener("click", (e) => {
  const checkbox = e.target;
  console.log(checkbox.checked);

  // data-id
  const id = checkbox.closest("label").dataset.id; // checkbox 내부의 가까운 label, data-id 값
  console.log(id);
  const form = document.querySelector("#actionForm");
  form.id.value = id;
  form.completed.value = checkbox.checked;

  form.submit();
});
