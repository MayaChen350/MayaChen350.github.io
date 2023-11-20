// Function to set a cookie with a specified name, value, and expiration date
function setCookie(name, value, days) {
  let expires = "";
  if (days) {
    let date = new Date();
    date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
    expires = "; expires=" + date.toUTCString();
  }
  document.cookie = name + "=" + value + expires + "; path=/";
}

// Function to get a cookie by name
function getCookie(name) {
  let nameEQ = name + "=";
  let ca = document.cookie.split(";");
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === " ") c = c.substring(1, c.length);
    if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
  }
  return null;
}

// Function to delete a cookie by name
function eraseCookie(name) {
  document.cookie = name + "=; Max-Age=-99999999;";

  let liste = document.getElementById("todos-list");
  while (liste.firstChild) {
    liste.removeChild(liste.firstChild);
  }
}

// Function to remove a todo from the list and update the cookie
function removetodo(todo) {
  let index = todos.indexOf(todo);
  if (index !== -1) {
    // Remove the todo from the array
    todos.splice(index, 1);

    // Update the todos list in the cookie
    setCookie("todos", todos.join(","), 30);

    // Remove the corresponding <li> element from the HTML
    let liToRemove = Array.from(liste.children).find(
      (li) => li.innerText === todo
    );
    if (liToRemove) {
      liste.removeChild(liToRemove);
    }
  }
}

// OUR CODE
let liste = document.getElementById("todos-list");
let addToDoButton = document.getElementById("addtodo-button");
let eraseCookieButton = document.getElementById("erase-cookies-button");
let allTodos = document.getElementById("todos");
let inputTodoInput = document.getElementById("input-todo");

let erasetodoCookieButton = document.getElementById(
  "erase-cookies-todo-button"
);

const addTodoFunc = () => {
  let inputtodo = document.getElementById("input-todo").value.trim();

  if (inputtodo.length > 0) {
    // Add the new todo to the list
    todos.push(inputtodo);

    // Update the todos list in the cookie
    setCookie("todos", todos.join(","), 30);

    // Display the new todo in the list
    let element = document.createElement("li");
    element.innerText = inputtodo;
    liste.appendChild(element);

    // Clear the input field
    document.getElementById("input-todo").value = "";
    
    if (allTodos.lastElementChild.nodeName == "P") {
      allTodos.removeChild(allTodos.lastElementChild);
    }
  }
}

// Retrieve todos from the cookie or use a default list
let todos = getCookie("todos") ? getCookie("todos").split(",") : [];
console.log(todos);
// Display todos from the cookie
if (todos.length > 0) {
  for (let todo of todos) {
    let element = document.createElement("li");
    element.innerText = todo;
    liste.appendChild(element);
  }
}

if (!liste.firstChild) {
  let element = document.createElement("p");
  element.innerText = "todos are empty";
  allTodos.appendChild(element);
}

addToDoButton.addEventListener("click", () => {
  addTodoFunc();
});

inputTodoInput.addEventListener("click", () => {
  addTodoFunc();
});

eraseCookieButton.addEventListener("click", () => {
  eraseCookie("todos");

  if (allTodos.lastElementChild.nodeName != "P") {
    let element = document.createElement("p");
    element.innerText = "todos are empty";
    allTodos.appendChild(element);
  }
});

erasetodoCookieButton.addEventListener("click", () => {
  removetodo(document.getElementById("input-todo").value);

  if (!liste.firstChild && allTodos.lastElementChild.nodeName != "P") {
    let element = document.createElement("p");
    element.innerText = "todos are empty";
    allTodos.appendChild(element);
  }
});
