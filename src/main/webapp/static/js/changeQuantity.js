export function changeQuantityExport() {changeQuantity()}

function changeQuantity() {
    const addButtons = document.getElementsByClassName('add-button');
    for (const addButton of addButtons) {

        addButton.addEventListener('click', () => {
            console.log(addButton.id);
            let amount = (addButton.parentElement.childNodes[3]);
            amount.innerText = (parseInt(amount.innerText) + 1).toString();
        })
    }
    const minusButtons = document.getElementsByClassName('minus-button');
    for (const minusButton of minusButtons) {
        minusButton.addEventListener('click', () => {
            let amount = (minusButton.parentElement.childNodes[3]);
            if (parseInt(amount.innerText) > 1) {
                amount.innerText = (parseInt(amount.innerText) - 1).toString();
            }
        })
    }
}
