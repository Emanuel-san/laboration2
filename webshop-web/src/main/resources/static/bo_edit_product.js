const productForm = document.querySelector('.bo-product-form form');

productForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const formData = new FormData(productForm);
    const productData = {};

    formData.forEach((value, key) => {
        productData[key] = value;
    });

    try {
        const response = await fetch('/backoffice/products/EDIT_PRODUCT', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productData)
        });

        const result = await response.json();

        if(result.updatedProduct.productId) {
            const resultDiv = document.createElement('div');
            resultDiv.classList.add('result');

            resultDiv.innerHTML = `
              <p>Product successfully updated</p>
              <p>Product ID: ${result.updatedProduct.productId}</p>
              <p>Name: ${result.updatedProduct.name}</p>
              <p>Price: ${result.updatedProduct.price}</p>
              <p>Description: ${result.updatedProduct.description}</p>
              <p>Image URL: ${result.updatedProduct.image}</p>
            `;

            document.getElementById("edit-product").appendChild(resultDiv);
        }

    } catch (error) {
        const resultDiv = document.createElement('div');
        resultDiv.classList.add('result');
        resultDiv.innerHTML = `<p>Error updating product: ${error}</p>`;
        document.getElementById("edit-product").appendChild(resultDiv);
    }
});