window.onload = initHandleSidebar;

function initHandleSidebar() {
    document.querySelector("#sidebarCollapse").addEventListener(
        "click", () => {
            document.querySelector("#sidebar").classList.toggle("active");
        });
}
