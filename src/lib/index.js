// place files you want to import through the `$lib` alias in this folder.

export async function getRecentTracks() {
    const url =
        "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=GChen3843&api_key=d7b26ab026668c5044cc4610d092bcd2&format=json";

    const response = await fetch(url);
    const text = await response.text();

    const mostRecentTrack = JSON.parse(text).recenttracks.track[0];

    return {
        albumImageLink: mostRecentTrack.image[2]["#text"],
        isPlayingSong: mostRecentTrack.hasOwnProperty("@attr"),
        recentTrackName: mostRecentTrack.name,
        recentTrackArtist: mostRecentTrack.artist["#text"],
        trackUrl: mostRecentTrack.url
    };
}

export async function getDiscordPfp() {
    const response = await fetch(
        "https://api.lanyard.rest/v1/users/491212588768821281"
    );

    const text = await response.text();
    const json = JSON.parse(text);

    const avatarID = json.data.discord_user.avatar;

    return "https://cdn.discordapp.com/avatars/491212588768821281/" + avatarID + ".webp?size=256";
}