<script>
    import dailyReferences from '$lib/data/daily_references.json' with {type: 'json'}
    import lodashColl from "lodash/collection.js";
    import lodash from "lodash";

    const {shuffle} = lodashColl;
    const {random} = lodash;

    const days = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"];
    const listThings = random(0, dailyReferences.length) === 1
        ? Array.from({length: 7}, () => "Everyday is exactly the same")
        : shuffle(dailyReferences).slice(0, 7);
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

        h3 {
            font-size: 1.7rem;
            text-align: center;
        }

        h4 {
            font-size: .85rem;
            text-align: center;
        }

        ul {
            font-size: 2rem;
        }

        @media (max-width: 750px) {
            flex-direction: column;
            align-items: start;
            padding: 3rem;
            font-size: 2rem;

            hgroup {
                padding-bottom: 1rem;
            }

            h2 {
                font-size: 3rem;
            }

            h3, h4 {
                text-align: start;
            }
        }
    }
</style>

<article id="list-things">
    <hgroup>
        <h2>Week Plan</h2>
        <h3>Or something</h3>
        <h4>(Idk anymore)</h4>
    </hgroup>
    <ul>
        {#each listThings as thing, i}
            <li>{days[i]}: <em>{thing}</em></li>
        {/each}
    </ul>
</article>