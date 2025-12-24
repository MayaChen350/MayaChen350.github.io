<script>
    import {getRecentTracks, lastFmApiUrl} from "$lib/index.js"
    import {onMount} from "svelte";

    // Last song listened to, powered by Last.fm (My beloved)
    let data = $state({
        albumImageLink: "/og/images/svg/loading.svg",
        isPlayingSong: false,
        recentTrackName: "Loading...",
        recentTrackArtist: "Loading...",
        trackUrl: null
    });

    // Text size depending of the text length
    let titleClassName = $state("")

    async function setData() {
        let result = await getRecentTracks()

        if (result === data) return;

        if (result.recentTrackName.length >= 25) {
            // console.log(result)
            titleClassName = "last-fm-text-gt25"
        } else if (result.recentTrackName.length >= 15)
            titleClassName = "last-fm-text-gt15"
        else
            titleClassName = ""
        data = result
    }

    onMount(async () => {
        await setData()
        setInterval(async () => {
                await setData()
            },
            30000);
    })
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

    /*Styles directly from the kobweb branch*/
    /*https://github.com/MayaChen350/MayaChen350.github.io/blob/5eefe11b9758d93869e1b652c8a318f7caca5116/site/src/jsMain/kotlin/io/github/mayachen350/website/components/sections/Header.kt*/

    /*Text length based classes:*/

    .last-fm-text-gt25 {
        h2, p {
            font-size: 1.15rem !important;
        }

        #recent-song-track {
            letter-spacing: 0 !important;

            @media (max-width: 1280px) {
                font-size: 2rem !important;
            }

            @media (max-width: 960px) {
                font-size: 1.5rem !important;
            }
        }
    }

    .last-fm-text-gt15 {
        @media (max-width: 960px) {
            h2, p {
                font-size: 1.25rem !important;
            }

            #recent-song-track {
                font-size: 2.15rem !important;
                letter-spacing: 0 !important;
            }
        }
    }
</style>

<svelte:head>
    <link rel="preconnect" href={lastFmApiUrl}>
</svelte:head>

<article id="lastfm">
    <img id="album-image" src={data.albumImageLink} alt="album artwork"/>
    <div id="now-playing" class={titleClassName !== "" ? titleClassName : ""}>
        <h2 id="time-of-listen">
            {#if data.isPlayingSong}
                Right now I'm listening to:
            {:else}
                Last song I listened to:
            {/if}
        </h2>
        <strong id="recent-song-track">
            <a id="track-url" href={data.trackUrl}>{data.recentTrackName}</a>
        </strong>
        <p id="recent-song-artist">By {data.recentTrackArtist}</p>
    </div>
</article>