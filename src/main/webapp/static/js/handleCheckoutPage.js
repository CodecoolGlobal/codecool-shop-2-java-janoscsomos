let requiredFields = document.querySelectorAll(".form-control")

for (let field of requiredFields) {
    field.addEventListener("change", fieldHandler)
}


function fieldHandler(e) {
    console.log(`${e.target.id} input occurred`);
}