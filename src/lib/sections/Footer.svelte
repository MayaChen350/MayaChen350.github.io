<script>
    import githubIcon from '$lib/assets/github-icon.svg';
</script>

<style>
    footer {
        font-family: "Space Mono", monospace;
        font-size: 1.5rem;
        color: ghostwhite;

        padding: 3rem 0;

        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        gap: 1.5rem;

        a:hover {
            cursor: pointer;
            text-decoration: underline;
        }
    }
</style>

<footer>
    <a title="Source code" href="https://github.com/MayaChen350/MayaChen350.github.io/tree/svelte">
        <img alt="github logo" width="20" src={githubIcon}/></a>
    <a title="See my original page and its design" href="/og/index.html">Older Page</a>
    <a title="Copy my discord username" href="javascript:navigator.clipboard.writeText('@giratina_shiny')">Contact me!</a>
</footer>