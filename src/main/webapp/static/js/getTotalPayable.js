export function getTotalPayableExport() {getTotalPayable()}

function getTotalPayable() {
    const totalPayableAmount = document.getElementById('total-amount-number');
    fetch('/api/session/get')
        .then(response => response.json())
        .then(data => {
            let finalAmount = 0;
            for (let product of data) {
                let currentAmount = parseFloat(product.amount) * parseFloat(product.defaultPrice);
                finalAmount += currentAmount;
            }
            totalPayableAmount.innerText = finalAmount.toString();
        })
}