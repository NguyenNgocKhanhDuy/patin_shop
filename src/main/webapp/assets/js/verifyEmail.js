var codes = document.querySelectorAll(".code");
codes[0].focus();
codes.forEach((code, index) => {
    code.addEventListener("keydown", function (evt) {
        if (evt.key >= 0 && evt.key <= 9) {
            codes[index].value = ''
            setTimeout(() => codes[index+1].focus(), 10)
        }
    })
})


