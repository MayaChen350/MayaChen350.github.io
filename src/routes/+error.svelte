<script>
    import {browser} from "$app/environment";
    import {onMount} from "svelte";

    let visible = $state(false);

    if (browser) {
        onMount(() => {
            const currentURL = window.location.href;
            const lowerCaseURL = currentURL.toLowerCase();
            if (currentURL !== lowerCaseURL) {
                location.replace(lowerCaseURL);
            } else if (window.location.pathname.endsWith("mayascope")) {
                location.pathname = "/Mayascope";
            } else {
                visible = true
            }
        })
    }
</script>


<h1 style="visibility: {visible ? 'visible' : 'hidden'}">Error page!</h1>