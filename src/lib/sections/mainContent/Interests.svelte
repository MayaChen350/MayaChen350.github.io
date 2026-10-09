<script>
    import interests from "$lib/data/interests.json" with { type: "json" };
    import { onMount } from "svelte";

    let allInterests = $state(interests);
    onMount(() => {
        // Make the list finish by a third element always
        switch (interests.length % 3) {
            case 2:
                allInterests.push("Other stuff");
                break;
            case 1:
                allInterests.push("Women");
                allInterests.push("Other stuff");
                break;
        }
    });
</script>

<ul>
    {#each allInterests as interest}
        <li>{@html interest}</li>
    {/each}
</ul>

<style>
    h2 {
        font-size: 4.6rem;
        color: ghostwhite;
        font-weight: 800;
        text-align: center;
        padding: 3rem 0;
    }

    ul :global {
        padding-top: 5rem;
        font-size: 2.7rem;
        text-align: center;

        column-count: 2;

        a {
            text-decoration: underline;
        }

        :nth-child(3n) {
            padding: 3.5rem 0;
            column-span: all;
            justify-content: center;
        }

        @media (min-width: 750px) {
            font-size: 3rem;
        }
    }
</style>
