import React from "react";
import "./_lastFmStatus.scss";
import { useState, useEffect } from "react";

export default function LastFmStatus() {
    const [recentTrack, setRecentTrack] = useState({
        timeListened: "",
        albumImage: "",
        trackUrl: "",
        recentTrackName: "",
        recentTrackArtist: "",
    });

    useEffect(() => {
        let ignore = false;
        fetch(
            "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=GChen3843&api_key=d7b26ab026668c5044cc4610d092bcd2&format=json"
        )
            .then((response) => response.text())
            .then((text) => {
                if (!ignore) {
                    const json = JSON.parse(text);

                    const mostRecentTrack = json?.recenttracks?.track[0];

                    const albumImage = mostRecentTrack?.image[2]["#text"];

                    let timeListened;

                    if (mostRecentTrack?.hasOwnProperty("@attr"))
                        timeListened = "Now listening to:";
                    else timeListened = "Last song I listened to:";

                    const recentTrackName = mostRecentTrack?.name;
                    console.log(mostRecentTrack?.name);
                    const recentTrackArtist = mostRecentTrack?.artist["#text"];

                    const trackUrl = mostRecentTrack?.url;

                    setRecentTrack({
                        timeListened: timeListened,
                        albumImage: albumImage,
                        trackUrl: trackUrl,
                        recentTrackName: recentTrackName,
                        recentTrackArtist: recentTrackArtist,
                    });
                }
            });
        return () => {
            ignore = true;
        };
    });

    return (
        <aside className="last-fm-status">
            <img
                id="album-image"
                src={recentTrack?.albumImage ?? "/images/loading.svg"}
                alt="loading"
            />
            <div>
                <p id="time-of-listen">
                    {recentTrack?.timeListened ?? "Last song I listened to:"}
                </p>
                <a id="track-url" href={recentTrack?.trackUrl ?? ""}>
                    <p id="recent-song-track">
                        {recentTrack?.recentTrackName ?? "Loading song..."}
                    </p>
                </a>
                <p id="recent-song-artist">
                    {recentTrack?.recentTrackArtist ?? "Loading artist..."}
                </p>
            </div>
        </aside>
    );
}
