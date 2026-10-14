<script lang="ts">
    import Interests from "$lib/sections/mainContent/Interests.svelte";
    import Poems from "$lib/components/Poems.svelte";
    import { rotatingDec, rotatingInc } from "$lib/utils.js";
    import Projects from "$lib/sections/mainContent/Projects.svelte";
    import SelectedPoems from "$lib/sections/mainContent/SelectedPoems.svelte";
    import type { Snippet } from "svelte";
    import ComingSoon from "../components/ComingSoon.svelte";
    type Section = {
        title: string;
        subtitle: string;
        content: Snippet;
    };

    // Content things
    const mainContents: Section[] = [
        {
            title: "Interests",
            subtitle: "Quirky arrangement of the things I like",
            content: Interests,
        },
        {
            title: "Tension High",
            subtitle:
                "(Name might change) My endless character and video game writing project",
            content: ComingSoon,
        },
        {
            title: "Projects",
            subtitle: "Amazing things you should check out right now fr",
            content: Projects,
        },
        {
            title: "Poems",
            subtitle: "Selection of the favorite poems I've written",
            content: SelectedPoems,
        },
    ];

    let menuIndex = $state(0);
    let currentMainContent = $derived(mainContents.at(menuIndex)!.content);

    // Menu buttons
    type Button = 0 | 1;
    const Button = {
        LEFT: 0,
        RIGHT: 1,
    } as const;

    let menuIndexDec = $derived(rotatingDec(menuIndex, mainContents.length));
    let menuIndexInc = $derived(rotatingInc(menuIndex, mainContents.length));

    // Fade in and out Animations
    const leftRightClasses = {
        animation: ["anime-menu-left", "anime-menu-right"],
        coming: ["coming-from-left", "coming-from-right"],
    } as const;
    let animateClasses = $state([""]);
    let buttonFunctionmentLocks = {
        animationing: false,
        uncloseting: false,
        transitioning: false,
    };

    // Saving data in variables because im lazy
    let lastContentIndex = 0;
    let lastButtonPressed: Button = Button.LEFT;

    function wipeContent(newContentIndex: number, whichButton: 0 | 1) {
        if (
            buttonFunctionmentLocks.animationing ||
            buttonFunctionmentLocks.uncloseting ||
            buttonFunctionmentLocks.transitioning
        )
            return;
        buttonFunctionmentLocks.animationing =
            buttonFunctionmentLocks.uncloseting =
            buttonFunctionmentLocks.transitioning =
                true;

        animateClasses = [leftRightClasses.animation[whichButton]];

        // The set content is called at ontransitionend
        lastContentIndex = newContentIndex;
        lastButtonPressed = whichButton;
    }

    function setContent() {
        animateClasses = [leftRightClasses.coming[lastButtonPressed]];
        setNewMenu(lastButtonPressed);
        currentMainContent = mainContents[lastContentIndex].content;
    }

    function setNewMenu(whichButton: Button) {
        if (whichButton === Button.LEFT)
            menuIndex =
                menuIndex !== 0 ? menuIndex - 1 : mainContents.length - 1;
        /*if (whichButton === Button.RIGHT)*/ else
            menuIndex =
                menuIndex !== mainContents.length - 1 ? menuIndex + 1 : 0;
    }
</script>

<section>
    <header>
        <menu id="content-menu">
            <li>
                <button
                    class={animateClasses}
                    onclick={() =>
                        wipeContent(
                            menuIndex !== 0
                                ? menuIndex - 1
                                : mainContents.length - 1,
                            Button.LEFT,
                        )}
                    ontransitionend={() => {
                        // Dual-lock system (that sounds very cool fr)
                        if (!buttonFunctionmentLocks.uncloseting)
                            buttonFunctionmentLocks.transitioning = false;
                        else buttonFunctionmentLocks.uncloseting = false;
                    }}
                >
                    {mainContents[menuIndexDec].title}
                </button>
            </li>
            <li>
                <hgroup id="content-title" class={animateClasses}>
                    <h2>
                        {mainContents[menuIndex].title}
                    </h2>
                    <h3 id="menu-subtitle">
                        {mainContents[menuIndex].subtitle}
                    </h3>
                </hgroup>
            </li>
            <li>
                <button
                    class={animateClasses}
                    onclick={() =>
                        wipeContent(
                            menuIndex !== mainContents.length - 1
                                ? menuIndex + 1
                                : 0,
                            Button.RIGHT,
                        )}
                >
                    {mainContents[menuIndexInc].title}
                </button>
            </li>
        </menu>
    </header>
    <span
        ontransitionstart={() => (buttonFunctionmentLocks.transitioning = true)}
        ontransitionend={() => setContent()}
        onanimationend={() => (buttonFunctionmentLocks.animationing = false)}
        id="the-actual-content"
        class={animateClasses}
    >
        {@render currentMainContent()}
    </span>
</section>

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

    section {
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
                font-size: 2rem;
                text-decoration: underline solid ghostwhite;
                cursor: pointer;

                @media (min-width: 750px) {
                    &:hover {
                        font-size: 2.8rem;
                        text-decoration: none;
                    }
                }

                position: relative;

                &.anime-menu-left,
                &.anime-menu-right {
                    font-size: 2rem;
                    opacity: 0;
                    transition-duration: 0.5s;
                    left: 0 !important;
                }

                &.coming-from-left,
                &.coming-from-right {
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
                        padding: 0 2rem;
                        font-size: 1.15rem;
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
