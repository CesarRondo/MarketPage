function loadUserName() {
    // Retrieve userData from sessionStorage

    if(sessionStorage.getItem('loggedIn')===false){
        window.location.href="/";
    }

    let userDataString = sessionStorage.getItem('userData')
    console.log(userDataString);
    if (userDataString) {
        // Display the username and balance on the page

        let userData = JSON.parse(userDataString);

        document.getElementById('username').textContent = userData.username;
        //document.getElementById('balance').textContent = userData.balance;

        console.log('Username:', userData.username);
    } else {
        console.log('No user data found in sessionStorage');
    }
}

async function logOut(){
    let endSession = await fetch("/users/endSession", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        // body: JSON.stringify({"username":name, "password":password})
    })
    if(endSession){

        console.log("Successfully logged out");
        sessionStorage.setItem('loggedIn', false);
        alert("logged out successfully");
        window.location.href = "/";
    }
    else{
        alert("Could not log out try again or close the tab");
    }

}

window.onload = function () {
    loadUserName()
    displayBasket()
};

function displayBasket() {
    let basketList = document.getElementById('basketList')
    let totalPriceElement = document.getElementById('totalPrice')
    let amountElement = document.getElementById('amount')
    let amount = 0
    basketList.innerHTML = ''; // Clear existing list

    let basket = sessionStorage.getItem('basket')
    basket = basket ? JSON.parse(basket) : []

    let totalPrice = 0;

    if (basket.length === 0) {
        basketList.innerHTML = "<p>Your cart is empty.</p>";
    } else {
        basket.forEach((product, index) => {
            let listItem = document.createElement('li')
            listItem.textContent = `${product.name} - $${product.price} x ${product.quantity}`
            basketList.appendChild(listItem)

            // Decrease Quantity Button (-)
            let decreaseButton = document.createElement('button');
            decreaseButton.textContent = "➖";
            decreaseButton.onclick = function () {
                if (product.quantity > 1) {
                    let basket = sessionStorage.getItem('basket')
                    //basket = basket ? JSON.parse(basket) : []
                    basket = JSON.parse(basket)
                    basket[index].quantity--
                    sessionStorage.setItem('basket', JSON.stringify(basket))
                    displayBasket()
                } else {
                    removeItemFromBasket(index); // Remove if quantity reaches 0
                    return;
                }
                displayBasket();
            };
            listItem.appendChild(decreaseButton);

            // Increase Quantity Button (+)
            let increaseButton = document.createElement('button');
            increaseButton.textContent = "➕";
            increaseButton.onclick = function () {
                let basket = sessionStorage.getItem('basket')
                //basket = basket ? JSON.parse(basket) : []
                basket = JSON.parse(basket)
                basket[index].quantity++
                sessionStorage.setItem('basket', JSON.stringify(basket))
                displayBasket()
            };
            listItem.appendChild(increaseButton);


            let removeButton = document.createElement('button')
            removeButton.textContent = "Remove"
            removeButton.onclick = function () {
                removeItemFromBasket(index)
            };
            listItem.appendChild(removeButton)


            totalPrice += product.price * product.quantity // Calculate total price
            amount+=product.price * product.quantity
        });
    }
    amountElement.value = amount.toFixed(2)
    totalPriceElement.textContent = totalPrice.toFixed(2); // Update total price
}

function removeItemFromBasket(index) {
    // Get the basket from sessionStorage
    let basket = sessionStorage.getItem('basket');
    basket = basket ? JSON.parse(basket) : [];

    // Remove the item at the specified index
    basket.splice(index, 1);

    // Save the updated basket back to sessionStorage
    sessionStorage.setItem('basket', JSON.stringify(basket));

    // Re-render the basket to reflect the changes
    displayBasket();
}

