document.getElementById("addToCartBtn").addEventListener("click", function () {
    const productId = this.getAttribute('data-product-id');

    fetch("/shopping-cart/addToCart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ productId: productId })
    })
        .then(response => response.json())
        .then(data => {
            if(data.success) {
                updateShoppingCartUI(data.cartItems);
            }
        })
        .catch(error => {
            console.error("Error: ", error);
        });
});