<script>
    import interests from '$lib/data/interests.json' with {type: 'json'}
    import {onMount} from "svelte";

    let allInterests = $state(interests);
    onMount(() => {
            // Make the list finish by a third element always
            switch (interests.length % 3) {
                case 0:
                    allInterests.push("Other stuff")
                    break;
                case 1:
                    allInterests.push("Programming")
                    allInterests.push("Other stuff")
                    break;
            }
        }
    )
</script>

<style>
    h2 {
        font-size: 4.6rem;
        color: ghostwhite;
        font-weight: 800;
        text-align: center;
        padding: 3rem 0;
    }

    ul {
        font-size: 3rem;
        text-align: center;

        column-count: 2;

        :nth-child(3n) {
            padding: 2rem 0;
            column-span: all;
            justify-content: center;
        }
    }
</style>

<ul>
    {#each allInterests as interest}
        <li>{interest}</li>
    {/each}
</ul>