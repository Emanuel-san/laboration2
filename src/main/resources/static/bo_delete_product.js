document.querySelector("#deleteProductBtn").addEventListener("click", function (){
    const productId = this.getAttribute('data-product-id');
    fetch(`/backoffice/products/DELETE_PRODUCT?productId=${productId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            if(data.success) {
                const resultText = document.createElement('p');
                resultText.innerHTML = `Product with product id ${productId} successfully deleted`;
                document.getElementById("delete-product").appendChild(resultText);
            }
            else {
                const resultText = document.createElement('p');
                resultText.innerHTML = `Something went wrong deleting product ${productId}`;
                document.getElementById("delete-product").appendChild(resultText);
            }
            let resultDiv = document.querySelector('.result');
            if (resultDiv) {
                resultDiv.parentNode.removeChild(resultDiv);
            }
        })
        .catch(error => {
            console.error("Error: ", error);
        });
});