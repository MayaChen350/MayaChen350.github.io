<script>
    import romanNums from "$lib/data/roman_num_poems.json";
    import poems from "$lib/data/poems.json";
    import Poems from "$lib/components/Poems.svelte";
    import selectedPoemIndexes from "$lib/data/selected_poems_indexes.json"

    const selectedRomanNums = selectedPoemIndexes.map((value) => {
        return romanNums[value];
    })
    const selectedPoems = selectedPoemIndexes.map((value) => {
        return poems[value]
    })
</script>

<Poems poems={selectedPoems} romanNums={selectedRomanNums}/>