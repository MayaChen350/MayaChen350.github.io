<script>
    import Poems from "$lib/sections/mainContent/Poems.svelte";
    import {onMount} from "svelte";
    import {browser} from "$app/environment";

    let poemIndex = $state(0);

    if (browser) onMount(() => {
        const urlParms = new URLSearchParams(window.location.search);
        if (urlParms.has('num'))
            poemIndex = urlParms.get('num') - 1
    })
</script>

<Poems poemIndex={poemIndex} />