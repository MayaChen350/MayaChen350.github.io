// The content
const content = document.querySelector(".content");

/////// NORMAL BIO ///////
const normalLayout = `<div id="normal-bio">
<div class="header">
    <img
        class="no-way"
        src="https://media1.tenor.com/m/u_9b73qLsRAAAAAC/persona3-yukaritakeba.gif"
        alt="Silly gif"
    />
    <div>
        <h2>Maya the Chen</h2>
        <h3>"Best girl"</h3>
    </div>
</div>

<div class="main">
    <div>
        <h3>Maya &lt;3</h3>
        <p>Just your average silly trans girl</p>
    </div>

    <div>
        <h3>The cool nerd :3</h3>
        <p>1000% real</p>
    </div>

    <div>
        <h3>Amazing</h3>
        <p>"Five stars"</p>
    </div>

    <div>
        <h3>Flawless</h3>
        <p>"Greater than great"</p>
    </div>

    <h2>Interests:</h2>

    <div>
        <ul>
            <li>Programming (duh!)</li>
            <li>Persona</li>
            <li>Fire Emblem</li>
            <li>Embedded systems</li>
            <li>Québec</li>
            <li>JJBA</li>
            <li>Music</li>
            <li>Pokémon (it's a curse)</li>
            <li>Ace attorney</li>
            <li>Toby Fox</li>
            <li>Muse</li>
            <li>2012 dubstep</li>
            <li>Devil May Cry</li>
            <li>Marvel</li>
            <li>No Man's Sky</li>
            <li>Psychology</li>
            <li>Giratina</li>
            <li>Clothes</li>
            <li>Turn-based RPGs</li>
            <li>Final Fantasy</li>
            <li>C</li>
            <li>J-pop</li>
            <li>Metal</li>
            <li>J-Metal</li>
            <li>Ultrakill</li>
            <li>Game modding</li>
            <li>Opening things</li>
            <li>Binary</li>
            <li>Trains</li>
            <li>Daredevil</li>
            <li>Helping people</li>
            <li>Working with people</li>
            <li>Computers</li>
            <li>Small computers</li>
            <li>Very small computers</li>
            <li>Arduino</li>
            <li>Being useful</li>
            <li>Rambling about low level programming</li>
        </ul>
    </div>
</div>
</div>`;

/////// LANGUAGE BIO ///////
const languageLayout = `
<div id="language-bio">
    <h2 class="pink">Hi I'm a nerd</h2>

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
    </div>
</div>`;

// Make the logo stay big
const click = (element, unclick = false) =>
    !unclick
        ? element.classList.add("clicked")
        : element.classList.remove("clicked");

const logoFunction = () =>
    Array.from(document.querySelectorAll(".logo")).forEach((logo, _, logos) =>
        logo.addEventListener("click", () => {
            logos.forEach((l) => click(l, true));
            click(logo);
        })
    );

// determine if night or day 🤓
const currentHour = new Date().getHours();
const bonjourBonsoir =
    currentHour > 5 && currentHour < 18
        ? () => {
              document.querySelector("#bonjour").innerText = "Bonjour!";
          }
        : () => {
              document.querySelector("#bonjour").innerText = "Bonsoir!";
          };

// Default content
const languageTextDefault = `<h3 id="bonjour">Bonjour!</h3>
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
const languageTexts = [
    `        <h3>C#</h3>
<div>
    <p>Java-OOP is so underated.</p>
    <p>
        But it's great! When the purpose of C# finally fitted into
        my brain, I understood that it's not actually that bad!
    </p>
    <p>Well, I like it tbh</p>
    <br />
    <p class="pink">
        I might learn Java in the future if we
        incitate me to.
    </p>
</div>`,
    `        <h3>C</h3>
<div>
<p>THE <span style="color:var(--accent-color-2);">PUREST</span> language! (after assembly)</p>
<br />
<p>Having a control over memory is really something I like.</p>
<p>C is also straight, simple yet not too simple, it's also used in embedded systems.</p>
<p>I love embedded systems.</p>
<p>I'm a beginner in low level programming and embedded systems though!</p>
</div>`,
    `        <h3>Web developpment</h3>
<div>
<p>I know HTML, CSS, SASS, (this page is made with SCSS) I've learned how to use bootstrap because of school and I sometimes see flexboxes when I look at my room.</p>
<p>Is that enough for now?</p>
<br />
<p>(I'm kinda bad at designing though)</p>
</div>`,
    `        <h3>Javascript</h3>
<div>
<p>Javascript is an extremely useful language because it's like the main language used on web. However, its syntax still annoys me sometimes.</p>
            <p>I'll be trying my best to become better in js though!</p>
</div>`,
    `        <h3>Python</h3>
<div>
<p>Why would I ever want to use python?</p>
<p>Easy! <span class="red">If we force me to.</span></p>
<p>Or well, I might use python if I want tasks to be easier with certain libraries,
or the main reason I would use python, to make my code easier to read for most people.</p>
<br />
<p>My 2 coding priorities are:</p>
<ol>
<li>Clean code</li> 
<li>Code that as most people as possible would be able to understand in the shortest time possible</li>
</ol>
<br />
<p>And obviously, if I work with or for others, like on a discord bot, I might use python for those others to understand easily what I write</p>
</div>`,
    `        <h3>Lua</h3>
<div>
<p>Lua. I don't have anything against Lua, but, from one of its utilities, roblox documentations are a nightmare.</p>
<p>Luau can be simple, the hardest part is to know what does what what to put where and to debug...</p>
<br />
<p>Good luck!</p>
<p>(I can still come help)<p>
</div>`,
    `        <h3>Modding</h3>
<div>
<p>(I never developped any game since 2018 but...)</p>
<p>I somehow know a little and am interested about how to make mods!</p>
<br />
<p>I've only tried with Ultrakill (an Unity game) with C# and Bepinex so far but if you have an idea...</p>
<p>Talk to me about it!</p>
<p>Minecraft mods (a classic) could be interesting too</p>
</div>`,
];

// Function to link the logos
const linkLogoContent = () =>
    Array.from(document.querySelectorAll(".logo")).forEach((logo, i) =>
        logo.addEventListener(
            "click",
            () =>
                (document.querySelector(".description").innerHTML =
                    languageTexts[i])
        )
    );

/////// THE PAGE ///////

// Choose the content
document.querySelector("#btn-nerd-bio").addEventListener("click", () => {
    content.innerHTML = languageLayout;
    // Add the functions
    logoFunction();
    bonjourBonsoir();
    linkLogoContent();
});

document
    .querySelector("#btn-normal-bio")
    .addEventListener("click", () => (content.innerHTML = normalLayout));
