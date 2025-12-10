<script>
    import Pfp from "$lib/sections/profile/Pfp.svelte";
    import ListThingie from "$lib/sections/profile/ListThingie.svelte";
</script>

<style>
    #first-section {
        width: 100%;
        
        @media (min-width: 960px) {
            display: flex;
            align-items: flex-start;
            flex-direction: row;
        }

        :global(#profile) {
            flex-grow: 1;
        }
        :global(#list-things) {
            flex-grow: 3;
        }
    }
</style>

<div id="first-section">
    <Pfp className=".pfp"/>
    <ListThingie  />
</div>