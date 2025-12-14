<script>
    import poems from '$lib/data/selected_poems.json' with {type: 'json'}
    import {browser} from "$app/environment";
    import {onMount} from "svelte";

    let poemIndex = $state(0);

    if (browser) onMount(() => {
        const urlParms = new URLSearchParams(window.location.search);
        if (urlParms.has('poem'))
            poemIndex = urlParms.get('poem') - 1
    })

    // TODO later: Make the menu fades away when untouched
</script>

<style>
    #poems-box {
        width: 100%;
        min-height: 100vh;
        padding: 5rem 0;
        background-color: black;
        border-radius: 10px;

        font-size: 2.8rem;
        font-style: italic;

        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

        #poem {
            padding: 0 1.5rem;

            .paragraph {
                text-align: left;
            }

            #poem-menu {
                font-size: 1.75rem;
                font-style: normal;

                display: flex;
                justify-content: space-between;

                * {
                    border: solid 0.005rem rgb(239, 194, 86);
                    padding: 0.3rem;
                }

                .no-ty {
                    opacity: 50%;
                }
            }
        }
    }
</style>

<div id="poems-box">
    <div id="poem">
        <div id="poem-menu">
            <button class={poemIndex <= 0 ? "no-ty" : ""} onclick={() =>{ if (poemIndex > 0) poemIndex--}}>
                PREV
            </button>
            <!--TODO: Poem index in Roman Numeral (obtained at build time)-->
            <span id="poem-index">{poemIndex + 1}</span>
            <button class={poemIndex >= poems.length - 1 ? "no-ty" : ""}
                    onclick={() => { if (poemIndex <= poems.length - 1) poemIndex++}}>
                NEXT
            </button>
        </div>
        {#each poems[poemIndex] as paragraph}
            <div class="paragraph">
                {#each paragraph as line}
                    <p>{line}</p>
                {/each}
            </div>
            <br/>
        {/each}
    </div>
</div>