<script>
    let {children} = $props();
</script>

<style>
    :global body {
        background-color: rgb(9, 9, 9);
        font-family: "Quintessential", sans-serif;
        font-size: 1.8rem;
        color: #efc256;
    }
</style>

<svelte:head>
    <!--Google Font-->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Space+Mono&amp;display=swapfamily=Nunito+Sans:wght@200&amp;family=Sacramento&amp;display=swap&amp;family=Lumanosimo&amp;family=Quintessential&amp;display=swap"
    >
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous">

    <!--Reset css-->
    <link rel="stylesheet" href="reset.css">
    <style>
        :root {
            font-size: 62.5%;
        }
    </style>
</svelte:head>

{@render children()}
