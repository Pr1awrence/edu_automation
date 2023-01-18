function modifyH1(message) {
    document.querySelectorAll("h1")
    .forEach(h1 => {h1.innerText = message;});
}

modifyH1("Use Selenium!");