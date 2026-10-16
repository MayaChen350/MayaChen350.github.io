<script>
    import { Content } from "../../../lib/menu";
    import ContentMenu from "../../../lib/sections/ContentMenu.svelte";
</script>

<ContentMenu contentType={Content.TensionHigh} />
<section>
    <main>
        <h2>About</h2>
        <p>
            <em>Tension High</em> is a <strong>character-driven</strong> game
            where your worst enemy is <em><strong>yourself.</strong></em>
        </p>
        <p>
            When war finally breaks out in your country, in an attempt to reduce
            casualties, the peaceful village you grew up in is forced to do what
            it has always done best: <em>Staying invisible.</em>
        </p>
        <p>
            Your high school, which fate has chosen other plans for, is spared.
            Due to the conditions outside,
            <strong>leaving is <em>no longer</em> an option.</strong>
            In a situation like this, yourself and others realise it fast enough:
            <strong>You are <em>trapped.</em> </strong>
        </p>

        <p>
            In the events that will follow, you will meet a handful of unique
            people for who, just like you, the future seems dim and uncertain. When
            the <em><strong>tension</strong></em>
            rises, some
            <strong>show who they are</strong>, while others,
            <strong><em>lose</em> it entirely.<strong></strong></strong>
        </p>
    </main>
</section>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Jersey+10+Charted&family=Special+Elite&display=swap");

    section {
        font-size: 1.75rem;
        color: ghostwhite;
        padding: 0 2rem;

        h2 {
            font-size: 4rem;
            font-weight: bold;
            letter-spacing: 1.5px;
            text-decoration: underline;
            padding-bottom: 0.5rem;
        }

        strong {
            color: salmon;
            font-weight: bold;
        }

        em {
            font-size: 2.2rem;
            font-weight: bold;
        }

        p {
            font-family: "Special Elite", system-ui;
            line-height: 2.8rem;
            padding-bottom: 2.5rem;
        }
    }
</style>
