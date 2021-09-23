export function handleSidebar () {initHandleSidebar();handleSideBarMenu();setMenuElementStyle()}
const supplierMenuElements = document.querySelectorAll(".supplier-menu-element");
const categoryMenuElements = document.querySelectorAll(".category-menu-element");

function setMenuElementStyle(){
    for (let menuElement of supplierMenuElements){
        menuElement.style.display = 'none';
    }
    for (let menuElement of categoryMenuElements){
        menuElement.style.display = 'none';
    }
}

function initHandleSidebar() {
    document.querySelector("#sidebarCollapse").addEventListener(
        "click", () => {
            document.querySelector("#sidebar").classList.toggle("active");
        });
}

function handleSideBarMenu(){
    const supplierMenu = document.querySelector("#suppliers");
    const categoryMenu = document.querySelector("#categories");
    supplierMenu.addEventListener('click',closeSupplierMenu);
    categoryMenu.addEventListener('click',closeCategoryMenu);
}

function closeSupplierMenu(){
    for (let menuElement of supplierMenuElements){
        if (menuElement.style.display === 'block'){
        menuElement.style.display = 'none';}
        else (menuElement.style.display = 'block')
    }
}

function closeCategoryMenu(){
    for (let menuElement of categoryMenuElements){
        if (menuElement.style.display === 'block'){
            menuElement.style.display = 'none';}
        else (menuElement.style.display = 'block')
    }
}
