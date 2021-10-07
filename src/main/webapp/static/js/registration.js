import {apiGet} from "./fetcher.js";

export function registerListener() {sendRegisterData();}

const email = document.querySelector("#user-email");
const userName = document.querySelector("#user-name")
const password = document.querySelector("#user-password-1");
const repeatPassword = document.querySelector("#user-password-2");

register();

function register(){
    const submit = document.querySelector("#submit-button");
    submit.addEventListener("click", sendRegisterData);
}

function passwordMatching(){
    return password.value === repeatPassword.value;
}

function sendRegisterData(){
    if (passwordMatching()) {
    apiGet(
        `register?name=${userName.value}&email=${ email.value}&password=${password.value}`
    ).then(() => {return null;});
    window.location.href = "/";
    } else {
        alert("Passwords does not match!");
    }
}

