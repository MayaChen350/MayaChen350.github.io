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
        }, 5000)
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

        padding: 1.5rem 0 0;

        img {
            width: 25rem;
            height: 25rem;
            border-radius: 50%;
        }
    }
</style>

<article class="pfp" id="profile">
    <img width="100%" height="100%" src={currSocMedia.pfp} alt="profile pic"/>
    <h2 id="soc-username"><a href={currSocMedia.link}>{currSocMedia.name}: {currSocMedia.username}</a></h2>
</article>