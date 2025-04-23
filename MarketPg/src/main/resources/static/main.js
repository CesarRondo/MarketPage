

async function userLogIn() {
    let data = await fetchUser(document.getElementById("username").value)
    //console.log(data);
    if (data == null) {
        alert("User not recognized")
        console.log("could not find the User")
    }
    let logInStatus =  Number( await startSession(data.id));
    // if (! await checkPassword(data.id)) {
    if(logInStatus===0){
        alert("Incorrect password")
        console.log("wrong password")
    } else {
        console.log("Correct Password")
        sessionStorage.setItem('loggedIn', true);
        sessionStorage.setItem('userData', JSON.stringify(data))
        alert("Logged In successfully, Redirecting to home page")
        window.location.href = "/"
    }

    //window.location.href = "/"
}

 async function fetchUser(name){
    //let name = document.getElementById("name").value
    console.log("function working, name : ", name)
    try {
        const response = await fetch("/users/getUserbyName", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({"content":name})
        })
        const data = await response.json();
        console.log("Success:", data);
        return data;
    }
    catch(error){
        console.log(error);
        return null;
    }
}

async function checkPassword(id){
    let password = document.getElementById("password").value
    //console.log(password);
    const response = await fetch("/users/checkPassword", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({"id":id, "password":password})
    })
    const data = await response.json();
    console.log("Success:", data);
    return data;
}

async function startSession(id){
    let password = document.getElementById("password").value
    //console.log(password);
    const response = await fetch("/users/startSession", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({"id":id, "password":password})
    })
    const data = await response.json();
    console.log("Success:", data);
    return data;
}
async function signUp(){
    await createUser();
    await userLogIn();


}
async function createUser(){
    let name = document.getElementById("username").value
    let password = document.getElementById("password").value
    console.log("function working, name : ", name)
    try {
        const response = await fetch("/users/newUser", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({"username":name, "password":password})
        })
        const data = await response.json();
        console.log("Success:", data);
        return data;
    }
    catch(error){
        console.log(error);
        return null;
    }

}

