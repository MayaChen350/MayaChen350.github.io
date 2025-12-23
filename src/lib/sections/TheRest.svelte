<script>
    import Interests from "$lib/sections/mainContent/Interests.svelte"
    import Poems from "$lib/sections/mainContent/Poems.svelte";
    import {rotatingDec, rotatingInc} from "$lib/utils.js";
    import Projects from "$lib/sections/mainContent/Projects.svelte";

    // Content things
    const mainContents = [
        Interests,
        Projects,
        Poems
    ]
    let currentMainContent = $state(mainContents[0]);

    // Menu buttons
    const WhichButton = Object.freeze({
        LEFT: 0,
        RIGHT: 1,
    })

    const menu = ["Interests", "Projects", "Poems"]
    const menuSubtitle = ["Quirky arrangement of the things I like", "Amazing things you should check out right now fr", "Selection of the favorite poems I've written"]

    let menuIndex = $state(0)
    let menuIndexDec = $derived(rotatingDec(menuIndex, menu.length))
    let menuIndexInc = $derived(rotatingInc(menuIndex, menu.length))

    // Fade in and out Animations
    const leftRightClasses = Object.freeze({
        animation: ["anime-menu-left", "anime-menu-right"],
        coming: ["coming-from-left", "coming-from-right"],
    })
    let animateClasses = $state([""])
    let buttonFunctionmentLocks = {animationing: false, uncloseting: false, transitioning: false};

    // Saving data in variables because im lazy
    let lastContentIndex = 0;
    /** @type {0|1} */
    let lastButtonPressed = WhichButton.LEFT;

    /**
     * @param {number} newContentIndex
     * @param {0|1} whichButton
     */
    function wipeContent(newContentIndex, whichButton) {
        if (buttonFunctionmentLocks.animationing || buttonFunctionmentLocks.uncloseting ||
            buttonFunctionmentLocks.transitioning) return;
        buttonFunctionmentLocks.animationing = true;
        buttonFunctionmentLocks.uncloseting = true;
        buttonFunctionmentLocks.transitioning = true;


        animateClasses = [leftRightClasses.animation[whichButton]];

        // The set content is called at ontransitionend
        lastContentIndex = newContentIndex;
        lastButtonPressed = whichButton;
    }

    function setContent() {
        animateClasses = [leftRightClasses.coming[lastButtonPressed]];
        setNewMenu(lastButtonPressed);
        currentMainContent = mainContents[lastContentIndex]
    }

    /**
     * @param {0|1|null} whichButton
     */
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

    @keyframes slide-left {
        0% {
            opacity: 0;
            left: 10%;
        }
    }

    main {
        #content-menu {
            color: ghostwhite;
            font-weight: 800;
            text-align: center;
            padding: 3rem 0;

            display: flex;
            justify-content: space-around;
            align-items: center;

            button {
                transition-property: opacity;
                font-size: 2.0rem;
                text-decoration: underline solid ghostwhite;
                cursor: pointer;

                &:hover {
                    font-size: 2.8rem;
                    text-decoration: none;
                }

                position: relative;

                &.anime-menu-left, &.anime-menu-right {
                    font-size: 2.0rem;
                    opacity: 0;
                    transition-duration: 0.5s;
                    left: 0 !important;
                }

                &.coming-from-left, &.coming-from-right {
                    transition-delay: 1s !important;
                    transition-duration: 0.8s !important;
                    animation: none !important;
                }
            }

            #content-title {
                position: relative;
                left: 0;
                right: 0;

                h2 {
                    font-size: 4.6rem;
                }

                @media (max-width: 750px) {
                    #menu-subtitle {
                        font-size: 1.49rem;
                    }
                }
            }
        }

        #the-actual-content {
            position: relative;
            left: 0;
            right: 0;
            font-size: 4.6rem;
        }

        :global(.anime-menu-left, .anime-menu-right) {
            opacity: 0;
            transition-duration: 1s;
        }

        :global(.anime-menu-left) {
            left: 5%;
            transition-property: opacity, left;
        }

        :global(.anime-menu-right) {
            right: 5%;
            transition-property: opacity, right;
        }

        :global(.coming-from-left, .coming-from-right) {
            transition: opacity;
            animation-duration: 1.5s;
            animation-timing-function: ease;
        }

        :global(.coming-from-left) {
            animation-name: slide-left;
        }

        :global(.coming-from-right) {
            animation-name: slide-right;
        }
    }
</style>

<main>
    <div id="content-menu">
        <button class={animateClasses}
                onclick={() => wipeContent(menuIndex !== 0 ? menuIndex - 1 : menu.length - 1,  WhichButton.LEFT)}
                ontransitionend={() => {
                    // Dual-lock system (that sounds very cool fr)
                    if (!buttonFunctionmentLocks.uncloseting)
                        buttonFunctionmentLocks.transitioning = false;
                    else buttonFunctionmentLocks.uncloseting = false;
                }}>
            {menu[menuIndexDec]}
        </button>
        <div id="content-title" class={animateClasses}>
            <h2>
                {menu[menuIndex]}
            </h2>
            <h4 id="menu-subtitle">{menuSubtitle[menuIndex]}</h4>
        </div>
        <button class={animateClasses}
                onclick={() => wipeContent(menuIndex !== menu.length - 1 ? menuIndex + 1 : 0, WhichButton.RIGHT)}>
            {menu[menuIndexInc]}
        </button>
    </div>
    <!--suppress JSValidateTypes -->
    <span ontransitionstart={() => buttonFunctionmentLocks.transitioning = true}
          ontransitionend={() => setContent()}
          onanimationend={() => buttonFunctionmentLocks.animationing = false}
          id="the-actual-content"
          class={animateClasses}>
        {@render currentMainContent()}
    </span>
</main>