<script>
    import poems from '$lib/data/selected_poems.json' with {type: 'json'}
    import romanNums from '$lib/data/roman_num_poems.json' with {type: 'json'}

    let poemIndex = $state(0);

    // if (browser) onMount(() => {
    //     const urlParms = new URLSearchParams(window.location.search);
    //     if (urlParms.has('poem'))
    //         poemIndex = urlParms.get('poem') - 1
    // })

    // TODO later: Make the menu fades away when untouched
</script>

<style>
    /*TODO: It can definitely be better*/
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

        #poem-menu {
            width: 100%;
            font-size: 1.75rem;
            font-style: normal;

            display: flex;
            justify-content: space-evenly;

            * {
                border: solid 0.005rem rgb(239, 194, 86);
                padding: 0.3rem;
            }

            .no-ty {
                opacity: 50%;
            }
        }

        #poem {
            padding: 0 1.5rem;
            max-width: 50rem;

            .paragraph {
                text-align: left;
            }
        }
    }
</style>

<div id="poems-box">
    <div id="poem">
        {#each poems[poemIndex] as paragraph}
            <div class="paragraph">
                {#each paragraph as line}
                    <p>{line}</p>
                {/each}
            </div>
            <br/>
        {/each}
    </div>
    <div id="poem-menu">
        <button class={poemIndex <= 0 ? "no-ty" : ""} onclick={() =>{ if (poemIndex > 0) poemIndex--}}>
            PREV
        </button>
        <span id="poem-index">{romanNums[poemIndex]}</span>
        <button class={poemIndex >= poems.length - 1 ? "no-ty" : ""}
                onclick={() => { if (poemIndex < poems.length - 1) poemIndex++}}>
            NEXT
        </button>
    </div>
</div>