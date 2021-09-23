export function getNormalProductCard(product) {
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

export function getCartContentCard(product) {
    return `<div class="media mb-3">
                  <img class="d-flex z-depth-1 rounded mr-3" width="64px"
                       src='/static/img/product_${product.id}.jpg' alt="Sample">
                  <div class="media-body">
                    <h5>${product.name}</h5>
                    <p class="mb-0"><span><strong>${product.defaultPrice} ${product.defaultCurrency}</strong></span></p>
                  </div>
                  <div class=”counter” style="width: 15%; display: flex; justify-content: space-between;align-items: center">
                        <div class="add-button" style="color:#202020;font-size:20px;width:40px;height:40px;border-radius:50%;background-color:#d9d9d9;
                         display:flex;justify-content:center;align-items:center;font-weight:300;cursor:pointer">+</div>
                         
                        <div class=”count” style="font-size: 20px; font-weight: 300; color: #202020">1</div>
                        
                        <div class="minus-button" style="color:#202020;font-size:20px;width:40px;height:40px;border-radius:50%;background-color:#d9d9d9;
                         display:flex;justify-content:center;align-items:center;font-weight:300;cursor:pointer">-</div>
                    </div>
                  <img id="${product.name}" class="d-flex z-depth-1 rounded mr-3 remove-item-from-cart" width="24px"
                       src='/static/img/red_x.png' alt="Red X">
            </div>`
}

export function getRecommendedItemCard(product) {
    return `<!-- Grid column -->
            <div class="col-md-6 col-lg-3 mb-4">
              <!-- Card -->
              <div class="">
                <!-- Top part: -->
                <div class="view zoom overlay z-depth-2 rounded">
                  <a href="#!">
                    <div class="mask">
                      <img class="img-fluid w-100"
                           src='/static/img/product_${product.id}.jpg'>
                      <div class="mask rgba-black-slight"></div>
                    </div>
                  </a>
                </div>
                <!-- Bottom part: -->
                <div class="pt-4">
                  <h5>${product.name}</h5>
                  <p><span><strong>${product.defaultPrice} ${product.defaultCurrency}</strong></span></p>
                  <button type="button" class="btn btn-primary btn-sm mr-1 mb-2"><i
                          class="fas fa-shopping-cart pr-2"></i>Add
                    to cart</button>
                </div>
              </div>
              <!-- Card -->
            </div>
            <!-- Grid column -->`
}