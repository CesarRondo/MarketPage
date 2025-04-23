function initializeBasket() {
    let basket = sessionStorage.getItem('basket');

    // If basket does not exist, create empty array
    if (!basket) {
        basket = [];
        sessionStorage.setItem('basket', JSON.stringify(basket));  // Store the empty array in sessionStorage
        console.log('Basket was created as empty array');
    } else {
        // If basket exists, parse it into a JavaScript object
        basket = JSON.parse(basket);
        console.log('Basket found:', basket);
    }
}
function addToBasket() {

    // Get the value of the data-logIn attribute
    let isLoggedIn = document.getElementById("logInStatus").dataset.login === "true";

    // Check if the user is logged in
    if (!isLoggedIn) {
        alert("You need to log in to add items to the Cart")
        return;
    }
    // Retrieve the basket from sessionStorage
    let productId=Number(document.getElementById('product_id').textContent)
    let productName =document.getElementById('product_name').textContent
    let productPrice = document.getElementById('product_price').textContent;



    let basket = sessionStorage.getItem('basket');

    // Parse the basket or initialize as an empty array if it doesn't exist
    basket = basket ? JSON.parse(basket) : [];

    // Check if the product is already in the basket
    let existingProduct = basket.find(item => item.id === productId);

    if (existingProduct) {
        // If the product exists, increase the quantity
        existingProduct.quantity += 1;
    } else {
        // Otherwise, add the new product
        basket.push({ id: productId, name: productName, price: productPrice, quantity: 1 });
    }

    // Store the updated basket back in sessionStorage
    sessionStorage.setItem('basket', JSON.stringify(basket));
    alert("product added to your basket")
    console.log('Product added to basket:', basket);
}






