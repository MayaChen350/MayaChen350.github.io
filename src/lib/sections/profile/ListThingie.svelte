<script>
    import dailyReferences from '$lib/data/daily_references.json' with {type: 'json'}
    import pkg from "lodash/collection.js";

    const {shuffle} = pkg;

    const days = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"];
    const listThings = shuffle(dailyReferences).slice(0, 7);
</script>

<style>
    #list-things {

        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-evenly;

        padding: 2rem;
        width: 100%;
        /*margin: auto;*/
        /*max-width: 900px;*/
        justify-self: center;
        color: ghostwhite;
        background-color: rgb(25, 22, 17);

        @media (max-width: 900px) {
            border-top: dotted 0.2rem rgb(239, 194, 86);
            border-bottom: dotted 0.2rem rgb(239, 194, 86);
        }


        @media (min-width: 900px) {
            border: 1px ridge rgb(239, 194, 86);
            border-radius: 20px;
        }


        h2 {
            font-size: 3.5rem;
            font-weight: 700;
        }

        ul {
            font-size: 2rem;
        }

        @media (max-width: 750px) {
            flex-direction: column;
            align-items: start;
            padding: 3rem;
            font-size: 2rem;

            h2 {
                font-size: 3rem;
                padding-bottom: 1rem;
            }
        }
    }
</style>

<div id="list-things">
    <h2>Week Plan</h2>
    <ul>
        {#each listThings as thing, i}
            <li>{days[i]}: {thing}</li>
        {/each}
    </ul>
</div>