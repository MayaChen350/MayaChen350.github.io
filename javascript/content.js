// The content
const content = document.querySelector(".content");

/////// LANGUAGE BIO ///////
const languageContent = `<h2 class="pink">Hi I'm a nerd</h2>

<div id="language-menu">
  <img id="csharp" class="logo" src="images/logos/c-sharp.svg" alt="C# logo" />
  <img id="c" class="logo" src="images/logos/C.svg" alt="C logo" />
  <img id="html" class="logo" src="images/logos/HTML5.svg" alt="HTML logo" />
  <img id="js" class="logo" src="images/logos/JavaScript.svg" alt="Js logo" />
  <img id="python" class="logo" src="images/logos/Python.svg" alt="Python logo" />
  <img id="lua" class="logo" src="images/logos/Lua.svg" alt="Lua logo" />
  <img id="modding" class="logo" src="images/logos/Bepinex.svg" alt="Bepinex logo" />
</div>

<div class="description">
  <h3 id="bonjour">Bonjour!</h3>
  <div>
    <p>I'm a programmer :p</p>
    <p>
      Right now I'm prefering low level languages and have crazy ideas
      like... opening microwaves but you can still check what I can say
      about the languages above
    </p>
    <br />
    <p>Just click on a language!</p>
  </div>
</div>`;

// Where the language content is going to be added
const languageDescription = document.querySelector("#description");

// Default content
const contentDefault = `<h3 id="bonjour">Bonjour!</h3>
<div>
  <p>I'm a programmer :p</p>
  <p>
    Right now I'm prefering low level languages and have crazy ideas
    like... opening microwaves but you can still check what I can say
    about the languages above
  </p>
  <br />
  <p>Just click on a language!</p>
</div>`;

// Language contents

/////// THE PAGE ///////

// Choose the content
document
    .querySelector("#btn-nerd-bio")
    .addEventListener("click", () => (content.innerHTML = languageContent));
