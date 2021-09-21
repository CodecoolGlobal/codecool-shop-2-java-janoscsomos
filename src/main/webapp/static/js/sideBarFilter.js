const categoryMenuButtons = document.getElementsByClassName("category-menu-element");
const supplierMenuButtons = document.getElementsByClassName("supplier-menu-element");
const urlCategory = "/api/category";
const urlSupplier = "/api/supplier"
const cardContainer = document.getElementById('products');
const menuNameContainer = document.getElementById('current-menu');
const searchByName = document.getElementById('search-by-name');

searchByName.addEventListener('change', () => {
    alert('hello');
})

for (const supplierMenuButton of supplierMenuButtons) {
    supplierMenuButton.addEventListener('click', () => {
        menuNameContainer.innerHTML = ` <strong>${supplierMenuButton.innerText}</strong>`
        fetch(`${urlSupplier}?supplier=${supplierMenuButton.id}`)
            .then(response => response.json())
            .then(data => {
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

for (const categoryMenuButton of categoryMenuButtons) {
    categoryMenuButton.addEventListener('click', () => {
        menuNameContainer.innerHTML = ` <strong>${categoryMenuButton.innerText}</strong>`
        fetch(`${urlCategory}?category=${categoryMenuButton.id}`)
            .then(response => response.json())
            .then(data => {
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



