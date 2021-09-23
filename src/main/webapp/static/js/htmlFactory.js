export function getNormalProductCard(product) {return makeNormalProductCard(product);}
export function getCartContentCard(product) {return makeCartContentCard(product);}

function makeNormalProductCard(product) {
    return `<div class="col col-sm-12 col-md-6 col-lg-4">
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
                                <a class="btn btn-success add-to-cart" id="${product.name}" href="#">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>`
}

function makeCartContentCard(product) {
    return `<div class="media mb-3">
              <img class="d-flex z-depth-1 rounded mr-3" width="64px"
                   src='/static/img/product_${product.id}.jpg' alt="Sample">
              <div class="media-body">
                <h5>${product.name}</h5>
                <p class="mb-0"><span><strong>${product.defaultPrice} ${product.defaultCurrency}</strong></span></p>
              </div>
              <div class=”counter” style="width: 15%; display: flex; justify-content: space-between;align-items: center; margin-top: auto;margin-bottom: auto;margin-right: 47%">
                    <div class="add-button" id="${product.name}" style="color:#202020;font-size:20px;width:40px;height:40px;border-radius:50%;background-color:#d9d9d9;
                     display:flex;justify-content:center;align-items:center;font-weight:300;cursor:pointer">+</div>
                     
                    <div class=”count” style="font-size: 20px; font-weight: 300; color: #202020">${product.amount}</div>
                    
                    <div class="minus-button" id="${product.name}" style="color:#202020;font-size:20px;width:40px;height:40px;border-radius:50%;background-color:#d9d9d9;
                     display:flex;justify-content:center;align-items:center;font-weight:300;cursor:pointer;">-</div>
                </div>
              <img id="${product.name}" class="d-flex z-depth-1 rounded mr-3 remove-item-from-cart" width="24px"
                   src='/static/img/red_x.png' alt="Red X">
            </div>
            <hr>`
}