<script>
    import romanNums from "$lib/data/roman_num_poems.json";
    import poems from "$lib/data/selected_poems.json";
    import Poems from "$lib/sections/mainContent/Poems.svelte";

    // TODO: Maybe put (some of?) that in a json instead
    const selectedPoemIndexes = [8, 29, 30, 40, 41, 42, 49, 50, 53, 54, 57, 62].map((value) => { return value -1 });
    const selectedRomanNums = selectedPoemIndexes.map((value) => {
        return romanNums[value];
    })
    const selectedPoems = selectedPoemIndexes.map((value) => {
        return poems[value]
    })
</script>

<Poems poems={selectedPoems} romanNums={selectedRomanNums}/>