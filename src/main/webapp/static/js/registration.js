
const email = document.getElementById("email");
const password = document.getElementById("psw");
const repeatPassword = document.getElementById("psw-repeat");

register();

function register(){
    const submit = document.querySelector(".register");
    submit.addEventListener("click",sendRegisterData);
}

function passwordMatching(){
    return password.value === repeatPassword.value;
}

function sendRegisterData(){
    if (passwordMatching()){
    fetch(`save-registration?email=${email.value}&password=${password.value}`,{method:'GET'});}
    else {
        alert("Password does not match with Repeat Password !")
    }
}

