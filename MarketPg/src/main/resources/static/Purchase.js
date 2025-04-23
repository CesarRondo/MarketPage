function clearBasket(){
    let basket = [];
    sessionStorage.setItem('basket', JSON.stringify(basket));
}
async function confirmPayment(){

    //Because I don't use a real payment platform I will just send acknowledgement of the server when
    //the "payment" has been confirmed, in theory this info would be sent by the payment service
    let purchases = []

    if(document.getElementById("itemId").value==="0"){
        let basket = sessionStorage.getItem('basket')
        basket = basket = basket ? JSON.parse(basket) : []

        basket.forEach((product)=>{
            purchases.push({"id":product.id, "amount":product.quantity})
        })
        clearBasket();
    }
    else{
        purchases.push({"id":document.getElementById("itemId").value, "amount":1})
    }
    //sending product Ids to store them in history
    try {
        const response = await fetch("/confirmPurchase", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(purchases)
        })
        if (!response.ok) {
            alert("Payment could not proceed")
        }
    }
    catch(error){
        console.log(error);
        return null;
    }
    console.log(purchases)
    alert("payment done")
}

async function handleForm(){
    event.preventDefault();
    await confirmPayment();
    document.querySelector("form").submit();

}