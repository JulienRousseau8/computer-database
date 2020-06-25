var name = document.getElementById("computerName");

email.addEventListener("keyup", function (event) {
  if(email.validity.typeMismatch) {
    email.setCustomValidity("Veuillez renseigner ce champ");
  } else {
    email.setCustomValidity("");
  }
});

