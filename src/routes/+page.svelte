<script>
    import Lastfm from "$lib/sections/Lastfm.svelte";
    import Profile from "$lib/sections/Profile.svelte";
    import TheRest from "$lib/sections/TheRest.svelte";
    import Footer from "$lib/sections/Footer.svelte";
</script>

<style>
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
    <title>Maya's page</title>
    <meta name="description" content="MayaChen350's personal webpage">
    <meta name="color-scheme" content="dark">

    <!--Open Graph (og)-->
    <meta name="og:title" content="Maya's page">
    <meta name="og:type" content="website">
    <meta name="og:url" content="https://mayachen350.github.io">
    <meta name="og:image" content="https://avatars.githubusercontent.com/u/145165822">
    <meta name="og:description" content="unradical. yeah. ain't rocking. bogus">
    <meta name="og:locale" content="en_CA">

    <!--Discord embed-->
    <meta content="#efc256" data-react-helmet="true" name="theme-color">
</svelte:head>

<span id="cursed-thing"></span>
<Lastfm/>
<div>
    <main id="the-content">
        <Profile/>
        <TheRest/>
    </main>
    <Footer/>
</div>