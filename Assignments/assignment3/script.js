console.log("Website loaded!");

const messageButton = document.getElementById("messageButton");
const message = document.getElementById("message");
const themeButton = document.getElementById("themeButton");

messageButton.addEventListener("click", function () {
    message.textContent = "Thanks for visiting my personal website!";
});

themeButton.addEventListener("click", function () {
    document.body.classList.toggle("dark-theme");
});
