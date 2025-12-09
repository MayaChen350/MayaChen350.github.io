<script>
    import Lastfm from "$lib/sections/Lastfm.svelte";
    import Profile from "$lib/sections/Profile.svelte";
    import TheRest from "$lib/sections/TheRest.svelte";
</script>

<svelte:head>
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

<Lastfm/>
<Profile/>
<TheRest/>