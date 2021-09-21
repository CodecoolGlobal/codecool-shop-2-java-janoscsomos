const categoryMenuButtons = document.getElementsByClassName("category-menu-element");
let url = "/api/category";
const cardContainer = document.getElementById('products');
const menuNameContainer = document.getElementById('current-menu');

for (const categoryMenuButton of categoryMenuButtons) {
    categoryMenuButton.addEventListener('click', () => {
        //console.log(menuNameContainer);
        menuNameContainer.innerHTML = ` <strong>${categoryMenuButton.innerText}</strong>`
        fetch(`${url}?category=${categoryMenuButton.id}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                let newContent = "";
                for (let product of data) {
                    newContent += `
                    <div class="col col-sm-12 col-md-6 col-lg-4">
                    <div class="card">
                        <div class="img-hover-zoom">
                            <img width="350px" height="400px" src='/static/img/product_${product.id}.jpg'
                                 alt=""/>
                        </div>
                        <div class="card-header">
                            <h4 class="card-title">${product.name}</h4>
                            <p class="card-text" >${product.description}</p>
                        </div>
                        <div class="card-body">
                            <div class="card-text">
                                <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                            </div>
                            <div class="card-text">
                                <a class="btn btn-success" href="#">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>`
                }
                cardContainer.innerHTML = newContent;
            })
    })
}



