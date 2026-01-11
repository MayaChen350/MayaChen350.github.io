<script>
    import {browser} from "$app/environment";

    let {children} = $props();

    if (browser) {
        document.body.style.visibility = "hidden";

        // Got lazy there
        // https://medium.com/@fbnlsr/how-to-get-rid-of-the-flash-of-unstyled-content-d6b79bf5d75f

        // Helper function
        let domReady = (cb) => {
            document.readyState === 'interactive' || document.readyState === 'complete'
                ? cb()
                : document.addEventListener('DOMContentLoaded', cb);
        };

        domReady(() => {
            // Display body when DOM is loaded
            document.body.style.visibility = 'visible';
        });
    }
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
