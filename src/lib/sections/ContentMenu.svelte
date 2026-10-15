<script lang="ts">
    import type { ContentType } from "../menu";

    const { contentType }: { contentType: ContentType } = $props();
</script>

<section>
    <header>
        <menu id="content-menu">
            <li>
                <a href={contentType.prev.link} data-sveltekit-reset="false">
                    {contentType.prev.title}
                </a>
            </li>
            <li>
                <hgroup id="content-title">
                    <h2>
                        {contentType.title}
                    </h2>
                    <h3 id="menu-subtitle">
                        {contentType.subtitle}
                    </h3>
                </hgroup>
            </li>
            <li>
                <a href={contentType.next.link} data-sveltekit-reset="false">
                    {contentType.next.title}
                </a>
            </li>
        </menu>
    </header>
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
