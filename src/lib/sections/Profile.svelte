<script>
    import Pfp from "$lib/sections/profile/Pfp.svelte";
    import ListThingie from "$lib/sections/profile/ListThingie.svelte";
</script>

<style>
    #first-section {
        width: 100%;


        #bio {
            font-size: 1.95rem;

            @media (max-width: 750px) {
                font-size: 2.8rem;
                font-weight: bold;
                /*background-color: rgb(25, 22, 17);*/
                color: ghostwhite;
                padding: 3rem 7rem;
            }
        }

        header {
            @media (min-width: 750px) {
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: row;

                #bio {
                    font-size: 2.7rem;
                    padding: 3rem 3rem;

                    p {
                        max-width: 60rem;
                    }
                }
            }

            p:not(:last-child) {
                margin-bottom: 4.2rem;
            }
        }
    }
</style>

<section id="first-section">
    <header>
        <Pfp/>
        <article id="bio">
            <h2 style="font-size:1.29rem;text-align:start">About me</h2>
            <p>My name is <strong>Maya</strong>.</p>
            <p>20 years old, programmer and <i>transfem</i> in my free time.</p>
            <p>You might also know me as a BIG fan of <abbr title="Nine Inch Nails">NiN</abbr>, of Persona, or
                <i>functional programming</i>.</p>
        </article>
    </header>
    <ListThingie/>
</section>