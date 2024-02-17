const socialMedias = [
  '<a id="social-media-username" class="yellow" href="https://github.com/MayaChen350">GITHUB: MayaChen350</a>',
  '<a id="social-media-username" class="yellow" href="#">DISCORD: giratina_shiny</a>',
  '<a id="social-media-username" class="yellow" href="https://www.last.fm/user/GChen3843">LAST.FM: GChen3843</a>',
];
const socialMediaPfps = [
  "images/PFP/github.jfif",
  "images/PFP/discord.webp",
  "images/PFP/lastfm.png",
];

const mediaUsername = document.querySelector("#social-media-username");
const mediaPfp = document.querySelector("#pfp");
let indexMedia = 0;

const socialMediaChangeTimer = setInterval(() => {
  indexMedia = indexMedia !== socialMedias.length - 1 ? indexMedia + 1 : 0;
  mediaPfp.src = socialMediaPfps[indexMedia];
  mediaUsername.innerHTML = socialMedias[indexMedia];
}, 10000);

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
}
getRecentTracks();
const lastFm = setInterval(getRecentTracks, 60000);
