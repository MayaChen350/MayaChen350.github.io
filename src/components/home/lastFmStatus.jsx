import React from "react";
import "./_lastFmStatus.scss";

export default function LastFmStatus() {
    // const url =
    //     "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=GChen3843&api_key=d7b26ab026668c5044cc4610d092bcd2&format=json";

    // let timeListened;

    // async function getRecentTracks() {
    //     const response = await fetch(url);

    //     const text = await response.text();

    //     const json = JSON.parse(text);
    //     const mostRecentTrack = json.recenttracks.track[0];

    //     albumImage.src = mostRecentTrack.image[2]["#text"];

    //     if (mostRecentTrack.hasOwnProperty("@attr"))
    //         timeListened = "Now listening to:";
    //     else timeListened = "Last song I listened to:";

    //     recentTrackName.innerText = mostRecentTrack.name;
    //     console.log(mostRecentTrack.name);
    //     recentTrackArtist.innerText = mostRecentTrack.artist["#text"];

    //     trackUrl.setAttribute("href", mostRecentTrack.url);
    //     start();
    // }
    // getRecentTracks();

    return (
        <aside className="last-fm-status">
            <img id="album-image" src="images/svg/loading.svg" alt="loading" />
            <div>
                <p id="time-of-listen">Last song I listened to:</p>
                <a id="track-url">
                    <p id="recent-song-track">Loading song...</p>
                </a>
                <p id="recent-song-artist">Loading artist...</p>
            </div>
        </aside>
    );
}
