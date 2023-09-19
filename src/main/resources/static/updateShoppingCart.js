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

            cartContainer.appendChild(cartItemDiv);
        });
    }
}