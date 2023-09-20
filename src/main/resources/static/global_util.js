document.querySelectorAll(".deleteFromCartBtn").forEach(button => {
    button.addEventListener("click", function (){
        const productId = this.getAttribute('data-product-id');
        fetch(`/shopping-cart/delete-from-cart?productId=${productId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => response.json())
        .then(data => {
            updateShoppingCartUI(data.cartItems);
        })
        .catch(error => {
            console.error("Error: ", error);
        });
    });
});

function updateShoppingCartUI(cartItems) {
    const cartContainer = document.getElementById("shopping-cart");

    // Clear existing items
    while (cartContainer.firstChild) {
        cartContainer.removeChild(cartContainer.firstChild);
    }

    // Check if cart is empty
    if (cartItems.length === 0) {
        const emptyText = document.createElement("p");
        emptyText.innerText = "Cart is empty";
        cartContainer.appendChild(emptyText);
    } else {
        cartItems.forEach(item => {
            const cartItemDiv = document.createElement("div");
            cartItemDiv.className = "cart-display";

            const cartItemText = document.createElement("p");
            cartItemText.innerText = `${item.product.name} ${item.quantity}`;
            cartItemDiv.appendChild(cartItemText);

            const cartItemRemoveButton = document.createElement("button");
            cartItemRemoveButton.className = "deleteFromCartBtn";
            cartItemRemoveButton.setAttribute("data-product-id", item.productId);
            cartItemRemoveButton.innerText = "Remove";
            cartItemDiv.appendChild(cartItemRemoveButton);

            cartContainer.appendChild(cartItemDiv);
        });
    }
}