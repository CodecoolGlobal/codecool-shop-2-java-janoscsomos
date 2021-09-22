export function addToCartExport () {addToCart()}

function addToCart() {

    const addToCartButtons = document.getElementsByClassName("add-to-cart");
    console.log(addToCartButtons);
    for (let addToCartButton of addToCartButtons) {
        addToCartButton.addEventListener('click', (e) => {
            e.preventDefault();
            fetch(`/api/session/add?productName=${addToCartButton.id}`).then(() => { return 0;})
        })
    }
}