<script>
    import transFlag from "$lib/assets/Transgender_Pride_flag.svg";
    import quebecFlag from "$lib/assets/Flag_of_Quebec.svg";
    import aceFlag from "$lib/assets/Asexual_Pride_Flag.svg";

    import Pfp from "$lib/sections/profile/Pfp.svelte";
    import ListThingie from "$lib/sections/profile/ListThingie.svelte";
</script>

<section id="first-section">
    <header>
        <Pfp />
        <article id="bio">
            <h2>About me</h2>
            <p id="my-name">
                <strong>Maya</strong>
                <img src={quebecFlag} class="flag" alt="flag of Québec" />
                <img
                    src={transFlag}
                    class="flag"
                    alt="transgender pride flag"
                />
                <img src={aceFlag} class="flag" alt="asexual pride flag" />
                <br />
                <small>(AKA Gira, Giratina_Shiny, GChen, MayChen)</small>
            </p>
            <p>
                21 years old, programmer and <i>transfem</i> in my free time.
                <br />
            </p>
            <p>
                You might also know me as a BIG fan of <abbr
                    title="Nine Inch Nails">NiN</abbr
                >, of Persona, or
                <i>functional programming</i>.
            </p>
        </article>
    </header>
    <ListThingie />
</section>

<style>
    #first-section {
        width: 100%;
        padding-bottom: 1.5rem;

        strong {
            font-weight: bold;
        }

        em {
            font-style: italic;
        }

        small {
            font-size: 0.6em;
        }

        header {
            h2 {
                font-size: 1.29rem;
                text-align: start;
            }

            #bio {
                line-height: 1.5;

                .flag {
                    height: 21px;
                    width: 31px;
                    object-fit: fill;
                }

                @media (max-width: 750px) {
                    font-size: 2.4rem;
                    font-weight: bold;
                    /*background-color: rgb(25, 22, 17);*/
                    color: ghostwhite;
                    padding: 0 4rem;
                    padding-top: 1.5rem;
                }

                @media (min-width: 750px) {
                    font-size: 2.7rem;
                    padding: 3rem 3rem;

                    p {
                        max-width: 60rem;
                    }
                }
            }

            @media (min-width: 750px) {
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: row;
            }

            p {
                margin-bottom: 4.2rem;
            }
        }
    }
</style>
