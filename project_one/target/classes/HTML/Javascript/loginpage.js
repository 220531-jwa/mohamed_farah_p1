let baseURL = "http://localhost:8080";

async function login(){
    console.log("login button pressed");

    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let user = {
        username: username,
        password: password
    }

    console.log(user);
    
    let userJson = JSON.stringify(user);
    console.log(userJson);

    let res = await fetch(
        `${baseURL}/login`, // the url where we're sending this request
        {
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            body: userJson
        }
    );


    let resJson = await res.json()
        // .then will execute if the promise is successfully resolved
        // .then() takes a function as an argument
        .then((resp) => {
            console.log(resp); // this is where we will eventually put our DOM manipulation if needed
            window.location.assign("homepage.html");
        })
        // .catch will execute if there's any error
        .catch((error) => {
            console.log(error);
        });

}