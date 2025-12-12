<script>
    import Interests from "$lib/sections/mainContent/Interests.svelte"
    import ListThingie from "$lib/sections/profile/ListThingie.svelte";

    const MainContents = Object.freeze({
        INTERESTS: Interests,
        TEST: ListThingie
    })

    let currentMainContent = $state(MainContents.INTERESTS)
    let animateClasses = $state([])

    function setNewContent(newContent) {
        animateClasses = ["anime-menu"];
        document.querySelector("#the-actual-content").ontransitionend = (e) => {
            animateClasses = ["anime-menu-trans"];
            setTimeout(() => {
                    currentMainContent = newContent
                animateClasses.pop();
                }, 1500
            )
        }
    }
</script>

<style>
    main {
        padding-bottom: 3rem;

        #content-menu {
            color: ghostwhite;
            font-weight: 800;
            text-align: center;
            padding: 3rem 0;

            display: flex;
            justify-content: space-around;
            align-items: center;

            button {
                transition: 0.2s font-size;
                font-size: 2.0rem;
                text-decoration: underline solid ghostwhite;

                &:hover {
                    font-size: 2.8rem;
                    text-decoration: none;
                }

                position: relative;
                left: 0;

                &.anime-menu {
                    font-size: 2.0rem;
                    opacity: 0;
                    transition: opacity;
                    transition-duration: 0.5s;
                }

                :not(.anime-menu) {
                    transition: opacity;
                    transition-duration: 0.5s;
                }
            }

            h2 {
                position: relative;
                left: 0;
                font-size: 4.6rem;

                &.anime-menu {
                    left: 5%;
                    opacity: 0;
                    transition: opacity, left;
                    transition-duration: 1s;
                }

                &.anime-menu-trans {
                    right: 0;
                    opacity: 0;
                }

                &:not(.anime-menu) {
                    transition: opacity;
                    transition-duration: 1s;
                }
            }
        }

        #the-actual-content {
            position: relative;
            left: 0;
            font-size: 4.6rem;

            &.anime-menu {
                left: 5%;
                opacity: 0;
                transition: opacity, left;
                transition-duration: 1s;
            }

            &.anime-menu-trans {
                right: 0;
                opacity: 0;
            }

            &:not(.anime-menu) {
                transition: opacity;
                transition-duration: 1s;
            }
        }
    }

</style>

<!--suppress JSValidateTypes -->
<main>
    <div id="content-menu">
        <button class={animateClasses} onclick={() => setNewContent(MainContents.TEST)}>Poems</button>
        <h2 class={animateClasses}>
            Interests
        </h2>
        <button class={animateClasses} onclick={() => setNewContent(MainContents.TEST)}>Projects
        </button>
    </div>
    <span id="the-actual-content" class={animateClasses}>
        {@render currentMainContent()}
    </span>
</main>