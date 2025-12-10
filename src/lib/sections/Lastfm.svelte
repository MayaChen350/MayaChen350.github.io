<script>
    import {getRecentTracks} from "$lib/index.js"
    import {onMount} from "svelte";

    // Last song listened to, powered by Last.fm (My beloved)
    let data = $state({
        albumImageLink: "/og/images/svg/loading.svg",
        isPlayingSong: false,
        recentTrackName: "Loading...",
        recentTrackArtist: "Loading...",
        trackUrl: "Loading..."
    });

    onMount(async () => {
        data = await getRecentTracks()
        setInterval(async () => {
                data = await getRecentTracks()
            },
            30000);
    })

    // TODO: Text size depending of the text length
</script>

<style>
    #lastfm {
        width: 100%;
        height: 10rem;
        color: rgb(255, 255, 255);
        border-bottom: 0.25rem ridge rgb(239, 194, 86);

        /*This was written in my Kobweb code but idk how really useful it was/is */
        font-size: 1.5rem;
        @media (min-width: 600px) /*and (max-width: 960px)*/ {
            height: 13.5rem;
            font-size: 2rem;
        }

        @media (min-width: 960px) {
            width: 100%;
            height: 100%;
        }

        display: flex;
        flex-direction: row;
        justify-content: flex-start;

        #album-image {
            height: 100%;
            max-height: 15.9rem;
        }

        #now-playing {
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;

            #recent-song-track {
                width: 100%;
                font-size: 2.5rem;
                font-weight: 700;
                letter-spacing: 0.85rem;
                color: rgb(239, 194, 86);

                @media (min-width: 600px) and (max-width: 960px) {
                    letter-spacing: 0;
                    font-size: 2rem;
                }

                @media (min-width: 960px) {
                    font-size: 5rem;
                }
            }
        }
    }
</style>

<div id="lastfm">
    <img id="album-image" src={data.albumImageLink} alt="album artwork"/>
    <div id="now-playing">
        <p id="time-of-listen">
            {#if data.isPlayingSong}
                Right now I'm listening to:
            {:else}
                Last song I listened to:
            {/if}
        </p>
        <p id="recent-song-track"><a id="track-url" href={data.trackUrl}>{data.recentTrackName}</a></p>
        <p id="recent-song-artist">By {data.recentTrackArtist}</p>
    </div>
</div>