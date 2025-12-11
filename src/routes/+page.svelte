<script>
    import Lastfm from "$lib/sections/Lastfm.svelte";
    import Profile from "$lib/sections/Profile.svelte";
    import TheRest from "$lib/sections/TheRest.svelte";
</script>

<style>
    :global body {
        background-color: rgb(9, 9, 9);
        font-family: "Quintessential", sans-serif;
        font-size: 1.8rem;
        color: #efc256;
    }

    :has(> #the-content) {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    #the-content {
        width: 100%;
        max-width: 900px;
    }

    /*Secret thing behind the background's functioning*/
    #cursed-thing {
        position: fixed;
        width: 100vw;
        height: 100vh;
        right: 0;
        z-index: -1;

        background-image: linear-gradient(315deg, rgb(230, 180, 52), rgb(9, 9, 9) 40%);

        @media (min-width: 600px) and (max-width: 1280px) {
            background-image: linear-gradient(315deg, rgb(230, 180, 52), rgb(9, 9, 9) 60%);
        }

        @media (min-width: 1280px) {
            width: 70%;
            background-image: linear-gradient(315deg, rgb(230, 180, 52), rgb(9, 9, 9) 40%);
        }
    }
</style>

<svelte:head>
    <base href="/"/>
    <title>Maya's page</title>
    <meta content="My page!!! Did you know that in terms of computer science, Porygon is..." name="description">
    <link href="/reset.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin="anonymous" href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Space+Mono&amp;display=swapfamily=Nunito+Sans:wght@200&amp;family=Sacramento&amp;display=swap&amp;family=Lumanosimo&amp;family=Quintessential&amp;display=swap"
          rel="stylesheet">
    <meta content="light dark" name="color-scheme">
    <meta content="Maya's page" name="og:title">
    <meta content="unradical. yeah. ain't rocking. bogus" name="og:description">
    <meta content="https://mayachen350.github.io" name="og:url">
    <meta content="https://avatars.githubusercontent.com/u/145165822" name="og:image">
    <meta content="#efc256" data-react-helmet="true" name="theme-color">
    <style>
        :root {
            font-size: 62.5%;
        }
    </style>
</svelte:head>

<div id="cursed-thing"></div>
<Lastfm/>
<div>
    <div id="the-content">
        <Profile/>
        <TheRest/>
    </div>
</div>