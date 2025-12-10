<script>
    import {getDiscordPfp} from "$lib";
    import {onMount} from "svelte";

    const socialMedias = [
        {
            name: "Github",
            username: "MayaChen350",
            link: "https://github.com/MayaChen350",
            pfp: "https://avatars.githubusercontent.com/u/145165822"
        },
        {
            name: "Discord",
            username: "giratina_shiny",
            link: null,
            pfp: null
        },
        {
            name: "Last.Fm",
            username: "GChen3843",
            link: "https://www.last.fm/user/GChen3843",
            pfp: "https://lustrous-croquembouche-c24038.netlify.app/lastfm.png"
        },
    ]

    let socMediaIndex = 0
    let currSocMedia = $state(socialMedias[socMediaIndex]);
    onMount(async () => {
        socialMedias[1].pfp = await getDiscordPfp()
        setInterval(() => {
            socMediaIndex = socMediaIndex !== socialMedias.length - 1 ? socMediaIndex + 1 : 0;
            currSocMedia = socialMedias[socMediaIndex];
        }, 10000)
    })

</script>

<svelte:head>
    {#each socialMedias as soc}
        {#if soc.pfp}
            <link rel="preload" as="image" href={soc.pfp}>
        {/if}
    {/each}
</svelte:head>

<style>
    #profile {
        text-align: center;
        font-size: 2rem;

        /*display: flex;*/
        /*flex-direction: column;*/
        /*align-self: start;*/
        /*align-items: center;*/

        padding: 1.5rem 0 0;
        border-bottom-color: rgb(24, 131, 255);
        border-bottom-style: hidden;

        img {
            width: 25rem;
            height: 25rem;
            border-radius: 50%;
        }

        /*p {*/
        /*    padding-top: 1.5rem;*/
        /*}*/
    }
</style>

<div class="pfp" id="profile">
    <img width="100%" height="100%" src={currSocMedia.pfp} alt="profile pic"/>
    <p id="soc-username"><a href={currSocMedia.link}>{currSocMedia.name}: {currSocMedia.username}</a></p>
</div>