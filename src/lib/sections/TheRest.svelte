<script>
    import Interests from "$lib/sections/mainContent/Interests.svelte"
    import ListThingie from "$lib/sections/profile/ListThingie.svelte";
    import Pfp from "$lib/sections/profile/Pfp.svelte";

    // Content things
    const mainContents = [
        Interests,
        ListThingie,
        Pfp
    ]
    let currentMainContent = $state(mainContents[0]);

    const menu = ["Interests", "Projects", "Poems"]
    let menuIndex = $state(0)

    // Fade in and out Animations
    let animateClasses = $state([])
    let setNewContentLock = false;

    function setNewContent(newContent, whichButton) {
        if (setNewContentLock) return;

        animateClasses.push("anime-menu");
        setTimeout(() => {
                setNewMenu(whichButton);
                currentMainContent = newContent
                animateClasses.pop();
            }, 1000
        )
    }

    // Menu buttons
    const WhichButton = Object.freeze({
        LEFT: 0,
        RIGHT: 1,
    })

    function setNewMenu(whichButton) {
        if (whichButton === WhichButton.LEFT)
            menuIndex = menuIndex !== 0 ? menuIndex - 1 : menu.length - 1;
        else /*if (whichButton === WhichButton.RIGHT)*/
            menuIndex = menuIndex !== menu.length - 1 ? menuIndex + 1 : 0;
    }
</script>

<style>
    @keyframes slide-right {
        0% {
            opacity: 0;
            left: -10%;
        }
    }

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

                &:not(.anime-menu) {
                    transition: opacity;
                    transition-delay: 1s;
                    transition-duration: 1s;
                    animation: none !important;
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
        }

        :global(:not(.anime-menu)) {
            transition: opacity;
            transition-duration: 0.5s;
            animation: slide-right 1.5s 1 0s normal ease none;
        }
    }
</style>

<!--suppress JSValidateTypes -->
<main>
    <div id="content-menu">
        <button class={animateClasses}
                onclick={() => setNewContent(mainContents[menuIndex !== 0 ? menuIndex - 1 : menu.length - 1],  WhichButton.LEFT)}>
            {menu[menuIndex !== 0 ? menuIndex - 1 : menu.length - 1]}
        </button>
        <h2 class={animateClasses}>
            {menu[menuIndex]}
        </h2>
        <button class={animateClasses}
                onclick={() => setNewContent(mainContents[menuIndex !== menu.length - 1 ? menuIndex + 1 : 0]/*, WhichButton.RIGHT*/)}>
            {menu[menuIndex !== menu.length - 1 ? menuIndex + 1 : 0]}
        </button>
    </div>
    <span ontransitionstart={() => setNewContentLock = true}
          onanimationend={() => setNewContentLock = false}
          id="the-actual-content"
          class={animateClasses}>
        {@render currentMainContent()}
    </span>
</main>