let windowWidth;
let windowHeight;
let heightBlackBox;

var primaryColorHue = 0;
var secondaryColorOneHue = 299;
var secondaryColorTwoHue = 47;
var accentColorOneHue = 340;
var accentColorTwoHue = 253;

var lastFmColorHue = 255;

const setColors = () => {};

const start = () => {
    let windowWidth = window.innerWidth;
    let windowHeight =
        document.querySelector("body > header").clientHeight +
        document.querySelector("body > main > div.left-side").clientHeight;
    let heightBlackBox =
        windowHeight -
        document.querySelector("#normal-bio > div.header").scrollHeight -
        document.querySelector("body > header").clientHeight;
    // define inner styling
    document.querySelector("style").innerHTML = `
        :root {
          font-size: 62.5%;
          /*DEFAULT COLORS BY DEFAULT*/
          --primary-color:hsl(${primaryColorHue}, 0.00%, 25.90%);
          --secondary-color-1:hsl(${secondaryColorOneHue}, 57.00%, 55.30%);
          --secondary-color-2:hsl(${secondaryColorTwoHue}, 100.00%, 52.70%);
          --accent-color-1:hsl(${accentColorOneHue}, 100.00%, 84.10%);
          --accent-color-2:hsl(${accentColorTwoHue}, 45.50%, 82.70%);
          /*Window size in px*/
          --vw-px: ${windowWidth}; 
          --vh-px: ${windowHeight};
        }
        /*Set the black box size*/
        /*${windowHeight} - ${
        document.querySelector("#normal-bio > div.header").clientHeight
    } - ${document.querySelector("body > header").clientHeight}*/
        #normal-bio .main {
            height: ${heightBlackBox}px;
        }
        .last-fm-font-color {
          color: rgb(${lastFmColorHue}, 140, 140) !important;
        }    
        `;
};

start();

// Execute when the window is resized
addEventListener("resize", (event) => start());
addEventListener("animationend ", (event) => start());

// Discord pfp
async function getDiscordPfp() {
    const response = await fetch(
        "https://api.lanyard.rest/v1/users/491212588768821281"
    );

    const text = await response.text();
    const json = JSON.parse(text);

    const avatarID = json.data.discord_user.avatar;
    console.log(
        "https://cdn.discordapp.com/avatars/491212588768821281/" +
            avatarID +
            ".webp?size=256"
    );

    socialMediaPfps[1] =
        "https://cdn.discordapp.com/avatars/491212588768821281/" +
        avatarID +
        ".webp?size=256";
}

getDiscordPfp();

const socialMedias = [
    '<a id="social-media-username" class="yellow" href="https://github.com/MayaChen350">GITHUB: MayaChen350</a>',
    '<a id="social-media-username" class="yellow" href="#">DISCORD: giratina_shiny</a>',
    '<a id="social-media-username" class="yellow" href="https://www.last.fm/user/GChen3843">LAST.FM: GChen3843</a>',
];
let socialMediaPfps = [
    "https://lustrous-croquembouche-c24038.netlify.app/github.jfif",
    "",
    "https://lustrous-croquembouche-c24038.netlify.app/lastfm.png",
];

const mediaUsername = document.querySelector("#social-media-username");
const mediaPfp = document.querySelector("#pfp");
let indexMedia = 0;

const socialMediaChangeTimer = setInterval(() => {
    indexMedia = indexMedia !== socialMedias.length - 1 ? indexMedia + 1 : 0;
    mediaPfp.src = socialMediaPfps[indexMedia];
    mediaUsername.innerHTML = socialMedias[indexMedia];
}, 10000);

const dog = document.querySelector("#dog-picture");
const oikophobia = document.querySelector("#Oikophobia");

let isPhobiaReal = false;

oikophobia.addEventListener("mouseout", () => {
    dog.style.opacity = 1;
    isPhobiaReal = false;
});

oikophobia.addEventListener("mouseenter", () => {
    dog.style.opacity = 1;
    isPhobiaReal = true;
    setTimeout(() => {
        let int = setInterval(() => {
            if (dog.style.opacity !== 0 && isPhobiaReal === true)
                dog.style.opacity -= 0.003;
            else clearInterval(int);
        }, 100);
    }, 5000);
});

// Last song listened to, powered by Last.fm (My beloved)
const albumImage = document.querySelector("#album-image");

const timeListened = document.querySelector("#time-of-listen");
const recentTrackName = document.querySelector("#recent-song-track");
const recentTrackArtist = document.querySelector("#recent-song-artist");
const trackUrl = document.querySelector("#track-url");

const url =
    "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=GChen3843&api_key=d7b26ab026668c5044cc4610d092bcd2&format=json";

async function getRecentTracks() {
    const response = await fetch(url);

    const text = await response.text();

    const json = JSON.parse(text);
    const mostRecentTrack = json.recenttracks.track[0];

    albumImage.src = mostRecentTrack.image[2]["#text"];

    if (mostRecentTrack.hasOwnProperty("@attr"))
        timeListened.innerText = "Now listening to:";
    else timeListened.innerText = "Last song I listened to:";

    recentTrackName.innerText = mostRecentTrack.name;
    console.log(mostRecentTrack.name);
    recentTrackArtist.innerText = mostRecentTrack.artist["#text"];

    trackUrl.setAttribute("href", mostRecentTrack.url);
    start();
}
getRecentTracks();
const lastFm = setInterval(getRecentTracks, 60000);

const setNewColors = () => {
    let newRotation = Math.floor(Math.random() * 360);

    primaryColorHue = (primaryColorHue + newRotation) % 360;
    secondaryColorOneHue = (secondaryColorOneHue + newRotation) % 360;
    secondaryColorTwoHue = (secondaryColorTwoHue + newRotation) % 360;
    accentColorOneHue = (accentColorOneHue + newRotation) % 360;
    accentColorTwoHue = (accentColorTwoHue + newRotation) % 360;
    lastFmColorHue = (lastFmColorHue + newRotation) % 360;

    start();
};

// Random colors every 3h
setInterval(setNewColors, 10800000);
