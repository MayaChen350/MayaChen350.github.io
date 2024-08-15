// Last song listened to, powered by Last.fm (My beloved)
const albumImage = document.querySelector("#album-image");

const timeListened = document.querySelector("#time-of-listen");
const recentTrackName = document.querySelector("#recent-song-track");
const recentTrackArtist = document.querySelector("#recent-song-artist");
const recentTrackLink = document.querySelector("#recent-track-link");

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
    else timeListened.innerText = "Last listened to";

    recentTrackName.innerText = mostRecentTrack.name;
    console.log(mostRecentTrack.name);
    recentTrackArtist.innerText = mostRecentTrack.artist["#text"];

    recentTrackLink.setAttribute("href", mostRecentTrack.url);
}
getRecentTracks();
const lastFm = setInterval(getRecentTracks, 60000);
