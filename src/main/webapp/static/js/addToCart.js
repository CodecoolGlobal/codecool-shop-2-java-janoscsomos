function addToCart() {
    const addToCartButtons = document.getElementsByClassName("add-to-cart");

    for (const addToCartButton of addToCartButtons) {
        addToCartButton.addEventListener('click', () => {
            fetch(`/api/session/add?productName=${addToCartButton.id}`).then(() => {return 0;})
        })
    }
}

window.onload = addToCart;

